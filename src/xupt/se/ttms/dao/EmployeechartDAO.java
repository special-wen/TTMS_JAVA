package xupt.se.ttms.dao;
import java.sql.ResultSet;

/**
 * author:liufan
 */
import xupt.se.ttms.idao.iEmployeechartDAO;
import xupt.se.ttms.model.Employeechart;
import xupt.se.util.DBUtil;

public class EmployeechartDAO implements iEmployeechartDAO{
	public Employeechart count(Employeechart e){
		DBUtil db;
		ResultSet rst;
		int i=1;
		//查询员工的个数
		String sql1="select count(*) from employee";
		db=new DBUtil();
		db.openConnection();
		try {
			rst=db.execQuery(sql1);
			if (rst != null) {
				while(rst.next()){
					e.setlength(rst.getInt(1));
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//查询员工ID
		String sql2="select emp_id,emp_name,emp_no from employee;";
		db=new DBUtil();
		db.openConnection();
		i=1;
		try {
			rst=db.execQuery(sql2);
			if (rst != null) {
				while(rst.next()){
					e.setemp_chart_id(i, rst.getInt("emp_id"));
					e.setemp_chart_name(i, rst.getString("emp_name"));
					e.setemp_chart_no(i, rst.getString("emp_no"));
					i++;
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		//查询每个员工的销售额
		for (i = 1; i <= e.getlength(); i++) {
			String sql3="select SUM(sale_payment-sale_change) from employee,sale"
					+" where employee.emp_id=sale.emp_id and employee.emp_id="+e.getemp_chart_id(i);
			db = new DBUtil();
			db.openConnection();
			try {
				rst = db.execQuery(sql3);
				if (rst != null) {
					while(rst.next()){
						e.setemp_chart_sale(i,rst.getInt(1));
					}
				}
				db.close(rst);
				db.close();
			} catch (Exception ex) {
				// TODO 自动生成的 catch 块
				ex.printStackTrace();
			}
		}
		return e;
	}
}
