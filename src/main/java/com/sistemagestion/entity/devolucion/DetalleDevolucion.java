package com.sistemagestion.entity.devolucion;

import com.sistemagestion.entity.Color;
import com.sistemagestion.entity.Polo;
import com.sistemagestion.entity.Talla;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_devolucion")
public class DetalleDevolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    // 🔗 RELACIÓN CON DEVOLUCIÓN
    @ManyToOne
    @JoinColumn(name = "devolucion_id")
    private Devolucion devolucion;

    // 🔗 PRODUCTO
    @ManyToOne
    @JoinColumn(name = "polo_id")
    private Polo polo;

    @ManyToOne
    @JoinColumn(name = "talla_id")
    private Talla talla;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    // GETTERS Y SETTERS
    public Long getId() { return id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Devolucion getDevolucion() { return devolucion; }
    public void setDevolucion(Devolucion devolucion) { this.devolucion = devolucion; }

    public Polo getPolo() { return polo; }
    public void setPolo(Polo polo) { this.polo = polo; }

    public Talla getTalla() { return talla; }
    public void setTalla(Talla talla) { this.talla = talla; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}