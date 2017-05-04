package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Servicio;

public class ServicioDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public ServicioDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Servicio c) {
		try {
			sql = "{call sp_agregar_servicio(?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setString(2, c.getDescripcion());
                        cs.setInt(3, c.getHotelId());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}
