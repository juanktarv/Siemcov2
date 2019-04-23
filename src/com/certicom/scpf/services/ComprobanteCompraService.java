package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.ComprobanteCompra;
import com.certicom.scpf.mapper.ComprobanteCompraMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ComprobanteCompraService implements ComprobanteCompraMapper{

	ComprobanteCompraMapper comprobanteCompraMapper =(ComprobanteCompraMapper) ServiceFinder.findBean("comprobanteCompraMapper");

	@Override
	public List<ComprobanteCompra> findAll() throws Exception {
		// TODO Auto-generated method stub
		return comprobanteCompraMapper.findAll();
	}

	@Override
	public void crearComprobanteCompra(ComprobanteCompra comprobanteCompra)
			throws Exception {
		// TODO Auto-generated method stub
		comprobanteCompraMapper.crearComprobanteCompra(comprobanteCompra);
	}

	@Override
	public void actualizarComprobanteCompra(ComprobanteCompra comprobanteCompra)
			throws Exception {
		// TODO Auto-generated method stub
		comprobanteCompraMapper.actualizarComprobanteCompra(comprobanteCompra);
	}

	@Override
	public void eliminarComprobanteCompra(Integer id_comprobante_compra)
			throws Exception {
		// TODO Auto-generated method stub
		comprobanteCompraMapper.eliminarComprobanteCompra(id_comprobante_compra);
	}

	@Override
	public int getCorrelativoComprobante(String tipo_comprobante)
			throws Exception {
		// TODO Auto-generated method stub
		return comprobanteCompraMapper.getCorrelativoComprobante(tipo_comprobante);
	}

	
	
}
