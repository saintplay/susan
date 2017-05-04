package com.app.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dominio.Servicio;

public class ServicioDAO {
	private String respuesta;
	private String sql;
	private Connection cn;

	public ServicioDAO() {
		cn = Conexion.conectar();
	}

	public String agregar(Servicio c) {
		try {
			sql = "{call sp_agregar_servicio(?,?,?)}";
			CallableStatement cs = cn.prepareCall(sql);
			cs.setString(1, c.getNombre());
			cs.setString(2, c.getDescripcion());
            cs.setFloat(3, c.getCosto());
			cs.executeUpdate();
			cs.close();
			respuesta = "Registro Correcto";
		} catch (SQLException e2) {
			respuesta = e2.getMessage();
		}
		return respuesta;
	}
        public List<Servicio> listarTodos() {
                List<Servicio> lista = new ArrayList<>();

                try {
                        CallableStatement cs = cn.prepareCall(sql);
                        ResultSet rs = cs.executeQuery();
                        while (rs.next()) {
                                Servicio h = new Servicio();
                                h.setId(rs.getInt(1));
                                h.setNombre(rs.getString(2));
                                h.setDescripcion(rs.getString(3));
                                h.setCosto(rs.getFloat(4));
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
