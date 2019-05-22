package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.CobranzaCabecera;
import com.certicom.scpf.domain.CobranzaDetalle;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.CuentaTesoreria;
import com.certicom.scpf.domain.MovimientoClientes;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.CuentaTesoreriaService;
import com.certicom.scpf.services.MovimientoClienteService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.GenericBeans;

import src.com.certicom.scpf.utils.Utils;

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
	private List<Cliente>listaClientes;
	private List<MovimientoClientes>listaSelectedMovimientos;
	private CobranzaCabecera cobro;
	private List<CobranzaDetalle> listaDetalleCobro;
	
	private List<CuentaTesoreria> listaCuentas;
	private CuentaTesoreriaService cuentaTesoreriaService;
	private CuentaTesoreria cuentaSelec;
	
	public CobranzaMB(){}
	
	@PostConstruct
	public void inicia(){
		SimpleDateFormat sdfm= new SimpleDateFormat("MM");
		SimpleDateFormat sdfy= new SimpleDateFormat("yyyy");
		
		this.mes=sdfm.format(new Date());
		this.anio=sdfy.format(new Date());
		
		this.movimientoClienteSelec= new MovimientoClientes();
		this.movimientoClienteService= new MovimientoClienteService();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.disableBuscar = Boolean.TRUE; 
		this.disableRespuesta = Boolean.TRUE; 
		this.clienteEncontrado= new Cliente();
		this.clienteService= new ClienteService();
		this.nroserie_documento="";
		this.cuentaTesoreriaService= new CuentaTesoreriaService();
		this.cuentaSelec= new CuentaTesoreria();
		
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listaClientes=this.clienteService.findAll();
			this.listaCuentas=this.cuentaTesoreriaService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void prepararCobro(){
		this.cobro= new CobranzaCabecera();
		this.cuentaSelec= new CuentaTesoreria();
		this.listaDetalleCobro= new ArrayList<>();
		if(this.clienteEncontrado!=null){
			this.cobro.setId_cliente(this.clienteEncontrado.getId_cliente());
			this.cobro.setCliente(this.clienteEncontrado);
			this.cobro.setFecha_cobranza(new Date());
			this.cobro.setTotal_importe_cobrado(new BigDecimal("0.00"));
			CobranzaDetalle detalle;
			System.out.println("this.listaSelectedMovimientos--->"+listaSelectedMovimientos.size());
			System.out.println("this.listaFiltroMovimientos--->"+listaFiltroMovimientos);
			
			for(MovimientoClientes mov:listaSelectedMovimientos){
				detalle= new CobranzaDetalle();
				this.cobro.setTotal_importe_cobrado(this.cobro.getTotal_importe_cobrado().add(mov.getImporte()));
				System.out.println("IMPORTE--->"+this.cobro.getTotal_importe_cobrado());
				detalle.setId_cliente(mov.getId_cliente());
				detalle.setId_comprobante(mov.getId_comprobante());
				detalle.setImporte_cobrado(Utils.redondeoImporteTotal(mov.getImporte()));
				detalle.setImporte_pendiente(Utils.redondeoImporteTotal(mov.getImporte(), 2));
				detalle.setComprobante(mov.getComprobante());
				this.listaDetalleCobro.add(detalle);
			}
			
		}
		System.out.println("this.cobro.getCliente().getNombre_cab()"+this.cobro.getCliente().getNombre_cab());
		System.out.println("this.cobro.getTotal_importe_cobrado()"+this.cobro.getTotal_importe_cobrado());
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
		 
		 if(this.clienteEncontrado!=null){
			 
		 }else{
			 this.clienteEncontrado= new Cliente();
		 }
		
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
							filters.put("id_cliente", clienteEncontrado!=null? clienteEncontrado.getId_cliente():"");
							filters.put("nroserie_documento", nroserie_documento!=null? nroserie_documento :"");
							totalRow = movimientoClienteService.countCompByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, filters);												
							datasource = movimientoClienteService.listComprobantesByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, first, pageSize, filters, "m.nroserie_documento", "DESC");
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
	

	
	public void onItemDocumento()  throws Exception{
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

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<MovimientoClientes> getListaSelectedMovimientos() {
		return listaSelectedMovimientos;
	}

	public void setListaSelectedMovimientos(List<MovimientoClientes> listaSelectedMovimientos) {
		this.listaSelectedMovimientos = listaSelectedMovimientos;
	}

	public CobranzaCabecera getCobro() {
		return cobro;
	}

	public void setCobro(CobranzaCabecera cobro) {
		this.cobro = cobro;
	}

	public List<CobranzaDetalle> getListaDetalleCobro() {
		return listaDetalleCobro;
	}

	public void setListaDetalleCobro(List<CobranzaDetalle> listaDetalleCobro) {
		this.listaDetalleCobro = listaDetalleCobro;
	}

	public List<CuentaTesoreria> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<CuentaTesoreria> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public CuentaTesoreriaService getCuentaTesoreriaService() {
		return cuentaTesoreriaService;
	}

	public void setCuentaTesoreriaService(CuentaTesoreriaService cuentaTesoreriaService) {
		this.cuentaTesoreriaService = cuentaTesoreriaService;
	}

	public CuentaTesoreria getCuentaSelec() {
		return cuentaSelec;
	}

	public void setCuentaSelec(CuentaTesoreria cuentaSelec) {
		this.cuentaSelec = cuentaSelec;
	}
	
	
}
