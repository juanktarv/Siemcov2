package com.certicom.scpf.domain;

import java.math.BigDecimal;


/*Siemco v2.0*/

public class MovimientoInventarioProveedores {
	
	
	private Integer id_comprobante_compra;
	private Integer id_proveedores; 
	private String  tipo_comprobante;
	private Integer id_producto;
	private Integer id_domicilio_fiscal;
	private Integer id_emisor;
	private Integer id_tipo_producto;
	private String  numero_serie_documento;
	private String  tipo_unidad_medida;
	private BigDecimal  cant_unidades_item; 
	private BigDecimal  saldo_arribo;
	private Boolean flag_regularizado;
	
	private String nombreProveedor; 
	private String numeroDocumentoProveedor;
	private String codigoProducto;
	private String descripcionProducto;
	private String abrevUnidadMedida;
	private String descripcionUnidadMedida;
	private String numeroSerieDocumento;

	private String nombreTipoComprobante;
	
	

	public Integer getId_comprobante_compra() {
		return id_comprobante_compra;
	}
	public void setId_comprobante_compra(Integer id_comprobante_compra) {
		this.id_comprobante_compra = id_comprobante_compra;
	}
	public Integer getId_proveedores() {
		return id_proveedores;
	}
	public void setId_proveedores(Integer id_proveedores) {
		this.id_proveedores = id_proveedores;
	}
	public String getTipo_comprobante() {
		return tipo_comprobante;
	}
	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}
	public Integer getId_producto() {
		return id_producto;
	}
	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}
	public Integer getId_domicilio_fiscal() {
		return id_domicilio_fiscal;
	}
	public void setId_domicilio_fiscal(Integer id_domicilio_fiscal) {
		this.id_domicilio_fiscal = id_domicilio_fiscal;
	}
	public Integer getId_tipo_producto() {
		return id_tipo_producto;
	}
	public void setId_tipo_producto(Integer id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}
	public Integer getId_emisor() {
		return id_emisor;
	}
	public void setId_emisor(Integer id_emisor) {
		this.id_emisor = id_emisor;
	}
	public Boolean getFlag_regularizado() {
		return flag_regularizado;
	}
	public void setFlag_regularizado(Boolean flag_regularizado) {
		this.flag_regularizado = flag_regularizado;
	}
	public String getNumero_serie_documento() {
		return numero_serie_documento;
	}
	public void setNumero_serie_documento(String numero_serie_documento) {
		this.numero_serie_documento = numero_serie_documento;
	}
	public String getTipo_unidad_medida() {
		return tipo_unidad_medida;
	}
	public void setTipo_unidad_medida(String tipo_unidad_medida) {
		this.tipo_unidad_medida = tipo_unidad_medida;
	}
	public BigDecimal getCant_unidades_item() {
		return cant_unidades_item;
	}
	public void setCant_unidades_item(BigDecimal cant_unidades_item) {
		this.cant_unidades_item = cant_unidades_item;
	}
	public BigDecimal getSaldo_arribo() {
		return saldo_arribo;
	}
	public void setSaldo_arribo(BigDecimal saldo_arribo) {
		this.saldo_arribo = saldo_arribo;
	}
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getNumeroDocumentoProveedor() {
		return numeroDocumentoProveedor;
	}
	public void setNumeroDocumentoProveedor(String numeroDocumentoProveedor) {
		this.numeroDocumentoProveedor = numeroDocumentoProveedor;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getAbrevUnidadMedida() {
		return abrevUnidadMedida;
	}
	public void setAbrevUnidadMedida(String abrevUnidadMedida) {
		this.abrevUnidadMedida = abrevUnidadMedida;
	}
	public String getDescripcionUnidadMedida() {
		return descripcionUnidadMedida;
	}
	public void setDescripcionUnidadMedida(String descripcionUnidadMedida) {
		this.descripcionUnidadMedida = descripcionUnidadMedida;
	}
	public String getNumeroSerieDocumento() {
		return numeroSerieDocumento;
	}
	public void setNumeroSerieDocumento(String numeroSerieDocumento) {
		this.numeroSerieDocumento = numeroSerieDocumento;
	}
	public String getNombreTipoComprobante() {
		return nombreTipoComprobante;
	}
	public void setNombreTipoComprobante(String nombreTipoComprobante) {
		this.nombreTipoComprobante = nombreTipoComprobante;
	}
	
	
	
    
	

}
