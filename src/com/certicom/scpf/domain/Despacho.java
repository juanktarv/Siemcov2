package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

/*Siemco v2.0*/
public class Despacho {
	
	  private Integer id_despacho;
	  private Integer id_producto;
	  private Date fecha_movimiento;
	  private BigDecimal cantidad_total_despacho;
	  
	  
	public Integer getId_despacho() {
		return id_despacho;
	}
	public void setId_despacho(Integer id_despacho) {
		this.id_despacho = id_despacho;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public BigDecimal getCantidad_total_despacho() {
		return cantidad_total_despacho;
	}
	public void setCantidad_total_despacho(BigDecimal cantidad_total_despacho) {
		this.cantidad_total_despacho = cantidad_total_despacho;
	}
	  
	  

}
