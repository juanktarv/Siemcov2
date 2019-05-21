package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

/*Siemco v2.0*/
public class DespachoDetalle {
	
	 private Integer id_despacho; 
	 private Integer id_comprobante ;
	 private Integer id_cliente;
	 private String tipo_comprobante;
	 private Integer id_producto ;
	 private Integer id_almacen;
	 private Integer id_emisor ;
	 private Integer id_domicilio_fiscal; 
	 private Integer id_tipo_producto ;
	 private Integer id_modo_pago;
	 private Date fecha_movimiento; 
	 private BigDecimal cantidad_despacho; 
	 private String documento_despacho ;
	 private String numero_documento_despacho;
	 
	 
	public Integer getId_despacho() {
		return id_despacho;
	}
	public void setId_despacho(Integer id_despacho) {
		this.id_despacho = id_despacho;
	}
	public Integer getId_comprobante() {
		return id_comprobante;
	}
	public void setId_comprobante(Integer id_comprobante) {
		this.id_comprobante = id_comprobante;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getTipo_comprobante() {
		return tipo_comprobante;
	}
	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
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
	public Integer getId_modo_pago() {
		return id_modo_pago;
	}
	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public BigDecimal getCantidad_despacho() {
		return cantidad_despacho;
	}
	public void setCantidad_despacho(BigDecimal cantidad_despacho) {
		this.cantidad_despacho = cantidad_despacho;
	}
	public String getDocumento_despacho() {
		return documento_despacho;
	}
	public void setDocumento_despacho(String documento_despacho) {
		this.documento_despacho = documento_despacho;
	}
	public String getNumero_documento_despacho() {
		return numero_documento_despacho;
	}
	public void setNumero_documento_despacho(String numero_documento_despacho) {
		this.numero_documento_despacho = numero_documento_despacho;
	}
	 
	 
	 

}
