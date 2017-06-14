/**
 * 
 */
package xupt.se.ttms.idao;
import xupt.se.ttms.model.Studio;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface iStudioDAO {
	public int insert(Studio stu);
	public int update(Studio stu);
	public int delete(int ID);
	public List<Studio> select(String condt);
	public List<Studio> select(int studio_id); 
	public List<Studio> select11(String condt);
}
