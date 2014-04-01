/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


/**
 *
 * @author biron
 */
public class FtpUtils {
  
  private static final String URL_FTP = "www.ok-team.com";
  private static final String USER_FTP = "okteamco";
  private static final String PWD_FTP = "6Hklq718eD";
  
  private FTPClient ftpClient;
  
  
  public String[] browseFileNamesOnFtp(){
    
    String[] fileNames = null;
    
    try {
      this.ftpClient.connect(URL_FTP);
      this.ftpClient.login(USER_FTP, PWD_FTP);
      
      // Obtain a list of filenames in the current working directory.
      // When no file found an empty array will be returned.
      fileNames = this.ftpClient.listNames();
      for (String name : fileNames) {
          System.out.println("Name = " + name);
      }
      
      this.ftpClient.logout();
      return fileNames;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try
      {
        this.ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return fileNames;
  }
  
  
  
  public boolean uploadFileOnFtp(String pFileName, File pFile){
    
    boolean retour = false;
    
    try {
      this.ftpClient.connect(URL_FTP);
      this.ftpClient.login(USER_FTP, PWD_FTP);
      
      // Create an InputStream of the file to be uploaded
      String filename = pFileName;
      FileInputStream fis = new FileInputStream(pFile);
      
      // Store file to server
      retour = this.ftpClient.storeFile(filename, fis);
      
      this.ftpClient.logout();
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try
      {
        this.ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    return retour;
  }
  
  
  
  
  
  public boolean downloadFromFtp(){
    return false;
  }
  
  
}
