package xupt.se.ttms.view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.util.Iterator;

import xupt.se.ttms.view.Selectplay.SelectplayMgrUI;
import xupt.se.ttms.dao.UserDAO;
import xupt.se.ttms.model.Users;
import xupt.se.ttms.view.tmpl.PopUITmpl;

public class UserSelectUI extends PopUITmpl implements ActionListener {

	private JButton btnCancel; 	//关闭按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblName, lblNO, lblAddr, lblTelnum,lblEmail;
	protected JLabel txtName, txtNO, txtAddr,txtEmail;
	JPasswordField passwordtext1,passwordtext2;
	Object data[] = new Object[7];
	protected void Select(String s){
		UserDAO userdao=new UserDAO();
		List<Users> uList=null;
		uList=new LinkedList<Users>();
		uList=userdao.select(s);
		this.setTitle("用户信息");
		if(uList!=null){
        Iterator<Users> itr = uList.iterator();
		while (itr.hasNext()) {
			Users user = itr.next();
			data[0] = Integer.toString(user.getID());
			data[1] = user.getNO();
			data[2] = user.getName();
			data[3] = user.getPassword();
			data[4] = user.getSure();
			data[5]=user.getAddr();
			data[6]=user.getEmail();
			System.out.println(data[0].toString()+","+data[1].toString()+","+data[2].toString()+","+data[3].toString());
		}
		lblNO = new JLabel("用户工号:");
		lblNO.setBounds(60, 30, 100, 30);
		contPan.add(lblNO);
		txtNO = new JLabel((String)data[1]);
		txtNO.setBounds(180, 30, 200, 30);
		contPan.add(txtNO);

		lblName = new JLabel("用户名:");
		lblName.setBounds(60, 80, 100, 30);
		contPan.add(lblName);
		txtName = new JLabel((String)data[2]);
		txtName.setBounds(180, 80, 200, 30);
		contPan.add(txtName);
		
		lblTelnum = new JLabel("用户密码:");
		lblTelnum.setBounds(60, 210, 100, 30);
		contPan.add(lblTelnum);
		passwordtext1 = new JPasswordField((String)data[3]);
		passwordtext1.setEditable(false);
		passwordtext1.setBounds(180, 210, 200, 30);
		contPan.add(passwordtext1);
  
		lblAddr = new JLabel("用户地址:");
		lblAddr.setBounds(60, 260, 100, 30);
		contPan.add(lblAddr);
		txtAddr = new JLabel((String)data[5]);
		txtAddr.setBounds(180, 260, 200, 30);
		contPan.add(txtAddr);

		lblEmail = new JLabel("用户邮箱:");
		lblEmail.setBounds(60, 310, 100, 30);
		contPan.add(lblEmail);
		txtEmail = new JLabel((String)data[6]);
		txtEmail.setBounds(180, 310, 200, 30);
		contPan.add(txtEmail);

		btnCancel = new JButton("关闭");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 350, 60, 30);
		contPan.add(btnCancel);
		}
	}
	public boolean getReturnStatus(){
		   return rst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst=false;
			this.setVisible(false);
		} 
	}
}