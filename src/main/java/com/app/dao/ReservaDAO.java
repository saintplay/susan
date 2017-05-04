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
			sql = "{call sp_agregar_reserva(?,?,?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setInt(2, c.getClienteId());
                        cs.setString(3, c.getFechaDesde());
                        cs.setString(4, c.getFechaHasta());
                        cs.setInt(5, c.getHotelId());
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
                                h.setNombre(rs.getString(2));
                                h.setClienteId(rs.getInt(3));
                                h.setFechaDesde(rs.getInt(4));
                                h.setFechaHasta(rs.getString(5));
                                h.setHotelId(rs.getInt(6));
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
