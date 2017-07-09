package xupt.se.ttms.dao;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import xupt.se.ttms.idao.iUserDAO;
import xupt.se.ttms.model.Users;
import xupt.se.util.DBUtil;
public class UserDAO implements iUserDAO{
	public boolean judgecount(Users u){
		String sql = "select count(*) from employee";
		sql+=" where emp_name ='"+u.getName()+"'";
		DBUtil db = new DBUtil();
		db.openConnection();
		try {
			ResultSet rs = db.execQuery(sql);
			while(rs.next()){
				if(Integer.parseInt(rs.getString(1))>0)
					return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	public boolean judgepassword(Users u){
		if(!u.getPassword().equals(u.getSure()))
			return false;
		return true;
	}
	public int insert(Users u) {
		if(!judgecount(u)){
			JOptionPane.showMessageDialog(null, "该用户名已被占用,请重新输入！",null, JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		if(!judgepassword(u)){
			JOptionPane.showMessageDialog(null, "确认密码失败,请重新确认！",null, JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		if(u.getName().equals("")||u.getPassword().equals("")||u.getSure().equals("")){
			JOptionPane.showMessageDialog(null, "数据不完整",null, JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		else{	
		try {
			String	sql = "insert into employee(emp_no,emp_name,emp_password,emp_sure,emp_addr,emp_email)"
					+ " values('"
					+u.getNO()
					+ "', '"+ u.getName()
					+ "', '" + u.getPassword()
					+ "', '" + u.getSure()
					+ "', '" + u.getAddr()
					+ "', '" + u.getEmail()
					+ "')";	
			//System.out.println(sql);
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst!=null && rst.first()) {
				u.setID(rst.getInt(1));
			    }
			db.close(rst);
			db.close();
			JOptionPane.showMessageDialog(null, "保存成功！"); 
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return 0;
	}
	@Override
	public int update(Users u) {
		int rtn=0;
		try {
			String sql = "update employee set "  + "emp_no ='"
					+ u.getNO()+ "'," +"emp_name ='"
					+ u.getName()+"',"+"emp_password ='"
					+ u.getPassword()+"',"+"emp_sure='"
					+u.getSure()+"',"+"emp_addr ='"
					+ u.getAddr()+"',"+"emp_email ='"
					+ u.getEmail()+"'";
			sql += " where emp_id = " + u.getID();
			//System.out.println(sql);
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn =db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int ID) {
		int rtn=0;		
		try{
			String sql = "delete from employee ";
			sql += " where emp_id= " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn=db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;		
	}

	@Override
	public List<Users> select(String condt) {
		List<Users> uList = null;
		uList=new LinkedList<Users>();
		try {
			String sql = "select emp_id,emp_no,emp_name,emp_password,emp_sure,emp_addr,emp_email from employee ";
			condt.trim();
			if(!condt.isEmpty())
				sql+= " where emp_name='"+ condt+"'";
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Users u=new Users();
					u.setID(rst.getInt("emp_id"));
					u.setNO(rst.getString("emp_no"));
					u.setName(rst.getString("emp_name"));
					u.setPassword(rst.getString("emp_password"));
					u.setSure(rst.getString("emp_sure"));
					u.setAddr(rst.getString("emp_addr"));
					u.setEmail(rst.getString("emp_email"));
					uList.add(u);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{	
		}
		return uList;
	}
}