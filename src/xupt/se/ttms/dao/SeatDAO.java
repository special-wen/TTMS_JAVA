package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.view.seat.*;
import xupt.se.util.DBUtil;
import xupt.se.ttms.view.studio.*;
public class SeatDAO implements iSeatDAO{

	@Override
	//将数据插入到座位表中
	public int insert(Seat seat) {
		// TODO Auto-generated method stub
		
		try{
			String sql = "insert into seat(studio_id, seat_row, seat_column )"
					+ " values("
					+ seat.getStudio_id()
					+ ", "
					+ seat.getRow()
					+ ", " + seat.getColumn() 
					+ ")";
			System.out.println(sql);
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				seat.setId(rst.getInt(1));
			}
			db.close(rst);
			db.close();
			return 1;	
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Seat stu) {
		// TODO Auto-generated method stub
		int rtn=0;
		try {
			String sql = "update seat set "+"seat_state ='"+ stu.getState()+"'"
					+"where studio_id = " + stu.getStudio_id()+" and seat_row= "
							+ stu.getRow()+" and seat_column= "+stu.getColumn();
			System.out.println(sql);
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
		// TODO Auto-generated method stub
		int rtn=0;		
		try{
			String sql = "update seat set seat_state = '不可用'"
					+" where studio_id = " + ID+";";
			System.out.println(sql);
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
	public List<Seat> select(Seat stu) {
		
		List<Seat> stuList = null;
		stuList=new LinkedList<Seat>();
		try {
			String sql = "select seat_state from seat"
						+" where studio_id = "
						+stu.getStudio_id()+" and seat_row="
						+stu.getRow()+ " and seat_column= "+stu.getColumn();
			//condt.trim();
			System.out.println(sql);
			//if(!condt.isEmpty())
			//sql+= " and studio_name = '" + condt +"'";
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Seat seat=new Seat();
					seat.setState(rst.getString("seat_state"));
					
					stuList.add(seat);
					System.out.println(seat.getState());
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return stuList;
	}
	
	public List<Seat> select(int id) {
		
		List<Seat> stuList = null;
		stuList=new LinkedList<Seat>();
		try {
			String sql = "select seat_id,seat_row,seat_column,seat_state from seat"
						+" where studio_id ="+id;
			System.out.println(sql);
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Seat seat=new Seat();
					seat.setId(rst.getInt("seat_id"));
					seat.setState(rst.getString("seat_state"));
					stuList.add(seat);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return stuList;
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		