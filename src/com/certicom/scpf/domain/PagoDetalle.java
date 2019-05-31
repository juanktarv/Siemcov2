package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PagoDetalle {

	private Integer id_proveedor;
	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_emisor;
	private Integer id_comprobante_compra;
	private Integer tipo_comprobante;
	private BigDecimal pago;
	private BigDecimal importe_pagado;
	private BigDecimal importe_pendiente;
	private ComprobanteCompra comprobanteCompra;
	private Integer id_pago;
	private Date fecha_pago;
	
	public Integer getId_proveedor() {
		return id_proveedor;
	}
	
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	
	public Integer getId_cuenta_tesoreria() {
		return id_cuenta_tesoreria;
	}
	
	public void setId_cuenta_tesoreria(Integer id_cuenta_tesoreria) {
		this.id_cuenta_tesoreria = id_cuenta_tesoreria;
	}
	
	public Integer getId_domicilio_fiscal_cab() {
		return id_domicilio_fiscal_cab;
	}
	
	public void setId_domicilio_fiscal_cab(Integer id_domicilio_fiscal_cab) {
		this.id_domicilio_fiscal_cab = id_domicilio_fiscal_cab;
	}
	
	public Integer getId_emisor() {
		return id_emisor;
	}
	
	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}
	
	public Integer getId_comprobante_compra() {
		return id_comprobante_compra;
	}
	
	public void setId_comprobante_compra(Integer id_comprobante_compra) {
		this.id_comprobante_compra = id_comprobante_compra;
	}
	
	public Integer getTipo_comprobante() {
		return tipo_comprobante;
	}
	
	public void setTipo_comprobante(Integer tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	
	public BigDecimal getPago() {
		return pago;
	}
	
	public void setPago(BigDecimal pago) {
		this.pago = pago;
	}

	public BigDecimal getImporte_pagado() {
		return importe_pagado;
	}

	public void setImporte_pagado(BigDecimal importe_pagado) {
		this.importe_pagado = importe_pagado;
	}

	public BigDecimal getImporte_pendiente() {
		return importe_pendiente;
	}

	public void setImporte_pendiente(BigDecimal importe_pendiente) {
		this.importe_pendiente = importe_pendiente;
	}

	public ComprobanteCompra getComprobanteCompra() {
		return comprobanteCompra;
	}

	public void setComprobanteCompra(ComprobanteCompra comprobanteCompra) {
		this.comprobanteCompra = comprobanteCompra;
	}

	public Integer getId_pago() {
		return id_pago;
	}

	public void setId_pago(Integer id_pago) {
		this.id_pago = id_pago;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}	
	
}
