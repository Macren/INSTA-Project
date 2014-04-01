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
import java.net.InetSocketAddress;
import java.net.Proxy;
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
  
  private static final String PROXY_HOST = "172.16.13.3";
  private static final int PROXY_PORT = 8080;
  private static final String PROXY_USER = "d.gauthier";
  private static final String PROXY_PWD = "Bbfbxh16";
  
  private static final String URL_FTP = "www.ok-team.com";
  private static final String USER_FTP = "okteamco";
  private static final String PWD_FTP = "6Hklq718eD";
  private static final String PATH_DOSSIER = "/campus_project/";
  
  private static final String PATH_DOSSIER_LOCAL = "./";
  
  private static FTPClient ftpClient = new FTPHTTPClient(PROXY_HOST, PROXY_PORT, PROXY_USER, PROXY_PWD);
  
  public static String[] browseFileNamesOnFtp(){
    
    String[] fileNames = null;
    
    try {
      ftpClient.connect(URL_FTP);
      ftpClient.login(USER_FTP, PWD_FTP);
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      
      // Obtain a list of filenames in the current working directory.
      // When no file found an empty array will be returned.
      fileNames = ftpClient.listNames(PATH_DOSSIER);
      //fileNames = ftpClient.listNames();
      
      ftpClient.logout();
      return fileNames;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try
      {
        ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return fileNames;
  }
  
  
  
  public static boolean uploadFileOnFtp(String pFileName, File pFile){
    
    boolean retour = false;
    
    try {
      ftpClient.connect(URL_FTP);
      ftpClient.login(USER_FTP, PWD_FTP);
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      
      // Create an InputStream of the file to be uploaded
      String filename = PATH_DOSSIER + pFileName;
      FileInputStream fis = new FileInputStream(pFile);
      
      // Store file to server
      retour = ftpClient.storeFile(filename, fis);
      
      ftpClient.logout();
      return retour;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try
      {
        ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    return retour;
  }
  
  
  
  
  
  public static boolean deleteFileOnFtp(String pFileName){
    
    boolean retour = false;
    
    try {
      ftpClient.connect(URL_FTP);
      ftpClient.login(USER_FTP, PWD_FTP);
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      
      // Delete file on the FTP server. When the FTP delete complete 
      // it returns true.
      String filename = PATH_DOSSIER + pFileName;
      retour = ftpClient.deleteFile(filename);
      
      ftpClient.logout();
      return retour;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try
      {
        ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    return retour;
  }
  
  
  
  
  
  public static boolean downloadFromFtp(String pFileName){
    
    boolean retour = false;
    FileOutputStream fos = null;
    
    try {
      ftpClient.connect(URL_FTP);
      ftpClient.login(USER_FTP, PWD_FTP);
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      
      // The remote filename to be downloaded.
      String filename = PATH_DOSSIER + pFileName;
      fos = new FileOutputStream(PATH_DOSSIER_LOCAL + "remoteFiles/" + pFileName);
      
      // Download file from FTP server
      retour = ftpClient.retrieveFile(filename, fos);
//      this.ftpClient.logout();
      return retour;
      
    } catch (IOException ex) {
      Logger.getLogger(FtpUtils.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        if (fos != null) {
          fos.close();
        }
        ftpClient.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    return retour;
  }
  
  
  
  
  
  
  
  
}
