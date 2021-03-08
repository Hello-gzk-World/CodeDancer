package codeMaker.java.Detection;

import java.util.*;
import java.util.regex.Pattern;

/*
 * ����һ��Դ�����࣬ÿһ���ϴ��Ĵ�����Ϊһ��Դ���򣬴�����ǳ����ļ���Ϣ��
 * �Լ��������ݵĸ�����Ϣ��
 * 
 * ���ԣ�
 * 	���ܵ��ַ����ķָʽ
 * 	�ļ���
 * 	�ؼ���ͳ�����飨���ͣ�
 * 	������Ϣ�࣬���ڳ�ʼ���ؼ���Map
 * 	�洢ָ�����ļ����µ������ļ����Ƶ�����
 * 	��ָ���ļ����¸����ļ����ƶ�ȡ�ļ�����
 * 	����һ���Լ������ã����ڼ��������
 * 	�����Ų㼶��ϵ�µı����Ƿ�Ӧ�����ӵ�һ�����飨��0/1���沼�����ͣ�
 * 
 * ������
 * 	Դ������Ϣ��ʼ����	
 * 		�ؼ��ֳ��ִ�����ʼ��
 * 		��ʼ��8��ϵͳ�����ı���
 * 	�����ļ���
 * 	����ļ���
 * 	�ַ����ָ��
 * 	�ؼ���ͳ�Ʒ���
 * 	�����Ų㼶��ϵ����--2
 * 	�ı�����Ų㼶��־λ�ķ���
 * 	�ַ������ִ������ӷ���
 * 	�ж����֮���Ƿ���ע�ͣ�ͬʱȥ��ע�ͣ�ע�⣬ȥ���Ľ��ǵ��к���ע�ͣ�
 * 
 */
public class SourcePrograme {
	//Ϊ��ʵ�ֿ��ӻ�Ч������Ҫ��������Դ���뱣������Դ���뱣������(400�д�������д���)
	Map<Integer, String> programeInfo = new TreeMap<Integer, String>();
	// �ַ����ķָʽ
	private static final String regex = "\\:|\\,|\\s|;|\\(|\\)|\\&|\\|";
	// �ļ���
	private String name;
	// �ؼ���ͳ������List
	List<Integer> keyWordCountList = new ArrayList<Integer>();
	// ������Ϣ�࣬����һ�Σ�ִ���侲̬�����������ظ�����
	LanguageInformation li = new LanguageInformation();
	//�ڴ����ŵĲ㼶��ϵ�����£��ж϶�ȡ��ָ���ַ����Ƿ�Ӧ�ý��м�1����
	List<Integer> variableList = new ArrayList<Integer>();
	/*
	 * �ؼ���ͳ�ƺ���
	 * �����ַ�����ʵ�����Ǵ��������ַ�����Ȼ��ͳ�����еĹؼ���
	 */
	//˵������Ҫ�ж��Ƿ����һ�������ڣ�����һ�����1���˳�һ�����1
	int enterBrackets = 0;
	protected void codeKeyWordCount(String str) {
		//ʵ����һ���ַ����������ڴ����Ҫͳ�Ƶ��ַ���
		List<String> strList = new ArrayList<String>();
		//����ʼ���ַ������зָ�����ܷ���ֵ
		strList = this.segmentString(str);
		//�������Ϊ�գ��հ��л���ע�ͣ�ֱ�����������账��
		if (null == strList ){
			return ;
		}
		
//System.out.println("�����Ų㼶��ϵ��   "+this.enterBrackets);
//System.out.println("����Ҫ������ַ������飺"+strList);	

		//����һ���α����ڱ������и���Ԫ��
		Iterator<String> it = strList.iterator();
		//��û�е�ĩβʱ�������ƥ�����
		while(it.hasNext()){
			/*
			 * ����α��ұߵ��ַ���
			 * �˴�ʹ�������ԭ���û����벻�淶�����п��ܰ���������
			 * ������а�����Ϊ��ͳ�ƣ���Ҫ���ָ����ַ������������н��йؼ���ͳ��
			 */
			String s = it.next();
				//�ж��ַ����Ƿ�Ϊ�����ţ����������Ƿ����������
			if(s.equals("{")){
				//�����������ţ������һ�㣬���־λ��1
				this.enterBrackets++;
				//����equals�������������Ȥ�����жϣ�ֱ���ж���һ���ַ���
				continue;
			} else if(s.equals("}")){
				//������Ҵ����ţ����˳�һ�㣬���־λ��1
				this.enterBrackets--;
				continue;
			}
			/*
			 * �жϽ�Ϊ���ӵ������������ž����ַ����ڣ���û�б��ָ����
			 * ͬʱ��Ҫ�����ж������������һ��
			 *
			 * ����������ŵĸ��������
			 * 	��������ڲ��淶�Ĵ����о����Եĺ����������������һ��ʹ�ã��м䲢���ÿո����
			 */
			s = this.BracketsClass(s);
			
//System.out.println("������һ�������ź�" + s);
			
			/*
			 * ��������Ҫ������������д����жϴ��������ַ������Ƿ��д�����
			 * ֮�����ڴ���֮������ж�����Ϊ֮ǰ�����Ѿ�������һЩ�򵥵�����������踴�Ӵ������Լ��ٿ���
			 */
			//����BracketsClass2�������ص��ַ���
			String[] strArray = this.BracketsClass2(s);
			
//System.out.println("��������������ź�" + strArray);	
			
			/*
			 * �жϴ����ŵĲ㼶��ϵ�Ƿ�Ϊ0�������0������Ҫ��ʼ��ϵͳ���������ؼ��ֶ�Ӧ������
			 * �뷽��needAddOrNot���Ӧ������һ��һ��
			 */
			if(0 == this.enterBrackets){
				this.initSysVariables();
			}
			//������ص��ַ���Ϊnull��������ʹ�����������������Ҫ��ιؼ���ͳ�ƣ��ڱ���ѭ����
			if(strArray == null){
				
//System.out.println("���������ַ�����"+s);	
				
				addOfKeyWordCountList(s);
			} else {
				
//System.out.println("���������ַ�����"+strArray);
				
				/*
				 * ˵�����зָ�������ţ���Ҫ���ͳ��,ͳ�ƴ��������ַ��������С���ȶ���
				 * һ��ͳ�����е�ֵ,���еĲ��������ظ���������
				 */
				for (int i = 0; i < strArray.length; i++) {
					addOfKeyWordCountList(strArray[i]);
				}
			}
		}
	}
	
