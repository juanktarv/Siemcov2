package com.certicom.scpf.mapper;

import org.apache.ibatis.annotations.Insert;

import com.certicom.scpf.domain.CobranzaDetalle;

public interface CobranzaDetalleMapper {


	@Insert("insert into t_cobranza_detalle (id_comprobante, "
										  + "id_cliente, "
										  + "tipo_comprobante, "
										  + "id_modo_pago, "
										  + "id_emisor, "
										  + "id_cuenta_tesoreria, "
										  + "id_domicilio_fiscal_cab, "
										  + "id_cobranza, "
										  + "importe_cobrado, "
										  + "fecha_cobranza ) values ("
										  + "#{id_comprobante}, "
										  + "#{id_cliente}, "
										  + "#{tipo_comprobante}, "
										  + "#{id_modo_pago}, "
										  + "#{id_emisor}, "
										  + "#{id_cuenta_tesoreria}, "
										  + "#{id_domicilio_fiscal_cab}, "
										  + "#{id_cobranza}, "
										  + "#{importe_cobrado}, "
										  + "#{fecha_cobranza})")
	public void crearCobranzaDetalle(CobranzaDetalle detalle) throws Exception;

}
