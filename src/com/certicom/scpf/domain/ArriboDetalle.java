package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

/*Siemco v2.0*/
public class ArriboDetalle {
	
	  private Integer id_arribo;
	  private Integer id_comprobante_compra;
	  private Integer id_proveedor;
	  private String tipo_comprobante ;
	  private Integer id_producto ;
	  private Integer id_almacen ;
	  private Integer id_emisor ;
	  private Integer id_domicilio_fiscal; 
	  private Integer id_tipo_producto; 
	  private Date fecha_movimiento ;
	  private BigDecimal cantidad_arribo ;
	  private String documento_arribo; 
	  private String numero_documento_arribo ;
	  
	  
	  
	public Integer getId_arribo() {
		return id_arribo;
	}
	public void setId_arribo(Integer id_arribo) {
		this.id_arribo = id_arribo;
	}
	public Integer getId_comprobante_compra() {
		return id_comprobante_compra;
	}
	public void setId_comprobante_compra(Integer id_comprobante_compra) {
		this.id_comprobante_compra = id_comprobante_compra;
	}
	public Integer getId_proveedor() {
		return id_proveedor;
	}
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
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
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	public String getTipo_comprobante() {
		return tipo_comprobante;
	}
	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public BigDecimal getCantidad_arribo() {
		return cantidad_arribo;
	}
	public void setCantidad_arribo(BigDecimal cantidad_arribo) {
		this.cantidad_arribo = cantidad_arribo;
	}
	public String getDocumento_arribo() {
		return documento_arribo;
	}
	public void setDocumento_arribo(String documento_arribo) {
		this.documento_arribo = documento_arribo;
	}
	public String getNumero_documento_arribo() {
		return numero_documento_arribo;
	}
	public void setNumero_documento_arribo(String numero_documento_arribo) {
		this.numero_documento_arribo = numero_documento_arribo;
	}
	  
	  
	  

}
