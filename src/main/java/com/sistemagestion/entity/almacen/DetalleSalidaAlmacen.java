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
@Table(name = "detalle_salida_almacen")
public class DetalleSalidaAlmacen {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // 👉 Relación con salida
	    @ManyToOne
	    @JoinColumn(name = "salida_id")
	    private SalidaAlmacen salida;

	    // 👉 Producto
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
	    
	    // GETTERS Y SETTERS

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public SalidaAlmacen getSalida() {
			return salida;
		}

		public void setSalida(SalidaAlmacen salida) {
			this.salida = salida;
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
    
	    
}
