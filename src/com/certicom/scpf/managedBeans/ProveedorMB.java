package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.domain.Proveedores;
import com.certicom.scpf.domain.TablaTablasDetalle;
import com.certicom.scpf.services.MenuServices;
import com.certicom.scpf.services.ProveedorService;
import com.certicom.scpf.services.TablaTablasDetalleService;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

@ManagedBean(name="proveedorMB")
@ViewScoped
public class ProveedorMB extends GenericBeans implements Serializable{

	private Proveedores proveedorSelec;
	private List<Proveedores> listaProveedores;
	private Boolean editarProveedor;
	private MenuServices menuServices;
	private ProveedorService proveedorService;
	private TablaTablasDetalleService tablaTablasDetalleService;
	private List<TablaTablasDetalle> listTablaTablasDetalles;
	
	private List<Proveedores>listaProveedoresFilter;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ProveedorMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.proveedorSelec = new Proveedores();
		this.proveedorService = new ProveedorService();
		this.tablaTablasDetalleService = new TablaTablasDetalleService();
		this.menuServices=new MenuServices();
		
		this.editarProveedor = Boolean.FALSE;

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaProveedores = this.proveedorService.findAll();
			this.listTablaTablasDetalles = this.tablaTablasDetalleService.findByIdMaestra(Constante.COD_TIPOS_DOCUMENTOS_IDENTIDAD);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:proveedor");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}		
	
	/* para tabla maestra */
	
	public void guardarProveedor(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarProveedor) {
				this.proveedorService.actualizarProveedor(this.proveedorSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el proveedor : " + this.proveedorSelec.getNombre_proveedor());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("El cliente ha sido actualizado", 3);
			}else{
				this.proveedorService.crearProveedor(this.proveedorSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el proveedor : " + this.proveedorSelec.getNombre_proveedor());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Cliente ha sido creado", 3);
			}
			
			this.proveedorSelec = new Proveedores();
			this.editarProveedor = Boolean.FALSE;
			
			this.listaProveedores = this.proveedorService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoProveedor(){
		this.proveedorSelec = new Proveedores();
		this.editarProveedor = Boolean.FALSE;
	}

	public void editarProveedor(Proveedores proveedor){
		this.proveedorSelec = proveedor;
		this.editarProveedor = Boolean.TRUE;
	}
	
	public void eliminarProveedor(Proveedores proveedor){
		this.proveedorSelec = proveedor;
	}
	
	
	public void confirmaEliminarProveedor(){
		try {
		
			this.proveedorService.eliminarProveedor(this.proveedorSelec.getId_proveedor());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina el cliente: " + this.proveedorSelec.getNombre_proveedor());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Cliente ha sido eliminado", 3);
			
			this.listaProveedores = this.proveedorService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

	

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	

	public Proveedores getProveedorSelec() {
		return proveedorSelec;
	}

	public void setProveedorSelec(Proveedores proveedorSelec) {
		this.proveedorSelec = proveedorSelec;
	}

	public List<Proveedores> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedores> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public Boolean getEditarProveedor() {
		return editarProveedor;
	}

	public void setEditarProveedor(Boolean editarProveedor) {
		this.editarProveedor = editarProveedor;
	}

	public List<Proveedores> getListaProveedoresFilter() {
		return listaProveedoresFilter;
	}

	public void setListaProveedoresFilter(List<Proveedores> listaProveedoresFilter) {
		this.listaProveedoresFilter = listaProveedoresFilter;
	}

	public List<TablaTablasDetalle> getListTablaTablasDetalles() {
		return listTablaTablasDetalles;
	}

	public void setListTablaTablasDetalles(
			List<TablaTablasDetalle> listTablaTablasDetalles) {
		this.listTablaTablasDetalles = listTablaTablasDetalles;
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

	
}