	/*
	 * �ַ������ִ������ӷ���
	 * Ϊ�˼��ٴ�������ȣ���������������������ָ���ַ����ĳ��ִ���
	 */
	private void addOfKeyWordCountList(String str) {
		/*
		 * ֻ��Ҫ����һ�ιؼ���ͳ�ƣ�ͬʱֻ��Ҫ����֮ǰ�ĵ����ַ�������������
		 * ������Ҫ���ݴ����ŵĲ㼶��ϵ���жϹؼ��ֵ�ͳ�ƴ����Ƿ�Ӧ������
		 */
		if(this.needAddOrNot(str)){
			/*
			 * ���ܴ�����ַ�����Ҫͳ�Ƶ��ַ���
			 * ��ʼ�������ͳ�Ʋ���
			 * ע�⣺�������ж�˵������Ҫͳ�Ƶ��ַ������ض��ڹؼ���������
			 */
			int index = LanguageInformation.keyWordNameMap.get(str);
			//����λ�ö�����м�1����
			this.keyWordCountList.set(index, this.keyWordCountList.get(index) + 1);
		/*
		 * ���������������
		 * 	1.��Ҫ��ͳ�Ƶ��ַ����ѽ����ӣ���������
		 * 	2.���ܵ��ַ���������needAddOrNot�������ܴ�����ַ���
		 * 
		 * ��Ҫ�ٴ��жϣ��Ƿ�����Ҫͳ�Ƶ��ַ�����ͬʱ�ж��Ƿ���֮ǰ�Ѿ�������8��ָ���ַ���
		 */
		} else if(LanguageInformation.keyWordNameMap.containsKey(str) 
				&& !LanguageInformation.keyWord_variablesMap.containsKey(str)){
			//����ǣ������Ӵ���
			int index = LanguageInformation.keyWordNameMap.get(str);
			//����λ�ö�����м�1����
			this.keyWordCountList.set(index, this.keyWordCountList.get(index) + 1);
		} else {
			/*
			 * ������Ҫͳ�Ƶ��ַ���
			 */
			
		}
	}
	
