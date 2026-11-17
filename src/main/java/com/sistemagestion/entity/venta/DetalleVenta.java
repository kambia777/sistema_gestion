package com.sistemagestion.entity.venta;

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
@Table(name = "detalle_venta")
public class DetalleVenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    
    
    @Column(name = "precio_unitario")
    private Double precioUnitario;

    // 🔗 RELACIÓN CON VENTA
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the precioUnitario
	 */
	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}

	/**
	 * @param venta the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	/**
	 * @return the polo
	 */
	public Polo getPolo() {
		return polo;
	}

	/**
	 * @param polo the polo to set
	 */
	public void setPolo(Polo polo) {
		this.polo = polo;
	}

	/**
	 * @return the talla
	 */
	public Talla getTalla() {
		return talla;
	}

	/**
	 * @param talla the talla to set
	 */
	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
    
    
    

}
