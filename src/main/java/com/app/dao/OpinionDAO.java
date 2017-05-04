package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			cs.setInt(1, c.getCliente_id());
			cs.setInt(2, c.getHotelId());
            cs.setInt(3, c.getHabitacionId());
            cs.setTimestamp(4, c.getFecha());
            cs.setString(4, c.getTexto());
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
                            h.setCliente_id(rs.getInt(2));
                            h.setHotelId(rs.getInt(3));
                            h.setHabitacionId(rs.getInt(4));
                            h.setFecha(rs.getTimestamp(5));
                            h.setTexto(rs.getString(6));
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
