package com.certicom.scpf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.certicom.scpf.domain.PagoCabecera;



public interface PagoCabeceraMapper {


	  
	@Insert("insert into t_pago_cabecera (id_proveedor, "
			+ "id_cuenta_tesoreria, "
			+ "id_domicilio_fiscal_cab, "
			+ "id_emisor, "
			+ "pago_total, "
			+ "fecha_pago ) values "
			+ "(#{id_proveedor}, "
			+ "#{id_cuenta_tesoreria}, "
			+ "#{id_domicilio_fiscal_cab}, "
			+ "#{id_emisor}, "
			+ "#{pago_total}, "
			+ "#{fecha_pago})")
@SelectKey(statement="SELECT nextval('sec_cobranza')", keyProperty="id_pago", before=true, resultType=int.class)
public int crearPagoCabecera(PagoCabecera pago)throws Exception;
}
