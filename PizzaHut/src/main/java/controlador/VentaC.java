package controlador;

import dao.VentaImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import modelo.VentaDetalle;
import reporte.reportesVenta;

@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    /*variables para la tabla temporal*/
    private int item;
    private String codigo;
    private String nombre;
    private int stock;
    private double precio;
    private int cantidad;
    private double subtotal;
    private double total;
    private String descripcion;

    /* Creando objeto al modelo */
    private VentaDetalle vd;
    private Cliente cli;
    private Producto pro;
    private Venta ven;

    /*Creando objeto al dao*/
    private VentaImpl dao;

    /*Creando listas de los modelos*/
    private List<Cliente> listado;
    private List<Producto> ListarProductos;
    private List<VentaDetalle> ListarVD = new ArrayList();

    public VentaC() {
        cli = new Cliente();
        dao = new VentaImpl();
        pro = new Producto();
        vd = new VentaDetalle();
        ven = new Venta();
    }

    public void registrar() throws Exception {
        try {
            if(ListarVD.isEmpty()||cli.getId()==0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Cliente no seleccionado"));
            }else{
            ven.setIdCliente(cli.getId());
            dao.registrar(ven);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Venta Registrado con éxito"));
            registrarDetalle();
            LimpiarTodo();        
            }
            
        } catch (Exception e) {
            System.out.println("Error en registrar/VentaC");
        }
    }

    public void registrarDetalle() throws Exception {
        /*venmax es el id de la cantidad final de la venta + 1*/
        int venmax = dao.ventasMaximas();
        for (int i = 0; i < ListarVD.size(); i++) {
            vd = new VentaDetalle();
            vd.setCantidad(ListarVD.get(i).getCantidad());
            vd.setIdVenta(venmax);
            vd.setCodigoProducto(ListarVD.get(i).getCodigoProducto());
            dao.registrarVentaDetalle(vd);
            dao.ActualizarStock(vd);
        }
    }

    public void cargarDatos() {
        try {
            dao.mostrarDatos(pro);
        } catch (Exception e) {
            System.out.println("Error en CargarDatos/VentaC" + e.getMessage());
        }
    }

    public List<String> autocompletePrueba(String query) throws Exception {
        try {
            return dao.autocompletar(query);
        } catch (Exception e) {
            System.out.println("Error en ventaC/Controlador" + e.getMessage());
            throw e;
        }
    }
    
public void verReportePDFEST() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, Exception {
        int idven = dao.ventasMaximas();
        reportesVenta reporte = new reportesVenta();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("img/rBoleta_1.jasper");
        String numeroinformesocial = String.valueOf(8);
        reporte.getReportePdf(root, numeroinformesocial);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void validadorProductoRepetido(Producto productos) {
        int indice = 0;
        int cantidad = 0;
        if (productos.getCodigo() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "PRODUCTO NO SELECCIONADO"));
        } else {
            if (pro.getCantidad() > 0) {
                try {

                    if (ListarVD.isEmpty()) {
                        if (pro.getCantidad() > pro.getStock()) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Cantidad de producto no disponible"));
                        } else {
                            tempTable();
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Producto agregado con éxito"));
                        }
                    } else {
                        for (VentaDetalle ventaDetalle : ListarVD) {
                            if (ventaDetalle.getCodigoProducto().equals(productos.getCodigo())) {
                                cantidad = ListarVD.get(indice).getCantidad() + productos.getCantidad();
                                if (cantidad <= pro.getStock()) {
                                    subtotal = ListarVD.get(indice).getPrecio() * cantidad;
                                    total = 0;
                                    ventaDetalle.setCantidad(cantidad);
                                    ventaDetalle.setSubtotal(subtotal);
                                    ListarVD.set(indice, ventaDetalle);
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Producto agregado con éxito"));
                                    CalcularTotalVenta();
                                    pro = new Producto();
                                    break;
                                } else {
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "cantidad de producto no disponible"));
                                }

                            } else {
                                indice++;
                                if (indice == ListarVD.size()) {
                                    if (pro.getCantidad() > pro.getStock()) {
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Cantidad de producto no disponible"));

                                    } else {
                                        tempTable();
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Producto agregado con éxito"));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("error  en validador producto repetido VentasC : " + e);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ingrese la la cantidad de venta"));
            }
        }
    }

    public void tempTable() {
        total = 0.0;
        item = item + 1;
        nombre = pro.getNombre();
        descripcion = pro.getDetalle();
        cantidad = pro.getCantidad();
        precio = pro.getPrecio();
        codigo = pro.getCodigo();
        subtotal = cantidad * precio;

        vd = new VentaDetalle();

        vd.setItem(item);
        vd.setCodigoProducto(codigo);
        vd.setCantidad(cantidad);
        vd.setDescripcion(descripcion);
        vd.setProducto(nombre);
        vd.setPrecio(precio);
        vd.setSubtotal(subtotal);
        ListarVD.add(vd);
        pro = new Producto();
    }

    public void CalcularTotalVenta() {
        for (int i = 0; i < ListarVD.size(); i++) {
            total = total + ListarVD.get(i).getSubtotal();
        }
    }

    public void Filtrado() throws Exception {
        try {
            dao.filtrarCliente(cli);
        } catch (Exception e) {
            System.out.println("Error en filtrar: " + e.getMessage());
        }
    }

    public void Limpiar() {
        pro = new Producto();
        ListarVD = new ArrayList();
    }

    public void LimpiarTodo() {
        pro = new Producto();
        ListarVD = new ArrayList();
        cli = new Cliente();
        total = 0;
    }

    public VentaDetalle getVd() {
        return vd;
    }

    public void setVd(VentaDetalle vd) {
        this.vd = vd;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public VentaImpl getDao() {
        return dao;
    }

    public void setDao(VentaImpl dao) {
        this.dao = dao;
    }

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public Venta getVen() {
        return ven;
    }

    public void setVen(Venta ven) {
        this.ven = ven;
    }

    public List<Cliente> getListado() {
        return listado;
    }

    public void setListado(List<Cliente> listado) {
        this.listado = listado;
    }

    public List<Producto> getListarProductos() {
        return ListarProductos;
    }

    public void setListarProductos(List<Producto> ListarProductos) {
        this.ListarProductos = ListarProductos;
    }

    public List<VentaDetalle> getListarVD() {
        return ListarVD;
    }

    public void setListarVD(List<VentaDetalle> ListarVD) {
        this.ListarVD = ListarVD;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
