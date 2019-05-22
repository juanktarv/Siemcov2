package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.certicom.scpf.domain.Inventario;
import com.certicom.scpf.domain.InventarioDetalle;
import com.certicom.scpf.domain.Almacen;
import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.services.InventarioService;
import com.certicom.scpf.services.AlmacenService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.InventarioDetalleService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;



/*Siemco v2.0*/


@ManagedBean(name="inventarioMB")
@ViewScoped
public class InventarioMB extends GenericBeans implements Serializable{
	
	private Inventario inventarioSelec;
	private List<Inventario> listaInventario;
	private List<InventarioDetalle> listaInventarioDetalle;
	private Boolean editarInventario;
	private MenuServices menuServices;
	private InventarioService inventarioService;
	private InventarioDetalleService inventariodetalleService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private Producto productoEncontrado;
	
	private List<Almacen> listAlmacen;
	private AlmacenService almacenService;
	private ProductoService productoService;
	private Producto productoSelec;
	private List<Producto>listaProductos;


	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public InventarioMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.inventarioSelec = new Inventario();
		this.inventarioService = new InventarioService();
		this.menuServices=new MenuServices();
		this.almacenService = new AlmacenService();
		this.productoService = new ProductoService();
		this.inventariodetalleService = new InventarioDetalleService();
		
		this.editarInventario = Boolean.FALSE;
		this.emisorService = new EmisorService();
		this.productoEncontrado = new Producto();

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaInventario = this.inventarioService.listarTodosInventario();
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.listAlmacen = this.almacenService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:inventario");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
			this.listaProductos=this.productoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/* para tabla maestra */
	
