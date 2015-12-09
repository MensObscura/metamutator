package foo;
public class Foo {
	
	public boolean op(Boolean a, Boolean b) {
		return a || b;
	}
	
	public boolean op2(Integer a, Integer b) {
		return a > b;
	}

	public boolean op3(Class c) {
		return Foo.class==c;
	}
	
	public boolean op4() {
		String medt = "Theobaldie";
		Integer tot = 11;
		Integer sot =  new Integer(1);
		String tu = null;
		return true;
	}
}