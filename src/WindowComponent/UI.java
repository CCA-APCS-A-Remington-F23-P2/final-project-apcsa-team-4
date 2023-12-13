package src.WindowComponent;

import src.UIComponent.ColorPicker;
import src.UIComponent.LayerSelector;
import src.UIComponent.LayerUI;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

public class UI extends Canvas implements MouseListener, Runnable, MouseMotionListener {

    private ColorPicker cp;
    private JColorChooser cp2;
    private LayerSelector ls;
    private paint p;
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean mouseDown = false;
    private int mouseX = 0;
    private int mouseY = 0;
    private boolean[] keys = new boolean[2];
    private boolean[] keysPrev = new boolean[2];
    public boolean mouseClick = false;

    public UI(int cWidth, int cHeight, int x, int y, paint p) {
        this.x = x;
        this.y = y;
        this.p = p;
        cp = new ColorPicker(x, y, cWidth - 1, cWidth - 1);
        ls = new LayerSelector(x, y + cWidth - 1, cWidth - 1, cHeight - 200 - cWidth + 1);

        ls.addLayer(p.getCurr());
        cp2 = null;

        width = cWidth;
        height = cHeight;

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        new Thread(this).start();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void setCP(JColorChooser cp2) {
        this.cp2 = cp2;
    }

    public void paint(Graphics window) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setStroke(new BasicStroke(3));
        g.clearRect(x, y, width, height);

        if (mouseDown && mouseX >= cp.getX() && mouseX <= cp.getX() + cp.getWidth() && mouseY >= cp.getY()
                && mouseY <= cp.getY() + cp.getHeight() - cp.barHeight()) {
            cp2.setColor(cp.pick(mouseX, mouseY));
        }

        if (cp2.getColor() != cp.getColor()) {
            cp.setColor(cp2.getColor());
        }

        p.recolor(cp2.getColor());

        if (ls.getNewLayerButton().isClicked(mouseX, mouseY, mouseClick)) {
            ls.addLayer(p.addLayer());
        }

        for (int i = 0; i < ls.getLayers().size(); i++) {
            LayerUI layer = ls.getLayers().get(i);

            if (layer.isClicked(mouseX, mouseY, mouseClick)) {
                ls.setSelectedLayer(ls.getLayers().indexOf(layer));
                p.setCurr(ls.getLayers().indexOf(layer));
            }

            if (layer.getDeleteButton().isClicked(mouseX, mouseY, mouseClick)) {
                if (ls.getLayers().indexOf(layer) == ls.getSelectedLayer()) {
                    ls.setSelectedLayer(0);
                    p.setCurr(0);
                }

                if (ls.getLayers().size() == 1) {
                    ls.addLayer(p.addLayer());
                }

                int index = ls.getLayers().indexOf(layer);

                ls.removeLayer(index);
                p.removeLayer(index);
            }

            if (layer.getHideButton().isClicked(mouseX, mouseY, mouseClick)) {
                // System.out.println(p.getLayers().get(i).getVisible());
                if (p.getLayers().get(i).getVisible()) {
                    p.getLayers().get(i).setVisible(false);
                    layer.getHideButton().setIcon(new ImageIcon("assets/show.png"));
                } else {
                    // p.removeLayer(i);
                    p.getLayers().get(i).setVisible(true);
                    layer.getHideButton().setIcon(new ImageIcon("assets/hide.png"));
                }
            }

            if (layer.getMoveUpButton().isClicked(mouseX, mouseY, mouseClick)) {
                int index = ls.getLayers().indexOf(layer);

                if (index == 0) {
                    return;
                }

                ls.switchLayers(index, index - 1);
                p.switchLayers(index, index - 1);
            }

            if (layer.getMoveDownButton().isClicked(mouseX, mouseY, mouseClick)) {
                int index = ls.getLayers().indexOf(layer);

                if (index == ls.getLayers().size() - 1) {
                    return;
                }

                ls.switchLayers(index, index + 1);
                p.switchLayers(index, index + 1);

            }
        }

        if (mouseX >= cp.getX() + cp.getWidth() - cp.barHeight() * 3
                && mouseX <= cp.getX() + cp.getWidth() - cp.barHeight() * 2
                && mouseY >= cp.getY() + cp.getHeight() - cp.barHeight() && mouseY <= cp.getY() + cp.getHeight()) {
            if (keys[0])
                cp.changeRed(1);
            if (keys[1])
                cp.changeRed(-1);
        }
        if (mouseX >= cp.getX() + cp.getWidth() - cp.barHeight() * 2
                && mouseX <= cp.getX() + cp.getWidth() - cp.barHeight()
                && mouseY >= cp.getY() + cp.getHeight() - cp.barHeight() && mouseY <= cp.getY() + cp.getHeight()) {
            if (keys[0])
                cp.changeGreen(1);
            if (keys[1])
                cp.changeGreen(-1);
        }
        if (mouseX >= cp.getX() + cp.getWidth() - cp.barHeight() && mouseX <= cp.getX() + cp.getWidth()
                && mouseY >= cp.getY() + cp.getHeight() - cp.barHeight() && mouseY <= cp.getY() + cp.getHeight()) {
            if (keys[0])
                cp.changeBlue(1);
            if (keys[1])
                cp.changeBlue(-1);
        }

        p.recolor(cp.getColor());
        cp.draw(g);
        ls.draw(g);

        mouseClick = false;

        g.setColor(new Color(130, 130, 130));
        g.setStroke(new BasicStroke(1));
        g.drawRect(x - 2, y - 1, width, height + 2);

        Graphics2D g2dComponent = (Graphics2D) window;
        g2dComponent.drawImage(bufferedImage, null, x, y);
    }

    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[1] = true;
        }
    }

    public void keyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[1] = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(16);
                repaint();
            }
        } catch (Exception e) {
            System.out.println("Screw you there was an error but we're too lazy to tell you what happened");
        }
    }

    public void mouseClicked(MouseEvent e) {
        mouseClick = true;
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        mouseClick = false;
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() - x;
        mouseY = e.getY() - y;
    }

    public void mouseDragged(MouseEvent e) {
        mouseDown = true;

        mouseX = e.getX() - x;
        mouseY = e.getY() - y;
    }

    public LayerSelector getLayerSelector() {
        return ls;
    }
}
