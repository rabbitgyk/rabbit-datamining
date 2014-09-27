package test;

public class SubClass extends Parients{

	public int sb = 9;
	static{
		System.out.println("subclass static kuai");
	}
	
	public SubClass(){
		super(10);
		st = "siaodofffffffffffffffffff";
		System.out.println("subClass gou zaohanshu");
	}
	
	public static void main(String[] args) {
		Parients sc = new SubClass();
		Parients sc1 = new SubClass();
		System.out.println(sc.st);
		
	}
}
