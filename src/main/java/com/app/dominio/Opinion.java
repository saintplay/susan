package com.app.dominio;

public class Opinion {
	private int Id;
	private String nombre;
	private String descripcion;
        private int hotel_id;
        private int habitacion_id;
	
	public Opinion() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
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
