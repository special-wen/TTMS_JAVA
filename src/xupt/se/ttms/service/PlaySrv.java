package xupt.se.ttms.service;
import java.util.List;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
public class PlaySrv {
private iPlayDAO pDAO=DAOFactory.creatPlayDAO();
	
	public int add(Play pla,String image){
		return pDAO.insert(pla,image); 		
	}
	
	public int modify(Play pla,String image){
		return pDAO.update(pla,image); 		
	}
	
	public int delete(int ID){
		return pDAO.delete(ID); 		
	}
	
	public List<Play> Fetch(String condt){
		return pDAO.select(condt);		
	}
	
	public List<Play> FetchAll(){
		return pDAO.select("");		
	}
	public List<Play> Fetchname(int t){
		return pDAO.select11(t);		
	}
}
