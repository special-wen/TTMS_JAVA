package xupt.se.ttms.view.schedule;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.dao.*;
import xupt.se.ttms.view.tmpl.*;
class schedTable{
	private static final long serialVersionUID = 1L;  //serialVersionUID用来作为Java对象序列化中的版本标示之用
	private JTable jt;
	 public  schedTable(JScrollPane jp){
		 DefaultTableModel tabModel=new DefaultTableModel(){ //通过DefaultTableModel可以对表格进行数据的增删改
				private static final long serialVersionUID = 1L;
				@Override              
					public boolean isCellEditable(int row,int column){
					return false;              
				}
			};
			tabModel.addColumn("演出厅名称");
			tabModel.addColumn("剧目名称");
			tabModel.addColumn("放映时间");
			tabModel.addColumn("票价");
			//初始化列名
			jt=new JTable(tabModel);	
			
			//设置各列的宽度
		    TableColumnModel columnModel = jt.getColumnModel();
		    TableColumn column = columnModel.getColumn(0);
		    column.setPreferredWidth(20);
	        column = columnModel.getColumn(1);
	        column.setPreferredWidth(20);
	        column = columnModel.getColumn(2);
	        column.setPreferredWidth(20);
	        column = columnModel.getColumn(3);
	        column.setPreferredWidth(20);   
			jp.add(jt);
			jp.setViewportView(jt);
			
		}
	 public Schedule  getsched(){
		 int rowSel=jt.getSelectedRow();
		 String t,s;
		if(rowSel>=0){
			Schedule sched=new Schedule();
				sched.setSched_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
				s=jt.getValueAt(rowSel, 1).toString();
				ScheduleSrv schedsrv =new ScheduleSrv();
				sched.setStudio_id(schedsrv.FetchstudID(s));
				t=jt.getValueAt(rowSel, 2).toString();
				sched.setPlay_id(schedsrv.FetchplayID(t));
				sched.setSched_time(jt.getValueAt(rowSel, 3).toString());
				sched.setSched_TPrice(Integer.parseInt(jt.getValueAt(rowSel, 4).toString()));
				return  sched;
		}
			else{
				return null;
			}
	 }
	 //创建表格
	 public void showschedList1(List<Schedule> sList){
		 TableColumnModel columnModel = jt.getColumnModel();
		    TableColumn column = columnModel.getColumn(0);
		    column.setMinWidth(0);
		    column.setMaxWidth(0);
	        column.setWidth(0);
	        column.setPreferredWidth(0);
		 try{
			 DefaultTableModel tabModel=(DefaultTableModel)jt.getModel();
			 tabModel.setRowCount(0);   //设置当前行数
			 Iterator<Schedule> itr=sList.iterator();
			 while(itr.hasNext()){
				 Schedule sched=itr.next();
				 Object data[]=new Object[4];
				 //data[0]=Integer.toString(sched.getSched_id());
					ScheduleSrv schedSrv=new ScheduleSrv();
				    data[0]=schedSrv.Fetchstudname(sched.getStudio_id());
				    System.out.println(data[0]);
				    data[1]=schedSrv.Fetchplayname(sched.getPlay_id());		
                    data[2]=sched.getSched_time();
                    data[3]=Integer.toString(sched.getSched_TPrice());
			        tabModel.addRow(data);
			 }
			 jt.invalidate();  //更新表格
		 }catch (Exception e){
			 e.printStackTrace();
		 }
	 }
	 public void showschedList2(List<Schedule> sList){
		 Object data[]=new Object[4];
		 TableColumnModel columnModel = jt.getColumnModel();
		    TableColumn column = columnModel.getColumn(1);
		    column.setMinWidth(0);
		    column.setMaxWidth(0);
	        column.setWidth(0);
	        column.setPreferredWidth(0);
		 try{
			 DefaultTableModel tabModel=(DefaultTableModel)jt.getModel();
			 tabModel.setRowCount(0);   //设置当前行数
			 Iterator<Schedule> itr=sList.iterator();
			 while(itr.hasNext()){
				 Schedule sched=itr.next();
					ScheduleSrv schedSrv=new ScheduleSrv();
				    data[0]=schedSrv.Fetchstudname(sched.getStudio_id());
				    data[1]=schedSrv.Fetchplayname(sched.getPlay_id());		
                    data[2]=sched.getSched_time();
                    data[3]=Integer.toString(sched.getSched_TPrice());
			        tabModel.addRow(data);
			 }
			 jt.invalidate();  //更新表格
		 }catch (Exception e){
			 e.printStackTrace();
		 }
	 }
	 public void showschedList3(List<Schedule> sList){
		 Object data[]=new Object[4];
		 try{
			 DefaultTableModel tabModel=(DefaultTableModel)jt.getModel();
			 tabModel.setRowCount(0);   //设置当前行数
			 Iterator<Schedule> itr=sList.iterator();
			 while(itr.hasNext()){
				 Schedule sched=itr.next();
					ScheduleSrv schedSrv=new ScheduleSrv();
				    data[0]=schedSrv.Fetchstudname(sched.getStudio_id());
				    data[1]=schedSrv.Fetchplayname(sched.getPlay_id());		
                    data[2]=sched.getSched_time();
                    data[3]=Integer.toString(sched.getSched_TPrice());
			        tabModel.addRow(data);
			 }
			 jt.invalidate();  //更新表格
		 }catch (Exception e){
			 e.printStackTrace();
		 }
	 }
}
public class schedSelectUI extends PopUITmpl implements ActionListener{
		private static final long serialVersionUID =1L;
		boolean rst=false;
		private JLabel ca1 = null; // 界面提示
		// 用来放表格的滚动控件
		private JScrollPane jsc;
		private JButton btnCancel;
		
