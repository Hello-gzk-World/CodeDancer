package mdMaker.Data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

public class GlobalData {
    //文件内容
    public static String SELECTED_TEXT;
    //文件名
    public static String FILE_NAME;
    //数据
    public static List<NoteData> NOTE_LIST = new LinkedList<>();
    //表格表头
    public static String[] HEADERS = {"Topic", "Content",  "Filename", "Code"};

    public static DefaultTableModel DEFAULT_TABLE = new DefaultTableModel(null, HEADERS);

    /**
     * 清楚列表
     */
    public static void clear() {
        NOTE_LIST.clear();
        DEFAULT_TABLE.setDataVector(null, HEADERS);
    }
}
