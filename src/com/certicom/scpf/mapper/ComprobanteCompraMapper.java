package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ComprobanteCompra;


public interface ComprobanteCompraMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	
	
	@Select("select e.* from t_comprobante_compra e order by e.id_comprobante_compra asc")
	public List<ComprobanteCompra> findAll() throws Exception;
	
	@Insert("insert into t_comprobante_compra (id_proveedor, tipo_comprobante, nroserie_documento, tipo_operacion, fecha_emision, hora_emision, fecha_vencimiento, tipo_moneda_cab, suma_tributos, total_valor_compra, total_precio_compra, suma_otros_cargos, importe_total_compra, estado_sunat, estado_pago) "
			+ "values (#{id_proveedor}, #{tipo_comprobante}, #{nroserie_documento}, #{tipo_operacion}, #{fecha_emision}, #{hora_emision}, #{fecha_vencimiento}, #{tipo_moneda_cab}, #{suma_tributos}, #{total_valor_compra}, #{total_precio_compra}, #{suma_otros_cargos}, #{importe_total_compra}, #{estado_sunat}, #{estado_pago})")
	public void crearComprobanteCompra(ComprobanteCompra comprobanteCompra) throws Exception;
	
	@Update("update t_comprobante_compra set id_proveedor = #{id_proveedor}, tipo_comprobante = #{tipo_comprobante}, nroserie_documento = #{nroserie_documento}, tipo_operacion = #{tipo_operacion}, fecha_emision = #{fecha_emision}, hora_emision = #{hora_emision}, fecha_vencimiento = #{fecha_vencimiento}, tipo_moneda_cab = #{tipo_moneda_cab}, suma_tributos = #{suma_tributos}, total_valor_compra = #{total_valor_compra}, total_precio_compra = #{total_precio_compra}, suma_otros_cargos = #{suma_otros_cargos}, importe_total_compra = #{importe_total_compra}, estado_sunat = #{estado_sunat}, estado_pago = #{estado_pago} where id_comprobante_compra= #{id_comprobante_compra}")
	@Options(flushCache=true,useCache=true)
    public void actualizarComprobanteCompra(ComprobanteCompra comprobanteCompra) throws Exception;
	
	@Delete("delete  from t_comprobante_compra  where id_comprobante_compra = #{id_comprobante_compra}")
	@Options(flushCache=true)
	public void eliminarComprobanteCompra(@Param("id_comprobante_compra") Integer id_comprobante_compra) throws Exception;
	
	@Select("SELECT (coalesce(MAX(correlativo), 0)+1) AS maxmcorrelativo "
			+ " from t_comprobante_compra where tipo_comprobante=#{tipo_comprobante}")
	public int getCorrelativoComprobante(@Param("tipo_comprobante") String tipo_comprobante)throws Exception;
	
	
}
