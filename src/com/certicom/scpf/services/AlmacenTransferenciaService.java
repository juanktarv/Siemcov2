package com.certicom.scpf.services;

import java.util.Date;
import java.util.List;

import com.certicom.scpf.domain.AlmacenTransferencia;
import com.certicom.scpf.mapper.AlmacenTransferenciaMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class AlmacenTransferenciaService implements AlmacenTransferenciaMapper{
	
	
	AlmacenTransferenciaMapper almacenTransferenciaMapper =(AlmacenTransferenciaMapper) ServiceFinder.findBean("almacenTransferenciaMapper");

	@Override
	public AlmacenTransferencia findById(Integer id_almacen_origen, Integer id_almacen_destino, Integer id_producto,
			Integer id_emisor, Integer id_domicilio_fiscal, Date fecha_transferencia, Integer id_almacen_transferencia) throws Exception {
		
		return almacenTransferenciaMapper.findById(id_almacen_origen, id_almacen_destino, id_producto, id_emisor, id_domicilio_fiscal, fecha_transferencia, id_almacen_transferencia);
	}

	@Override
	public List<AlmacenTransferencia> findAll() throws Exception {
		
		return almacenTransferenciaMapper.findAll();
	}

	@Override
	public void crearAlmacenTransferencia(AlmacenTransferencia almacenTransferencia) throws Exception {
		almacenTransferenciaMapper.crearAlmacenTransferencia(almacenTransferencia);
		
	}

	@Override
	public void actualizarAlmacenTransferencia(AlmacenTransferencia almacenTransferencia) throws Exception {
		almacenTransferenciaMapper.actualizarAlmacenTransferencia(almacenTransferencia);
		
	}

	@Override
	public void eliminarAlmacenTransferencia(Integer id_almacen_origen, Integer id_almacen_destino, Integer id_producto,
			Integer id_emisor, Integer id_domicilio_fiscal, Date fecha_transferencia , Integer id_almacen_transferencia) throws Exception {
           almacenTransferenciaMapper.eliminarAlmacenTransferencia(id_almacen_origen, id_almacen_destino, id_producto, id_emisor, id_domicilio_fiscal, fecha_transferencia, id_almacen_transferencia);
		
	}

	@Override
	public List<AlmacenTransferencia> listarTodos() throws Exception {
		// TODO Auto-generated method stub
		return almacenTransferenciaMapper.listarTodos();
	}

	@Override
	public int getSecIdAlmacenTransferencia() {
		return almacenTransferenciaMapper.getSecIdAlmacenTransferencia();
	}

}
