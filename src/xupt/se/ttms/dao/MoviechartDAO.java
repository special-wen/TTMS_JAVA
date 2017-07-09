package xupt.se.ttms.dao;

import java.sql.ResultSet;

import xupt.se.ttms.idao.iMoviechartDAO;
import xupt.se.ttms.model.Moviechart;
import xupt.se.util.DBUtil;

/*
 * author:liufan*/
public class MoviechartDAO implements iMoviechartDAO {
	// 统计电影票房
	public Moviechart count(Moviechart m) {
		DBUtil db;
		ResultSet rst;
		ResultSet rst1;
		int i = 1;
		try {
			// 查询正在上映的电影个数(状态为1)
			String sql1 = "select count(*) from Play where play_status=1;";
			db = new DBUtil();
			db.openConnection();
			rst = db.execQuery(sql1);
			if (rst != null) {
				while(rst.next()){
					m.setLength(rst.getInt(1));
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 查询正在上映的电影ID
		String sql2 = "select play_id,play_name from play where play_status=1;";
		db = new DBUtil();
		db.openConnection();
		try {
			rst = db.execQuery(sql2);
			if (rst != null) {
				i = 1;
				while (rst.next()) {
					m.setPlay_chart_id(i, rst.getInt("play_id"));
					m.setPlay_chart_name(i,rst.getString("play_name"));
					i++;
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//查询剧目卖出的票数和剧目票价并相乘
		for (i = 1; i <= m.getLength(); i++) {
			String sql3 = "select play_ticket_price from ticket,schedule,play where ticket.sched_id=schedule.sched_id and play.play_id=schedule.play_id and ticket.ticket_status=9 and play.play_id=" + m.getPlay_chart_id(i);
			String sql4 = "select count(*) from ticket,schedule,play where ticket.sched_id=schedule.sched_id and play.play_id=schedule.play_id and ticket.ticket_status=9 and play.play_id=" + m.getPlay_chart_id(i);
			db = new DBUtil();
			db.openConnection();
			try {
				rst1=db.execQuery(sql3);
				rst = db.execQuery(sql4);
				if (rst != null && rst1!=null) {
					while(rst.next() && rst1.next()){
						m.setPlay_chart_price(i, rst.getInt(1)*rst1.getInt(1));
						System.out.print(m.getPlay_chart_price(i));
					}
				}
				db.close(rst1);
				db.close(rst);
				db.close();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		return m;
	}
}
