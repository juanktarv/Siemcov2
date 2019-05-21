package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.ArriboDetalle;

/*Siemco v2.0*/
public interface ArriboDetalleMapper {
	
	@Select("select * from t_arribo_detalle e "
			+ " where e.id_arribo = #{id_arribo} and "
			+ " e.id_comprobante_compra = #{id_comprobante_compra} and "
			+ " e.tipo_comprobante = #{tipo_comprobante} and "
			+ " e.id_proveedor = #{id_proveedor} and "
			+ " e.id_producto = #{id_producto} and "
			+ " e.id_emisor = #{id_emisor} and"
			+ " e.id_domicilio_fiscal = #{id_domicilio_fiscal} and "
			+ " e.id_almacen =  #{id_almacen} ")
	public ArriboDetalle 
	findById(@Param("id_arribo") Integer codigoArribo, 
			@Param("id_comprobante_compra") Integer codigoComprobanteCompra, 
			@Param("tipo_comprobante") String tipoComprobante,
			@Param("id_proveedor") Integer codigoProveedor,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal, 
			@Param("id_almacen") Integer codigoAlmacen) throws Exception;
		
	
	@Select("select * from t_arribo_detalle order by id_arribo desc")
	public List<ArriboDetalle> findAll() throws Exception;
	
	
	@Insert("insert into t_arribo_detalle "
			+ " (id_arribo, id_comprobante_compra, tipo_comprobante, id_proveedor, id_producto, id_domicilio_fiscal, id_emisor, id_almacen,  "
			+ " id_tipo_producto, fecha_movimiento, cantidad_arribo, documento_arribo, numero_documento_arribo ) "
			+ " values (#{id_arribo}, #{id_comprobante_compra}, #{tipo_comprobante},  #{id_proveedor}, #{id_producto}, #{id_domicilio_fiscal}, #{id_emisor}, #{id_almacen}, "
			+ " #{id_tipo_producto}, #{fecha_movimiento} , #{cantidad_arribo},  #{documento_arribo},  #{numero_documento_arribo})")
	public void crearArriboDetalle(ArriboDetalle arriboDetalle) throws Exception;
	
	
	
	@Update("update t_arribo_detalle set "
			+ " id_tipo_producto = #{id_tipo_producto}, fecha_movimiento = #{fecha_movimiento}, cantidad_arribo = #{cantidad_arribo}, documento_arribo = #{documento_arribo},"
			+ " numero_documento_arribo = #{numero_documento_arribo}  "
			+ " where id_arribo= #{id_arribo} and id_comprobante_compra= #{id_comprobante_compra} and id_proveedor= #{id_proveedor} and  tipo_comprobante= #{tipo_comprobante} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} and id_almacen= #{id_almacen} ")
	@Options(flushCache=true,useCache=true)
    public void actualizarArriboDetalle(ArriboDetalle arriboDetalle) throws Exception;
	
	@Delete("delete from t_arribo_detalle  "
			+ "where id_arribo= #{id_arribo} and id_comprobante_compra= #{id_comprobante_compra} and id_proveedor= #{id_proveedor} and  tipo_comprobante= #{tipo_comprobante} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} and id_almacen= #{id_almacen}  ")
	@Options(flushCache=true)
	public void eliminarArriboDetalle(
			@Param("id_arribo") Integer codigoArribo, 
			@Param("id_comprobante_compra") Integer codigoComprobanteCompra, 
			@Param("tipo_comprobante") String tipoComprobante,
			@Param("id_proveedor") Integer codigoProveedor,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal, 
			@Param("id_almacen") Integer codigoAlmacen) throws Exception;
	


}
