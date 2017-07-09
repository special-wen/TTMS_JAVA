package xupt.se.ttms.view.play;

import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import xupt.se.ttms.model.DataDict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.play.playAddUI;

public class playEditUI extends playAddUI {
		private static final long serialVersionUID = 1L;
		private Play pla1;
		public playEditUI(Play pla2){
			initData(pla2);
		}
		
		public void initData(Play pla2) {
			if(null== pla2){
				return;
			}
			//根据play里的play_type_id，在数据字典里找出对应的dict_value，显示到下拉框
			DataDictSrv ddsrv=new DataDictSrv();
			List<DataDict> dList=ddsrv.Fetch("dict_id="+pla2.getPlay_type_id());
			Iterator<DataDict> itr=dList.iterator();
			DataDict dd=new DataDict();
			while(itr.hasNext()){
				dd=itr.next();
			}
			txtType.setSelectedItem(dd.getValue());
			
			ddsrv=new DataDictSrv();
			dList=ddsrv.Fetch("dict_id="+pla2.getPlay_lang_id());
			itr=dList.iterator();
			dd=new DataDict();
			while(itr.hasNext()){
				dd=itr.next();
			}
			txtLang.setSelectedItem(dd.getValue());
			
			txtName.setText(pla2.getName());
			pic="Cache/Play_Image/"+pla2.getID()+"_"+pla2.getName()+".jpg";
			Icon icon1=new ImageIcon(pic);
			JLabel imagelab=new JLabel(icon1);
			imagelab.setBounds(500, 150, 150, 200);
			this.add(imagelab);
			txtIntro.setText(pla2.getIntroduction().toString());
			txtLen.setText(String.valueOf(pla2.getLength()));
			txtTprice.setText(String.valueOf(pla2.getTicket_price()));
			txtStatus.setText(String.valueOf(pla2.getStatus()));
			pla1=pla2;
			this.invalidate();
			this.setTitle("修改剧目");
		}

		@Override
		protected void btnSaveClicked(){
			if ( txtIntro.getText() != null && txtType.getSelectedItem().toString()!=null
					&& txtLang.getSelectedItem().toString()!=null && txtLen.getText() != null
					&&txtTprice.getText()!=null&&txtStatus.getText()!=null 
					/*&& pic!=null*/) {
				String playtype=txtType.getSelectedItem().toString();//获取下拉列表所选值
				DataDictSrv ddsrv = new DataDictSrv();
				List<DataDict> dList=ddsrv.Fetch("dict_value='"+playtype+"'");
				Iterator<DataDict> itr=dList.iterator();
				DataDict dd=new DataDict();
				while(itr.hasNext()){
					dd=itr.next();
				}//获取到下拉列表所选值在数字字典中的id
				PlaySrv plaSrv = new PlaySrv();
				Play pla3= pla1;
				pla3.setPlay_type_id(dd.getId());
				
				String playlang=txtLang.getSelectedItem().toString();//获取下拉列表所选值
				ddsrv = new DataDictSrv();
				dList=ddsrv.Fetch("dict_value='"+playlang+"'");
				itr=dList.iterator();
				dd=new DataDict();
				while(itr.hasNext()){
					dd=itr.next();
				}//获取到下拉列表所选值在数字字典中的id
				pla3.setPlay_lang_id(dd.getId());
				pla3.setName(txtName.getText());
				pla3.setIntroduction(txtIntro.getText());
				pla3.setLength(Integer.parseInt(txtLen.getText()));
				pla3.setTicket_price(Integer.parseInt(txtTprice.getText()));
				pla3.setStatus(Integer.parseInt(txtStatus.getText()));
				plaSrv.modify(pla3,pic);
				this.setVisible(false);
				rst=true;
				
			} else {
				JOptionPane.showMessageDialog(null, "数据不完整");
			}		
		}
		
	}


