package com.app.dominio;

public class Pago {
	private int Id;
	private double monto;
	private String fecha;
        private int reserva_id;
	
	public Pago() {
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

        public void setFecha(String fecha) {
                this.fecha = fecha;
        }
        public String getReservaId() {
                return reserva_id;
        }

        public void setReservaId(int reserva_id) {
                this.reserva_id = reserva_id;
        }
}
