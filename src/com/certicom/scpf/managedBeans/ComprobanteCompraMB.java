package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Comprobante;
import com.certicom.scpf.domain.ComprobanteCompra;
import com.certicom.scpf.domain.ComprobanteCompraDetalle;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.domain.MovimientoProveedores;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.domain.Proveedores;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.TributoProducto;
import com.certicom.scpf.services.ComprobanteCompraDetalleService;
import com.certicom.scpf.services.ComprobanteCompraService;
import com.certicom.scpf.services.ComprobanteDetalleService;
import com.certicom.scpf.services.DomicilioFiscalService;
import com.certicom.scpf.services.EmisorService;
//import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ModoPagoService;
import com.certicom.scpf.services.MovimientoInventarioProveedorService;
import com.certicom.scpf.services.MovimientoProveedorService;
import com.certicom.scpf.services.ProductoService;
import com.certicom.scpf.services.ProveedorService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.certicom.scpf.services.TributoProductoService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="comprobanteCompraMB")
@ViewScoped
public class ComprobanteCompraMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ComprobanteCompra comprobanteCompraSelec;
	private ComprobanteCompraDetalle comprobanteCompraDetalleSelec;
	private List<ComprobanteCompra> listaComprobanteCompra;
	private Boolean editarCliente;
	private MenuServices menuServices;
	private ComprobanteCompraService comprobanteCompraService;
	private ProveedorService proveedorService;
	private ModoPagoService modoPagoService;
	private List<ModoPago> listModoPagos;
	private Integer id_modo_pago = 0;
	private List<TributoProducto> listTributoProductos;
	private TributoProductoService tributoProductoService;
	private TablaTablasDetalle tablaTablasDetalleTipoComprobante;
	private List<ComprobanteCompraDetalle> listaComprobanteCompraDetalle;
	private Proveedores proveedorEncontrado;
	private Producto productoEncontrado;
	private Producto productoSelec;
	private ProductoService productoService;
	private ComprobanteCompraDetalleService comprobanteCompraDetalleService;
	private String nroserie_documento;
	private MovimientoInventarioProveedorService movimientoInventarioProveedorService;
	
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private List<TablaTablasDetalle> listTablaTablasDetallesOperacion;
	private List<TablaTablasDetalle> listTablaTablasDetallesMoneda;
	private List<TablaTablasDetalle> listTablaTablasDetallesProducto;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private DomicilioFiscal domicilioFiscalSelec;
	private DomicilioFiscalService domicilioFiscalService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private Date fechaActual;
	//private EmisorService emisorService;
	private MovimientoProveedores movimientoProveedores;
	private MovimientoProveedorService movimientoProveedorService; 
	
	private boolean adicionar; /*Jesus*/
    private boolean generarComprobante; /*Jesus*/
    private boolean ingresarProveedor; /*Jesus*/
	
	private List<Cliente>listaClientesFilter;
	private Boolean estadoEditarProducto;
	private int posicionEdicion;
	private List<Producto> listaProductos;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ComprobanteCompraMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.comprobanteCompraSelec = new ComprobanteCompra();
		this.comprobanteCompraService = new ComprobanteCompraService();
		this.proveedorService = new ProveedorService();
		this.proveedorEncontrado = new Proveedores();
		this.modoPagoService = new ModoPagoService();
		this.menuServices=new MenuServices();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.ingresarProveedor = Boolean.TRUE;
		this.comprobanteCompraSelec.setFecha_emision(new Date()); /*Jesus*/
		this.comprobanteCompraSelec.setFecha_vencimiento(new Date()); /*Jesus*/
		this.comprobanteCompraSelec.setHora_emision(new Date());/*Jesus*/
		//this.comprobanteCompraSelec.setId_modo_pago(4); /* Jesus contado*/
		this.comprobanteCompraSelec.setTipo_operacion("0101"); /* Jesus Tipo Operacion*/
		this.comprobanteCompraSelec.setTipo_moneda_cab("PEN"); /* Jesus Tipo Moneda*/
		this.adicionar = Boolean.TRUE;
		this.estadoEditarProducto=Boolean.FALSE;
		//this.comprobanteCompraSelec.setId_vendedor(2);
		this.productoEncontrado= new Producto();
		this.productoSelec= new Producto();
		this.productoService= new ProductoService();
		this.editarCliente = Boolean.FALSE;
		this.posicionEdicion=-1;
		this.tributoProductoService= new TributoProductoService();
		this.movimientoInventarioProveedorService= new MovimientoInventarioProveedorService();
		this.listaComprobanteCompraDetalle= new ArrayList<ComprobanteCompraDetalle>();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		this.comprobanteCompraDetalleService= new ComprobanteCompraDetalleService();
		this.movimientoProveedores=new MovimientoProveedores();
		this.movimientoProveedorService=new MovimientoProveedorService();
		this.emisorService= new EmisorService();
		this.nroserie_documento="";
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listaComprobanteCompra = this.comprobanteCompraService.findAll();
			this.listModoPagos = this.modoPagoService.findAll();
			this.listaProductos=this.productoService.findAll();
			this.fechaActual = new Date();
			System.out.println("CANTIDAD DE MODO PAGOS: "+this.listModoPagos.size());
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:comprobanteCompra");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public void setNumeroSerie(){
		System.out.println(" nroserie_documento -----> "+this.nroserie_documento);
		this.comprobanteCompraSelec.setNroserie_documento(this.nroserie_documento);
	}
	
	public void eliminarProducto(ComprobanteCompraDetalle detalle){
		this.comprobanteCompraDetalleSelec=detalle;
		this.productoEncontrado=this.comprobanteCompraDetalleSelec.getProducto();
		this.estadoEditarProducto=Boolean.TRUE;
		try {
			consultarProductoCod(this.productoEncontrado.getCod_prod_det());
			for(int i=0;i<listaComprobanteCompraDetalle.size(); i++){
				if(this.comprobanteCompraDetalleSelec.getId_producto()==listaComprobanteCompraDetalle.get(i).getId_producto()){
					this.posicionEdicion=i;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void confirmaEliminarProducto(){
		
			ComprobanteCompraDetalle comprobanteDetallex = this.listaComprobanteCompraDetalle.get(posicionEdicion);
			this.comprobanteCompraSelec.setSuma_tributos_cab(this.comprobanteCompraSelec.getSuma_tributos_cab().subtract(comprobanteDetallex.getSuma_tributos_det()));
			this.comprobanteCompraSelec.setTotal_precio_venta_cab(this.comprobanteCompraSelec.getTotal_precio_venta_cab().subtract(comprobanteDetallex.getPrecio_venta_unitario_det()));
			this.comprobanteCompraSelec.setTotal_valor_venta_cab(this.comprobanteCompraSelec.getTotal_valor_venta_cab().subtract(comprobanteDetallex.getValor_venta_item_det()));
			this.comprobanteCompraSelec.setImporte_total_venta_cab(this.comprobanteCompraSelec.getImporte_total_venta_cab().subtract(comprobanteDetallex.getPrecio_venta_unitario_det()));
			this.listaComprobanteCompraDetalle.remove(this.comprobanteCompraDetalleSelec);
			this.estadoEditarProducto=Boolean.FALSE;
			this.posicionEdicion=-1;
	}
	
	public void editarProducto(ComprobanteCompraDetalle detalle){
		this.comprobanteCompraDetalleSelec=detalle;
		this.productoEncontrado=this.comprobanteCompraDetalleSelec.getProducto();
		this.estadoEditarProducto=Boolean.TRUE;
		try {
			consultarProductoCod(this.productoEncontrado.getCod_prod_det());
			for(int i=0;i<listaComprobanteCompraDetalle.size(); i++){
				if(this.comprobanteCompraDetalleSelec.getId_producto()==listaComprobanteCompraDetalle.get(i).getId_producto()){
					this.posicionEdicion=i;
				}
			}
			
			this.comprobanteCompraDetalleSelec.setCant_unidades_item_det(detalle.getCant_unidades_item_det());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancelarAdicion(){
		System.out.println("cancelarAdicion--->");
		this.comprobanteCompraDetalleSelec= new ComprobanteCompraDetalle();
		this.productoEncontrado=new Producto();
		this.productoSelec= new Producto();
	}
	
	public void onItemSelect()  throws Exception{

		for(Producto p: this.listaProductos){
			if(p.getDescripcion_prod_det().trim().toUpperCase().equalsIgnoreCase(this.productoEncontrado.getDescripcion_prod_det().trim().toUpperCase())){
				this.productoSelec = p;    
			}
		}
		
		this.productoEncontrado=this.productoService.findById(this.productoSelec.getId_producto());
		
		if(this.productoSelec.isValor_unit_incluye_impuestos()== Boolean.FALSE){			
			  System.out.println("false : " + this.productoSelec.isValor_unit_incluye_impuestos());
			  this.productoSelec.setPrecio_final_editado_cliente(this.productoSelec.getValor_unitario_prod_det()); 		
			  this.comprobanteCompraDetalleSelec.setId_producto(this.productoSelec.getId_producto());
			  this.comprobanteCompraDetalleSelec.setProducto(this.productoSelec);
		
		
		this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
		
		for (TributoProducto tp : this.listTributoProductos) {
			
			if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
				this.comprobanteCompraDetalleSelec.setTpISC(tp);
			}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteCompraDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().divide(new BigDecimal("100.00")));
				this.comprobanteCompraDetalleSelec.setTpIGV(tp);
			}else{
				this.comprobanteCompraDetalleSelec.setTpOtros(tp);
			}
		}
				
					this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add((this.comprobanteCompraDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoIGV()).add((this.comprobanteCompraDetalleSelec.getMontoISC() == null ? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoISC()))));					
			
		}else{
			
			        System.out.println("Everd es  true : " + this.productoSelec.isValor_unit_incluye_impuestos());
			        this.productoSelec.setPrecio_final_editado_cliente(this.productoSelec.getValor_unitario_prod_det()); /*Jesus*/			
					this.comprobanteCompraDetalleSelec.setId_producto(this.productoSelec.getId_producto());
					this.comprobanteCompraDetalleSelec.setProducto(this.productoSelec);
					
					
					this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
					
					for (TributoProducto tp : this.listTributoProductos) {
						
						if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
							TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
							tp.setDescTT(ttd.getDescripcion_largo());
							this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
							this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
							this.comprobanteCompraDetalleSelec.setTpISC(tp);
						}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
							TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
							tp.setDescTT(ttd.getDescripcion_largo());
							this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente());
							this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().multiply(new BigDecimal("18.00")));
							this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().divide(new BigDecimal("118.00"), RoundingMode.HALF_UP));
							this.productoSelec.setValor_unitario_prod_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteCompraDetalleSelec.getMontoIGV()));
							this.comprobanteCompraDetalleSelec.setTpIGV(tp);
						}else{
							this.comprobanteCompraDetalleSelec.setTpOtros(tp);
						}
					}
					
					
					//this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoIGV()).add((this.comprobanteDetalleSelec.getMontoISC() == null ? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()))));					
					
		}
		
		
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Seleccionado", this.productoSelec.getDescripcion_prod_det()));
    }
	
	public List<Proveedores> consultarProveedor(String query) throws Exception {
		System.out.println("entrando autocomplete");
        List<Proveedores> allProveedores = this.proveedorService.findAll();
        List<Proveedores> filteredProveedores = new ArrayList<Proveedores>();
        System.out.println("allProveedores --->"+allProveedores.size()); 
        for (int i = 0; i < allProveedores.size(); i++) {
        	Proveedores skin = allProveedores.get(i);
            if(skin.getNombre_proveedor().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProveedores.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredProveedores.size());
         
        return filteredProveedores;
    }
	
	public void adicionarProducto(){
		this.productoEncontrado = new Producto();
		this.comprobanteCompraDetalleSelec = new ComprobanteCompraDetalle();
		
	}
	
	public void onItemSelectCod()  throws Exception{
		
		
		for(Producto p: this.listaProductos){
			if(p.getCod_prod_det().trim().toUpperCase().equalsIgnoreCase(this.productoEncontrado.getCod_prod_det().trim().toUpperCase())){
				this.productoSelec=p;
			}
		}
		
		this.productoEncontrado=this.productoService.findById(this.productoSelec.getId_producto());
		
		if(this.productoSelec.isValor_unit_incluye_impuestos()== Boolean.FALSE){
			
			        System.out.println("Everd es  false : " + this.productoSelec.isValor_unit_incluye_impuestos());
			        
			        this.productoSelec.setPrecio_final_editado_cliente(this.productoSelec.getValor_unitario_prod_det()); /*Jesus*/
			
		this.comprobanteCompraDetalleSelec.setId_producto(this.productoSelec.getId_producto());
		this.comprobanteCompraDetalleSelec.setProducto(this.productoSelec);
		
		
		this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
		
		for (TributoProducto tp : this.listTributoProductos) {
			
			if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
				this.comprobanteCompraDetalleSelec.setTpISC(tp);
			}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
				TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
				tp.setDescTT(ttd.getDescripcion_largo());
				this.comprobanteCompraDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().divide(new BigDecimal("100.00")));
				this.comprobanteCompraDetalleSelec.setTpIGV(tp);
			}else{
				this.comprobanteCompraDetalleSelec.setTpOtros(tp);
			}
		}
		
		
					this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add((this.comprobanteCompraDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoIGV()).add((this.comprobanteCompraDetalleSelec.getMontoISC() == null ? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoISC()))));					
			
		}else{
			
			        System.out.println("Everd es  true : " + this.productoSelec.isValor_unit_incluye_impuestos());
			        this.productoSelec.setPrecio_final_editado_cliente(this.productoSelec.getValor_unitario_prod_det()); /*Jesus*/
			
					this.comprobanteCompraDetalleSelec.setId_producto(this.productoSelec.getId_producto());
					this.comprobanteCompraDetalleSelec.setProducto(this.productoSelec);
					
					
					this.listTributoProductos = this.tributoProductoService.findByIdProducto(this.productoSelec.getId_producto());
					
					for (TributoProducto tp : this.listTributoProductos) {
						
						if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
							TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_sistema_isc_det());
							tp.setDescTT(ttd.getDescripcion_largo());
							this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
							this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
							this.comprobanteCompraDetalleSelec.setTpISC(tp);
						}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
							TablaTablasDetalle ttd = this.tablaTablasDetalleService.findById(tp.getTipo_afectacion_igv_det());
							tp.setDescTT(ttd.getDescripcion_largo());
							this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente());
							this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().multiply(new BigDecimal("18.00")));
							this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().divide(new BigDecimal("118.00"), RoundingMode.HALF_UP));
							this.productoSelec.setValor_unitario_prod_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteCompraDetalleSelec.getMontoIGV()));
							this.comprobanteCompraDetalleSelec.setTpIGV(tp);
						}else{
							this.comprobanteCompraDetalleSelec.setTpOtros(tp);
						}
					}
					
					
					//this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det().add((this.comprobanteDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoIGV()).add((this.comprobanteDetalleSelec.getMontoISC() == null ? new BigDecimal("0.00"):this.comprobanteDetalleSelec.getMontoISC()))));					
					
		}
		
		
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Seleccionado", this.productoSelec.getCod_prod_det()));
    }
	
	public void calcularMonto(){
		Producto p=new Producto();
		try {
			p = this.productoService.findById(this.comprobanteCompraDetalleSelec.getId_producto());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (TributoProducto tp : this.listTributoProductos) {
									
			if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
				
				if(p.isValor_unit_incluye_impuestos()){
					this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
				}else{
					this.comprobanteCompraDetalleSelec.setValor_venta_item_det(this.productoSelec.getPrecio_final_editado_cliente()
							.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
					this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.comprobanteCompraDetalleSelec.getValor_venta_item_det()));
				}
				
				this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC()
						.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
				this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
				this.comprobanteCompraDetalleSelec.setTpISC(tp);
			}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
				if(p.isValor_unit_incluye_impuestos()){
					
					this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente()
							.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()
									)
						  );
					
