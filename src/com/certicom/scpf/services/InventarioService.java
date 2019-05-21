package com.certicom.scpf.services;
import java.math.BigDecimal;
import java.util.List;

import com.certicom.scpf.domain.Inventario;
import com.certicom.scpf.mapper.InventarioMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


/*Siemco v2.0*/

public class InventarioService implements InventarioMapper{
	
	InventarioMapper inventarioMapper =(InventarioMapper) ServiceFinder.findBean("inventarioMapper");

	@Override
	public List<Inventario> findAll() throws Exception {
		return inventarioMapper.findAll();
	}

	@Override
	public void crearInventario(Inventario inventario) throws Exception {
		inventarioMapper.crearInventario(inventario);
		
	}

	@Override
	public void actualizarInventario(Inventario inventario) throws Exception {
		inventarioMapper.actualizarInventario(inventario);
		
	}

	@Override
	public void eliminarInventario(Integer id_almacen, Integer id_producto, Integer id_emisor, Integer id_domicilio_fiscal) throws Exception {
		inventarioMapper.eliminarInventario(id_almacen, id_producto, id_emisor, id_domicilio_fiscal );
		
	}

	@Override
	public List<Inventario> listarTodosInventario() throws Exception {
		return inventarioMapper.listarTodosInventario();
	}

	@Override
	public Inventario findById(Integer codigoAlmacen, Integer codigoProducto, Integer codigoEmisor,
			Integer codigoDomicilioFiscal) throws Exception {

		return inventarioMapper.findById(codigoAlmacen, codigoProducto, codigoEmisor, codigoDomicilioFiscal);
	}

	@Override
	public BigDecimal cantidadStock(Integer codigoProducto) throws Exception {
		return inventarioMapper.cantidadStock(codigoProducto);
	}

}
