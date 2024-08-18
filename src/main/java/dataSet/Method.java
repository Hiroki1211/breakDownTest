package dataSet;

import java.util.ArrayList;
import java.util.regex.Pattern;

import tracer.ValueOption;

public class Method {

	private String name;
	private ArrayList<ValueOption> params = new ArrayList<ValueOption>();
	private String executeStatement;
	private String owner;
	private String returnValueType;
	private ValueOption returnValue;
	private String id;
	private String calledFrom;
	private ValueOption ownerValueOption;
	private boolean hasAssignment = false;
	
	public Method clone() {
		Method method = new Method();
		method.setName(name);
		method.setParams(params);
		method.setExecuteStatement(executeStatement);
		method.setOwner(owner);
		method.setReturnValueType(returnValueType);
		method.setReturnValue(returnValue);
		method.setId(id);
		method.setCalledFrom(calledFrom);
		method.setOwnerValueOption(ownerValueOption);
		method.setHasAssignment(hasAssignment);
		return method;
	}
	
	public void setName(String input) {
		name = input;
	}
	
	private void setParams(ArrayList<ValueOption> input) {
		params = new ArrayList<ValueOption>(input);
	}
	
	public void addParams(ValueOption input) {
		params.add(input);
	}
	
	public void setExecuteStatement(String input) {
		executeStatement = input;
	}
	
	public void setOwner(String input) {
		owner = input;
	}
	
	public void setReturnValueType(String input) {
		String[] splitOwner = input.split(Pattern.quote("."));
		returnValueType = splitOwner[splitOwner.length - 1];
	}
	
	public void setReturnValue(ValueOption input) {
		returnValue = input;
	}
	
	public void setId(String input) {
		id = input;
	}
	
	public void setCalledFrom(String input) {
		calledFrom = input;
	}
	
	public void setOwnerValueOption(ValueOption input) {
		ownerValueOption = input;
	}
	
	public void setHasAssignment(boolean input) {
		hasAssignment = input;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<ValueOption> getParams(){
		return params;
	}
	
	public String getExecuteStatement() {
		return executeStatement;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getReturnValueType() {
		return returnValueType;
	}
	
	public ValueOption getReturnValue() {
		return returnValue;
	}
	
	public String getId() {
		return id;
	}
	
	public String getCalledFrom() {
		return calledFrom;
	}
	
	public ValueOption getOwnerValueOption() {
		return ownerValueOption;
	}
	
	public boolean getHasAssignment() {
		return hasAssignment;
	}
}
