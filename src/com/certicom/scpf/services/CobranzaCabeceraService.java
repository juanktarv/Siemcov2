package com.certicom.scpf.services;

import com.certicom.scpf.domain.CobranzaCabecera;
import com.certicom.scpf.mapper.CobranzaCabeceraMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class CobranzaCabeceraService implements CobranzaCabeceraMapper{

	CobranzaCabeceraMapper cobranzaCabeceraMapper =(CobranzaCabeceraMapper) ServiceFinder.findBean("cobranzaCabeceraMapper");
	
	@Override
	public int crearCobranzaCabecera(CobranzaCabecera cobranza) throws Exception {
		// TODO Auto-generated method stub
		return this.cobranzaCabeceraMapper.crearCobranzaCabecera(cobranza);
	}

}
