package mdMaker.Window.NoteListWindow;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;
import mdMaker.Data.GlobalData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import mdMaker.template.DefaultSrcNoteData;
import mdMaker.template.MdFreemarkerProcessor;
import mdMaker.template.TplProcessor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NoteListWindow {
    private JTextField TBtopic;
    private JTable contentList;    //表格内笔记list
    private JButton TBcreate;
    private JButton TBclose;
    private JButton TBclear;
    private JScrollPane TBcontent;   //滚轮
    private JPanel contentPanel;      //整个视窗

    private void init() {
        contentList.setModel(GlobalData.DEFAULT_TABLE);
        contentList.setEnabled(true);
    }

    public NoteListWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init();
        TBclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalData.clear();
            }
        });
        TBclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
        TBcreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic=TBtopic.getText();
                //意外情况处理
                if (topic == null || topic.trim().length() == 0) {
                    MessageDialogBuilder.yesNo("Result", "Notes title lost!");
                }

                //虚拟文件写入 类选择器
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
                if (virtualFile != null) {
                    String path = virtualFile.getPath();  //保存路径
                    String fullPath = path + "/" + topic+".md";
                    //String fullPath = path + File.pathSeparator + topic+".md"; 利用系统分隔符却最终无法得到正确的路径
                    TplProcessor processor = new MdFreemarkerProcessor();
                    try {
                        processor.process(new DefaultSrcNoteData(fullPath, topic, GlobalData.NOTE_LIST));
                        sendNotification(fullPath); //发现并没有起作用
                        MessageDialogBuilder.yesNo("Result", "Notes build successfully!").show();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            }
        });
    }

    /**
     * content :  通知内容
     * type  ：通知的类型，warning,info,error
     * 发送成功信息
     */
    public void sendNotification(String path){
        NotificationGroup notificationGroup = new NotificationGroup("codedance_id", NotificationDisplayType.BALLOON, false);
        Notification notification = notificationGroup.createNotification("文档创建成功: "+path, MessageType.INFO);
        Notifications.Bus.notify(notification);
    }

    public JComponent getContentPanel() {
        return contentPanel;
    }
}
