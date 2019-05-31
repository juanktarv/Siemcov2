package com.certicom.scpf.services;

import com.certicom.scpf.domain.PagoCabecera;
import com.certicom.scpf.mapper.PagoCabeceraMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class PagoCabeceraService implements PagoCabeceraMapper{

	PagoCabeceraMapper pagoCabeceraMapper =(PagoCabeceraMapper) ServiceFinder.findBean("pagoCabeceraMapper");
	
	
	@Override
	public int crearPagoCabecera(PagoCabecera pago) throws Exception {
		// TODO Auto-generated method stub
		return pagoCabeceraMapper.crearPagoCabecera(pago);
	}

}
