package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Users;
public interface iUserDAO {
	public int insert(Users u);
	public int update(Users u);
	public int delete(int ID);
	public List<Users> select(String condt);
}
