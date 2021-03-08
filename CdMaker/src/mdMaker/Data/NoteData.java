package mdMaker.Data;

public class NoteData {
    /**
     * 标题 内容 源码 所在文件名 文件类型
     */
    private String title;
    private String remark;
    private String content;
    private String fileName;
    private String fileType;

    public NoteData(String title, String remark, String content, String fileName, String fileType) {
        this.title = title;
        this.remark = remark;
        this.content = content;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "NoteData{" +
                "titile='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", content='" + content + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
