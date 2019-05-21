package com.certicom.scpf.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ComprobanteCompraDetalle;
import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.Emisor;
import com.certicom.scpf.domain.MovimientoInventarioProveedores;
import com.certicom.scpf.mapper.ComprobanteDetalleMapper;
import com.certicom.scpf.mapper.MovInventarioProveedorMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;


/*Siemco v2.0*/
public class MovimientoInventarioProveedorService implements MovInventarioProveedorMapper{
	
	MovInventarioProveedorMapper movimientoInventarioProveedorMapper =(MovInventarioProveedorMapper) ServiceFinder.findBean("movimientoInventarioProveedorMapper");

	@Override
	public MovimientoInventarioProveedores findById(Integer codigoComprobante, String tipoComprobante,
			Integer codigoProveedores, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal)
			throws Exception {

		return movimientoInventarioProveedorMapper.findById(codigoComprobante, tipoComprobante, codigoProveedores, codigoProducto, codigoEmisor, codigoDomicilioFiscal);
	}

	@Override
	public List<MovimientoInventarioProveedores> findAll() throws Exception {
		
		return movimientoInventarioProveedorMapper.findAll();
	}

	@Override
	public void crearMovimientoInventarioProveedores(MovimientoInventarioProveedores movimientoInventarioProveedores) throws Exception {
		movimientoInventarioProveedorMapper.crearMovimientoInventarioProveedores(movimientoInventarioProveedores);
		
	}

	@Override
	public void actualizarMovimientoInventarioProveedores(MovimientoInventarioProveedores movimientoInventarioProveedores) throws Exception {
		movimientoInventarioProveedorMapper.actualizarMovimientoInventarioProveedores(movimientoInventarioProveedores);
		
	}

	@Override
	public void eliminarMovimientoInventarioProveedores(Integer codigoComprobante, String tipoComprobante,
			Integer codigoProveedores, Integer codigoProducto, Integer codigoEmisor, Integer codigoDomicilioFiscal)
			throws Exception {
		movimientoInventarioProveedorMapper.eliminarMovimientoInventarioProveedores(codigoComprobante, tipoComprobante, codigoProveedores, codigoProducto, codigoEmisor, codigoDomicilioFiscal);
		
	}
	
	public void insertBatchMovimientoInventarioProveedor(List<ComprobanteCompraDetalle> cDetalle, Integer id_comprobante) throws Exception {
			
			Boolean resultado=Boolean.FALSE;
			SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
			SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
			ComprobanteDetalleMapper daoObj= (ComprobanteDetalleMapper)sqlSession.getMapper(ComprobanteDetalleMapper.class);
			
			Emisor emisorSelec = new Emisor();
			EmisorService emisorService = new EmisorService();
			emisorSelec = emisorService.findAll().get(0);
			
			System.out.println("id_comprobante==> "+id_comprobante+" Longitud: "+cDetalle.size());
			for (ComprobanteCompraDetalle fpd:cDetalle) {
				fpd.setId_comprobante_compra(id_comprobante);
				MovimientoInventarioProveedores movimientoInventarioProveedores = new MovimientoInventarioProveedores();
				
				movimientoInventarioProveedores.setId_comprobante_compra(fpd.getId_comprobante_compra());
				movimientoInventarioProveedores.setId_proveedores(fpd.getId_proveedor());
				movimientoInventarioProveedores.setTipo_comprobante(fpd.getTipo_comprobante());
				movimientoInventarioProveedores.setId_producto(fpd.getId_producto());
				movimientoInventarioProveedores.setId_emisor(emisorSelec.getId_emisor());
				movimientoInventarioProveedores.setId_domicilio_fiscal(emisorSelec.getId_domicilio_fiscal_cab());
			//	movimientoInventarioProveedores.setId_modo_pago(fpd.getId_modo_pago());
			//	movimientoInventarioProveedores.setId_tipo_producto(fpd.getId_tipo_producto());
			//	movimientoInventarioProveedores.getNumero_serie_documento(fpd.getNumeroSerie()); 
			//	movimientoInventarioProveedores.setTipo_unidad_medida(fpd.getTipo_unidad_medida_det().toString()); esta viniendo null
				movimientoInventarioProveedores.setCant_unidades_item(fpd.getCant_unidades_item_det());
				movimientoInventarioProveedores.setSaldo_arribo(fpd.getCant_unidades_item_det());
				movimientoInventarioProveedores.setFlag_regularizado(Boolean.FALSE);
			
				
				
				this.movimientoInventarioProveedorMapper.crearMovimientoInventarioProveedores(movimientoInventarioProveedores);
				
			}
			sqlSession.close();
	
		}

	@Override
	public List<MovimientoInventarioProveedores> listarMovimientosInventarioProveedores() throws Exception {
		
		return this.movimientoInventarioProveedorMapper.listarMovimientosInventarioProveedores();
	}

}
