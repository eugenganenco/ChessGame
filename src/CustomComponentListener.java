import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomComponentListener extends ComponentAdapter{
    public void componentResized(ComponentEvent arg0) {
        int W = 1;
        int H = 1;
        Rectangle b = arg0.getComponent().getBounds();
        arg0.getComponent().setBounds(b.x, b.y, b.width, b.width*H/W);

    }
}
