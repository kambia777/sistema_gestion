package com.sistemagestion.entity.almacen;

import com.sistemagestion.entity.Color;
import com.sistemagestion.entity.Polo;
import com.sistemagestion.entity.Talla;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalle_entrada_almacen")
public class DetalleEntradaAlmacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 RELACIÓN CON ENTRADA
    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private EntradaAlmacen entrada;

    // 🔗 RELACIONES CON CATÁLOGOS
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntradaAlmacen getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaAlmacen entrada) {
		this.entrada = entrada;
	}

	public Polo getPolo() {
		return polo;
	}

	public void setPolo(Polo polo) {
		this.polo = polo;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

    // getters y setters
    
    
}