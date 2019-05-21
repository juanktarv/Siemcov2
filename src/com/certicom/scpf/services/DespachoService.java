package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Despacho;
import com.certicom.scpf.mapper.DespachoMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


/*Siemco v2.0*/
public class DespachoService implements DespachoMapper{
	
	
	DespachoMapper despachoMapper =(DespachoMapper) ServiceFinder.findBean("despachoMapper");
	
	

	@Override
	public Despacho findById(Integer codigoDespacho) throws Exception {
		
		return despachoMapper.findById(codigoDespacho);
	}

	@Override
	public List<Despacho> findAll() throws Exception {
		
		return despachoMapper.findAll();
	}

	@Override
	public void crearDespacho(Despacho despacho) throws Exception {
		
		despachoMapper.crearDespacho(despacho);
	}

	@Override
	public void actualizarDespacho(Despacho despacho) throws Exception {
		
		despachoMapper.actualizarDespacho(despacho);
	}

	@Override
	public void eliminarDespacho(Integer id_despacho) throws Exception {
		
		despachoMapper.eliminarDespacho(id_despacho);
	}

	@Override
	public int getSecIdDespacho() {
		return despachoMapper.getSecIdDespacho();
	}



}
