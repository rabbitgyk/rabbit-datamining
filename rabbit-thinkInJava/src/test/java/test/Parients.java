package test;

public class Parients {
	
	private int mm = 0;
	public String st;
	
	public Parients(int mm) {
		this.mm = mm;
		init();
	}
	
	public Parients(){
		this(20);
	}
	
	public void init(){
		st = "parients init a String";
	}

}
