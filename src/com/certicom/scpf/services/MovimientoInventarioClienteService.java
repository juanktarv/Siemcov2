package com.certicom.scpf.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.MovimientoInventarioClientes;
import com.certicom.scpf.mapper.ComprobanteDetalleMapper;
import com.certicom.scpf.mapper.MovInventarioClienteMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


public class MovimientoInventarioClienteService implements MovInventarioClienteMapper{
	
	MovInventarioClienteMapper movimientoInventarioClienteMapper =(MovInventarioClienteMapper) ServiceFinder.findBean("movimientoInventarioClienteMapper");


	@Override
	public MovimientoInventarioClientes findById(Integer codigoComprobante, String tipoComprobante,
			Integer codigoCliente, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal)
			throws Exception {
		return movimientoInventarioClienteMapper.findById(codigoComprobante, tipoComprobante, codigoCliente, codigoProducto, codigoEmisor, codigoDomicilioFiscal);

	}

	@Override
	public List<MovimientoInventarioClientes> findAll() throws Exception {
		return movimientoInventarioClienteMapper.findAll();
	}

	@Override
	public void crearMovimientoInventarioClientes(MovimientoInventarioClientes movimientoInventarioClientes)
			throws Exception {
		 movimientoInventarioClienteMapper.crearMovimientoInventarioClientes(movimientoInventarioClientes);
	}

	@Override
	public void actualizarMovimientoInventarioClientes(MovimientoInventarioClientes movimientoInventarioClientes)
			throws Exception {
		  movimientoInventarioClienteMapper.actualizarMovimientoInventarioClientes(movimientoInventarioClientes);
	}

	@Override
	public void eliminarMovimientoInventarioClientes(Integer codigoComprobante, String tipoComprobante,
			Integer codigoCliente, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal)
			throws Exception {
		movimientoInventarioClienteMapper.eliminarMovimientoInventarioClientes(codigoComprobante, tipoComprobante, codigoCliente, codigoProducto, codigoEmisor, codigoDomicilioFiscal);
	}
	
	public void insertBatchMovimientoInventarioCliente(List<ComprobanteDetalle> cDetalle, Integer id_comprobante) throws Exception {
		
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ComprobanteDetalleMapper daoObj= (ComprobanteDetalleMapper)sqlSession.getMapper(ComprobanteDetalleMapper.class);
		
		System.out.println("id_comprobante==> "+id_comprobante+" Longitud: "+cDetalle.size());
		for (ComprobanteDetalle fpd:cDetalle) {
			fpd.setId_comprobante(id_comprobante);
			MovimientoInventarioClientes movimientoInventarioClientes = new MovimientoInventarioClientes();
			
			movimientoInventarioClientes.setId_comprobante(fpd.getId_comprobante());
			movimientoInventarioClientes.setId_cliente(fpd.getId_cliente());
			movimientoInventarioClientes.setTipo_comprobante(fpd.getTipo_comprobante());
			movimientoInventarioClientes.setId_producto(fpd.getId_producto());
			movimientoInventarioClientes.setId_emisor(fpd.getId_emisor());
			movimientoInventarioClientes.setId_domicilio_fiscal(fpd.getId_domicilio_fiscal_cab());
			movimientoInventarioClientes.setId_modo_pago(fpd.getId_modo_pago());
		//	movimientoInventarioClientes.setId_tipo_producto(fpd.getId_tipo_producto());
		//	movimientoInventarioClientes.getNumero_serie_documento(fpd.getNumeroSerie()); 
		//	movimientoInventarioClientes.setTipo_unidad_medida(fpd.getTipo_unidad_medida_det().toString()); esta viniendo null
			movimientoInventarioClientes.setCant_unidades_item(fpd.getCant_unidades_item_det());
			movimientoInventarioClientes.setSaldo_despacho(fpd.getCant_unidades_item_det());
			movimientoInventarioClientes.setFlag_regularizado(Boolean.FALSE);
		
			
			
			this.movimientoInventarioClienteMapper.crearMovimientoInventarioClientes(movimientoInventarioClientes);
			
		}
		sqlSession.close();

	}

	@Override
	public List<MovimientoInventarioClientes> listarMovimientosInventarioClientes() throws Exception {

		return movimientoInventarioClienteMapper.listarMovimientosInventarioClientes();
	}


}
