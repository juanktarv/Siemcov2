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
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.CobranzaCabecera;
import com.certicom.scpf.domain.CobranzaDetalle;
import com.certicom.scpf.domain.CuentaTesoreria;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.MovimientoClientes;
import com.certicom.scpf.domain.MovimientoCuentaTesoreria;
import com.certicom.scpf.domain.MovimientoProveedores;
import com.certicom.scpf.domain.PagoCabecera;
import com.certicom.scpf.domain.PagoDetalle;
import com.certicom.scpf.domain.Proveedores;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.CuentaTesoreriaService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MovimientoProveedorService;
import com.certicom.scpf.services.MovimientoTesoreriaService;
import com.certicom.scpf.services.PagoCabeceraService;
import com.certicom.scpf.services.PagoDetalleService;
import com.certicom.scpf.services.ProveedorService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.GenericBeans;

import src.com.certicom.scpf.utils.Utils;

@ManagedBean(name="pagosMB")
@ViewScoped
public class PagosMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mes;
	private String anio;
	private Boolean disableRespuesta; 
	private Boolean disableBuscar; 
	private String tipo_comprobante;
	private String nroserie_documento;
	
	private PagoCabecera pago;
	private List<CuentaTesoreria> listaCuentas;
	private CuentaTesoreriaService cuentaTesoreriaService;
	private CuentaTesoreria cuentaSelec;
	private List<PagoDetalle>listaDetallePago;
	
	private List<TablaTablasDetalle> listTablaTablasDetallesComprobante;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private Proveedores proveedorEncontrado;
	private List<Proveedores>listaproveedors;
	private ProveedorService proveedorService;
	private LazyDataModel<MovimientoProveedores> listamovimientos;
	private MovimientoProveedorService movimientoProveedorService;
	private List<MovimientoProveedores>listaSelectedMovimientos;
	private List<MovimientoProveedores> listaFiltroMovimientos;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	private Integer id_cuenta_tesoreria;
	private PagoCabeceraService pagoCabeceraService;
	private PagoDetalleService pagoDetalleService;
	private MovimientoTesoreriaService movimientoTesoreriaService;

	
	@PostConstruct
	public void inicia(){
		
		SimpleDateFormat sdfm= new SimpleDateFormat("MM");
		SimpleDateFormat sdfy= new SimpleDateFormat("yyyy");
		this.mes=sdfm.format(new Date());
		this.anio=sdfy.format(new Date());
		this.nroserie_documento="";
		this.disableBuscar = Boolean.TRUE; 
		this.disableRespuesta = Boolean.TRUE; 
		this.proveedorEncontrado=new Proveedores();
		this.pago= new PagoCabecera();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.proveedorService= new ProveedorService();
		this.cuentaSelec= new CuentaTesoreria();
		this.listaDetallePago= new ArrayList<>();
		this.emisorService = new EmisorService();
		this.cuentaTesoreriaService= new CuentaTesoreriaService();
		this.pagoCabeceraService= new PagoCabeceraService();
		this.movimientoProveedorService= new MovimientoProveedorService();
		this.pagoDetalleService= new PagoDetalleService();
		this.movimientoTesoreriaService=new MovimientoTesoreriaService();
		
		try {
			this.emisorSelec = this.emisorService.findAll().get(0);
			this.listTablaTablasDetallesComprobante = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS);
			this.listaproveedors= this.proveedorService.findAll();
			
			if(!this.listaproveedors.isEmpty()){
				this.proveedorEncontrado=this.listaproveedors.get(0);
			}
			
			this.id_cuenta_tesoreria=0;
			this.listaCuentas=this.cuentaTesoreriaService.findAll();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onItemProveedor()  throws Exception{		
		System.out.println("onItemCliente --->");				
		List<Proveedores> allProveedors = this.proveedorService.findAll();
		
		for (int i = 0; i < allProveedors.size(); i++) {
        	Proveedores skin = allProveedors.get(i);
            if(skin.getNombre_proveedor().equalsIgnoreCase(this.proveedorEncontrado.getNombre_proveedor())) {
            	this.proveedorEncontrado = skin;
            	break;
            }
        }		
			this.proveedorEncontrado=this.proveedorService.findById(this.proveedorEncontrado.getId_proveedor());
			System.out.println("this.proveedorEncontrado.getId_proveedor() --->"+this.proveedorEncontrado.getId_proveedor());
	}
	
	public void onItemDocumento()  throws Exception{
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.TRUE; 
		
	}
	
	public void listarMovimientosFiltros(){
		System.out.println(" listarComprobantesFiltros --->tipo_comprobante "+this.tipo_comprobante);
		
		 this.disableBuscar = Boolean.FALSE; 
		 this.disableRespuesta = Boolean.FALSE; 
		 
		 if(this.proveedorEncontrado!=null){
			 System.out.println(" listarMovimientosFiltros --->"+this.proveedorEncontrado.getId_proveedor());
		 }else{
			 this.proveedorEncontrado= new Proveedores();
		 }
		
		this.listamovimientos = new LazyDataModel<MovimientoProveedores>() {
			private static final long serialVersionUID = 1L;
			private List<MovimientoProveedores> datasource; 
			private Integer totalRow=0;
			
			
			@Override  
			public MovimientoProveedores getRowData(String rowKey){
				 for(MovimientoProveedores e : datasource) {  
                     if(e.getId_comprobante_compra().equals(rowKey))  
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
	            public Object getRowKey(MovimientoProveedores e) {  
	                return e.getId_comprobante_compra();  
	            }  
			 @Override  
	            public List<MovimientoProveedores> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {              
					try {
							filters.put("id_proveedor", proveedorEncontrado!=null? proveedorEncontrado.getId_proveedor():"");
							filters.put("nroserie_documento", nroserie_documento!=null? nroserie_documento :"");
							totalRow = movimientoProveedorService.countCompByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, filters);												
							
							System.out.println("TOTAL-->totalRow:"+totalRow);
							datasource = movimientoProveedorService.listComprobantesByAnioMesTipoPAGINATOR(Integer.parseInt(anio), Integer.parseInt(mes), tipo_comprobante, first, pageSize, filters, "m.nroserie_documento", "DESC");
						 
							System.out.println("TOTAL-->datasource:"+datasource.size());
							
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
	
	public void prepararPago(){
		this.pago= new PagoCabecera();
		this.cuentaSelec= new CuentaTesoreria();
		this.listaDetallePago= new ArrayList<>();
		if(this.proveedorEncontrado!=null){
			System.out.println("this.clienteEncontrado.getId_cliente()--------->"+this.proveedorEncontrado.getId_proveedor());
			this.pago.setId_proveedor(this.proveedorEncontrado.getId_proveedor());
			this.pago.setProveedor(this.proveedorEncontrado);
			this.pago.setFecha_pago(new Date());
			this.pago.setTotal_importe_pagado(new BigDecimal("0.00"));
			this.pago.setId_emisor(this.emisorSelec.getId_emisor());
			this.pago.setId_domicilio_fiscal_cab(this.emisorSelec.getId_domicilio_fiscal_cab());
			
			PagoDetalle detalle;
			System.out.println("this.listaSelectedMovimientos--->"+listaSelectedMovimientos.size());
			System.out.println("this.listaFiltroMovimientos--->"+listaFiltroMovimientos);
			
			for(MovimientoProveedores mov:listaSelectedMovimientos){
				detalle= new PagoDetalle();
				this.pago.setTotal_importe_pagado(this.pago.getTotal_importe_pagado().add(mov.getDeuda()));
				System.out.println("IMPORTE--->"+this.pago.getTotal_importe_pagado());
				detalle.setId_proveedor(mov.getId_proveedor());
				detalle.setId_comprobante_compra(mov.getId_comprobante_compra());
				detalle.setImporte_pagado(Utils.redondeoImporteTotal(mov.getDeuda(),2));
				detalle.setImporte_pendiente(Utils.redondeoImporteTotal(mov.getDeuda(), 2));
				detalle.setComprobanteCompra(mov.getComprobanteCompra());
				this.listaDetallePago.add(detalle);
			}
			
		}
		this.pago.setSaldo_deudor(Utils.redondeoImporteTotal(this.pago.getTotal_importe_pagado(), 2));
		this.pago.setSaldo_pagar(Utils.redondeoImporteTotal(this.pago.getTotal_importe_pagado(),2));
		this.cuentaSelec.setMontoIngresado(Utils.redondeoImporteTotal(this.pago.getTotal_importe_pagado(),2));
	
	}
	
	public void guardarPago(){
		System.out.println(" guardarPago ----------->"+this.id_cuenta_tesoreria);
		RequestContext context = RequestContext.getCurrentInstance(); 
		try {
			System.out.println("this.id_cuenta_tesoreria ---------->"+this.id_cuenta_tesoreria);
			this.cuentaSelec=this.cuentaTesoreriaService.findById(this.id_cuenta_tesoreria);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int id_pago=0;
		try {
			this.pago.setId_cuenta_tesoreria(this.cuentaSelec.getId_cuenta_tesoreria());
			id_pago=this.pagoCabeceraService.crearPagoCabecera(pago);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(PagoDetalle det: this.listaDetallePago){
			MovimientoProveedores mov= new MovimientoProveedores();
			mov.setComprobanteCompra(det.getComprobanteCompra());
			mov.setNroserie_documento(det.getComprobanteCompra().getNroserie_documento());
			mov.setPago(det.getImporte_pagado());
			mov.setId_comprobante_compra(det.getId_comprobante_compra());
			
			this.movimientoProveedorService.actualizarMovimiento(mov);			
			
			det.setId_pago(id_pago);
			det.setId_emisor(this.emisorSelec.getId_emisor());
			det.setId_domicilio_fiscal_cab(this.emisorSelec.getId_domicilio_fiscal_cab());
			det.setId_cuenta_tesoreria(this.cuentaSelec.getId_cuenta_tesoreria());
			det.setFecha_pago(new Date());
			try {
				this.pagoDetalleService.crearCobranzaDetalle(det);
				
				MovimientoCuentaTesoreria movt= new MovimientoCuentaTesoreria();
				movt.setSalida(det.getImporte_pagado());
				movt.setFecha_movimiento(new Date());
				movt.setId_proveedor(det.getId_proveedor());
				movt.setId_cobranza(id_pago);
				movt.setId_cuenta_tesoreria(this.pago.getId_cuenta_tesoreria());
				movt.setId_domicilio_fiscal_cab(this.pago.getId_domicilio_fiscal_cab());
				movt.setId_emisor(this.pago.getId_emisor());
				movt.setTipomovimiento("PAGO");
				
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
	
	public void cancelarPago(){
		
		
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

	public Proveedores getProveedorEncontrado() {
		return proveedorEncontrado;
	}

	public void setProveedorEncontrado(Proveedores proveedorEncontrado) {
		this.proveedorEncontrado = proveedorEncontrado;
	}

	public List<Proveedores> getListaproveedors() {
		return listaproveedors;
	}

	public void setListaproveedors(List<Proveedores> listaproveedors) {
		this.listaproveedors = listaproveedors;
	}

	public ProveedorService getProveedorService() {
		return proveedorService;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNroserie_documento() {
		return nroserie_documento;
	}

	public void setNroserie_documento(String nroserie_documento) {
		this.nroserie_documento = nroserie_documento;
	}

	public PagoCabecera getPago() {
		return pago;
	}

	public void setPago(PagoCabecera pago) {
		this.pago = pago;
	}

	public CuentaTesoreria getCuentaSelec() {
		return cuentaSelec;
	}

	public void setCuentaSelec(CuentaTesoreria cuentaSelec) {
		this.cuentaSelec = cuentaSelec;
	}

	public List<PagoDetalle> getListaDetallePago() {
		return listaDetallePago;
	}

	public void setListaDetallePago(List<PagoDetalle> listaDetallePago) {
		this.listaDetallePago = listaDetallePago;
	}

	public LazyDataModel<MovimientoProveedores> getListamovimientos() {
		return listamovimientos;
	}

	public void setListamovimientos(LazyDataModel<MovimientoProveedores> listamovimientos) {
		this.listamovimientos = listamovimientos;
	}

	public MovimientoProveedorService getMovimientoProveedorService() {
		return movimientoProveedorService;
	}

	public void setMovimientoProveedorService(MovimientoProveedorService movimientoProveedorService) {
		this.movimientoProveedorService = movimientoProveedorService;
	}

	public List<MovimientoProveedores> getListaSelectedMovimientos() {
		return listaSelectedMovimientos;
	}

	public void setListaSelectedMovimientos(List<MovimientoProveedores> listaSelectedMovimientos) {
		this.listaSelectedMovimientos = listaSelectedMovimientos;
	}

	public List<MovimientoProveedores> getListaFiltroMovimientos() {
		return listaFiltroMovimientos;
	}

	public void setListaFiltroMovimientos(List<MovimientoProveedores> listaFiltroMovimientos) {
		this.listaFiltroMovimientos = listaFiltroMovimientos;
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

	public Integer getId_cuenta_tesoreria() {
		return id_cuenta_tesoreria;
	}

	public void setId_cuenta_tesoreria(Integer id_cuenta_tesoreria) {
		this.id_cuenta_tesoreria = id_cuenta_tesoreria;
	}

	public PagoCabeceraService getPagoCabeceraService() {
		return pagoCabeceraService;
	}

	public void setPagoCabeceraService(PagoCabeceraService pagoCabeceraService) {
		this.pagoCabeceraService = pagoCabeceraService;
	}

	public PagoDetalleService getPagoDetalleService() {
		return pagoDetalleService;
	}

	public void setPagoDetalleService(PagoDetalleService pagoDetalleService) {
		this.pagoDetalleService = pagoDetalleService;
	}

	public MovimientoTesoreriaService getMovimientoTesoreriaService() {
		return movimientoTesoreriaService;
	}

	public void setMovimientoTesoreriaService(MovimientoTesoreriaService movimientoTesoreriaService) {
		this.movimientoTesoreriaService = movimientoTesoreriaService;
	}
	
	
}
