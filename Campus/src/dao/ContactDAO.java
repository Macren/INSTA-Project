/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.IDAO.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Contact;

/**
 *
 * @author Thierry
 */
public class ContactDAO implements IDAO<Contact> {

    public ContactDAO() {
    }

    public ContactDAO(String pUrl) {
        this.db.setUrl(pUrl);
    }

    @Override
    public int insert(Contact pContact) {
        Connection cnx = null;

        try {
            cnx = db.connect();

            String sql = "INSERT INTO `contact`(`id_user_1`,`id_user_2`) VALUES (?,?);";

            PreparedStatement stat = cnx.prepareStatement(sql);

            stat.setInt(1, pContact.getUtilisateur1().getId());
            stat.setInt(2, pContact.getUtilisateur2().getId());

            stat.executeUpdate();

            return 1;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.disconnect(cnx);
        }

        return 0;
    }

    @Override
    public boolean update(Contact pContact) {
        Connection cnx = null;

        try {
            cnx = db.connect();

            String sql = "UPDATE `contact` SET `id_user_1` = ?, `id_user_2`=? WHERE `id_user_1` = ?;";

            PreparedStatement stat = cnx.prepareStatement(sql);

            stat.setInt(1, pContact.getUtilisateur1().getId());
            stat.setInt(2, pContact.getUtilisateur2().getId());
            stat.setInt(3, pContact.getUtilisateur1().getId());

            stat.executeUpdate();

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.disconnect(cnx);
        }

        return false;
    }

    @Override
    public boolean delete(Contact pContact) {
        Connection cnx = null;

        try {
            cnx = db.connect();

            String sql = "DELETE FROM `contact` WHERE `id_user_1`= ? AND `id_user_2`= ?;";
            PreparedStatement stat = cnx.prepareStatement(sql);
            stat.setInt(1, pContact.getUtilisateur1().getId());
            stat.setInt(2, pContact.getUtilisateur2().getId());

            stat.executeUpdate();
            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.disconnect(cnx);
        }
        return false;
    }

    @Override
    public Contact selectById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contact> selectAll() {
        List<Contact> listContacts = new ArrayList();

        Connection cnx = null;

        try {
            cnx = db.connect();

            String sql = "SELECT * FROM `contact";
            Statement stat = cnx.createStatement();
            ResultSet res = stat.executeQuery(sql);

//            while (res.next()) {
//
//                Contact contact = new Contact(res.getInt("id_user_1"),res.getInt("id_user_2") );
//                listContacts.add(contact);
//            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.disconnect(cnx);
        }

        return listContacts;
    }

}
