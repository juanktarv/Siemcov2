package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Producto;


public interface ProductoMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */
	@Select("select e.*, ttd1.descripcion_largo desProductoSunat, ttd2.descripcion_largo desUnidadMedida from (t_producto e left join t_tabla_tablas_detalle ttd1 on ttd1.id_codigo = e.tipo_prod_sunat_det) left join t_tabla_tablas_detalle ttd2 on ttd2.codigo_catalogo = e.unidad_medida_det where e.id_producto = #{p_producto}")
	public Producto findById(@Param("p_producto") Integer codigoProducto) throws Exception;
	
	@Select("select e.* , (select d.descripcion_largo from t_tabla_tablas_detalle d where d.codigo_catalogo = e.unidad_medida_det and d.id_maestra = 3) desUnidadMedida from t_producto e  order by e.id_producto asc")
	public List<Producto> findAll() throws Exception;
	
	@Insert("insert into t_producto (cod_prod_det, tipo_prod_sunat_det, descripcion_prod_det, valor_unitario_prod_det, unidad_medida_det, stock, valor_unit_incluye_impuestos, tipo_articulo) "
			+ "values (#{cod_prod_det}, #{tipo_prod_sunat_det}, #{descripcion_prod_det}, #{valor_unitario_prod_det}, #{unidad_medida_det}, #{stock}, #{valor_unit_incluye_impuestos},#{tipo_articulo})")
	public void crearProducto(Producto producto) throws Exception;
	
	@Update("update t_producto set tipo_articulo= #{tipo_articulo}, cod_prod_det = #{cod_prod_det}, tipo_prod_sunat_det = #{tipo_prod_sunat_det}, descripcion_prod_det = #{descripcion_prod_det}, valor_unitario_prod_det = #{valor_unitario_prod_det}, unidad_medida_det = #{unidad_medida_det}, stock = #{stock}, valor_unit_incluye_impuestos = #{valor_unit_incluye_impuestos} where id_producto= #{id_producto}")
	@Options(flushCache=true,useCache=true)
    public void actualizarProducto(Producto producto) throws Exception;
	
	@Update("update t_producto set stock = #{stock} where id_producto= #{id_producto}")
	@Options(flushCache=true,useCache=true)
    public void actualizarStockProducto(Producto producto) throws Exception;
	
	@Delete("delete  from t_producto  where id_producto = #{id_producto}")
	@Options(flushCache=true)
	public void eliminarProducto(@Param("id_producto") Integer id_producto) throws Exception;
	
	@Select("select e.*, ttd1.descripcion_largo desProductoSunat, ttd2.descripcion_largo desUnidadMedida "
			+ "from (t_producto e left join t_tabla_tablas_detalle ttd1 on ttd1.id_codigo = e.tipo_prod_sunat_det) "
			+ "left join t_tabla_tablas_detalle ttd2 on ttd2.codigo_catalogo = e.unidad_medida_det "
			+ " where e.cod_prod_det = #{cod_prod_det} and e.descripcion_prod_det = #{descripcion_prod_det}")
	public Producto findByCodigoDescripcion(@Param("cod_prod_det") String cod_prod_det, @Param("descripcion_prod_det") String descripcion_prod_det) throws Exception;

	@Select("select prod.* from t_producto prod"
			+ " inner join t_detalle_comprobante det on prod.id_producto=det.id_producto "
			+ "where prod.id_producto=#{id_producto}")
	public List<Producto> buscarProductoComprobante(@Param("id_producto")Integer id_producto);
	
}