//					System.out.println("TOTAL:---->"+this.comprobanteDetalleSelec.getPrecio_venta_unitario_det());
					BigDecimal per=(tp.getPorcentaje_det().add(new BigDecimal("100.00"))).divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP);
							
							
					
//					System.out.println(" DIVE ENTRE IGV ---->"+per+" % "+tp.getPorcentaje_det());
					
					this.comprobanteCompraDetalleSelec.setValor_venta_item_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det()
										 								.divide(per,2, RoundingMode.HALF_UP));
//					System.out.println("SUB TOTAL ---------->"+this.comprobanteDetalleSelec.getValor_venta_item_det());
					
					this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det()
															.subtract(this.comprobanteCompraDetalleSelec.getValor_venta_item_det()));

//					System.out.println(" IGV ------->"+this.comprobanteDetalleSelec.getMontoIGV());
				}else{
					
					this.comprobanteCompraDetalleSelec.setValor_venta_item_det(this.productoSelec.getPrecio_final_editado_cliente()
												.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
					
					BigDecimal per=(tp.getPorcentaje_det().divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP));
					
					this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getValor_venta_item_det()
							                    .multiply(per));
					
					this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteCompraDetalleSelec.getValor_venta_item_det()
							                    .add(this.comprobanteCompraDetalleSelec.getMontoIGV()));
					
				}
				
				this.comprobanteCompraDetalleSelec.setSuma_tributos_det((this.comprobanteCompraDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteCompraDetalleSelec.getMontoIGV()).add(this.comprobanteCompraDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoISC()));
				this.comprobanteCompraDetalleSelec.setTpIGV(tp);
			}else{
				this.comprobanteCompraDetalleSelec.setTpOtros(tp);
			}
			
		}
		
	}
	
	public boolean buscarComprobante(String numero_serie_documento_cab){
		List<Comprobante> lista= new ArrayList<>();
		boolean respuesta=false;
		try {
			lista=this.comprobanteCompraService.findByNumeroSerie(numero_serie_documento_cab);
			if(!lista.isEmpty()){
				respuesta= true;
			}else{
				respuesta= false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;
	}
	
	public void guardarComprobante(){
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
//			System.out.println(" this.comprobanteCompraSelec.getNroserie_documento() ------->"+this.comprobanteCompraSelec.getNroserie_documento());
//			System.out.println("this.nroserie_documento ============> "+this.nroserie_documento);
			if(buscarComprobante(this.comprobanteCompraSelec.getNroserie_documento())){
				FacesUtils.showFacesMessage("Ya existe el comprobante " + this.comprobanteCompraSelec.getNroserie_documento(), 3);
			}else{
				  
				  	this.comprobanteCompraSelec.setSuma_tributos(this.comprobanteCompraSelec.getSuma_tributos_cab());
				  	this.comprobanteCompraSelec.setTotal_valor_compra(this.comprobanteCompraSelec.getTotal_valor_venta_cab());
					this.comprobanteCompraSelec.setTotal_precio_compra(this.comprobanteCompraSelec.getTotal_precio_venta_cab());
					this.comprobanteCompraSelec.setSuma_otros_cargos(this.comprobanteCompraSelec.getSuma_otros_cargos_cab());
					this.comprobanteCompraSelec.setImporte_total_compra(this.comprobanteCompraSelec.getImporte_total_venta_cab());
					
					this.comprobanteCompraSelec.setVersion_ubl(Constante.VERSION_UBL_SUNAT);
					this.comprobanteCompraSelec.setEstado_comunicacion_baja(Boolean.FALSE);
					this.comprobanteCompraSelec.setCorrelativo(this.comprobanteCompraService.getCorrelativoComprobante(this.comprobanteCompraSelec.getTipo_comprobante()));
					this.comprobanteCompraSelec.setEstado_sunat(Constante.ESTADO_PENDIENTE);
					this.comprobanteCompraService.crearComprobanteCompraSec(this.comprobanteCompraSelec);
					int id = this.comprobanteCompraService.getSecIdComprobante();
					System.out.println("ID: "+id);
					
					this.comprobanteCompraDetalleService.insertBatchComprobanteDetalle(this.listaComprobanteCompraDetalle, id-1);
					this.movimientoInventarioProveedorService.insertBatchMovimientoInventarioProveedor(this.listaComprobanteCompraDetalle, id-1);					
					this.comprobanteCompraSelec.setId_comprobante_compra(id-1);
					registrarMovimentoProveedor(this.comprobanteCompraSelec);

					context.update("msgGeneral");
					context.update("formAction");
					FacesUtils.showFacesMessage("Se registro el comprobante " + this.comprobanteCompraSelec.getNroserie_documento(), 3);
			}
			
			this.comprobanteCompraSelec= new ComprobanteCompra();
			this.listaComprobanteCompraDetalle= new ArrayList<ComprobanteCompraDetalle>();
			
			
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listModoPagos = this.modoPagoService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.comprobanteCompraSelec.setId_emisor(this.emisorSelec.getId_emisor());
			this.proveedorEncontrado= new Proveedores();
			this.productoEncontrado=new Producto();
			this.productoEncontrado.setDescripcion_prod_det("");
			this.productoEncontrado.setCod_prod_det("");
			
			this.comprobanteCompraSelec.setFecha_emision_cab(new Date()); 
			this.comprobanteCompraSelec.setFecha_vencimiento_cab(new Date()); 
			this.comprobanteCompraSelec.setHora_emision_cab(new Date());
			this.comprobanteCompraSelec.setId_modo_pago(4); 
			this.comprobanteCompraSelec.setTipo_operacion_cab("0101"); 
			this.comprobanteCompraSelec.setTipo_moneda_cab("PEN"); 
			this.adicionar = Boolean.TRUE; 
			this.generarComprobante = Boolean.TRUE;
			this.nroserie_documento="";
			
//			this.ingresarCliente = Boolean.TRUE; 
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void registrarMovimentoProveedor(ComprobanteCompra compra) {
		// TODO Auto-generated method stub
		MovimientoProveedores movimiento=new MovimientoProveedores();
		movimiento.setFecha_movimiento(compra.getFecha_emision());
		movimiento.setFecha_vencimiento(compra.getFecha_vencimiento());
		movimiento.setForma_pago(compra.getId_modo_pago());
		movimiento.setId_comprobante_compra(compra.getId_comprobante_compra());
		movimiento.setId_proveedor(compra.getId_proveedor());
		movimiento.setImporte(compra.getImporte_total_compra());
		movimiento.setNroserie_documento(compra.getNroserie_documento());
		movimiento.setTipo_comprobante(compra.getTipo_comprobante());
		
		
		this.movimientoProveedorService.crearMovimiento(movimiento);
	}

	public void adicionarCompra(){
		
		/*vega.com*/
		Boolean valido=Boolean.TRUE;
   	       	    
   	    try {
   	    	
		   	    	System.out.println("Entra a adicionarCompra");
		   	    	
		   	    	if(this.comprobanteCompraDetalleSelec.getProducto().getTipo_articulo().equals("Producto")){
		   	    		
		   	    		    valido=Boolean.TRUE;
				   	 		RequestContext context = RequestContext.getCurrentInstance();  
				   	   	    context.addCallbackParam("esValido", valido);
		   	    		
				   			this.comprobanteCompraDetalleSelec.setTipo_comprobante(this.comprobanteCompraSelec.getTipo_comprobante());
				   			this.comprobanteCompraDetalleSelec.setId_proveedor(this.comprobanteCompraSelec.getId_proveedor());
				   			this.comprobanteCompraDetalleSelec.setId_emisor(this.comprobanteCompraSelec.getId_emisor());
				   			this.comprobanteCompraDetalleSelec.setId_domicilio_fiscal_cab(this.comprobanteCompraSelec.getId_domicilio_fiscal_cab());
				   			this.comprobanteCompraDetalleSelec.setId_modo_pago(this.comprobanteCompraSelec.getId_modo_pago());
				   			System.out.println("MODO PAGO :"+this.comprobanteCompraSelec.getId_modo_pago());
				   			
				   			
				   			if(this.estadoEditarProducto){
			   					ComprobanteCompraDetalle comprobanteDetallex = this.listaComprobanteCompraDetalle.get(posicionEdicion);
				   				this.comprobanteCompraSelec.setSuma_tributos_cab(this.comprobanteCompraSelec.getSuma_tributos_cab().subtract(comprobanteDetallex.getSuma_tributos_det()));
				   				this.comprobanteCompraSelec.setTotal_precio_venta_cab(this.comprobanteCompraSelec.getTotal_precio_venta_cab().subtract(comprobanteDetallex.getPrecio_venta_unitario_det()));
				   				this.comprobanteCompraSelec.setTotal_valor_venta_cab(this.comprobanteCompraSelec.getTotal_valor_venta_cab().subtract(comprobanteDetallex.getValor_venta_item_det()));
				   				this.comprobanteCompraSelec.setImporte_total_venta_cab(this.comprobanteCompraSelec.getImporte_total_venta_cab().subtract(comprobanteDetallex.getPrecio_venta_unitario_det()));				   			
				   				this.listaComprobanteCompraDetalle.remove(posicionEdicion);
				   				this.estadoEditarProducto=Boolean.FALSE;
				   				this.posicionEdicion=-1;
			   			}
				   			
				   			
				   			this.listaComprobanteCompraDetalle.add(this.comprobanteCompraDetalleSelec);
				   			
				   			this.comprobanteCompraSelec.setSuma_tributos_cab(new BigDecimal("0.00"));
				   			this.comprobanteCompraSelec.setTotal_precio_venta_cab(new BigDecimal("0.00"));
				   			this.comprobanteCompraSelec.setTotal_valor_venta_cab(new BigDecimal("0.00"));
				   			this.comprobanteCompraSelec.setImporte_total_venta_cab(new BigDecimal("0.00"));
				   			
				   			for (ComprobanteCompraDetalle comprobanteDetalle : this.listaComprobanteCompraDetalle) {
				   				this.comprobanteCompraSelec.setSuma_tributos_cab(this.comprobanteCompraSelec.getSuma_tributos_cab().add(comprobanteDetalle.getSuma_tributos_det()));
				   				this.comprobanteCompraSelec.setTotal_precio_venta_cab(this.comprobanteCompraSelec.getTotal_precio_venta_cab().add(comprobanteDetalle.getPrecio_venta_unitario_det()));
				   				this.comprobanteCompraSelec.setTotal_valor_venta_cab(this.comprobanteCompraSelec.getTotal_valor_venta_cab().add(comprobanteDetalle.getValor_venta_item_det()));
				   				this.comprobanteCompraSelec.setImporte_total_venta_cab(this.comprobanteCompraSelec.getImporte_total_venta_cab().add(comprobanteDetalle.getPrecio_venta_unitario_det()));
				   			}
				   			
				   			this.generarComprobante = Boolean.FALSE; /*Jesus*/
				   			
				   			FacesUtils.showFacesMessage("Se adiciono "+this.comprobanteCompraDetalleSelec.getProducto().getDescripcion_prod_det(), 3); /*vega.com*/
				   			context.update("msgGeneral"); /*vega.com*/
		   	    		   
			   	    	
		   	    	}else{
		   	    		
		   	    	 valido=Boolean.TRUE;
			   	 		RequestContext context = RequestContext.getCurrentInstance();  
			   	   	    context.addCallbackParam("esValido", valido);
	   	    		
			   			this.comprobanteCompraDetalleSelec.setTipo_comprobante(this.comprobanteCompraSelec.getTipo_comprobante());
			   			this.comprobanteCompraDetalleSelec.setId_proveedor(this.comprobanteCompraSelec.getId_proveedor());
			   			this.comprobanteCompraDetalleSelec.setId_emisor(this.comprobanteCompraSelec.getId_emisor());
			   			this.comprobanteCompraDetalleSelec.setId_domicilio_fiscal_cab(this.comprobanteCompraSelec.getId_domicilio_fiscal_cab());
			   			this.comprobanteCompraDetalleSelec.setId_modo_pago(this.comprobanteCompraSelec.getId_modo_pago());
			   			System.out.println("MODO PAGO :"+this.comprobanteCompraSelec.getId_modo_pago());
			   			
			   			if(this.estadoEditarProducto){
		   					ComprobanteCompraDetalle comprobanteDetallex = this.listaComprobanteCompraDetalle.get(posicionEdicion);
			   				this.comprobanteCompraSelec.setSuma_tributos_cab(this.comprobanteCompraSelec.getSuma_tributos_cab().subtract(comprobanteDetallex.getSuma_tributos_det()));
			   				this.comprobanteCompraSelec.setTotal_precio_venta_cab(this.comprobanteCompraSelec.getTotal_precio_venta_cab().subtract(comprobanteDetallex.getPrecio_venta_unitario_det()));
			   				this.comprobanteCompraSelec.setTotal_valor_venta_cab(this.comprobanteCompraSelec.getTotal_valor_venta_cab().subtract(comprobanteDetallex.getValor_venta_item_det()));
			   				this.comprobanteCompraSelec.setImporte_total_venta_cab(this.comprobanteCompraSelec.getImporte_total_venta_cab().subtract(comprobanteDetallex.getPrecio_venta_unitario_det()));
			   				this.listaComprobanteCompraDetalle.remove(posicionEdicion);
			   				this.estadoEditarProducto=Boolean.FALSE;
			   				this.posicionEdicion=-1;
		   			}
			   			
			   			
			   			this.listaComprobanteCompraDetalle.add(this.comprobanteCompraDetalleSelec);
			   			
			   			this.comprobanteCompraSelec.setSuma_tributos_cab(new BigDecimal("0.00"));
			   			this.comprobanteCompraSelec.setTotal_precio_venta_cab(new BigDecimal("0.00"));
			   			this.comprobanteCompraSelec.setTotal_valor_venta_cab(new BigDecimal("0.00"));
			   			this.comprobanteCompraSelec.setImporte_total_venta_cab(new BigDecimal("0.00"));
			   			
			   			for (ComprobanteCompraDetalle comprobanteDetalle : this.listaComprobanteCompraDetalle) {
			   				this.comprobanteCompraSelec.setSuma_tributos_cab(this.comprobanteCompraSelec.getSuma_tributos_cab().add(comprobanteDetalle.getSuma_tributos_det()));
			   				this.comprobanteCompraSelec.setTotal_precio_venta_cab(this.comprobanteCompraSelec.getTotal_precio_venta_cab().add(comprobanteDetalle.getPrecio_venta_unitario_det()));
			   				this.comprobanteCompraSelec.setTotal_valor_venta_cab(this.comprobanteCompraSelec.getTotal_valor_venta_cab().add(comprobanteDetalle.getValor_venta_item_det()));
			   				this.comprobanteCompraSelec.setImporte_total_venta_cab(this.comprobanteCompraSelec.getImporte_total_venta_cab().add(comprobanteDetalle.getPrecio_venta_unitario_det()));
			   			}
			   			
			   			this.generarComprobante = Boolean.FALSE; /*Jesus*/
			   			
			   			FacesUtils.showFacesMessage("Se adiciono Item", 3); /*vega.com*/
			   			context.update("msgGeneral"); /*vega.com*/
		   	    		
		   	    	}
		   	    	
		   	    	
		   	    	

   	    	
   	    } catch (Exception e) {
			e.printStackTrace(); /*vega.com*/
		}
		
		
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
	
	public void onItemProveedor(SelectEvent event)  throws Exception{
		
		String s = event.getObject().toString();		
				
				
				List<Proveedores> allProveedores = this.proveedorService.findAll();
		        //List<Cliente> filteredProducts = new ArrayList<Cliente>();
				
				for (int i = 0; i < allProveedores.size(); i++) {
		        	Proveedores skin = allProveedores.get(i);
		            if(skin.getNombre_proveedor().equals(s)) {
		            	//filteredProducts.add(skin);
		            	//this.comprobanteSelec.setTipo_comprobante(skin.getDescripcion_prod_det());
		            	this.proveedorEncontrado = skin;
		            	break;
		            	
		            }
		        }		
				
				this.comprobanteCompraSelec.setId_proveedor(this.proveedorEncontrado.getId_proveedor());
				//this.comprobanteCompraSelec.set(this.clienteEncontrado.getTipo_docu_iden_cab());
				this.adicionar = Boolean.FALSE; /*Jesus*/
	}
	
	public void obtenerAbreviatura() throws Exception{
		
		System.out.println("obtenerAbreviatura");
		//this.comprobanteCompraSelec.setId_domicilio_fiscal(this.domicilioFiscalSelec.getId_domicilio_fiscal_cab());		
		this.proveedorEncontrado = new Proveedores();
		this.comprobanteCompraSelec.setFecha_emision(new Date()); /*Jesus*/
		this.comprobanteCompraSelec.setFecha_vencimiento(new Date()); /*Jesus*/
		this.comprobanteCompraSelec.setHora_emision(new Date());/*Jesus*/
		//this.comprobanteCompraSelec.set(4); /* Jesus contado*/
		this.comprobanteCompraSelec.setTipo_operacion("0101"); /* Jesus Tipo Operacion*/
		this.comprobanteCompraSelec.setTipo_moneda_cab("PEN"); /* Jesus Tipo Moneda*/
		//this.comprobanteCompraSelec.setId_vendedor(2);/*Vega.com*/
		this.comprobanteCompraSelec.setSuma_tributos(new BigDecimal("0.00"));
		this.comprobanteCompraSelec.setTotal_precio_compra(new BigDecimal("0.00"));
		this.comprobanteCompraSelec.setTotal_valor_compra(new BigDecimal("0.00"));
		this.comprobanteCompraSelec.setImporte_total_compra(new BigDecimal("0.00"));
		this.adicionar = Boolean.TRUE; /*Jesus*/
		this.generarComprobante=  Boolean.TRUE; /*Jesus*/
		this.ingresarProveedor = Boolean.FALSE; /*Jesus*/
		this.listaComprobanteCompra = new ArrayList<ComprobanteCompra>();/*Jesus*/		
		System.out.println("Código de Documento: "+this.comprobanteCompraSelec.getNroserie_documento());
		
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
	
public void calcularMontoPrecio(){
		
		
		try{   
			       if(this.productoSelec.isValor_unit_incluye_impuestos()== Boolean.FALSE){   
			    	   
			    	   System.out.println(" INCLUYE IMPUESTO FALSE ");
				
									for (TributoProducto tp : this.listTributoProductos) {
										
										if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
											this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
											this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC()
													.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
											this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
											this.comprobanteCompraDetalleSelec.setTpISC(tp);
										}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
											
											
											this.comprobanteCompraDetalleSelec.setMontoIGV(tp.getPorcentaje_det().multiply(this.productoSelec.getPrecio_final_editado_cliente()));
											
											this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().divide(new BigDecimal("100.00")));
											this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente().add(this.comprobanteCompraDetalleSelec.getMontoIGV()));
											this.productoSelec.setValor_unitario_prod_det(this.productoSelec.getPrecio_final_editado_cliente());
											this.comprobanteCompraDetalleSelec.setProducto(this.productoSelec);
											String unidades="1.00";
											if(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det().compareTo(new BigDecimal("0.00"))>0){
												unidades =this.comprobanteCompraDetalleSelec.getCant_unidades_item_det().toString();
											}
											
//											System.out.println(" IGV===========LINEA 3 >"+unidades);
											this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().multiply(new BigDecimal( unidades)));											
											this.comprobanteCompraDetalleSelec.setTpIGV(tp);
											
											
											
										}else{
											this.comprobanteCompraDetalleSelec.setTpOtros(tp);
										}
									}
									
									this.productoSelec.setValor_unitario_prod_det(this.productoSelec.getPrecio_final_editado_cliente());
									this.comprobanteCompraDetalleSelec.setProducto(this.productoSelec);
									this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getValor_unitario_prod_det()
											.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
									this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().add(this.comprobanteCompraDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteCompraDetalleSelec.getMontoIGV()));
									this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().add(this.comprobanteCompraDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"): this.comprobanteCompraDetalleSelec.getMontoISC()));
									this.comprobanteCompraDetalleSelec.setSuma_tributos_det((this.comprobanteCompraDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteCompraDetalleSelec.getMontoIGV()).add(this.comprobanteCompraDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoISC()));
									this.comprobanteCompraDetalleSelec.setValor_venta_item_det(this.productoSelec.getValor_unitario_prod_det()
											.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
									
					}else{
						System.out.println(" INCLUYE IMPUESTOS ----------> TRUE");
									for (TributoProducto tp : this.listTributoProductos) {
										
										if(tp.getTipo_tributo_det().equals(Constante.COD_ISC_IMPUESTO_SELECTIVO_AL_CONSUMO)){
											this.comprobanteCompraDetalleSelec.setMontoISC(tp.getPorcentaje_det().multiply(this.productoSelec.getValor_unitario_prod_det()));
											this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC()
													.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
											this.comprobanteCompraDetalleSelec.setMontoISC(this.comprobanteCompraDetalleSelec.getMontoISC().divide(new BigDecimal("100.00")));
											this.comprobanteCompraDetalleSelec.setTpISC(tp);
										}else if(tp.getTipo_tributo_det().equals(Constante.COD_IGV_IMPUESTO_GENERAL_A_LAS_VENTAS)){
											
											System.out.println(" IGV------------>");
											this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente());
											this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().multiply(new BigDecimal("18.00")));
											this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV().divide(new BigDecimal("118.00"), RoundingMode.HALF_UP));	
											this.productoSelec.setValor_unitario_prod_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteCompraDetalleSelec.getMontoIGV()));
											this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getMontoIGV()
													.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det()));
											
											
											
											this.comprobanteCompraDetalleSelec.setPrecio_venta_unitario_det(this.productoSelec.getPrecio_final_editado_cliente()
																										.multiply(this.comprobanteCompraDetalleSelec.getCant_unidades_item_det())
																									  );
											
											BigDecimal per=(tp.getPorcentaje_det().add(new BigDecimal("100.00"))).divide(new BigDecimal("100.00")).setScale(2, RoundingMode.HALF_UP);
											
											this.comprobanteCompraDetalleSelec.setValor_venta_item_det(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det()
																								 .divide(per, 2, RoundingMode.HALF_UP));
											
											this.comprobanteCompraDetalleSelec.setMontoIGV(this.comprobanteCompraDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteCompraDetalleSelec.getValor_venta_item_det()));
											
											this.comprobanteCompraDetalleSelec.setTpIGV(tp);
										}else{
											this.comprobanteCompraDetalleSelec.setTpOtros(tp);
										}
									}
									
