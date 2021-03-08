package codeMaker.java.Detection;

import java.util.Map;
import java.util.TreeMap;

/*
 * 源代码信息设置类，用于初始化用户上传的源程序的信息，比如系统关键字的出现次数
 * 
 * 调用：此类应该被源程序类调用，对每创建的一个对象，即一个上传用户，此时都应该将其
 * 信息初始化。
 * 
 */
public class LanguageInformation {
	
	/*
	 * 存放代码需要检测的全部信息，并且将其初始化
	 */
	//Map类，用于存放关键字与其下标，模仿数组，便于查找
	public static Map<String, Integer> keyWordNameMap;
	//关键字总数--54个
	public static int KEY_WORD_NUMBER = 54;
	//变量申明关键字统计
	public static Map<String, Integer> keyWord_variablesMap;
	//课申明的系统变量总数--7个
	public static int SYS_VARIABLES = 7;

	static {
		//java语言系统关键字--54个
		String[] keyWord = {
				"protected","extends","final","new","static","strictfp","synchronized",
				"transient","volatile","break","continue","return","do","while","if",
				"else","for","instanceof","switch","case","default","try","catch","throw",
				"throws","final","finally","import","package","super","this","void","goto",
				"const","null","implements","interface","native","public","class","abstract",
				"char","short","int","long","enum","float","double","signed","private",
				"boolean","byte","true","false"
		}; 
		//实例化Map对象，用于存储关键字
		keyWordNameMap = new TreeMap<String, Integer>();
		//初始化关键字下标
		for(int i = 0; i < KEY_WORD_NUMBER; i++){
			keyWordNameMap.put(keyWord[i], i);
		}
		
		/*
		 * 新增：关键字是否统计的关系，根据源程序检测时的大括号的层级关系进行判断
		 */
		String[] keyWord_variables = {
				"char","short","int","long","float","double","byte"
		};
		//实例化keyWord_variablesMap对象，用于存储关键字位置
		keyWord_variablesMap = new TreeMap<String, Integer>();
		//初始化关键字下标
		for(int i = 0; i < SYS_VARIABLES; i++){
			keyWord_variablesMap.put(keyWord_variables[i], i);
		}
	}
}
