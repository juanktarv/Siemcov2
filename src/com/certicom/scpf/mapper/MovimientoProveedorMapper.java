package com.certicom.scpf.mapper;

import org.apache.ibatis.annotations.Insert;

import com.certicom.scpf.domain.MovimientoProveedores;

public interface MovimientoProveedorMapper {

	@Insert(" insert into t_movimiento_proveedores "
			+ "(id_comprobante_compra, tipo_comprobante, id_proveedor, fecha_movimiento, "
			+ "fecha_vencimiento, tipo_documento, importe, forma_pago, nroserie_documento) values ("
			+ "#{id_comprobante_compra}, #{tipo_comprobante}, #{id_proveedor}, #{fecha_movimiento}, "
			+ "#{fecha_vencimiento}, #{tipo_documento}, #{importe}, #{forma_pago}, #{nroserie_documento})")
	public void crearMovimiento(MovimientoProveedores movimiento);

}
