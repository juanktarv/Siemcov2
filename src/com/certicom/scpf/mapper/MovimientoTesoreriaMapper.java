package com.certicom.scpf.mapper;

import org.apache.ibatis.annotations.Insert;

import com.certicom.scpf.domain.MovimientoCuentaTesoreria;

public interface MovimientoTesoreriaMapper {
	

	  
	@Insert(" insert into t_movimiento_cuenta_tesoreria (id_cuenta_tesoreria,"
													  + "id_domicilio_fiscal_cab, "
													  + "id_emisor,"
													  + "id_cliente, "
													  + "id_cobranza, "
													  + "id_proveedor, "
													  + "fecha_movimiento,"
													  + "entrada,"
													  + "salida, "
													  + "tipomovimiento ) "
													  + "values ("
													  + "#{id_cuenta_tesoreria}, "
													  + "#{id_domicilio_fiscal_cab}, "
													  + "#{id_emisor}, "
													  + "#{id_cliente}, "
													  + "#{id_cobranza}, "
													  + "#{id_proveedor}, "
													  + "#{fecha_movimiento}, "
													  + "#{entrada}, "
													  + "#{salida}, "
													  + "#{tipomovimiento})")
	public void crearMovimientoTesoreria( MovimientoCuentaTesoreria movimientoCuentaTesoreria) throws Exception;

}
