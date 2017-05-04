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
        public List<Opinion> listarTodos() {
                List<Opinion> lista = new ArrayList<>();

                try {
                        CallableStatement cs = cn.prepareCall(sql);
                        ResultSet rs = cs.executeQuery();
                        while (rs.next()) {
                                Opinion h = new Opinion();
                                h.setId(rs.getInt(1));
                                h.setNombre(rs.getString(2));
                                h.setDescripcion(rs.getString(3));
                                h.setHotelId(rs.getInt(4));
                                h.setHabitacionId(rs.getInt(5));
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
