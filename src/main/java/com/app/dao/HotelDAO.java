package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Hotel;

public class HotelDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public HotelDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Hotel c) {
		try {
			sql = "{call sp_agregar_hotel(?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setString(2, c.getCalificacion());
			cs.setString(3, c.getDireccion());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}
