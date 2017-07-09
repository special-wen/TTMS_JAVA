package xupt.se.ttms.view.Selectplay;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
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
import xupt.se.ttms.model.Selectplay;
import xupt.se.ttms.model.Users;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.SelectplaySrv;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.ttms.view.user.UserAddUI;
import xupt.se.ttms.view.user.UserEditUI;
import xupt.se.ttms.view.user.UserSelectUI;
class UserTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable jt;
	public UserTable(JScrollPane jp) {
		
		DefaultTableModel tabModel=new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			@Override              
			public boolean isCellEditable(int row,int column){
				return false;              
			};
		};
		tabModel.addColumn("剧目名称");
		tabModel.addColumn("上映时间");
		tabModel.addColumn("剧目类型");
		tabModel.addColumn("剧目语言");
		tabModel.addColumn("剧目状态");
		tabModel.addColumn("剧目票数");
		//初始化列明
		jt=new JTable(tabModel);	
		
		//设置各列的宽度
	    TableColumnModel columnModel = jt.getColumnModel();
	    
	    //隐藏ID这一列
        TableColumn column = columnModel.getColumn(0);
//        column.setMinWidth(0);
//        column.setMaxWidth(0);
//        column.setWidth(0);
        column = columnModel.getColumn(0);
        column.setPreferredWidth(0);

        column = columnModel.getColumn(1);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(2);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(3);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(4);
        column.setPreferredWidth(10);
        column = columnModel.getColumn(5);
//        column.setPreferredWidth(10);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Selectplay getUser() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Selectplay sp = new Selectplay();
			sp.setPlayname(jt.getValueAt(rowSel, 0).toString());
			sp.setSchedtime(jt.getValueAt(rowSel,1).toString());
			sp.setPlaytype(jt.getValueAt(rowSel, 2).toString());
			sp.setPlaylang(jt.getValueAt(rowSel, 3).toString());
			sp.setPlaystatus(Integer.parseInt(jt.getValueAt(rowSel, 4).toString()));
			sp.setTicketstatus(Integer.parseInt(jt.getValueAt(rowSel, 5).toString()));
			return sp;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showUserList(List<Selectplay> spList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Selectplay> itr = spList.iterator();
			while (itr.hasNext()) {
				Selectplay sp = itr.next();
				Object data[] = new Object[6];
				data[0] = sp.getPlayname();
				data[1] = sp.getSchedtime();
				data[2] = sp.getPlaytype();
				data[3] = sp.getPlaylang();
				data[4] = Integer.toString(sp.getPlaystatus());
				data[5] = Integer.toString(sp.getTicketstatus());
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
public class SelectplayMgrUI extends MainUITmpl{
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnQuery;
	
	UserTable tms; //显示用户列表
	public SelectplayMgrUI(){
	}
	// To be override by the detailed business block interface
		@Override
		public void showCurrentUser(){
			LoginedUser curUser=LoginedUser.getInstance();
			String name=curUser.getEmpName();
			if(null==name ||  name.isEmpty())
				usrName.setText("售票员");
			else
				usrName.setText(name);		
		}
		protected void initContent() {
			Rectangle rect = contPan.getBounds();

			ca1 = new JLabel("查询演出", JLabel.CENTER);
			ca1.setBounds(0, 5, rect.width, 30);
			ca1.setFont(new java.awt.Font("宋体", 1, 20));
			ca1.setForeground(Color.blue);
			contPan.add(ca1);

			jsc = new JScrollPane();
			jsc.setBounds(0, 40, rect.width, rect.height - 90);
			contPan.add(jsc);

			hint = new JLabel("请输入剧目名称:", JLabel.RIGHT);
			hint.setBounds(250, rect.height - 45, 150, 30);
			contPan.add(hint);

			input = new JTextField();
			input.setBounds(400, rect.height - 45, 200, 30);
			contPan.add(input);

			// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
			btnQuery = new JButton("查找");
			btnQuery.setBounds(650, rect.height - 45, 60, 30);
			btnQuery.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent Event) {
						btnQueryClicked();
			}
		});
			contPan.add(btnQuery);
			contPan.add(ca1);
			
			tms = new UserTable(jsc);
			showTable();
		}

		private void btnQueryClicked() {
			String user_name=input.getText();
			//System.out.println(user_name);
			if (!user_name.equals("")){
				SelectUI spUI=null;
				spUI = new SelectUI();
				spUI.Select(user_name);
//				spUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				spUI.setWindowName("查找的信息");
				spUI.toFront();
				spUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				spUI.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "请输入检索条件");
			}
		}

		private void showTable() {
			List<Selectplay> userList = new SelectplaySrv().FetchAll();
			tms.showUserList(userList);
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SelectplayMgrUI frmUserMgr = new SelectplayMgrUI();
		frmUserMgr.setVisible(true);
	}

}