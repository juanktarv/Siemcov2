package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoProveedores {

	private Integer id_comprobante_compra;
	private String tipo_comprobante;
	private Integer id_proveedor;
	private Date fecha_movimiento;
	private Date fecha_vencimiento;
	private String tipo_documento;
	private BigDecimal importe;
	private Integer forma_pago;
	private String nroserie_documento;
	private BigDecimal saldopagado;
	private BigDecimal deuda;
	private String flag_regularizado;
	
	private BigDecimal pago;
	
	private Proveedores proveedor;
	private ComprobanteCompra comprobanteCompra;
	
	public Integer getId_comprobante_compra() {
		return id_comprobante_compra;
	}
	
	public void setId_comprobante_compra(Integer id_comprobante_compra) {
		this.id_comprobante_compra = id_comprobante_compra;
	}
	
	public String getTipo_comprobante() {
		return tipo_comprobante;
	}
	
	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
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
	
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public String getTipo_documento() {
		return tipo_documento;
	}
	
	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public Integer getForma_pago() {
		return forma_pago;
	}
	
	public void setForma_pago(Integer forma_pago) {
		this.forma_pago = forma_pago;
	}
	
	public String getNroserie_documento() {
		return nroserie_documento;
	}
	
	public void setNroserie_documento(String nroserie_documento) {
		this.nroserie_documento = nroserie_documento;
	}

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public ComprobanteCompra getComprobanteCompra() {
		return comprobanteCompra;
	}

	public void setComprobanteCompra(ComprobanteCompra comprobanteCompra) {
		this.comprobanteCompra = comprobanteCompra;
	}

	public BigDecimal getSaldopagado() {
		return saldopagado;
	}

	public void setSaldopagado(BigDecimal saldopagado) {
		this.saldopagado = saldopagado;
	}

	public BigDecimal getDeuda() {
		return deuda;
	}

	public void setDeuda(BigDecimal deuda) {
		this.deuda = deuda;
	}

	public BigDecimal getPago() {
		return pago;
	}

	public void setPago(BigDecimal pago) {
		this.pago = pago;
	}

	public String getFlag_regularizado() {
		return flag_regularizado;
	}

	public void setFlag_regularizado(String flag_regularizado) {
		this.flag_regularizado = flag_regularizado;
	}	
	
	
	
}
