package mdMaker.template;

import freemarker.template.Template;

import java.io.IOException;
import java.io.Writer;

/**
 * 定义模板类处理流程
 */
public abstract class AbstractFreemarkerProcessor implements TplProcessor {

    /**
     * 数据模型
     * @param noteData
     * @return
     */
    protected abstract Object getModel(SrcNoteData noteData);

    /**
     * 数据模板
     * @return
     * @throws IOException
     */
    protected abstract Template getTemplate() throws IOException;

    /**
     * 数据输出方式
     * @param noteData
     * @return
     * @throws Exception
     */
    protected abstract Writer getWriter(SrcNoteData noteData) throws Exception;

    @Override
    public final void process(SrcNoteData noteData) throws Exception {
        Template template = getTemplate();
        Object model = getModel(noteData);
        template.process(model, getWriter(noteData));
    }
}
