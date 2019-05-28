package com.certicom.scpf.services;

import com.certicom.scpf.domain.CobranzaDetalle;
import com.certicom.scpf.mapper.CobranzaCabeceraMapper;
import com.certicom.scpf.mapper.CobranzaDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class CobranzaDetalleService implements CobranzaDetalleMapper{

	CobranzaDetalleMapper cobranzaDetalleMapper =(CobranzaDetalleMapper) ServiceFinder.findBean("cobranzaDetalleMapper");
	
	@Override
	public void crearCobranzaDetalle(CobranzaDetalle detalle) throws Exception {
		// TODO Auto-generated method stub
		this.cobranzaDetalleMapper.crearCobranzaDetalle(detalle);
	}

}
