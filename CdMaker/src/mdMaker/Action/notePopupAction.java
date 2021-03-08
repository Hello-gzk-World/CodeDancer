package mdMaker.Action;

import mdMaker.Data.GlobalData;
import mdMaker.Dialog.AddNoteDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

public class notePopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前编辑器对象
        Editor editor=e.getRequiredData(CommonDataKeys.EDITOR);
        // 获取选择的数据模型
        SelectionModel selectionModel=editor.getSelectionModel();
        // 获取当前选择的文本
        String selectedText=selectionModel.getSelectedText();
        GlobalData.SELECTED_TEXT=selectedText;
        //文件名
        String filename = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile().getName();
        GlobalData.FILE_NAME=filename;

        //显示对话框
        AddNoteDialog dialog = new AddNoteDialog();
        dialog.show();
    }
}
