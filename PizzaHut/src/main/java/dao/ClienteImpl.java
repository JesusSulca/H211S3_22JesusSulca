package dao;

import java.sql.Statement;
import java.util.List;
import modelo.Cliente;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Ubigeo;

public class ClienteImpl extends Conexion implements ICRUD<Cliente> {

    
    
    @Override
    public void registrar(Cliente cli) throws Exception {
//        Date fecha = (Date) cliente.getFechaNacimiento();
//        System.out.println("Fecha directa :" + UtilToSql.convert(cliente.getFechaNacimiento()));
//        System.out.println("Fecha con (Date):" + UtilToSql.convert(fecha));

        if (cli.getNombre().equals("") || cli.getApellido().equals("") || cli.getDni().equals("")
                || cli.getEmail().equals("") || cli.getCelular().equals("") || cli.getDireccion().equals("")
                || cli.getFechaNacimiento().equals("") || cli.getUbigeo().equals("")) {
            System.out.println("Ingresar datos");
        } else {
            try {
                String sql = "INSERT INTO CLIENTE"
                        + " (NOMCLI, APECLI , EMACLI , CELCLI, DNICLI, DIRCLI, ESTCLI , FECNACCLI , CODUBI,IDLOG)"
                        + " values (?,?,?,?,?,?,?,?,?,?) ";
                PreparedStatement ps = this.conectar().prepareStatement(sql);
                ps.setString(1, cli.getNombre());
                ps.setString(2, cli.getApellido());
                ps.setString(3, cli.getEmail());
                ps.setString(4, cli.getCelular());
                ps.setString(5, cli.getDni());
                ps.setString(6, cli.getDireccion());
                ps.setString(7, "A");
                ps.setString(8, cli.getFechaNacimiento());
                ps.setString(9, cli.getUbigeo());
                ps.setInt(10, 20);
                ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                System.out.println("Error en ClienteImpl/Registrar: " + e.getMessage());
            }
        }
    }

