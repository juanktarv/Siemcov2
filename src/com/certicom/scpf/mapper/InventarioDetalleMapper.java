package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.InventarioDetalle;

/*Siemco v2.0*/
public interface InventarioDetalleMapper {
	
	@Select("select * from t_inventario_detalle e "
			+ " where e.id_almacen = #{id_almacen} and "
			+ " e.id_despacho = #{id_despacho} and "
			+ " e.id_arribo = #{id_arribo} and "
			+ " e.id_producto = #{id_producto} and "
			+ " e.id_emisor = #{id_emisor} and"
			+ " e.id_domicilio_fiscal = #{id_domicilio_fiscal} and "
			+ " e.id_almacen_transferencia = #{id_almacen_transferencia} ")
	public InventarioDetalle 
	findById(@Param("id_almacen") Integer codigoAlmacen, 
			@Param("id_despacho") Integer codigoDespacho, 
			@Param("id_arribo") Integer codigoArribo,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal,
			@Param("id_almacen_transferencia") Integer codigoAlmacenTransferencia) throws Exception;
		
	
	@Select("select * from t_inventario_detalle order by id_producto desc")
	public List<InventarioDetalle> findAll() throws Exception;
	
	@Select("select id.* "
			+ "from  t_inventario_detalle id "
			+ "where id.id_producto = #{id_producto} and "
			+ " id.id_almacen = #{id_almacen}  and "
			+ "id.id_emisor= #{id_emisor} and "
			+ "id.id_domicilio_fiscal = #{id_domicilio_fiscal} "
			+ "order by fecha_movimiento desc")
	public List<InventarioDetalle> buscarPorInventario(@Param("id_producto") Integer codigoProducto, 
			                                           @Param("id_almacen") Integer codigoAlmacen, 
			                                           @Param("id_emisor") Integer codigoEmisor, 
			                                           @Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal ) throws Exception;
		
	@Insert("insert into t_inventario_detalle "
			+ " (id_almacen, id_despacho, id_arribo, id_producto, id_domicilio_fiscal, id_emisor, "
			+ " id_tipo_producto, fecha_movimiento, cantidad_ingresa, cantidad_salida, tipo_movimiento , id_almacen_transferencia ) "
			+ " values (#{id_almacen}, #{id_despacho}, #{id_arribo}, #{id_producto}, #{id_domicilio_fiscal}, #{id_emisor},  "
			+ " #{id_tipo_producto}, #{fecha_movimiento} , #{cantidad_ingresa},  #{cantidad_salida},  #{tipo_movimiento} , #{id_almacen_transferencia} )")
	public void crearInventarioDetalle(InventarioDetalle inventarioDetalle) throws Exception;
	
	
	@Update("update t_inventario_detalle set "
			+ " id_tipo_producto = #{id_tipo_producto}, fecha_movimiento = #{fecha_movimiento}, cantidad_ingresa = #{cantidad_ingresa}, "
			+ " cantidad_salida = #{cantidad_salida},  tipo_movimiento = #{tipo_movimiento} "
			+ " where id_almacen= #{id_almacen} and id_despacho= #{id_despacho} and  id_arribo= #{id_arribo} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} and  id_almacen_transferencia= #{id_almacen_transferencia} ")
	@Options(flushCache=true,useCache=true)
    public void actualizarInventarioDetalle(InventarioDetalle inventarioDetalle) throws Exception;
	
	@Delete("delete from t_inventario_detalle  "
			+ " where id_almacen= #{id_almacen} and id_despacho= #{id_despacho} and  id_arribo= #{id_arribo} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} and   id_almacen_transferencia= #{id_almacen_transferencia} ")
	@Options(flushCache=true)
	public void eliminarInventarioDetalle(
			@Param("id_almacen") Integer codigoAlmacen, 
			@Param("id_despacho") Integer codigoDespacho, 
			@Param("id_arribo") Integer codigoArribo,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal, 
			@Param("id_almacen_transferencia") Integer codigoAlmacenTransferencia) throws Exception;
	

	

}
