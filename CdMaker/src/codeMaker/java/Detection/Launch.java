package codeMaker.java.Detection;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * ���Ӻ�̨����
 * ���ԣ�
 * 	��ȡָ��λ�õ��ļ����ڵ�����--�ַ���
 * 	����һ�������������
 * ������
 * 	��ȡָ��Ŀ¼�µ��ļ�����
 * 	��ȡָ���ļ�������
 * 	
 */
public class Launch {
	// ��ȡָ��λ�õ��ļ����ڵ�����
	public static String DefaultPath = "D:/0SaveUploadFile";

	/*
	 * ����Ӧ�����󣬶��һ�����÷�������Ĭ��·�����
	 */
	public static void setDefaultPath(String defaultPath) {
		DefaultPath = defaultPath;
	}

	// ����һ�������������
	public PlagiarismDetection pd = new PlagiarismDetection();

	/*
	 * �������������򣬲��Ҵ����� ���ܵ�ֵΪ�ļ����ƣ�������ӽ��Ƚ϶���֮�ף�������
	 */
	public HashMap<String, Double> start(String fileName) {

			// Ϊ�˱����ܶ�αȽϣ����ϴ��ߵ��ļ����ڱȽ϶��е���ǰ��
			this.addNewFile(fileName);
			// �ѽ����ļ��ϴ��󣬿�ʼ��ȡ�ļ�������
			this.readDirctories(fileName);
			// ֱ�ӵ��ü����򣬷��ؽ��ܵķ��ص�ֵ
//		    pd.launchSimiMeasWithAll();
		    pd.launchSimiMeas();
		    HashMap<String, Double> Simi= pd.Simi;
		    pd=new PlagiarismDetection();
			return Simi;
//		}	
	}

	/*
	 * ���ϴ��ߵ��ļ����ڱȽ϶��е���ǰ�� ���ܵ��ַ���Ϊ�û��ϴ����ļ�����
	 */
	protected  void addNewFile(String fileName) {
		this.readFile(fileName);
	}

	/*
	 * �����ļ����ݵĶ�ȡ��Ƚϣ���Ҫ����Կ��ӻ���Ӧ�� ���������ļ��ĵ�ַ��Ȼ����ݵ�ַ�е�ֵ���ж�ȡ�ļ�������
	 */
	public  void readFileForApp(String fileName1, String fileName2) {

	}

	/*
	 * �ļ���ȡ���� ��ָ�����ļ����¸���ָ�������ƶ�ȡ�ļ����� ���� ����ȡǶ�׵��ļ��е����ݵ�ʱ�򣬻���ڵ��ļ���˵�����ڣ��Ҳ����ļ�
	 * 
	 * programeInfo����������ʱ�洢������Ϣ,����desktopapplication����
	 */
	Map<Integer, String> programeInfo = new TreeMap<Integer, String>();

