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
	private Producto producto;
	private Integer id_emisor;
	private Integer id_domicilio_fiscal_cab;
	private Integer id_modo_pago;
	private BigDecimal suma_tributos_cab;
	private BigDecimal total_valor_venta_cab;
	private BigDecimal total_precio_venta_cab;
	private BigDecimal total_descuentos_cab;
	private BigDecimal suma_otros_cargos_cab;
	private BigDecimal total_anticipos_cab;
	private BigDecimal importe_total_venta_cab;
	private String numero_serie_documento_cab;
	private String version_ubl;
	private Boolean estado_comunicacion_baja;
	private Date fecha_emision_cab;
	private Date hora_emision_cab;
	private Date fecha_vencimiento_cab;
	private String tipo_operacion_cab;
	private String tipo_documento;
	
	
	public String getTipo_operacion_cab() {
		return tipo_operacion_cab;
	}

	public void setTipo_operacion_cab(String tipo_operacion_cab) {
		this.tipo_operacion_cab = tipo_operacion_cab;
	}

	public Date getFecha_emision_cab() {
		return fecha_emision_cab;
	}

	public void setFecha_emision_cab(Date fecha_emision_cab) {
		this.fecha_emision_cab = fecha_emision_cab;
	}

	public Date getHora_emision_cab() {
		return hora_emision_cab;
	}

	public void setHora_emision_cab(Date hora_emision_cab) {
		this.hora_emision_cab = hora_emision_cab;
	}

	public Date getFecha_vencimiento_cab() {
		return fecha_vencimiento_cab;
	}

	public void setFecha_vencimiento_cab(Date fecha_vencimiento_cab) {
		this.fecha_vencimiento_cab = fecha_vencimiento_cab;
	}

	public Boolean getEstado_comunicacion_baja() {
		return estado_comunicacion_baja;
	}

	public void setEstado_comunicacion_baja(Boolean estado_comunicacion_baja) {
		this.estado_comunicacion_baja = estado_comunicacion_baja;
	}

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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getId_emisor() {
		return id_emisor;
	}

	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}

	public Integer getId_domicilio_fiscal_cab() {
		return id_domicilio_fiscal_cab;
	}

	public void setId_domicilio_fiscal_cab(Integer id_domicilio_fiscal_cab) {
		this.id_domicilio_fiscal_cab = id_domicilio_fiscal_cab;
	}

	public Integer getId_modo_pago() {
		return id_modo_pago;
	}

	public void setId_modo_pago(Integer id_modo_pago) {
		this.id_modo_pago = id_modo_pago;
	}

	public BigDecimal getSuma_tributos_cab() {
		return suma_tributos_cab;
	}

	public void setSuma_tributos_cab(BigDecimal suma_tributos_cab) {
		this.suma_tributos_cab = suma_tributos_cab;
	}

	public BigDecimal getTotal_valor_venta_cab() {
		return total_valor_venta_cab;
	}

	public void setTotal_valor_venta_cab(BigDecimal total_valor_venta_cab) {
		this.total_valor_venta_cab = total_valor_venta_cab;
	}

	public BigDecimal getTotal_precio_venta_cab() {
		return total_precio_venta_cab;
	}

	public void setTotal_precio_venta_cab(BigDecimal total_precio_venta_cab) {
		this.total_precio_venta_cab = total_precio_venta_cab;
	}

	public BigDecimal getTotal_descuentos_cab() {
		return total_descuentos_cab;
	}

	public void setTotal_descuentos_cab(BigDecimal total_descuentos_cab) {
		this.total_descuentos_cab = total_descuentos_cab;
	}

	public BigDecimal getSuma_otros_cargos_cab() {
		return suma_otros_cargos_cab;
	}

	public void setSuma_otros_cargos_cab(BigDecimal suma_otros_cargos_cab) {
		this.suma_otros_cargos_cab = suma_otros_cargos_cab;
	}

	public BigDecimal getTotal_anticipos_cab() {
		return total_anticipos_cab;
	}

	public void setTotal_anticipos_cab(BigDecimal total_anticipos_cab) {
		this.total_anticipos_cab = total_anticipos_cab;
	}

	public BigDecimal getImporte_total_venta_cab() {
		return importe_total_venta_cab;
	}

	public void setImporte_total_venta_cab(BigDecimal importe_total_venta_cab) {
		this.importe_total_venta_cab = importe_total_venta_cab;
	}

	public String getNumero_serie_documento_cab() {
		return numero_serie_documento_cab;
	}

	public void setNumero_serie_documento_cab(String numero_serie_documento_cab) {
		this.numero_serie_documento_cab = numero_serie_documento_cab;
	}

	public String getVersion_ubl() {
		return version_ubl;
	}

	public void setVersion_ubl(String version_ubl) {
		this.version_ubl = version_ubl;
	}

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}	
	
}
