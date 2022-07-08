package controlador;

import dao.ProductoImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Producto;
@Named(value = "ProductoC")
@SessionScoped
public class ProductoC implements Serializable {

    private Producto pro;
    private ProductoImpl dao;
    private List<Producto> listadoPro;
    private List<Producto> lisProAgo;

    public ProductoC() {
        pro = new Producto();
        dao = new ProductoImpl();
    }

    public void registrar() throws Exception {
        try {
            if(dao.existe(pro, listadoPro)){
            dao.registrar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
            limpiar();
            listar();    
            }else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "Producto Duplicado"));
            }
            

        } catch (Exception e) {
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            if(updateExiste(pro, listadoPro)){
                pro.setEstado("A");
            dao.modificar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
            limpiar();
            listar();
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Producto repetido"));
            }
            

        } catch (Exception e) {
            System.out.println("Error en modificarC" + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            pro.setEstado("I");
            dao.eliminar(pro);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "Eliminado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminarC" + e.getMessage());
        }
    }

    public void limpiar() {
        pro = new Producto();
    }

    public void listar() {
        try {
            listadoPro = dao.listar();
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void listarAgotado() {
        try {
            lisProAgo = dao.listarAgotado();
        } catch (Exception e) {
            System.out.println("Error en listarAgotado" + e.getMessage());
        }
    }
    
     public boolean updateExiste(Producto pro, List<Producto> listaModelo) {
        for (Producto producto : listaModelo) {
            if (pro.getNombre().equals(producto.getNombre())&& pro.getTamano().equals(producto.getTamano())) {
                return pro.getNombre().equals(pro.getNombre()) && pro.getTamano().equals(pro.getTamano());
            }
        }
        return true;
    }

    public Producto getPro() {
        return pro;
    }

    public void setPro(Producto pro) {
        this.pro = pro;
    }

    public ProductoImpl getDao() {
        return dao;
    }

    public void setDao(ProductoImpl dao) {
        this.dao = dao;
    }

    public List<Producto> getListadoPro() {
        return listadoPro;
    }

    public void setListadoPro(List<Producto> listadoPro) {
        this.listadoPro = listadoPro;
    }

    public List<Producto> getLisProAgo() {
        return lisProAgo;
    }

    public void setLisProAgo(List<Producto> lisProAgo) {
        this.lisProAgo = lisProAgo;
    }
}
