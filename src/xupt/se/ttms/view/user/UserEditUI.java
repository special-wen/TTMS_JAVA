package xupt.se.ttms.view.user;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Users;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.user.UserAddUI;

public class UserEditUI extends UserAddUI{

	private static final long serialVersionUID = 1L;
	private Users user;

	public UserEditUI(Users u){
		initData(u);
	}
	
	public void initData(Users u) {
		if(null== u){
			return;
		}
		String str=txtNO.getSelectedItem().toString();
		txtNO.setToolTipText(str);
		txtName.setText(u.getName().toString());
		passwordtext1.setText(u.getPassword().toString());
		passwordtext2.setText(u.getSure().toString());
		txtAddr.setText(u.getAddr().toString());
		txtEmail.setText(u.getEmail().toString());
		
		user=u;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked(){
		JOptionPane.showConfirmDialog(null, "确认所选修改？", "修改", JOptionPane.YES_NO_OPTION);
		if ( txtNO.getSelectedItem() != null&&txtName.getText()!=null	
			&&passwordtext1.getText()!=null && passwordtext2.getText()!=null &&txtAddr.getText()!=null && txtEmail.getText()!=null) {
			UserSrv userSrv = new UserSrv();
			Users u0= user;
			u0.setNO(txtNO.getSelectedItem());
			u0.setName(txtName.getText());
			u0.setPassword(passwordtext1.getText());
			u0.setSure(passwordtext2.getText());
			u0.setAddr(txtAddr.getText());
			u0.setEmail(txtEmail.getText());
			userSrv.modify(u0);
			this.setVisible(false);
			rst=true;
			
		} else {
			JOptionPane.showMessageDialog(null, "���ݲ�����");
		}		
	}
}
