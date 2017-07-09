package xupt.se.ttms.dao;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import xupt.se.ttms.idao.iSelectplayDAO;
import xupt.se.ttms.model.Selectplay;
import xupt.se.util.DBUtil;
public class SelectplayDAO implements iSelectplayDAO{
	public int count(String name){
		int count=0;
		String sql="select count(*) from ticket LEFT OUTER JOIN schedule on ticket.sched_id=schedule.sched_id LEFT OUTER JOIN play on play.play_id=schedule.play_id";
		sql+=" where play_status = 9 and ticket_status = 1 and play_name='"+name+"'";
		DBUtil db = new DBUtil();
		db.openConnection();
		try {
			ResultSet rs = db.execQuery(sql);
			while(rs.next()){
				count=Integer.parseInt(rs.getString(1));
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(count);
		return count;
	}
	//根据剧目id查找剧目类型
	public String selectPlaytype(int id){
		String s=null;
		try{
			String sql="select distinct dict_value from data_dict";
		    sql+=" where dict_parent_id = 2 and dict_id="+id+"";
			DBUtil db=new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					s=rst.getString(1);
				}
		}
			db.close(rst);
			db.close();
	}catch (Exception e){
		e.printStackTrace();
	}
		finally{
			
		}
		return s;
}
	public String selectPlaylang(int id){
		String s=null;
		try{
			String sql="select distinct dict_value from data_dict";
		    sql+=" where dict_parent_id = 3 and dict_id="+id+"";
			DBUtil db=new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					s=rst.getString(1);
				}
		}
			db.close(rst);
			db.close();
	}catch (Exception e){
		e.printStackTrace();
	}
		finally{
			
		}
		return s;
}
	public List<Selectplay> select(String condt) {
		    int num=0;
		    num=count(condt);
		    System.out.println(num);
		    String str1 = null;
		    String str2 = null;
			List<Selectplay> list = null;
			list=new LinkedList<Selectplay>();
			try {
				String sql = "select distinct play_name,sched_time,play_type_id,play_lang_id,play_status from play LEFT OUTER JOIN schedule on play.play_id=schedule.play_id LEFT OUTER JOIN ticket on schedule.sched_id=ticket.sched_id";
				condt.trim();
				if(!condt.isEmpty())
					sql+=" where play_name=" + "'"+condt+"';";
				System.out.println(sql);
				DBUtil db = new DBUtil();
				if(!db.openConnection()){
					System.out.print("fail to connect database");
					return null;
				}
				ResultSet rst = db.execQuery(sql);
				if (rst!=null) {
					while(rst.next()){
						str1 = selectPlaytype(rst.getInt("play_type_id"));
						str2 = selectPlaylang(rst.getInt("play_lang_id"));
						Selectplay sp=new Selectplay();
						sp.setPlayname(rst.getString("play_name"));
						sp.setSchedtime(rst.getString("sched_time"));
						sp.setPlaytype(str1);
						sp.setPlaylang(str2);
					    sp.setPlaystatus(rst.getInt("play_status"));
					    sp.setTicketstatus(num);
					    //System.out.println(rst.getString("play_name")+rst.getString("sched_time")+rst.getInt("play_type_id")+rst.getString("play_image")+rst.getInt("ticket_status"));
						list.add(sp);
					}
				}
				db.close(rst);
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{	
			}
			return list;
		}
}