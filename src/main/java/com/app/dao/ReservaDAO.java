package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			sql = "{call sp_agregar_reserva(?,?,?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setInt(1, c.getClienteId());
			cs.setInt(2, c.getHabitacionId());
            cs.setTimestamp(3, c.getFechaDesde());
            cs.setTimestamp(4, c.getFechaHasta());
            cs.setString(5, c.getEstado());
            cs.setString(6, c.getTipo());
            cs.setFloat(7, c.getCosto_total());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}
        public List<Reserva> listarTodos() {
                List<Reserva> lista = new ArrayList<>();

                try {
                        CallableStatement cs = cn.prepareCall(sql);
                        ResultSet rs = cs.executeQuery();
                        while (rs.next()) {
                                Reserva h = new Reserva();
                                h.setId(rs.getInt(1));
                                h.setClienteId(rs.getInt(2));
                                h.setHabitacionId(rs.getInt(3));
                                h.setFechaDesde(rs.getTimestamp(4));
                                h.setFechaHasta(rs.getTimestamp(5));
                                h.setEstado(rs.getString(6));
                                h.setTipo(rs.getString(7));
                                h.setCosto_total(rs.getFloat(8));
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
