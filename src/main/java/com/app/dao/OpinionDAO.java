package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Opinion;

public class OpinionDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public OpinionDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Opinion c) {
		try {
			sql = "{call sp_agregar_opinion(?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setInt(2, c.getDescripcion());
                        cs.setInt(3, c.getHotelId());
                        cs.setInt(4, c.getHabitacionId());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}
