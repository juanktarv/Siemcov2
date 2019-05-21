package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.MovimientoInventarioClientes;



/*Siemco v2.0*/
public interface MovInventarioClienteMapper {
	
	@Select("select * from t_movimientos_inventario_cliente e "
			+ " where e.id_comprobante = #{id_comprobante} and "
			+ " e.tipo_comprobante = #{tipo_comprobante} and "
			+ " e.id_cliente = #{id_cliente} and "
			+ " e.id_producto = #{id_producto} and "
			+ " e.id_emisor = #{id_emisor} and"
			+ " e.id_domicilio_fiscal = #{id_domicilio_fiscal} ")
	public MovimientoInventarioClientes 
	findById(@Param("id_comprobante") Integer codigoComprobante, 
			@Param("tipo_comprobante") String tipoComprobante, 
			@Param("id_cliente") Integer codigoCliente,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal) throws Exception;
	
	
	@Select("select * from t_movimientos_inventario_cliente order by id_comprobante desc")
	public List<MovimientoInventarioClientes> findAll() throws Exception;
	
	@Select("select e.*, c.nombre_cab nombreCliente, c.numero_docu_iden_cab numeroDocumentoCliente, p.cod_prod_det codigoProducto, p.descripcion_prod_det descripcionProducto, p.unidad_medida_det abrevUnidadMedida, " 
			+ " (select d.descripcion_largo from t_tabla_tablas_detalle d where d.codigo_catalogo = p.unidad_medida_det and d.id_maestra = 3) descripcionUnidadMedida, "
			+ "  (select com.numero_serie_documento_cab from t_comprobante com where com.id_comprobante = e.id_comprobante)  numeroSerieDocumento, "
			+ " (select  doc.descripcion_largo   from  t_tabla_tablas_detalle doc where doc.codigo_catalogo = e.tipo_comprobante and doc.id_maestra = 1) nombreTipoComprobante "
			+ " from "
			+ " t_movimientos_inventario_cliente e, "
			+ " t_cliente c, "
			+ " t_producto p "
			+ " where "
			+ " e.id_cliente= c.id_cliente and "
			+ " p.id_producto = e.id_producto "
			+ " order by id_comprobante desc" ) 
	public List<MovimientoInventarioClientes> listarMovimientosInventarioClientes() throws Exception;
	
	
	@Insert("insert into t_movimientos_inventario_cliente "
			+ " (id_comprobante, id_cliente, tipo_comprobante, id_producto, id_domicilio_fiscal, id_emisor, "
			+ " id_tipo_producto, id_modo_pago, numero_serie_documento, tipo_unidad_medida, cant_unidades_item, saldo_despacho, flag_regularizado ) "
			+ " values (#{id_comprobante}, #{id_cliente}, #{tipo_comprobante}, #{id_producto}, #{id_domicilio_fiscal}, #{id_emisor},  "
			+ " #{id_tipo_producto}, #{id_modo_pago} , #{numero_serie_documento},  #{tipo_unidad_medida},  #{cant_unidades_item},  #{saldo_despacho},  #{flag_regularizado})")
	public void crearMovimientoInventarioClientes(MovimientoInventarioClientes movimientoInventarioClientes) throws Exception;
	
	
	@Update("update t_movimientos_inventario_cliente set "
			+ " id_tipo_producto = #{id_tipo_producto}, id_modo_pago = #{id_modo_pago}, numero_serie_documento = #{numero_serie_documento}, tipo_unidad_medida = #{tipo_unidad_medida},"
			+ " cant_unidades_item = #{cant_unidades_item}, saldo_despacho = #{saldo_despacho}, flag_regularizado = #{flag_regularizado} "
			+ " where id_comprobante= #{id_comprobante} and id_cliente= #{id_cliente} and  tipo_comprobante= #{tipo_comprobante} and  "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} ")
	@Options(flushCache=true,useCache=true)
    public void actualizarMovimientoInventarioClientes(MovimientoInventarioClientes movimientoInventarioClientes) throws Exception;
	
	@Delete("delete from t_movimientos_inventario_cliente  "
			+ "where id_comprobante= #{id_comprobante} and id_cliente= #{id_cliente} and  tipo_comprobante= #{tipo_comprobante} and "
			+ " id_producto= #{id_producto} and id_domicilio_fiscal= #{id_domicilio_fiscal} and id_emisor= #{id_emisor} ")
	@Options(flushCache=true)
	public void eliminarMovimientoInventarioClientes(
			@Param("id_comprobante") Integer codigoComprobante, 
			@Param("tipo_comprobante") String tipoComprobante, 
			@Param("id_cliente") Integer codigoCliente,
			@Param("id_producto") Integer codigoProducto, 
			@Param("id_emisor") Integer codigoEmisor,
			@Param("id_domicilio_fiscal") Integer codigoDomicilioFiscal) throws Exception;
	

	

}
