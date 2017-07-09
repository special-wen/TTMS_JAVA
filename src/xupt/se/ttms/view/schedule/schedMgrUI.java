package xupt.se.ttms.view.schedule;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.util.List;
import java.util.Iterator;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.ScheduleSrv;

import xupt.se.ttms.view.tmpl.*;
import xupt.se.util.DBUtil;


class scheduleTable{
	private static final long serialVersionUID = 1L;  //serialVersionUID用来作为Java对象序列化中的版本标示之用
	private JTable jt;
	 public  scheduleTable(JScrollPane jp){
		 DefaultTableModel tabModel=new DefaultTableModel(){ //通过DefaultTableModel可以对表格进行数据的增删改
				private static final long serialVersionUID = 1L;

				@Override              
					public boolean isCellEditable(int row,int column){
					return false;              
				}
			};
			tabModel.addColumn("演出计划ID");
			tabModel.addColumn("演出厅名称");
			tabModel.addColumn("剧目名称");
			tabModel.addColumn("放映时间");
			tabModel.addColumn("票价");
			//初始化列名
			jt=new JTable(tabModel);	
			
			//设置各列的宽度
		    TableColumnModel columnModel = jt.getColumnModel();
		    TableColumn column = columnModel.getColumn(0);
		    column.setMinWidth(0);
		    column.setMaxWidth(0);
	        column.setWidth(0);
	        column.setPreferredWidth(0);
	        
	        column = columnModel.getColumn(1);
	        column.setPreferredWidth(100);
	        column = columnModel.getColumn(2);
	        column.setPreferredWidth(100);
	        column = columnModel.getColumn(3);
	        column.setPreferredWidth(100);
	        column = columnModel.getColumn(4);
	        column.setPreferredWidth(10);        
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
	 public void showschedList(List<Schedule> sList){
		 try{
			 DefaultTableModel tabModel=(DefaultTableModel)jt.getModel();
			 tabModel.setRowCount(0);   //设置当前行数
			 Iterator<Schedule> itr=sList.iterator();
			 while(itr.hasNext()){
				 Schedule sched=itr.next();
				 Object data[]=new Object[5];
				 data[0]=Integer.toString(sched.getSched_id());
					ScheduleSrv schedSrv=new ScheduleSrv();
				    data[1]=schedSrv.Fetchstudname(sched.getStudio_id());
				    data[2]=schedSrv.Fetchplayname(sched.getPlay_id());		
                    data[3]=sched.getSched_time();
                    data[4]=Integer.toString(sched.getSched_TPrice());
			        tabModel.addRow(data);
			 }
			 jt.invalidate();  //更新表格
		 }catch (Exception e){
			 e.printStackTrace();
		 }
	 }
}
public class schedMgrUI extends MainUITmpl{
		private static final long serialVersionUID =1L;
		private JLabel ca1 = null; // 界面提示
		// 用来放表格的滚动控件
		private JScrollPane jsc;
		// 查找的提示和输出
		//private JLabel hint;
		 JComboBox hint;
		private JTextField input;

		// 查找，编辑和删除按钮
		private JButton btnAdd, btnEdit, btnDel, btnQuery;
		
		scheduleTable tms; //显示演出计划
		public schedMgrUI() {
			
		}
		protected void initContent() {
			Rectangle rect = contPan.getBounds();  //获取边界

			ca1 = new JLabel("管理演出", JLabel.CENTER);
			ca1.setBounds(0, 5, rect.width, 30);
			ca1.setFont(new java.awt.Font("宋体", 1, 20));
			ca1.setForeground(Color.blue);
			contPan.add(ca1);

			//添加滑动控件
			jsc = new JScrollPane();
			jsc.setBounds(0, 40, rect.width, rect.height - 90);
			contPan.add(jsc);

			//hint = new JLabel("请输入需要查询的演出厅ID:", JLabel.RIGHT);
		    hint=new JComboBox();
		    hint.addItem("按演出厅名称查询");
		    hint.addItem("按剧目名称查询");
		    hint.addItem("按日期查询");
			hint.setEditable(true);
			hint.setBounds(20, rect.height - 45, 200, 30);
			contPan.add(hint);

			input = new JTextField();
			input.setBounds(230, rect.height - 45, 200, 30);
			contPan.add(input);

			// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
			btnQuery = new JButton("查找");
			btnQuery.setBounds(450, rect.height - 45, 60, 30);
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnQueryClicked();
				}
			});
			contPan.add(btnQuery);

			btnAdd = new JButton("添加");
			btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnAddClicked();
				}
			});
			contPan.add(btnAdd);

			btnEdit = new JButton("修改");
			btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnModClicked();
				}
			});
			contPan.add(btnEdit);

			btnDel = new JButton("删除");
			btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
			btnDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnDelClicked();
				}
			});
			contPan.add(btnDel);
			contPan.add(ca1);
			
			tms = new scheduleTable(jsc);
			
			showTable();
		}

		private void btnAddClicked() {
			schedAddUI addSchedUI=null;
			
			addSchedUI = new schedAddUI();
			addSchedUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			addSchedUI.setWindowName("添加演出计划");
			addSchedUI.toFront();
			addSchedUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			addSchedUI.setVisible(true);
			if (addSchedUI.getReturnStatus()) {
				JOptionPane.showMessageDialog(null, "添加成功");
				showTable();
			}
		}


		private void btnModClicked() {
			Schedule sched = tms.getsched();
			if(null== sched){
				JOptionPane.showMessageDialog(null, "请选择要修改的演出计划");
				return; 
			}
			
			schedEditUI modSchedUI = new schedEditUI(sched);
			modSchedUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			modSchedUI.setWindowName("修改演出计划");
//			modStuUI.initData(stud);
			modSchedUI.toFront();
			modSchedUI.setModal(true);
			modSchedUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			modSchedUI.setVisible(true);

			if (modSchedUI.getReturnStatus()) {
				JOptionPane.showMessageDialog(null, "修改成功");
				showTable();
			}	
		}

		private void btnDelClicked() {
			Schedule sched = tms.getsched();
			if(null== sched){
				JOptionPane.showMessageDialog(null, "请选择要删除的演出计划");
				return; 
			}		
			
			int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				ScheduleSrv schedSrv = new ScheduleSrv();
				schedSrv.delete(sched.getSched_id());
				showTable();
			}
		}

		private void btnQueryClicked() {
			String m=hint.getSelectedItem().toString();
			String s=input.getText();
			if (!s.equals("")) {
				schedSelectUI SelectschedUI=new schedSelectUI(m,s);
				SelectschedUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				SelectschedUI.setWindowName("演出计划信息");
				SelectschedUI.toFront();
				SelectschedUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				SelectschedUI.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "请输入检索条件");
			}
		}

		private void showTable() {
			List<Schedule> sList = new ScheduleSrv().FetchAll();
			tms.showschedList(sList);
		}
		

		public static void main(String[] args) {
			schedMgrUI frmStuMgr = new schedMgrUI();
			frmStuMgr.setVisible(true);
		}
	


}
