package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.dominio.Habitacion;

public class HabitacionDAO {
	private String respuesta = null;
	private String sql = null;
	private Connection cn = null;

	public HabitacionDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Habitacion h) {

		try {
			sql = "{call sp_agregarhatitacion(?,?,?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, h.getId());
			cs.setString(2, h.getNombre());
			cs.setInt(3, h.getPiso());
			cs.setInt(4, h.getTipo());
			cs.setString(5, h.getDescripcion());
			cs.setString(6, h.getFoto());
			cs.setInt(7, h.getHotelId());
			cs.setFloat(8, h.getPrecio());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (Exception e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

	public List<Habitacion> listarTodos() {
		List<Habitacion> lista = new ArrayList<>();

		try {
			CallableStatement cs = cn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				Habitacion h = new Habitacion();
				h.setId(rs.getInt(1));
				h.setNombre(rs.getString(2));
				h.setPiso(rs.getInt(3));
				h.setTipo(rs.getInt(4));
				h.setDescripcion(rs.getString(5));
				h.setFoto(rs.getString(6));
				h.setHotelId(rs.getInt(7));
				h.setPrecio(rs.getInt(8));
				lista.add(h);
			}
			rs.close();
			cs.close();
		} catch (Exception e) {
			lista = null;
		}
		return lista;
	}

	public String actualizar(Habitacion h) {
		try {
			sql = "{call sp_actualizarhabitacion(?,?,?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, h.getId());
			cs.setString(2, h.getNombre());
			cs.setInt(3, h.getPiso());
			cs.setInt(4, h.getTipo());
			cs.setString(5, h.getDescripcion());
			cs.setString(6, h.getFoto());
			cs.setInt(7, h.getHotelId());
			cs.setFloat(8, h.getPrecio());
			cs.executeUpdate();
			cs.close();
			respuesta = "Actualizacion Correcta";
		} catch (Exception e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}

}