package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class Inventario {
	
	private Integer id_almacen;
	private Integer id_producto;
	private Integer id_tipo_producto;
	private Integer stock;
	private BigDecimal costo_promedio;
	
	public Integer getId_almacen() {
		return id_almacen;
	}
	
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}
	
	public Integer getId_producto() {
		return id_producto;
	}
	
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public BigDecimal getCosto_promedio() {
		return costo_promedio;
	}
	
	public void setCosto_promedio(BigDecimal costo_promedio) {
		this.costo_promedio = costo_promedio;
	}	
	
}
