package xupt.se.ttms.idao;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public interface iScheduleDAO {
		public int insert(Schedule sched);
		public int update(Schedule sched);
		public int delete(int ID);
		public List<Schedule> select(int ID);
		public List<Schedule> select1(Date s,Date m);
		public List<Schedule> select2(String s);
		public int selectStudid(String studname);
		public int selectPlayid(String playname);
		public String selectPlayname(int id);
		public String selectStudname(int id);
		public List<Schedule> selectSched(int ID);
		public List<Schedule> select11(int ID,int ID1);
		public int selectTime(int  t);
}
