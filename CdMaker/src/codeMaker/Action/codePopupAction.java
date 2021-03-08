package codeMaker.Action;

import codeMaker.Data.codeData;
import codeMaker.java.Maxtree.Fileclear;
import codeMaker.java.Maxtree.RecommandInterface;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class codePopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前编辑器对象
        Editor editor=e.getRequiredData(CommonDataKeys.EDITOR);
        // 获取选择的数据模型
        SelectionModel selectionModel=editor.getSelectionModel();
        // 获取当前选择的代码段
        String selectedText=selectionModel.getSelectedText();
        //根据对比得到相应的相似度 文件内容 代码质量
        try {
            start(selectedText);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 程序入口
     * @param text
     * @throws IOException
     */
    public void start(String text) throws IOException {
        Fileclear.Fileclear("D:/111/A.java");
        fileWrite(text);
        String srcpath="D:/111/A.java";
        String databasepath="D:/0SaveUploadFile";
        RecommandInterface.start(srcpath,databasepath);
        Fileclear.Fileclear("D:/0SaveUploadFile"+"/A.java");//清空该文件方便下次写入
    }

    /**
     * 修改对比代码段文件
     * @param text
     */
    public void fileWrite(String text){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("D:/111/A.java"));
            out.write(text);
            out.close();
        } catch ( IOException e) {
            System.out.print("Wrong input");
        }
    }
}
