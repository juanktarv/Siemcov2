package com.certicom.scpf.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import com.certicom.scpf.domain.CobranzaCabecera;

public interface CobranzaCabeceraMapper {

	  
	@Insert("insert into t_cobranza_cabecera (id_cliente,"
											+ "id_cuenta_tesoreria, "
											+ "id_domicilio_fiscal_cab, "
											+ "id_emisor, "
											+ "total_importe_cobrado, "
											+ "fecha_cobranza ) values "
											+ "(#{id_cliente}, "
											+ "#{id_cuenta_tesoreria}, "
											+ "#{id_domicilio_fiscal_cab}, "
											+ "#{id_emisor}, "
											+ "#{total_importe_cobrado}, "
											+ "#{fecha_cobranza})")
	@SelectKey(statement="SELECT nextval('sec_cobranza')", keyProperty="id_cobranza", before=true, resultType=int.class)
	public int crearCobranzaCabecera(CobranzaCabecera cobranza)throws Exception;
}