		schedTable tms; //显示演出计划
		public schedSelectUI(String m,String s) {
			this.setTitle("查询演出计划");
			int studid,playid;
			if(m.equals("按演出厅名称查询")){
				ScheduleSrv schedSrv=new ScheduleSrv();
				studid=schedSrv.FetchstudID(s);
				System.out.println(studid);
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				sList=schedSrv.Fetch(studid);
				tms.showschedList1(sList);
			}
			if(m.equals("按剧目名称查询")){
				ScheduleSrv schedSrv=new ScheduleSrv();
				playid=schedSrv.FetchplayID(s);
				List<Schedule> s1List=null;
				s1List=new LinkedList<Schedule>();
				s1List=schedSrv.Fetch2(playid);
				tms.showschedList2(s1List);
			}
			if(m.equals("按日期查询")){
				ScheduleSrv schedSrv=new ScheduleSrv();
				List<Schedule> sList=null;
				sList=new LinkedList<Schedule>();
				sList=schedSrv.Fetch1(s);
				tms.showschedList3(sList);
			}
			/*ScheduleDAO scheddao=new ScheduleDAO();
			List<Schedule> sList=null;
			sList=new LinkedList<Schedule>();
			sList=new ScheduleSrv().Fetch1(s);
			
			this.setTitle("查询演出计划");
			tms.showschedList(sList);*/
			
		}
		protected void initContent() {
			Rectangle rect = contPan.getBounds();  //获取边界

			ca1 = new JLabel("演出计划信息", JLabel.CENTER);
			ca1.setBounds(0, 5, rect.width, 30);
			ca1.setFont(new java.awt.Font("宋体", 1, 20));
			ca1.setForeground(Color.blue);
			contPan.add(ca1);

			//添加滑动控件
			jsc = new JScrollPane();
			jsc.setBounds(0, 40, rect.width, rect.height - 90);
			contPan.add(jsc);
			btnCancel = new JButton("关闭");
			btnCancel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
			
			
			btnCancel.addActionListener(this);
			contPan.add(btnCancel);
			contPan.add(ca1);
			tms = new schedTable(jsc);
			btnCancel.addActionListener(this);
			//showTable();
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCancel) {
				rst=false;
				this.setVisible(false);
			} 
		}	
		/*private void showTable() {
			List<Schedule> sList = new ScheduleSrv().FetchAll();
			tms.showschedList(sList);
		}*/
}

