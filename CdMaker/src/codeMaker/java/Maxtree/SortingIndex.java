package codeMaker.java.Maxtree;


import codeMaker.java.Detection.CalculateCycleAndLine;
import codeMaker.java.Detection.Launch;
import codeMaker.java.com.metrics.halstead.App;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SortingIndex {
    public static HashMap<String, Double> start(String path) throws IOException {
        SortingIndex sort=new SortingIndex();
        HashMap<String, Double> sortindex= sort.setSortingIndex(path);//保存文件名和对应的复杂度指数列表
        return sortindex;
    }
    public static HashMap<String, Double> start2(HashMap<String, Double> hashMap) throws IOException {
        //根据传进来的文件名列表进行对该列表文件进行按照复杂度指数进行排序
        SortingIndex sort=new SortingIndex();
        recommand recommand=new recommand();
        HashMap<String, Double> sortindex= sort.setSortingIndex(Launch.DefaultPath);//保存文件名和对应的复杂度指数列表
        HashMap<String, Double> recommandindex=new HashMap<String, Double>();
        for (String s:sortindex.keySet()){
            for (String s1:hashMap.keySet()){
                if((s+".java").equals(s1))recommandindex.put( s, sortindex.get(s));
            }
        }
        //s:MD5 s1:MD5.java
        return recommandindex;
    }

    public HashMap<String, Double> setSortingIndex(String Dirpath) throws IOException {
        HashMap<Integer, String> FileName=getFileName(Dirpath);
        HashMap<String, Double> SortingIndex=new HashMap<String, Double>();
        App app=new App();
        HashMap<String,double[]> Metricslog=app.app(Dirpath);//复杂度矩阵
        for(int p=0;p<FileName.size();p++)
        { String name=FileName.get(p).substring(0,FileName.get(p).length()-5);
            double Volume =Metricslog.get(name+".java")[0];//代码容量
            CalculateCycleAndLine cycleAndLine = new CalculateCycleAndLine();
            int CyclomaticComplexity = cycleAndLine.CaculateCycle(Dirpath,name)[0];//圈复杂度
            int LineSize = cycleAndLine.CaculateCycle(Dirpath,name)[1];//代码行数
            double Maintainability = Math.max(0, (171 - 5.2 * Math.log(Volume) - 0.23 * (CyclomaticComplexity) - 16.2 * Math.log(LineSize)) * 100 / 171);
            //代码可维护性指数。越高越好，越低说明越复杂。
            SortingIndex.put(name,Maintainability);
        }
        return SortingIndex;
    }
    public HashMap<Integer, String> getFileName(String directory){
        int count=0;
        HashMap<Integer, String> FilesName=new HashMap<>();
        File dir = new File(directory);
        if(dir.isDirectory()){
            File[]files=dir.listFiles();
            for (int i=0;i< files.length;i++)FilesName.put(i,files[i].getName());
        }
        return FilesName;
    }


}