//									this.comprobanteDetalleSelec.setPrecio_venta_unitario_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().multiply(new BigDecimal(this.comprobanteDetalleSelec.getCant_unidades_item_det())));
								    this.comprobanteCompraDetalleSelec.setSuma_tributos_det((this.comprobanteCompraDetalleSelec.getMontoIGV() == null? new BigDecimal("0.00"): this.comprobanteCompraDetalleSelec.getMontoIGV()).add(this.comprobanteCompraDetalleSelec.getMontoISC() == null? new BigDecimal("0.00"):this.comprobanteCompraDetalleSelec.getMontoISC()));
//									this.comprobanteDetalleSelec.setValor_venta_item_det(this.comprobanteDetalleSelec.getPrecio_venta_unitario_det().subtract(this.comprobanteDetalleSelec.getSuma_tributos_det()));
									
//					System.out.println(" SUB TOTAL----------->"+this.comprobanteDetalleSelec.getPrecio_venta_unitario_det());
//					System.out.println(" IGV----------------->"+this.comprobanteDetalleSelec.getMontoIGV());
//					System.out.println(" TOTAL -------------->"+this.comprobanteDetalleSelec.getValor_venta_item_det());
					
					}
								
		}catch (Exception e) { 
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
		}
		
		
		
		
	}
	
	/* para tabla maestra */
	
	public void guardarCliente(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarCliente) {
				this.comprobanteCompraService.actualizarComprobanteCompra(this.comprobanteCompraSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el comprobante de compra : " + this.comprobanteCompraSelec.getNroserie_documento());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("El comprobante de compra ha sido actualizado", 3);
			}else{
				this.comprobanteCompraService.crearComprobanteCompra(this.comprobanteCompraSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el comprobante de compra : " + this.comprobanteCompraSelec.getNroserie_documento());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Comprobante de Compra ha sido creado", 3);
			}
			
			this.comprobanteCompraSelec = new ComprobanteCompra();
			this.editarCliente = Boolean.FALSE;
			
			this.listaComprobanteCompra = this.comprobanteCompraService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoComprobanteCompra(){
		this.comprobanteCompraSelec = new ComprobanteCompra();
		this.editarCliente = Boolean.FALSE;
	}

	public void editarComprobanteCompra(ComprobanteCompra comprobanteCompra){
		this.comprobanteCompraSelec = comprobanteCompra;
		this.editarCliente = Boolean.TRUE;
	}
	
	public void eliminarComprobanteCompra(ComprobanteCompra comprobanteCompra){
		this.comprobanteCompraSelec = comprobanteCompra;
	}
	
	public void confirmaEliminarCliente(){
		try {
		
			this.comprobanteCompraService.eliminarComprobanteCompra(this.comprobanteCompraSelec.getId_comprobante_compra());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina el comprobante de Compra: " + this.comprobanteCompraSelec.getNroserie_documento());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Cliente ha sido eliminado", 3);
			
			this.listaComprobanteCompra = this.comprobanteCompraService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

	public Boolean getEditarCliente() {
		return editarCliente;
	}

	public ComprobanteCompra getComprobanteCompraSelec() {
		return comprobanteCompraSelec;
	}

	public void setComprobanteCompraSelec(ComprobanteCompra comprobanteCompraSelec) {
		this.comprobanteCompraSelec = comprobanteCompraSelec;
	}

	public void setEditarCliente(Boolean editarCliente) {
		this.editarCliente = editarCliente;
	}	

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
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

	public List<ComprobanteCompra> getListaComprobanteCompra() {
		return listaComprobanteCompra;
	}

	public void setListaComprobanteCompra(
			List<ComprobanteCompra> listaComprobanteCompra) {
		this.listaComprobanteCompra = listaComprobanteCompra;
	}

	public TablaTablasDetalle getTablaTablasDetalleTipoComprobante() {
		return tablaTablasDetalleTipoComprobante;
	}

	public void setTablaTablasDetalleTipoComprobante(
			TablaTablasDetalle tablaTablasDetalleTipoComprobante) {
		this.tablaTablasDetalleTipoComprobante = tablaTablasDetalleTipoComprobante;
	}

	public Proveedores getProveedorEncontrado() {
		return proveedorEncontrado;
	}

	public void setProveedorEncontrado(Proveedores proveedorEncontrado) {
		this.proveedorEncontrado = proveedorEncontrado;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesComprobante() {
		return listTablaTablasDetallesComprobante;
	}

	public void setListTablaTablasDetallesComprobante(
			List<TablaTablasDetalle> listTablaTablasDetallesComprobante) {
		this.listTablaTablasDetallesComprobante = listTablaTablasDetallesComprobante;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesOperacion() {
		return listTablaTablasDetallesOperacion;
	}

	public void setListTablaTablasDetallesOperacion(
			List<TablaTablasDetalle> listTablaTablasDetallesOperacion) {
		this.listTablaTablasDetallesOperacion = listTablaTablasDetallesOperacion;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesMoneda() {
		return listTablaTablasDetallesMoneda;
	}

	public void setListTablaTablasDetallesMoneda(
			List<TablaTablasDetalle> listTablaTablasDetallesMoneda) {
		this.listTablaTablasDetallesMoneda = listTablaTablasDetallesMoneda;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetallesProducto() {
		return listTablaTablasDetallesProducto;
	}

	public void setListTablaTablasDetallesProducto(
			List<TablaTablasDetalle> listTablaTablasDetallesProducto) {
		this.listTablaTablasDetallesProducto = listTablaTablasDetallesProducto;
	}

	public Emisor getEmisorSelec() {
		return emisorSelec;
	}

	public void setEmisorSelec(Emisor emisorSelec) {
		this.emisorSelec = emisorSelec;
	}

	public DomicilioFiscal getDomicilioFiscalSelec() {
		return domicilioFiscalSelec;
	}

	public void setDomicilioFiscalSelec(DomicilioFiscal domicilioFiscalSelec) {
		this.domicilioFiscalSelec = domicilioFiscalSelec;
	}

	public boolean isGenerarComprobante() {
		return generarComprobante;
	}

	public void setGenerarComprobante(boolean generarComprobante) {
		this.generarComprobante = generarComprobante;
	}	

	public boolean isAdicionar() {
		return adicionar;
	}

	public void setAdicionar(boolean adicionar) {
		this.adicionar = adicionar;
	}

	public boolean isIngresarProveedor() {
		return ingresarProveedor;
	}

	public void setIngresarProveedor(boolean ingresarProveedor) {
		this.ingresarProveedor = ingresarProveedor;
	}

	public List<Cliente> getListaClientesFilter() {
		return listaClientesFilter;
	}

	public void setListaClientesFilter(List<Cliente> listaClientesFilter) {
		this.listaClientesFilter = listaClientesFilter;
	}

	public List<ModoPago> getListModoPagos() {
		return listModoPagos;
	}

	public void setListModoPagos(List<ModoPago> listModoPagos) {
		this.listModoPagos = listModoPagos;
	}

	public Integer getId_modo_pago() {
		return id_modo_pago;
	}

	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public ComprobanteCompraDetalle getComprobanteCompraDetalleSelec() {
		return comprobanteCompraDetalleSelec;
	}

	public void setComprobanteCompraDetalleSelec(
			ComprobanteCompraDetalle comprobanteCompraDetalleSelec) {
		this.comprobanteCompraDetalleSelec = comprobanteCompraDetalleSelec;
	}

	public ComprobanteCompraService getComprobanteCompraService() {
		return comprobanteCompraService;
	}

	public void setComprobanteCompraService(ComprobanteCompraService comprobanteCompraService) {
		this.comprobanteCompraService = comprobanteCompraService;
	}

	public ProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public ModoPagoService getModoPagoService() {
		return modoPagoService;
	}

	public void setModoPagoService(ModoPagoService modoPagoService) {
		this.modoPagoService = modoPagoService;
	}

	public Producto getProductoEncontrado() {
		return productoEncontrado;
	}

	public void setProductoEncontrado(Producto productoEncontrado) {
		this.productoEncontrado = productoEncontrado;
	}

	public DomicilioFiscalService getDomicilioFiscalService() {
		return domicilioFiscalService;
	}

	public void setDomicilioFiscalService(DomicilioFiscalService domicilioFiscalService) {
		this.domicilioFiscalService = domicilioFiscalService;
	}

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TributoProducto> getListTributoProductos() {
		return listTributoProductos;
	}

	public void setListTributoProductos(List<TributoProducto> listTributoProductos) {
		this.listTributoProductos = listTributoProductos;
	}

	public TributoProductoService getTributoProductoService() {
		return tributoProductoService;
	}

	public void setTributoProductoService(TributoProductoService tributoProductoService) {
		this.tributoProductoService = tributoProductoService;
	}

	public List<ComprobanteCompraDetalle> getListaComprobanteCompraDetalle() {
		return listaComprobanteCompraDetalle;
	}

	public void setListaComprobanteCompraDetalle(List<ComprobanteCompraDetalle> listaComprobanteCompraDetalle) {
		this.listaComprobanteCompraDetalle = listaComprobanteCompraDetalle;
	}

	public Producto getProductoSelec() {
		return productoSelec;
	}

	public void setProductoSelec(Producto productoSelec) {
		this.productoSelec = productoSelec;
	}

	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public ComprobanteCompraDetalleService getComprobanteCompraDetalleService() {
		return comprobanteCompraDetalleService;
	}

	public void setComprobanteCompraDetalleService(ComprobanteCompraDetalleService comprobanteCompraDetalleService) {
		this.comprobanteCompraDetalleService = comprobanteCompraDetalleService;
	}

	public EmisorService getEmisorService() {
		return emisorService;
	}

	public void setEmisorService(EmisorService emisorService) {
		this.emisorService = emisorService;
	}

	public String getNroserie_documento() {
		return nroserie_documento;
	}

	public void setNroserie_documento(String nroserie_documento) {
		this.nroserie_documento = nroserie_documento;
	}

	public MovimientoProveedores getMovimientoProveedores() {
		return movimientoProveedores;
	}

	public void setMovimientoProveedores(MovimientoProveedores movimientoProveedores) {
		this.movimientoProveedores = movimientoProveedores;
	}

	public MovimientoProveedorService getMovimientoProveedorService() {
		return movimientoProveedorService;
	}

	public void setMovimientoProveedorService(MovimientoProveedorService movimientoProveedorService) {
		this.movimientoProveedorService = movimientoProveedorService;
	}

	public Boolean getEstadoEditarProducto() {
		return estadoEditarProducto;
	}

	public void setEstadoEditarProducto(Boolean estadoEditarProducto) {
		this.estadoEditarProducto = estadoEditarProducto;
	}

	public int getPosicionEdicion() {
		return posicionEdicion;
	}

	public void setPosicionEdicion(int posicionEdicion) {
		this.posicionEdicion = posicionEdicion;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
}
