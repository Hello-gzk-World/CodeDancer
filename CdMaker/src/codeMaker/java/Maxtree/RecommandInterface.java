package codeMaker.java.Maxtree;


import codeMaker.java.Detection.Launch;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RecommandInterface {
    public static  void start(String srcpath, String database) throws IOException {
        String a=srcpath;
        String path=database;
        Filecopy.copy(new File(a),new File(path+"/A.java"));
        Launch.setDefaultPath(path);
        HashMap<String, Double> judgeresult=judge.judge("A");//返回这类算法的相似度最高的几个算法并根据相似度判断这是什么算法。
        HashMap<String, Double> newpp=SortingIndex.start2(judgeresult);//返回这几个算法的质量好坏程度。
        recommand.recommand(newpp);//根据好坏程度进行推荐。

    }
}
