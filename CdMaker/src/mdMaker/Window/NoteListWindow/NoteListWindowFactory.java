package mdMaker.Window.NoteListWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class NoteListWindowFactory implements ToolWindowFactory {
    /**
     * 先将生成一个panel放进contentFactory中，并加载进toolwindow，就将关键的界面一一绑定。
     * @param project
     * @param toolWindow
     */
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        //创建出NoteListWindow对象
        NoteListWindow noteListWindow = new NoteListWindow(project, toolWindow);
        //获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        //获取用于toolWindow显示的内容
        Content content = contentFactory.createContent(noteListWindow.getContentPanel(), "", false);
        //给toolWindow设置内容
        toolWindow.getContentManager().addContent(content);
    }
}
