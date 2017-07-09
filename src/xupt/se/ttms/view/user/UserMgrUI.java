package xupt.se.ttms.view.user;
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
import xupt.se.ttms.model.Users;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.tmpl.MainUITmpl;
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
		tabModel.addColumn("用户ID");
		tabModel.addColumn("用户工号");
		tabModel.addColumn("用户名 ");
		tabModel.addColumn("用户密码");
		tabModel.addColumn("用户确认密码");
		tabModel.addColumn("用户地址");
		tabModel.addColumn("用户邮箱");
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
//        column.setPreferredWidth(10);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column = columnModel.getColumn(4);
//        column.setPreferredWidth(10);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setWidth(0);
        column = columnModel.getColumn(5);
        column.setPreferredWidth(10);        
        column = columnModel.getColumn(6);
        column.setPreferredWidth(10);        

		
		jp.add(jt);
		jp.setViewportView(jt);
		
	}
	
	public Users getUser() {
		int rowSel=jt.getSelectedRow();
		if(rowSel>=0){
			Users user = new Users();
			user.setID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			user.setNO(jt.getValueAt(rowSel,1).toString());
			user.setName(jt.getValueAt(rowSel, 2).toString());
			user.setPassword(jt.getValueAt(rowSel, 3).toString());
			user.setSure(jt.getValueAt(rowSel, 4).toString());
			user.setAddr(jt.getValueAt(rowSel, 5).toString());
			user.setEmail(jt.getValueAt(rowSel, 6).toString());
			//判断是否为空，为空时则不显示，不为空则添加
//			if (jt.getValueAt(rowSel, 5) != null)
//				user.setEmail(jt.getValueAt(rowSel, 5).toString());
//			else
//				user.setEmail("");
			return user;
		}
		else{
			return null;
		}
			
	}
	
	// 创建JTable
	public void showUserList(List<Users> userList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);
			
			Iterator<Users> itr = userList.iterator();
			while (itr.hasNext()) {
				Users user = itr.next();
				Object data[] = new Object[7];
				data[0] = Integer.toString(user.getID());
				data[1] = user.getNO();
				data[2] = user.getName();
				data[3] = user.getPassword();
				data[4] = user.getSure();
				data[5] = user.getAddr();
				data[6] = user.getEmail();
				tabModel.addRow(data);
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
public class UserMgrUI extends MainUITmpl{
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;
	
	UserTable tms; //显示用户列表
	public UserMgrUI(){
	}
	// To be override by the detailed business block interface
		@Override
		public void showCurrentUser(){
			LoginedUser curUser=LoginedUser.getInstance();
			String name=curUser.getEmpName();
			if(null==name ||  name.isEmpty())
				usrName.setText("管理员");
			else
				usrName.setText(name);		
		}
		protected void initContent() {
			Rectangle rect = contPan.getBounds();

			ca1 = new JLabel("用户管理", JLabel.CENTER);
			ca1.setBounds(0, 5, rect.width, 30);
			ca1.setFont(new java.awt.Font("宋体", 1, 20));
			ca1.setForeground(Color.blue);
			contPan.add(ca1);

			jsc = new JScrollPane();
			jsc.setBounds(0, 40, rect.width, rect.height - 90);
			contPan.add(jsc);

			hint = new JLabel("请输入用户名:", JLabel.RIGHT);
			hint.setBounds(60, rect.height - 45, 150, 30);
			contPan.add(hint);

			input = new JTextField();
			input.setBounds(220, rect.height - 45, 200, 30);
			contPan.add(input);

			// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
			btnQuery = new JButton("查找");
			btnQuery.setBounds(440, rect.height - 45, 60, 30);
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
			
			tms = new UserTable(jsc);
			
			showTable();
		}

		private void btnAddClicked() {

			UserAddUI addUserUI=null;
			
			addUserUI = new UserAddUI();
//			addUserUI.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			addUserUI.setWindowName("添加用户");
			addUserUI.toFront();
			addUserUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			addUserUI.setVisible(true);
			if (addUserUI.getReturnStatus()){
				showTable();
			}
		}

		private void btnModClicked() {
			Users user = tms.getUser();
			if(null== user){
				JOptionPane.showMessageDialog(null, "请选择要修改的用户");
				return; 
			}
			
			UserEditUI editUserUI = new UserEditUI(user);
			editUserUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			editUserUI.setWindowName("修改用户");
			editUserUI.initData(user);
			editUserUI.toFront();
			editUserUI.setModal(true);
			editUserUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			editUserUI.setVisible(true);
			if (editUserUI.getReturnStatus()) {
				showTable();
			}	
		}

		private void btnDelClicked() {
			Users user = tms.getUser();
			if(null== user){
				JOptionPane.showMessageDialog(null, "请选择要删除的用户");
				return; 
			}		
			
			int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				UserSrv userSrv = new UserSrv();
				userSrv.delete(user.getID());
				showTable();
			}
		}

		private void btnQueryClicked() {
			String user_name=input.getText();
			if (!user_name.equals("")) {
				UserSelectUI SelectUserUI=null;
				SelectUserUI = new UserSelectUI();
				SelectUserUI.Select(user_name);
				SelectUserUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				SelectUserUI.setWindowName("查找的信息");
				SelectUserUI.toFront();
				SelectUserUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				SelectUserUI.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "请输入检索条件");
			}
		}

		private void showTable() {
			List<Users> userList = new UserSrv().FetchAll();
			tms.showUserList(userList);
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserMgrUI frmUserMgr = new UserMgrUI();
		frmUserMgr.setVisible(true);
	}

}