/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.controller;
import java.sql.SQLException;
import bukutelepon.dao.daoBukuTelepon;
import bukutelepon.dao.implementBukuTelepon;
import bukutelepon.model.bukutelepon;
import bukutelepon.model.tableModelBukuTelepon;
import bukutelepon.view.FrameTelepon;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author Aldino
 */
public class controllerBukuTelepon {
    
    FrameTelepon frame;
    implementBukuTelepon implBukuTelepon;
    List<bukutelepon> lb;
    
    public controllerBukuTelepon(FrameTelepon frame){
        this.frame = frame;
        implBukuTelepon = new daoBukuTelepon();
        lb = implBukuTelepon.getALL();
    }
    
    //Mengosongkan field
    public void reset(){
        frame.getTxtID().setText("");
        frame.getTxtNama().setText("");
        frame.getTxtAlamat().setText("");
        frame.getTxtNoTelp().setText("");
    }
    
    //menampilkan data ke dalam tabel
    public void isiTable(){
        lb = implBukuTelepon.getALL();
        tableModelBukuTelepon tmb = new tableModelBukuTelepon(lb);
        frame.getTableData().setModel(tmb);
    }
    
    //merupakan fungsi untuk menampilkan data yang dipilih dari tabel
    public void isiField(int row){
        frame.getTxtID().setText(lb.get(row).getId().toString());
        frame.getTxtNama().setText(lb.get(row).getNama());
        frame.getTxtAlamat().setText(lb.get(row).getAlamat());
        frame.getTxtNoTelp().setText(lb.get(row).getNomer());
         
    }
    
    //merupakan fungsi untuk insert data berdasarkan inputan user dari txtField di frame
    public void insert(){
        bukutelepon b = new bukutelepon();
        b.setNomer(frame.getTxtNoTelp().getText());
        b.setAlamat(frame.getTxtAlamat().getText());
        b.setNama(frame.getTxtNama().getText());
        
        implBukuTelepon.insert(b);
    }
    
    //merupakan fungsi untuk update data berdasarkan inputan user dari txtField di frame
    public void update(){
        bukutelepon b = new bukutelepon();
        b.setNomer(frame.getTxtNoTelp().getText());
        b.setAlamat(frame.getTxtAlamat().getText());
        b.setNama(frame.getTxtNama().getText());
        b.setId(Integer.parseInt(frame.getTxtID().getText()));
        
        implBukuTelepon.update(b);
    }
    
    //berfungsi untuk menghapus data yang dipilih
    public void delete(){
        if(!frame.getTxtID().getText().trim().isEmpty()){
            int id = Integer.parseInt(frame.getTxtID().getText());
            implBukuTelepon.delete(id);
        }
        else{
            JOptionPane.showMessageDialog(frame, "Pilih Data yang akan dihapus");
        }
    }
    
    public void isiTableCariNama(){
        lb = implBukuTelepon.getCariNama(frame.getTxtCariNama().getText());
        tableModelBukuTelepon tmb = new tableModelBukuTelepon(lb);
        frame.getTableData().setModel(tmb);
        
    }
    public void carinama(){
        if (!frame.getTxtCariNama().getText().trim().isEmpty()){
           
            implBukuTelepon.getCariNama(frame.getTxtCariNama().getText());
            isiTableCariNama();
           
        }
        else{
            JOptionPane.showMessageDialog(frame, "SILAHKAN PILIH DATA");
        }
    }
}
