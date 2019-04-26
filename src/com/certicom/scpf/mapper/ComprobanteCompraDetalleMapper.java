package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ComprobanteCompraDetalle;



public interface ComprobanteCompraDetalleMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select e.* from t_comprobante_compra_detalle e where e.id_proveedor = #{id_proveedor}")
	public ComprobanteCompraDetalle findById(@Param("p_cliente") Integer codigoCliente) throws Exception;
	
	@Select("select e.* from t_comprobante_compra_detalle e order by e.id_proveedor asc")
	public List<ComprobanteCompraDetalle> findAll() throws Exception;
	
	
	@Insert("insert into t_comprobante_compra_detalle (id_comprobante_compra, id_proveedor, id_producto, tipo_comprobante, id_tipo_producto, id_almacen, tipo_unidad_medida, cantidad_unidades_item, suma_tributos, descuento, monto_tributo_igv, base_imponible_igv, monto_tributo_isc, base_imponible_isc, monto_tributo_otros, base_imponible_otros, precio_venta_unitario_costo, valor_venta_item, valor_referencial_unit) values "
			+ "(#{id_comprobante_compra}, #{id_proveedor}, #{id_producto}, #{tipo_comprobante}, #{id_tipo_producto}, #{id_almacen}, #{tipo_unidad_medida}, #{cantidad_unidades_item}, #{suma_tributos}, #{descuento}, #{monto_tributo_igv}, #{base_imponible_igv}, #{monto_tributo_isc}, #{base_imponible_isc} , #{monto_tributo_otros}, #{base_imponible_otros}, #{precio_venta_unitario_costo}, #{valor_venta_item}, #{valor_referencial_unit})")
	public void crearComprobanteCompraDetalle(ComprobanteCompraDetalle comprobanteDetalle) throws Exception; /*Vega.com*/
	
	@Update("update t_comprobante_compra_detalle set id_comprobante_compra = #{id_comprobante_compra}, id_proveedor = #{id_proveedor}, id_producto = #{id_producto}, tipo_comprobante = #{tipo_comprobante}, id_tipo_producto = #{id_tipo_producto}, id_almacen = #{id_almacen}, tipo_unidad_medida = #{tipo_unidad_medida}, cantidad_unidades_item = #{cantidad_unidades_item}, suma_tributos = #{suma_tributos}, descuento = #{descuento}, "
			+ "monto_tributo_igv = #{monto_tributo_igv}, base_imponible_igv = #{base_imponible_igv}, monto_tributo_isc = #{monto_tributo_isc}, base_imponible_isc = #{base_imponible_isc}, monto_tributo_otros = #{monto_tributo_otros}, base_imponible_otros = #{base_imponible_otros}, precio_venta_unitario_costo = #{precio_venta_unitario_costo}, valor_venta_item = #{valor_venta_item}, valor_referencial_unit = #{valor_referencial_unit} where id_proveedor= #{id_proveedor}")
	@Options(flushCache=true,useCache=true)
    public void actualizarComprobanteCompraDetalle(ComprobanteCompraDetalle comprobanteDetalle) throws Exception; /*Vega.com*/
	
	@Delete("delete  from t_comprobante_compra_detalle  where id_proveedor = #{id_proveedor}")
	@Options(flushCache=true)
	public void eliminarComprobanteCompraDetalle(@Param("id_proveedor") Integer id_comprobante) throws Exception;
	
	
	public List<ComprobanteCompraDetalle> findByIdComprobanteCompra(@Param("id_comprobante_compra") Integer id_comprobante) throws Exception;
	
}
