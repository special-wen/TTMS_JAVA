package xupt.se.ttms.view.Selectplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import xupt.se.ttms.dao.SelectplayDAO;
import xupt.se.ttms.model.Selectplay;
import xupt.se.ttms.view.tmpl.PopUITmpl;
public class SelectUI extends PopUITmpl implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; 	//取消，保存按鈕

	protected boolean rst=false; 				//操作结果
	private JLabel lblNO, lblName, lblTelnum, lblAddr, lblEmail,lblnum;
	protected JLabel txtName,txtNO,txtTelnum,txtAddr,txtEmail,txtnum;
	Object data[] = new Object[6];
	@Override
	protected void initContent(){
		this.setTitle("添加用户");

		lblNO = new JLabel("剧目名称:");
		lblNO.setBounds(60,30,100,30);
		contPan.add(lblNO);     
		txtNO = new JLabel();
//		txtNO.setBounds(180, 30, 200, 30);
//		contPan.add(txtNO);
		
		lblName = new JLabel("上映时间:");
		lblName.setBounds(60, 80, 100, 30);
		contPan.add(lblName);
		txtName = new JLabel();
//		txtName.setBounds(180, 80, 200, 30);
//		contPan.add(txtName);

		lblTelnum = new JLabel("类型:");
		lblTelnum.setBounds(60, 130, 100, 30);
		contPan.add(lblTelnum);
		txtTelnum = new JLabel();
//		txtTelnum.setBounds(180, 210, 200, 30);
//		contPan.add(txtTelnum);
		
		lblAddr = new JLabel("地区");
		lblAddr.setBounds(60, 180, 100, 30);
		contPan.add(lblAddr);
		txtAddr = new JLabel();
//		txtAddr.setBounds(180, 260, 200, 30);
//		contPan.add(txtAddr);
  
		lblEmail = new JLabel("剧目状态:");
		lblEmail.setBounds(60, 230, 100, 30);
		contPan.add(lblEmail);
		txtEmail = new JLabel();
//		txtEmail.setBounds(180, 310, 200, 30);
//		contPan.add(txtEmail);
		
		
		lblnum = new JLabel("票数:");
		lblnum.setBounds(60, 280, 100, 30);
		contPan.add(lblnum);
		
		btnCancel = new JButton("关闭");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(120, 380, 60, 30);
		contPan.add(btnCancel);

	}
	protected void Select(String s){
		SelectplayDAO spdao=new SelectplayDAO();
		List<Selectplay> spList=null;
		spList=new LinkedList<Selectplay>();
		spList=spdao.select(s);
		this.setTitle("查询演出");
        Iterator<Selectplay> itr = spList.iterator();
		while (itr.hasNext()) {
			Selectplay sp = itr.next();
			
			data[0] = sp.getPlayname();
			data[1] = sp.getSchedtime();
			data[2] = sp.getPlaytype();
			data[3] = sp.getPlaylang();
			data[4] = Integer.toString(sp.getPlaystatus());
			data[5] = Integer.toString(sp.getTicketstatus());
			//System.out.println(sp.getPlayname()+ sp.getSchedtime()+sp.getPlaytypeid());
			//data[5]=user.getEmail();
			//System.out.println(data[0]+","+data[1]+","+data[2]+","+data[3]);
			txtNO = new JLabel((String) data[0]);
			txtNO.setBounds(180, 30, 200, 30);
			contPan.add(txtNO);
			txtName = new JLabel((String) data[1]);
			txtName.setBounds(180, 80, 200, 30);
			contPan.add(txtName);
			txtTelnum = new JLabel((String) data[2]);
			txtTelnum.setBounds(180, 130, 200, 30);
			contPan.add(txtTelnum);
			txtAddr = new JLabel((String) data[3]);
			txtAddr.setBounds(180, 180, 200, 30);
			contPan.add(txtAddr);
			txtEmail = new JLabel((String) data[4]);
			txtEmail.setBounds(180, 230, 200, 30);
			contPan.add(txtEmail);
			txtnum = new JLabel((String) data[5]);
			txtnum.setBounds(180, 280, 200, 30);
			contPan.add(txtnum);
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
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		SelectUI frmUserMgr = new SelectUI();
//		frmUserMgr.setVisible(true);
//	}
}