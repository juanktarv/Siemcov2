package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;


/*Siemco v2.0*/
public class Arribo {
	
	
	  private Integer id_arribo;
	  private Integer id_producto;
	  private Date fecha_movimiento;
	  private BigDecimal cantidad_total_arribo;
	  
	  
	public Integer getId_arribo() {
		return id_arribo;
	}
	public void setId_arribo(Integer id_arribo) {
		this.id_arribo = id_arribo;
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
	public BigDecimal getCantidad_total_arribo() {
		return cantidad_total_arribo;
	}
	public void setCantidad_total_arribo(BigDecimal cantidad_total_arribo) {
		this.cantidad_total_arribo = cantidad_total_arribo;
	}
	  
	  



}
