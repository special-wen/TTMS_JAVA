package xupt.se.ttms.idao;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;

import java.util.List;
public interface iPlayDAO {
	public int insert(Play p,String image);
	public int update(Play p,String image);
	public int delete(int ID);
	public List<Play> select(String condt); 
	public List<Play> select11(int  t);
}