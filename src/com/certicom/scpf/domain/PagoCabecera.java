package com.certicom.scpf.domain;

import java.math.BigDecimal;

public class PagoCabecera {
	
	private Integer id_proveedor;
	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_emisor;
	private BigDecimal pago_total;
	
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
	
	public BigDecimal getPago_total() {
		return pago_total;
	}
	
	public void setPago_total(BigDecimal pago_total) {
		this.pago_total = pago_total;
	}	
	
}
