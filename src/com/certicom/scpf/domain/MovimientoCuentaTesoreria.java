package com.certicom.scpf.domain;

import java.util.Date;

public class MovimientoCuentaTesoreria {

	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_emisor;
	private Integer id_cliente;
	private Integer id_cobranza;
	private Integer id_proveedor;
	private Date fecha_movimiento;
	private Date entrada;
	private Date salida;
	private String tipomovimiento;
	
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
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Integer getId_cobranza() {
		return id_cobranza;
	}
	
	public void setId_cobranza(Integer id_cobranza) {
		this.id_cobranza = id_cobranza;
	}
	
	public Integer getId_proveedor() {
		return id_proveedor;
	}
	
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	
	public Date getEntrada() {
		return entrada;
	}
	
	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}
	
	public Date getSalida() {
		return salida;
	}
	
	public void setSalida(Date salida) {
		this.salida = salida;
	}
	
	public String getTipomovimiento() {
		return tipomovimiento;
	}
	
	public void setTipomovimiento(String tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}	
	
}
