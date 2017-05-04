package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Reserva;

public class ReservaDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public ReservaDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Reserva c) {
		try {
			sql = "{call sp_agregar_reserva(?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setInt(2, c.getClienteId());
                        cs.setString(3, c.getFechaDesde());
                        cs.setString(4, c.getFechaHasta());
                        cs.setInt(5, c.getHotelId());
                        cs.setInt(6, c.getHabitacionId());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}
