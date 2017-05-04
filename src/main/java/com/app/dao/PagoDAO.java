package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.dominio.Pago;

public class PagoDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public PagoDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Pago c) {
		try {
			sql = "{call sp_agregar_pago(?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setString(2, c.getMonto());
                        cs.setInt(3, c.getFecha());
                        cs.setInt(4, c.getReservaId());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}
