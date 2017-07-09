package xupt.se.ttms.model;

public class Selectplay {
	private String playname;
	private String schedtime;
	private String playtype;
	private String playlang;
	private int playstatus;
	private int ticketstatus;
	public void setPlayname(String Playname){
		this.playname=Playname;
	}
	public String getPlayname(){
		return playname;
	}
	public void setSchedtime(String Schedtime){
		this.schedtime=Schedtime;
	}
	public String getSchedtime(){
		return schedtime;
	}
	public void setPlaytype(String Playtype){
		this.playtype=Playtype;
	}
	public String getPlaytype(){
		return playtype;
	}
	public void setPlaylang(String Playlang){
		this.playlang=Playlang;
	}
	public String getPlaylang(){
		return playlang;
	}
	public void setPlaystatus(int Playstatus){
		this.playstatus=Playstatus;
	}
	public int getPlaystatus(){
		return playstatus;
	}
	public void setTicketstatus(int Ticketstatus){
		this.ticketstatus=Ticketstatus;
	}
	public int getTicketstatus(){
		return ticketstatus;
	}
}