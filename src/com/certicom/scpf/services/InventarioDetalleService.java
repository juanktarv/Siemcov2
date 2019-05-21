package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.InventarioDetalle;
import com.certicom.scpf.mapper.InventarioDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


/*Siemco v2.0*/
public class InventarioDetalleService implements InventarioDetalleMapper{
	
	InventarioDetalleMapper inventarioDetalleMapper =(InventarioDetalleMapper) ServiceFinder.findBean("inventarioDetalleMapper");

	@Override
	public InventarioDetalle findById(Integer codigoAlmacen, Integer codigoDespacho, Integer codigoArribo,
			Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal, Integer codigoAlmacenTransferencia) throws Exception {
		
		return inventarioDetalleMapper.findById(codigoAlmacen, codigoDespacho, codigoArribo, codigoProducto, codigoEmisor, codigoDomicilioFiscal, codigoAlmacenTransferencia);
	}

	@Override
	public List<InventarioDetalle> findAll() throws Exception {
	
		return inventarioDetalleMapper.findAll();
	}

	@Override
	public void crearInventarioDetalle(InventarioDetalle inventarioDetalle) throws Exception {
		
		inventarioDetalleMapper.crearInventarioDetalle(inventarioDetalle);
	}

	@Override
	public void actualizarInventarioDetalle(InventarioDetalle inventarioDetalle) throws Exception {
		
		inventarioDetalleMapper.actualizarInventarioDetalle(inventarioDetalle);
	}

	@Override
	public void eliminarInventarioDetalle(Integer codigoAlmacen, Integer codigoDespacho, Integer codigoArribo,
			Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal, Integer codigoAlmacenTransferencia) throws Exception {
		
		inventarioDetalleMapper.eliminarInventarioDetalle(codigoAlmacen, codigoDespacho, codigoArribo, codigoProducto, codigoEmisor, codigoDomicilioFiscal, codigoAlmacenTransferencia);
		
	}

	@Override
	public List<InventarioDetalle> buscarPorInventario(Integer codigoProducto, Integer codigoAlmacen,
			Integer codigoEmisor, Integer codigoDomicilioFiscal) throws Exception {
		
		return inventarioDetalleMapper.buscarPorInventario(codigoProducto, codigoAlmacen, codigoEmisor, codigoDomicilioFiscal);
	}



}
