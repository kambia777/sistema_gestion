package com.sistemagestion.entity.dto;

public class StockDTO {

    private String polo;
    private String talla;
    private String color;
    private String tienda;

    private int stockAlmacen;
    private int stockTienda;
    private int ventas;
    
//    private String tienda;

    // constructor
    public StockDTO(String polo, String talla, String color, int stockAlmacen, int stockTienda, String tienda) {
        this.polo = polo;
        this.talla = talla;
        this.color = color;
        this.tienda=tienda;
        this.stockAlmacen = 0;
        this.stockTienda = 0;
        this.ventas = 0;

    }

    // getters y setters
    public String getPolo() { return polo; }
    public String getTalla() { return talla; }
    public String getColor() { return color; }

    public int getStockAlmacen() { return stockAlmacen; }
    public void setStockAlmacen(int stockAlmacen) { this.stockAlmacen = stockAlmacen; }

    public int getStockTienda() { return stockTienda; }
    public void setStockTienda(int stockTienda) { this.stockTienda = stockTienda; }

	/**
	 * @return the ventas
	 */
	public int getVentas() {
		return ventas;
	}

	/**
	 * @param ventas the ventas to set
	 */
	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	/**
	 * @return the tienda
	 */
	public String getTienda() {
		return tienda;
	}

	/**
	 * @param tienda the tienda to set
	 */
	public void setTienda(String tienda) {
		this.tienda = tienda;
	}
	
	
    
    


    
    
}