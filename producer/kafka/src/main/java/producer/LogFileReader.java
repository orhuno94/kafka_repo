package producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFileReader{
	private long lastmodified;
	private String foldername;
	private HashMap<String,Long> filelineMap;
	Pattern pattern;
	Matcher matcher;
	
	public LogFileReader(String LogFolder) {
		this.lastmodified=0;
		this.filelineMap=new HashMap<String,Long>();
		this.foldername=LogFolder;
		this.pattern=Pattern.compile("\\d{4}");
	}

    public ArrayList<String> readFile(String fileName) {
    	ArrayList<String> lines=new ArrayList<String>();
    	long linenumber = getLineNumber(fileName);
    	String line="";
    	 try{   		 
             BufferedReader read = new BufferedReader(new FileReader(fileName));
             //Go to new lines
             for(int i =0; i<linenumber; i++)
                line = read.readLine();
             line=read.readLine();
             while (line!=null) {
                 //ignore multiline logs
                 matcher=pattern.matcher(line.substring(0,4));
                 if(matcher.matches()) {
                     lines.add(LogParser.parse(line));
                     line = read.readLine();
                 }
             }
             read.close();
         }
         catch (IOException iox){
             System.err.println(fileName+" cannot read file");
         }
    	 this.getFilelineMap().put(fileName,this.getFilelineMap().get(fileName)+lines.size());
    	return lines;
    }

    //Get count of line numbers if file exists before
    private Long getLineNumber(String fileName){
	    long lineCount=0;
	    try{
            lineCount=this.filelineMap.get(fileName);
        }
	    catch (Exception e){
	        this.filelineMap.put(fileName,0L);
        }
	    finally {
            System.out.println(this.filelineMap.get(fileName));
            return this.filelineMap.get(fileName);
        }

    }

    public long getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(long lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public HashMap<String, Long> getFilelineMap() {
        return filelineMap;
    }

    public void setFilelineMap(HashMap<String, Long> filelineMap) {
        this.filelineMap = filelineMap;
    }
}