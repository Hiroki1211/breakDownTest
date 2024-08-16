package executer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import analyzer.*;
import dataSet.*;
import tracer.Lexer;
import tracer.Trace;
import tracer.ValueOption;

public class Executer {
	
	private ArrayList<Trace> traceLists;
	private static String inputFileName = "src/main/resources/trace.json";
	private static String packageName = "breakDownTest";

	public Executer(ArrayList<Trace> t) {
		traceLists = t;
	}
	
	public static void main(String[] argv) {
		File inputFile = new File(inputFileName);
		Lexer lexer = new Lexer(inputFile);
		ArrayList<Trace> traceLists = lexer.getTraceLists();
		Analyzer analyzer = new Analyzer();
		Executer executer = new Executer(traceLists);
		ArrayList<String> analyzeTargetLists = executer.getAnalyzeFile();
		analyzer.run(analyzeTargetLists);
//		analyzer.displayVariableLists();
//		analyzer.displayMethodLists();
		ArrayList<AnalyzerMethod> analyzerMethodLists = analyzer.getMethodLists();
		ArrayList<AnalyzerVariable> analyzerVariableLists = analyzer.getVariableLists();
		executer.run(analyzerMethodLists, analyzerVariableLists);
	}
	
	public ArrayList<String> getAnalyzeFile(){
		ArrayList<String> result = new ArrayList<String>();
		result.add("src/main/resources/fuga/Executer.java");
		result.add("src/main/resources/fuga/Formula.java");
		result.add("src/main/resources/fuga/Lexer.java");
		result.add("src/main/resources/fuga/Main.java");
		result.add("src/main/resources/fuga/Parser.java");

		return result;
	}
	
	public void run(ArrayList<AnalyzerMethod> analyzerMethodLists, ArrayList<AnalyzerVariable> analyzerVariableLists) {
		ArrayList<UnitTest> unitTestLists = createUnitTestLists(analyzerMethodLists, analyzerVariableLists);
		ArrayList<UnitTestGroup> unitTestGroupLists = createUnitTestGroupLists(unitTestLists);
		createExternalFile(unitTestGroupLists);
	}
	
