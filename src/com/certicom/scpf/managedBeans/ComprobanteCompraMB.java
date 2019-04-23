package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.ComprobanteCompra;
import com.certicom.scpf.domain.DomicilioFiscal;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.ModoPago;
import com.certicom.scpf.domain.Proveedores;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.ComprobanteCompraService;
import com.certicom.scpf.services.DomicilioFiscalService;
//import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ModoPagoService;
import com.certicom.scpf.services.ProveedorService;
import com.certicom.scpf.services.TablaTablasDetalleService;
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
	private List<ComprobanteCompra> listaComprobanteCompra;
	private Boolean editarCliente;
	private MenuServices menuServices;
	private ComprobanteCompraService comprobanteCompraService;
	private ProveedorService proveedorService;
	private ModoPagoService modoPagoService;
	private List<ModoPago> listModoPagos;
	
	private TablaTablasDetalle tablaTablasDetalleTipoComprobante;
	
	private Proveedores proveedorEncontrado;
	
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private List<TablaTablasDetalle> listTablaTablasDetallesOperacion;
	private List<TablaTablasDetalle> listTablaTablasDetallesMoneda;
	private List<TablaTablasDetalle> listTablaTablasDetallesProducto;
	private Emisor emisorSelec;
	private DomicilioFiscal domicilioFiscalSelec;
	private DomicilioFiscalService domicilioFiscalService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	//private EmisorService emisorService;
	
	private boolean adicionar; /*Jesus*/
    private boolean generarComprobante; /*Jesus*/
    private boolean ingresarProveedor; /*Jesus*/
	
	private List<Cliente>listaClientesFilter;
	
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
		this.menuServices=new MenuServices();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		
		this.editarCliente = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listTablaTablasDetallesOperacion = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_OPERACION);
			this.listTablaTablasDetallesMoneda = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_DE_MONEDA);
			this.listTablaTablasDetallesProducto = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_PRODUCTO);
			this.listaComprobanteCompra = this.comprobanteCompraService.findAll();
			this.listModoPagos = this.modoPagoService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:comprobanteCompra");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public List<Proveedores> consultarProveedor(String query) throws Exception {
		System.out.println("entrando autocomplete");
        List<Proveedores> allProveedores = this.proveedorService.findAll();
        List<Proveedores> filteredProveedores = new ArrayList<Proveedores>();
         
        for (int i = 0; i < allProveedores.size(); i++) {
        	Proveedores skin = allProveedores.get(i);
            if(skin.getNombre_proveedor().toLowerCase().startsWith(query.toLowerCase())) {
            	filteredProveedores.add(skin);
            }
        }
        
        System.out.println("Cantidad: "+filteredProveedores.size());
         
        return filteredProveedores;
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
		
		System.out.println("Estoy aqui");
		
		String sTexto = "";
		
		System.out.println(sTexto);
		
		String sValor = "";
		
		for (TablaTablasDetalle tablaTablasDetalle : listTablaTablasDetallesComprobante) {
			System.out.println("codigo_catalogo : "+tablaTablasDetalle.getCodigo_catalogo());
			System.out.println("tipo_comprobante "+this.comprobanteCompraSelec.getTipo_comprobante());
			if(tablaTablasDetalle.getCodigo_catalogo().equals(this.comprobanteCompraSelec.getTipo_comprobante())){
				sTexto = tablaTablasDetalle.getDescripcion_largo();
				sValor = tablaTablasDetalle.getCodigo_catalogo();
				this.tablaTablasDetalleTipoComprobante = tablaTablasDetalle;
			}
			
		}
		System.out.println("sTexto " + sTexto);
		System.out.println("sValor " + sValor);
		
		StringTokenizer stPalabras = new StringTokenizer(sTexto);
		
		
		
		
		String sPalabra = "";
		switch(sValor){
		case "01": sPalabra="F";
		break;
		case "03": sPalabra="B";
			break;
		case "07": sPalabra="F";
			break;
		case "08": sPalabra="F";
			break;
		
		}
	
				
		int cantComprobante = 8;
		
		String com = "";
		
		Integer valor = Integer.parseInt(sValor);
		valor = valor + 1;
		
//		String sValue = String.valueOf(valor);
		String sValue = String.valueOf(this.comprobanteCompraService.getCorrelativoComprobante(this.comprobanteCompraSelec.getTipo_comprobante()));
		System.out.println("sValue ===> "+sValue);
		for (int i = 0; i < (cantComprobante - sValue.length()); i++) {
			
			com = com + "0";
			
		}	
		
		com = com + sValue;
		
		this.comprobanteCompraSelec.setNroserie_documento(sPalabra+"-"+com);
		
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
	
}
