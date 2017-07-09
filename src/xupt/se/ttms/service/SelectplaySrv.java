package xupt.se.ttms.service;
import java.util.List;

import xupt.se.ttms.idao.DAOFactorySelectplay;
import xupt.se.ttms.idao.iSelectplayDAO;
import xupt.se.ttms.model.Selectplay;
public class SelectplaySrv {
	private iSelectplayDAO spDAO = DAOFactorySelectplay.creatSelectplayDAO();
	public List<Selectplay> Fetch(String condt){
    	return spDAO.select(condt);
    }
    public List<Selectplay> FetchAll(){
    	return spDAO.select("");
    }
}
