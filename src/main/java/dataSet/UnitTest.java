package dataSet;

import java.util.ArrayList;
import java.util.Collections;

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
		
		ArrayList<Method> forCreateMethodLists = new ArrayList<Method>();
		
		for(int i = 0; i < constructorLists.size(); i++) {
			forCreateMethodLists.add(constructorLists.get(i));
		}
		
		for(int i = 0; i < constructorArgumentLists.size(); i++) {
			forCreateMethodLists.add(constructorArgumentLists.get(i));
		}
		
		for(int i = 0; i < methodLists.size(); i++) {
			forCreateMethodLists.add(methodLists.get(i));
		}
		
		for(int i = 0; i < argumentMethodLists.size(); i++) {
			forCreateMethodLists.add(argumentMethodLists.get(i));
		}
		
		for(int i = 0; i < forCreateMethodLists.size(); i++) {
			int minSeqnum = forCreateMethodLists.get(i).getSeqNum();
			int minSeqnumMethodIndex = i;
			
			for(int j = i; j < forCreateMethodLists.size(); j++) {
				if(minSeqnum > forCreateMethodLists.get(j).getSeqNum()) {
					minSeqnum = forCreateMethodLists.get(j).getSeqNum();
					minSeqnumMethodIndex = j;
				}
			}
			
			if(minSeqnumMethodIndex != i) {
				Collections.swap(forCreateMethodLists, i, minSeqnumMethodIndex);
			}
		}
		
		for(int i = 0; i < forCreateMethodLists.size(); i++) {
			unitTestStatement.add("\t" + forCreateMethodLists.get(i).getExecuteStatement());
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
