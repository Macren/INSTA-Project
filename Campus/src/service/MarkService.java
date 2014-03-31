/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.MarkDAO;
import java.util.List;
import metier.Mark;

/**
 *
 * @author biron
 */
public class MarkService {
  
  private final MarkDAO markDao;
  
  public MarkService(){
    this.markDao = new MarkDAO();
  }
  
  public int insert(Mark pMark) {
    return this.markDao.insert(pMark);
  }
  
  public boolean update(Mark pMark) {
    return this.markDao.update(pMark);
  }
  
  public boolean delete(Mark pMark) {
    return this.markDao.delete(pMark);
  }
  
  public Mark selectById(int id) {
    return this.markDao.selectById(id);
  }
  
  public List<Mark> selectAll() {
    return this.markDao.selectAll();
  }
  
  
  
}
