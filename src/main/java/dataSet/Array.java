package dataSet;

import java.util.ArrayList;

public class Array {

	private String id;
	private String name;
	private String type;
	private ArrayList<String> value = new ArrayList<String>();
	
	public String getDeclareStatement() {
		String declareStatement = type + " " + name + " = {";
		for(int i = 0; i < value.size(); i++) {
			if(i == value.size() - 1) {
				declareStatement += value.get(i) + "};";
			}else {
				declareStatement += value.get(i) + ", ";
			}
		}
		return declareStatement;
	}
	
	public Array clone() {
		Array array = new Array();
		array.setId(this.getId());
		array.setName(this.getName());
		array.setType(this.getType());
		array.setValue(this.getValue());
		return array;
	}
	
	public void setId(String input) {
		id = input;
	}

	public void setName(String input) {
		name = input;
	}
	
	public void setType(String input) {
		type = input;
	}
	
	public void addValue(String input, int index) {
		if(value.size() == index) {
			value.add(input);
		}else {
			value.add(index, input);
		}
	}
	
	public void setValue(ArrayList<String> input) {
		value = input;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public ArrayList<String> getValue(){
		return value;
	}
}
