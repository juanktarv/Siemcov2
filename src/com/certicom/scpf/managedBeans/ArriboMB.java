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
import com.certicom.scpf.domain.Arribo;
import com.certicom.scpf.domain.ArriboDetalle;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Inventario;
import com.certicom.scpf.domain.InventarioDetalle;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.MovimientoInventarioClientes;
import com.certicom.scpf.domain.MovimientoInventarioProveedores;
import com.certicom.scpf.services.AlmacenService;
import com.certicom.scpf.services.ArriboDetalleService;
import com.certicom.scpf.services.ArriboService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.InventarioDetalleService;
import com.certicom.scpf.services.InventarioService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.MovimientoInventarioProveedorService;
import com.certicom.scpf.services.ProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;


/*Siemco v2.0*/


@ManagedBean(name="arriboMB")
@ViewScoped
public class ArriboMB extends GenericBeans implements Serializable{
	
	
	private MovimientoInventarioProveedores movimientoInventarioProveedorSelec;
	private List<MovimientoInventarioProveedores> listaMovimientoInventarioProveedores;
	private Boolean editarMovimientoInventarioProveedor;
	private MenuServices menuServices;
	private MovimientoInventarioProveedorService movimientoInventarioProveedorService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private List<Almacen> listAlmacen;
	private List<Almacen> listAlmacenPorProducto;
	private AlmacenService almacenService;
	private String documentoArribo;
	private String numeroDocumentoArribo;
	private BigDecimal cantidadArribo;
	private Integer id_almacen;
	private Date fechaArribo;
	private ArriboService arriboService;
	private ArriboDetalleService arriboDetalleService;
	private InventarioDetalleService inventarioDetalleService;
	private InventarioService inventarioService;
	private ProductoService productoService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ArriboMB(){}
	
