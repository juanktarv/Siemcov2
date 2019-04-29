package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.MovimientoClientes;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.MovimientoClienteService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="cobranzaMB")
@ViewScoped
public class CobranzaMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MovimientoClientes movimientoClienteSelec;
	private MovimientoClienteService movimientoClienteService;
	private String mes;
	private String anio;
	private String tipo_comprobante;
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private Boolean disableRespuesta; 
	private Boolean disableBuscar;  
	private LazyDataModel<MovimientoClientes> listamovimientos;
	private List<MovimientoClientes> listaFiltroMovimientos;
	private Cliente clienteEncontrado;
	private ClienteService clienteService;
	private String nroserie_documento;
	
	public CobranzaMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.movimientoClienteSelec= new MovimientoClientes();
		this.movimientoClienteService= new MovimientoClienteService();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.disableBuscar = Boolean.TRUE; 
		this.disableRespuesta = Boolean.TRUE; 
		this.clienteEncontrado= new Cliente();
		this.clienteService= new ClienteService();
		this.nroserie_documento="";
		
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Cliente> consultarCliente(String query) throws Exception {
		System.out.println("entrando autocomplete");
        List<Cliente> allClients = this.clienteService.findAll();
        List<Cliente> filteredClients = new ArrayList<Cliente>();
         
        for (int i = 0; i < allClients.size(); i++) {
        	Cliente skin = allClients.get(i);
            if(skin.getNombre_cab().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredClients.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredClients.size());
         
        return filteredClients;
    }
	
	public void onItemCliente(SelectEvent event)  throws Exception{
		
				String s = event.getObject().toString();
				List<Cliente> allProducts = this.clienteService.findAll();
		        //List<Cliente> filteredProducts = new ArrayList<Cliente>();
				
				for (int i = 0; i < allProducts.size(); i++) {
		        	Cliente skin = allProducts.get(i);
		            if(skin.getNombre_cab().equals(s)) {
		            	//filteredProducts.add(skin);
		            	//this.comprobanteSelec.setTipo_comprobante(skin.getDescripcion_prod_det());
		            	this.clienteEncontrado = skin;
		            	break;
		            	
		            }
		        }		
				
	}
	
	public void listarMovimientosFiltros(){
		System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
		
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.FALSE; 		
		
		this.listamovimientos = new LazyDataModel<MovimientoClientes>() {
			private static final long serialVersionUID = 1L;
			private List<MovimientoClientes> datasource; 
			private Integer totalRow=0;
			
			
			@Override  
			public MovimientoClientes getRowData(String rowKey){
				 for(MovimientoClientes e : datasource) {  
                     if(e.getId_comprobante().equals(rowKey))  
                         return e;  
                 }  

                 return null;  
			}
			
			 @Override
             public void setRowIndex(int rowIndex){
                 if (rowIndex == -1 || getPageSize() == 0) {
                     super.setRowIndex(-1);
                 }
                 else
                     super.setRowIndex(rowIndex % getPageSize());
             }
			 
			 @Override  
	            public Object getRowKey(MovimientoClientes e) {  
	                return e.getId_comprobante();  
	            }  
			 @Override  
	            public List<MovimientoClientes> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {              
					try {
						totalRow = movimientoClienteService.countCompByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, filters);
												
						  datasource = movimientoClienteService.listComprobantesByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, first, pageSize, filters, "e.numero_serie_documento_cab", "DESC");
						 return datasource;
					} catch (Exception e) {
						System.out.println("NULL ");
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
	            } 
			 
			 @Override  
				public int getRowCount() {     
					return totalRow;
	            }			 
		};
	}
	
	public void buscarClienteComprobante(){
		
	}
	
	public void onItemDocumento(SelectEvent event)  throws Exception{
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.TRUE; 
		
	}

	public MovimientoClientes getMovimientoClienteSelec() {
		return movimientoClienteSelec;
	}

	public void setMovimientoClienteSelec(MovimientoClientes movimientoClienteSelec) {
		this.movimientoClienteSelec = movimientoClienteSelec;
	}

	public MovimientoClienteService getMovimientoClienteService() {
		return movimientoClienteService;
	}

	public void setMovimientoClienteService(MovimientoClienteService movimientoClienteService) {
		this.movimientoClienteService = movimientoClienteService;
	}


	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getTipo_comprobante() {
		return tipo_comprobante;
	}

	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesComprobante() {
		return listTablaTablasDetallesComprobante;
	}

	public void setListTablaTablasDetallesComprobante(List<TablaTablasDetalle> listTablaTablasDetallesComprobante) {
		this.listTablaTablasDetallesComprobante = listTablaTablasDetallesComprobante;
	}

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
	}

	public Boolean getDisableRespuesta() {
		return disableRespuesta;
	}

	public void setDisableRespuesta(Boolean disableRespuesta) {
		this.disableRespuesta = disableRespuesta;
	}

	public Boolean getDisableBuscar() {
		return disableBuscar;
	}

	public void setDisableBuscar(Boolean disableBuscar) {
		this.disableBuscar = disableBuscar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LazyDataModel<MovimientoClientes> getListamovimientos() {
		return listamovimientos;
	}

	public void setListamovimientos(LazyDataModel<MovimientoClientes> listamovimientos) {
		this.listamovimientos = listamovimientos;
	}

	public List<MovimientoClientes> getListaFiltroMovimientos() {
		return listaFiltroMovimientos;
	}

	public void setListaFiltroMovimientos(List<MovimientoClientes> listaFiltroMovimientos) {
		this.listaFiltroMovimientos = listaFiltroMovimientos;
	}

	public Cliente getClienteEncontrado() {
		return clienteEncontrado;
	}

	public void setClienteEncontrado(Cliente clienteEncontrado) {
		this.clienteEncontrado = clienteEncontrado;
	}

	public ClienteService getClienteService() {
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public String getNroserie_documento() {
		return nroserie_documento;
	}

	public void setNroserie_documento(String nroserie_documento) {
		this.nroserie_documento = nroserie_documento;
	}
	
	
}
