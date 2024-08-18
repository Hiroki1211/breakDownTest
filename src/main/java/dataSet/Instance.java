package dataSet;

import java.util.ArrayList;
import java.util.regex.Pattern;

import tracer.ValueOption;

public class Instance implements Cloneable{

	private ArrayList<String> constructorLists = new ArrayList<String>();
	private ArrayList<ValueOption> constructorParams = new ArrayList<ValueOption>();
	private ArrayList<Instance> constructorParamInstanceLists = new ArrayList<Instance>();
	private ArrayList<Array> constructorParamArrayLists = new ArrayList<Array>();
	private ArrayList<Method> methodLists = new ArrayList<Method>();
	private String owner;
	private String id;
	private String name;
	private String className;
	
	public void createConstructorStatement(ArrayList<Instance> instanceLists, ArrayList<Array> arrayLists, String constructor) {
		// コンストラクタのパラメータ処理
		if(constructorParams.size() == 0) {
			constructorLists.add(constructor);
		}else {
			for(int i = 0; i < constructorParams.size(); i++) {
				ValueOption targetValueOption = constructorParams.get(i);
				if(!targetValueOption.getValue().equals("")) {
					
				}else {
					// インスタンスの場合
					Instance targetInstance = null;
					for(int instanceListNum = 0; instanceListNum < instanceLists.size(); instanceListNum++) {
						if(instanceLists.get(instanceListNum).getId().equals(targetValueOption.getId())) {
							targetInstance = instanceLists.get(instanceListNum).clone();
							constructorParamInstanceLists.add(targetInstance);
							break;
						}
					}
					
					// 配列の場合
					Array targetArray = null;
					for(int arrayListNum = 0; arrayListNum < arrayLists.size(); arrayListNum++) {
						if(arrayLists.get(arrayListNum).getId().equals(targetValueOption.getId())) {
							targetArray = arrayLists.get(arrayListNum).clone();
							constructorParamArrayLists.add(targetArray);
							break;
						}
					}
				}
			}
			
			// for Instance
			for(int i = 0; i < constructorParamInstanceLists.size(); i++) {
				Instance targetParamInstance = constructorParamInstanceLists.get(i);
				ArrayList<String> targetParamInstanceConstructorLists = targetParamInstance.getConstructorLists();
				for(int j = 0; j < targetParamInstanceConstructorLists.size(); j++) {
					constructorLists.add(targetParamInstanceConstructorLists.get(j));
				}
			}
			
			// for Array
			for(int i = 0; i < constructorParamArrayLists.size(); i++) {
				constructorLists.add(constructorParamArrayLists.get(i).getDeclareStatement());
			}
			
			constructorLists.add(constructor);
		}
	}
	
	public void createConstructorStatement(ArrayList<Instance> instanceLists, ArrayList<Array> arrayLists) {
		String[] split = owner.split(Pattern.quote("."));
		className = split[split.length - 1];
		
		String constructor = className + " " + name + " = new " + className + "(";
		
		// コンストラクタのパラメータ処理
		if(constructorParams.size() == 0) {
			constructor += ");";
			constructorLists.add(constructor);
		}else {
			for(int i = 0; i < constructorParams.size(); i++) {
				String param = null;
				ValueOption targetValueOption = constructorParams.get(i);
				if(!targetValueOption.getValue().equals("")) {
					param = targetValueOption.getValue();
				}else {
					// インスタンスの場合
					Instance targetInstance = null;
					for(int instanceListNum = 0; instanceListNum < instanceLists.size(); instanceListNum++) {
						if(instanceLists.get(instanceListNum).getId().equals(targetValueOption.getId())) {
							targetInstance = instanceLists.get(instanceListNum).clone();
							param = targetInstance.getName();
							constructorParamInstanceLists.add(targetInstance);
							break;
						}
					}
					
					// 配列の場合
					Array targetArray = null;
					for(int arrayListNum = 0; arrayListNum < arrayLists.size(); arrayListNum++) {
						if(arrayLists.get(arrayListNum).getId().equals(targetValueOption.getId())) {
							targetArray = arrayLists.get(arrayListNum).clone();
							param = targetArray.getName();
							constructorParamArrayLists.add(targetArray);
							break;
						}
					}
				}

				if(i == constructorParams.size() - 1) {
					constructor += param + ");";
				}else {
					constructor += param + ", ";
				}
			}
			
			// for Instance
			for(int i = 0; i < constructorParamInstanceLists.size(); i++) {
				Instance targetParamInstance = constructorParamInstanceLists.get(i);
				ArrayList<String> targetParamInstanceConstructorLists = targetParamInstance.getConstructorLists();
				for(int j = 0; j < targetParamInstanceConstructorLists.size(); j++) {
					constructorLists.add(targetParamInstanceConstructorLists.get(j));
				}
			}
			
			// for Array
			for(int i = 0; i < constructorParamArrayLists.size(); i++) {
				constructorLists.add(constructorParamArrayLists.get(i).getDeclareStatement());
			}
			
			constructorLists.add(constructor);
		}
	}
	