	public  void readFile(String name) {
		String filePath = Launch.DefaultPath + "\\" + name;
		File file = new File(filePath);
//		System.out.println("�ļ�����=" + filePath);
		// �ж��ļ��Ƿ���ڣ�����������򱨴��˳�����
		if (file.exists()) {
			/*
			 * �жϽ��ܵ���������ͨ���ļ�����һ��Ŀ¼�������Ŀ¼���ٴε����ļ��з���
			 */
			try {
				/*
				 * ����Ǹ���ͨ�ļ�����ʼ��������ļ���ȡ
				 */
				if (true == file.isFile()) {
					// �������ֹܵ��������ȡ�ļ�������
					FileInputStream in = new FileInputStream(file);
					InputStreamReader ipr = new InputStreamReader(in, "UTF-8");
					BufferedReader bf = new BufferedReader(ipr);
					// ������ʱ�洢��ȡ��һ���ļ�����
					String str = "";
					// ������ʾЧ��
//					System.out.println("====Launch: file name:" + file.getName() + " ====");
					// ����һ���µĶ��󣬱����������ֵһֱ�ظ�
					SourcePrograme sp = new SourcePrograme();
					// ÿ�ζ�ȡһ�У�����������������������Ĳ��������û��������
					while ((str = bf.readLine()) != null) {
						sp.lineCount++;
						// ���ܴ��������ַ���
						sp.codeKeyWordCount(str);
					}
					/*
					 * �˴����������� ����ͼ�λ����棬��Ҫ������Դ����Ĵ�������ȡ�� ��˽�Map�ڵ�ֵȡ��
					 */
					this.programeInfo = sp.programeInfo;
					/*
					 * Ψ�ֳ���������󣬱���������˴��ж�һ���ļ������󣬴����Ų㼶��ϵ�Ƿ�Ϊ0
					 * �����Ϊ0�������м���ض�������ʱӦ�ñ����˳�����
					 */
					if (sp.enterBrackets != 0) {
//						System.out.println("�Բ���ϵͳ�������ش���" + sp.enterBrackets);
					}
					// ����Դ���������Ϣ��ѧ�������������ϴ����ļ�����
					sp.setName(file.getName());

					// �����õ��Ĵ�����Ϣ��ͨ�ļ�·��һ����������Ϣ������֮��
					pd.addIn_spList(sp);

					// һ�ιرո��ֹܵ�
					bf.close();
					ipr.close();
					in.close();
					/*
					 * ����Ǹ��ļ��У���Ӧ�ñ�����Ϊ�Ҳ��ᴦ��=��=
					 */
				} else if (true == file.isDirectory()) {
					this.readDirctories(filePath);
				} else {
//					System.out.println("whats the fuck!");
				}
			} catch (FileNotFoundException e) {
//				System.out.println("�ļ�û���ҵ���ϵͳ�˳���");
				System.exit(0);
			} catch (IOException e) {
//				System.out.println("�򿪴�ʧ�ܣ�ϵͳ�Ƴ���");
				System.exit(0);
			}
		} else {
//			System.out.println("����ָ���ļ������ڣ�");
		}
	}

	/*
	 * Ϊ�����������ܹ����programeInfo
	 */
	public Map<Integer, String> getProgrameInfo() {
		return this.programeInfo;
	}

	/*
	 * �ļ������ļ����ƻ�ȡ���� ָ���ļ������ļ���ȡ����֪���ݹ��ȡ������Ƕ���ļ��У� ���ݶ�ȡ�������ļ����ƣ�Ȼ���ٵ���
	 */
	public boolean readDirctories(String newFileName) {
		// ����ָ�����ļ���ַ��ȡ����
		File directorie = new File(Launch.DefaultPath);
		/*
		 * ����list�����������ļ����µ������ļ������ƣ����Ϊһ��String���飩
		 */
		String[] fileList = directorie.list();

		// System.out.println("newfilename:"+newFileName);

		// �����ó��ļ����е��ļ�Ȼ����ж�ȡ
		for (String s : fileList) {
			// ��������ļ�����������ж�ȡ��֮ǰ�Ѿ�������ڱȽ϶��е�һλ
			if (false == s.equals(newFileName)) {
//				System.out.println("filename:" + newFileName);
//				System.out.println("string s:" + s);
				readFile(s);
			}
		}
		return true;
	}
	
	public void readDirctoriesWithAll() {
		// ����ָ�����ļ���ַ��ȡ����
		File directorie = new File(Launch.DefaultPath);
		/*
		 * ����list�����������ļ����µ������ļ������ƣ����Ϊһ��String���飩
		 */
		String[] fileList = directorie.list();

		// System.out.println("newfilename:"+newFileName);

		// �����ó��ļ����е��ļ�Ȼ����ж�ȡ
		for (String s : fileList) {
			// ��������ļ�����������ж�ȡ��֮ǰ�Ѿ�������ڱȽ϶��е�һλ
			//System.out.println("string s:" + s);
			if(!s.endsWith(".pd")){
				readFile(s);
			}
		}
	}
	
}
