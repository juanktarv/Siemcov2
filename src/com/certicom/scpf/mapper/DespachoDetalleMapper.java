package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.DespachoDetalle;

/*Siemco v2.0*/
public interface DespachoDetalleMapper {
	
	@Select("select * from t_despacho_detalle e "
			+ " where e.id_despacho = #{id_despacho} and "
			+ " e.id_comprobante = #{id_comprobante} and "
			+ " e.tipo_comprobante = #{tipo_comprobante} and "
			+ " e.id_cliente = #{id_cliente} and "
			+ " e.id_producto = #{id_producto} and "
			+ " e.id_emisor = #{id_emisor} and"
			+ " e.id_domicilio_fiscal = #{id_domicilio_fiscal} and "
			+ " e.id_almacen =  #{id_almacen} ")
	public DespachoDetalle 
	findById(@Param("id_despacho") Integer codigoDespacho, 
			@Param("id_comprobante") Integer codigoComprobante, 
			@Param("tipo_comprobante") String tipoComprobante,
			@Param("id_cliente") Integer codigoCliente,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal, 
			@Param("id_almacen") Integer codigoAlmacen) throws Exception;
		
	
	@Select("select * from t_despacho_detalle order by id_despacho desc")
	public List<DespachoDetalle> findAll() throws Exception;
	
	
	@Insert("insert into t_despacho_detalle "
			+ " (id_despacho, id_comprobante, tipo_comprobante, id_cliente, id_producto, id_domicilio_fiscal, id_emisor, id_almacen,  "
			+ " id_tipo_producto, fecha_movimiento, cantidad_despacho, documento_despacho, numero_documento_despacho ) "
			+ " values (#{id_despacho}, #{id_comprobante}, #{tipo_comprobante},  #{id_cliente}, #{id_producto}, #{id_domicilio_fiscal}, #{id_emisor}, #{id_almacen}, "
			+ " #{id_tipo_producto}, #{fecha_movimiento} , #{cantidad_despacho},  #{documento_despacho},  #{numero_documento_despacho})")
	public void crearDespachoDetalle(DespachoDetalle despachoDetalle) throws Exception;
	
	
	
	@Update("update t_despacho_detalle set "
			+ " id_tipo_producto = #{id_tipo_producto}, fecha_movimiento = #{fecha_movimiento}, cantidad_despacho = #{cantidad_despacho}, documento_despacho = #{documento_despacho},"
			+ " numero_documento_despacho = #{numero_documento_despacho}  "
			+ " where id_despacho= #{id_despacho} and id_comprobante= #{id_comprobante} and id_cliente= #{id_cliente} and  tipo_comprobante= #{tipo_comprobante} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} and id_almacen= #{id_almacen} ")
	@Options(flushCache=true,useCache=true)
    public void actualizarDespachoDetalle(DespachoDetalle despachoDetalle) throws Exception;
	
	@Delete("delete from t_despacho_detalle  "
			+ "where id_despacho= #{id_despacho} and id_comprobante= #{id_comprobante} and id_cliente= #{id_cliente} and  tipo_comprobante= #{tipo_comprobante} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} and id_almacen= #{id_almacen}  ")
	@Options(flushCache=true)
	public void eliminarDespachoDetalle(
			@Param("id_despacho") Integer codigoDespacho, 
			@Param("id_comprobante") Integer codigoComprobante, 
			@Param("tipo_comprobante") String tipoComprobante,
			@Param("id_cliente") Integer codigoCliente,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal, 
			@Param("id_almacen") Integer codigoAlmacen) throws Exception;

}
