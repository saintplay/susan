package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Habitacion;

public class HabitacionDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public HabitacionDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Habitacion c) {
		try {
			sql = "{call sp_agregar_habitacion(?,?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setInt(2, c.getPiso());
			cs.setInt(3, c.getTipo());
                        cs.setString(4, c.getDescripcion());
                        cs.setString(5, c.getFoto());
                        cs.setString(6, c.getHotelId());
                        cs.setString(7, c.getPrecio());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}
