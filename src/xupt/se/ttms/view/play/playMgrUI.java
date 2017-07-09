package xupt.se.ttms.view.play;
/**
 * author:
 * 		minpan
 * 		liufan
 */
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import xupt.se.ttms.model.DataDict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DataDictSrv;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.tmpl.*;
	class playTable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;//serialVersionUID用来作为Java对象序列化中的版本标示之用
		private JTable jt;

		public playTable(JScrollPane jp) {
			//通过DefaultTableModel可以对表格进行数据的增删改
			DefaultTableModel tabModel=new DefaultTableModel(){
				private static final long serialVersionUID = 1L;

				@Override              
				public boolean isCellEditable(int row,int column){
					return false;              
				};
			};
			tabModel.addColumn("剧目ID");
			tabModel.addColumn("名称");
			tabModel.addColumn("类型");
			tabModel.addColumn("语言");
			tabModel.addColumn("简介");
			tabModel.addColumn("剧目海报");
			tabModel.addColumn("演出时长");
			tabModel.addColumn("票价");
			tabModel.addColumn("状态");
			//初始化列名
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
	        column.setPreferredWidth(50);
	        column = columnModel.getColumn(2);
	        column.setPreferredWidth(50);
	        column = columnModel.getColumn(3);
	        column.setPreferredWidth(50);        
	        column = columnModel.getColumn(4);
	        column.setPreferredWidth(150); 
	        column = columnModel.getColumn(5);
	        column.setMinWidth(0);
	        column.setMaxWidth(0);
	        column.setWidth(0);
	        column.setPreferredWidth(0);
	        column = columnModel.getColumn(6);
	        column.setPreferredWidth(20); 
	        column = columnModel.getColumn(7);
	        column.setPreferredWidth(20); 
	        column = columnModel.getColumn(8);
	        column.setPreferredWidth(20); 
	        
			jp.add(jt);
			jp.setViewportView(jt);
			
		}
		
		public Play getplay() {
			int rowSel=jt.getSelectedRow();
			if(rowSel>=0){
				Play pla = new Play();
				pla.setID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
//				pla.setName(jt.getValueAt(rowSel, 1).toString());
				
//				DataDictSrv ddsrv = new DataDictSrv();
//				List<DataDict> dList=ddsrv.Fetch("dict_value='"+jt.getValueAt(rowSel, 2).toString()+"'");
//				Iterator<DataDict> itr=dList.iterator();
//				DataDict dd=new DataDict();
//				while(itr.hasNext()){
//					dd=itr.next();
//				}
//				pla.setPlay_type_id(dd.getId());
				
//				dList=ddsrv.Fetch("dict_value='"+jt.getValueAt(rowSel, 3).toString()+"'");
//				itr=dList.iterator();
//				dd=new DataDict();
//				while(itr.hasNext()){
//					dd=itr.next();
////			/	}
//				pla.setPlay_lang_id(dd.getId());
//				pla.setIntroduction(jt.getValueAt(rowSel, 4).toString()); 
//				
				PlaySrv playsrv= new PlaySrv();
				List<Play> pList=playsrv.Fetch("play_id="+Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
				Iterator<Play> itr1 = pList.iterator();
				while(itr1.hasNext()){
					pla=itr1.next();
				}
//				pla.setImage();
//				pla.setLength(Integer.parseInt(jt.getValueAt(rowSel, 6).toString()));
//				pla.setTicket_price(Integer.parseInt(jt.getValueAt(rowSel, 7).toString()));
//				pla.setStatus(Integer.parseInt(jt.getValueAt(rowSel, 8).toString()));

				return pla;
			}
			else{
				return null;
			}
				
		}
		
		// 创建JTable
		public void showplayList(List<Play> plaList) {
			try {
				DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
				tabModel.setRowCount(0);
				
				Iterator<Play> itr = plaList.iterator();
				while (itr.hasNext()) {
					Play pla = itr.next();
					Object data[] = new Object[9];
					data[0] = Integer.toString(pla.getID());
					data[1] = pla.getName();
					DataDictSrv ddsrv = new DataDictSrv();
					List<DataDict> dList=ddsrv.Fetch("dict_id="+pla.getPlay_type_id());
					Iterator<DataDict> itr1=dList.iterator();
					DataDict dd=new DataDict();
					while(itr1.hasNext()){
						dd=itr1.next();
					}
					data[2] = dd.getValue();
					
					dList=ddsrv.Fetch("dict_id="+pla.getPlay_lang_id());
					itr1=dList.iterator();
					dd=new DataDict();
					while(itr1.hasNext()){
						dd=itr1.next();
					}
					data[3] = dd.getValue();
					data[4] = pla.getIntroduction();
					data[5] = pla.getImage();
					data[6] = Integer.toString(pla.getLength());
					data[7]=Integer.toString(pla.getTicket_price());
					data[8]=Integer.toString(pla.getStatus());
					tabModel.addRow(data);;
				}
				jt.invalidate();//更新表格

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class playMgrUI extends MainUITmpl {
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
		private JButton btnAdd, btnEdit, btnDel, btnQuery;
		
		playTable tms; //显示剧目列表


		public playMgrUI() {
			
		}
		public void showCurrentUser(){
			LoginedUser curUser=LoginedUser.getInstance();
			String name=curUser.getEmpName();
			if(null==name ||  name.isEmpty())
				usrName.setText("经理");
			else
				usrName.setText(name);		
		}
		// To be override by the detailed business block interface
		@Override
		protected void initContent() {
			Rectangle rect = contPan.getBounds();

			ca1 = new JLabel("剧目管理", JLabel.CENTER);
			ca1.setBounds(0, 5, rect.width, 30);
			ca1.setFont(new java.awt.Font("宋体", 1, 20));
			ca1.setForeground(Color.blue);
			contPan.add(ca1);

			jsc = new JScrollPane();
			jsc.setBounds(0, 40, rect.width, rect.height - 90);
			contPan.add(jsc);

			hint = new JLabel("请输入剧目名称:", JLabel.RIGHT);
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
			
			tms = new playTable(jsc);
			
			showTable();
		}

		private void btnAddClicked() {

			playAddUI addPlaUI=null;
			
			addPlaUI = new playAddUI();
			addPlaUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			addPlaUI.setWindowName("添加剧目");
			addPlaUI.toFront();
			addPlaUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			addPlaUI.setVisible(true);
			if (addPlaUI.getReturnStatus()) {
				showTable();
			}
		}

		private void btnModClicked() {
			Play pla = tms.getplay();
			if(null== pla){
				JOptionPane.showMessageDialog(null, "请选择要修改的剧目");
				return; 
			}
			
			playEditUI modPlaUI = new playEditUI(pla);
			modPlaUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			modPlaUI.setWindowName("修改剧目");
			modPlaUI.toFront();
			modPlaUI.setModal(true);
			modPlaUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
			modPlaUI.setVisible(true);

			if (modPlaUI.getReturnStatus()) {
				showTable();
			}	
		}

		private void btnDelClicked() {
			Play pla = tms.getplay();
			if(null== pla){
				JOptionPane.showMessageDialog(null, "请选择要删除的剧目");
				return; 
			}		
			
			int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				PlaySrv stuSrv = new PlaySrv();
				stuSrv.delete(pla.getID());
				showTable();
			}
		}

		private void btnQueryClicked() {
			String play_name=input.getText();
			if (!play_name.equals("")) {
				playSelectUI SelectPlayUI=null;
				SelectPlayUI = new playSelectUI();
				SelectPlayUI.Select(play_name);
				SelectPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				SelectPlayUI.setWindowName("剧目信息");
				SelectPlayUI.toFront();
				SelectPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
				SelectPlayUI.setVisible(true);

			} else if(play_name.equals("")){
				JOptionPane.showMessageDialog(null, "请输入检索条件！");
			}
			
			/*
			 * 
			 * 
			 * 输入的剧目不存在时应警告：该剧目不存在，不能没有提示！！！！
			 * 
			 *
			 */
		
		}

		private void showTable() {
			List<Play> plaList = new PlaySrv().FetchAll();
			tms.showplayList(plaList);
		}
		

		public static void main(String[] args) {
			playMgrUI frmStuMgr = new playMgrUI();
			frmStuMgr.setVisible(true);
		}
	}

