package xupt.se.ttms.model;

public class Schedule {
	private int sched_id=0;
	private int studio_id=0;
	private int play_id=0;
	private String sched_time="";
	private int sched_ticket_price=0;
	public void setSched_id(int ID){
		this.sched_id=ID;
	}
	public int getSched_id(){
		return this.sched_id;
	}
	public void setStudio_id(int ID){
		this.studio_id=ID;
	}
	public int getStudio_id(){
		return this.studio_id;
	}
	public void setPlay_id(int ID){
		this.play_id=ID;
	}
	public int getPlay_id(){
		return this.play_id;
	}
	public void setSched_time(String time){
		this.sched_time=time;
	}
	public String getSched_time(){
		return this.sched_time;
	}
	public void setSched_TPrice(int price){
		this.sched_ticket_price=price;
	}
	public int getSched_TPrice(){
		return this.sched_ticket_price;
	}
   
}