	/*
	 * ���ݴ����ŵĲ㼶��ϵ���жϹؼ��ֵĳ��ִ����Ƿ�Ӧ������1
	 */
	protected boolean needAddOrNot(String str) {
		//�жϽ��ܵ��ַ����Ƿ���ϵͳ���������Ĺؼ���
		if(LanguageInformation.keyWord_variablesMap.containsKey(str)){
			//��ý��ܵ��ַ�����λ��
			int index = LanguageInformation.keyWord_variablesMap.get(str);
			//�������ϵͳ���������Ĺؼ��֣����ٴ��ж��Ƿ�Ӧ�ý��м�1����
			if(0 == this.variableList.get(index)){
				//����Ӧ��λ���滻��1��˵���Ѿ���ʼ���м�����������һ�������ŵĲ㼶��ϵ��
				this.variableList.set(index, 1);
				return true;
			} else {
				//��ʱ˵����1�����ѽ����ӹ�һ�Σ��������ӵڶ���
				return false;
			}
		//˵�����в�����ϵͳ���������Ĺؼ���
		} else {
			return false;	
		}
	}
	
	/*
	 * �����Ǵ����Ų㼶�жϷ����������Ƕ����ж�
	 * ���ĳЩ�������������������жϴ����ŷ��ڴ��������ַ�����
	 * ���ܵȴ�������ַ����У����Σ���������һ�����飬���ڹؼ���ͳ��
	 */
	private String[] BracketsClass2(String str){
		//�ж��ύ�������ַ����Ƿ����������������,���ַ��������û���ҵ��򷵻�ֵΪ-1
		if(str.indexOf("{") == -1 && str.indexOf("}") == -1){
			return null;
		//���ܵ��ַ����а����������
		} else if(str.indexOf("{") != -1 || str.indexOf("}") != -1){
			Pattern pattern = Pattern.compile("\\{|\\}");
			//������ʱ��������Ҫ�����滻�Ĵ�������Ŀ
			String s;
			s = str.replace("{", "");
			//�����滻�Ĵ�������Ŀ���Դ����Ų㼶��־λ���мӼ�����
			for(int i = 0; i < str.length() - s.length(); i++){
				this.enterBrackets++;
			}
			//����ͬ�ϣ�ֻ�����˴��Ƕ��Ҵ����Ž����Լ��������˳�N�������
			s = str.replace("}", "");
			for(int i = 0; i < str.length() - s.length(); i++){
				this.enterBrackets--;
			}
			
//System.out.println("���������ַ�����"+pattern.split(str));	
			
			return pattern.split(str);
		//��������Ϊ��ӭ��JAVA�����������Ƕ��趨��������ֻ��������������ĳ���
		} else {
			System.out.println("���棺ϵͳ�������ش�����BracketsClass2�����У�");
			System.exit(0);
		}
		return null;
	}
	/*
	 * �����Ų㼶��ⷽ��
	 * Ϊ�˼��ٴ��������ȣ�����������Ų㼶��ⷽ��
	 * Ϊ�˷�ֹ���ж�����������������ѭ��
	 * s.length()-temps.length(),��С����ȡ��ȡ����������Ŀ
	 */
	private String BracketsClass(String str){
		//��Ը��ӵ�������ж��Ƿ��д�����
		if(str.startsWith("{")){
			String temps = str.replace("{", "");
			this.changeBrackets(str.length()-temps.length(), "{");
			// ��������ȥ��
			return str.replace("{", "");
		} else if(str.startsWith("}")){
			String temps = str.replace("}", "");
			this.changeBrackets(str.length()-temps.length(), "}");
			// ��������ȥ��
			return str.replace("}", "");
		}
		//�ж����ɼ�������ͬ��
		if(str.endsWith("{")){
			String temps = str.replace("{", "");
			this.changeBrackets(str.length()-temps.length(), "{");
			// ��������ȥ��
			return str.replace("{", "");
		} else if(str.endsWith("}")){
			String temps = str.replace("}", "");
			this.changeBrackets(str.length()-temps.length(), "}");
			// ��������ȥ��
			return str.replace("}", "");
		}
		/*
		 * ����һЩ���񰡣���Щͬѧ�Ѵ�����Ƕ�����ַ�������eg: do{printf / do{if
		 * �˴����������������һ����������codeKeyWordCount�ж���ķ��������м��
		 */
		return str;
	}
	
