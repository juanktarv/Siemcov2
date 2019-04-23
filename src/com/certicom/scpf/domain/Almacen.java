package com.certicom.scpf.domain;

public class Almacen {

	private Integer id_almacen;
	private String nombre_almacen;
	private String direccion;
	private String estado;
	
	public Integer getId_almacen() {
		return id_almacen;
	}
	
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}
	
	public String getNombre_almacen() {
		return nombre_almacen;
	}
	
	public void setNombre_almacen(String nombre_almacen) {
		this.nombre_almacen = nombre_almacen;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	
}
