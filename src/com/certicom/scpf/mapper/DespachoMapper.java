package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Despacho;

/*Siemco v2.0*/
public interface DespachoMapper {
	
	@Select("select * from t_despacho e where e.id_despacho = #{p_despacho}")
	public Despacho findById(@Param("p_despacho") Integer codigoDespacho) throws Exception;
	
	@Select("select * from t_despacho order by id_despacho asc")
	public List<Despacho> findAll() throws Exception;
	
	@Insert("insert into t_despacho (id_producto, fecha_movimiento, cantidad_total_despacho) "
			+ "values (#{id_producto}, #{fecha_movimiento}, #{cantidad_total_despacho})")
	public void crearDespacho(Despacho despacho) throws Exception;
	
	
	@Update("update t_despacho set id_producto = #{id_producto}, fecha_movimiento = #{fecha_movimiento}, cantidad_total_despacho = #{cantidad_total_despacho} "
			+ " where id_despacho= #{id_despacho}")
	@Options(flushCache=true,useCache=true)
    public void actualizarDespacho(Despacho despacho) throws Exception;
	
	@Delete("delete from t_despacho  where id_despacho = #{id_despacho}")
	@Options(flushCache=true)
	public void eliminarDespacho(@Param("id_despacho") Integer id_despacho) throws Exception;
	
	@Select("SELECT nextval('sec_despacho')")
	public int getSecIdDespacho();
	

}
