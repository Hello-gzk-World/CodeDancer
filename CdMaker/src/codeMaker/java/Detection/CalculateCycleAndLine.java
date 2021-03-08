package codeMaker.java.Detection;

import java.io.*;

public class CalculateCycleAndLine {
    public int[] CaculateCycle(String path, String name) throws IOException {//圈复杂度计算
        String filePath = path + "\\" + name;
        File file = new File(filePath+".java");
        int count = 1;
        int linesize=0;
        int[] array=new int[2];
        //第一个元素放圈复杂度，第二个元素存放代码行数。
        {
            // 创建各种管道，方便读取文件的内容
            FileInputStream in = new FileInputStream(file);
            InputStreamReader ipr = new InputStreamReader(in, "GBK");
            BufferedReader bf = new BufferedReader(ipr);
            String str = "";
            SourcePrograme sp = new SourcePrograme();
            while ((str = bf.readLine()) != null) {
                linesize+=1;
                if (str.contains("while")&&!str.contains("//")) count++;
                if (str.contains("if")&&!str.contains("//")) count++;
                if (str.contains("for")&&!str.contains("//")) count++;
                if (str.contains("case")&&!str.contains("//")) count++;
                if (str.contains("catch")&&!str.contains("//")) count++;
                if (str.contains("&")&&!str.contains("//")) count++;
                if (str.contains("|")&&!str.contains("//")) count++;
                if (str.contains("^")&&!str.contains("//")) count++;
                if (str.contains("?")&&str.contains(":")&&!str.contains("//")) count++;
                if (str.contains("else")&&!str.contains("//")) count++;
                if (str.contains("break")&&!str.contains("//")) count++;
                if (str.contains("goto")&&!str.contains("//")) count++;
                if (str.contains("return")&&!str.contains("//")) count++;
                if (str.contains("continue")&&!str.contains("//")) count++;
                if (str.contains("throw")&&!str.contains("//")) count++;
                if (str.contains("default")&&!str.contains("//")) count++;
                if (str.contains("catch")&&!str.contains("//")) count++;
                if (str.contains("except")&&!str.contains("//")) count++;

            }
            bf.close();
            ipr.close();
            in.close();
//            System.out.println("Cycles="+count);
        }
        array[0]=count;
        array[1]=linesize;
        return array;
    }
}
