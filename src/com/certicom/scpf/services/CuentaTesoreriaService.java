package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.CuentaTesoreria;
import com.certicom.scpf.mapper.CuentaTesoreriaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class CuentaTesoreriaService implements CuentaTesoreriaMapper{
	
	CuentaTesoreriaMapper cuentaTesoreriaMapper = (CuentaTesoreriaMapper)ServiceFinder.findBean("cuentaTesoreriaMapper"); 

	@Override
	public CuentaTesoreria findById(Integer id_cuenta_tesoreria) throws Exception {
		// TODO Auto-generated method stub
		return cuentaTesoreriaMapper.findById(id_cuenta_tesoreria);
	}

	@Override
	public List<CuentaTesoreria> findAll() throws Exception {
		// TODO Auto-generated method stub
		return cuentaTesoreriaMapper.findAll();
	}

	@Override
	public void crearCuentaTesoreria(CuentaTesoreria cuentaTesoreria) throws Exception {
		// TODO Auto-generated method stub
		cuentaTesoreriaMapper.crearCuentaTesoreria(cuentaTesoreria);
	}

	@Override
	public void actualizarCuentaTesoreria(CuentaTesoreria cuentaTesoreria) throws Exception {
		// TODO Auto-generated method stub
		cuentaTesoreriaMapper.actualizarCuentaTesoreria(cuentaTesoreria);
	}

	@Override
	public void eliminarCuentaTesoreria(Integer id_cuenta_tesoreria) throws Exception {
		// TODO Auto-generated method stub
		cuentaTesoreriaMapper.eliminarCuentaTesoreria(id_cuenta_tesoreria);
	}

}
