package mdMaker.template;


import mdMaker.Data.NoteData;

import java.util.List;

/**
 * 源数据类型
 */
public interface SrcNoteData {
    String getFileName();
    List<NoteData> getNoteList();
    String getTopic();
}
