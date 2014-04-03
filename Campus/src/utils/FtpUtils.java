/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPHTTPClient;


/**
 *
 * @author biron
 */
public class FtpUtils {
  
  private static final boolean ENABLE_PROXY  = false;
  
  private static final String PROXY_HOST  = "172.16.13.3";
  private static final int PROXY_PORT     = 8080;
  private static final String PROXY_USER  = "d.gauthier";
  private static final String PROXY_PWD   = "Bbfbxh16";
  
  private static final String URL_FTP           = "www.ok-team.com";
  private static final String USER_FTP          = "okteamco";
  private static final String PWD_FTP           = "6Hklq718eD";
  private static final String PATH_DOSSIER_FTP  = "/campus_project/";
  
  private static final String PATH_DOSSIER_LOCAL = "./remoteFiles/";
  
  private static FTPClient ftpClient;
  
  
  
  public static String[] browseFileNamesOnFtp(){
    
    String[] fileNames = null;
    connectFtp();
    
    try {
      // Obtain a list of filenames in the current working directory.
      // When no file found an empty array will be returned.
      fileNames = ftpClient.listNames(PATH_DOSSIER_FTP);
      //fileNames = ftpClient.listNames();
      
      return fileNames;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      disconnectFtp();
    }
    return fileNames;
  }
  
  
  
  public static boolean uploadFileOnFtp(String pFileName, File pFile){
    
    boolean retour = false;
    
    try {
      connectFtp();
      
      // Create an InputStream of the file to be uploaded
      FileInputStream fis = new FileInputStream(pFile);
      
      // Store file to server
      retour = ftpClient.storeFile(PATH_DOSSIER_FTP + pFileName, fis);
      
      return retour;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      disconnectFtp();
    }
    
    return retour;
  }
  
  
  
  
  
  public static boolean deleteFileOnFtp(String pFileName){
    
    boolean retour = false;
    
    try {
      connectFtp();
      
      // Delete file on the FTP server. When the FTP delete complete it returns true.
      retour = ftpClient.deleteFile(PATH_DOSSIER_FTP + pFileName);
      
      return retour;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      disconnectFtp();
    }
    
    return retour;
  }
  
  
  
  
  /**
   * Télécharge 
   * 
   * @param pFileName
   * @return 
   */
  public static boolean downloadFromFtp(String pFileName){
    
    boolean retour = false;
    FileOutputStream fos;
    connectFtp();
    
    try {
      // The remote filename to be downloaded.
      fos = new FileOutputStream(PATH_DOSSIER_LOCAL + pFileName);
      
      // Download file from FTP server
      retour = ftpClient.retrieveFile(PATH_DOSSIER_FTP + pFileName, fos);
      
      return retour;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      disconnectFtp();
    }
    
    return retour;
  }
  
  
  
  
  
  
  
  
  /**
   * Se connecte au FTP
   */
  private static void connectFtp()
  {
    if(ENABLE_PROXY){
      ftpClient = new FTPHTTPClient(PROXY_HOST, PROXY_PORT, PROXY_USER, PROXY_PWD);
    }
    else{
      ftpClient = new FTPClient();
    }
    
    try {
      ftpClient.connect(URL_FTP);
      ftpClient.login(USER_FTP, PWD_FTP);
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  /**
   * Se deconnecte du FTP
   */
  private static void disconnectFtp()
  {
    try {
      ftpClient.logout();
      ftpClient.disconnect();
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  
  
  
}
