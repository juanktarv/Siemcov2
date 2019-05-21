package com.certicom.scpf.services;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.certicom.scpf.domain.ComprobanteDetalle;
import com.certicom.scpf.domain.Producto;
import com.certicom.scpf.mapper.ComprobanteDetalleMapper;
import com.certicom.scpf.mapper.ProductoMapper;
import com.pe.certicom.scpf.commons.ServiceFinder;

public class ProductoService implements ProductoMapper{

	ProductoMapper productoMapper =(ProductoMapper) ServiceFinder.findBean("productoMapper");

	@Override
	public Producto findById(Integer codigoProducto) throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findById(codigoProducto);
	}

	@Override
	public List<Producto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return productoMapper.findAll();
	}

	@Override
	public void crearProducto(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		productoMapper.crearProducto(producto);
	}

	@Override
	public void actualizarProducto(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		productoMapper.actualizarProducto(producto);
	}

	@Override
	public void eliminarProducto(Integer id_producto) throws Exception {
		// TODO Auto-generated method stub
		productoMapper.eliminarProducto(id_producto);
	}
	
	/*Actualizar stock*/
	public void actualizarBatchStockProducto(List<ComprobanteDetalle> cDetalle) throws Exception {

		Boolean resultado=Boolean.FALSE;
		System.out.println("entra en actualizarBatchStockProducto");
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ProductoMapper daoObj= (ProductoMapper)sqlSession.getMapper(ProductoMapper.class);
		
		for (ComprobanteDetalle fpd:cDetalle) {
		 	
			if(fpd.getProducto().getTipo_articulo().equals("Producto")){
				BigDecimal nuevoStock = fpd.getProducto().getStock().subtract(fpd.getCant_unidades_item_det()); 
				fpd.getProducto().setStock(nuevoStock);
				this.productoMapper.actualizarStockProducto(fpd.getProducto());
			}						
		}
		sqlSession.close();

	}
	
	public void actualizarBatchStockProductoDevolucion(List<ComprobanteDetalle> cDetalle) throws Exception { 
		Boolean resultado=Boolean.FALSE;
		System.out.println("entra en actualizarBatchStockProductoDevolucion");
		SqlSessionFactory sqlSessionFactory =  (SqlSessionFactory)ServiceFinder.findBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		ProductoMapper daoObj= (ProductoMapper)sqlSession.getMapper(ProductoMapper.class);
		
		for (ComprobanteDetalle fpd:cDetalle) {		
			BigDecimal nuevoStock = fpd.getProducto().getStock().subtract(fpd.getCant_unidades_item_det()); 
			fpd.getProducto().setStock(nuevoStock);
			this.productoMapper.actualizarStockProducto(fpd.getProducto());
			
		}
		sqlSession.close();
	}

	@Override
	public List<Producto> findByCodigoDescripcion(String cod_prod_det,String descripcion_prod_det) throws Exception { 
		// TODO Auto-generated method stub
		return this.productoMapper.findByCodigoDescripcion(cod_prod_det, descripcion_prod_det);
	}

	public List<Producto> buscarProductoComprobante(Integer id_producto) {
		// TODO Auto-generated method stub
		return this.productoMapper.buscarProductoComprobante(id_producto);
	}

	@Override
	public void actualizarStockProducto(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		this.productoMapper.actualizarStockProducto(producto);
	}

	public List<Producto> buscarMovimientosPorProducto(Producto producto) {
		// TODO Auto-generated method stub
		return this.productoMapper.buscarMovimientosPorProducto(producto);
	}

	/*Siemco v2.0*/
	@Override
	public void actualizarStockProductoPorCodigo(Integer id_producto, BigDecimal stock) throws Exception {
		this.productoMapper.actualizarStockProductoPorCodigo(id_producto, stock);
		
	}


	
	
}
