package xupt.se.ttms.service;
/**
 * author:liufan
 */
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeechartDAO;
import xupt.se.ttms.model.Employeechart;

public class EmployeechartSrv {

	private iEmployeechartDAO eDAO=DAOFactory.createemployeechartDAO();
	public Employeechart count(Employeechart e){
		return eDAO.count(e);
	}
}
