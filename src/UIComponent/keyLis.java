package src.UIComponent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyLis extends KeyAdapter {

    public Window parent;

    public void keyPressed(KeyEvent e) {
        parent.keyHandle(e);
    }
    public void keyReleased(KeyEvent e) {
        parent.keyHandleRelease(e);
    }
}