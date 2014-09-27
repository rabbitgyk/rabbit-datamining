package test;

public class Parients {
	
	private int mm = 0;
	public String st;
	static{
		System.out.println("静态块 Parients");
	}
	
	public Parients(int mm) {
		this.mm = mm;
		init();
	}
	
	public Parients(){
		System.out.println("parients gou zao");
	}
	
	public void init(){
		st = "parients init a String";
	}

}
