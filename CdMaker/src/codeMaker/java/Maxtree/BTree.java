package codeMaker.java.Maxtree;

import java.util.HashMap;

public class BTree {
    public Object[][] findkth(HashMap<String, Double> has, int k){
        Object[][]fina=new Object[k][2];
        Object[][]ob=new Object[has.size()][2];
        int i=0;
        for(String s: has.keySet()){
            ob[i][0]=s;
            ob[i][1]=has.get(s);
            i++;
        }
        for(int j=0;j<k;j++){
            fina[j][0]=ob[j][0];
            fina[j][1]=ob[j][1];
        }
        for(int w=k;w<ob.length;w++){
            int kth=findmin(fina);
            if((Double)ob[w][1]>(Double)fina[kth][1]){
                fina[kth][0]=ob[w][0];
                fina[kth][1]=ob[w][1];
            }

        }
       return fina;

    }
    public int findmin(Object[][]objects){
        Double defauitv=(Double) objects[0][1];
        int pp=0;
        for(int i=0;i<objects.length;i++){
            if((Double)objects[i][1]<defauitv){
                pp=i;
                defauitv=(Double)objects[i][1];
            }
        }
        return pp;
    }

}
