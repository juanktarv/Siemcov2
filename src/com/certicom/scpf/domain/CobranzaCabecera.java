package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CobranzaCabecera {

	private Integer id_cliente;
	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_emisor;
	private Integer id_cobranza;
	private BigDecimal total_importe_cobrado;
	private Date fecha_cobranza;
	
	private Cliente cliente;
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
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
	
	public Integer getId_cobranza() {
		return id_cobranza;
	}
	
	public void setId_cobranza(Integer id_cobranza) {
		this.id_cobranza = id_cobranza;
	}
	
	public BigDecimal getTotal_importe_cobrado() {
		return total_importe_cobrado;
	}
	
	public void setTotal_importe_cobrado(BigDecimal total_importe_cobrado) {
		this.total_importe_cobrado = total_importe_cobrado;
	}
	
	public Date getFecha_cobranza() {
		return fecha_cobranza;
	}
	
	public void setFecha_cobranza(Date fecha_cobranza) {
		this.fecha_cobranza = fecha_cobranza;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
	
}
