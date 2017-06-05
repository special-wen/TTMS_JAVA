package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;
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
		return 0;
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
	public List<Seat> select(String condt) {
		// TODO Auto-generated method stub
		return null;
	}
	
}