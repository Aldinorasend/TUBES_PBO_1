/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.dao;
import bukutelepon.koneksi.Koneksi;
import bukutelepon.model.bukutelepon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Aldino
 */
public class daoBukuTelepon implements implementBukuTelepon{
    
    Connection connection;
    final String insert     =   "INSERT INTO bukutelepon (nomer, nama, alamat) VALUES (?, ?, ?);";
    final String update     =   "UPDATE bukutelepon set nomer=?, nama=?, alamat=? where id =?;";
    final String delete     =   "DELETE FROM bukutelepon where id=?;";
    final String select     =   "SELECT * FROM bukutelepon;";
    final String carinama   =   "SELECT * FROM bukutelepon where nama like ?;";
    
    public daoBukuTelepon(){
        connection = Koneksi.getconnection();
    }

    @Override
    public void insert(bukutelepon b) {
        PreparedStatement stm = null;
        try{
            stm = connection.prepareStatement(insert,stm.RETURN_GENERATED_KEYS);
            stm.setString(1, b.getNomer());
            stm.setString(2, b.getNama());
            stm.setString(3, b.getAlamat());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            while(rs.next()){
                b.setId(rs.getInt(1));
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            try{
                stm.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(bukutelepon b) {
       PreparedStatement stm = null;
       try{
            stm = connection.prepareStatement(update);
            stm.setString(1, b.getNomer());
            stm.setString(2, b.getNama());
            stm.setString(3, b.getAlamat());
            stm.setInt(4, b.getId());
        
            stm.executeUpdate();
       }
       catch(SQLException ex){
            ex.printStackTrace();
        }
       finally{
            try{
                stm.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement stm = null;
       try{
            stm = connection.prepareStatement(delete);
          
            stm.setInt(1, id);
            stm.executeUpdate();
       }
       catch(SQLException ex){
            ex.printStackTrace();
        }
       finally{
            try{
                stm.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<bukutelepon> getALL() {
       List<bukutelepon> lb = null;
       try{
           lb = new ArrayList<bukutelepon>();
           Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery(select);
           while(rs.next()){
               bukutelepon b = new bukutelepon();
               b.setId(rs.getInt("id"));
               b.setNomer(rs.getString("nomer"));
               b.setNama(rs.getString("nama"));
               b.setAlamat(rs.getString("alamat"));
               lb.add(b);
           }
       }
       catch(SQLException ex){
            Logger.getLogger(daoBukuTelepon.class.getName()).log(Level.SEVERE, null, ex);
        }
            return lb;
    }
    

    @Override
    public List<bukutelepon>getCariNama(String nama) {
         List<bukutelepon> lb = null;
       try{
           lb = new ArrayList<bukutelepon>();
           PreparedStatement st = connection.prepareStatement(carinama);
           st.setString(1, "%" + nama + "%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               bukutelepon b = new bukutelepon();
               b.setId(rs.getInt("id"));
               b.setNomer(rs.getString("nomer"));
               b.setNama(rs.getString("nama"));
               b.setAlamat(rs.getString("alamat"));
               lb.add(b);
           }
       }
       catch(SQLException ex){
            Logger.getLogger(daoBukuTelepon.class.getName()).log(Level.SEVERE, null, ex);
        }
            return lb;
    }
    
    
}
