package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.CuentaTesoreria;

public interface CuentaTesoreriaMapper {

	@Select("select * from t_cuenta_tesoreria e where e.id_cuenta_tesoreria = #{p_id_cuenta_tesoreria}")
	public CuentaTesoreria findById(@Param("p_id_cuenta_tesoreria") Integer id_cuenta_tesoreria) throws Exception;
	
	
	public List<CuentaTesoreria> findAll() throws Exception;
	

	@Insert("insert into t_cuenta_tesoreria (id_domicilio_fiscal_cab, id_emisor, banco, cuenta, "
											+ "tipo_moneda, tipo_cuenta, saldo, fecha_saldo) "
								  + "values ( #{id_domicilio_fiscal_cab}, #{id_emisor}, #{banco}, #{cuenta}, "
								            + "#{tipo_moneda}, #{tipo_cuenta}, #{saldo}, #{fecha_saldo})")
	public void crearCuentaTesoreria(CuentaTesoreria cuentaTesoreria) throws Exception;
	

	@Update("update t_cuenta_tesoreria set  id_domicilio_fiscal_cab = #{id_domicilio_fiscal_cab}, "
			+ "id_emisor = #{id_emisor}, "
			+ "banco = #{banco}, "
			+ "cuenta = #{cuenta}, "
			+ "tipo_moneda = #{tipo_moneda}, "
			+ "tipo_cuenta = #{tipo_cuenta}, "
			+ "saldo = #{saldo}, "
			+ "fecha_saldo = #{fecha_saldo} "
			+ "where id_cuenta_tesoreria= #{id_cuenta_tesoreria}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCuentaTesoreria(CuentaTesoreria cuentaTesoreria) throws Exception;
	
	@Delete("delete from t_cuenta_tesoreria  where id_cuenta_tesoreria = #{id_cuenta_tesoreria}")
	@Options(flushCache=true)
	public void eliminarCuentaTesoreria(@Param("id_cuenta_tesoreria") Integer id_cuenta_tesoreria) throws Exception;
}
