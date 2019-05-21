package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;


/*Siemco v2.0*/
public class AlmacenTransferencia {
	
	   
	private Integer id_almacen_transferencia;
	private Integer id_almacen_origen;
	private Integer id_almacen_destino;
	private Integer id_producto;
	private Integer id_emisor;
	private Integer id_domicilio_fiscal; 
	private Date fecha_transferencia;
	private Integer id_tipo_producto;
	private BigDecimal cantidad_transferencia;
	private String documento_transferencia;
	private String numero_documento_transferencia;
	
	private String descripcionAlmacenOrigen;
	private String descripcionAlmacenDestino;
	private String descripcionProducto;
	
	
	public Integer getId_almacen_origen() {
		return id_almacen_origen;
	}
	public void setId_almacen_origen(Integer id_almacen_origen) {
		this.id_almacen_origen = id_almacen_origen;
	}
	public Integer getId_almacen_destino() {
		return id_almacen_destino;
	}
	public void setId_almacen_destino(Integer id_almacen_destino) {
		this.id_almacen_destino = id_almacen_destino;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
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
	public Date getFecha_transferencia() {
		return fecha_transferencia;
	}
	public void setFecha_transferencia(Date fecha_transferencia) {
		this.fecha_transferencia = fecha_transferencia;
	}
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	public BigDecimal getCantidad_transferencia() {
		return cantidad_transferencia;
	}
	public void setCantidad_transferencia(BigDecimal cantidad_transferencia) {
		this.cantidad_transferencia = cantidad_transferencia;
	}
	public String getDocumento_transferencia() {
		return documento_transferencia;
	}
	public void setDocumento_transferencia(String documento_transferencia) {
		this.documento_transferencia = documento_transferencia;
	}
	public String getNumero_documento_transferencia() {
		return numero_documento_transferencia;
	}
	public void setNumero_documento_transferencia(String numero_documento_transferencia) {
		this.numero_documento_transferencia = numero_documento_transferencia;
	}
	public String getDescripcionAlmacenOrigen() {
		return descripcionAlmacenOrigen;
	}
	public void setDescripcionAlmacenOrigen(String descripcionAlmacenOrigen) {
		this.descripcionAlmacenOrigen = descripcionAlmacenOrigen;
	}
	public String getDescripcionAlmacenDestino() {
		return descripcionAlmacenDestino;
	}
	public void setDescripcionAlmacenDestino(String descripcionAlmacenDestino) {
		this.descripcionAlmacenDestino = descripcionAlmacenDestino;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public Integer getId_almacen_transferencia() {
		return id_almacen_transferencia;
	}
	public void setId_almacen_transferencia(Integer id_almacen_transferencia) {
		this.id_almacen_transferencia = id_almacen_transferencia;
	}
		
     
	


}
