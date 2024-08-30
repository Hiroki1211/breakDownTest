package dataSet;

import java.util.ArrayList;

public class UnitTest {

	private ArrayList<Method> constructorLists = new ArrayList<Method>();
	private ArrayList<Method> constructorArgumentLists = new ArrayList<Method>();
	private ArrayList<Method> methodLists = new ArrayList<Method>();
	private ArrayList<Method> argumentMethodLists = new ArrayList<Method>();
	private Method method = null;
	private ArrayList<String> assertionLists = new ArrayList<String>();
	
	private ArrayList<String> constructorArrayLists = new ArrayList<String>();
	private String owner;
	
	private ArrayList<String> unitTestStatement = new ArrayList<String>();
	
	public void addTestDeclarationUnitTestStatement(String input) {
		unitTestStatement.add(1, input);
	}
	
	public void createUnitTest() {
		unitTestStatement = new ArrayList<String>();
		unitTestStatement.add("@Test");
		
		unitTestStatement.add("\t" + "// create Instance for Method");
		for(int i = 0; i < constructorLists.size(); i++) {
			unitTestStatement.add("\t" + constructorLists.get(i).getExecuteStatement());
		}
		
		unitTestStatement.add("\t" + "// create Instance for Method Argument");
		for(int i = 0; i < constructorArgumentLists.size(); i++) {
			unitTestStatement.add("\t" + constructorArgumentLists.get(i).getExecuteStatement());
		}
		
		unitTestStatement.add("\t" + "// execute Method for Instance of Method");
		for(int i = 0; i < methodLists.size(); i++) {
			if(methodLists.get(i).getHasAssignment()) {
				unitTestStatement.add("\t" + methodLists.get(i).getExecuteStatement());
			}
		}
		
		unitTestStatement.add("\t" + "// execute Method for Instance of Method Argument");
		for(int i = 0; i < argumentMethodLists.size(); i++) {
			if(argumentMethodLists.get(i).getHasAssignment()) {
				unitTestStatement.add("\t" + argumentMethodLists.get(i).getExecuteStatement());
			}
		}
		
		unitTestStatement.add("\t" + "// execute Target Method");
		unitTestStatement.add("\t" + method.getExecuteStatement());
		
		unitTestStatement.add("\t" + "// assertion");
		for(int i = 0; i < assertionLists.size(); i++) {
			unitTestStatement.add("\t" + assertionLists.get(i));
		}
		
		unitTestStatement.add("}");
	}
	
	public void setConstructorLists(ArrayList<Method> input) {
		constructorLists = input;
	}
	
	public void addConstructorArgumentLists(Method input) {
		constructorArgumentLists.add(input);
	}
	
	public void setMethodLists(ArrayList<Method> input) {
		methodLists = input;
	}
	
	public void addArgumentMethodLists(Method input) {
		argumentMethodLists.add(input);
	}
	
	public void setMethod(Method input) {
		method = input;
	}
	
	public void addAssertionLists(String input) {
		assertionLists.add(input);
	}
	
	public void addConstructorArrayLists(String input) {
		constructorArrayLists.add(input);
	}
	
	public void setOwner(String input) {
		owner = input;
	}
	
	public ArrayList<Method> getConstructorLists() {
		return constructorLists;
	}
	
	public ArrayList<Method> getConstructorArgumentLists(){
		return constructorArgumentLists;
	}
	
	public ArrayList<Method> getMethodLists(){
		return methodLists;
	}
	
	public ArrayList<Method> getArgumentMethodLists(){
		return argumentMethodLists;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public ArrayList<String> getAssertionLists(){
		return assertionLists;
	}
	
	public ArrayList<String> getConstructorArrayLists(){
		return constructorArrayLists;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public ArrayList<String> getUnitTestStatement(){
		return unitTestStatement;
	}
}
