package mdMaker.Dialog;

import com.intellij.openapi.ui.MessageDialogBuilder;
import mdMaker.Data.GlobalData;
import mdMaker.Data.NoteData;
import mdMaker.Data.ConvertData;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class AddNoteDialog extends DialogWrapper {
    EditorTextField title;
    EditorTextField remark;

    public AddNoteDialog() {
        super(true);
        setTitle("Add Note");
        init();
    }

    @Override
    protected @Nullable
    JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        title = new EditorTextField("Topic");
        remark = new EditorTextField("content");
        remark.setPreferredSize(new Dimension(200, 100));

        panel.add(title, BorderLayout.NORTH);
        panel.add(remark, BorderLayout.CENTER);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton("Add notes to list");
        button.addActionListener(e -> {
            //得到笔记标题
            String title = this.title.getText();
            //得到笔记内容
            String remark = this.remark.getText();
            //文件类型
            String fileType = GlobalData.FILE_NAME.substring(GlobalData.FILE_NAME.lastIndexOf(".") + 1);

            NoteData noteData = new NoteData(title, remark, GlobalData.SELECTED_TEXT, GlobalData.FILE_NAME, fileType);
            GlobalData.NOTE_LIST.add(noteData);
            //表格加入行
            GlobalData.DEFAULT_TABLE.addRow(ConvertData.convert(noteData));
            //System.out.print(noteData.toString());
            //通知以及用户更加友好
            MessageDialogBuilder.yesNo("Result", "Success!").show();
            AddNoteDialog.this.dispose();
        });
        panel.add(button);
        return panel;
    }
}
