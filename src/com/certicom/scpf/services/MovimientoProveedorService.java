package com.certicom.scpf.services;

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

}
