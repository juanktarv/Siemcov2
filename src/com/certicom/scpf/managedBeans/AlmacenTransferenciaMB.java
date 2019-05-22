package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.certicom.scpf.domain.Almacen;
import com.certicom.scpf.domain.AlmacenTransferencia;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Inventario;
import com.certicom.scpf.domain.InventarioDetalle;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.services.AlmacenService;
import com.certicom.scpf.services.AlmacenTransferenciaService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.InventarioDetalleService;
import com.certicom.scpf.services.InventarioService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

/*Siemco v2.0*/


@ManagedBean(name="almacenTransferenciaMB")
@ViewScoped
public class AlmacenTransferenciaMB extends GenericBeans implements Serializable{
	
	
	private AlmacenTransferencia almacenTransferenciaSelec;
	private List<AlmacenTransferencia> listaAlmacenTransferencia;
	private Boolean editarAlmacenTransferencia;
	private MenuServices menuServices;
	private AlmacenTransferenciaService almacenTransferenciaService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private ProductoService productoService;
	private Producto productoSelec;
	private Producto productoEncontrado;
	private Almacen almacenOrigenSelec;
	private Almacen almacenDestinoSelec;
	private List<Almacen> listAlmacenOrigen;
	private List<Almacen> listAlmacenDestino;
    private AlmacenService almacenService;
    private InventarioDetalleService inventarioDetalleService;
    private InventarioService inventarioService;
    
    private List<Producto>listaProductos;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public AlmacenTransferenciaMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.almacenTransferenciaSelec = new AlmacenTransferencia();
		this.almacenTransferenciaService = new AlmacenTransferenciaService();
		this.menuServices=new MenuServices();
		
		this.editarAlmacenTransferencia = Boolean.FALSE;
		this.emisorService = new EmisorService();
		this.productoService = new ProductoService();
		this.productoEncontrado = new Producto();
		this.almacenDestinoSelec = new Almacen();
		this.almacenOrigenSelec = new Almacen();
		this.almacenService = new AlmacenService(); 
		this.inventarioDetalleService = new InventarioDetalleService(); 
		this.inventarioService = new InventarioService();
	

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaAlmacenTransferencia = this.almacenTransferenciaService.listarTodos();
			this.listaProductos=this.productoService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:almacen");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/* para tabla maestra */
	
