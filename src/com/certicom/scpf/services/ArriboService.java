package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Arribo;
import com.certicom.scpf.mapper.ArriboMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


/*Siemco v2.0*/
public class ArriboService implements ArriboMapper{
	
	ArriboMapper arriboMapper =(ArriboMapper) ServiceFinder.findBean("arriboMapper");

	@Override
	public Arribo findById(Integer codigoArribo) throws Exception {
		return arriboMapper.findById(codigoArribo);
	}

	@Override
	public List<Arribo> findAll() throws Exception {
		
		return arriboMapper.findAll();
	}

	@Override
	public void crearArribo(Arribo arribo) throws Exception {
		arriboMapper.crearArribo(arribo);
		
	}

	@Override
	public void actualizarArribo(Arribo arribo) throws Exception {
		arriboMapper.actualizarArribo(arribo);
		
	}

	@Override
	public void eliminarArribo(Integer id_arribo) throws Exception {
		arriboMapper.eliminarArribo(id_arribo);
		
	}

	@Override
	public int getSecIdArribo() {
		
		return arriboMapper.getSecIdArribo();
	}


	
	
}
