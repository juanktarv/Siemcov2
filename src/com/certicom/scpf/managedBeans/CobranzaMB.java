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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.CobranzaCabecera;
import com.certicom.scpf.domain.CobranzaDetalle;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.CuentaTesoreria;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.MovimientoClientes;
import com.certicom.scpf.domain.MovimientoCuentaTesoreria;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.ClienteService;
import com.certicom.scpf.services.CobranzaCabeceraService;
import com.certicom.scpf.services.CobranzaDetalleService;
import com.certicom.scpf.services.CuentaTesoreriaService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MovimientoClienteService;
import com.certicom.scpf.services.MovimientoTesoreriaService;
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
	private CobranzaCabeceraService cobranzaCabeceraService;
	private CobranzaDetalleService cobranzaDetalleService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private List<CuentaTesoreria> listaCuentas;
	private CuentaTesoreriaService cuentaTesoreriaService;
	private CuentaTesoreria cuentaSelec;
	private MovimientoTesoreriaService movimientoTesoreriaService;
	private Integer id_cuenta_tesoreria;
	
	public CobranzaMB(){}
	
	@PostConstruct
	public void inicia(){
		SimpleDateFormat sdfm= new SimpleDateFormat("MM");
		SimpleDateFormat sdfy= new SimpleDateFormat("yyyy");
		
		this.mes=sdfm.format(new Date());
		this.anio=sdfy.format(new Date());
		this.emisorService = new EmisorService();
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
		this.cobranzaCabeceraService= new CobranzaCabeceraService();
		this.cobranzaDetalleService=new CobranzaDetalleService();
		this.movimientoTesoreriaService= new MovimientoTesoreriaService();
		this.cuentaSelec= new CuentaTesoreria();
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.id_cuenta_tesoreria=0;
			this.listaClientes=this.clienteService.findAll();
			if(!this.listaClientes.isEmpty()){
				this.clienteEncontrado=this.listaClientes.get(0);
			}
			this.cuentaSelec= new CuentaTesoreria();
			this.listaCuentas=this.cuentaTesoreriaService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);
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
			System.out.println("this.clienteEncontrado.getId_cliente()--------->"+this.clienteEncontrado.getId_cliente());
			this.cobro.setId_cliente(this.clienteEncontrado.getId_cliente());
			this.cobro.setCliente(this.clienteEncontrado);
			this.cobro.setFecha_cobranza(new Date());
			this.cobro.setTotal_importe_cobrado(new BigDecimal("0.00"));
			this.cobro.setId_emisor(this.emisorSelec.getId_emisor());
			this.cobro.setId_domicilio_fiscal_cab(this.emisorSelec.getId_domicilio_fiscal_cab());
			
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
		this.cobro.setSaldo_deudor(this.cobro.getTotal_importe_cobrado());
		this.cobro.setSaldo_pagar(this.cobro.getTotal_importe_cobrado());
		this.cuentaSelec.setMontoIngresado(this.cobro.getTotal_importe_cobrado());
	
	}

	
	public void guardarCobro(){
		System.out.println(" guardarCobro ----------->"+this.id_cuenta_tesoreria);
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
			System.out.println("this.id_cuenta_tesoreria ---------->"+this.id_cuenta_tesoreria);
			this.cuentaSelec=this.cuentaTesoreriaService.findById(this.id_cuenta_tesoreria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int id_cobranza=0;
		try {
			this.cobro.setId_cuenta_tesoreria(this.cuentaSelec.getId_cuenta_tesoreria());
			 id_cobranza=this.cobranzaCabeceraService.crearCobranzaCabecera(cobro);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(CobranzaDetalle det: this.listaDetalleCobro){
			MovimientoClientes mov= new MovimientoClientes();
			mov.setComprobante(det.getComprobante());
			mov.setNroserie_documento(det.getComprobante().getNumero_serie_documento_cab());
			mov.setPago(det.getImporte_cobrado());
			this.movimientoClienteService.actualizarMovimiento(mov);			
			
			det.setId_cobranza(id_cobranza);
			det.setId_emisor(this.emisorSelec.getId_emisor());
			det.setId_domicilio_fiscal_cab(this.emisorSelec.getId_domicilio_fiscal_cab());
			det.setId_cuenta_tesoreria(this.cuentaSelec.getId_cuenta_tesoreria());
			det.setFecha_cobranza(new Date());
			try {
				this.cobranzaDetalleService.crearCobranzaDetalle(det);
				
				MovimientoCuentaTesoreria movt= new MovimientoCuentaTesoreria();
				movt.setEntrada(det.getImporte_cobrado());
				movt.setFecha_movimiento(new Date());
				movt.setId_cliente(det.getId_cliente());
				movt.setId_cobranza(id_cobranza);
				movt.setId_cuenta_tesoreria(this.cobro.getId_cuenta_tesoreria());
				movt.setId_domicilio_fiscal_cab(this.cobro.getId_domicilio_fiscal_cab());
				movt.setId_emisor(this.cobro.getId_emisor());
				movt.setTipomovimiento("COBRO");
				
				this.movimientoTesoreriaService.crearMovimientoTesoreria(movt);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.listaFiltroMovimientos=new ArrayList<>();
		listarMovimientosFiltros();
		context.update("msgGeneral");
	}
	
	public void cancelarCobro(){
		
		
	}
	
	public void cambiarTotalPagar(CobranzaDetalle detalle, BigDecimal monto){
		List<CobranzaDetalle> listaAux=this.listaDetalleCobro;
		this.listaDetalleCobro=new ArrayList<>();
		this.cobro.setSaldo_pagar(new BigDecimal("0.00"));
		System.out.println("CAMBIA ITEM TOTAL A PAGAR"+detalle.getImporte_cobrado()+" MONTO:"+monto);
		for(int i=0;i<listaAux.size(); i++){
			CobranzaDetalle c=listaAux.get(i);
			if(c.getComprobante().getNumero_serie_documento_cab().equals(detalle.getComprobante().getNumero_serie_documento_cab())){
				this.listaDetalleCobro.add(detalle);
				this.cobro.setSaldo_pagar(detalle.getImporte_cobrado().add(this.cobro.getSaldo_pagar()));
			}else{
				this.listaDetalleCobro.add(c);
				this.cobro.setSaldo_pagar(c.getImporte_cobrado().add(this.cobro.getSaldo_pagar()));
			}
		}	
	}
	
	
	
	public void onItemCliente()  throws Exception{
		
		System.out.println("onItemCliente --->");
				
				List<Cliente> allProducts = this.clienteService.findAll();
		        //List<Cliente> filteredProducts = new ArrayList<Cliente>();
				
				for (int i = 0; i < allProducts.size(); i++) {
		        	Cliente skin = allProducts.get(i);
		            if(skin.getNombre_cab().equalsIgnoreCase(this.clienteEncontrado.getNombre_cab())) {
		            	//filteredProducts.add(skin);
		            	//this.comprobanteSelec.setTipo_comprobante(skin.getDescripcion_prod_det());
		            	this.clienteEncontrado = skin;
		            	break;
		            	
		            }
		        }		
			this.clienteEncontrado=this.clienteService.findById(this.clienteEncontrado.getId_cliente());
			System.out.println("this.clienteEncontrado.getId_cliente() --->"+this.clienteEncontrado.getId_cliente());
	}
	
	public void listarMovimientosFiltros(){
		System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
		
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.FALSE; 
		 
		 if(this.clienteEncontrado!=null){
			 System.out.println(" listarMovimientosFiltros --->"+this.clienteEncontrado.getId_cliente());
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

	public CobranzaCabeceraService getCobranzaCabeceraService() {
		return cobranzaCabeceraService;
	}

	public void setCobranzaCabeceraService(CobranzaCabeceraService cobranzaCabeceraService) {
		this.cobranzaCabeceraService = cobranzaCabeceraService;
	}

	public CobranzaDetalleService getCobranzaDetalleService() {
		return cobranzaDetalleService;
	}

	public void setCobranzaDetalleService(CobranzaDetalleService cobranzaDetalleService) {
		this.cobranzaDetalleService = cobranzaDetalleService;
	}

	public Emisor getEmisorSelec() {
		return emisorSelec;
	}

	public void setEmisorSelec(Emisor emisorSelec) {
		this.emisorSelec = emisorSelec;
	}

	public EmisorService getEmisorService() {
		return emisorService;
	}

	public void setEmisorService(EmisorService emisorService) {
		this.emisorService = emisorService;
	}

	public MovimientoTesoreriaService getMovimientoTesoreriaService() {
		return movimientoTesoreriaService;
	}

	public void setMovimientoTesoreriaService(MovimientoTesoreriaService movimientoTesoreriaService) {
		this.movimientoTesoreriaService = movimientoTesoreriaService;
	}

	public Integer getId_cuenta_tesoreria() {
		return id_cuenta_tesoreria;
	}

	public void setId_cuenta_tesoreria(Integer id_cuenta_tesoreria) {
		this.id_cuenta_tesoreria = id_cuenta_tesoreria;
	}
	
	
}
