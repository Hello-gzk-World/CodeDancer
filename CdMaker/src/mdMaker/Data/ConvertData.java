package mdMaker.Data;

/**
 * 对于行的数据类型转换
 */
public class ConvertData {
    public static String[] convert(NoteData noteData) {
        String[] rowList = new String[4];
        rowList[0]=noteData.getTitle();
        rowList[1]=noteData.getremark();
        rowList[2]=noteData.getFileName();
        rowList[3]=noteData.getContent();
        return rowList;
    }
}
