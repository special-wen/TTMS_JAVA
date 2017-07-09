package xupt.se.ttms.view.sellticket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.schedule.schedEditUI;
import xupt.se.ttms.view.seat.SeatMgrUI;
import xupt.se.ttms.view.tmpl.ImagePanel;

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
			tabModel.addColumn("sched_id");
			tabModel.addColumn("演出厅");
			tabModel.addColumn("剧目");
			tabModel.addColumn("演出时间");
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
	        column.setPreferredWidth(1);
	        column = columnModel.getColumn(2);
	        column.setPreferredWidth(1);
	        column = columnModel.getColumn(3);
	        column.setPreferredWidth(70);
	        column = columnModel.getColumn(4);
	        column.setPreferredWidth(1);        
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

public class choseTime extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private int frmWidth=800;
	private int frmHeight=600;
	protected final ImagePanel headPan = new ImagePanel("resource/image/header.jpg");
	protected final JPanel contPan = new JPanel();
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JLabel sumPrice=new JLabel();
	public JLabel windowName = new JLabel();
	int k=0;
	scheduleTable tms; //显示演出计划列表
	private JScrollPane jsc;
	private JButton btnAdd, btnEdit, btnDel, btnQuery;
	
	JPanel top = new JPanel();
    JPanel bottom = new JPanel();
    JPanel butt = new JPanel();
    int [][]sate = new int [11][8];

	public choseTime(){
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("那一年剧院票务管理系统");
		this.setLayout(null);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				onWindowClosing();
			}
		});		
		
		headPan.setBounds(0, 0, frmWidth, 80);
		headPan.setLayout(null);
		this.add(headPan);
		
		contPan.setBounds(0, 80, frmWidth, this.frmHeight-100);
		contPan.setLayout(null);
		this.add(contPan);
		
		
		initHeader();
		initContent();
	}


	/**
	 * @param args
	 */

	public int getWidth(){
		return this.frmWidth;
		
	}
	public int getHeight(){
		return this.frmHeight;
		
	}
	
	private void initHeader() {
		try {


			windowName.setBounds(frmWidth-160, 5, 160, 50);
			windowName.setFont(new java.awt.Font("dialog", 1, 20));
			windowName.setForeground(Color.blue);	
			headPan.add(windowName);
			setWindowName("影院排期");
			
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}
	public void setWindowName(String name){
		windowName.setText(name);
	}
	
	
	//To be override by the detailed business block interface 
	protected void onWindowClosing(){
		this.dispose();
	}
	
	
	//To be override by the detailed business block interface 
	protected void initContent(){
	        jsc = new JScrollPane();
			jsc.setBounds(0, 5, 800, 450);
			contPan.add(jsc);
	       
			btnAdd = new JButton("选座购票");
			btnAdd.setBounds(800-150, 500-45, 100, 30);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnModClicked();
				}
			});
			contPan.add(btnAdd);
	        tms = new scheduleTable(jsc);
			
			showTable();
	        
	        setVisible(true);

    }
	private void showTable() {
		List<Schedule> sList = new ScheduleSrv().FetchAll();
		tms.showschedList(sList);
	}
	
	private void btnModClicked() {
		Schedule sched = tms.getsched();
		if(null== sched){
			JOptionPane.showMessageDialog(null, "请选择要购票的演出计划");
			return; 
		}
		SaleUI sale=new SaleUI(sched);
	}
	
	
	//To be override by the detailed business block interface 
	protected void btnExitClicked(ActionEvent Event){
		System.exit(0);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
