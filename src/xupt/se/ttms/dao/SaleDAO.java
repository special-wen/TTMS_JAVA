package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.util.DBUtil;

public class SaleDAO implements iSaleDAO {

	@Override
	public int selectticket(Sale sale) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateticket(Sale sale) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update ticket set " + " seat_id = "
					+ sale.getseatID() + ", " + " sched_id = "
					+ sale.getschedID() + ", " + "ticket_price="
					+sale.getticketPrice()+","+" ticket_status = "
					+ sale.getticketStatus() + " ";

			sql += " where ticket_id = " + sale.getticketID();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Sale> select(String condt) {
		// TODO Auto-generated method stub
		List<Sale> saleList = null;
		saleList=new LinkedList<Sale>();
		try {
			String sql = "select ticket_id, seat_id, sched_id, ticket_price, ticket_status from ticket ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Sale sale=new Sale();
					sale.setticketID(rst.getInt("ticket_id"));
					sale.setseatID(rst.getInt("seat_id"));
					sale.setschedID(rst.getInt("sched_id"));
					sale.setticketPrice(rst.getFloat("ticket_price"));
					sale.setticketStatus(rst.getInt("ticket_status"));
					saleList.add(sale);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return saleList;
	}
}
