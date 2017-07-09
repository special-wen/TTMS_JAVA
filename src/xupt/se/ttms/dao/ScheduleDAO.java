package xupt.se.ttms.dao;
import java.util.LinkedList;
import java.util.List;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.util.DBUtil;


public class ScheduleDAO  implements iScheduleDAO{
			public int insert(Schedule sched){
				try{
					String sql="insert into schedule(studio_id,play_id,sched_time,sched_ticket_price)"
							   +" values("+sched.getStudio_id()+", "+sched.getPlay_id()+", '"+sched.getSched_time()
							   +"', "+sched.getSched_TPrice()+")";
					DBUtil db=new DBUtil();
					db.openConnection();
					ResultSet rst=db.getInsertObjectIDs(sql);
					if (rst!=null && rst.first()) {
						sched.setSched_id(rst.getInt(1));
					}
					db.close(rst);
					db.close();
					return 1;
				}catch (Exception e){
					e.printStackTrace();
				}
				return 0;
			}
			public int update(Schedule sched){
				int rtn=0;
				try{
					String sql="update schedule set "+"studio_id="
				+sched.getStudio_id()+",play_id="+sched.getPlay_id()
				+",sched_time= '"+sched.getSched_time()+"',sched_ticket_price="
				+sched.getSched_TPrice()+" ";
					sql+=" where sched_id="+sched.getSched_id();
					DBUtil db=new DBUtil();
					db.openConnection();
					rtn=db.execCommand(sql);
					db.close();
				}catch (Exception e){
					e.printStackTrace();
				}
				return rtn;
			}
			public int delete (int ID){
				int rtn=0;
				try{
					String sql="delete from schedule ";
					sql+="  where sched_id="+ID;
					DBUtil db=new DBUtil();
					db.openConnection();
					rtn=db.execCommand(sql);
					db.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				return rtn;
			}
			//根据演出厅名称查找演出厅id
			public int selectStudid(String studname){
				int t=-1;
				try{
					String sql="select studio_id from Studio";
					if(!studname.isEmpty()){
					    sql+=" where studio_name='"+studname+"'";
					}
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return -1;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							t=rst.getInt(1);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return t;
}
			//根据剧目名称查找剧目id
			public int selectPlayid(String playname){
				int k=-1;
				try{
					String sql="select play_id from Play";
					if(!playname.isEmpty()){
					    sql+=" where play_name='"+playname+"'";
					}
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return -1;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							k=rst.getInt(1);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return k;
}
			//根据剧目id查找剧目名称
			public String selectPlayname(int id){
				String s=null;
				try{
					String sql="select play_name from Play";
				    sql+=" where play_id="+id+"";
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
			//根据演出厅id查找演出厅名称
			public String selectStudname(int id){
				String m=null;
				try{
					String sql="select studio_name from Studio";
				    sql+=" where studio_id="+id+"";
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return null;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							m=rst.getString(1);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return m;
}
			//根据演出厅ID查找相应的演出计划
			public List<Schedule> select(int ID){
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				
				try{
					String sql="select sched_id,play_id,sched_time,sched_ticket_price from schedule";
					if(ID!=-1){
					    sql+=" where studio_id="+ID;
					}
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return null;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							Schedule sched=new Schedule();
							sched.setSched_id(rst.getInt("sched_id"));
							sched.setPlay_id(rst.getInt("play_id"));
							sched.setSched_time(rst.getString("sched_time"));
							sched.setSched_TPrice(rst.getInt("sched_ticket_price"));
							sList.add(sched);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return sList;
}
			//根据剧目id查找相应的演出计划
			public List<Schedule> selectSched(int ID){
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				
				try{
					String sql="select sched_id,studio_id,sched_time,sched_ticket_price from schedule";
				    sql+=" where play_id="+ID;
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return null;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							Schedule sched=new Schedule();
							sched.setSched_id(rst.getInt("sched_id"));
							sched.setStudio_id(rst.getInt("studio_id"));
							sched.setSched_time(rst.getString("sched_time"));
							sched.setSched_TPrice(rst.getInt("sched_ticket_price"));
							sList.add(sched);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return sList;
}
			//根据日期查询演出计划
			public List<Schedule> select1(Date s,Date m){
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				try{
					String sql="select sched_id,studio_id,play_id,sched_time,sched_ticket_price from schedule";
					sql+=" where sched_time >='"+s+"' and sched_time <='"+m+"'";
					
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return null;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							Schedule sched=new Schedule();
							sched.setSched_id(rst.getInt("sched_id"));
							sched.setStudio_id(rst.getInt("studio_id"));
							sched.setPlay_id(rst.getInt("play_id"));
							sched.setSched_time(rst.getString("sched_time"));
							sched.setSched_TPrice(rst.getInt("sched_ticket_price"));
							sList.add(sched);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return sList;
}
			public List<Schedule> select2(String s){
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				try{
					String sql="select sched_id,studio_id,play_id,sched_time,sched_ticket_price from schedule";
					s.trim();
					if(!s.isEmpty()){
						sql+= " where " + s;
					}
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return null;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							Schedule sched=new Schedule();
							sched.setSched_id(rst.getInt("sched_id"));
							sched.setStudio_id(rst.getInt("studio_id"));
							sched.setPlay_id(rst.getInt("play_id"));
							sched.setSched_time(rst.getString("sched_time"));
							sched.setSched_TPrice(rst.getInt("sched_ticket_price"));
							sList.add(sched);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return sList;
}
			public List<Schedule> select11(int ID,int ID1){
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				
				try{
					String sql="select sched_id,sched_time,sched_ticket_price from schedule";
					if(ID!=-1){
					    sql+=" where studio_id="+ID+"and play_id<>"+ID1;
					}
					DBUtil db=new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return null;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							Schedule sched=new Schedule();
							sched.setSched_id(rst.getInt("sched_id"));
							sched.setSched_time(rst.getString("sched_time"));
							sched.setSched_TPrice(rst.getInt("sched_ticket_price"));
							sList.add(sched);
						}
				}
					db.close(rst);
					db.close();
			}catch (Exception e){
				e.printStackTrace();
			}
				finally{
					
				}
				return sList;
}
			public int selectTime(int  t) {
				int time=-1;
				try {
					String sql = "select play_length  from play where play_id="+t;
					DBUtil db = new DBUtil();
					if(!db.openConnection()){
						System.out.print("fail to connect database");
						return -1;
					}
					ResultSet rst = db.execQuery(sql);
					if (rst!=null) {
						while(rst.next()){
							time=rst.getInt(1);
						}
					}
					db.close(rst);
					db.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally{
					
				}
				
				return time;
			}
}
