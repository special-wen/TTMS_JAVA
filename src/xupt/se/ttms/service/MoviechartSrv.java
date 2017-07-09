package xupt.se.ttms.service;


import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iMoviechartDAO;
import xupt.se.ttms.model.Moviechart;
/*
 * author:liufan*/
public class MoviechartSrv {
	private iMoviechartDAO mDAO=DAOFactory.createmoviechartDAO();
	public Moviechart count(Moviechart m){
		return mDAO.count(m);
	}
	
}
