package com.certicom.scpf.domain;

import java.math.BigDecimal;

/*Siemco v2.0*/

public class Inventario {
	
	private Integer id_almacen;
	private Integer id_producto;
	private Integer id_emisor;
	private Integer id_domicilio_fiscal;
	private Integer id_tipo_producto;
	private BigDecimal cantidad; 
	private BigDecimal costo_promedio;
	
	private Producto producto;
	

	private String descripcionProducto; 
	private String desUnidadMedida; 
	private String descripcionAlmacen; 
	
	
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
	
	
	public BigDecimal getCosto_promedio() {
		return costo_promedio;
	}
	
	public void setCosto_promedio(BigDecimal costo_promedio) {
		this.costo_promedio = costo_promedio;
	}

	public Integer getId_emisor() {
		return id_emisor;
	}

	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}

	public Integer getId_domicilio_fiscal() {
		return id_domicilio_fiscal;
	}

	public void setId_domicilio_fiscal(Integer id_domicilio_fiscal) {
		this.id_domicilio_fiscal = id_domicilio_fiscal;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getDesUnidadMedida() {
		return desUnidadMedida;
	}

	public void setDesUnidadMedida(String desUnidadMedida) {
		this.desUnidadMedida = desUnidadMedida;
	}

	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}

	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}	
	
	
	
	
}
