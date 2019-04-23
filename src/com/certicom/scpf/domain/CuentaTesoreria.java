package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class CuentaTesoreria {

	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_emisor;
	private String banco;
	private String cuenta;
	private Integer tipo_moneda;
	private Integer tipo_cuenta;
	private BigDecimal saldo;
	private Date fecha_saldo;
	
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
	
	public String getBanco() {
		return banco;
	}
	
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public String getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	public Integer getTipo_moneda() {
		return tipo_moneda;
	}
	
	public void setTipo_moneda(Integer tipo_moneda) {
		this.tipo_moneda = tipo_moneda;
	}
	
	public Integer getTipo_cuenta() {
		return tipo_cuenta;
	}
	
	public void setTipo_cuenta(Integer tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public Date getFecha_saldo() {
		return fecha_saldo;
	}
	
	public void setFecha_saldo(Date fecha_saldo) {
		this.fecha_saldo = fecha_saldo;
	}	
	
}