	@PostConstruct
	public void inicia(){
		
	
		this.movimientoInventarioProveedorSelec = new MovimientoInventarioProveedores();
		this.movimientoInventarioProveedorService = new MovimientoInventarioProveedorService();
		this.menuServices=new MenuServices();
		this.almacenService = new AlmacenService();
		this.arriboService  = new ArriboService();
		this.arriboDetalleService = new ArriboDetalleService();
		this.inventarioDetalleService = new InventarioDetalleService(); 
		this.inventarioService = new InventarioService(); 
		
		this.editarMovimientoInventarioProveedor = Boolean.FALSE;
		this.emisorService = new EmisorService();
        this.productoService = new ProductoService();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			
			this.listaMovimientoInventarioProveedores = this.movimientoInventarioProveedorService.listarMovimientosInventarioProveedores();
			this.emisorSelec = this.emisorService.findAll().get(0);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:almacen");
			//this.listAlmacen = this.almacenService.findAll();
			this.almacenService = new AlmacenService();
			this.documentoArribo="";
			this.numeroDocumentoArribo=""; 
			this.id_almacen=0;
			this.cantidadArribo= new BigDecimal(0.0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			this.fechaArribo =  new Date(); 
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void editarArribo(MovimientoInventarioProveedores movimientoInventarioProveedores) throws Exception{
		
		RequestContext context = RequestContext.getCurrentInstance();
		 this.listAlmacenPorProducto = this.almacenService.listarPorProducto(movimientoInventarioProveedores.getId_producto(), this.emisorSelec.getId_emisor(), this.emisorSelec.getId_domicilio_fiscal_cab());

		if(!this.listAlmacenPorProducto.isEmpty()){
			this.movimientoInventarioProveedorSelec = movimientoInventarioProveedores;
			RequestContext.getCurrentInstance().execute("PF('dlgNuevoArribo').show()");
			context.update("msgGeneral");
		}else{
			FacesUtils.showFacesMessage("No existe Almacen para el Producto, Registrar Inventario", 1);
			RequestContext.getCurrentInstance().execute("PF('dlgNuevoArribo').hide()");
			context.update("msgGeneral");
		}
		
	}
	

	
	public void guardarArribo(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
	   	 
	   	 
	     try {    
	    	      
	    	    if(this.cantidadArribo.compareTo(this.movimientoInventarioProveedorSelec.getSaldo_arribo()) <= 0 && this.cantidadArribo.compareTo(new BigDecimal (0.0)) > 0){
	    	   
	    	    	
	    	    	/*Inserta Arribo*/
					 Arribo arribo = new Arribo();
					 arribo.setId_producto(this.movimientoInventarioProveedorSelec.getId_producto());
					 arribo.setFecha_movimiento(this.fechaArribo);
					 arribo.setCantidad_total_arribo(this.cantidadArribo);
					 
					 this.arriboService.crearArribo(arribo);
					 
					  int id = this.arriboService.getSecIdArribo();
					 
					 System.out.println("id de arribo es " + arribo.getId_arribo());
					 
					 /*Inserta Arribo Detalle*/
					 ArriboDetalle arriboDetalle = new ArriboDetalle();
					 arriboDetalle.setId_arribo(id-1);
					 arriboDetalle.setId_comprobante_compra(this.movimientoInventarioProveedorSelec.getId_comprobante_compra());
					 arriboDetalle.setId_proveedor(this.movimientoInventarioProveedorSelec.getId_proveedores());
					 arriboDetalle.setTipo_comprobante(this.movimientoInventarioProveedorSelec.getTipo_comprobante());
					 arriboDetalle.setId_producto(this.movimientoInventarioProveedorSelec.getId_producto());
					 arriboDetalle.setId_almacen(this.id_almacen);
					 arriboDetalle.setId_emisor(this.emisorSelec.getId_emisor());
					 arriboDetalle.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
					 arriboDetalle.setId_tipo_producto(this.movimientoInventarioProveedorSelec.getId_tipo_producto());
					 arriboDetalle.setFecha_movimiento(this.fechaArribo);
					 arriboDetalle.setCantidad_arribo(this.cantidadArribo);
					 arriboDetalle.setDocumento_arribo(this.documentoArribo);
					 arriboDetalle.setNumero_documento_arribo(this.numeroDocumentoArribo);
					 				 
					 
					 this.arriboDetalleService.crearArriboDetalle(arriboDetalle);
					 
					 
					 /*Inserta Inventario Detalle*/
					 InventarioDetalle inventarioDetalle = new InventarioDetalle();
					 inventarioDetalle.setId_producto(this.movimientoInventarioProveedorSelec.getId_producto());
					 inventarioDetalle.setId_almacen(this.id_almacen);
					 inventarioDetalle.setId_emisor(this.emisorSelec.getId_emisor());
					 inventarioDetalle.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
					 inventarioDetalle.setId_despacho(0);
					 inventarioDetalle.setId_arribo(id-1);
					 inventarioDetalle.setId_tipo_producto(this.movimientoInventarioProveedorSelec.getId_producto());
					 inventarioDetalle.setFecha_movimiento(this.fechaArribo);
					 inventarioDetalle.setCantidad_ingresa(this.cantidadArribo);
					 inventarioDetalle.setCantidad_salida( new BigDecimal(0.0));
					 inventarioDetalle.setTipo_movimiento("ARRIBO");
					 inventarioDetalle.setId_almacen_transferencia(0);
				
					 
					 this.inventarioDetalleService.crearInventarioDetalle(inventarioDetalle);
					 
					 
					 /*Actualiza movimiento*/
					 BigDecimal nuevaCantidadPendiente = new BigDecimal(0.0);
					 nuevaCantidadPendiente= this.movimientoInventarioProveedorSelec.getSaldo_arribo().subtract(this.cantidadArribo);
					 this.movimientoInventarioProveedorSelec.setSaldo_arribo(nuevaCantidadPendiente);
					 if (this.movimientoInventarioProveedorSelec.getSaldo_arribo().intValue()<=0){
						 this.movimientoInventarioProveedorSelec.setFlag_regularizado(true);
					 }
					 this.movimientoInventarioProveedorService.actualizarMovimientoInventarioProveedores(this.movimientoInventarioProveedorSelec);
					 
					 
					 /*SUMA EL INVENTARIO*/
					 Inventario inventario = new Inventario();
					 inventario = this.inventarioService.findById(this.id_almacen, 
							                                   this.movimientoInventarioProveedorSelec.getId_producto(),
							                                   this.emisorSelec.getId_emisor(),
					 		                                   this.emisorSelec.getId_domicilio_fiscal_cab());

					 BigDecimal nuevaCantidadInventario = new BigDecimal(0.0);
					 nuevaCantidadInventario = inventario.getCantidad().add(this.cantidadArribo);
					 inventario.setCantidad(nuevaCantidadInventario);			 
					 this.inventarioService.actualizarInventario(inventario);
					 
					/*Actualizamos el Stock del producto*/
					
					BigDecimal stock = new BigDecimal(0.0);
					stock = this.inventarioService.cantidadStock(inventario.getId_producto());
					System.out.println( " stock " + stock);
					this.productoService.actualizarStockProductoPorCodigo(inventario.getId_producto(), stock);
					 
					 
					 
					 /*TODO OK*/
					 FacesUtils.showFacesMessage("Arribo se ha actualizado" , 3);
					 
					
					context.update("msgGeneral");	
					
	    	    }else{
	    	    	
	   	    		valido=Boolean.FALSE; 
		   	   	    context.addCallbackParam("esValido", valido);
		     	   	FacesUtils.showFacesMessage("El arribo debe ser menor o igual que el saldo y/o mayor que cero ", Constante.FATAL); 
		   	   	    context.update("msgNuevo"); 
	    	    }
	    	 
			this.documentoArribo="";
			this.numeroDocumentoArribo=""; 
			this.id_almacen=0;
			this.cantidadArribo= new BigDecimal(0.0);  
			this.listAlmacenPorProducto = null;
				
			
			} catch (Exception e) {
				e.printStackTrace();
				FacesUtils.showFacesMessage("Ocurrio un error" , 2);
				
			}
	   
		
	}
	
	
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/

	public MovimientoInventarioProveedores getMovimientoInventarioProveedorSelec() {
		return movimientoInventarioProveedorSelec;
	}

	public void setMovimientoInventarioProveedorSelec(MovimientoInventarioProveedores movimientoInventarioProveedorSelec) {
		this.movimientoInventarioProveedorSelec = movimientoInventarioProveedorSelec;
	}

	public List<MovimientoInventarioProveedores> getListaMovimientoInventarioProveedores() {
		return listaMovimientoInventarioProveedores;
	}

	public void setListaMovimientoInventarioProveedores(
			List<MovimientoInventarioProveedores> listaMovimientoInventarioProveedores) {
		this.listaMovimientoInventarioProveedores = listaMovimientoInventarioProveedores;
	}

	public Boolean getEditarMovimientoInventarioProveedor() {
		return editarMovimientoInventarioProveedor;
	}

	public void setEditarMovimientoInventarioProveedor(Boolean editarMovimientoInventarioProveedor) {
		this.editarMovimientoInventarioProveedor = editarMovimientoInventarioProveedor;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public MovimientoInventarioProveedorService getMovimientoInventarioProveedorService() {
		return movimientoInventarioProveedorService;
	}

	public void setMovimientoInventarioProveedorService(
			MovimientoInventarioProveedorService movimientoInventarioProveedorService) {
		this.movimientoInventarioProveedorService = movimientoInventarioProveedorService;
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

	public String getDocumentoArribo() {
		return documentoArribo;
	}

	public void setDocumentoArribo(String documentoArribo) {
		this.documentoArribo = documentoArribo;
	}

	public String getNumeroDocumentoArribo() {
		return numeroDocumentoArribo;
	}

	public void setNumeroDocumentoArribo(String numeroDocumentoArribo) {
		this.numeroDocumentoArribo = numeroDocumentoArribo;
	}

	public BigDecimal getCantidadArribo() {
		return cantidadArribo;
	}

	public void setCantidadArribo(BigDecimal cantidadArribo) {
		this.cantidadArribo = cantidadArribo;
	}

	public Integer getId_almacen() {
		return id_almacen;
	}

	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}

	public Date getFechaArribo() {
		return fechaArribo;
	}

	public void setFechaArribo(Date fechaArribo) {
		this.fechaArribo = fechaArribo;
	}

	public ArriboService getArriboService() {
		return arriboService;
	}

	public void setArriboService(ArriboService arriboService) {
		this.arriboService = arriboService;
	}

	public ArriboDetalleService getArriboDetalleService() {
		return arriboDetalleService;
	}

	public void setArriboDetalleService(ArriboDetalleService arriboDetalleService) {
		this.arriboDetalleService = arriboDetalleService;
	}

	public InventarioDetalleService getInventarioDetalleService() {
		return inventarioDetalleService;
	}

	public void setInventarioDetalleService(InventarioDetalleService inventarioDetalleService) {
		this.inventarioDetalleService = inventarioDetalleService;
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