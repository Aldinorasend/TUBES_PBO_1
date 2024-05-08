package bukutelepon.dao;
import bukutelepon.model.bukutelepon;
 import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aldino
 */
public interface implementBukuTelepon {
    public void insert (bukutelepon b);
    public void update (bukutelepon b);
    public void delete (int id);
    public List<bukutelepon> getALL();
    public List<bukutelepon> getCariNama(String nama);
    
}
