package com.certicom.scpf.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.AlmacenTransferencia;


/*Siemco v2.0*/
public interface AlmacenTransferenciaMapper {
	
	@Select("select * from t_almacen_transferencia e "
			+ " where e.id_almacen_origen = #{p_almacen_origen} and "
			+ "e.id_almacen_destino = #{p_almacen_destino} and "
			+ "e.id_producto = #{p_producto} and "
			+ "e.id_emisor = #{p_emisor} and "
			+ "e.id_domicilio_fiscal = #{p_domicilio_fiscal} and "
			+ "e.fecha_transferencia = #{p_fecha_transferencia} and "
			+ "e.id_almacen_transferencia = #{p_almacen_transferencia} ")
	public AlmacenTransferencia findById(@Param("p_almacen_origen") Integer id_almacen_origen, 
										@Param("p_almacen_destino") Integer id_almacen_destino,
										@Param("p_producto") Integer id_producto, 
										@Param("p_emisor") Integer id_emisor, 
										@Param("p_domicilio_fiscal") Integer id_domicilio_fiscal,
										@Param("p_fecha_transferencia") Date fecha_transferencia, 
										@Param("p_almacen_transferencia") Integer id_almacen_transferencia) throws Exception;
	
	
	@Select("select * from t_almacen_transferencia order by fecha_transferencia asc")
	public List<AlmacenTransferencia> findAll() throws Exception;
	
	@Select("select at.*, p.descripcion_prod_det as descripcionProducto,  "
			+ " ( select o.descripcion_almacen from t_almacen o where o.id_almacen = at.id_almacen_origen ) as descripcionAlmacenOrigen, "
			+ " ( select d.descripcion_almacen from t_almacen d where d.id_almacen = at.id_almacen_destino ) as descripcionAlmacenDestino "
			+ " from "
			+ " t_almacen_transferencia at, "
			+ " t_producto p "
			+ " where "
			+ " p.id_producto = at.id_producto "
			+ " order by fecha_transferencia asc")
	public List<AlmacenTransferencia> listarTodos() throws Exception;
		
	
	@Insert("insert into t_almacen_transferencia (id_almacen_origen, id_almacen_destino, id_producto, id_emisor, id_domicilio_fiscal, fecha_transferencia, "
			+ " id_tipo_producto, cantidad_transferencia, documento_transferencia, numero_documento_transferencia ) "
			+ "values (#{id_almacen_origen}, #{id_almacen_destino}, #{id_producto}, #{id_emisor}, #{id_domicilio_fiscal}, #{fecha_transferencia}, "
			+ " #{id_tipo_producto}, #{cantidad_transferencia}, #{documento_transferencia}, #{numero_documento_transferencia} )")
	public void crearAlmacenTransferencia(AlmacenTransferencia almacenTransferencia) throws Exception;
	
	
	@Update("update t_almacen_transferencia set id_tipo_producto = #{id_tipo_producto}, cantidad_transferencia = #{cantidad_transferencia}, documento_transferencia = #{documento_transferencia},"
			+ " numero_documento_transferencia = #{numero_documento_transferencia}  "
			+ " where id_almacen_origen = #{id_almacen_origen} and "
			+ " id_almacen_destino = #{id_almacen_destino} and "
			+ " id_producto = #{id_producto} and "
			+ " id_emisor = #{id_emisor} and "
			+ " id_domicilio_fiscal = #{id_domicilio_fiscal} and "
			+ " fecha_transferencia = #{fecha_transferencia} and "
			+ " id_almacen_transferencia = #{id_almacen_transferencia} ")
	@Options(flushCache=true,useCache=true)
    public void actualizarAlmacenTransferencia(AlmacenTransferencia almacenTransferencia) throws Exception;
	
	@Delete("delete from t_almacen_transferencia "
			+ " where id_almacen_origen = #{id_almacen_origen} and "
			+ " id_almacen_destino = #{id_almacen_destino} and "
			+ " id_producto = #{id_producto} and "
			+ " id_emisor = #{id_emisor} and "
			+ " id_domicilio_fiscal = #{id_domicilio_fiscal} and "
			+ " fecha_transferencia = #{fecha_transferencia} and "
			+ " id_almacen_transferencia = #{id_almacen_transferencia} ")
	@Options(flushCache=true)
	public void eliminarAlmacenTransferencia(@Param("id_almacen_origen") Integer id_almacen_origen, 
											@Param("id_almacen_destino") Integer id_almacen_destino, 
											@Param("id_producto") Integer id_producto, 
											@Param("id_emisor") Integer id_emisor, 
											@Param("id_domicilio_fiscal") Integer id_domicilio_fiscal, 
											@Param("fecha_transferencia") Date fecha_transferencia, 
											@Param("id_almacen_transferencia") Integer id_almacen_transferencia) throws Exception;
	
	
	@Select("SELECT nextval('sec_almacen_transferencia')")
	public int getSecIdAlmacenTransferencia();
	

}
