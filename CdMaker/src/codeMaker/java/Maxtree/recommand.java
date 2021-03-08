package codeMaker.java.Maxtree;


import codeMaker.Data.codeData;
import codeMaker.java.Detection.Launch;

import java.io.*;
import java.util.HashMap;

public class recommand {
    public static void recommand(HashMap<String, Double> newpp) throws IOException {
        //根据计算出来的指标进行排序
        for (String s:newpp.keySet()){
            System.out.println(s+" "+newpp.get(s));
        }
        Double max=-999.0;
        String s1="";
        int len=newpp.size();
        Object[][]sort1=new Object[len][2];
        while (len>0) {
            for (String s : newpp.keySet()) {
                if (newpp.get(s) > max) {
                    s1 = s;
                    max = newpp.get(s);
                }
            }
            sort1[newpp.size()-len][0]=s1;
            sort1[newpp.size()-len][1]=newpp.get(s1);
            newpp.put(s1,-999.0);
            s1="";max=-999.0;
            len--;

        }
//相似度最高的十个中推荐最高的六个
        String[][] codeList= new String[6][2];
        for(int rank=0;rank<6;rank++){//根据优先级进行推荐。
            String filePath = Launch.DefaultPath + "\\" +sort1[rank][0]+".java";
            File file = new File(filePath);
            FileInputStream in = new FileInputStream(file);
            InputStreamReader ipr = new InputStreamReader(in, "GBK");
            BufferedReader bf = new BufferedReader(ipr);
            String str="";
            String code = "";
            while ((str = bf.readLine()) != null) {
                code+=(str+"\n");
            }
            System.out.println(code);
            codeList[rank][0]=code;
            codeList[rank][1]=sort1[rank][1]+"";
        }
        codeData.codeList=codeList;
    }
}
