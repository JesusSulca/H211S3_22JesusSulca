package controlador;

import dao.ClienteImpl;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import modelo.Cliente;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Ubigeo;
import reporte.reportesCliente;
@Named(value = "ClienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private int Listado = 1;
    private Cliente cli;
    private Ubigeo ubi;
    private ClienteImpl dao;
    private List<Cliente> listadoCli;
    private List<Ubigeo> listarUbi;
    private Cliente selectedCliente = new Cliente();

    public ClienteC() {
        cli = new Cliente();
        dao = new ClienteImpl();
        ubi = new Ubigeo();

    }

    public void registrar() throws Exception {
        try {
            if (cli.getDni().length() == 8 && cli.getCelular().length() == 9) {
                if (dao.existe(cli, listadoCli)) {
                    cli.setEstado("A");
                    cli.setNombre(caseMayuscula(cli.getNombre()));
                    cli.setApellido(caseMayuscula(cli.getApellido()));
                    cli.setEmail(caseMinuscula(cli.getEmail()));
                    cli.setDireccion(caseMinuscula(cli.getDireccion()));
                    dao.registrar(cli);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registrado con éxito"));
                    limpiar();
                    listar();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "Cliente existente"));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "Ingresar datos correctos"));
            }
        } catch (Exception e) {
            System.out.println("error en registar/ClienteC" + e);
        }
    }

    public String caseMinuscula(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {  // Si se encuentra el primer carácter de una palabra
                if (ch[i] >= 'A' && ch[i] <= 'Z') {      // Si está en mayúsculas
                    ch[i] = (char) (ch[i] - 'A' + 'a');  // Convertir en minúsculas
                }
            } // Si aparte del primer carácter cualquiera está en mayúsculas
            else if (ch[i] >= 'A' && ch[i] <= 'Z') {     // Convertir en minúsculas
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        camelcase = st;
        return camelcase;
    }

    public String caseMayuscula(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {  // Si se encuentra el primer carácter de una palabra
                if (ch[i] >= 'a' && ch[i] <= 'z') {      // Si está en minúsculas
                    ch[i] = (char) (ch[i] - 'a' + 'A');  // Convertir en mayúsculas
                }
            } // Si aparte del primer carácter cualquiera está en mayúsculas
            else if (ch[i] >= 'A' && ch[i] <= 'Z') {     // Convertir en minúsculas
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        camelcase = st;
        return camelcase;
    }

//    public void modificar() throws Exception {
//        try {
//            cli.setEstado("A");
//            cli.setNombre(caseMayuscula(cli.getNombre()));
//            cli.setApellido(caseMayuscula(cli.getApellido()));
//            cli.setEmail(caseMinuscula(cli.getEmail()));
//            cli.setDireccion(caseMinuscula(cli.getDireccion()));
//            dao.modificar(cli);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con exito"));
//            limpiar();
//            listar();
//        } catch (Exception e) {
//            System.out.println("Error en modificarC" + e.getMessage());
//        }
//    }
    public void eliminar() throws Exception {
        try {
            cli.setEstado("I");
            dao.eliminar(cli);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Eliminado con éxito"));
            limpiar();
            listar();

        } catch (Exception e) {
            System.out.println("Error en eliminarC/ClienteC" + e.getMessage());
        }
    }
    
    public void modificar() throws Exception {
        try {
            if (cli.getDni().length() == 8 && cli.getCelular().length() == 9) {
                if (updateExiste(cli, listadoCli)) {
                    cli.setNombre(caseMayuscula(cli.getNombre()));
                    cli.setApellido(caseMayuscula(cli.getApellido()));
                    cli.setEmail(caseMinuscula(cli.getEmail()));
                    cli.setDireccion(caseMinuscula(cli.getDireccion()));
                    dao.modificar(cli);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificado con éxito"));
                    limpiar();
                    listar();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Numero de DNI repetido"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Ingrese datos coherentes"));
            }
        } catch (Exception e) {
            System.out.println("error en modificar/ClienteC : " + e);
        }
    }

   public void restaurar() throws Exception {
       try {
           dao.restaurar(cli);
           limpiar();
           listar();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ok", "Se restauro correctamente"));
       } catch (Exception e) {
           System.out.println("error en restaurar/ClienteC " + e);
       }
   }
   
   public void verReportePDFEST() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        reportesCliente reCliente = new reportesCliente();
        FacesContext facescontext = FacesContext.getCurrentInstance();
        ServletContext servletcontext = (ServletContext) facescontext.getExternalContext().getContext();
        String root = servletcontext.getRealPath("img/clienteHut.jasper");
        reCliente.getReportePdf(root);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    
    public void limpiar() {
        cli = new Cliente();
    }

    public boolean updateExiste(Cliente cli, List<Cliente> listaModelo) {
        for (Cliente cliente : listaModelo) {
            if (cli.getDni().equals(cliente.getDni())) {
                return cli.getId() == cli.getId();
            }
        }
        return true;
    }

    public void Filtrado() throws Exception {

        try {
            dao.filtrarCliente(cli);

        } catch (Exception e) {
            System.out.println("Error en filtrar: " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listadoCli = dao.listar(Listado);
        } catch (Exception e) {
            System.out.println("Error en listarC " + e.getMessage());
        }
    }

    public void datos() {
        this.cli = selectedCliente;
        System.out.println(selectedCliente);
    }

    public List<Ubigeo> listarUbigeo() {

        try {
            listarUbi = dao.listarUbi();
        } catch (Exception e) {
            System.out.println("Error en listar Ubigeo" + e.getMessage());
        }
        return listarUbi;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public ClienteImpl getDao() {
        return dao;
    }

    public void setDao(ClienteImpl dao) {
        this.dao = dao;
    }

    public List<Cliente> getListadoCli() {
        return listadoCli;
    }

    public void setListadoCli(List<Cliente> listadoCli) {
        this.listadoCli = listadoCli;
    }

    public int getListado() {
        return Listado;
    }

    public void setListado(int Listado) {
        this.Listado = Listado;
    }

    public Ubigeo getUbi() {
        return ubi;
    }

    public void setUbi(Ubigeo ubi) {
        this.ubi = ubi;
    }

    public List<Ubigeo> getListarUbi() {
        return listarUbi;
    }

    public void setListarUbi(List<Ubigeo> listarUbi) {
        this.listarUbi = listarUbi;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

}

