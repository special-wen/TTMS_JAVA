package xupt.se.ttms.view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.util.DBUtil;
import xupt.se.ttms.view.Selectplay.SelectplayMgrUI;
import xupt.se.ttms.model.Users;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.tmpl.*;

public class UserAddUI extends PopUITmpl implements ActionListener,ItemListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblNO, lblName, lblTelnum1, lblTelnum2,lblAddr, lblEmail;
	JComboBox<Object> txtNO;
	JPasswordField passwordtext1,passwordtext2;
	protected JTextField txtName,txtAddr,txtEmail;
	

	@Override
	protected void initContent(){
		this.setTitle("添加用户");

		lblNO = new JLabel("用户工号:");
		lblNO.setBounds(60,30,100,30);
		contPan.add(lblNO);
		 final String def[] =
		 { "售票员","经理","管理员" };        
		txtNO = new JComboBox<Object>(def);
		txtNO.addItemListener(this);
		txtNO.setEditable(true);
		txtNO.setBounds(180, 30, 200, 30);
		contPan.add(txtNO);
		
		lblName = new JLabel("用户名:");
		lblName.setBounds(60, 80, 100, 30);
		contPan.add(lblName);
		txtName = new JTextField();
		txtName.setBounds(180, 80, 200, 30);
		contPan.add(txtName);

		lblTelnum1 = new JLabel("设置密码:");
		lblTelnum1.setBounds(60, 130, 100, 30);
		contPan.add(lblTelnum1);
        passwordtext1 = new JPasswordField(10);
        passwordtext1.setBounds(180, 130, 200, 30);
		contPan.add(passwordtext1);
		
		lblTelnum2 = new JLabel("确认密码:");
		lblTelnum2.setBounds(60, 180, 100, 30);
		contPan.add(lblTelnum2);
		passwordtext2 = new JPasswordField(10);
		passwordtext2.setBounds(180, 180, 200, 30);
		contPan.add(passwordtext2);
		
		lblAddr = new JLabel("用户地址:");
		lblAddr.setBounds(60, 250, 100, 30);
		contPan.add(lblAddr);
		txtAddr = new JTextField();
		txtAddr.setBounds(180, 250, 200, 30);
		contPan.add(txtAddr);
  
		lblEmail = new JLabel("用户邮箱:");
		lblEmail.setBounds(60, 300, 100, 30);
		contPan.add(lblEmail);
		txtEmail = new JTextField();
		txtEmail.setBounds(180, 300, 200, 30);
		contPan.add(txtEmail);
		
		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(80, 380, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(280, 380, 60, 30);
		contPan.add(btnCancel);

	}
	
	
	public boolean getReturnStatus(){
		   return rst;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}
	
	protected void btnSaveClicked(){
		if (txtNO.getSelectedItem()!= null && txtName.getText() != null && passwordtext1.getText() != null
			&& passwordtext2.getText() != null && txtAddr.getText() != null&&txtEmail.getText()!=null) {
			UserSrv userSrv = new UserSrv();
			Users user=new Users();
			user.setNO(txtNO.getSelectedItem());
			user.setName(txtName.getText());
			user.setPassword(passwordtext1.getText());
			user.setSure(passwordtext2.getText());
			user.setAddr(txtAddr.getText());
			user.setEmail(txtEmail.getText());

			userSrv.add(user);
			this.setVisible(false);
			rst=true;
		} 
		else {
			if(txtName.getText() == null)
			JOptionPane.showMessageDialog(null, "数据不完整");
		}		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		txtNO.getSelectedItem();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserAddUI frmUserMgr = new UserAddUI();
		frmUserMgr.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmUserMgr.setVisible(true);
	}
}