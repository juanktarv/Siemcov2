package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoClientes {

	private Integer id_comprobante;
	private Integer id_cliente;
	private String tipo_comprobante;
	private Integer id_modo_pago;
	private Integer id_emisor;
	private Date fecha_movimiento;
	private Date fecha_vencimiento;
	private Integer tipo_documento;
	private BigDecimal importe;
	private String nroserie_documento;
	private String flag_regularizado;
	private String formapago;
	private BigDecimal saldopagado;
	
	private Cliente cliente;
	private Comprobante comprobante;
	
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
	
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public Integer getTipo_documento() {
		return tipo_documento;
	}
	
	public void setTipo_documento(Integer tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public String getNroserie_documento() {
		return nroserie_documento;
	}
	
	public void setNroserie_documento(String nroserie_documento) {
		this.nroserie_documento = nroserie_documento;
	}
	
	public String getFlag_regularizado() {
		return flag_regularizado;
	}
	
	public void setFlag_regularizado(String flag_regularizado) {
		this.flag_regularizado = flag_regularizado;
	}
	
	public String getFormapago() {
		return formapago;
	}
	
	public void setFormapago(String formapago) {
		this.formapago = formapago;
	}
	
	public BigDecimal getSaldopagado() {
		return saldopagado;
	}
	
	public void setSaldopagado(BigDecimal saldopagado) {
		this.saldopagado = saldopagado;
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
	
}
