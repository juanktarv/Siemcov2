package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CobranzaDetalle {

	private Integer id_comprobante;
	private Integer id_cliente;
	private Integer tipo_comprobante;
	private Integer id_modo_pago;
	private Integer id_emisor;
	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_cobranza;
	private BigDecimal importe_cobrado;
	private Date fecha_cobranza;
	private BigDecimal importe_pendiente;
	
	private Cliente cliente;
	private Comprobante comprobante;
	private CuentaTesoreria cuenta;
	private CobranzaCabecera cobranzaCabecera;
	
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
	
	public Integer getTipo_comprobante() {
		return tipo_comprobante;
	}
	
	public void setTipo_comprobante(Integer tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	
	public Integer getId_modo_pago() {
		return id_modo_pago;
	}
	
	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}
	
	public Integer getId_emisor() {
		return id_emisor;
	}
	
	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
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
	
	public Integer getId_cobranza() {
		return id_cobranza;
	}
	
	public void setId_cobranza(Integer id_cobranza) {
		this.id_cobranza = id_cobranza;
	}
	
	public BigDecimal getImporte_cobrado() {
		return importe_cobrado;
	}
	
	public void setImporte_cobrado(BigDecimal importe_cobrado) {
		this.importe_cobrado = importe_cobrado;
	}
	
	public Date getFecha_cobranza() {
		return fecha_cobranza;
	}
	
	public void setFecha_cobranza(Date fecha_cobranza) {
		this.fecha_cobranza = fecha_cobranza;
	}

	public BigDecimal getImporte_pendiente() {
		return importe_pendiente;
	}

	public void setImporte_pendiente(BigDecimal importe_pendiente) {
		this.importe_pendiente = importe_pendiente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}

	public CuentaTesoreria getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaTesoreria cuenta) {
		this.cuenta = cuenta;
	}

	public CobranzaCabecera getCobranzaCabecera() {
		return cobranzaCabecera;
	}

	public void setCobranzaCabecera(CobranzaCabecera cobranzaCabecera) {
		this.cobranzaCabecera = cobranzaCabecera;
	}	
	
	
}
