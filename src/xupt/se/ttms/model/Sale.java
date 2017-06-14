package xupt.se.ttms.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.system.SysUserModUI;
import xupt.se.ttms.view.tmpl.ImagePanel;

/**
 * @author Saler
 *
 */

public class Sale{
	   private int ticket_id;
	   private int seat_id;
	   private int sched_id;
	   private float ticket_price;
	   private int ticket_status;
	   public void setticketID(int ticketID){
			this.ticket_id=ticketID;
		}
		
		public int getticketID(){
			return ticket_id;
		}
		
		public void setseatID(int seatID){
			this.seat_id=seatID;
		}
		
		public int getseatID(){
			return seat_id;
		}
		
		public void setschedID(int schedID){
			this.sched_id=schedID;
		}
		
		public int getschedID(){
			return sched_id;
		}
		public void setticketPrice(float f){
			this.ticket_price=f;
		}
		
		public float getticketPrice(){
			return ticket_price;
		}
		
		public void setticketStatus(int ticketStatus){
			this.ticket_status=ticketStatus;
		}
		
		public int getticketStatus(){
			return ticket_status;
		}	
		
}


