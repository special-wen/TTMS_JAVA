package xupt.se.ttms.dao;

import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Studio;
import xupt.se.util.DBUtil;

public class PlayDAO implements iPlayDAO {

	public int insert(Play p,String image) {
	String sql="insert into play(play_type_id, play_lang_id, play_name, play_introduction,play_length,play_ticket_price, play_status,play_image )"
				+ " values(?,?,?,?,?,?,?,?)";
		Object[] params=new Object[8];
		params[0]=p.getPlay_type_id();
		params[1]=p.getPlay_lang_id();
		params[2]=p.getName();
		params[3]=p.getIntroduction();
		params[4]=p.getLength();
		params[5]=p.getTicket_price();
		params[6]=p.getStatus();
		
		FileInputStream fis=null;
		File file=new File(image);
		
		try {
			DBUtil db = new DBUtil();
			db.openConnection();
			fis=new FileInputStream(file);
			params[7]=fis;
			ResultSet rst = db.getInsertObjectIDs(sql,params);
			if (rst != null) {
				while(rst.next()){
					p.setPlay_type_id(rst.getInt("play_type_id"));
					p.setPlay_lang_id(rst.getInt("play_lang_id"));
					p.setName(rst.getString("play_name"));
					p.setIntroduction(rst.getString("play_introduction"));
					p.setLength(rst.getInt("play_length"));
					p.setTicket_price(rst.getInt("play_ticket_price"));
					p.setStatus(rst.getInt("play_status"));
					p.setImage(rst.getBlob("play_image"));
				}
			}
			db.close(rst);
			db.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int update(Play p,String image) {
		int rtn = 0;
			String sql = "update play set "
					+ " play_name= '"+p.getName()+"',"
					+ " play_type_id= '"+p.getPlay_type_id()+"',"
					+ " play_lang_id= '"+p.getPlay_lang_id()+"',"
					+ " play_introduction = '" + p.getIntroduction() + "', " 
					+ " play_length = "+ p.getLength() + ", " 
					+ " play_ticket_price = " + p.getTicket_price() + ", "
					+ " play_status = " + p.getStatus()/* + ", "
					+ " play_image = " + p.getImage()*/ ;
			sql += " where play_ID = " + p.getID();
//			Object[] params=new Object[8];
//			params[0]=p.getName();
//			params[1]=p.getPlay_type_id();
//			params[2]=p.getPlay_lang_id();
//			params[3]=p.getIntroduction();
//			params[4]=p.getLength();
//			params[5]=p.getTicket_price();
//			params[6]=p.getStatus();
//			FileInputStream fis=null;
//			File file=new File(image);
			try {
				DBUtil db = new DBUtil();
				db.openConnection();
//				fis=new FileInputStream(file);
//				params[7]=fis;
//				ResultSet rst = db.getUpdateObjectIDs(sql,params);
//				if (rst != null) {
//					while(rst.next()){
//						p.setPlay_type_id(rst.getInt("play_type_id"));
//						p.setPlay_lang_id(rst.getInt("play_lang_id"));
//						p.setName(rst.getString("play_name"));
//						p.setIntroduction(rst.getString("play_introduction"));
//						p.setLength(rst.getInt("play_length"));
//						p.setTicket_price(rst.getInt("play_ticket_price"));
//						p.setStatus(rst.getInt("play_status"));
//						p.setImage(rst.getBlob("play_image"));
//					}
//				}
//				db.close(rst);
				rtn=db.execCommand(sql);
				db.close();
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 1;
	}

	@Override
	public int delete(int ID) {
		int rtn = 0;
		try {
			String sql = "delete from  play ";
			sql += " where play_ID = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public List<Play> select(String condt) {
		List<Play> pList = null;
		pList = new LinkedList<Play>();
		try {
			String sql = "select play_id,play_type_id,play_lang_id, play_name,Play_image,play_introduction, play_length, play_ticket_price,play_status from play ";
			condt.trim(); // 去掉字符串开头和结尾的空格
			if (!condt.isEmpty())
				sql += " where " + condt;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Play p = new Play();
					p.setID(rst.getInt("play_id"));
					p.setPlay_type_id(rst.getInt("play_type_id"));
					p.setPlay_lang_id(rst.getInt("play_lang_id"));
					p.setName(rst.getString("play_name"));
					
					byte[] buf=new byte[256];
					Blob blob=rst.getBlob("play_image");
					p.setImage(blob);
					if(blob!=null){
						File file = new File("Cache/Play_Image/"+p.getID()+"_"+p.getName()+".jpg");
//						System.out.println("图片路径："+"Cache/Play_Image/"+p.getID()+"_"+p.getName()+".jpg");
						FileOutputStream sout = new FileOutputStream(file);
						InputStream in = blob.getBinaryStream();//获取blob数据的输入数据流
						for(int i= in.read(buf);i!=-1;){
							sout.write(buf);
							i = in.read(buf);
						}
						in.close();
						sout.close();
					}
					
					p.setIntroduction(rst.getString("play_introduction"));
					p.setLength(rst.getInt("play_length"));
					p.setTicket_price(rst.getInt("play_ticket_price"));
					p.setStatus(rst.getInt("play_status"));
					pList.add(p);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return pList;
	}
	public List<Play> select11(int  t) {
		List<Play> stuList = null;
		stuList=new LinkedList<Play>();
		try {
			String sql = "select play_name  from play where play_status=1";
			DBUtil db = new DBUtil();
			if(!db.openConnection()){
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst!=null) {
				while(rst.next()){
					Play stu=new Play();
					stu.setName(rst.getString("play_name"));
					stuList.add(stu);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			
		}
		
		return stuList;
	}
}
