package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.ArriboDetalle;
import com.certicom.scpf.mapper.ArriboDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


/*Siemco v2.0*/
public class ArriboDetalleService implements ArriboDetalleMapper{
	
	ArriboDetalleMapper arriboDetalleMapper =(ArriboDetalleMapper) ServiceFinder.findBean("arriboDetalleMapper");


	@Override
	public ArriboDetalle findById(Integer codigoArribo, Integer codigoComprobanteCompra, String tipoComprobante,
			Integer codigoProveedor, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal,
			Integer codigoAlmacen) throws Exception {

		return arriboDetalleMapper.findById(codigoArribo, codigoComprobanteCompra, tipoComprobante, codigoProveedor, codigoProducto, codigoEmisor, codigoDomicilioFiscal, codigoAlmacen);
	}

	@Override
	public List<ArriboDetalle> findAll() throws Exception {

		return arriboDetalleMapper.findAll();
	}

	@Override
	public void crearArriboDetalle(ArriboDetalle arriboDetalle) throws Exception {
		  arriboDetalleMapper.crearArriboDetalle(arriboDetalle);
		
	}

	@Override
	public void actualizarArriboDetalle(ArriboDetalle arriboDetalle) throws Exception {
		arriboDetalleMapper.actualizarArriboDetalle(arriboDetalle);
		
	}

	@Override
	public void eliminarArriboDetalle(Integer codigoArribo, Integer codigoComprobanteCompra, String tipoComprobante,
			Integer codigoProveedor, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal,
			Integer codigoAlmacen) throws Exception {
		arriboDetalleMapper.eliminarArriboDetalle(codigoArribo, codigoComprobanteCompra, tipoComprobante, codigoProveedor, codigoProducto, codigoEmisor, codigoDomicilioFiscal, codigoAlmacen);
		
	}

}
