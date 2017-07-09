package xupt.se.ttms.idao;

import xupt.se.ttms.dao.SelectplayDAO;

public class DAOFactorySelectplay {
	public static iSelectplayDAO creatSelectplayDAO(){
		return new SelectplayDAO();
	}
}
