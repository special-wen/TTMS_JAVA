package xupt.se.ttms.idao;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;

import java.util.List;

public interface iSeatDAO{
	public int insert(Seat stu);
	public int update(Seat stu);
	public int delete(int ID);
	public List<Seat> select(Seat stu);
	public List<Seat> select(int id); 
}