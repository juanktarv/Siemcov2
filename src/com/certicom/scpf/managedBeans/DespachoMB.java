package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Almacen;
import com.certicom.scpf.domain.Despacho;
import com.certicom.scpf.domain.DespachoDetalle;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Inventario;
import com.certicom.scpf.domain.InventarioDetalle;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.MovimientoInventarioClientes;
import com.certicom.scpf.services.AlmacenService;
import com.certicom.scpf.services.DespachoDetalleService;
import com.certicom.scpf.services.DespachoService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.InventarioDetalleService;
import com.certicom.scpf.services.InventarioService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.MovimientoInventarioClienteService;
import com.certicom.scpf.services.ProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;


/*Siemco v2.0*/


@ManagedBean(name="despachoMB")
@ViewScoped
public class DespachoMB extends GenericBeans implements Serializable{
	
	
	private MovimientoInventarioClientes movimientoInventarioClienteSelec;
	private List<MovimientoInventarioClientes> listaMovimientoInventarioClientes;
	private Boolean editarMovimientoInventarioCliente;
	private MenuServices menuServices;
	private MovimientoInventarioClienteService movimientoInventarioClienteService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private List<Almacen> listAlmacen;
	private List<Almacen> listAlmacenPorProducto;
	private AlmacenService almacenService;
	private String documentoDespacho;
	private String numeroDocumentoDespacho;
	private BigDecimal cantidadDespacho;
	private Integer id_almacen;
	private Date fechaDespacho;
	private DespachoService despachoService;
	private DespachoDetalleService despachoDetalleService;
	private InventarioDetalleService inventarioDetalleService;
	private InventarioService inventarioService;
	private ProductoService productoService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public DespachoMB(){}
	
