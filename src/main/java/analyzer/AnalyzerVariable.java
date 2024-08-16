package analyzer;

public class AnalyzerVariable {

	private String accessModifier = "";
	private String fieldModifier = "";
	private String type = "";
	private String name = "";
	private AnalyzerMethod getterMethod = new AnalyzerMethod(null);
	private AnalyzerMethod setterMethod = new AnalyzerMethod(null);
	private Class ownerClass;
	
	public void display() {
		System.out.println("ownerClass:" + ownerClass.getName());
		System.out.println("accessModifier:" + accessModifier);
		System.out.println("fieldModifier:" + fieldModifier);
		System.out.println("type:" + type);
		System.out.println("name:" + name);
		System.out.println("getterMethodName:" + getterMethod.getName());
		System.out.println("setterMethodName:" + setterMethod.getName());
		System.out.println();
	}
	
	public AnalyzerVariable(Class oC) {
		ownerClass = oC;
	}
	
	public void setAccessModifier(String input) {
		accessModifier = input;
	}
	
	public void setFieldModifier(String input) {
		fieldModifier = input;
	}
	
	public void setType(String input) {
		type = input;
	}
	
	public void setName(String input) {
		name = input;
	}
	
	public void setGetterMethod(AnalyzerMethod input) {
		getterMethod = input;
	}
	
	public void setSetterMethod(AnalyzerMethod input) {
		setterMethod = input;
	}
	
	public String getAccessModifier() {
		return accessModifier;
	}
	
	public String getFieldModifier() {
		return fieldModifier;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public AnalyzerMethod getGetterMethod() {
		return getterMethod;
	}
	
	public AnalyzerMethod getSetterMethod() {
		return setterMethod;
	}
	
	public Class getOwnerClass() {
		return ownerClass;
	}
}
