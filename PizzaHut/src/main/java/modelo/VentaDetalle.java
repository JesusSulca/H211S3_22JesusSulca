
package modelo;

import java.util.Date;
import java.util.GregorianCalendar;


public class VentaDetalle {
    private int idVentaDetalle;
    private int cantidad;
    private String CodigoProducto;
    private int idVenta;
    private int item;
    private Date fechapruebas = GregorianCalendar.getInstance().getTime();
    private String descripcion;
    private String producto;
    private double precio;
    private double subtotal;
    
    public int getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(int idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Date getFechapruebas() {
        return fechapruebas;
    }

    public void setFechapruebas(Date fechapruebas) {
        this.fechapruebas = fechapruebas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
