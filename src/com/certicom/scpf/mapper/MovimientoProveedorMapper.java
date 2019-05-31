package com.certicom.scpf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.MovimientoClientes;
import com.certicom.scpf.domain.MovimientoProveedores;

public interface MovimientoProveedorMapper {

	@Insert(" insert into t_movimiento_proveedores "
			+ "(id_comprobante_compra, tipo_comprobante, id_proveedor, fecha_movimiento, "
			+ "fecha_vencimiento, tipo_documento, importe, forma_pago, nroserie_documento) values ("
			+ "#{id_comprobante_compra}, #{tipo_comprobante}, #{id_proveedor}, #{fecha_movimiento}, "
			+ "#{fecha_vencimiento}, #{tipo_documento}, #{importe}, #{forma_pago}, #{nroserie_documento})")
	public void crearMovimiento(MovimientoProveedores movimiento);

	
	public Integer countCompByAnioMesTipoPAGINATOR(@Param("anio") Integer annio, @Param("mes") Integer mes,
			   @Param("tipo_comprobante") String tipo_comprobante, 
			   @Param("filters") Map<String,Object> filters);
	
	public List<MovimientoProveedores> listComprobantesByAnioMesTipoPAGINATOR(
			@Param("anio") Integer annio, @Param("mes") Integer mes,@Param("tipo_comprobante") String tipo_comprobante, 
			@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, 
			@Param("sortField") String sortField, @Param("sortOrder") String sortOrder);


	@Update(" update t_movimiento_proveedores  set saldopagado=((CASE WHEN saldopagado IS NULL THEN 0 ELSE saldopagado END)+#{pago}) "
			+ "where id_comprobante_compra=#{id_comprobante_compra}")
	@Options(flushCache=true,useCache=true)
	public void actualizarMovimiento(MovimientoProveedores mov);

	

}
