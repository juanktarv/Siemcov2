package com.certicom.scpf.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.certicom.scpf.domain.MovimientoClientes;

public interface MovimientoClienteMapper {
	
@Insert("insert into t_movimiento_clientes "
		+ "(id_comprobante, id_cliente, tipo_comprobante, id_modo_pago, "
		+ "id_emisor, fecha_movimiento,fecha_vencimiento, tipo_documento, "
		+ "importe, nroserie_documento, flag_regularizado, formapago, saldopagado) values("
		+ "#{id_comprobante},#{id_cliente}, #{tipo_comprobante}, #{id_modo_pago}, "
		+ "#{id_emisor}, #{fecha_movimiento}, #{fecha_vencimiento}, #{tipo_documento}, "
		+ "#{importe}, #{nroserie_documento}, #{flag_regularizado}, #{formapago}, #{saldopagado})")
	public void crearMovimiento(MovimientoClientes movimiento);

	@Select(" select * from t_movimiento_clientes")
    public List<MovimientoClientes> listarMovimientos();	

	public List<MovimientoClientes> listComprobantesByAnioMesTipoPAGINATOR(
			@Param("anio") Integer annio, @Param("mes") Integer mes,@Param("tipo_comprobante") String tipo_comprobante, 
			@Param("first") Integer  first, @Param("pageSize") Integer pageSize,  @Param("filters") Map<String,Object> filters, 
			@Param("sortField") String sortField, @Param("sortOrder") String sortOrder);

	public Integer countCompByAnioMesTipoPAGINATOR(@Param("anio") Integer annio, @Param("mes") Integer mes,
			   @Param("tipo_comprobante") String tipo_comprobante, 
			   @Param("filters") Map<String,Object> filters);

}
