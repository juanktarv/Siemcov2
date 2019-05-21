package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.MovimientoInventarioProveedores;

/*Siemco v2.0*/
public interface MovInventarioProveedorMapper {
	
	@Select("select * from t_movimientos_inventario_proveedor e "
			+ " where e.id_comprobante_compra = #{id_comprobante_compra} and "
			+ " e.tipo_comprobante = #{tipo_comprobante} and "
			+ " e.id_proveedores = #{id_proveedores} and "
			+ " e.id_producto = #{id_producto} and "
			+ " e.id_emisor = #{id_emisor} and"
			+ " e.id_domicilio_fiscal = #{id_domicilio_fiscal} ")
	public MovimientoInventarioProveedores 
	findById(@Param("id_comprobante_compra") Integer codigoComprobante, 
			@Param("tipo_comprobante") String tipoComprobante, 
			@Param("id_proveedores") Integer codigoProveedores,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal) throws Exception;
	
	
	@Select("select * from t_movimientos_inventario_proveedor order by id_comprobante_compra desc")
	public List<MovimientoInventarioProveedores> findAll() throws Exception;
	
	@Select("select e.*, c.nombre_proveedor nombreProveedor, c.numero_documento numeroDocumentoProveedor, p.cod_prod_det codigoProducto, p.descripcion_prod_det descripcionProducto, p.unidad_medida_det abrevUnidadMedida, "
			+ " (select d.descripcion_largo from t_tabla_tablas_detalle d where d.codigo_catalogo = p.unidad_medida_det and d.id_maestra = 3) descripcionUnidadMedida, "
			+ " (select com.nroserie_documento from t_comprobante_compra com where com.id_comprobante_compra = e.id_comprobante_compra)  numeroSerieDocumento, "
			+ " (select  doc.descripcion_largo   from  t_tabla_tablas_detalle doc where doc.codigo_catalogo = e.tipo_comprobante and doc.id_maestra = 1) nombreTipoComprobante "
			+ " from "
			+ " t_movimientos_inventario_proveedor e, "
			+ " t_proveedores c, "
			+ " t_producto p "
			+ " where "
			+ " e.id_proveedores= c.id_proveedor and "
			+ " p.id_producto = e.id_producto "
			+ " order by id_comprobante_compra desc " ) 
	public List<MovimientoInventarioProveedores> listarMovimientosInventarioProveedores() throws Exception;
	
	
	@Insert("insert into t_movimientos_inventario_proveedor "
			+ " (id_comprobante_compra, id_proveedores, tipo_comprobante, id_producto, id_domicilio_fiscal, id_emisor, "
			+ " id_tipo_producto, numero_serie_documento, tipo_unidad_medida, cant_unidades_item, saldo_arribo, flag_regularizado ) "
			+ " values (#{id_comprobante_compra}, #{id_proveedores}, #{tipo_comprobante}, #{id_producto}, #{id_domicilio_fiscal}, #{id_emisor},  "
			+ " #{id_tipo_producto},  #{numero_serie_documento},  #{tipo_unidad_medida},  #{cant_unidades_item},  #{saldo_arribo},  #{flag_regularizado})")
	public void crearMovimientoInventarioProveedores(MovimientoInventarioProveedores movimientoInventarioProveedores) throws Exception;
	
	
	@Update("update t_movimientos_inventario_proveedor set "
			+ " id_tipo_producto = #{id_tipo_producto}, numero_serie_documento = #{numero_serie_documento}, tipo_unidad_medida = #{tipo_unidad_medida},"
			+ " cant_unidades_item = #{cant_unidades_item}, saldo_arribo = #{saldo_arribo}, flag_regularizado = #{flag_regularizado} "
			+ " where id_comprobante_compra= #{id_comprobante_compra} and id_proveedores= #{id_proveedores} and  tipo_comprobante= #{tipo_comprobante} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} ")
	@Options(flushCache=true,useCache=true)
    public void actualizarMovimientoInventarioProveedores(MovimientoInventarioProveedores movimientoInventarioProveedores) throws Exception;
	
	@Delete("delete from t_movimientos_inventario_proveedor  "
			+ "where id_comprobante_compra= #{id_comprobante_compra} and id_proveedores= #{id_proveedores} and  tipo_comprobante= #{tipo_comprobante} and "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} ")
	@Options(flushCache=true)
	public void eliminarMovimientoInventarioProveedores(
			@Param("id_comprobante") Integer codigoComprobante, 
			@Param("tipo_comprobante") String tipoComprobante, 
			@Param("id_proveedores") Integer codigoProveedores,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal) throws Exception;
	

	

}
