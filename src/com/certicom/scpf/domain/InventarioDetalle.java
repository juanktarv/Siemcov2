package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

/*Siemco v2.0*/
public class InventarioDetalle {
	
      private Integer id_producto;
      private Integer id_almacen;
      private Integer id_emisor;
      private Integer id_domicilio_fiscal;
      private Integer id_despacho;
      private Integer id_arribo;
      private Integer id_tipo_producto;
      private Date fecha_movimiento;
      private BigDecimal cantidad_ingresa;
      private BigDecimal cantidad_salida;
      private String tipo_movimiento;
      private Integer id_almacen_transferencia;
      
      
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public Integer getId_almacen() {
		return id_almacen;
	}
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
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
	public Integer getId_despacho() {
		return id_despacho;
	}
	public void setId_despacho(Integer id_despacho) {
		this.id_despacho = id_despacho;
	}
	public Integer getId_arribo() {
		return id_arribo;
	}
	public void setId_arribo(Integer id_arribo) {
		this.id_arribo = id_arribo;
	}
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public BigDecimal getCantidad_ingresa() {
		return cantidad_ingresa;
	}
	public void setCantidad_ingresa(BigDecimal cantidad_ingresa) {
		this.cantidad_ingresa = cantidad_ingresa;
	}
	public BigDecimal getCantidad_salida() {
		return cantidad_salida;
	}
	public void setCantidad_salida(BigDecimal cantidad_salida) {
		this.cantidad_salida = cantidad_salida;
	}
	public String getTipo_movimiento() {
		return tipo_movimiento;
	}
	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	public Integer getId_almacen_transferencia() {
		return id_almacen_transferencia;
	}
	public void setId_almacen_transferencia(Integer id_almacen_transferencia) {
		this.id_almacen_transferencia = id_almacen_transferencia;
	}
      
      
      

}
