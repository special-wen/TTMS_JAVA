package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;






import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;



public class TicketDAO implements iTicketDAO {
	@Override
	public int insert(Ticket tic) {
		try {
			String sql = "insert into ticket(seat_id, sched_id, ticket_price,ticket_status)"
					+ " values("
					+ tic.getSeat_id()
					+ ", " + tic.getSched_id() 
					+ ", " + tic.getTicket_price()
					+ ", " +tic.getTicket_status()
//					+ ", '" +tic.getTicket_date()
					+ " )";	//添加演出票的SQL语句
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				tic.setTicket_id(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int update(Ticket tic) {
		int rtn=0;
		try {
			String sql = "update ticket set " + " seat_id = "
					+ tic.getSeat_id() +", "+" sched_id = "
					+ tic.getSched_id() + ", " + " ticket_price = "
					+ tic.getTicket_price() + " " + " ticket_status =" 
					+ tic.getTicket_status() +", " +"ticket_locked_time ='"
					+ tic.getTicket_date()+" '";

			sql += " where ticket_id = " + tic.getTicket_id();
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
	public int delete(int ID) {
		int rtn=0;		
		try{
			String sql = "delete from  ticket ";
			sql += " where ticket_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;		
	}
    
	@Override
	public List<Ticket> select(String condt) {
		List<Ticket> ticList = null;
		ticList=new LinkedList<Ticket>();
		try {
			String sql = "select ticket_id, seat_id, sched_id, ticket_price, ticket_status,ticket_locked_time from ticket ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			System.out.println(ticList);
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Ticket tic=new Ticket();
				
					tic.setTicket_id(rst.getInt("ticket_id"));
					tic.setSeat_id(rst.getInt("seat_id"));
					tic.setSched_id(rst.getInt("sched_id"));
					tic.setTicket_price(rst.getInt("ticket_price"));
					tic.setTicket_status(rst.getInt("ticket_status"));
					tic.setTicket_date(rst.getString("ticket_locked_time"));
					ticList.add(tic);
					
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return ticList;
	}
}
   
