package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class ComprobanteCompraDetalle {

	private Integer id_comprobante_compra;
	private Integer id_proveedor;
	private Integer id_producto;
	private Integer tipo_comprobante;
	private Integer id_tipo_producto;
	private Integer id_almacen;
	private Integer tipo_unidad_medida;
	private Integer cantidad_unidades_item;
	private BigDecimal suma_tributos;
	private BigDecimal descuento;
	private BigDecimal monto_tributo_igv;
	private BigDecimal base_imponible_igv;
	private BigDecimal monto_tributo_isc;
	private BigDecimal base_imponible_isc;
	private BigDecimal monto_tributo_otros;
	private BigDecimal base_imponible_otros;
	private BigDecimal precio_venta_unitario_costo;
	private BigDecimal valor_venta_item;
	private BigDecimal valor_referencial_unit;
	
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
	
	public Integer getTipo_comprobante() {
		return tipo_comprobante;
	}
	
	public void setTipo_comprobante(Integer tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	
	public Integer getId_almacen() {
		return id_almacen;
	}
	
	public void setId_almacen(Integer id_almacen) {
		this.id_almacen = id_almacen;
	}
	
	public Integer getTipo_unidad_medida() {
		return tipo_unidad_medida;
	}
	
	public void setTipo_unidad_medida(Integer tipo_unidad_medida) {
		this.tipo_unidad_medida = tipo_unidad_medida;
	}
	
	public Integer getCantidad_unidades_item() {
		return cantidad_unidades_item;
	}
	
	public void setCantidad_unidades_item(Integer cantidad_unidades_item) {
		this.cantidad_unidades_item = cantidad_unidades_item;
	}
	
	public BigDecimal getSuma_tributos() {
		return suma_tributos;
	}
	
	public void setSuma_tributos(BigDecimal suma_tributos) {
		this.suma_tributos = suma_tributos;
	}
	
	public BigDecimal getDescuento() {
		return descuento;
	}
	
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	
	public BigDecimal getMonto_tributo_igv() {
		return monto_tributo_igv;
	}
	
	public void setMonto_tributo_igv(BigDecimal monto_tributo_igv) {
		this.monto_tributo_igv = monto_tributo_igv;
	}
	
	public BigDecimal getBase_imponible_igv() {
		return base_imponible_igv;
	}
	
	public void setBase_imponible_igv(BigDecimal base_imponible_igv) {
		this.base_imponible_igv = base_imponible_igv;
	}
	
	public BigDecimal getMonto_tributo_isc() {
		return monto_tributo_isc;
	}
	
	public void setMonto_tributo_isc(BigDecimal monto_tributo_isc) {
		this.monto_tributo_isc = monto_tributo_isc;
	}
	
	public BigDecimal getBase_imponible_isc() {
		return base_imponible_isc;
	}
	
	public void setBase_imponible_isc(BigDecimal base_imponible_isc) {
		this.base_imponible_isc = base_imponible_isc;
	}
	
	public BigDecimal getMonto_tributo_otros() {
		return monto_tributo_otros;
	}
	
	public void setMonto_tributo_otros(BigDecimal monto_tributo_otros) {
		this.monto_tributo_otros = monto_tributo_otros;
	}
	
	public BigDecimal getBase_imponible_otros() {
		return base_imponible_otros;
	}
	
	public void setBase_imponible_otros(BigDecimal base_imponible_otros) {
		this.base_imponible_otros = base_imponible_otros;
	}
	
	public BigDecimal getPrecio_venta_unitario_costo() {
		return precio_venta_unitario_costo;
	}
	
	public void setPrecio_venta_unitario_costo(
			BigDecimal precio_venta_unitario_costo) {
		this.precio_venta_unitario_costo = precio_venta_unitario_costo;
	}
	
	public BigDecimal getValor_venta_item() {
		return valor_venta_item;
	}
	
	public void setValor_venta_item(BigDecimal valor_venta_item) {
		this.valor_venta_item = valor_venta_item;
	}
	
	public BigDecimal getValor_referencial_unit() {
		return valor_referencial_unit;
	}
	
	public void setValor_referencial_unit(BigDecimal valor_referencial_unit) {
		this.valor_referencial_unit = valor_referencial_unit;
	}	
	
}
