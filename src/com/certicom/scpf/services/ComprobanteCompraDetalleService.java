package com.certicom.scpf.services;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ComprobanteCompraDetalle;
import com.certicom.scpf.mapper.ComprobanteCompraDetalleMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ComprobanteCompraDetalleService implements ComprobanteCompraDetalleMapper{

	ComprobanteCompraDetalleMapper comprobanteDetalleMapper =(ComprobanteCompraDetalleMapper) ServiceFinder.findBean("comprobanteCompraDetalleMapper");

	
	
	public void insertBatchComprobanteDetalle(List<ComprobanteCompraDetalle> cDetalle, Integer id_comprobante) throws Exception {
		// TODO Auto-generated method stub
		Boolean resultado=Boolean.FALSE;
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ComprobanteCompraDetalleMapper daoObj= (ComprobanteCompraDetalleMapper)sqlSession.getMapper(ComprobanteCompraDetalleMapper.class);
		
		System.out.println("id_comprobante==> "+id_comprobante+" Longitud: "+cDetalle.size());
		for (ComprobanteCompraDetalle fpd:cDetalle) {
			fpd.setId_comprobante_compra(id_comprobante);
			//System.out.println("MODO PGO :"+fpd.getId_modo_pago());
			this.comprobanteDetalleMapper.crearComprobanteCompraDetalle(fpd);
			
		}
		sqlSession.close();
//		
//		try{
//		
//			//sqlSession.commit(true);
//			for (ComprobanteDetalle fpd:cDetalle) {
//				fpd.setId_comprobante(id_comprobante);
//				daoObj.crearComprobanteDetalle(fpd);
//				
//			}
//			resultado=Boolean.TRUE;
//			//sqlSession.commit();
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			resultado=Boolean.FALSE;
//		}finally{
//			sqlSession.close();
//		}
		
		//return resultado;
		//facturacionPlanillaDetalleMapper.eliminarFacturacionPlanillaDetalle(codFacturacionPlanillaDetalle);
	}



	@Override
	public ComprobanteCompraDetalle findById(Integer codigoCliente)
			throws Exception {
		// TODO Auto-generated method stub
		return comprobanteDetalleMapper.findById(codigoCliente);
	}



	@Override
	public List<ComprobanteCompraDetalle> findAll() throws Exception {
		// TODO Auto-generated method stub
		return comprobanteDetalleMapper.findAll();
	}



	@Override
	public void crearComprobanteCompraDetalle(
			ComprobanteCompraDetalle comprobanteDetalle) throws Exception {
		// TODO Auto-generated method stub
		comprobanteDetalleMapper.crearComprobanteCompraDetalle(comprobanteDetalle);
	}



	@Override
	public void actualizarComprobanteCompraDetalle(
			ComprobanteCompraDetalle comprobanteDetalle) throws Exception {
		// TODO Auto-generated method stub
		comprobanteDetalleMapper.actualizarComprobanteCompraDetalle(comprobanteDetalle);
	}



	@Override
	public void eliminarComprobanteCompraDetalle(Integer id_comprobante)
			throws Exception {
		// TODO Auto-generated method stub
		comprobanteDetalleMapper.eliminarComprobanteCompraDetalle(id_comprobante);
	}



	@Override
	public List<ComprobanteCompraDetalle> findByIdComprobanteCompra(
			Integer id_comprobante) throws Exception {
		// TODO Auto-generated method stub
		return comprobanteDetalleMapper.findByIdComprobanteCompra(id_comprobante);
	}





	
}
