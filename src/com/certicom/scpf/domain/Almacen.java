package com.certicom.scpf.domain;

/*Siemco v2.0*/
public class Almacen {

	private Integer id_almacen;
	private String descripcion_almacen;
	private String direccion_almacen;
	private Boolean estado_almacen;
	
	private Integer id_domicilio_fiscal; 
	private Integer id_emisor;
	
	public Integer getId_almacen() {
		return id_almacen;
	}
	
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}

	public String getDescripcion_almacen() {
		return descripcion_almacen;
	}

	public void setDescripcion_almacen(String descripcion_almacen) {
		this.descripcion_almacen = descripcion_almacen;
	}

	public String getDireccion_almacen() {
		return direccion_almacen;
	}

	public void setDireccion_almacen(String direccion_almacen) {
		this.direccion_almacen = direccion_almacen;
	}

    

	public Integer getId_domicilio_fiscal() {
		return id_domicilio_fiscal;
	}

	public void setId_domicilio_fiscal(Integer id_domicilio_fiscal) {
		this.id_domicilio_fiscal = id_domicilio_fiscal;
	}

	public Integer getId_emisor() {
		return id_emisor;
	}

	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}

	public Boolean getEstado_almacen() {
		return estado_almacen;
	}

	public void setEstado_almacen(Boolean estado_almacen) {
		this.estado_almacen = estado_almacen;
	}
	
    
	
}
