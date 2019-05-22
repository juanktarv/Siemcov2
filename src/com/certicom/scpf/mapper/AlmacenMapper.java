package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.certicom.scpf.domain.Almacen;

/*Siemco v2.0*/
public interface AlmacenMapper {
	
	
	@Select("select * from t_almacen e where e.id_almacen = #{p_almacen}")
	public Almacen findById(@Param("p_almacen") Integer codigoAlmacen) throws Exception;
	
	@Select("select * from t_almacen order by id_almacen asc")
	public List<Almacen> findAll() throws Exception;
	
	@Select("select a.* "
			+ " from t_almacen a, t_inventario i "
			+ "where a.id_almacen = i.id_almacen and "
			+ "a.id_emisor = i.id_emisor and "
			+ "a.id_domicilio_fiscal = i.id_domicilio_fiscal  and "
			+ "i.id_producto = #{id_producto} and i.id_emisor = #{id_emisor} and i.id_domicilio_fiscal = #{id_domicilio_fiscal} order by id_almacen asc")
	public List<Almacen> listarPorProducto( @Param("id_producto") Integer id_producto, 
											@Param("id_emisor") Integer id_emisor, 
											@Param("id_domicilio_fiscal") Integer id_domicilio_fiscal) throws Exception;
	
	@Select("select a.* "
			+ " from t_almacen a, t_inventario i "
			+ "where a.id_almacen = i.id_almacen and "
			+ "a.id_emisor = i.id_emisor and "
			+ "a.id_domicilio_fiscal = i.id_domicilio_fiscal  and "
			+ "i.id_producto = #{id_producto} and i.id_emisor = #{id_emisor} and i.id_domicilio_fiscal = #{id_domicilio_fiscal} and i.id_almacen not in (#{id_almacen}) order by id_almacen asc")
	public List<Almacen> listarPorProductoAnidado( @Param("id_producto") Integer id_producto, 
											@Param("id_emisor") Integer id_emisor, 
											@Param("id_domicilio_fiscal") Integer id_domicilio_fiscal, 
											@Param("id_almacen") Integer id_almacen) throws Exception;
	
	@Select(" select a.* from t_almacen a "
			+ "where a.id_emisor = #{id_emisor} "
			+ "and a.id_domicilio_fiscal = #{id_domicilio_fiscal} "
			+ "and a.id_almacen not in (#{id_almacen}) "
			+ "order by id_almacen asc")
	public List<Almacen> listarAlmacenDestino(	@Param("id_emisor") Integer id_emisor, 
												@Param("id_domicilio_fiscal") Integer id_domicilio_fiscal, 
												@Param("id_almacen") Integer id_almacen);
	
	
	@Insert("insert into t_almacen (id_emisor, id_domicilio_fiscal, descripcion_almacen, direccion_almacen, estado_almacen) "
			+ "values (#{id_emisor}, #{id_domicilio_fiscal}, #{descripcion_almacen}, #{direccion_almacen}, #{estado_almacen})")
	public void crearAlmacen(Almacen almacen) throws Exception;
	
	
	@Update("update t_almacen set id_emisor = #{id_emisor}, id_domicilio_fiscal = #{id_domicilio_fiscal}, descripcion_almacen = #{descripcion_almacen},"
			+ " direccion_almacen = #{direccion_almacen}, estado_almacen = #{estado_almacen} "
			+ " where id_almacen= #{id_almacen}")
	@Options(flushCache=true,useCache=true)
    public void actualizarAlmacen(Almacen almacen) throws Exception;
	
	@Delete("delete from t_almacen  where id_almacen = #{id_almacen}")
	@Options(flushCache=true)
	public void eliminarAlmacen(@Param("id_almacen") Integer id_almacen) throws Exception;
	

	

}
