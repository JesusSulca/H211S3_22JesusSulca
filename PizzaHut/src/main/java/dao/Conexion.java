package dao;

import java.sql.Connection;
import java.sql.DriverManager;


//
public class Conexion {
//
    public static Connection cnx = null;

    public static Connection conectar() throws Exception {

        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://localhost:1433;databaseName=bdPizzaHut";
            String user = "sa";
            String pwd = "123456";
//            
//            String driver="com.mysql.jdbc.Driver";
//            String url="jdbc:mysql://localhost:3306/BDFarmacia";
//            String user="root";
//            String pwd="";

            Class.forName(driver);
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("Error de Conexión" + "  " + e.getMessage() + "  " + e.getStackTrace());
        }
        return cnx;
    }
    
    
    public static void main(String[] args) throws Exception {
        try {
            Conexion.conectar();
            if (Conexion.cnx != null) {
                System.out.println("Estoy Conectado");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
            System.out.println("Cerrado");
        }
    }

}

//    public static Connection cnx = null;
//
//    public static Connection conectar() throws Exception {
//        InputStream inputStream
//                = Conexion.class.getClassLoader().getResourceAsStream("properties/db.properties");
//        Properties properties = new Properties();
//        try {
//            properties.load(inputStream);
//            String user = properties.getProperty("user");
//            String pwd = properties.getProperty("pwd");
//            String driver = properties.getProperty("driver");
//            String url = properties.getProperty("url");
//            Class.forName(driver).newInstance();
//            cnx = DriverManager.getConnection(url, user, pwd);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error de conexión, revise xfa");
//            System.out.println("error de conexion " + e.getMessage());
//        }
//        return cnx;
//    }
//
//    public void cerrar() throws Exception {
//        if (cnx != null) {
//            cnx.close();
//            System.out.println("Cerrado");
//        }
//    }
//    public static void main(String[] args) throws Exception {
//        conectar();
//        if (cnx != null) {
//            System.out.println("esta abierta, jojolete");
//        } else {
//            System.out.println("fijate el driver, conexión cerrada, etc....");
//        }
//    }
//}
//
//