	/*
	 * �ı�����Ų㼶��־λ�ķ���
	 * ���ٴ������࣬�Ӵ����Ų㼶��ⷽ���г���ı�����Ų㼶��־λ�ķ���
	 * countʵ�ʴ������ŵĳ��ִ�����str��ʾδ������ַ���
	 */
	private void changeBrackets(int count, String str){
		//�ж��Ƿ��������ƥ��
		if(str.equals("{")){
			for(int i = 0; i < count;i++){
				// ���������һ�㣬��־λ��Ҫ��1
				this.enterBrackets++;	
			}
		}
		if(str.equals("}")){
			for(int i = 0; i < count;i++){
				// ���������һ�㣬��־λ��Ҫ��1
				this.enterBrackets--;	
			}
		}
	}

	/*
	 * �ַ����ָ�
	 * �ָ����ַ��������ҷ���һ���������ArrayList����
	 * ��ȥ��ע����һЩ�հ��л������õ�ͷ�ļ���
	 * 
	 * ע�⣺ͬʱ���ж������֮���Ƿ���ע��
	 * 
	 * ���ڻ���ע������Ľ����
	 * 	����һ��ȫ�ֱ�������ʼֵΪfalse���������һ��ע������Ӧ��ֵ����Ϊtrue���˳�������Ϊfalse
	 * 
	 * �������⣺��Щע���Ǹ��ڴ�����֮���
	 */
	boolean enterNotes = false;
	private List<String> segmentString(String str) {
		//���ڴ�����õ��ַ��������������ַ�����֮���ύ��ͳ�Ʋ��ֽ���ͳ��
		List<String> strList = new ArrayList<String>();
		//ȥ����ͷ�ͽ�β�Ŀհ׷�
		str = str.trim();
		//ȥ������
		//str = str.replaceAll("\\d", "");
		//ȥ���հ��У�ͬʱȥ��class��ͷ���У����������ļ���������ֱ��ɾ��
		if(str.matches("^[\\s&&[^\\n]]*$") || str.matches("^class.*")){
			return null;
		}
		//����ǰ���ȥ���հ��ַ������ڿ���ֱ���ж��ǲ���ע�ʹ����ˣ���Ե���ע�� || (str.startsWith("/*") && str.endsWith("*/")) 
		if( str.startsWith("//")){
			return null;
		}
		
		/*
		 * ��Ը���ͬѧ��ע�͸��ڴ����ź��棬����û�м��������˴��ٴ��ж�һ��
		 * ������ǵ��ַ�������Ҫ�ж��Ƿ���ں���ע��
		 * ע�⣺��ʱ������ǵ���ע��������Ϊ�����Ǻŵ�ע��
		 */
		str = this.hasComments(str);
		
		//�ָ�����  : , * ; ( ) { } & | �հ׷� (\\{|\\}|)
		Pattern pattern = Pattern.compile(regex);
		//��ʱ���string�����飬��������List��
		String[] tempStr = pattern.split(str);
		//System.out.println("�����Ų㼶��ϵ��   "+this.enterBrackets);
        //System.out.print("�ָ�֮����ַ������飺");
        //for(int i = 0; i < tempStr.length; i++) {
        //System.out.print(tempStr[i]+"  ");
        //}
        //System.out.println("");

		/*
		 * �ж��Ƿ�Ϊ�գ����Ϊ�գ�ֱ��ȥ����������������
		 * ��ʱ�����������ж��Ƿ�Ϊע�ͣ����۵���ע�ͻ��Ƕ���ע��
		 * 
		 * ע��:Only including +-* / and non-negative Real Numbers
		 * ������������ӣ�������ͬѧ��printf�����д�������ӵ���䵼��ϵͳ��Ϊ��ע��
		 * 
		 * ����취��ֱ�����Σ���ʾ�û�ָ���г������⣬��Ҫ��������һ��
		 * ����һ��������룬����ΪΪ������������
		 * 
		 * ע�⣺//��ںͳ�������* /
		 * ����Ϊĳ��ͬѧ��ע�ͷ�ʽ���ڶ������debug��ʱ���֣���Ȼ������ôע��
		 * ����취��ͬ�ϣ�ֱ�ӱ���������ô����������ư���ֱ�ӱ�������
		 * 
		 */
		for(int i = 0; i < tempStr.length; i++){
			/*
			 * ����ȽϽ����ȣ�˵���ٴ�����
			 * ע��1��for(i=0;i<=(L->length-2)/2;i++){
			 * ������Ϊ�������ӣ����н����ַָ���־��ǳ��ţ�����ϵͳ��Ϊ��֮���Ϊ����ע��
			 * ֱ�ӷ���null
			 * ��������������Ϊ˫б��//
			 * ע��2��/*	for(i=0;i<=(L->length-2)/2;i++)* /
			 * ����Ϊ�������ӣ��ָ���ʣһ��б��/�������޷�����Ƿ�Ϊ����ע��
			 * ������������ӣ�|| tempStr[i].equals("/*")
			 * 			tempStr[i].equals("* /") �����ж���Ч
			 * Ӧ�ø�Ϊ startwith �� endswith 
			 * ע��3��for (i = 1; i <= poly_size; ++i) {  // Generator polynomial
			 * ֮���ע�ʹ���Ϊ����ע�ͣ���Ӧ�øı�ע�ͱ�־λ
			 * ��������������жϣ�Ȼ��ֱ��break
			 */
			if(tempStr[i].equals("//")){
				break;
			}
			if(tempStr[i].startsWith("/*") || tempStr[i].endsWith("*/")) {
				this.enterNotes = !this.enterNotes;
				//��ʱ����continue�����˷��ţ����⽫�����������
				continue;
			}
			//���ע�ͱ�־λ��Ϊtrue˵����������Ч�ַ�������ע��
			if(false == this.enterNotes){
				//����ж��Ƿ�ǿգ������Ϊ���������ַ�������
				if(!tempStr[i].equals("")){
					strList.add(tempStr[i]);
				}				
			}
		}

		/*
		 * �˴���������ʾ��ӡ��Ϣ����ӡ���������ַ���
		 * 
		 * ���ڽ���������ٽ�һ�δ����������һ�н�������Map��
		 * ͬʱ��KeyֵΪ��value��Ӧ��ʵ���ı���λ��
		 */
		Iterator<String> it = strList.iterator();
		String s = "";
		while(it.hasNext()){
			s += it.next();
			s += "  ";
		}
		/*
		 * ���ݱȽ���Ҫ������ǿհ��У���ȥ��
		 */
		if(s.matches("^[\\s&&[^\\n]]*$")){
			return strList;
		} else {
			programeInfo.put(lineCount, s);
			return strList;
		}
	}
	//���ڼ�¼��ȡ�ļ��ĵڼ����ˣ��α�������segmentStr���������ã����Ҵ���Map��
	int lineCount = 0;
	/*
	 * �ж����֮���Ƿ���ע�ͣ�ͬʱȥ��ע��
	 * ע�⣺��ʱ����������һ�β����е�ע��
	 */
	private String hasComments(String str) {
		/*
		 * �ж�һ�����������֮���Ƿ���һ��ע��
		 * ��Ҫͬʱ�ҵ�����ע�ͱ�־��˵����һ��ע�ͣ���������Ƕ��ע��
		 */
		if(-1 != str.indexOf("/*") && (-1 != str.indexOf("*/"))){
			str = str.substring(0,str.indexOf("/*"));
		}
		return str;
	}

