/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.koneksi;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Aldino
 */
public class Koneksi {
    
    static Connection con;
    
    public static Connection getconnection(){
        if (con == null){
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("bukutelepon");
            data.setUser("root");
            data.setPassword("");
            try{
                con = data.getConnection();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return con;
    }
    
}
