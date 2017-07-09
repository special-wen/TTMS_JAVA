package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iStudioDAO;
import xupt.se.ttms.model.Studio;

public class StudioSrv {
	private iStudioDAO stuDAO=DAOFactory.creatStudioDAO();
	
	public int add(Studio stu){
		return stuDAO.insert(stu); 		
	}
	
	public int modify(Studio stu){
		return stuDAO.update(stu); 		
	}
	
	public int delete(int ID){
		return stuDAO.delete(ID); 		
	}
	
	public List<Studio> Fetch(String condt){
		return stuDAO.select(condt);		
	}
	
	public List<Studio> FetchAll(){
		return stuDAO.select("");		
	}
    public List<Studio> Fetchname(String condt){
    	return stuDAO.select11(condt);
    }
	public  List<Studio> Fetchs(int studio_id) {
		// TODO Auto-generated method stub
		return stuDAO.select(studio_id);
	}
}