	/*
	 * ��ʼ���û��ύ��Դ������Ϣ
	 */
	protected void initLanguageInformation() {
		// Ϊ���ʼ��54�Σ��ֱ��Ӧ54���ؼ���
		initKeyWordNumber();
		// Ϊ���ʼ��7�Σ��ֱ��Ӧ�����Ų㼶��ϵ�ڵ�ֵ�Ƿ�Ӧ������
		initSysVariables();
	}
	
	/*
	 * ��ʼ��54���ؼ���
	 */
	private void initKeyWordNumber() {
		for (int i = 0; i < LanguageInformation.KEY_WORD_NUMBER; i++) {
			this.keyWordCountList.add(i, 0);
		}
	}
	
	/*
	 * ��ʼ��8��ϵͳ�����ı���
	 */
	private void initSysVariables() {
		for (int i = 0; i < LanguageInformation.SYS_VARIABLES; i++) {
			this.variableList.add(i, 0);
		}
	}
	
	/*
	 * ����ѧ��������
	 */
	public void setName(String name) {
		this.name = name;
	}	
	
	/*
	 * ���ѧ������
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() 
	 * �����Ϊ�˷��������ʾЧ��,ֱ������ؼ���ͳ�ƽ��
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "\n" +this.keyWordCountList.toString();
	}

	/*
	 * ���췽����ÿ���û��ύԴ�����ʱ��Ӧ�ó�ʼ��Դ������Ϣ
	 */
	public SourcePrograme() {
		// TODO Auto-generated constructor stub
		//Դ������Ϣ��ʼ��
		initLanguageInformation();
	}

}

