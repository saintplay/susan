package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.dominio.Pago;

public class PagoDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public PagoDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Pago p) {

		try {
			sql = "{call sp_agregarpago(?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, p.getId());
			cs.setInt(2, p.getReservaId());
			cs.setTimestamp(3, p.getFecha());
			cs.setFloat(4, p.getMonto());
			cs.setString(5, p.getEstado());
			cs.setString(6, p.getModalidad());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (Exception e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

	public List<Pago> listarTodos() {
		List<Pago> lista = new ArrayList<>();

		try {
			CallableStatement cs = cn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Pago p = new Pago();
				p.setId(rs.getInt(1));
				p.setReservaId(rs.getInt(2));
				p.setFecha(rs.getTimestamp(3));
				p.setMonto(rs.getFloat(4));
				p.setEstado(rs.getString(5));
				p.setModalidad(rs.getString(6));
				lista.add(p);
			}
			rs.close();
			cs.close();
		} catch (Exception e) {
			lista = null;
		}
		return lista;
	}

	public String actualizar(Pago p) {
		try {
			sql = "{call sp_actualizarpago(?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, p.getId());
			cs.setInt(2, p.getReservaId());
			cs.setTimestamp(3, p.getFecha());
			cs.setFloat(4, p.getMonto());
			cs.setString(5, p.getEstado());
			cs.setString(6, p.getModalidad());
			cs.executeUpdate();
			cs.close();
			respuesta = "Actualizacion Correcta";
		} catch (Exception e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}