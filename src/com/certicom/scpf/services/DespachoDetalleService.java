package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.DespachoDetalle;
import com.certicom.scpf.mapper.DespachoDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

/*Siemco v2.0*/
public class DespachoDetalleService implements DespachoDetalleMapper{
	
	DespachoDetalleMapper despachoDetalleMapper =(DespachoDetalleMapper) ServiceFinder.findBean("despachoDetalleMapper");

	@Override
	public DespachoDetalle findById(Integer codigoDespacho, Integer codigoComprobante, String tipoComprobante,
			Integer codigoCliente, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal,
			Integer codigoAlmacen) throws Exception {
		return despachoDetalleMapper.findById(codigoDespacho, codigoComprobante, tipoComprobante, codigoCliente, codigoProducto, codigoEmisor, codigoDomicilioFiscal, codigoAlmacen);
	}

	@Override
	public List<DespachoDetalle> findAll() throws Exception {
		return despachoDetalleMapper.findAll();
	}

	@Override
	public void crearDespachoDetalle(DespachoDetalle despachoDetalle) throws Exception {
		despachoDetalleMapper.crearDespachoDetalle(despachoDetalle);
	}

	@Override
	public void actualizarDespachoDetalle(DespachoDetalle despachoDetalle) throws Exception {
		despachoDetalleMapper.actualizarDespachoDetalle(despachoDetalle);
		
	}

	@Override
	public void eliminarDespachoDetalle(Integer codigoDespacho, Integer codigoComprobante, String tipoComprobante,
			Integer codigoCliente, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal,
			Integer codigoAlmacen) throws Exception {
		
		despachoDetalleMapper.eliminarDespachoDetalle(codigoDespacho, codigoComprobante, tipoComprobante, codigoCliente, codigoProducto, codigoEmisor, codigoDomicilioFiscal, codigoAlmacen);
	}

}