	@PostConstruct
	public void inicia(){
		
	
		this.movimientoInventarioClienteSelec = new MovimientoInventarioClientes();
		this.movimientoInventarioClienteService = new MovimientoInventarioClienteService();
		this.menuServices=new MenuServices();
		this.almacenService = new AlmacenService();
		this.despachoService  = new DespachoService();
		this.despachoDetalleService = new DespachoDetalleService();
		this.inventarioDetalleService = new InventarioDetalleService(); 
		this.inventarioService = new InventarioService(); 
		this.productoService = new ProductoService();
		
		this.editarMovimientoInventarioCliente = Boolean.FALSE;
		this.emisorService = new EmisorService();

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			
			this.listaMovimientoInventarioClientes = this.movimientoInventarioClienteService.listarMovimientosInventarioClientes();
			this.emisorSelec = this.emisorService.findAll().get(0);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:almacen");
			//this.listAlmacen = this.almacenService.findAll();
			this.almacenService = new AlmacenService();
			this.documentoDespacho="";
			this.numeroDocumentoDespacho=""; 
			this.id_almacen=0;
			this.cantidadDespacho= new BigDecimal(0.0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			this.fechaDespacho =  new Date(); 
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void editarDespacho(MovimientoInventarioClientes movimientoInventarioClientes) throws Exception{
		
		RequestContext context = RequestContext.getCurrentInstance();
		 this.listAlmacenPorProducto = this.almacenService.listarPorProducto(movimientoInventarioClientes.getId_producto(), this.emisorSelec.getId_emisor(), this.emisorSelec.getId_domicilio_fiscal_cab());
				
		if(!this.listAlmacenPorProducto.isEmpty()){
			this.movimientoInventarioClienteSelec = movimientoInventarioClientes;
			RequestContext.getCurrentInstance().execute("PF('dlgNuevoDespacho').show()");
			context.update("msgGeneral");
		}else{
			FacesUtils.showFacesMessage("No existe Almacen para el Producto, Registrar Inventario", 1);
			RequestContext.getCurrentInstance().execute("PF('dlgNuevoDespacho').hide()");
			context.update("msgGeneral");
		}
		
		
	}
	
	public void guardarDespacho(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
   	     System.out.println(" documentoDespacho " + this.documentoDespacho);
	   	 System.out.println(" numeroDocumentoDespacho " + this.numeroDocumentoDespacho);
	   	 System.out.println(" cantidadDespacho " + this.cantidadDespacho);
	   	 System.out.println(" fechaDespacho " + this.fechaDespacho);
	   	 
	   	 
	     try {    
	    	     
	    	 
	    	   if(this.cantidadDespacho.compareTo(this.movimientoInventarioClienteSelec.getSaldo_despacho()) <= 0 && this.cantidadDespacho.compareTo(new BigDecimal (0.0)) > 0){
	    		   
	    		   /*Inserta Despacho*/
					 Despacho despacho = new Despacho();
					 despacho.setId_producto(this.movimientoInventarioClienteSelec.getId_producto());
					 despacho.setFecha_movimiento(this.fechaDespacho);
					 despacho.setCantidad_total_despacho(this.cantidadDespacho);
					 
					 this.despachoService.crearDespacho(despacho);
					 
					  int id = this.despachoService.getSecIdDespacho();
					 
					 System.out.println("id de despacho es " + despacho.getId_despacho());
					 
					 /*Inserta Despacho Detalle*/
					 DespachoDetalle despachoDetalle = new DespachoDetalle();
					 despachoDetalle.setId_despacho(id-1);
					 despachoDetalle.setId_comprobante(this.movimientoInventarioClienteSelec.getId_comprobante());
					 despachoDetalle.setId_cliente(this.movimientoInventarioClienteSelec.getId_cliente());
					 despachoDetalle.setTipo_comprobante(this.movimientoInventarioClienteSelec.getTipo_comprobante());
					 despachoDetalle.setId_producto(this.movimientoInventarioClienteSelec.getId_producto());
					 despachoDetalle.setId_almacen(this.id_almacen);
					 despachoDetalle.setId_emisor(this.emisorSelec.getId_emisor());
					 despachoDetalle.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
					 despachoDetalle.setId_tipo_producto(this.movimientoInventarioClienteSelec.getId_tipo_producto());
					 despachoDetalle.setId_modo_pago(this.movimientoInventarioClienteSelec.getId_modo_pago());
					 despachoDetalle.setFecha_movimiento(this.fechaDespacho);
					 despachoDetalle.setCantidad_despacho(this.cantidadDespacho);
					 despachoDetalle.setDocumento_despacho(this.documentoDespacho);
					 despachoDetalle.setNumero_documento_despacho(this.numeroDocumentoDespacho);
					 				 
					 
					 this.despachoDetalleService.crearDespachoDetalle(despachoDetalle);
					 
					 
					 /*Inserta Inventario Detalle*/
					 InventarioDetalle inventarioDetalle = new InventarioDetalle();
					 inventarioDetalle.setId_producto(this.movimientoInventarioClienteSelec.getId_producto());
					 inventarioDetalle.setId_almacen(this.id_almacen);
					 inventarioDetalle.setId_emisor(this.emisorSelec.getId_emisor());
					 inventarioDetalle.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
					 inventarioDetalle.setId_despacho(id-1);
					 inventarioDetalle.setId_arribo(0);
					 inventarioDetalle.setId_tipo_producto(this.movimientoInventarioClienteSelec.getId_producto());
					 inventarioDetalle.setFecha_movimiento(this.fechaDespacho);
					 inventarioDetalle.setCantidad_ingresa(new BigDecimal(0.0));
					 inventarioDetalle.setCantidad_salida(this.cantidadDespacho ); 
					 inventarioDetalle.setTipo_movimiento("DESPACHO");
					 inventarioDetalle.setId_almacen_transferencia(0);
				
					 
					 this.inventarioDetalleService.crearInventarioDetalle(inventarioDetalle);
					 
					 
					 /*Actualiza movimiento*/
					 BigDecimal nuevaCantidadPendiente = new BigDecimal(0.0);
					 nuevaCantidadPendiente= this.movimientoInventarioClienteSelec.getSaldo_despacho().subtract(this.cantidadDespacho);
					 this.movimientoInventarioClienteSelec.setSaldo_despacho(nuevaCantidadPendiente);
					 if (this.movimientoInventarioClienteSelec.getSaldo_despacho().intValue()<=0){
						 System.out.println("Entro en everd");
						 this.movimientoInventarioClienteSelec.setFlag_regularizado(true);
					 }
					 this.movimientoInventarioClienteService.actualizarMovimientoInventarioClientes(this.movimientoInventarioClienteSelec);
					 
					 
					 
					 /*RESTA EL INVENTARIO*/
					 Inventario inventario = new Inventario();
					 inventario = this.inventarioService.findById(this.id_almacen, 
							                                   this.movimientoInventarioClienteSelec.getId_producto(),
							                                   this.emisorSelec.getId_emisor(),
					 		                                   this.emisorSelec.getId_domicilio_fiscal_cab());

					 BigDecimal nuevaCantidadInventario = new BigDecimal(0.0);
					 nuevaCantidadInventario = inventario.getCantidad().subtract(this.cantidadDespacho);
					 inventario.setCantidad(nuevaCantidadInventario);			 
					 this.inventarioService.actualizarInventario(inventario);
					 
					 
					/*Actualizamos el Stock del producto*/
						
					BigDecimal stock = new BigDecimal(0.0);
					stock = this.inventarioService.cantidadStock(inventario.getId_producto());
					System.out.println( " stock " + stock);
					this.productoService.actualizarStockProductoPorCodigo(inventario.getId_producto(), stock);
					 			 
					 
					 /*TODO OK*/
					 
					 FacesUtils.showFacesMessage("Despacho se ha actualizado" , 3);
					 				
	    		   
					context.update("msgGeneral");
					
	    	   }else{
	    		   
	   	    		valido=Boolean.FALSE; 
		   	   	    context.addCallbackParam("esValido", valido);
		     	   	FacesUtils.showFacesMessage("El Despacho debe ser menor o igual que el saldo y/o mayor que cero ", Constante.FATAL); 
		   	   	    context.update("msgNuevo"); 
	    		   
	    	   }
		    	   
			this.documentoDespacho="";
			this.numeroDocumentoDespacho=""; 
			this.id_almacen=0;
			this.cantidadDespacho= new BigDecimal(0.0);    	 
			this.listAlmacenPorProducto = null;
	    	 
			
			} catch (Exception e) {
				e.printStackTrace();
				FacesUtils.showFacesMessage("Ocurrio un error" , 2);
			}
	     
		
	}
	
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/

	public MovimientoInventarioClientes getMovimientoInventarioClienteSelec() {
		return movimientoInventarioClienteSelec;
	}

	public void setMovimientoInventarioClienteSelec(MovimientoInventarioClientes movimientoInventarioClienteSelec) {
		this.movimientoInventarioClienteSelec = movimientoInventarioClienteSelec;
	}

	public List<MovimientoInventarioClientes> getListaMovimientoInventarioClientes() {
		return listaMovimientoInventarioClientes;
	}

	public void setListaMovimientoInventarioClientes(List<MovimientoInventarioClientes> listaMovimientoInventarioClientes) {
		this.listaMovimientoInventarioClientes = listaMovimientoInventarioClientes;
	}

	public Boolean getEditarMovimientoInventarioCliente() {
		return editarMovimientoInventarioCliente;
	}

	public void setEditarMovimientoInventarioCliente(Boolean editarMovimientoInventarioCliente) {
		this.editarMovimientoInventarioCliente = editarMovimientoInventarioCliente;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public MovimientoInventarioClienteService getMovimientoInventarioClienteService() {
		return movimientoInventarioClienteService;
	}

	public void setMovimientoInventarioClienteService(
			MovimientoInventarioClienteService movimientoInventarioClienteService) {
		this.movimientoInventarioClienteService = movimientoInventarioClienteService;
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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public LogMB getLogmb() {
		return logmb;
	}

	public void setLogmb(LogMB logmb) {
		this.logmb = logmb;
	}

	public List<Almacen> getListAlmacen() {
		return listAlmacen;
	}

	public void setListAlmacen(List<Almacen> listAlmacen) {
		this.listAlmacen = listAlmacen;
	}

	public AlmacenService getAlmacenService() {
		return almacenService;
	}

	public void setAlmacenService(AlmacenService almacenService) {
		this.almacenService = almacenService;
	}

	public String getDocumentoDespacho() {
		return documentoDespacho;
	}

	public void setDocumentoDespacho(String documentoDespacho) {
		this.documentoDespacho = documentoDespacho;
	}

	public String getNumeroDocumentoDespacho() {
		return numeroDocumentoDespacho;
	}

	public void setNumeroDocumentoDespacho(String numeroDocumentoDespacho) {
		this.numeroDocumentoDespacho = numeroDocumentoDespacho;
	}

	public BigDecimal getCantidadDespacho() {
		return cantidadDespacho;
	}

	public void setCantidadDespacho(BigDecimal cantidadDespacho) {
		this.cantidadDespacho = cantidadDespacho;
	}

	public Date getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}

	public DespachoService getDespachoService() {
		return despachoService;
	}

	public void setDespachoService(DespachoService despachoService) {
		this.despachoService = despachoService;
	}

	public DespachoDetalleService getDespachoDetalleService() {
		return despachoDetalleService;
	}

	public void setDespachoDetalleService(DespachoDetalleService despachoDetalleService) {
		this.despachoDetalleService = despachoDetalleService;
	}

	public InventarioDetalleService getInventarioDetalleService() {
		return inventarioDetalleService;
	}

	public void setInventarioDetalleService(InventarioDetalleService inventarioDetalleService) {
		this.inventarioDetalleService = inventarioDetalleService;
	}

	public Integer getId_almacen() {
		return id_almacen;
	}

	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}

	public InventarioService getInventarioService() {
		return inventarioService;
	}

	public void setInventarioService(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}

	public List<Almacen> getListAlmacenPorProducto() {
		return listAlmacenPorProducto;
	}

	public void setListAlmacenPorProducto(List<Almacen> listAlmacenPorProducto) {
		this.listAlmacenPorProducto = listAlmacenPorProducto;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}	
	
	
	
    
}
