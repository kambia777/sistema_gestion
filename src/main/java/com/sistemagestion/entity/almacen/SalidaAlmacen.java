package com.sistemagestion.entity.almacen;

import java.time.LocalDate;
import java.util.List;

import com.sistemagestion.entity.Tienda;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "salida_almacen")
public class SalidaAlmacen {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String observaciones;

    // 👉 Relación con tienda
    @ManyToOne
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;

    // 👉 Detalles
    @OneToMany(mappedBy = "salida", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleSalidaAlmacen> detalles;
    
    

    // GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public List<DetalleSalidaAlmacen> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleSalidaAlmacen> detalles) {
		this.detalles = detalles;
	}
 

}
