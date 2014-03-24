/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

/**
 *
 * @author Madeleine
 */
public interface IDocUse {
 
    /**
     * boolean uploadDoc()
     * -------------------
     * Will upload a document on FTP server
     * 
     * @param path of document
     * @return true if succeed
     */
    public  boolean uploadDoc(String path);
    
    /**
     * boolean downloadDoc()
     * ---------------------
     * Will download a document on FTP server
     * 
     * @param path of document
     * @return the content of a document
     */
    public  String  downloadDoc(String path);
}
