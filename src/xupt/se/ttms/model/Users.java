package xupt.se.ttms.model;

public class Users {
	private int id=0;
	private Object no="";//工号
	private String name="";
	private String password="";
	private String sure="";
	private String addr="";
	private String email="";
	//other infor
	public void setID(int ID){
		this.id=ID;
	}
	public int getID(){
		return id;
	}
	public void setNO(Object NO){
		this.no=NO;
	}
	public Object getNO(){
		return no;
	}
	public void setName(String Name){
		this.name=Name;
	}
	public String getName(){
		return name;
	}
	public void setPassword(String Password){
		this.password=Password;
	}
	public String getPassword(){
		return password;
	}
	public void setSure(String Sure){
		this.sure=Sure;
	}
	public String getSure(){
		return sure;
	}
	public void setAddr(String Addr){
		this.addr=Addr;
	}
	public String getAddr(){
		return addr;
	}
	public void setEmail(String Email){
		this.email=Email;
	}
	public String getEmail(){
		return email;
	}
}