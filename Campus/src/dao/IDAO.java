/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;

/**
 *
 * @author Madeleine
 */
public interface IDAO<T> {
    
    public  DB        db = new DB();
    // renvoie un int (l'id (généré par mysql) de l'objet inséré ou bien 0 (zéro)
    public  int       insert(T objet);
    public  boolean   update(T objet);
    public  boolean   delete(T objet);
    public  T         selectById(int id);
    public  List<T>   selectAll();
}