	public void guardarInventario(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			

					 if(this.editarInventario) {
							this.inventarioService.actualizarInventario(this.inventarioSelec);
							 
							/*Llamamos Inventario Detalle y actualizamos*/
							InventarioDetalle inventarioDetalle = new InventarioDetalle();
							inventarioDetalle= this.inventariodetalleService.findById(this.inventarioSelec.getId_almacen(), 0, 0, this.inventarioSelec.getId_producto(), this.inventarioSelec.getId_emisor(), this.inventarioSelec.getId_domicilio_fiscal(), 0);
							inventarioDetalle.setCantidad_ingresa(this.inventarioSelec.getCantidad());
							this.inventariodetalleService.actualizarInventarioDetalle(inventarioDetalle);
							
							 log.setAccion("UPDATE");
				             log.setDescripcion("Se actualiza la inventario : " + this.inventarioSelec.getId_almacen());
				             logmb.insertarLog(log);
							FacesUtils.showFacesMessage("Inventario ha sido actualizado", 3);
							context.update("msgGeneral");
						}else{
							
							Inventario inventarioValidado = new Inventario();
							inventarioValidado = this.inventarioService.findById(this.inventarioSelec.getId_almacen(), this.inventarioSelec.getId_producto(), this.emisorSelec.getId_emisor(), this.emisorSelec.getId_domicilio_fiscal_cab());
							
							
							
							if(inventarioValidado== null){
								
									this.inventarioSelec.setId_emisor(this.emisorSelec.getId_emisor());
									this.inventarioSelec.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
									this.inventarioService.crearInventario(this.inventarioSelec);
									
									/*Creamos Inventario Detalle*/
									InventarioDetalle inventarioDetalle = new InventarioDetalle();
									inventarioDetalle.setId_almacen(this.inventarioSelec.getId_almacen());
									inventarioDetalle.setId_producto(this.inventarioSelec.getId_producto());
									inventarioDetalle.setId_emisor(this.inventarioSelec.getId_emisor());
									inventarioDetalle.setId_domicilio_fiscal(this.inventarioSelec.getId_domicilio_fiscal());
									inventarioDetalle.setId_despacho(0);
									inventarioDetalle.setId_arribo(0);
									inventarioDetalle.setId_tipo_producto(this.inventarioSelec.getId_producto());
									inventarioDetalle.setFecha_movimiento(new Date());
									inventarioDetalle.setCantidad_ingresa(this.inventarioSelec.getCantidad());
									inventarioDetalle.setCantidad_salida(new BigDecimal(0.0));
									inventarioDetalle.setTipo_movimiento("CREACION INVENTARIO");
									inventarioDetalle.setId_almacen_transferencia(0);
									
									this.inventariodetalleService.crearInventarioDetalle(inventarioDetalle);
									
									/*Actualizamos el Stock del producto*/
									
									BigDecimal stock = new BigDecimal(0.0);
									stock = this.inventarioService.cantidadStock(this.inventarioSelec.getId_producto());
									System.out.println( " stock " + stock);
									this.productoService.actualizarStockProductoPorCodigo(this.inventarioSelec.getId_producto(), stock);
									
										
									/*Todo Ok*/
									 log.setAccion("INSERT");
						             log.setDescripcion("Se inserta la inventario : " + this.inventarioSelec.getId_almacen());
						             logmb.insertarLog(log);
									 FacesUtils.showFacesMessage("Inventario ha sido creado", 3);
									 context.update("msgGeneral");
								
							}else{
								
				   	    		valido=Boolean.FALSE; 
					   	   	    context.addCallbackParam("esValido", valido);
					     	   	FacesUtils.showFacesMessage("Inventario ya existe ", Constante.FATAL); 
					   	   	    context.update("msgNuevo"); 
								
							}
							
							
							System.out.println("Micabezona 1");
						}
				 		
			
			System.out.println("Micabezona 2");
			this.inventarioSelec = new Inventario();
			this.editarInventario = Boolean.FALSE;
			
			this.listaInventario = this.inventarioService.listarTodosInventario();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoInventario(){
		System.out.println("nuevo inventario");
		this.inventarioSelec = new Inventario();
		this.editarInventario = Boolean.FALSE;
	}

	public void editarInventario(Inventario inventario) throws Exception{	
		
		 RequestContext context = RequestContext.getCurrentInstance();
		 List<InventarioDetalle> listainventarioDetalleValidar = this.inventariodetalleService.buscarPorInventario(inventario.getId_producto(),
																									                 inventario.getId_almacen(), 			
																									                 this.emisorSelec.getId_emisor(), 
																									                 this.emisorSelec.getId_domicilio_fiscal_cab());
		 Integer contador = listainventarioDetalleValidar.size();
		 
		 if(contador <= 1){
			 	this.productoEncontrado=this.productoService.findById(inventario.getId_producto());
				this.inventarioSelec = inventario;
				this.editarInventario = Boolean.TRUE;
				RequestContext.getCurrentInstance().execute("PF('dlgNuevoInventario').show()");
				context.update("msgGeneral");
			 
		 }else{
				FacesUtils.showFacesMessage("No es posible editar Inventario, tiene movimientos asociados", 1);
				RequestContext.getCurrentInstance().execute("PF('dlgNuevoInventario').hide()");
				context.update("msgGeneral");
		 }

	}
	
	
	public void eliminarInventario(Inventario inventario) throws Exception{
		
		
		RequestContext context = RequestContext.getCurrentInstance();
		List<InventarioDetalle> listainventarioDetalleValidar = this.inventariodetalleService.buscarPorInventario(inventario.getId_producto(),
																				                 inventario.getId_almacen(), 			
																				                 this.emisorSelec.getId_emisor(), 
																				                 this.emisorSelec.getId_domicilio_fiscal_cab());
         Integer contador = listainventarioDetalleValidar.size();

		if(contador <= 1){
			this.inventarioSelec = inventario;
			RequestContext.getCurrentInstance().execute("PF('dlgEliminarInventario').show()");
			context.update("msgGeneral");
		}else{
			FacesUtils.showFacesMessage("No es posible eliminar Inventario, tiene movimientos asociados", 1);
			RequestContext.getCurrentInstance().execute("PF('dlgEliminarInventario').hide()");
			context.update("msgGeneral");
		}
		
	}
	
	public void detalleInventario(Inventario inventario) throws Exception{
		
		this.inventarioSelec = inventario;
		this.listaInventarioDetalle = inventariodetalleService.buscarPorInventario(inventario.getId_producto(),
				                                                                   inventario.getId_almacen(), 			
				                                                                   this.emisorSelec.getId_emisor(), 
				                                                                   this.emisorSelec.getId_domicilio_fiscal_cab());
		
		
		this.editarInventario = Boolean.TRUE;
	}
	
	
	public void confirmaEliminarInventario(){
		try {
		
			this.inventarioService.eliminarInventario(this.inventarioSelec.getId_almacen(), this.inventarioSelec.getId_producto(), this.inventarioSelec.getId_emisor(), this.inventarioSelec.getId_domicilio_fiscal());
			this.inventariodetalleService.eliminarInventarioDetalle(this.inventarioSelec.getId_almacen(), 0, 0, this.inventarioSelec.getId_producto(), this.inventarioSelec.getId_emisor(), this.inventarioSelec.getId_domicilio_fiscal(), 0);
			
			
			/*Actualizamos el Stock del producto*/
			
			BigDecimal stock = new BigDecimal(0.0);
			stock = this.inventarioService.cantidadStock(this.inventarioSelec.getId_producto());
			System.out.println( " stock " + stock);
			this.productoService.actualizarStockProductoPorCodigo(this.inventarioSelec.getId_producto(), stock);
			
			
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina inventario: " + this.inventarioSelec.getId_almacen());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Inventario ha sido eliminado", 3);
			
			this.listaInventario = this.inventarioService.listarTodosInventario();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<Producto> consultarProductoCod(String query) throws Exception{
		
		System.out.println("entrando autocomplete");
        List<Producto> allProducts = this.productoService.findAll();
        List<Producto> filteredProducts = new ArrayList<Producto>();
         
        for (int i = 0; i < allProducts.size(); i++) {
        	Producto skin = allProducts.get(i);
            if(skin.getCod_prod_det().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProducts.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredProducts.size());
		
		return filteredProducts;
		
	}
	
	public List<Producto> consultarProducto(String query) throws Exception{
		
		System.out.println("entrando autocomplete");
        List<Producto> allProducts = this.productoService.findAll();
        List<Producto> filteredProducts = new ArrayList<Producto>();
         
        for (int i = 0; i < allProducts.size(); i++) {
        	Producto skin = allProducts.get(i);
            if(skin.getDescripcion_prod_det().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProducts.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredProducts.size());
		
		return filteredProducts;
		
	}
	
	
	public void onItemSelect()  throws Exception{
		
		for(Producto p : this.listaProductos){
			if(p.getDescripcion_prod_det().trim().equals(this.productoEncontrado.getDescripcion_prod_det().trim())){
				this.productoSelec=p;
				break;
			}
		}
		this.productoEncontrado=this.productoService.findById(this.productoSelec.getId_producto());
		this.inventarioSelec.setProducto(this.productoEncontrado);
		this.inventarioSelec.setId_producto(this.productoSelec.getId_producto());
		
		
	}
	

	
	public void onItemSelectCod()  throws Exception{
			
		for(Producto p : this.listaProductos){
			if(p.getCod_prod_det().trim().equals(this.productoEncontrado.getCod_prod_det())){
				this.productoSelec=p;
				break;
			}
		}
		this.productoEncontrado=this.productoService.findById(this.productoSelec.getId_producto());	
		
		this.inventarioSelec.setId_producto(this.productoSelec.getId_producto());
		this.inventarioSelec.setProducto(this.productoEncontrado);
		
			
	}
			
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/

	
	public Inventario getInventarioSelec() {
		return inventarioSelec;
	}

	public void setInventarioSelec(Inventario inventarioSelec) {
		this.inventarioSelec = inventarioSelec;
	}

	public List<Inventario> getListaInventario() {
		return listaInventario;
	}

	public void setListaInventario(List<Inventario> listaInventario) {
		this.listaInventario = listaInventario;
	}

	public Boolean getEditarInventario() {
		return editarInventario;
	}

	public void setEditarInventario(Boolean editarInventario) {
		this.editarInventario = editarInventario;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public InventarioService getInventarioService() {
		return inventarioService;
	}

	public void setInventarioService(InventarioService inventarioService) {
		this.inventarioService = inventarioService;
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

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public Producto getProductoEncontrado() {
		return productoEncontrado;
	}

	public void setProductoEncontrado(Producto productoEncontrado) {
		this.productoEncontrado = productoEncontrado;
	}

	public Producto getProductoSelec() {
		return productoSelec;
	}

	public void setProductoSelec(Producto productoSelec) {
		this.productoSelec = productoSelec;
	}

	public List<InventarioDetalle> getListaInventarioDetalle() {
		return listaInventarioDetalle;
	}

	public void setListaInventarioDetalle(List<InventarioDetalle> listaInventarioDetalle) {
		this.listaInventarioDetalle = listaInventarioDetalle;
	}

	public InventarioDetalleService getInventariodetalleService() {
		return inventariodetalleService;
	}

	public void setInventariodetalleService(InventarioDetalleService inventariodetalleService) {
		this.inventariodetalleService = inventariodetalleService;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	

}
