package com.certicom.scpf.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PagoCabecera {
	
	private Integer id_proveedor;
	private Integer id_cuenta_tesoreria;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_emisor;
	private BigDecimal pago_total;
	private Date fecha_pago;
	private BigDecimal total_importe_pagado;
	private BigDecimal saldo_deudor;
	private BigDecimal saldo_pagar;
	
	private Proveedores proveedor;
	
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

	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public BigDecimal getTotal_importe_pagado() {
		return total_importe_pagado;
	}

	public void setTotal_importe_pagado(BigDecimal total_importe_pagado) {
		this.total_importe_pagado = total_importe_pagado;
	}

	public BigDecimal getSaldo_deudor() {
		return saldo_deudor;
	}

	public void setSaldo_deudor(BigDecimal saldo_deudor) {
		this.saldo_deudor = saldo_deudor;
	}

	public BigDecimal getSaldo_pagar() {
		return saldo_pagar;
	}

	public void setSaldo_pagar(BigDecimal saldo_pagar) {
		this.saldo_pagar = saldo_pagar;
	}	
	
}
