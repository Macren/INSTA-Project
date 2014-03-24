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
    
    public  DB      db = new DB(); 
    public  void    insert(T objet);
    public  void    update(T objet);
    public  void    delete(T objet);
    public  T       selectById(int id);
    public  List<T> selectAll();
}
