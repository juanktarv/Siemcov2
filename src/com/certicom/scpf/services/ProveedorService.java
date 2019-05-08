package com.certicom.scpf.services;

import java.util.List;

import com.certicom.scpf.domain.Proveedores;
import com.certicom.scpf.mapper.ProveedorMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ProveedorService implements ProveedorMapper{

	ProveedorMapper proveedorMapper =(ProveedorMapper) ServiceFinder.findBean("proveedorMapper");

	@Override
	public List<Proveedores> findAll() throws Exception {
		// TODO Auto-generated method stub
		return proveedorMapper.findAll();
	}

	@Override
	public void crearProveedor(Proveedores proveedor) throws Exception {
		// TODO Auto-generated method stub
		proveedorMapper.crearProveedor(proveedor);
	}

	@Override
	public void actualizarProveedor(Proveedores proveedor) throws Exception {
		// TODO Auto-generated method stub
		proveedorMapper.actualizarProveedor(proveedor);
	}

	@Override
	public void eliminarProveedor(Integer id_proveedor) throws Exception {
		// TODO Auto-generated method stub
		proveedorMapper.eliminarProveedor(id_proveedor);
	}

	@Override
	public List<Proveedores> buscarProveedorPorRazonSocialDocumento(Proveedores proveedor) {
		// TODO Auto-generated method stub
		return proveedorMapper.buscarProveedorPorRazonSocialDocumento(proveedor);
	}

	public List<Proveedores> buscarMovimientosPorProveedor(Proveedores proveedor) {
		// TODO Auto-generated method stub
		return proveedorMapper.buscarMovimientosPorProveedor(proveedor);
	}

	
	
}
