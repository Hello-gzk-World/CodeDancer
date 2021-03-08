package codeMaker.Window;

import codeMaker.Data.codeData;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;


public class codeListWindow {
    private JPanel panel1;
    private JTextArea third_codeArea;
    private JTextArea sixth_codeArea;
    private JTextArea forth_codeArea;
    private JTextArea first_codeArea;
    private JTextArea second_codeArea;
    private JButton second_button;
    private JButton third_button;
    private JButton forth_button;
    private JButton fifth_button;
    private JLabel titile_label;
    private JButton sixth_button;
    private JTextArea fifth_codeArea;
    private JButton first_button;
    private JLabel first_label;
    private JLabel second_label;
    private JLabel third_label;
    private JLabel forth_label;
    private JLabel fifth_label;
    private JPanel codeList_panel;
    private JLabel sixth_label;
    private JButton close_button;

    private void init() {

    }

    public codeListWindow(Project project, ToolWindow toolWindow) {
        init();
        //复制按钮功能
        first_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text=first_codeArea.getText();
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });
        second_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text=second_codeArea.getText();
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });
        third_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text=third_codeArea.getText();
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });
        forth_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text=forth_codeArea.getText();
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });
        fifth_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text=fifth_codeArea.getText();
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });
        sixth_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text=sixth_codeArea.getText();
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });
        close_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
/*               first_codeArea.setText("");
                second_codeArea.setText("");
                third_codeArea.setText("");
                forth_codeArea.setText("");
                fifth_codeArea.setText("");
                sixth_codeArea.setText("");*/
                //推荐代码
                first_codeArea.setText(codeData.getCode(1));
                second_codeArea.setText(codeData.getCode(2));
                System.out.print(codeData.getCode(2));
                third_codeArea.setText(codeData.getCode(3));
                forth_codeArea.setText(codeData.getCode(4));
                fifth_codeArea.setText(codeData.getCode(5));
                sixth_codeArea.setText(codeData.getCode(6));
                //判定功能
                titile_label.setText(codeData.getFunction());
                //代码排序以及质量
                first_label.setText(codeData.getData(1));
                second_label.setText(codeData.getData(2));
                third_label.setText(codeData.getData(3));
                forth_label.setText(codeData.getData(4));
                fifth_label.setText(codeData.getData(5));
                sixth_label.setText(codeData.getData(6));
            }
        });
    }

    public JComponent getContentPanel() {
        return panel1;
    }
}
