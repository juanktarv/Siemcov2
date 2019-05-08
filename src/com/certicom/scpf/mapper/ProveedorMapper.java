package com.certicom.scpf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.scpf.domain.Cliente;
import com.certicom.scpf.domain.Proveedores;

public interface ProveedorMapper {

	/**
	 * @Desc: buscar un cliente pro su Id
	 * @param clienteRuc : ruc del cliente, es el ID
	 * @return : retorna un objeto Cliente
	 * @throws Exception
	 */

	@Select("select e.* from t_proveedores e order by e.id_proveedor asc")
	public List<Proveedores> findAll() throws Exception;
	
	@Insert("insert into t_proveedores (tipo_documento, numero_documento, nombre_proveedor, direccion, numero_telefono, correo) values (#{tipo_documento}, #{numero_documento}, #{nombre_proveedor}, #{direccion}, #{numero_telefono}, #{correo})")
	public void crearProveedor(Proveedores proveedor) throws Exception;
	
	@Update("update t_proveedores set tipo_documento = #{tipo_documento}, numero_documento = #{numero_documento}, nombre_proveedor = #{nombre_proveedor}, direccion = #{direccion}, numero_telefono = #{numero_telefono}, correo = #{correo} where id_proveedor= #{id_proveedor}")
	@Options(flushCache=true,useCache=true)
    public void actualizarProveedor(Proveedores proveedor) throws Exception;
	
	@Delete("delete  from t_proveedores  where id_proveedor = #{id_proveedor}")
	@Options(flushCache=true)
	public void eliminarProveedor(@Param("id_proveedor") Integer id_proveedor) throws Exception;
	
	@Select(" select * from t_proveedores c where trim(c.numero_docu_iden_cab)=trim(#{numero_documento}) or trim(c.nombre_cab)=trim(#{nombre_proveedor}) ")
	public List<Proveedores> buscarProveedorPorRazonSocialDocumento(Proveedores proveedor);

	@Select("select c.* from t_proveedores c where c.id_proveedor in (select distinct d.id_proveedor from t_comprobante_compra d where d.id_proveedor=#{id_proveedor})")
	public List<Proveedores> buscarMovimientosPorProveedor(Proveedores proveedor);
	

	
}
