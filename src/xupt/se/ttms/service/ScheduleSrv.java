package xupt.se.ttms.service;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iScheduleDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;

public class ScheduleSrv {
		private iScheduleDAO sDAO=DAOFactory.createScheduleDAO();

		public int add(Schedule sched){
			return sDAO.insert(sched); 		
		}
		
		public int modify(Schedule sched){
			return sDAO.update(sched); 		
		}
		
		public int delete(int ID){
			return sDAO.delete(ID); 		
		}
		
		public List<Schedule> Fetch(int ID){
			return sDAO.select(ID);		
		}
		
		public List<Schedule> Fetch1(String s){
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); 
			java.sql.Date sqlDate = null; 
			java.sql.Date sqldate1=null;
			try{ 
			java.util.Date date = f.parse(s);
			java.util.Date date1 = new Date(f.parse(s).getTime()+24*3600*1000); 
			sqlDate = new java.sql.Date(date.getTime());
			sqldate1 = new java.sql.Date(date1.getTime());
			
			} 
			catch (Exception ex) { 
			System.out.println(ex.getMessage()); 
			
		}
			return sDAO.select1(sqlDate,sqldate1);
		}
		
		public int FetchstudID(String s){
			return sDAO.selectStudid(s);
		}
		
		public int FetchplayID(String s){
			return sDAO.selectPlayid(s);
		}
		
		public String Fetchstudname(int id){
			return sDAO.selectStudname(id);
		}
		
		public String Fetchplayname(int id){
			return sDAO.selectPlayname(id);
		}
		 
		public List<Schedule> Fetch2(int ID){
			return sDAO.selectSched(ID);
		}
		public List<Schedule> FetchAll(){
			return sDAO.select2("");		
		}
		public List<Schedule> select11(int ID,int ID1){
			return sDAO.select11(ID, ID1);
		}
		public int select_Time(int t){
			return sDAO.selectTime(t);
		}
}
