package com.certicom.scpf.services;

import java.util.List;
import java.util.Map;

import com.certicom.scpf.domain.MovimientoProveedores;
import com.certicom.scpf.mapper.ComprobanteDetalleMapper;
import com.certicom.scpf.mapper.MovimientoProveedorMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class MovimientoProveedorService implements MovimientoProveedorMapper{

	MovimientoProveedorMapper movimientoProveedorMapper =(MovimientoProveedorMapper) ServiceFinder.findBean("movimientoProveedorMapper");
	
	public void crearMovimiento(MovimientoProveedores movimiento) {
		// TODO Auto-generated method stub
		this.movimientoProveedorMapper.crearMovimiento(movimiento);
	}


	public void actualizarMovimiento(MovimientoProveedores mov) {
		// TODO Auto-generated method stub
		System.out.println("ACTUALIZAR --->"+mov.getPago());
		this.movimientoProveedorMapper.actualizarMovimiento(mov);
	}

	@Override
	public Integer countCompByAnioMesTipoPAGINATOR(Integer anio, Integer mes, String tipo_comprobante,
			Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return this.movimientoProveedorMapper.countCompByAnioMesTipoPAGINATOR(anio,mes,tipo_comprobante, filters);
	}

	@Override
	public List<MovimientoProveedores> listComprobantesByAnioMesTipoPAGINATOR(Integer anio, Integer mes,
			String tipo_comprobante, Integer first, Integer pageSize, Map<String, Object> filters, String sortField,
			String sortOrder) {
		// TODO Auto-generated method stub
		return this.movimientoProveedorMapper.listComprobantesByAnioMesTipoPAGINATOR(anio,mes,tipo_comprobante,first,pageSize,filters,sortField,sortOrder);
	}

}
