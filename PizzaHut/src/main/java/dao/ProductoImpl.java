
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;


public class ProductoImpl extends Conexion implements ICRUD<Producto>{

    
    
    
    @Override
    public void registrar(Producto producto) throws Exception {
//        Date fecha = (Date) cliente.getFechaNacimiento();
//        System.out.println("Fecha directa :" + UtilToSql.convert(cliente.getFechaNacimiento()));
//        System.out.println("Fecha con (Date):" + UtilToSql.convert(fecha));
if(producto.getCodigo().equals("")||producto.getTipo().equals("")||producto.getNombre().equals("")||producto.getTamano().equals("")
        ){
    System.out.println("error");
}else{
    try {
            String sql = "INSERT INTO PRODUCTO"
                    + " (CODPRO, TIPPRO , NOMPRO, PREPRO,TAMPRO, STOCKPRO, ESTPRO, DETPRO)"
                    + " values (?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getNombre());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5, producto.getTamano());
            ps.setInt(6, producto.getStock());
            ps.setString(7, "A");
            ps.setString(8, producto.getDetalle());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/Registrar: " + e.getMessage());
        }
}
        
    }

    @Override
    public void modificar(Producto pro) throws Exception {

        String sql = "UPDATE producto SET NOMPRO=?, PREPRO=?,TIPPRO=?,TAMPRO=?,STOCKPRO=?,DETPRO=? , ESTPRO=? where CODPRO=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
       //     ps.setString(1, pro.getCodigo());
            ps.setString(1, pro.getNombre());
            ps.setDouble(2, pro.getPrecio());
            ps.setString(3, pro.getTipo());
            ps.setString(4, pro.getTamano());
            ps.setInt(5, pro.getStock());
            ps.setString(6, pro.getDetalle());
            ps.setString(7, "A" );
            ps.setString(8, pro.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/Modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Producto pro) throws Exception {
        try {
            String sql = "update PRODUCTO set ESTPRO=? where CODPRO=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1,pro.getEstado());
            ps.setString(2,pro.getCodigo());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/Eliminar: " + e.getMessage());
        }
    }
 
    @Override
    public List listar() throws Exception {

        List<Producto> listado = null;
        Producto pro;
        String sql = "SELECT * FROM PRODUCTO WHERE ESTPRO = 'A' order by CODPRO DESC";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pro = new Producto();
                pro.setCodigo(rs.getString("CODPRO"));
                pro.setTipo(rs.getString("TIPPRO"));
                pro.setNombre(rs.getString("NOMPRO"));
                pro.setPrecio(rs.getDouble("PREPRO"));
                pro.setTamano(rs.getString("TAMPRO"));
                pro.setStock(rs.getInt("STOCKPRO"));
                pro.setEstado(rs.getString("ESTPRO"));
                pro.setDetalle(rs.getString("DETPRO"));
                listado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en ProductoImpl/Listar: " + e.getMessage());
        }
        return listado;
    }

    @Override
    public List listarAgotado() throws Exception {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Producto> listadoAgotado = null;
        Producto pro;
        String sql = "Select * from PRODUCTO where STOCKPRO = 0 ";

        try {
            listadoAgotado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                pro = new Producto();
                pro.setCodigo(rs.getString("CODPRO"));
                pro.setNombre(rs.getString("NOMPRO"));
                pro.setPrecio(rs.getDouble("PREPRO"));
                pro.setTipo(rs.getString("TIPPRO"));
                pro.setTamano(rs.getString("TAMPRO"));
                pro.setStock(rs.getInt("STOCKPRO"));
                pro.setEstado(rs.getString("ESTPRO"));
                pro.setDetalle(rs.getString("DETPRO"));
                listadoAgotado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar Productos Agotados " + e.getMessage());
        }
        return listadoAgotado;

    }

    @Override
    public List<Producto> Listado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean existe(Producto modelo, List<Producto> listaModelo) {
        for (Producto pro : listaModelo) {
            if (modelo.getCodigo().equals(pro.getCodigo())||modelo.getNombre().equals(pro.getNombre()) && modelo.getTamano().equals(pro.getTamano())) {
                return false;
            }
        }
        return true;
    }
    
}
