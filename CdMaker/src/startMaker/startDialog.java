package startMaker;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class startDialog extends DialogWrapper {
    JLabel label=new JLabel();
    public startDialog(){
        super(true);
        setTitle("CodeDancer ");//设置对话框标题标题
        init();//初始化dialog

    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        //创建一个面板，设置其布局为边界布局
        JPanel centerPanel = new JPanel(new BorderLayout());
        //创建一个文字标签，来承载内容
        label = new JLabel(" Welcome to Code Dancer! ");
        //设置首先大小
        label.setPreferredSize(new Dimension(100,100));

        //将文字标签添加的面板的正中间
        centerPanel.add(label,BorderLayout.CENTER);
        return centerPanel;
    }

    @Override
    protected JComponent createSouthPanel() {
        Font font=new Font("宋体",Font.PLAIN,22);
        label.setFont(font);
        JPanel southPanel = new JPanel(new FlowLayout());
        JButton button=new JButton(" Great! ");
        JButton button2=new JButton(" Just this? ");
        button.addActionListener(e -> {
            label.setText(" Have a nice day! ");
        });
        button2.addActionListener(e -> {
            label.setText(" Louder! I can't hear! ");
        });
        southPanel.add(button);
        southPanel.add(button2);
        return southPanel;
    }

}
