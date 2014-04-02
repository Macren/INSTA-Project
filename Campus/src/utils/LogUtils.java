/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

/**
 *
 * @author biron
 */
public class LogUtils {
  
  private static final String PATH_LOG_FILE = "./log/log.xml";
  
  
  // racine XML
  private static Element racineElement;
  // Document JDOM
  private static Document document;
  
  
  
//  public static void main(String[] args)
//  {
//    addLog("Antonin", "Connection.");
//    addLog("Antonin", "Creation Teacher[id='2', login='vincent.poirier']");
//    addLog("Antonin", "Deconnection");
//    addLog("Thierry", "Connection");
//    addLog("Thierry", "Creation Education[id='5', name='Analyste Informaticien']");
//    addLog("Thierry", "Creation Education[id='6', name='BTS SIO']");
//    addLog("Thierry", "Deconnection");
//    showLogFile();
//  }
  
  
  
  /**
   * Affiche les logs (lus dans le fichier XML) dans la console
   */
  private static void showLogFile(){
    try {
      buildLogFile(PATH_LOG_FILE);
      
      //On crée une List contenant tous les noeuds "log" de l'Element racine
      List listLogs = racineElement.getChildren("log");
      
      //On crée un Iterator sur notre liste
      Iterator i = listLogs.iterator();
      while(i.hasNext())
      {
        // On recrée l'Element courant à chaque tour de boucle afin de
        // pouvoir utiliser les méthodes propres aux Element comme :
        // sélectionner un nœud fils, modifier du texte, etc...
        Element elementCourant = (Element)i.next();
        //On affiche le nom de l’élément courant
        System.out.println(elementCourant.getAttribute("date").getValue() + " - userlogin="
                            + elementCourant.getAttribute("userlogin").getValue()
                            + " : " + elementCourant.getChild("description").getText());
      }
      
    } catch (JDOMException ex) {
      Logger.getLogger(LogUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LogUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  
  
  
  /**
   * Ajoute une ligne (avec le text 'pLogDescription' et le loginuser) dans le
   * fichier xml de log
   * 
   * @param pLogDescription 
   * @param pUserLogin  
   */
  public static void addLog(String pUserLogin, String pLogDescription){
    try {
      buildLogFile(PATH_LOG_FILE);
      
      Element newLogElement = new Element("log");
      
      // On recupere la date actuelle
      DateFormat df             = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
      Date dateActuelle         = Calendar.getInstance().getTime();
      String dateActuelleString = df.format(dateActuelle);
      
      Attribute dateAttribut = new Attribute("date", dateActuelleString);
      newLogElement.setAttribute(dateAttribut);
      
      Attribute userloginAttribut = new Attribute("userlogin", pUserLogin);
      newLogElement.setAttribute(userloginAttribut);
      
      Element descriptionElement = new Element("description");
      descriptionElement.setText(pLogDescription);
      
      newLogElement.addContent(descriptionElement);
      
      racineElement.addContent(newLogElement);
      
      saveLogFile(PATH_LOG_FILE);
    } catch (JDOMException ex) {
      Logger.getLogger(LogUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LogUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
  
  /**
   * Créer, initialise, et sauvegarde le fichier xml de log
   * 
   * @throws IOException 
   */
  private static void initLogFile() throws IOException{
    racineElement = new Element("Logs");
    document      = new Document(racineElement);
    
    //On crée un nouvel Element log et on l'ajoute
    //en tant qu'Element de racine
    Element logElement = new Element("log");
    racineElement.addContent(logElement);
    
    // On recupere la date actuelle
    DateFormat df             = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date dateActuelle         = Calendar.getInstance().getTime();
    String dateActuelleString = df.format(dateActuelle);
    
    
    //On crée un nouvel Attribut dateAttribut et on l'ajoute à logElement
    //grâce à la méthode setAttribute
    Attribute dateAttribut = new Attribute("date", dateActuelleString);
    logElement.setAttribute(dateAttribut);
    
    // Nouvel attributs: userlogin
    Attribute userloginAttribut = new Attribute("userlogin", "ROOT");
    logElement.setAttribute(userloginAttribut);
    
    //On crée un nouvel Element descriptionElement, on lui assigne du texte
    //et on l'ajoute en tant qu'Element de log
    Element descriptionElement = new Element("description");
    descriptionElement.setText("Initialisation de l'application Campus.");
    logElement.addContent(descriptionElement);
    
    saveLogFile(PATH_LOG_FILE);
  }
  
  
  
  
  /**
   * Instancie 'document' et 'racineElement' grace au chemin
   * du fichier xml passé en param
   * 
   * @param pFilePath
   * @throws JDOMException
   * @throws IOException 
   */
  private static void buildLogFile(String pFilePath) throws JDOMException, IOException{
    // si le fichier n'existe pas
    if(!new File(PATH_LOG_FILE).exists())
    {
      initLogFile(); // On va créer le fichier
    }
    SAXBuilder sxb  = new SAXBuilder();
    document        = sxb.build(new File(pFilePath));
    racineElement   = document.getRootElement();
  }
  
  /**
   * Sauvegarde le fichier xml dont le chemin est passé en param
   * 
   * @param pFilePath
   * @throws FileNotFoundException
   * @throws IOException 
   */
  private static void saveLogFile(String pFilePath) throws FileNotFoundException, IOException
  {
    XMLOutputter exit = new XMLOutputter(Format.getPrettyFormat());
    exit.output(document, new FileOutputStream(pFilePath));
  }
  
  
  
  
  /**
   * Supprime le dernier log inséré dans le fichier xml
   */
  public static void deleteLastLog()
  {
    try {
      buildLogFile(PATH_LOG_FILE);
      
      // traitement
      int nbElemLog = racineElement.getChildren().size();
      racineElement.getChildren().remove(nbElemLog-1);
      
      saveLogFile(PATH_LOG_FILE);
    } catch (JDOMException ex) {
      Logger.getLogger(LogUtils.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LogUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  
}
