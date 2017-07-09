package xupt.se.ttms.idao;
import java.util.List;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.Studio;
/**
 * @author Saler
 *
 */
public interface iSaleDAO {
	public int selectticket(Sale sale);
	public int updateticket(Sale sale);
	public List<Sale> select(String condt); 
}
