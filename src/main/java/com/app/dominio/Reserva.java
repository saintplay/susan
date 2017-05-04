package com.app.dominio;

public class Reserva {
	private int Id;
	private int cliente_id;
	private String fecha_desde;
        private String fecha_hasta;
        private int hotel_id;
        private int habitacion_id;
	
	public Reserva() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getClienteId() {
		return cliente_id;
	}

	public void setClienteId(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getFechaDesde() {
		return fecha_desde;
	}

        public void setFechaHasta(String fecha_hasta) {
                this.fecha_hasta = fecha_hasta;
        }
        public String getHotelId() {
                return hotel_id;
        }

        public void setHotelId(int hotel_id) {
                this.hotel_id = hotel_id;
        }
        public String getHabitacionId() {
                return habitacion_id;
        }

        public void setHabitacionId(int habitacion_id) {
                this.habitacion_id = habitacion_id;
        }
}
