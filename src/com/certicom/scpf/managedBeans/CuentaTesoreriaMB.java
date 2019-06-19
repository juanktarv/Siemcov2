package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.CuentaTesoreria;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.MovimientoCuentaTesoreria;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.domain.Vendedor;
import com.certicom.scpf.services.CuentaTesoreriaService;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="cuentaTesoreriaMB")
@ViewScoped
public class CuentaTesoreriaMB extends GenericBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CuentaTesoreria cuentaTesoreriaSelec;
	private List<CuentaTesoreria> listaCuentaTesoreria;
	private Boolean editarCuentaTesoreria;
	private MenuServices menuServices;
	private CuentaTesoreriaService cuentaTesoreriaService;
	private List<TablaTablasDetalle> listaBanco;
	private List<TablaTablasDetalle> listaTipoMoneda;
	private List<TablaTablasDetalle> listaTipoCuenta;
	private TablaTablasDetalleService tablaTablasDetalleService;
	
	private List<MovimientoCuentaTesoreria> listaMovimientoCuenta;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public CuentaTesoreriaMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.cuentaTesoreriaSelec = new CuentaTesoreria();
		this.cuentaTesoreriaService = new CuentaTesoreriaService();
		this.menuServices=new MenuServices();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.listaMovimientoCuenta= new ArrayList<MovimientoCuentaTesoreria>();
		
		this.editarCuentaTesoreria = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaCuentaTesoreria = this.cuentaTesoreriaService.findAll();
			this.listaBanco = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_BANCO);
			this.listaTipoCuenta=this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_CUENTA);
			this.listaTipoMoneda=this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPO_MONEDA);		
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:cuenta");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public void verMovimientos(CuentaTesoreria p){		
//		this.listaMovimientoCuenta=this.
	}
	
	/* para tabla maestra */
	
	public void guardarCuenta(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarCuentaTesoreria) {
				this.cuentaTesoreriaService.actualizarCuentaTesoreria(this.cuentaTesoreriaSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza la cuenta : " + this.cuentaTesoreriaSelec.getCuenta());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Descripcion de la cuenta ha sido actualizado", 3);
			}else{
				this.cuentaTesoreriaService.crearCuentaTesoreria(this.cuentaTesoreriaSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta vendedor: " + this.cuentaTesoreriaSelec.getCuenta());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("La Cuenta ha sido creada", 3);
			}
			
			this.cuentaTesoreriaSelec = new CuentaTesoreria();
			this.editarCuentaTesoreria = Boolean.FALSE;
			
			this.listaCuentaTesoreria = this.cuentaTesoreriaService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoVendedor(){
		this.cuentaTesoreriaSelec = new CuentaTesoreria();
		this.editarCuentaTesoreria = Boolean.FALSE;
	}

	public void editarCuentaTesoreria(CuentaTesoreria cuentaTesoreria){
		this.cuentaTesoreriaSelec = cuentaTesoreria;
		this.editarCuentaTesoreria = Boolean.TRUE;
	}
	
	public void eliminarCuenta(CuentaTesoreria cuentaTesoreria){
		this.cuentaTesoreriaSelec = cuentaTesoreria;
	}
	
	
	public void confirmaEliminarCuenta(){
		try {
		
			this.cuentaTesoreriaService.eliminarCuentaTesoreria(this.cuentaTesoreriaSelec.getId_cuenta_tesoreria());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la cuenta: " + this.cuentaTesoreriaSelec.getCuenta());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("La Cuenta ha sido eliminada", 3);
			
			this.listaCuentaTesoreria = this.cuentaTesoreriaService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public CuentaTesoreria getCuentaTesoreriaSelec() {
		return cuentaTesoreriaSelec;
	}

	public void setCuentaTesoreriaSelec(CuentaTesoreria cuentaTesoreriaSelec) {
		this.cuentaTesoreriaSelec = cuentaTesoreriaSelec;
	}

	public List<CuentaTesoreria> getListaCuentaTesoreria() {
		return listaCuentaTesoreria;
	}

	public void setListaCuentaTesoreria(List<CuentaTesoreria> listaCuentaTesoreria) {
		this.listaCuentaTesoreria = listaCuentaTesoreria;
	}

	public Boolean getEditarCuentaTesoreria() {
		return editarCuentaTesoreria;
	}

	public void setEditarCuentaTesoreria(Boolean editarCuentaTesoreria) {
		this.editarCuentaTesoreria = editarCuentaTesoreria;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public CuentaTesoreriaService getCuentaTesoreriaService() {
		return cuentaTesoreriaService;
	}

	public void setCuentaTesoreriaService(CuentaTesoreriaService cuentaTesoreriaService) {
		this.cuentaTesoreriaService = cuentaTesoreriaService;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TablaTablasDetalle> getListaBanco() {
		return listaBanco;
	}

	public void setListaBanco(List<TablaTablasDetalle> listaBanco) {
		this.listaBanco = listaBanco;
	}

	public TablaTablasDetalleService getTablaTablasDetalleService() {
		return tablaTablasDetalleService;
	}

	public void setTablaTablasDetalleService(TablaTablasDetalleService tablaTablasDetalleService) {
		this.tablaTablasDetalleService = tablaTablasDetalleService;
	}

	public List<TablaTablasDetalle> getListaTipoMoneda() {
		return listaTipoMoneda;
	}

	public void setListaTipoMoneda(List<TablaTablasDetalle> listaTipoMoneda) {
		this.listaTipoMoneda = listaTipoMoneda;
	}

	public List<TablaTablasDetalle> getListaTipoCuenta() {
		return listaTipoCuenta;
	}

	public void setListaTipoCuenta(List<TablaTablasDetalle> listaTipoCuenta) {
		this.listaTipoCuenta = listaTipoCuenta;
	}
	
}
