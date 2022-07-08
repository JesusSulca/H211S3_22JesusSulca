package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import modelo.VentaDetalle;


public class VentaImpl extends Conexion implements ICRUD<Venta> {

    DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void registrar(Venta ven) throws Exception {
        try {
            String sql = "insert into VENTA"
                    + "(FECVEN,IDCLI,IDSU,IDVEND,TIPVEN)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(ven.getFechaVenta()));
            ps.setInt(2, 1);
            ps.setInt(3, 1);
            ps.setInt(4, 1);
            ps.setString(5, ven.getTipoventa());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar/VentaImpl" + e.getMessage());
        }
    }

    @Override
    public void modificar(Venta generic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Venta generic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> listar() throws Exception {
        //throws new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public List<Venta> listarAgotado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

/*Función para el autocomplit*/
    public List<String> autocompletar(String consulta) throws Exception {
        Producto pro = new Producto();
        List<String> listado = new ArrayList<>();
        String sql = "Select * from PRODUCTO WHERE NOMPRO LIKE ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pro.setNombre(rs.getString("NOMPRO"));
                listado.add(rs.getString("NOMPRO"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en autocompletar Producto " + e.getMessage());
        }
        return listado;

    }
    
/*Mostrar datos despues del autocomplit con el nombre del Producto */
    
    public void mostrarDatos(Producto pro) throws Exception {
        try {
            String sql = "Select * from PRODUCTO where NOMPRO =?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                pro.setCodigo(rs.getString("CODPRO"));
                pro.setTipo(rs.getString("TIPPRO"));
                pro.setNombre(rs.getString("NOMPRO"));
                pro.setPrecio(rs.getDouble("PREPRO"));
                pro.setTamano(rs.getString("TAMPRO"));
                pro.setStock(rs.getInt("STOCKPRO"));
                pro.setEstado(rs.getString("ESTPRO"));
                pro.setDetalle(rs.getString("DETPRO"));
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en mostrar datos" + e.getMessage());
        }
    }

    /* Buscador de cliente por DNI */
    public void filtrarCliente(Cliente cli) throws Exception {
        try {
            String sql = "select * from cliente where DNICLI =" + cli.getDni();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cli.setId(rs.getInt("IDCLI"));
                cli.setNombre(rs.getString("NOMCLI"));
                cli.setApellido(rs.getString("APECLI"));
                cli.setEmail(rs.getString("EMACLI"));
                cli.setCelular(rs.getString("CELCLI"));
                cli.setDni(rs.getString("DNICLI"));
                cli.setDireccion(rs.getString("DIRCLI"));
                cli.setEstado(rs.getString("ESTCLI"));
                cli.setFechaNacimiento(rs.getString("FECNACCLI"));
                cli.setUbigeo(rs.getString("CODUBI"));
                cli.setIdlog(rs.getInt("IDLOG"));
            }
        } catch (Exception e) {
            System.out.println("error en filtrarCliente/VentaImpl");
        }

    }

    public int ventasMaximas() throws Exception {
        /*X almacenara el resultado de la consulta al SQL Server*/
        int x = 0;
        String sql = "SELECT MAX(IDVEN) FROM VENTA";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                x = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error en ventas Maximas" + e.getMessage());
        }
        return x;

    }

    public void registrarVentaDetalle(VentaDetalle Vendet) throws Exception {
        
        String sql = "INSERT INTO VENTA_DETALLE"
                + "(CANVENDET,IDVEN,CODPRO)"
                + "VALUES (?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, Vendet.getCantidad());
            ps.setInt(2, Vendet.getIdVenta());
            ps.setString(3, Vendet.getCodigoProducto());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar VentaDetalle/VentaDetalleImpl " + e.getMessage());
        }
    }

    public void ActualizarStock(VentaDetalle vd) {
        int cantidad = vd.getCantidad();
        String codpro = vd.getCodigoProducto();
        String sql = "UPDATE producto SET STOCKPRO = STOCKPRO - '" + cantidad + "' where CODPRO = '" + codpro + "'";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en Actualizar Stock" + e.getMessage());
        }
    }

    
    @Override
    public List<Venta> Listado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
