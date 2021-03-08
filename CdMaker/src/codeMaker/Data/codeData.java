package codeMaker.Data;

import codeMaker.java.Maxtree.Agrim;

/**
 * 所要推荐代码的全局变量
 */

public class codeData {

    public static String function;
    public static String[][] codeList=new String[6][2];  //包含推荐的代码以及代码指标

    public codeData(String function, String[][] codeList) {
        this.function = function;
        this.codeList = codeList;
    }

    public static String getFunction() {
        return function;
    }

    public static void setFunction(String x) {
        function = x;
    }

    public static String getCode(int rank) {
        return codeList[rank-1][0];
    }

    public static String getData(int rank) {
        return codeList[rank-1][1];
    }

}