	public Instance clone() {
		Instance instance = new Instance();
		instance.setConstructorLists(new ArrayList<String>(this.getConstructorLists()));
		instance.setConstructorParams(new ArrayList<ValueOption>(this.getConstructorParams()));
		instance.setConstructorParamInstanceLists(new ArrayList<Instance>(this.getConstructorParamInstanceLists()));
		instance.setConstructorParamArrayLists(new ArrayList<Array>(this.getConstructorParamArrayLists()));
		instance.setMethodLists(new ArrayList<Method>(this.getMethodLists()));
		instance.setOwner(this.getOwner());
		instance.setId(this.getId());
		instance.setName(this.getName());
		instance.setClassName(this.getClassName());
		
		return instance;
	}
	
	public void setConstructorLists(ArrayList<String> input) {
		constructorLists = input;
	}
	
	public void addConstructorParams(ValueOption input) {
		constructorParams.add(input);
	}
	
	public void setConstructorParams(ArrayList<ValueOption> input) {
		constructorParams = input;
	}
	
	public void addConstructorParamInstanceLists(Instance input) {
		constructorParamInstanceLists.add(input);
	}
	
	public void setConstructorParamInstanceLists(ArrayList<Instance> input) {
		constructorParamInstanceLists = input;
	}
	
	public void setConstructorParamArrayLists(ArrayList<Array> input) {
		constructorParamArrayLists = input;
	}
	
	public Method addMethodLists(Method input) {
		String executeStatement = input.getExecuteStatement();
		String swapName = this.name + "Method" + methodLists.size();
		executeStatement = executeStatement.replace("result", swapName);
		Method method = input.clone();
		method.setExecuteStatement(executeStatement);
		methodLists.add(method);
		
		return method;
	}
	
	public void setMethodLists(ArrayList<Method> input) {
		methodLists = input;
	}
	
	private void setOwner(String string) {
		owner = string;
	}
	
	public void setOwner(String input, int num) {
		owner = input;
		String[] splitOwner = owner.split(Pattern.quote("."));
		className = splitOwner[splitOwner.length - 1];
		name = className.substring(0, 1).toLowerCase() + className.substring(1, className.length()) + String.valueOf(num);
	}
	
	public void setId(String input) {
		id = input;
	}
	
	private void setName(String input) {
		name = input;
	}
	
	public void setClassName(String input) {
		className = input;
	}
	
	public ArrayList<String> getConstructorLists() {
		return constructorLists;
	}
	
	public ArrayList<ValueOption> getConstructorParams(){
		return constructorParams;
	}
	
	public ArrayList<Instance> getConstructorParamInstanceLists(){
		return constructorParamInstanceLists;
	}
	
	public ArrayList<Array> getConstructorParamArrayLists(){
		return constructorParamArrayLists;
	}
	
	public ArrayList<Method> getMethodLists(){
		return methodLists;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getClassName() {
		return className;
	}
}
