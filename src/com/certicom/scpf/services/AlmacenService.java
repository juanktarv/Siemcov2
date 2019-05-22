package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Almacen;
import com.certicom.scpf.mapper.AlmacenMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

/*Siemco v2.0*/

public class AlmacenService implements AlmacenMapper{
	
	AlmacenMapper almacenMapper =(AlmacenMapper) ServiceFinder.findBean("almacenMapper");

	@Override
	public Almacen findById(Integer codigoAlmacen) throws Exception {
		return almacenMapper.findById(codigoAlmacen);
	}

	@Override
	public List<Almacen> findAll() throws Exception {
		return almacenMapper.findAll();
	}

	@Override
	public void crearAlmacen(Almacen almacen) throws Exception {
		almacenMapper.crearAlmacen(almacen);
		
	}

	@Override
	public void actualizarAlmacen(Almacen almacen) throws Exception {
		almacenMapper.actualizarAlmacen(almacen);
		
	}

	@Override
	public void eliminarAlmacen(Integer id_almacen) throws Exception {
		almacenMapper.eliminarAlmacen(id_almacen);
		
	}

	@Override
	public List<Almacen> listarPorProducto(Integer id_producto, Integer id_emisor, Integer id_domicilio_fiscal)
			throws Exception {
		return almacenMapper.listarPorProducto(id_producto, id_emisor, id_domicilio_fiscal);
	}

	@Override
	public List<Almacen> listarPorProductoAnidado(Integer id_producto, Integer id_emisor, Integer id_domicilio_fiscal,
			Integer id_almacen) throws Exception {
		
		return almacenMapper.listarPorProductoAnidado(id_producto, id_emisor, id_domicilio_fiscal, id_almacen);
	}

	@Override
	public List<Almacen> listarAlmacenDestino(Integer id_emisor, Integer id_domicilio_fiscal, Integer id_almacen) {
		// TODO Auto-generated method stub
		return this.almacenMapper.listarAlmacenDestino(id_emisor, id_domicilio_fiscal, id_almacen);
	}

}