	public void guardarAlmacenTransferencia(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarAlmacenTransferencia) {
				this.almacenTransferenciaService.actualizarAlmacenTransferencia(this.almacenTransferenciaSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza la sucursal : " + this.almacenTransferenciaSelec.getDocumento_transferencia());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("AlmacenTransferencia ha sido actualizado", 3);
			}else{
				this.almacenTransferenciaSelec.setId_emisor(this.emisorSelec.getId_emisor());
				this.almacenTransferenciaSelec.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
				this.almacenTransferenciaSelec.setId_almacen_origen(this.almacenOrigenSelec.getId_almacen());
				this.almacenTransferenciaSelec.setId_almacen_destino(this.almacenDestinoSelec.getId_almacen());
				this.almacenTransferenciaService.crearAlmacenTransferencia(this.almacenTransferenciaSelec);
				
				
				int id = this.almacenTransferenciaService.getSecIdAlmacenTransferencia();
				
				/*Ingresamos a Detalle Inventario: DESTINO */
				
				InventarioDetalle inventarioDetalleDestino = new InventarioDetalle();
				inventarioDetalleDestino.setId_almacen(this.almacenTransferenciaSelec.getId_almacen_destino());
				inventarioDetalleDestino.setId_producto(this.almacenTransferenciaSelec.getId_producto());
				inventarioDetalleDestino.setId_emisor(this.emisorSelec.getId_emisor());
				inventarioDetalleDestino.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
				inventarioDetalleDestino.setId_despacho(0);
				inventarioDetalleDestino.setId_arribo(0);
				inventarioDetalleDestino.setId_tipo_producto(this.almacenTransferenciaSelec.getId_producto());
				inventarioDetalleDestino.setFecha_movimiento(new Date());
				inventarioDetalleDestino.setCantidad_ingresa(this.almacenTransferenciaSelec.getCantidad_transferencia());
				inventarioDetalleDestino.setCantidad_salida(new BigDecimal(0.0));
				inventarioDetalleDestino.setTipo_movimiento("TRANSFERENCIA");
				inventarioDetalleDestino.setId_almacen_transferencia(id-1);
				
				this.inventarioDetalleService.crearInventarioDetalle(inventarioDetalleDestino);
				
				
				/*Sacamos a Detalle Inventario: ORIGEN */
				
				InventarioDetalle inventarioDetalleOrigen = new InventarioDetalle();
				inventarioDetalleOrigen.setId_almacen(this.almacenTransferenciaSelec.getId_almacen_origen());
				inventarioDetalleOrigen.setId_producto(this.almacenTransferenciaSelec.getId_producto());
				inventarioDetalleOrigen.setId_emisor(this.emisorSelec.getId_emisor());
				inventarioDetalleOrigen.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
				inventarioDetalleOrigen.setId_despacho(0);
				inventarioDetalleOrigen.setId_arribo(0);
				inventarioDetalleOrigen.setId_tipo_producto(this.almacenTransferenciaSelec.getId_producto());
				inventarioDetalleOrigen.setFecha_movimiento(new Date());
				inventarioDetalleOrigen.setCantidad_ingresa(new BigDecimal(0.0));
				inventarioDetalleOrigen.setCantidad_salida(this.almacenTransferenciaSelec.getCantidad_transferencia());  
				inventarioDetalleOrigen.setTipo_movimiento("TRANSFERENCIA");
				inventarioDetalleOrigen.setId_almacen_transferencia(id-1);
				
				this.inventarioDetalleService.crearInventarioDetalle(inventarioDetalleOrigen);
				
				
				 /*SUMA EL INVENTARIO DESTINO*/
				 Inventario inventarioDestino = new Inventario();
				 inventarioDestino = this.inventarioService.findById( this.almacenTransferenciaSelec.getId_almacen_destino(), 
						                                   this.almacenTransferenciaSelec.getId_producto(),
						                                   this.emisorSelec.getId_emisor(),
				 		                                   this.emisorSelec.getId_domicilio_fiscal_cab());

				 BigDecimal nuevaCantidadInventarioDestino = new BigDecimal(0.0);
				 nuevaCantidadInventarioDestino = inventarioDestino.getCantidad().add(this.almacenTransferenciaSelec.getCantidad_transferencia());
				 inventarioDestino.setCantidad(nuevaCantidadInventarioDestino);			 
				 this.inventarioService.actualizarInventario(inventarioDestino);
				 
				 
				 /*RESTA EL INVENTARIO ORIGEN*/
				 Inventario inventario = new Inventario();
				 inventario = this.inventarioService.findById( this.almacenTransferenciaSelec.getId_almacen_origen(), 
						                                   this.almacenTransferenciaSelec.getId_producto(),
						                                   this.emisorSelec.getId_emisor(),
				 		                                   this.emisorSelec.getId_domicilio_fiscal_cab());

				 BigDecimal nuevaCantidadInventarioOrigen = new BigDecimal(0.0);
				 nuevaCantidadInventarioOrigen = inventario.getCantidad().subtract(this.almacenTransferenciaSelec.getCantidad_transferencia());
				 inventario.setCantidad(nuevaCantidadInventarioOrigen);			 
				 this.inventarioService.actualizarInventario(inventario);
				
				
				 log.setAccion("INSERT");
	             log.setDescripcion("Se registró la Transferencia : " + this.almacenTransferenciaSelec.getDocumento_transferencia());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("AlmacenTransferencia ha sido creado", 3);
			}
			
			this.almacenTransferenciaSelec = new AlmacenTransferencia();
			this.editarAlmacenTransferencia = Boolean.FALSE;
			
			this.listaAlmacenTransferencia = this.almacenTransferenciaService.listarTodos();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoAlmacenTransferencia() throws Exception{
		this.almacenTransferenciaSelec = new AlmacenTransferencia();
		this.almacenTransferenciaSelec.setFecha_transferencia(new Date());
		this.listAlmacenOrigen = this.almacenService.findAll();
		this.listAlmacenDestino = this.almacenService.findAll();
		this.editarAlmacenTransferencia = Boolean.FALSE;
	}

	public void editarAlmacenTransferencia(AlmacenTransferencia almacen){
		this.almacenTransferenciaSelec = almacen;
		this.editarAlmacenTransferencia = Boolean.TRUE;
	}
	
	public void eliminarAlmacenTransferencia(AlmacenTransferencia almacen){
		this.almacenTransferenciaSelec = almacen;
	}
	
	
	public void confirmaEliminarAlmacenTransferencia(){
		try {
		
			this.almacenTransferenciaService.eliminarAlmacenTransferencia(this.almacenTransferenciaSelec.getId_almacen_origen(), 
					                                                      this.almacenTransferenciaSelec.getId_almacen_destino(), 
					                                                      this.almacenTransferenciaSelec.getId_producto(), 
					                                                      this.almacenTransferenciaSelec.getId_emisor(), 
					                                                      this.almacenTransferenciaSelec.getId_domicilio_fiscal(),
					                                                      this.almacenTransferenciaSelec.getFecha_transferencia(), 
					                                                      this.almacenTransferenciaSelec.getId_almacen_transferencia());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina almacen: " + this.almacenTransferenciaSelec.getDocumento_transferencia());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("AlmacenTransferencia ha sido eliminado", 3);
			
			this.listaAlmacenTransferencia = this.almacenTransferenciaService.listarTodos();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Producto> consultarProductoCod(String query) throws Exception{
		
		System.out.println("entrando autocomplete 2");
        List<Producto> allProducts = this.productoService.findAll();
        List<Producto> filteredProducts = new ArrayList<Producto>();
         
        for (int i = 0; i < allProducts.size(); i++) {
        	Producto skin = allProducts.get(i);
            if(skin.getCod_prod_det().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProducts.add(skin);
            }
        }
        
        System.out.println("Cantidad 2: "+filteredProducts.size());
		
		return filteredProducts;
		
	}
	
	public List<Producto> consultarProducto(String query) throws Exception{
		
		System.out.println("entrando autocomplete 1");
        List<Producto> allProducts = this.productoService.findAll();
        List<Producto> filteredProducts = new ArrayList<Producto>();
         
        for (int i = 0; i < allProducts.size(); i++) {
        	Producto skin = allProducts.get(i);
            if(skin.getDescripcion_prod_det().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProducts.add(skin);
            }
        }
        
        System.out.println("Cantidad 1: "+filteredProducts.size());
		
		return filteredProducts;
		
	}
	
	
	public void onItemSelect()  throws Exception{
		for(Producto p: this.listaProductos){
			if(p.getDescripcion_prod_det().trim().toUpperCase().equalsIgnoreCase(this.productoEncontrado.getDescripcion_prod_det().trim().toUpperCase())){
				this.productoSelec = p;    
			}
		}		
		this.productoEncontrado=this.productoService.findById(this.productoSelec.getId_producto());			
		this.almacenTransferenciaSelec.setId_producto(this.productoSelec.getId_producto());		
		this.listAlmacenOrigen = this.almacenService.listarPorProducto(this.almacenTransferenciaSelec.getId_producto(), this.emisorSelec.getId_emisor(), this.emisorSelec.getId_domicilio_fiscal_cab());		
		System.out.println("this.listAlmacenOrigen " + this.listAlmacenOrigen.size());
		
			
		}
	
	
	public void onItemSelectCod()  throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();		
		for(Producto p: this.listaProductos){
			if(p.getCod_prod_det().trim().toUpperCase().equalsIgnoreCase(this.productoEncontrado.getCod_prod_det().trim().toUpperCase())){
				this.productoSelec=p;
			}
		}
		
		this.productoEncontrado=this.productoService.findById(this.productoSelec.getId_producto());		
		this.almacenTransferenciaSelec.setId_producto(this.productoSelec.getId_producto());		
		this.listAlmacenOrigen = this.almacenService.listarPorProducto(this.almacenTransferenciaSelec.getId_producto(), this.emisorSelec.getId_emisor(), this.emisorSelec.getId_domicilio_fiscal_cab());
		
		System.out.println("this.listAlmacenOrigen " + this.listAlmacenOrigen.size());
		
		/*
		Integer contador = 4;
		
		if(contador <= 1){
			
			RequestContext.getCurrentInstance().execute("PF('dlgNuevoAlmacenTransferencia').show()");
			context.update("msgGeneral");
		}else{
			FacesUtils.showFacesMessage("No existe inventarios para este producto", 1);
			RequestContext.getCurrentInstance().execute("PF('dlgNuevoAlmacenTransferencia').hide()");
			context.update("msgGeneral");
		}*/
		
}
	
	

	
	
	
public void cargaListaDestino(AjaxBehaviorEvent ajaxBehaviorEvent)  throws Exception{
		
			
	   System.out.println("entro en cargaListaDestino");
	   System.out.println("producto" + this.almacenTransferenciaSelec.getId_producto());
	   System.out.println("id origen" + this.almacenTransferenciaSelec.getId_almacen_origen());
	   System.out.println("id origen" + this.almacenOrigenSelec.getId_almacen());
		
		this.listAlmacenDestino = this.almacenService.listarPorProductoAnidado(this.almacenTransferenciaSelec.getId_producto(), 
																				this.emisorSelec.getId_emisor(), 
																				this.emisorSelec.getId_domicilio_fiscal_cab(), 
																				this.almacenOrigenSelec.getId_almacen());
//	   this.listAlmacenDestino = this.almacenService.listarAlmacenDestino(this.emisorSelec.getId_emisor(), 
//			   															  this.emisorSelec.getId_domicilio_fiscal_cab(), 
//			   															  this.almacenOrigenSelec.getId_almacen());
		 
		System.out.println("this.listAlmacenDestino " +  this.listAlmacenDestino.size());
		
}
	
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

	public AlmacenTransferencia getAlmacenTransferenciaSelec() {
		return almacenTransferenciaSelec;
	}

	public void setAlmacenTransferenciaSelec(AlmacenTransferencia almacenTransferenciaSelec) {
		this.almacenTransferenciaSelec = almacenTransferenciaSelec;
	}

	public List<AlmacenTransferencia> getListaAlmacenTransferencia() {
		return listaAlmacenTransferencia;
	}

	public void setListaAlmacenTransferencia(List<AlmacenTransferencia> listaAlmacenTransferencia) {
		this.listaAlmacenTransferencia = listaAlmacenTransferencia;
	}

	public Boolean getEditarAlmacenTransferencia() {
		return editarAlmacenTransferencia;
	}

	public void setEditarAlmacenTransferencia(Boolean editarAlmacenTransferencia) {
		this.editarAlmacenTransferencia = editarAlmacenTransferencia;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public AlmacenTransferenciaService getAlmacenTransferenciaService() {
		return almacenTransferenciaService;
	}

	public void setAlmacenTransferenciaService(AlmacenTransferenciaService almacenTransferenciaService) {
		this.almacenTransferenciaService = almacenTransferenciaService;
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

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public Producto getProductoSelec() {
		return productoSelec;
	}

	public void setProductoSelec(Producto productoSelec) {
		this.productoSelec = productoSelec;
	}

	public Producto getProductoEncontrado() {
		return productoEncontrado;
	}

	public void setProductoEncontrado(Producto productoEncontrado) {
		this.productoEncontrado = productoEncontrado;
	}

    

	public Almacen getAlmacenOrigenSelec() {
		return almacenOrigenSelec;
	}

	public void setAlmacenOrigenSelec(Almacen almacenOrigenSelec) {
		this.almacenOrigenSelec = almacenOrigenSelec;
	}

	public Almacen getAlmacenDestinoSelec() {
		return almacenDestinoSelec;
	}

	public void setAlmacenDestinoSelec(Almacen almacenDestinoSelec) {
		this.almacenDestinoSelec = almacenDestinoSelec;
	}

	public List<Almacen> getListAlmacenOrigen() {
		return listAlmacenOrigen;
	}

	public void setListAlmacenOrigen(List<Almacen> listAlmacenOrigen) {
		this.listAlmacenOrigen = listAlmacenOrigen;
	}

	public List<Almacen> getListAlmacenDestino() {
		return listAlmacenDestino;
	}

	public void setListAlmacenDestino(List<Almacen> listAlmacenDestino) {
		this.listAlmacenDestino = listAlmacenDestino;
	}

	public AlmacenService getAlmacenService() {
		return almacenService;
	}

	public void setAlmacenService(AlmacenService almacenService) {
		this.almacenService = almacenService;
	}

	public InventarioDetalleService getInventarioDetalleService() {
		return inventarioDetalleService;
	}

	public void setInventarioDetalleService(InventarioDetalleService inventarioDetalleService) {
		this.inventarioDetalleService = inventarioDetalleService;
	}

	public InventarioService getInventarioService() {
		return inventarioService;
	}

	public void setInventarioService(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	

}
