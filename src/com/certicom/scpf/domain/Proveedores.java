package com.certicom.scpf.domain;

public class Proveedores {

	private Integer id_proveedor;
	private Integer tipo_documento;
	private String numero_documento;
	private String nombre_proveedor;
	private String direccion;
	private String numero_telefono;
	private String correo;
	
	/* transitorio */
	
	private String desTipoDocumento;
	
	public Integer getId_proveedor() {
		return id_proveedor;
	}
	
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	
	public Integer getTipo_documento() {
		return tipo_documento;
	}
	
	public void setTipo_documento(Integer tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
	public String getNumero_documento() {
		return numero_documento;
	}
	
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	
	public String getNombre_proveedor() {
		return nombre_proveedor;
	}
	
	public void setNombre_proveedor(String nombre_proveedor) {
		this.nombre_proveedor = nombre_proveedor;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNumero_telefono() {
		return numero_telefono;
	}
	
	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDesTipoDocumento() {
		return desTipoDocumento;
	}

	public void setDesTipoDocumento(String desTipoDocumento) {
		this.desTipoDocumento = desTipoDocumento;
	}	
	
}
