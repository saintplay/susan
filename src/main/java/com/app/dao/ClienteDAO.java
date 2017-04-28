package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Cliente;

public class ClienteDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public ClienteDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Cliente c) {
		try {
			sql = "{call sp_agregarcliente(?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombres());
			cs.setString(2, c.getApellidos());
			cs.setString(3, c.getCorreo());
			cs.setString(4, c.getUsuario());
			cs.setString(5, c.getContrasenia());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}
	
	
	public Cliente login(String usuario, String pass){
		Cliente cliente = null;
		try {
			sql = "{call sp_logincliente(?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, usuario);
			cs.setString(2, pass);
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setIdcliente(rs.getInt(1));
				cliente.setNombres(rs.getString(2));
				cliente.setApellidos(rs.getString(3));
				cliente.setCorreo(rs.getString(4));
				cliente.setUsuario(rs.getString(5));
				cliente.setContrasenia(rs.getString(6));
			}
			rs.close();
			cs.close();
		} catch (Exception ex) {
			cliente = null;
		}
		return cliente;
	}

}
