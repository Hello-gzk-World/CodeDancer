package codeMaker.java.Detection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 此为源程序检测类，用于检测指定文件夹下的文件的相似度
 * 属性：
 * 	存放源程序信息的数组
 * 方法：
 * 	将源程序类加入源程序比较队列
 * 	相似度比较方法（读取源程序数组中的值，然后将其传入真正的计算方法中）
 * 	注：	判断函数修改为修正余弦相似度
 * 		修正cosine相似度的目的是解决cosine相似度仅考虑向量维度方向上的相似，
 * 		而没考虑到各个维度的量纲的差异性，所以在计算相似度的时候，
 * 		做了每个维度减去均值的修正操作。
 * 	
 * 	获得两个向量的平均值
 * 	调整余弦函数求相似度
 * 
 */
public class PlagiarismDetection {
	HashMap<String, Double> Simi=new HashMap<>();

	//原程序信息存放的数组
	List<SourcePrograme> spList  = new ArrayList<SourcePrograme>();
	//检测代码的初设阈值
	public double THRESHOLD = 0.95;
	
	public void setTHRESHOLD(double tHRESHOLD) {
		THRESHOLD = tHRESHOLD;
	}
	/*
	 * 比较待检测程序与其他所有源程序的相似程度
	 */
	public boolean launchSimiMeas() {
		double res = 0.0;
		info.clear();
		HashMap<String, Double> Similarity=new HashMap<>();
		if(this.spList.size() >= 2){
				//先提前取出一个元素，避免自己与自己比较
				List<Integer> l1 = this.spList.get(0).keyWordCountList;
				//循环取出数组中的值进行比较

			for(int i = 1; i < this.spList.size(); i++) {
				List<Integer> l2 = this.spList.get(i).keyWordCountList;
				String name=this.spList.get(i).getName();
				//调用检测代码
				Double result = new Double(this.AdjustedCosineSimilarity(l1, l2));
				res = 0.0;
				/*
				 * 将得到的结果进行分割，去前面五位数字，便于观察
				 */
				if(result.toString().length() >= 5){
					res = Double.parseDouble(result.toString().substring(0, 5));
				} else {
					res = result;
				}
//
				Similarity.put(name,res);
				    this.Simi=Similarity;
			}
			return true;
		} else {
		//说明检测队列中没有需要检测的源程序，可以直接返回0.0，无须比较
			return true;
		}
		
	}

	/* 
	 * 由于没有获得启动类的引用，此时通过一个函数将值传入出去
	 */
	public List<String> info = new ArrayList<String>();
	public void setInfo(String name1, String name2, Double result){
		info.add(name1);
		info.add(name2);
		info.add(result.toString());
	}
	
	/*
	 * 余弦函数：
	 * 	定义向量a,b
	 * 	cossim = ab/|a||b|
	 * 调整余弦函数：
	 * 	定义向量a,b,mean
	 * 	adjcossim = (a-mean)(b-mean)/|a-mean||b-mean|
	 * 比较两个源程序的相似程度(关键字的出现次数)，返回比较结果
	 * 
	 */
	public double AdjustedCosineSimilarity(List<Integer> list1, List<Integer> list2){
		//先将接受的两个参数进行修正
		List<Double> lista = this.adjustCount(list1);
		List<Double> listb = this.adjustCount(list2);
		/*
		 * 求分子：向量a * 向量b
		 * 分母：向量a的模 * 向量b的模
		 */
		double aa = 0.0;
		double bb = 0.0;
		double ab = 0.0;
		for(int i = 0; i < lista.size(); i++){
			aa += lista.get(i) * lista.get(i);
			bb += listb.get(i) * listb.get(i);
			ab += lista.get(i) * listb.get(i);
		}
		return ab / (Math.sqrt(aa) * Math.sqrt(bb));
	}

	
	/*
	 * 统计结果修正方法
	 * 根据修正余弦函数的要求，需要对统计结果进行修正即：
	 * 程序代码关键字出现次数减去该程序代码中出现所有关键字的均值得到修正次数
	 * 根据接受的参数依次遍历其中的值，然后减去均值
	 */
	private List<Double> adjustCount(List<Integer> list){
		List<Double> l = new ArrayList<Double>();
		//获得该程序所有关键字t统计的修正均值
		double mean = this.adjustMean(list);
		//依次遍历其中的值，将非零项均减去关键字种类出现次数，即转换为修正统计次数
		for(int i = 0; i < list.size(); i++) {
			//如果循环的项中有非零项，则将其修正
			if(0 != list.get(i)) {
				l.add(list.get(i) - mean);
			} else {
				l.add(0.0);
			}
		}
		return l;
	}

	/*
	 * 统计结果修正方法之统计总次数，即统计过的关键字次数
	 * 根据接受的参数，循环遍历，统计非零项
	 */
	private double adjustMean(List<Integer> list) {
		//关键字的出现总次数
		int allNum = 0;
		//关键字种类出现的次数
		int num = 0;
		for(int i = 0; i < list.size(); i++){
			//对于其中的非零项，统计结果加1
			if(0 != list.get(i)) {
				allNum += list.get(i);
				num++;
			}
		}
		return allNum * 1.0 / num;
	}
	/*
	 * 源程序信息添加入组
	 */
	public void addIn_spList(SourcePrograme sp) {
		this.spList.add(sp);
	}

	/*
	 * 显示添加进入的源程序的统计结果
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.spList.size(); i++){
//			System.out.println("文件名称："+this.spList.get(i).getNameOfStudent());
//			System.out.println(this.spList.get(i));
		}
		return null;
	}
}
