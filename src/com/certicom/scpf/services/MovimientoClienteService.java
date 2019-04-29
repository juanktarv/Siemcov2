package com.certicom.scpf.services;

import java.util.List;
import java.util.Map;

import com.certicom.scpf.domain.MovimientoClientes;
import com.certicom.scpf.mapper.MovimientoClienteMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class MovimientoClienteService implements MovimientoClienteMapper{

	MovimientoClienteMapper movimientoClienteMapper =(MovimientoClienteMapper) ServiceFinder.findBean("movimientoClienteMapper");
	
	public void crearMovimiento(MovimientoClientes movimiento) {
		// TODO Auto-generated method stub
		this.movimientoClienteMapper.crearMovimiento(movimiento);
	}

	public List<MovimientoClientes> listarMovimientos() {
		// TODO Auto-generated method stub
		return this.movimientoClienteMapper.listarMovimientos();
	}

	@Override
	public Integer countCompByAnioMesTipoPAGINATOR(Integer annio, Integer mes, String tipo_comprobante,
			Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return this.movimientoClienteMapper.countCompByAnioMesTipoPAGINATOR(annio,mes, tipo_comprobante, filters);
	}

	@Override
	public List<MovimientoClientes> listComprobantesByAnioMesTipoPAGINATOR(Integer annio, Integer mes,
			String tipo_comprobante, Integer first, Integer pageSize, Map<String, Object> filters, String sortField,
			String sortOrder) {
		// TODO Auto-generated method stub
		return this.movimientoClienteMapper.listComprobantesByAnioMesTipoPAGINATOR(
				annio,mes,tipo_comprobante, first, pageSize, filters, sortField, sortOrder );
		
	}

}
