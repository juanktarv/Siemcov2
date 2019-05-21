package com.certicom.scpf.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Despacho;
import com.certicom.scpf.domain.Inventario;

/*Siemco v2.0*/

public interface InventarioMapper {
	
	@Select("select e.* from t_inventario e order by e.id_producto asc")
	public List<Inventario> findAll() throws Exception;
	
	@Select("select * from t_inventario "
			+ " where  "
			+ "id_almacen= #{id_almacen} and "
			+ "id_producto = #{id_producto} and "
			+ "id_emisor = #{id_emisor} and "
			+ "id_domicilio_fiscal = #{id_domicilio_fiscal}")
	public Inventario findById(@Param("id_almacen") Integer codigoAlmacen, 
								@Param("id_producto") Integer codigoProducto, 
								@Param("id_emisor") Integer codigoEmisor,
								@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal) throws Exception;
	
	@Select("   select e.* , "
			+ " p.descripcion_prod_det descripcionProducto, a.descripcion_almacen descripcionAlmacen,  "
			+ " (select ttd.descripcion_largo from t_tabla_tablas_detalle ttd where ttd.codigo_catalogo = p.unidad_medida_det ) as desUnidadMedida "
			+ " from  t_inventario e, t_producto p, t_almacen a "
			+ " where e.id_producto = p.id_producto and a.id_almacen = e.id_almacen order by e.id_producto asc")
	public List<Inventario> listarTodosInventario() throws Exception;
	
	
	
	@Select(" select sum(cantidad) from t_inventario where id_producto = #{id_producto} ")
	public BigDecimal cantidadStock(@Param("id_producto") Integer codigoProducto) throws Exception;
	
	
	@Insert("insert into t_inventario (id_almacen, id_emisor, id_domicilio_fiscal, id_producto, id_tipo_producto, cantidad, costo_promedio) "
			+ "values (#{id_almacen}, #{id_emisor}, #{id_domicilio_fiscal}, #{id_producto}, #{id_tipo_producto}, #{cantidad}, #{costo_promedio})")
	public void crearInventario(Inventario inventario) throws Exception;
	
	
	@Update("update t_inventario set  id_tipo_producto = #{id_tipo_producto}, cantidad = #{cantidad}, costo_promedio = #{costo_promedio} "
			+ " where "
			+ " id_almacen= #{id_almacen} and "
			+ "id_producto = #{id_producto} and "
			+ "id_emisor = #{id_emisor} and "
			+ "id_domicilio_fiscal = #{id_domicilio_fiscal}")
	@Options(flushCache=true,useCache=true)
    public void actualizarInventario(Inventario inventario) throws Exception;
	
	@Delete("delete  from t_inventario "
			+ " where "
			+ "id_almacen = #{id_almacen} and  "
			+ "id_producto = #{id_producto} and  "
			+ "id_emisor = #{id_emisor} and "
			+ "id_domicilio_fiscal = #{id_domicilio_fiscal} ")
	@Options(flushCache=true)
	public void eliminarInventario(@Param("id_almacen") Integer id_almacen, @Param("id_producto") Integer id_producto, @Param("id_emisor") Integer id_emisor, @Param("id_domicilio_fiscal") Integer id_domicilio_fiscal) throws Exception;
	

	

}
