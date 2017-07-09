package xupt.se.ttms.service;

import xupt.se.ttms.idao.iUserDAO;
import xupt.se.ttms.model.Users;

import java.util.List;

import xupt.se.ttms.idao.DAOFactoryUser;
public class UserSrv {
private iUserDAO uDAO = DAOFactoryUser.creatUserDAO();
    public int add(Users user){
    	return uDAO.insert(user);
    }
    public int modify(Users user){
    	return uDAO.update(user);
    }
    public int delete(int ID){
    	return uDAO.delete(ID);
    }
    public List<Users> Fetch(String condt){
    	return uDAO.select(condt);
    }
    public List<Users> FetchAll(){
    	return uDAO.select("");
    }
}