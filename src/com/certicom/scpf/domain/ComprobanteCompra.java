package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ComprobanteCompra {

	private Integer id_comprobante_compra;
	private Integer id_proveedor;
	private String tipo_comprobante;
	private String nroserie_documento;
	private String tipo_operacion;
	private Date fecha_emision;
	private Date hora_emision;
	private Date fecha_vencimiento;
	private String tipo_moneda_cab;
	private BigDecimal suma_tributos;
	private BigDecimal total_valor_compra;
	private BigDecimal total_precio_compra;
	private BigDecimal suma_otros_cargos;
	private BigDecimal importe_total_compra;
	private String estado_sunat;
	private String estado_pago;
	private Integer correlativo;
	
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
	
	public String getTipo_comprobante() {
		return tipo_comprobante;
	}
	
	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	
	public String getNroserie_documento() {
		return nroserie_documento;
	}
	
	public void setNroserie_documento(String nroserie_documento) {
		this.nroserie_documento = nroserie_documento;
	}
	
	public String getTipo_operacion() {
		return tipo_operacion;
	}
	
	public void setTipo_operacion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	
	public Date getFecha_emision() {
		return fecha_emision;
	}
	
	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}
	
	public Date getHora_emision() {
		return hora_emision;
	}
	
	public void setHora_emision(Date hora_emision) {
		this.hora_emision = hora_emision;
	}
	
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public String getTipo_moneda_cab() {
		return tipo_moneda_cab;
	}
	
	public void setTipo_moneda_cab(String tipo_moneda_cab) {
		this.tipo_moneda_cab = tipo_moneda_cab;
	}
	
	public BigDecimal getSuma_tributos() {
		return suma_tributos;
	}
	
	public void setSuma_tributos(BigDecimal suma_tributos) {
		this.suma_tributos = suma_tributos;
	}
	
	public BigDecimal getTotal_valor_compra() {
		return total_valor_compra;
	}
	
	public void setTotal_valor_compra(BigDecimal total_valor_compra) {
		this.total_valor_compra = total_valor_compra;
	}
	
	public BigDecimal getTotal_precio_compra() {
		return total_precio_compra;
	}
	
	public void setTotal_precio_compra(BigDecimal total_precio_compra) {
		this.total_precio_compra = total_precio_compra;
	}
	
	public BigDecimal getSuma_otros_cargos() {
		return suma_otros_cargos;
	}
	
	public void setSuma_otros_cargos(BigDecimal suma_otros_cargos) {
		this.suma_otros_cargos = suma_otros_cargos;
	}
	
	public BigDecimal getImporte_total_compra() {
		return importe_total_compra;
	}
	
	public void setImporte_total_compra(BigDecimal importe_total_compra) {
		this.importe_total_compra = importe_total_compra;
	}
	
	public String getEstado_sunat() {
		return estado_sunat;
	}
	
	public void setEstado_sunat(String estado_sunat) {
		this.estado_sunat = estado_sunat;
	}
	
	public String getEstado_pago() {
		return estado_pago;
	}
	
	public void setEstado_pago(String estado_pago) {
		this.estado_pago = estado_pago;
	}

	public Integer getCorrelativo() {
		return correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}	
	
}
