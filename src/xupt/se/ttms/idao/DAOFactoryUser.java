package xupt.se.ttms.idao;
import xupt.se.ttms.dao.UserDAO;
public class DAOFactoryUser {
	public static iUserDAO creatUserDAO(){
		return new UserDAO();
	}
}
