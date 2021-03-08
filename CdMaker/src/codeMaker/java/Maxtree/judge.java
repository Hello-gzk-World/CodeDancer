package codeMaker.java.Maxtree;


import codeMaker.Data.codeData;
import codeMaker.java.Detection.Launch;

import java.io.*;
import java.util.HashMap;

public class judge {
    public static HashMap<String, Double> judge(String filename) throws IOException {//判断该程序属于什么算法
        Agrim agrim1 = null;
        String filePath = Launch.DefaultPath + "/" +filename+".java";
        File file = new File(filePath);
        FileInputStream in = new FileInputStream(file);
        InputStreamReader ipr = new InputStreamReader(in, "GBK");
        BufferedReader bf = new BufferedReader(ipr);
        String str="";
        while ((str = bf.readLine()) != null) {//根据标签判断代码功能
            if(str.toLowerCase().contains("sort")){agrim1=Agrim.Sorting;break;}
            else if(str.toLowerCase().contains("balance")
                    || (str).toLowerCase().contains("load")
                    || (str).toLowerCase().contains(" tcp ")
                    || (str).toLowerCase().contains(" udp ")
                    || (str).toLowerCase().contains("roundrobin")
                    || (str).toLowerCase().contains(" wrr ")
                    || (str).toLowerCase().contains("randomload")
                    || (str).toLowerCase().contains("response")
                    || (str).toLowerCase().contains(" dlb ")
                    || (str).toLowerCase().contains("topology")
                    || (str).toLowerCase().contains("consistencyhash")
                    || (str).toLowerCase().contains("consistenthash")
                    || (str).toLowerCase().contains("HashAlgorithmImpl")
                    || (str).toLowerCase().contains("genetic")
                    || (str).toLowerCase().contains("schedule")
                    || (str).toLowerCase().contains("lcf")
                    || (str).toLowerCase().contains("WeightedRandom")
                    || (str).toLowerCase().contains("WeightedRound")

            ){agrim1=Agrim.Balance;break;}
            else if((str).toLowerCase().contains(" aes ") ||
                    (str).toLowerCase().contains("affine") ||
                    (str).toLowerCase().contains("atabash") ||
                    (str).toLowerCase().contains("bellaso") ||
                    (str).toLowerCase().contains("blowfish")
                    || (str).toLowerCase().contains("ceaserencryption")
                    || (str).toLowerCase().contains("cesar")
                    || (str).toLowerCase().contains("criptografia")
                    || (str).toLowerCase().contains(" des ")
                    || (str).toLowerCase().contains("crypt")
                    || (str).toLowerCase().contains("huffman")
                    || (str).toLowerCase().contains("vernam")
                    || (str).toLowerCase().contains(" md ")
                    || (str).toLowerCase().contains("paillier")
                    || (str).toLowerCase().contains("playfair")
                    || (str).toLowerCase().contains("rc4")
                    || (str).toLowerCase().contains(" rsa ")
                    || (str).toLowerCase().contains(" sha ")
                    || (str).toLowerCase().contains(" sm2 ")
                    || (str).toLowerCase().contains(" sm ")
                    || (str).toLowerCase().contains(" sm3 ")
                    || (str).toLowerCase().contains(" sm4 ")
                    || (str).toLowerCase().contains(" sm5 ")
                    || (str).toLowerCase().contains("substitution")
                    || (str).toLowerCase().contains(" tea ")
                    || (str).toLowerCase().contains("tinye")
                    || (str).toLowerCase().contains("encode")
                    || (str).toLowerCase().contains("decode")
                    || (str).toLowerCase().contains("cipher")
                    || (str).toLowerCase().contains("hill")
                    || (str).toLowerCase().contains("vector")
                    || (str).toLowerCase().contains("column")
                    || (str).toLowerCase().contains("symmetric")
                    || (str).toLowerCase().contains("asymmetric")
                    || (str).toLowerCase().contains("Beaufor")
                    || (str).toLowerCase().contains("vigenere")
                    || (str).toLowerCase().contains(" ecc ")){agrim1=Agrim.Encry;break;}

        }
        if(agrim1!=null){
        codeData.setFunction(agrim1+"");
        String path="D:/"+agrim1;
        BTree bTree=new BTree();
        Launch newlaunch=new Launch();
        newlaunch.setDefaultPath(path);//设置新的路径
        Launch.setDefaultPath(path);
        File src=file;
        File dest=new File(path+"/A.java");
        Filecopy.copy(src,dest);//将该文件拷贝至新的文件夹中以方便进行相似度检验
        HashMap<String, Double> simi = newlaunch.start(path);//相似度计算
        Fileclear.Fileclear(path+"/A.java");//清空该文件方便下次写入
        Object[][] kth = bTree.findkth(simi, 10);//前十高的相似度
        HashMap<String, Double> sorting=new HashMap<>();
            for (int i = 0; i < 10; i++) {
                sorting.put((String) kth[i][0], (Double) kth[i][1]);
            }
        return sorting;
        }

        else {
            Launch launchs = new Launch();
            BTree bTree = new BTree();
            HashMap<String, Double> agrim = new HashMap<>();
            HashMap<String, Double> simi = launchs.start(filename);//相似度计算
            Object[][] kth = bTree.findkth(simi, 10);//前八高的相似度
            int Sortcount = 0, ASEcount = 0, Balancecount = 0;

            Double Sortweight = 0.0, Balanceweight = 0.0, encryweight = 0.0;
            for (int i = 0; i < 10; i++) {
                if (((String) kth[i][0]).toLowerCase().contains("sort")) {
                    Sortcount++;
                    Sortweight += (Double) kth[i][1];
                } else if (((String) kth[i][0]).toLowerCase().contains("encrypt")) {
                    ASEcount++;
                    encryweight += (Double) kth[i][1];
                } else if (((String) kth[i][0]).toLowerCase().contains("balance")) {
                    Balancecount++;
                    Balanceweight += (Double) kth[i][1];
                }
            }

            if (Sortcount > ASEcount && Sortcount > Balancecount) {
                agrim1 = Agrim.Sorting;
                for (int i = 0; i < 10; i++) {
//                    if (((String) kth[i][0]).toLowerCase().contains("sort"))
//                        agrim.put((String) kth[i][0], (Double) kth[i][1]);
                }
            } else if (Balancecount > Sortcount && Balancecount > ASEcount) {
                agrim1 = Agrim.Balance;
                for (int i = 0; i < 10; i++) {
//                    if (((String) kth[i][0]).toLowerCase().contains("balance"))
//                        agrim.put((String) kth[i][0], (Double) kth[i][1]);
                }
            } else if (ASEcount > Balancecount && ASEcount > Sortcount) {
                agrim1 = Agrim.Encry;
                for (int i = 0; i < 10; i++) {
//                    if (!((String) kth[i][0]).toLowerCase().contains("balance") && !((String) kth[i][0]).toLowerCase().contains("sort"))
////                        agrim.put((String) kth[i][0], (Double) kth[i][1]);
                }
            } else if (Sortcount == ASEcount && ASEcount == Balancecount) {
                if (Sortweight > Balanceweight && Sortweight > encryweight) {
                    agrim1 = Agrim.Sorting;
                    for (int i = 0; i < 10; i++) {
//                        if (((String) kth[i][0]).toLowerCase().contains("sort"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                } else if (Balanceweight > Sortweight && Balanceweight > encryweight) {
                    agrim1 = Agrim.Balance;
                    for (int i = 0; i < 10; i++) {
//                        if (((String) kth[i][0]).toLowerCase().contains("balance"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                } else {
                    agrim1 = Agrim.Encry;
                    for (int i = 0; i < 10; i++) {
//                        if (!((String) kth[i][0]).toLowerCase().contains("balance") && !((String) kth[i][0]).toLowerCase().contains("sort"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                }

            } else if (Sortcount == ASEcount) {
                if (Sortweight > encryweight) {
                    agrim1 = Agrim.Sorting;
                    for (int i = 0; i < 10; i++) {
//                        if (((String) kth[i][0]).toLowerCase().contains("sort"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                } else {
                    agrim1 = Agrim.Encry;
                    for (int i = 0; i < 10; i++) {
//                        if (!((String) kth[i][0]).toLowerCase().contains("balance") && !((String) kth[i][0]).toLowerCase().contains("sort"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                }

            } else if (ASEcount == Balancecount) {
                if (Balanceweight > encryweight) {
                    agrim1 = Agrim.Balance;
                    for (int i = 0; i < 10; i++) {
//                        if (((String) kth[i][0]).toLowerCase().contains("balance"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                } else {
                    agrim1 = Agrim.Encry;
                    for (int i = 0; i < 10; i++) {
//                        if (!((String) kth[i][0]).toLowerCase().contains("balance") && !((String) kth[i][0]).toLowerCase().contains("sort"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                }
            } else if (Sortcount == Balancecount) {
                if (Sortweight > Balanceweight) {
                    agrim1 = Agrim.Sorting;
                    for (int i = 0; i < 10; i++) {
//                        if (((String) kth[i][0]).toLowerCase().contains("sort"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                } else {
                    agrim1 = Agrim.Balance;
                    for (int i = 0; i < 10; i++) {
//                        if (((String) kth[i][0]).toLowerCase().contains("balance"))
////                            agrim.put((String) kth[i][0], (Double) kth[i][1]);
                    }
                }
            }
            codeData.setFunction(agrim1+"");
            String path="D:/"+agrim1;
            Launch.setDefaultPath(path);
            launchs.setDefaultPath(path);//设置新的路径
            File src=file;
            File dest=new File(path+"/A.java");
            Filecopy.copy(src,dest);//将该文件拷贝至新的文件夹中以方便进行相似度检验
             simi = launchs.start("A.java");//相似度计算
            Fileclear.Fileclear(path+"/A.java");//清空该文件方便下次写入
             kth = bTree.findkth(simi, 10);//前十高的相似度
            HashMap<String, Double> sorting=new HashMap<>();
            for (int i = 0; i < 10; i++) {
                sorting.put((String) kth[i][0], (Double) kth[i][1]);
            }
            return sorting;
        }
    }
}
