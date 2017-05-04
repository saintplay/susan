package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.dominio.Hotel;

public class HotelDAO {
	private String respuesta = null;
	private String sql = null;
	private Connection cn = null;

	public HotelDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Hotel h) {

		try {
			sql = "{call sp_agregarhotel(?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, h.getId());
			cs.setString(2, h.getNombre());
			cs.setInt(3, h.getCalificacion());
			cs.setString(4, h.getDireccion());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (Exception e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

	public String actualizar(Hotel h) {
		try {
			sql = "{call sp_actualizarhotel(?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, h.getId());
			cs.setString(2, h.getNombre());
			cs.setInt(3, h.getCalificacion());
			cs.setString(4, h.getDireccion());
			cs.executeUpdate();
			cs.close();
			respuesta = "Actualizacion Correcta";
		} catch (Exception e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

	public List<Hotel> listarTodos() {
		List<Hotel> lista = new ArrayList<>();

		try {
			CallableStatement cs = cn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Hotel h = new Hotel();
				h.setId(rs.getInt(1));
				h.setNombre(rs.getString(2));
				h.setCalificacion(rs.getInt(3));
				h.setDireccion(rs.getString(4));
				lista.add(h);
			}
			rs.close();
			cs.close();
		} catch (Exception e) {
			lista = null;
		}
		return lista;
	}

}