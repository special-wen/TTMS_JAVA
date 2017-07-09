//package xupt.se.ttms.view.login;
//author：努尔比亚
//import java.awt.Color;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.Insets;
//import java.awt.Point;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.UIManager;
//import javax.swing.border.EmptyBorder;
//
////import com.xy.today.sql.Employee;
//
///**
// */
//
//public class Login {
//
//	/**
//	 * 记住密码
//	 */
//	private JCheckBox jCheckBox_jizhu;
//	/**
//	 * 忘记密码
//	 */
//	private JLabel jLabel_wangji;
//	private MyPanel jPanel_login;
//	/**
//	 * 登陆的图片
//	 */
//	private ImageIcon imageIcon_login_bg = new ImageIcon(
//			Login.class.getResource("/com/today/images/denglu.gif"));
//	private ImageIcon imageIcon_login_hudie = new ImageIcon(
//			Login.class.getResource("/com/today/images/hudie.gif"));
//	private ImageIcon imageIcon_button = new ImageIcon(
//			Login.class.getResource("/com/today/images/button.png"));
//	/**
//	 * 关闭的图片
//	 */
//	private ImageIcon imageIcon_guanbi = new ImageIcon(
//			Login.class.getResource("/com/today/images/guanbi.png"));
//	/**
//	 * 最小的图片
//	 */
//	private ImageIcon imageIcon_zuixiaohua = new ImageIcon(
//			Login.class.getResource("/com/today/images/zuixiaohua.png"));
//	/**
//	 * 登陆窗体
//	 */
//	private JFrame jFrame_login = new JFrame();
//
//	/**
//	 * 登陆button
//	 */
//	private JButton jButton_denglu;
//	/**
//	 * 注册button
//	 */
//	private JButton jbButton_zhuce;
//	/**
//	 * 账号输入框
//	 */
//	private JTextField jTextField_zhanghao;
//	/**
//	 * 密码输入框
//	 */
//	private JPasswordField jPasswordField_mima;
//
//	private JButton jButton_guanbi;
//	private JButton jButton_zuixiaohua;
//
//	public static void main(String args[]) {
//		new Login();
//	}
//
//	public Login() {
//		jFrame_login.setSize(900, 700);
//
//		// 第一层嵌套，login的外层
//		jPanel_login = login_panel();
//		jPanel_login.setOpaque(false);
//
//		jFrame_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jFrame_login.setUndecorated(true);
//		// AWTUtilities.setWindowOpaque(jFrame_login, false);
//		jFrame_login.setContentPane(jPanel_login);
//		// 设置点击事件
//		Dianji();
//		jFrame_login.setBackground(Color.black);
//		jFrame_login.setLocationRelativeTo(null); // 设置窗体居中
//		jFrame_login.setVisible(true);
//	}
//
//	/**
//	 * 定义 log的类容
//	 * 
//	 * @return
//	 */
//	public MyPanel login_panel() {
//
//		imageIcon_login_hudie.setImage(imageIcon_login_hudie.getImage()
//				.getScaledInstance(800, 700, Image.SCALE_DEFAULT));
//		MyPanel jPanel = new MyPanel(imageIcon_login_hudie.getImage(), 800, 700);
//
//		Insets insets = new Insets(0, 0, 0, 0);
//		jPanel.setBorder(new EmptyBorder(80, 100, 150, 100));
//		// 设置关闭 最小化的布局
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		jPanel.setLayout(gridBagLayout);
//		GridBagConstraints gridBagConstraints = new GridBagConstraints();
//		gridBagConstraints.insets = insets;
//
//		JPanel jPanel_wei = gaunbi();
//		gridBagConstraints.fill = GridBagConstraints.BOTH;
//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 0;
//		gridBagConstraints.weightx = 200;
//		gridBagConstraints.weighty = 10;
//		jPanel.add(jPanel_wei, gridBagConstraints);
//
//		MyPanel jPanel_nei = denglu();
//		gridBagConstraints.fill = GridBagConstraints.BOTH;
//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 1;
//		gridBagConstraints.weightx = 200;
//		gridBagConstraints.weighty = 200;
//		jPanel.add(jPanel_nei, gridBagConstraints);
//		return jPanel;
//	}
//
//	private MyPanel denglu() {
//		imageIcon_login_bg.setImage(imageIcon_login_bg.getImage()
//				.getScaledInstance(600, 500, Image.SCALE_DEFAULT));
//		MyPanel jPane_denlu = new MyPanel(imageIcon_login_bg.getImage(), 600,
//				600);
//		jPane_denlu.setLayout(new GridLayout(6, 1));
//
//		/**
//		 * 空jpanel占用地方
//		 */
//		JPanel jPanel0 = new JPanel();
//		jPane_denlu.add(jPanel0);
//		jPanel0.setOpaque(false);
//
//		/**
//		 * jpanel1设置账号
//		 */
//		JPanel jPanel1 = new JPanel();
//		jPanel1.setLayout(new FlowLayout());
//		JLabel jLabel_zhanghao = new JLabel("账号", JLabel.RIGHT);
//		jLabel_zhanghao.setFont(new Font("宋体", Font.BOLD, 20));
//		jTextField_zhanghao = new JTextField(15);
//		jPanel1.add(jLabel_zhanghao);
//		jPanel1.setOpaque(false);
//		jPanel1.add(jTextField_zhanghao);
//		jPane_denlu.add(jPanel1);
//
//		/**
//		 * jpanel设置密码
//		 */
//		JPanel jPanel2 = new JPanel();
//		jPanel2.setLayout(new FlowLayout());
//		JLabel jLabel_mima = new JLabel("密码", JLabel.RIGHT);
//		jLabel_mima.setFont(new Font("宋体", Font.BOLD, 20));
//		jPanel2.add(jLabel_mima);
//		jPasswordField_mima = new JPasswordField(15);
//		jPanel2.add(jPasswordField_mima);
//		jPanel2.setOpaque(false);
//		jPane_denlu.add(jPanel2);
//
//		/**
//		 * jpanel设置忘记密码 和记住密码
//		 */
//		JPanel jPanel3 = new JPanel();
//		jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
//		jCheckBox_jizhu = new JCheckBox("记住密码");
//		jCheckBox_jizhu.setOpaque(false);
//		jPanel3.add(jCheckBox_jizhu);
//		jLabel_wangji = new JLabel("忘记密码");
//		jLabel_wangji.setFont(new Font("宋体", Font.BOLD, 15));
//		jPanel3.add(jLabel_wangji);
//		jPane_denlu.add(jPanel3);
//		jPanel3.setOpaque(false);
//
//		/**
//		 * 登陆 注册button
//		 */
//		imageIcon_button.setImage(imageIcon_button.getImage()
//				.getScaledInstance(30, 30, Image.SCALE_DEFAULT));
//		JPanel jPanel4 = new JPanel();
//		jButton_denglu = new JButton("登录", imageIcon_button);
//		jButton_denglu.setContentAreaFilled(false);
//		jPanel4.add(jButton_denglu);
//		jbButton_zhuce = new JButton("注册", imageIcon_button);
//		jbButton_zhuce.setContentAreaFilled(false);
//		jPanel4.add(jbButton_zhuce);
//		jPanel4.setOpaque(false);
//		jPane_denlu.add(jPanel4);
//
//		jPane_denlu.setOpaque(false);
//		return jPane_denlu;
//	}
//
//	/**
//	 * 登陆 注册 事件点击 处理
//	 */
//	private void Dianji() {
//		/**
//		 * 登录的事件点击
//		 */
//		jButton_denglu.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("登录点击成功");
//				String string_zhanghao = jTextField_zhanghao.getText();
//				String string_mima = jPasswordField_mima.getText();
//				if (string_mima.equals("") || string_zhanghao.equals("")) {
//					int num = 10;// 抖动次数
//					Point point = jFrame_login.getLocation();// 窗体位置
//					for (int i = 10; i > 0; i--) {
//						// 设置 真的
//						for (int j = num; j > 0; j--) {
//							point.y += i;
//							jFrame_login.setLocation(point);
//							point.x += i;
//							jFrame_login.setLocation(point);
//							point.y -= i;
//							jFrame_login.setLocation(point);
//							point.x -= i;
//							jFrame_login.setLocation(point);
//
//						}
//					}
//					/**
//					 * 系统警告信息
//					 */
//					JOptionPane.showMessageDialog(
//							jFrame_login.getContentPane(), "密码或账号不能为空!",
//							"today登录系统信息", JOptionPane.WARNING_MESSAGE);
//					System.out.println("密码或账号不能为空");
//				} else {
//					Employee employee = new Employee();
//					if (employee.loginin(string_zhanghao, string_mima)) {
//						System.out.println("登录成功");
//						String zhiwei = employee
//								.employeeZhiWei(string_zhanghao);
//						AdministratorGeRenXinXi.string_Gonghao = string_zhanghao;
//						ConductorGeRenXinXi.string_Gonghao = string_zhanghao;
//						/**
//						 * 通过登录 账号密码 然后从数据库中 获取职位是 管理员还是 售票员 然后 进行 界面的 选择登录
//						 */
//						jFrame_login.dispose();
//						new LoginJProgressBar();
//						if (zhiwei.equals("售票员")) {
//							LoginJProgressBar.tag = 1;
//							// new Conductor();
//						} else {
//							// new Administrator();
//							LoginJProgressBar.tag = 2;
//						}
//
//					} else {
//						int num = 10;// 抖动次数
//						Point point = jFrame_login.getLocation();// 窗体位置
//						for (int i = 10; i > 0; i--) {
//							// 设置 真的
//							for (int j = num; j > 0; j--) {
//								point.y += i;
//								jFrame_login.setLocation(point);
//								point.x += i;
//								jFrame_login.setLocation(point);
//								point.y -= i;
//								jFrame_login.setLocation(point);
//								point.x -= i;
//								jFrame_login.setLocation(point);
//
//							}
//						}
//						/**
//						 * 系统错误提示信息
//						 */
//						JOptionPane.showMessageDialog(
//								jFrame_login.getContentPane(), "密码或账号错误，登录失败!",
//								"today登录系统信息", JOptionPane.ERROR_MESSAGE);
//						System.out.println("登录失败");
//
//					}
//				}
//			}
//		});
//		/**
//		 * 注册的事件点击处理
//		 */
//		jbButton_zhuce.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				jFrame_login.dispose();
//				new Registered();
//				System.out.println("注册点击成功");
//			}
//		});
//
//		/**
//		 * 忘记密码的事件点击处理
//		 */
//		jLabel_wangji.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("忘记密码点击成功");
//			}
//		});
//	}
//
//	/**
//	 * 关闭的Jpanel
//	 * 
//	 * @return
//	 */
//	private JPanel gaunbi() {
//		JPanel jPanel_waibu = new JPanel();
//
//		Insets insets = new Insets(2, 2, 2, 2);
//		// 设置关闭 最小化的布局
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		jPanel_waibu.setLayout(gridBagLayout);
//		GridBagConstraints gridBagConstraints = new GridBagConstraints();
//		gridBagConstraints.insets = insets;
//
//		ImageIcon imageIcon_denglu = new ImageIcon(
//				Login.class.getResource("/com/today/images/denglu.png"));
//		imageIcon_denglu.setImage(imageIcon_denglu.getImage()
//				.getScaledInstance(550, 120, Image.SCALE_DEFAULT));
//		JLabel jLabel = new JLabel(imageIcon_denglu);
//		gridBagConstraints.fill = GridBagConstraints.BOTH;
//		gridBagConstraints.gridx = 0;
//		gridBagConstraints.gridy = 0;
//		gridBagConstraints.weightx = 1;
//		gridBagConstraints.weighty = 6;
//		jPanel_waibu.add(jLabel, gridBagConstraints);
//
//		imageIcon_guanbi.setImage(imageIcon_guanbi.getImage()
//				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//		jButton_guanbi = new JButton(imageIcon_guanbi);
//		jButton_guanbi.setContentAreaFilled(false);
//		jButton_guanbi.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("login的关闭button点击成功");
//				jFrame_login.dispose();
//			}
//		});
//		imageIcon_zuixiaohua.setImage(imageIcon_zuixiaohua.getImage()
//				.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
//		jButton_zuixiaohua = new JButton(imageIcon_zuixiaohua);
//		jButton_zuixiaohua.setContentAreaFilled(false);
//		jButton_zuixiaohua.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				System.out.println("login的最小化button点击成功");
//				jFrame_login.setExtendedState(JFrame.ICONIFIED);
//			}
//		});
//
//		jPanel_waibu.add(jButton_guanbi);
//		jPanel_waibu.add(jButton_zuixiaohua);
//		jPanel_waibu.setOpaque(false);
//		return jPanel_waibu;
//	}
//
//}
