package com.certicom.scpf.services;

import com.certicom.scpf.domain.MovimientoCuentaTesoreria;
import com.certicom.scpf.mapper.MovimientoTesoreriaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class MovimientoTesoreriaService implements MovimientoTesoreriaMapper {

	MovimientoTesoreriaMapper movimientoTesoreriaMapper =(MovimientoTesoreriaMapper) ServiceFinder.findBean("movimientoTesoreriaMapper");
	
	@Override
	public void crearMovimientoTesoreria(MovimientoCuentaTesoreria movimientoCuentaTesoreria) throws Exception {
		// TODO Auto-generated method stub
		this.movimientoTesoreriaMapper.crearMovimientoTesoreria(movimientoCuentaTesoreria);
	}

}
