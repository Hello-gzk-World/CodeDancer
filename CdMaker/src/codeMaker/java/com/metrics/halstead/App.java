/**
 * 
 */

/**
 * @author Ahmed Metwally
 *
 */

package codeMaker.java.com.metrics.halstead;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class App {
	// construct AST of the .java files	
	public static ASTVisitorMod parse(char[] str, String file) {
		ASTParser parser = ASTParser.newParser(AST.JLS13);

        Hashtable<String, String> options = JavaCore.getDefaultOptions();
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_13);
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_13);

        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_13);
        parser.setCompilerOptions(options);
		parser.setSource(str);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
				
		// visit nodes of the constructed AST
		ASTVisitorMod visitor= new ASTVisitorMod();
		cu.accept(visitor);
	    return visitor;
	}
	// parse file in char array
	public static HashMap<Integer, String> getFileName(String directory){
		int count=0;
		HashMap<Integer, String> FilesName=new HashMap<>();
		File dir = new File(directory);
        if(dir.isDirectory()){
        	File[]files=dir.listFiles();
        	for (int i=0;i< files.length;i++)FilesName.put(count++,files[i].getName());
		}
        return FilesName;
	}
	public static char[] ReadFileToCharArray(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
 
		return  fileData.toString().toCharArray();	
	}
	// parse files in a directory to list of char array
	public static List<char[]> ParseFilesInDir(List<String> JavaFiles) throws IOException {
		if(JavaFiles.isEmpty()) 
		{
			System.out.println("There is no java source code in the provided directory");
			System.exit(0);
		}
		List<char[]> FilesRead= new ArrayList<char []>();
		
		for(int i=0; i<JavaFiles.size(); i++)
		{
			//System.out.println("Now, reading: "+ JavaFiles.get(i));
			FilesRead.add(ReadFileToCharArray(JavaFiles.get(i)));
		}
		return FilesRead;
	}
	// retrieve all .java files in the directory and subdirectories.
	public static List<String> retrieveFiles(String directory) {
		List<String> Files = new ArrayList<String>();
		File dir = new File(directory);
		
		if (!dir.isDirectory())
		{			
			 System.out.println("The provided path is not a valid directory");
			 System.exit(1);
		}

		for (File file : dir.listFiles()) {
			if(file.isDirectory())
			{
				Files.addAll(retrieveFiles(file.getAbsolutePath()));
			}
			else{
                            if (file.getName().endsWith((".java"))) 
			    {
				Files.add(file.getAbsolutePath());
			    }
                        }
		}

		return Files;
	}

	public HashMap<String,double[]> app(String DirName) throws IOException {
		// retrieve all .java files in the directory and subdirectories. 
		List<String> JavaFiles=retrieveFiles(DirName);
		HashMap<Integer, String> FilesName=getFileName(DirName);
		double[] Log=new double[8];
		//第一个元素存Volume；容量
		// 第二个元素存Difficulty；难度
		// 第三个元素存Effort；编程工作量
		// 第四个元素存TimeReqProg；编程时间
		// 第五个元素存TimeDelBugs；
		// 第六个元素存Vocabulary；词汇表长度
		// 第七个元素存Proglen；程序长度
		// 第八个元素存CalcProgLen程序预测程度
		// parse files in a directory to list of char array
		List<char[]> FilesRead=ParseFilesInDir(JavaFiles);
		HalsteadMetrics hal = new HalsteadMetrics();
		ASTVisitorMod ASTVisitorFile;
		int DistinctOperators=0;
		int DistinctOperands=0;
		int TotalOperators=0;
		int TotalOperands=0;
		int OperatorCount=0;
		int OperandCount=0;
		HashMap<String,double[]> FilesMatricsLogs=new HashMap<String,double[]>();
		// Construct the AST of each java file. visit different nodes to get the number of operors and operands
		// Retrieve the number of distinct operators, distinct operands, 
		// total operators, and total operands for each .java file in the directory. 
		// Sum each parameter from different files together to be used in Halstead Complexity metrics. 
		for(int i=0; i<FilesRead.size(); i++)
		{
			//System.out.println("Now, AST parsing for : "+ JavaFiles.get(i));
			ASTVisitorFile=parse(FilesRead.get(i), JavaFiles.get(i));
			DistinctOperators=ASTVisitorFile.oprt.size();
			DistinctOperands=ASTVisitorFile.names.size();

			OperatorCount=0;
			for (int f : ASTVisitorFile.oprt.values()) {
				OperatorCount+= f;
			}
			TotalOperators=OperatorCount;
			OperandCount=0;
			for (int f : ASTVisitorFile.names.values()) {
				OperandCount += f;
			}
			TotalOperands=OperandCount;

			hal.setParameters(DistinctOperators, DistinctOperands, TotalOperators, TotalOperands);
            double volume=hal.getVolume();
			Log[0]=hal.getVolume();
			Log[1]=hal.getDifficulty();
			Log[2]=hal.getEffort();
			Log[3]=hal.getTimeReqProg();
			Log[4]=hal.getTimeDelBugs();
			Log[5]=hal.getVocabulary();
			Log[6]=hal.getProglen();
			Log[7]=hal.getCalcProgLen();
			FilesMatricsLogs.put(FilesName.get(i),Log);
			//System.out.println(new String(JavaFiles.get(i)) + ";" + String.valueOf(volume));
            //writeUsingFiles(new String(JavaFiles.get(i)) + ";" + String.valueOf(volume));
		}

		return FilesMatricsLogs;
	}

}
