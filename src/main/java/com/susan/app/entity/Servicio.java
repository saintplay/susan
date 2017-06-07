package com.susan.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Servicio {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  @Size(min = 4, max = 30, message = "nombre debe tener mas de 4 letras y menos de 30")
  private String nombre;
  private String descripcion;
  private float costo;
  
  public Servicio() {
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
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
  public float getCosto() {
    return costo;
  }
  public void setCosto(float costo) {
    this.costo = costo;
  }
}