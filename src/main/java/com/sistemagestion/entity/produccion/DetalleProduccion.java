package com.sistemagestion.entity.produccion;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sistemagestion.entity.Color;
import com.sistemagestion.entity.Polo;
import com.sistemagestion.entity.Talla;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_produccion")
public class DetalleProduccion {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con la orden
    @ManyToOne
    @JoinColumn(name = "orden_id")
    @JsonBackReference
    private OrdenProduccion ordenProduccion;

    // Relaciones con producto, talla y color
    @ManyToOne
    @JoinColumn(name = "polo_id")
    private Polo polo;

    @ManyToOne
    @JoinColumn(name = "talla_id")
    private Talla talla;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    private Integer cantidad;
    
    @Column(name = "fecha_entrega")
    private LocalDate fecha;
    
    private String estado;
    
    public DetalleProduccion() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrdenProduccion getOrdenProduccion() { return ordenProduccion; }
    public void setOrdenProduccion(OrdenProduccion ordenProduccion) { this.ordenProduccion = ordenProduccion; }

    public Polo getPolo() { return polo; }
    public void setPolo(Polo polo) { this.polo = polo; }

    public Talla getTalla() { return talla; }
    public void setTalla(Talla talla) { this.talla = talla; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
	public LocalDate getFechaEntrega() {
		return fecha;
	}
	public void setFechaEntrega(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
    

}
