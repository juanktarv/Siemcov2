package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Arribo;

/*Siemco v2.0*/
public interface ArriboMapper {
	
	@Select("select * from t_arribo e where e.id_arribo = #{p_arribo}")
	public Arribo findById(@Param("p_arribo") Integer codigoArribo) throws Exception;
	
	@Select("select * from t_arribo order by id_arribo asc")
	public List<Arribo> findAll() throws Exception;
	
	@Insert("insert into t_arribo (id_producto, fecha_movimiento, cantidad_total_arribo) "
			+ "values (#{id_producto}, #{fecha_movimiento}, #{cantidad_total_arribo})")
	public void crearArribo(Arribo arribo) throws Exception;
	
	
	@Update("update t_arribo set id_producto = #{id_producto}, fecha_movimiento = #{fecha_movimiento}, cantidad_total_arribo = #{cantidad_total_arribo} "
			+ " where id_arribo= #{id_arribo}")
	@Options(flushCache=true,useCache=true)
    public void actualizarArribo(Arribo arribo) throws Exception;
	
	@Delete("delete from t_arribo  where id_arribo = #{id_arribo}")
	@Options(flushCache=true)
	public void eliminarArribo(@Param("id_arribo") Integer id_arribo) throws Exception;
	
	@Select("SELECT nextval('sec_arribo')")
	public int getSecIdArribo();
	

}
