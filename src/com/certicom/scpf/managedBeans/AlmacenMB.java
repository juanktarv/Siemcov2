package com.certicom.scpf.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.scpf.domain.Almacen;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.Log;
import com.certicom.scpf.domain.Menu;
import com.certicom.scpf.services.AlmacenService;
import com.certicom.scpf.services.EmisorService;
import com.certicom.scpf.services.MenuServices;
import com.pe.certicom.scpf.commons.Constante;
import com.pe.certicom.scpf.commons.FacesUtils;
import com.pe.certicom.scpf.commons.GenericBeans;

/*Siemco v2.0*/


@ManagedBean(name="almacenMB")
@ViewScoped
public class AlmacenMB extends GenericBeans implements Serializable{
	
	private Almacen almacenSelec;
	private List<Almacen> listaAlmacen;
	private Boolean editarAlmacen;
	private MenuServices menuServices;
	private AlmacenService almacenService;
	private Emisor emisorSelec;
	private EmisorService emisorService;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public AlmacenMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.almacenSelec = new Almacen();
		this.almacenService = new AlmacenService();
		this.menuServices=new MenuServices();
		
		this.editarAlmacen = Boolean.FALSE;
		this.emisorService = new EmisorService();

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();	
		
		try {
			this.listaAlmacen = this.almacenService.findAll();
			this.emisorSelec = this.emisorService.findAll().get(0);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:almacen");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/* para tabla maestra */
	
	public void guardarAlmacen(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			if(this.editarAlmacen) {
				this.almacenService.actualizarAlmacen(this.almacenSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza la sucursal : " + this.almacenSelec.getDescripcion_almacen());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Almacen ha sido actualizado", 3);
			}else{
				this.almacenSelec.setId_emisor(this.emisorSelec.getId_emisor());
				this.almacenSelec.setId_domicilio_fiscal(this.emisorSelec.getId_domicilio_fiscal_cab());
				this.almacenService.crearAlmacen(this.almacenSelec);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta la sucursal : " + this.almacenSelec.getDescripcion_almacen());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Almacen ha sido creado", 3);
			}
			
			this.almacenSelec = new Almacen();
			this.editarAlmacen = Boolean.FALSE;
			
			this.listaAlmacen = this.almacenService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void nuevoAlmacen(){
		this.almacenSelec = new Almacen();
		this.editarAlmacen = Boolean.FALSE;
	}

	public void editarAlmacen(Almacen almacen){
		this.almacenSelec = almacen;
		this.editarAlmacen = Boolean.TRUE;
	}
	
	public void eliminarAlmacen(Almacen almacen){
		this.almacenSelec = almacen;
	}
	
	
	public void confirmaEliminarAlmacen(){
		try {
		
			this.almacenService.eliminarAlmacen(this.almacenSelec.getId_almacen());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina almacen: " + this.almacenSelec.getDescripcion_almacen());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Almacen ha sido eliminado", 3);
			
			this.listaAlmacen = this.almacenService.findAll();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/

	public Almacen getAlmacenSelec() {
		return almacenSelec;
	}

	public void setAlmacenSelec(Almacen almacenSelec) {
		this.almacenSelec = almacenSelec;
	}

	public List<Almacen> getListaAlmacen() {
		return listaAlmacen;
	}

	public void setListaAlmacen(List<Almacen> listaAlmacen) {
		this.listaAlmacen = listaAlmacen;
	}

	public Boolean getEditarAlmacen() {
		return editarAlmacen;
	}

	public void setEditarAlmacen(Boolean editarAlmacen) {
		this.editarAlmacen = editarAlmacen;
	}

	public MenuServices getMenuServices() {
		return menuServices;
	}

	public void setMenuServices(MenuServices menuServices) {
		this.menuServices = menuServices;
	}

	public AlmacenService getAlmacenService() {
		return almacenService;
	}

	public void setAlmacenService(AlmacenService almacenService) {
		this.almacenService = almacenService;
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
