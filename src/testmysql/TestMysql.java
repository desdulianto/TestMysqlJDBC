/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class TestMysql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con = null;
        Statement st   = null;
        ResultSet rs   = null;
        
        // url konfigurasi koneksi ke mysql server
        // syntax nya jdbc:mysql://<namahost/ip>/<namadatabase>[?opsi]
        // ini misalnya mau konek ke mysql yang ada di localhost,
        // kalau mau konek ke mysql yang ada di cloud
        // masukkan ip/hostname sebagai ganti localhost
        // PERHATIAN: mysql server di cloud harus membolehkan akses dari
        // host lain (listen ke jaringan) dan user diperbolehkan
        // konek dari host lain             
        String url = "jdbc:mysql://localhost/web?useSSL=false";
        String user = "web";
        String password = "web";
        
        
        try {
            // buka koneksi
            con = DriverManager.getConnection(url, user, password);
            // lakukan query
            st = con.createStatement();
            // eksekusi query
            rs = st.executeQuery("select * from produk");
            
            // tampilkan hasil
            if (rs.next()) {
                System.out.println("ID: " + rs.getString(1));
                System.out.println("Nama Produk: " + rs.getString(2));
                System.out.println("Harga: " + rs.getInt(3));
            }
            
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(TestMysql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            
        // tutup seluruh koneksi
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                
                if (st != null) {
                    st.close();                   
                }
                
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(TestMysql.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
    
}
