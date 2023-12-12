package src.UIComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import src.WindowComponent.Window;

public class keyLis extends KeyAdapter {

    public Window parent;

    public keyLis(Window parent) {
        this.parent = parent;
    }

    public void keyPressed(KeyEvent e) {
        parent.keyHandle(e);
    }
    public void keyReleased(KeyEvent e) {
        parent.keyHandleRelease(e);
    }
}
