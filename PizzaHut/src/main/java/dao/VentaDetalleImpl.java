/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import modelo.VentaDetalle;

/**
 *
 * @author 51992
 */
public class VentaDetalleImpl extends Conexion implements ICRUD<VentaDetalle>{

      DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void modificar(VentaDetalle generic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(VentaDetalle generic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaDetalle> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaDetalle> listarAgotado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void registrar(VentaDetalle generic) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaDetalle> Listado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /*  Grafico */
    
    public void prueba() throws Exception{
        System.out.println("hola");
    }
    
    public int graficar() throws Exception{
        System.out.println("asd");
        VentaDetalle vd  = new VentaDetalle();
        int cantmax = 0 ;
        String sql = "select sum(canvendet) from datosventas where fecven = ? ";
        try{
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, formato.format(vd.getFechapruebas()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                cantmax = rs.getInt(1);
                System.out.println(cantmax);
            }
        }catch (Exception e){
            System.out.println("error en graficar/VentaDetalleImpl" + e);
        }
          return cantmax;
  
}
}