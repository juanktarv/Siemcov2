package com.certicom.scpf.domain;

public class TipoProducto {

	private Integer id_tipo_producto;
	private String descripcion;
	private String estado;
	
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}	
	
}
