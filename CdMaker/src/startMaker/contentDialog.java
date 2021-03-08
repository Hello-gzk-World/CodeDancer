package startMaker;

import com.intellij.openapi.components.ApplicationComponent;

public class contentDialog implements ApplicationComponent {
    @Override
    public void initComponent() {
        startDialog dialog=new startDialog();
        dialog.show();
    }
}
