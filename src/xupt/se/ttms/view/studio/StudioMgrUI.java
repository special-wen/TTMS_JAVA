package xupt.se.ttms.view.studio;

import java.awt.Color;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.lang.model.type.TypeKind;
import javax.swing.JButton;
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

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.tmpl.*;
import xupt.se.ttms.view.seat.*;
/*import xupt.se.ttms.model.Seat;
import xupt.se.ttms.service.SeatSrv;*/
class StudioTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;

	public StudioTable(JScrollPane jp) {
		
		//设置单元格不可修改
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;

			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		
		//增加列名
		tabModel.addColumn("id");
		tabModel.addColumn("name");
		tabModel.addColumn("row");
		tabModel.addColumn("column");
		tabModel.addColumn("desciption");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    //隐藏ID这一列
        TableColumn column = columnModel.getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column.setPreferredWidth(0);

        column = columnModel.getColumn(1);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(4);
        column.setPreferredWidth(500);        

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Studio getStudio() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Studio stud = new Studio();
			stud.setID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setName(jt.getValueAt(rowSel, 1).toString());
			stud.setRowCount(Integer.parseInt(jt.getValueAt(rowSel, 2).toString())); // 0
			stud.setColCount(Integer.parseInt(jt.getValueAt(rowSel, 3).toString()));
			if (jt.getValueAt(rowSel, 4) != null)
				stud.setIntroduction(jt.getValueAt(rowSel, 4).toString());
			else
				stud.setIntroduction("");

			return stud;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showStudioList(List<Studio> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Studio> itr = stuList.iterator();
			while (itr.hasNext()) {
				Studio stu = itr.next();
				Object data[] = new Object[5];
				data[0] = Integer.toString(stu.getID());
				data[1] = stu.getName();
				data[2] = Integer.toString(stu.getRowCount());
				data[3] = Integer.toString(stu.getColCount());
				data[4] = stu.getIntroduction();
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class StudioMgrUI extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery,btnSeat;
	
	StudioTable tms; //显示演出厅列表


	public StudioMgrUI() {
		
	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("演出厅管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入演出厅名称:", JLabel.RIGHT);
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);

		input = new JTextField();
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除，编辑，座位管理的按钮，其中含有相关的事件处理！
		
		btnQuery = new JButton("查找");
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);
		
		btnSeat = new JButton("管理座位");
		btnSeat.setBounds(rect.width -330,rect.height -45, 100,30 );
		btnSeat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSeatCliked();
			}
		});
		contPan.add(btnSeat);
		
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
		
		tms = new StudioTable(jsc);
		
		showTable();
	}

	private void btnAddClicked() {

		StudioAddUI addStuUI=null;
		
		addStuUI = new StudioAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加演出厅");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}
	private void btnSeatCliked(){
		Studio stud = tms.getStudio();
		if (null == stud){
			JOptionPane.showMessageDialog(null, "请选择要管理座位的演出厅");
			return;
		}
		
		SeatMgrUI modStuUI = new SeatMgrUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setVisible(true);
		
		
		  
	}
	private void btnModClicked() {
		Studio stud = tms.getStudio();
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
			return; 
		}
		
		StudioEditUI modStuUI = new StudioEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改演出厅");
		modStuUI.initData(stud);
		modStuUI.toFront();
		modStuUI.setModal(true);
		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modStuUI.setVisible(true);

		if (modStuUI.getReturnStatus()) {
			showTable();
		}	
	}

	private void btnDelClicked() {
		Studio stud = tms.getStudio();
		
		if(null== stud){
			JOptionPane.showMessageDialog(null, "请选择要删除的演出厅");
			return; 
		}		
		
		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			StudioSrv stuSrv = new StudioSrv();
			stuSrv.delete(stud.getID());
			showTable();
		}
	}

	private void btnQueryClicked() {
		String studio_name = input.getText();
		if (!studio_name.equals("")) {
			//请自行补充
			StudioSelectUI SelectStudioUI = null;
			SelectStudioUI = new StudioSelectUI();
			SelectStudioUI.Select(studio_name);
			SelectStudioUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			SelectStudioUI.setWindowName("演出厅信息");
			SelectStudioUI.toFront();
			SelectStudioUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			SelectStudioUI.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		List<Studio> stuList = new StudioSrv().FetchAll();
		tms.showStudioList(stuList);
	}
	

	public static void main(String[] args) {
		StudioMgrUI frmStuMgr = new StudioMgrUI();
		frmStuMgr.setVisible(true);
	}
}