    @Override
    public void modificar(Cliente cli) throws Exception {
        if (cli.getNombre().equals("") || cli.getApellido().equals("") || cli.getDni().equals("")
                || cli.getEmail().equals("") || cli.getCelular().equals("") || cli.getDireccion().equals("")
                || cli.getFechaNacimiento().equals("") || cli.getUbigeo().equals(""))  {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "OK", "No dejar campos vacios"));
        } else {
            String sql = "UPDATE CLIENTE SET NOMCLI=?, APECLI=?,DNICLI=?,EMACLI=?,CELCLI=?,DIRCLI=?,ESTCLI=? , FECNACCLI=?,CODUBI=?  where IDCLI=?";
            try {
                PreparedStatement ps = this.conectar().prepareStatement(sql);
                ps.setString(1, cli.getNombre());
                ps.setString(2, cli.getApellido());
                ps.setString(3, cli.getDni());
                ps.setString(4, cli.getEmail());
                ps.setString(5, cli.getCelular());
                ps.setString(6, cli.getDireccion());
                ps.setString(7, "A");
                ps.setString(8, cli.getFechaNacimiento());
                ps.setString(9, cli.getUbigeo());
                ps.setInt(10, cli.getId());
                ps.executeUpdate();
                ps.close();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Editado con éxito"));
            } catch (Exception e) {
                System.out.println("Error en ClienteImpl/Modificar: " + e.getMessage());
            }
        }

    }
    
     public void restaurar(Cliente cliente) throws Exception{
        try {
            String sql = "UPDATE CLIENTE SET ESTCLI = 'A' WHERE IDCLI = ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1,cliente.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("error en restaurar/ClienteImpl " + e);
        }
    }
    
    
   

    @Override
    public void eliminar(Cliente cli) throws Exception {
        try {
            String sql = "update cliente set ESTCLI=? where DNICLI=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cli.getEstado());
            ps.setString(2, cli.getDni());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en ClienteImpl/Eliminar: " + e.getMessage());
        }
    }

    public List<Cliente> listar(int Listado) throws Exception {
        List<Cliente> listado = null;
        Cliente cli;
        String sql = "";
        switch (Listado) {
            case 1:
                sql = "select * from vActivos ORDER BY IDCLI DESC";
                break;
            case 2:
                sql = "select * from vInactivos ORDER BY IDCLI DESC";
                break;
            case 3:
                sql = "SELECT * FROM CLIENTE ";
                break;
        }
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cli = new Cliente();
                cli.setId(rs.getInt("IDCLI"));
                cli.setNombre(rs.getString("NOMCLI"));
                cli.setApellido(rs.getString("APECLI"));
                cli.setDni(rs.getString("DNICLI"));
                cli.setEmail(rs.getString("EMACLI"));
                cli.setCelular(rs.getString("CELCLI"));
                cli.setDireccion(rs.getString("DIRCLI"));
                cli.setEstado(rs.getString("ESTCLI"));
                cli.setFechaNacimiento(rs.getString("FECNACCLI"));
                cli.setUbigeo(rs.getString("CODUBI"));
                listado.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en ClienteImpl/Listar: " + e.getMessage());
        }
        return listado;
    }

    @Override
    public List listarAgotado() throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Cliente> listadoAgotado = null;
        Cliente cli;
        String sql = "Select * from CLIENTE where ESTCLI = 'I' ";

        try {
            listadoAgotado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                cli = new Cliente();
                cli.setId(rs.getInt("IDCLI"));
                cli.setNombre(rs.getString("NOMCLI"));
                cli.setApellido(rs.getString("APECLI"));
                cli.setDni(rs.getString("DNICLI"));
                cli.setEmail(rs.getString("EMACLI"));
                cli.setCelular(rs.getString("CELCLI"));
                cli.setDireccion(rs.getString("DIRCLI"));
                cli.setEstado(rs.getString("ESTCLI"));
                cli.setFechaNacimiento(rs.getString("FECNACCLI"));
                cli.setUbigeo(rs.getString("CODUBI"));
                listadoAgotado.add(cli);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar Cliente Inactivo/ClieneImpl" + e.getMessage());
        }
        return listadoAgotado;
    }

    public List listarUbi() throws Exception {
        List<Ubigeo> listUbig = null;

        Ubigeo ubi;
        String sql = "select * from UBIGEO";
        try {
            listUbig = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ubi = new Ubigeo();
                ubi.setCodigo(rs.getString("CODUBI"));
                ubi.setDepartamento(rs.getString("DEPA"));
                ubi.setProvincia(rs.getString("PROV"));
                ubi.setDistrito(rs.getString("DIST"));
                listUbig.add(ubi);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en dao listar Ubigeo" + e.getMessage());
        }
        return listUbig;

    }

    public boolean existe(Cliente modelo, List<Cliente> listaModelo) {
        for (Cliente cli : listaModelo) {
            if (modelo.getDni().equals(cli.getDni())) {
                return false;
            }
        }
        return true;
    }

    public void filtrarCliente(Cliente cli) throws Exception {
        try {
            String sql = "select * from CLIENTE where DNICLI =" + cli.getDni();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cli.setId(rs.getInt("IDCLI"));
                cli.setNombre(rs.getString("NOMCLI"));
                System.out.println(cli.getNombre());
                cli.setApellido(rs.getString("APECLI"));
                cli.setDni(rs.getString("DNICLI"));
                cli.setCelular(rs.getString("CELCLI"));
                cli.setDireccion(rs.getString("DIRCLI"));
                cli.setEstado(rs.getString("ESTCLI"));
                cli.setUbigeo(rs.getString("CODUBI"));

            }
        } catch (Exception e) {
            System.out.println("Error en filtrarCliente/ClienteImpl");
        }

    }

    @Override
    public List<Cliente> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> Listado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
