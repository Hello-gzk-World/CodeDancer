package codeMaker.java.Detection;

import java.util.Map;
import java.util.TreeMap;

/*
 * Դ������Ϣ�����࣬���ڳ�ʼ���û��ϴ���Դ�������Ϣ������ϵͳ�ؼ��ֵĳ��ִ���
 * 
 * ���ã�����Ӧ�ñ�Դ��������ã���ÿ������һ�����󣬼�һ���ϴ��û�����ʱ��Ӧ�ý���
 * ��Ϣ��ʼ����
 * 
 */
public class LanguageInformation {
	
	/*
	 * ��Ŵ�����Ҫ����ȫ����Ϣ�����ҽ����ʼ��
	 */
	//Map�࣬���ڴ�Źؼ��������±꣬ģ�����飬���ڲ���
	public static Map<String, Integer> keyWordNameMap;
	//�ؼ�������--54��
	public static int KEY_WORD_NUMBER = 54;
	//���������ؼ���ͳ��
	public static Map<String, Integer> keyWord_variablesMap;
	//��������ϵͳ��������--7��
	public static int SYS_VARIABLES = 7;

	static {
		//java����ϵͳ�ؼ���--54��
		String[] keyWord = {
				"protected","extends","final","new","static","strictfp","synchronized",
				"transient","volatile","break","continue","return","do","while","if",
				"else","for","instanceof","switch","case","default","try","catch","throw",
				"throws","final","finally","import","package","super","this","void","goto",
				"const","null","implements","interface","native","public","class","abstract",
				"char","short","int","long","enum","float","double","signed","private",
				"boolean","byte","true","false"
		}; 
		//ʵ����Map�������ڴ洢�ؼ���
		keyWordNameMap = new TreeMap<String, Integer>();
		//��ʼ���ؼ����±�
		for(int i = 0; i < KEY_WORD_NUMBER; i++){
			keyWordNameMap.put(keyWord[i], i);
		}
		
		/*
		 * �������ؼ����Ƿ�ͳ�ƵĹ�ϵ������Դ������ʱ�Ĵ����ŵĲ㼶��ϵ�����ж�
		 */
		String[] keyWord_variables = {
				"char","short","int","long","float","double","byte"
		};
		//ʵ����keyWord_variablesMap�������ڴ洢�ؼ���λ��
		keyWord_variablesMap = new TreeMap<String, Integer>();
		//��ʼ���ؼ����±�
		for(int i = 0; i < SYS_VARIABLES; i++){
			keyWord_variablesMap.put(keyWord_variables[i], i);
		}
	}
}
