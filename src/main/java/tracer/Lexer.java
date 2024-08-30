package tracer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lexer {

	private ArrayList<Trace> traceLists = new ArrayList<Trace>();
	private static String inputFileName = "src/main/resources/trace.json";
	private String junitFileName = "junit-4.13.2.jar";
	
	public static void main(String[] argv) {
		File inputFile = new File(inputFileName);
		Lexer lexer = new Lexer(inputFile);
		ArrayList<Trace> traceLists = lexer.getTraceLists();
		for(int i = 0; i < traceLists.size(); i++) {
			traceLists.get(i).displayContent();
		}
	}
	
	public ArrayList<Trace> getTraceLists(){
		return traceLists;
	}
	
	public Lexer(File inputFile) {
		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			String readLine = br.readLine();
			
			while((readLine = br.readLine()) != null) {
				String[] sp = readLine.split("filename");
				
				if(sp.length > 0) {
					String fileName = sp[0];
					fileName = trimDoubleQout(fileName);
					fileName = trimRoundBracket(fileName);
					fileName = trimConma(fileName);
					sp = fileName.split("[:/]");
					
					if(! sp[sp.length - 1].equals(junitFileName)) {
						
						Trace trace = new Trace();
						Attr attr = new Attr();
						Value value = new Value();
						readLine = trimDoubleQout(readLine);
						readLine = readLine.substring(1, readLine.length()-1);
						readLine = trimRoundBracket(readLine);
						String[] split = readLine.split(",");
						
						int splitIndex = 0;
						int mode = 0; // 0. normal, 1. attr, 2. value
						
						while(splitIndex < split.length) {
							String[] splitContent = splitContent(split[splitIndex]);
							if(splitContent.length > 1) {
								splitContent[1] = trimRoundBracket(splitContent[1]);
								
								if(mode == 0) {
									mode = traceMode0(splitContent, trace, attr, value);
								}else if(mode == 1) {
									mode = traceMode1(splitContent, trace, attr);
								}else if(mode == 2) {
									mode = traceMode2(splitContent, trace, value);
								}else if(mode == 3) {
									mode = traceMode3(splitContent, trace);
								}
							}else if(mode == 2) {
								splitContent[0] = trimBracket(splitContent[0]);
								value.addValue(splitContent[0]);
							}else if(mode == 3) {
								splitContent[0] = trimBracket(splitContent[0]);
								trace.addSeqnum(splitContent[0]);
							}
							splitIndex += 1;
						}
					
						trace.setAttr(attr);
						trace.setValue(value);
						if(trace.getLoadedFrom() != null) {
							traceLists.add(trace);
						}
					}
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int traceMode0(String[] splitContent, Trace trace, Attr attr, Value value) {
		int mode = 0;
		switch(splitContent[0]) {
			case "loadedFrom":
				trace.setLoadedFrom(splitContent[1]);
				break;
			case "filename":
				trace.setFilename(splitContent[1]);
				break;
			case "cname":
				trace.setCname(splitContent[1]);
				break;
			case "mname":
				trace.setMname(splitContent[1]);
				break;
			case "mdesc":
				trace.setMdesc(splitContent[1]);
				break;
			case "mhash":
				trace.setMhash(splitContent[1]);
				break;
			case "line":
				trace.setLine(Integer.parseInt(splitContent[1]));
				break;
			case "inst":
				trace.setInst(Integer.parseInt(splitContent[1]));
				break;
			case "event":
				trace.setEvent(splitContent[1]);
				break;
			case "attr":
				mode = 1;
				splitContent = splitContent(splitContent[1]);
				if(splitContent.length > 1) {
					splitContent[1] = trimRoundBracket(splitContent[1]);
					
					mode = traceMode1(splitContent, trace, attr);
				}
				
				break;
			case "freq":
				trace.setFreq(Integer.parseInt(splitContent[1]));
				break;
			case "record":
				trace.setRecord(Integer.parseInt(splitContent[1]));
				break;
			case "value" :
				mode = 2;
				String trimBracket = trimBracket(splitContent[1]);
				splitContent = splitContent(trimBracket);
				if(splitContent.length > 1) {
					splitContent[1] = trimRoundBracket(splitContent[1]);
					
					mode = traceMode2(splitContent, trace, value);
				}else {
					value.addValue(trimBracket);
				}
				break;
			case "seqnum":
				splitContent[1] = trimBracket(splitContent[1]);
				trace.addSeqnum(splitContent[1]);
			case "thread":
				trace.setThread(splitContent[1]);
			default:
				break;
		}
		
		return mode;
	}
	
	private int traceMode1(String[] splitContent, Trace trace, Attr attr) {
		int mode = 1;
		
		switch(splitContent[0]) {
			// mode = 0 に変更
			case "valuetype":
				mode = 0;
				trace.setValuetype(splitContent[1]);
				break;
				
			case "owner":
				attr.setOwner(splitContent[1]);
				break;
			case "name":
				attr.setName(splitContent[1]);
				break;
			case "desc":
				attr.setDesc(splitContent[1]);
				break;
			case "type":
				attr.setType(splitContent[1]);
				break;
			case "methodtype":
				attr.setMethodtype(splitContent[1]);
				break;
			case "index":
				attr.setIndex(Integer.parseInt(splitContent[1]));
				break;
			case "varindex":
				attr.setVarindex(Integer.parseInt(splitContent[1]));
				break;
			case "opcode":
				attr.setOpcode(splitContent[1]);
				break;
			case "jumpto":
				
				break;
			case "amount":
				attr.setAmount(Integer.parseInt(splitContent[1]));
				break;
			case "location":
				
				break;
			case "created":
				attr.setCreated(Integer.parseInt(splitContent[1]));
				break;
			case "blocktype":
				
				break;
			case "start":
				
				break;
			case "end":
				
				break;
			default:
				break;
		}
		
		return mode;
	}
	
	private int traceMode2(String[] splitContent, Trace trace, Value value) {
		int mode = 2;
		
		switch(splitContent[0]) {
			case "id":
				value.addID(splitContent[1]);
				break;
			case "type":
				String tmp = this.trimBracket(splitContent[1]);
				value.addType(tmp);
				break;
			case "str":
				splitContent[1] = trimBracket(splitContent[1]);
				splitContent[1] = '"' + splitContent[1] + '"';
				value.addStr(splitContent[1]);
				break;
			case "seqnum":
				mode = 3;
				splitContent[1] = trimBracket(splitContent[1]);
				trace.addSeqnum(splitContent[1]);
				break;
			case "thread":
				mode = 0;
				trace.setThread(splitContent[1]);
				break;
		}
		
		return mode;
	}
	
	private int traceMode3(String[] splitContent, Trace trace) {
		int mode = 3;
		
		switch(splitContent[0]) {
		case "thread":
			mode = 0;
			trace.setThread(splitContent[1]);
			break;
		}
		
		return mode;
	}
	
	private String trimDoubleQout(String input) {
		input = input.replace('"', '?');
		input = input.replace("?", "");
		return input;
	}
	
	private String[] splitContent(String input) {
		String[] split = input.split(":", 2);
		return split;
	}	
	
	private String trimBracket(String input) {
		input = input.replace('[', '?');
		input = input.replace(']', '?');
		input = input.replace("?", "");
		return input;
	}
	
	private String trimRoundBracket(String input) {
		input = input.replace('{', '?');
		input = input.replace('}', '?');
		input = input.replace("?", "");
		return input;
	}
	
	public String trimConma(String input) {
		input = input.replace(',', '?');
		input = input.replace("?", "");
		return input;
	}
	
//    private static boolean isNumeric(String str){
//    	if(str.substring(0, 1).equals("-")) {
//    		str = str.substring(1, str.length());
//    	}
//        return str != null && str.matches("[0-9.]+");
//    }
}
