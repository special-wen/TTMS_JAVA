/**
 * 
 */
package xupt.se.ttms.idao;
import xupt.se.ttms.model.Ticket;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface iTicketDAO {
	public int insert(Ticket tic);
	public int update(Ticket tic);
	public int delete(int ID);
	public List<Ticket> select(String condt); 
}

