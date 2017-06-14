package xupt.se.ttms.idao;
import xupt.se.ttms.dao.*;

public class DAOFactorySeat {
	public static iSeatDAO creatSeatDAO(){
		return new SeatDAO();
	}
	
}