	private void createExternalFile(ArrayList<UnitTestGroup> unitTestGroupLists) {
		LocalDateTime nowDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formatNowDate = dtf.format(nowDate);
        
        // create directory
        String directoryPathName = "src/test/java/breakDownTest" + formatNowDate;
        File dir = new File(directoryPathName);
        dir.mkdir();
        
        String packageName = "breakDownTest" + formatNowDate;
		
        // create file
		for(int i = 0; i < unitTestGroupLists.size(); i++) {
			UnitTestGroup unitTestGroup = unitTestGroupLists.get(i);
			if(!unitTestGroup.getOwner().equals("java.lang.String") && !unitTestGroup.getOwner().equals("java.lang.Integer")) {
				String fileName = unitTestGroup.getClassName() + "_Test";
				File file = new File(directoryPathName + "/" + fileName + ".java");
				file.setExecutable(true);
				file.setReadable(true);
				file.setWritable(true);
	
				try {
					FileWriter fw = new FileWriter(file);
					fw.write("package " + packageName + ";\n");
					fw.write("\n");
					fw.write("import static org.junit.Assert.*;\n");
					fw.write("import org.junit.Test;\n");
//					fw.write("import " + unitTestGroup.getOwner() + ";");
					fw.write("\n");
					fw.write("public class " + fileName + " {\n");
					fw.write("\n");
					for(int j = 0; j < unitTestGroup.getUnitTestLists().size(); j++) {
						UnitTest unitTest = unitTestGroup.getUnitTestLists().get(j);
						ArrayList<String> unitTestStatementLists = unitTest.getUnitTestStatement();
						for(int k = 0; k < unitTestStatementLists.size(); k++) {
							fw.write("\t" + unitTestStatementLists.get(k) + "\n");
						}
						fw.write("\n");
					}
					
					fw.write("}");
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private ArrayList<UnitTestGroup> createUnitTestGroupLists(ArrayList<UnitTest> unitTestLists) {
		ArrayList<UnitTestGroup> unitTestGroupLists = new ArrayList<UnitTestGroup>();
		
		for(int i = 0; i < unitTestLists.size(); i++) {
			UnitTest unitTest = unitTestLists.get(i);
			
			if(unitTestGroupLists.size() == 0) {
				unitTest.addTestDeclarationUnitTestStatement("public void test0(){");
				
				UnitTestGroup unitTestGroup = new UnitTestGroup();
				unitTestGroup.addUnitTestLists(unitTest);
				unitTestGroup.setOwner(unitTest.getOwner());
				String[] split = unitTest.getOwner().split(Pattern.quote("."));
				String className = split[split.length - 1];
				unitTestGroup.setClassName(className);
				unitTestGroup.setpackageName(packageName);
				unitTestGroupLists.add(unitTestGroup);
			}else {
				boolean unitTestGroupRegisterFlag = false;
				
				for(int j = 0; j < unitTestGroupLists.size(); j++) {
					if(unitTest.getOwner().equals(unitTestGroupLists.get(j).getOwner())){
						unitTestGroupRegisterFlag = true;
						UnitTestGroup targetUnitTestGroup = unitTestGroupLists.get(j);
						
						unitTest.addTestDeclarationUnitTestStatement("public void test" + String.valueOf(targetUnitTestGroup.getUnitTestLists().size()) + "(){");
						
						targetUnitTestGroup.addUnitTestLists(unitTest);
						break;
					}
				}
				
				if(!unitTestGroupRegisterFlag) {
					unitTest.addTestDeclarationUnitTestStatement("public void test0(){");
					
					UnitTestGroup unitTestGroup = new UnitTestGroup();
					unitTestGroup.addUnitTestLists(unitTest);
					unitTestGroup.setOwner(unitTest.getOwner());
					String[] split = unitTest.getOwner().split(Pattern.quote("."));
					String className = split[split.length - 1];
					unitTestGroup.setClassName(className);
					unitTestGroup.setpackageName(packageName);
					unitTestGroupLists.add(unitTestGroup);
				}
			}
		}
		
		return unitTestGroupLists;
	}
	
	private ArrayList<UnitTest> createUnitTestLists(ArrayList<AnalyzerMethod> analyzerMethodLists, ArrayList<AnalyzerVariable> analyzerVariableLists) {
		// from dataSet ArrayLists
		ArrayList<Array> arrayLists = new ArrayList<Array>();
		ArrayList<Instance> instanceLists = new ArrayList<Instance>();
		ArrayList<UnitTest> unitTestLists = new ArrayList<UnitTest>();
		
		// for Instance
		boolean createInstanceFlag = false;
		
		// for method
		String methodName = "";
		String methodOwner = "";
		String methodCalledFrom = "";
		String methodType = "";
		ArrayList<ValueOption> methodValueOptionForId = new ArrayList<ValueOption>();
		ArrayList<ArrayList<ValueOption>> params = new ArrayList<ArrayList<ValueOption>>();	
		
		// for array
		ArrayList<String> arrayIdLists = new ArrayList<String>();
		ArrayList<String> arrayIndexLists = new ArrayList<String>();
		ArrayList<String> arrayTypeLists = new ArrayList<String>();
		
		// for assignment statement
		ArrayList<ValueOption> targetInstanceLists = new ArrayList<ValueOption>();
		
		int record = 1;
		
		for(int i = 0; i < traceLists.size(); i++) {
			Trace trace = traceLists.get(i);
			
			switch(trace.getEvent()) {
				case "CALL":
					methodName = trace.getAttr().getName();
					methodOwner = trace.getAttr().getOwner();
					methodCalledFrom = trace.getFilename();
					methodType = trace.getAttr().getMethodtype();
					methodValueOptionForId = trace.getValue().getValueOptionLists();
					
					break;
					
				case "CALL_PARAM":
					params.add(trace.getValue().getValueOptionLists());
					break;
					
				case "CALL_RETURN":
					// コンストラクタ以外
					if(!createInstanceFlag) {
						record = trace.getRecord();
						if(methodType.equals("instance")){
							for(int recordNum = 0; recordNum < record; recordNum++) {
								// createMethod
							 	Method method = new Method();
								method.setId(methodValueOptionForId.get(recordNum).getId());
								method.setName(methodName);
								method.setOwner(methodOwner);
								method.setOwnerValueOption(methodValueOptionForId.get(recordNum));
								for(int j = 0; j < params.size(); j++) {
									method.addParams(params.get(j).get(recordNum));
								}
								method.setReturnValueType(trace.getAttr().getType());
								method.setReturnValue(trace.getValue().getValueOptionLists().get(recordNum));
								
								// create Method executeStatement
								String executeStatement = "";
								if(!method.getReturnValueType().equals("void")) {
									executeStatement = method.getReturnValueType() + " result = ";
								}
								
								Instance executeMethodInstance = this.getInstanceFromId(method.getId(), instanceLists, method.getOwner());
								if(executeMethodInstance != null) {
									executeStatement += executeMethodInstance.getName() + "." + method.getName() + "(";
									if(method.getParams().size() == 0) {
										executeStatement += ");";
									}else {
										for(int executeParamNum = 0; executeParamNum < method.getParams().size(); executeParamNum++) {
											String param = "";
											if(!method.getParams().get(executeParamNum).getValue().equals("")) {
												param = method.getParams().get(executeParamNum).getValue();
											}else {
												Instance instance = this.getInstanceFromId(method.getParams().get(executeParamNum).getId(), instanceLists, method.getOwner());
												if(instance != null) {
													param = instance.getName();
												}
												
												Array array = this.getArrayFromId(method.getParams().get(executeParamNum).getId(), arrayLists);
												if(array != null) {
													param = instance.getName();
												}
											}
											
											if(executeParamNum == params.size() - 1) {
												executeStatement += param + ");";
											}else {
												executeStatement += param + ", ";
											}
										}
									}
								}
								
								method.setExecuteStatement(executeStatement);
								
								// createUnitTest
								UnitTest unitTest = new UnitTest();
								unitTest.setOwner(methodOwner);
								// 1. メソッドで使用するインスタンスのコンストラクタを宣言
								Instance methodInstance = this.getInstanceFromId(method.getId(), instanceLists, method.getOwner());
								if(methodInstance != null) {
									unitTest.setConstructorLists(methodInstance.getConstructorLists());
								}
								// 2. メソッドの引数で使用するインスタンスの宣言
								for(int paramNum = 0; paramNum < method.getParams().size(); paramNum++) {
									ValueOption valueOption = method.getParams().get(paramNum);
									if(!valueOption.getId().equals("")) {
										Instance argumentInstance = this.getInstanceFromId(valueOption.getId(), instanceLists, valueOption.getType());
										if(argumentInstance != null) {
											ArrayList<String> constructorArgumentConstructorLists = argumentInstance.getConstructorLists();
											for(int j = 0; j < constructorArgumentConstructorLists.size(); j++) {
												unitTest.addConstructorArgumentLists(constructorArgumentConstructorLists.get(j));
											}
										}
									}
								}
								// 3. メソッドの引数で使用する配列の宣言
								for(int paramNum = 0; paramNum < method.getParams().size(); paramNum++) {
									ValueOption valueOption = method.getParams().get(paramNum);
									if(!valueOption.getId().equals("")) {
										Array argumentArray = this.getArrayFromId(valueOption.getId(), arrayLists);
										if(argumentArray != null) {
											unitTest.addConstructorArgumentLists(argumentArray.getDeclareStatement());
										}
									}
								}
								// 4. メソッドで使用するインスタンスのメソッドを実行
								if(methodInstance != null) {
									ArrayList<Method> methodInstanceMethodLists = new ArrayList<Method>(methodInstance.getMethodLists());
									unitTest.setMethodLists(methodInstanceMethodLists);
								}
								// 5. メソッドの引数で使用するインスタンスのメソッドを実行
								for(int paramNum = 0; paramNum < method.getParams().size(); paramNum++) {
									ValueOption valueOption = method.getParams().get(paramNum);
									if(!valueOption.getId().equals("")) {
										Instance argumentInstance = this.getInstanceFromId(valueOption.getId(), instanceLists, valueOption.getType());
										if(argumentInstance != null) {
											ArrayList<Method> argumentInstanceMethodLists = argumentInstance.getMethodLists();
											for(int argumentInstanceMethodNum = 0; argumentInstanceMethodNum < argumentInstanceMethodLists.size(); argumentInstanceMethodNum++) {
												Method argumentMethod = argumentInstanceMethodLists.get(argumentInstanceMethodNum);
												unitTest.addArgumentMethodLists(argumentMethod);
											}
										}
									}
								}
								// 6. メソッドを実行
								unitTest.setMethod(method);
								// 7. アサーションを追加
								String assertionStatement;
								if(!method.getReturnValue().getValue().equals("")) {
									assertionStatement = this.createAssertion(method.getReturnValue().getValue());
									unitTest.addAssertionLists(assertionStatement);
								}

								// 8. unitTest生成
								unitTest.createUnitTest();
								// 9. unitTestListsに追加
								unitTestLists.add(unitTest);
								
								// add Method to Instance
								if(!method.getOwner().equals(methodCalledFrom)) {
									if(methodInstance != null) {
										methodInstance.addMethodLists(method);
									}
								}
								
								// create Instance 
								if(trace.getValuetype().equals("java.lang.Object")) {
									Instance instance = new Instance();
									instance.setId(trace.getValue().getValueOptionLists().get(recordNum).getId());
									instance.setOwner(method.getOwner(), instanceLists.size());
									instanceLists.add(instance);
									if(methodInstance != null) {
										instance.addConstructorParamInstanceLists(methodInstance);
									}
									instance.setConstructorParams(method.getParams());
									String methodExecuteStatement = method.getExecuteStatement();
									methodExecuteStatement = methodExecuteStatement.replace("result", instance.getName());
									instance.createConstructorStatement(instanceLists, arrayLists, methodExecuteStatement);
								}
							}
						}else {

						}
						
						// 初期化
						params = new ArrayList<ArrayList<ValueOption>>();
					}
					
					break;
					
				case "NEW_OBJECT":
					createInstanceFlag = true;
					
					break;
					
				case "NEW_OBJECT_CREATED":
					createInstanceFlag = false;
					
					// create Instance
					record = trace.getRecord();
					Instance instance = new Instance();
					for(int recordNum = 0; recordNum < record; recordNum++) {
						instance = new Instance();
						ValueOption instanceValueOption = trace.getValue().getValueOptionLists().get(recordNum);
						instance.setOwner(instanceValueOption.getType(), instanceLists.size());
						instance.setId(instanceValueOption.getId());
						for(int constructorParamNum = 0; constructorParamNum < params.size(); constructorParamNum++) {
							instance.addConstructorParams(params.get(constructorParamNum).get(recordNum));
						}
						
						instance.createConstructorStatement(instanceLists, arrayLists);
						instanceLists.add(instance);
					}
					
					// create UnitTest
					for(int recordNum = 0; recordNum < record; recordNum++) {
						// 1. create Method
						Method instanceMethod = new Method();
						instanceMethod.setName("<init>");
						for(int j = 0; j < params.size(); j++) {
							instanceMethod.addParams(params.get(j).get(recordNum));
						}
						instanceMethod.setOwner(trace.getValue().getValueOptionLists().get(recordNum).getType());
						String tmp = methodOwner;
						String[] tmpSplit = tmp.split(Pattern.quote("."));
						instanceMethod.setReturnValueType(tmpSplit[tmpSplit.length - 1]);
						instanceMethod.setReturnValue(trace.getValue().getValueOptionLists().get(recordNum));
						instanceMethod.setId(trace.getValue().getValueOptionLists().get(recordNum).getId());
						instanceMethod.setCalledFrom(methodCalledFrom);
						instanceMethod.setOwnerValueOption(trace.getValue().getValueOptionLists().get(recordNum));
						
						Instance tmpInstance = this.getInstanceFromId(instanceMethod.getId(), instanceLists, instanceMethod.getOwner());
						
						String executeStatement = instanceMethod.getReturnValueType() + " " + tmpInstance.getName() +" = new " + instanceMethod.getReturnValueType() + "(";
						ArrayList<ValueOption> executeConstructorParams = instanceMethod.getParams();
						for(int exConParamNum = 0; exConParamNum < executeConstructorParams.size(); exConParamNum++) {
							String addParam = "";
							if(executeConstructorParams.get(exConParamNum).getValue().equals("")) {
								String paramInstanceId = executeConstructorParams.get(exConParamNum).getId();
								Instance paramInstance = this.getInstanceFromId(paramInstanceId, instanceLists, executeConstructorParams.get(exConParamNum).getType());
								addParam = paramInstance.getName();
							}else {
								addParam = executeConstructorParams.get(exConParamNum).getValue();
							}
							
							if(exConParamNum == executeConstructorParams.size() - 1) {
								executeStatement += addParam;
							}else {
								executeStatement += addParam + ", ";
							}
						}
						executeStatement += ");";
						instanceMethod.setExecuteStatement(executeStatement);
						
						// 2. create UnitTest
						UnitTest instanceUnitTest = new UnitTest();
						instanceUnitTest.setOwner(methodOwner);
						for(int exConParamNum = 0; exConParamNum < executeConstructorParams.size(); exConParamNum++) {
							// 2a. constructor の引数のオブジェクトのコンストラクタ実行
							ValueOption executeConstructorParam = executeConstructorParams.get(exConParamNum);
							if(executeConstructorParam.getValue().equals("")) {
								Instance executeConstructorParamInstance = this.getInstanceFromId(executeConstructorParam.getId(), instanceLists, executeConstructorParam.getType());
								if(executeConstructorParamInstance != null) {
									for(int j = 0; j < executeConstructorParamInstance.getConstructorLists().size(); j++) {
										instanceUnitTest.addConstructorArgumentLists(executeConstructorParamInstance.getConstructorLists().get(j));
										// 2b. constructor の引数のオブジェクトのインスタンスのメソッドを実行
										instanceUnitTest.addArgumentMethodLists(executeConstructorParamInstance.getMethodLists().get(j));
									}
								}
							}
							
						}
						
						// 2c. methodの実行
						instanceUnitTest.setMethod(instanceMethod);
						
						instanceUnitTest.createUnitTest();
						unitTestLists.add(instanceUnitTest);
						
					}
					
					
					// 初期化
					params = new ArrayList<ArrayList<ValueOption>>();
					
					break;
				
				case "NEW_ARRAY_RESULT":
					record = trace.getRecord();
					for(int recordNum = 0; recordNum < record; recordNum++) {
						Array array = new Array();
						ValueOption arrayValueOption = trace.getValue().getValueOptionLists().get(recordNum);
						array.setId(arrayValueOption.getId());
						array.setType(arrayValueOption.getType());
						
						arrayLists.add(array);
					}
					
					break;
					
				case "ARRAY_STORE":
					for(int recordNum = 0; recordNum < trace.getValue().getValueOptionLists().size(); recordNum++) {
						ValueOption arrayStoreValueOption = trace.getValue().getValueOptionLists().get(recordNum);
						arrayIdLists.add(arrayStoreValueOption.getId());
						arrayTypeLists.add(arrayStoreValueOption.getType());
					}
					
					break;
					
				case "ARRAY_STORE_INDEX":
					for(int recordNum = 0; recordNum < trace.getValue().getValueOptionLists().size(); recordNum++) {
						ValueOption arrayStoreValueOption = trace.getValue().getValueOptionLists().get(recordNum);
						arrayIndexLists.add(arrayStoreValueOption.getValue());
					}
					
					break;
					
				case "ARRAY_STORE_VALUE":
					record = trace.getRecord();
					for(int recordNum = 0; recordNum < record; recordNum++) {
						Array storeArray = this.getArrayFromId(arrayIdLists.get(recordNum), arrayLists);
						storeArray.setId(arrayIdLists.get(recordNum));
						storeArray.setType(arrayTypeLists.get(recordNum));
						storeArray.addValue(trace.getValue().getValueOptionLists().get(recordNum).getValue(), Integer.valueOf(arrayIndexLists.get(recordNum)));
					}
					
					// 初期化
					arrayIdLists = new ArrayList<String>();
					arrayIndexLists = new ArrayList<String>();
					arrayTypeLists = new ArrayList<String>();
					
					break;
					
				case "PUT_INSTANCE_FIELD":
					// インスタンスid・メソッドの名前・変数名
					targetInstanceLists = new ArrayList<ValueOption>(trace.getValue().getValueOptionLists());
					
					break;
					
				case "PUT_INSTANCE_FIELD_VALUE":
					// メソッドの名前・変数名・変数の値
					String targetMethodName = trace.getMname();
					String variableName = trace.getAttr().getName();
					ArrayList<ValueOption> targetVariableValueLists = trace.getValue().getValueOptionLists();
					if(!targetVariableValueLists.get(0).getValue().equals("")) {
						for(int targetNum = 0; targetNum < targetVariableValueLists.size(); targetNum++) {
							String targetVariableValue = targetVariableValueLists.get(targetNum).getValue();
							String targetOwner = trace.getAttr().getOwner();
							String[] targetTmpSplit = targetOwner.split(Pattern.quote("."));
							targetOwner = targetTmpSplit[targetTmpSplit.length - 1];
							AnalyzerVariable analyzerVariable = null;
							
							// 1. 変数の特定
							for(int targetAnalyzerVarNum = 0; targetAnalyzerVarNum < analyzerVariableLists.size(); targetAnalyzerVarNum++) {
								String targetAnalyzerVariable = analyzerVariableLists.get(targetAnalyzerVarNum).getName();
								String targetAnalyzerVariableOwner = analyzerVariableLists.get(targetAnalyzerVarNum).getOwnerClass().getName();
								if(targetAnalyzerVariableOwner.equals(targetOwner) && targetAnalyzerVariable.equals(variableName)) {
									analyzerVariable = analyzerVariableLists.get(targetAnalyzerVarNum);
									break;
								}
							}
							
							// 3. UnitTestListsから対象のUnitTestを特定・assertion文の追加
							ValueOption targetInstance = targetInstanceLists.get(targetNum);
							for(int targetUnitTestNum = 0; targetUnitTestNum < unitTestLists.size(); targetUnitTestNum++) {
								UnitTest targetUnitTest = unitTestLists.get(targetUnitTestNum);
								Method targetUnitTestMethod = targetUnitTest.getMethod();

								if(targetUnitTestMethod.getId().equals(targetInstance.getId()) && targetUnitTestMethod.getName().equals(targetMethodName)) {
									// 2. assertion文の作成
									System.out.println(targetUnitTestMethod.getName());
									System.out.println(targetUnitTestMethod.getOwner());
									System.out.println(targetUnitTestMethod.getId());
									System.out.println(this.getInstanceFromId(targetUnitTest.getMethod().getId(), instanceLists, targetUnitTest.getOwner()).getName());
									System.out.println();
									
									
									String targetInstanceName = this.getInstanceFromId(targetUnitTest.getMethod().getId(), instanceLists, targetUnitTest.getOwner()).getName();
									String assertionStatement = "assertEquals(" + targetVariableValue + ", " + targetInstanceName + "." + analyzerVariable.getGetterMethod().getName() + ");";
//									System.out.println(targetInstance.getId());
//									System.out.println(targetUnitTest.getMethod().getId());
//									System.out.println(targetUnitTest.getMethod().getName());
//									System.out.println();
									
									targetUnitTest.addAssertionLists(assertionStatement);
									targetUnitTest.createUnitTest();
									break;
								}
							}
						}
					}
					
					break;
			}
		}
		
		for(int i = 0; i < instanceLists.size(); i++) {
			System.out.println(instanceLists.get(i).getId());
			System.out.println(instanceLists.get(i).getName());
			System.out.println(instanceLists.get(i).getOwner());
			System.out.println();
		}
		
		return unitTestLists;
	}
	
	private Instance getInstanceFromId(String id, ArrayList<Instance> instanceLists, String owner) {
		Instance instance = null;
		for(int i = 0; i < instanceLists.size(); i++) {
			Instance targetInstance = instanceLists.get(i);
			if(targetInstance.getId().equals(id) && targetInstance.getOwner().equals(owner)) {
				instance = targetInstance;
				break;
			}
		}
		
		return instance;
	}
	
	public Array getArrayFromId(String id, ArrayList<Array> arrayLists) {
		Array array = null;		
		for(int i = 0; i < arrayLists.size(); i++) {
			Array targetArray = arrayLists.get(i);
			if(targetArray.getId().equals(id)) {
				array = targetArray;
				break;
			}
		}
				
		return array;
	}
	
	private String createAssertion(String returnValue){
		String assertionStatement = "assertEquals(result, " + returnValue + ");";
		return assertionStatement;
	}
	
}
