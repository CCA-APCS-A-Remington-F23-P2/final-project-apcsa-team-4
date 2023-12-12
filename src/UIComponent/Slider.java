
package src.UIComponent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Slider extends JPanel implements MouseListener, MouseMotionListener {

    private final int MIN_VALUE = 0;
    private final int MAX_VALUE = 100;
    private int currentValue;
    private int trackLength;
    private int knobSize;
    private int knobX;
    private int knobY;

    public Slider(int maxValue, int minValue, int defaultValue, int x, int y, int width, int height) {
        trackLength = width;
        this.knobSize = height;
        this.knobX = x;
        this.knobY = y;
        this.currentValue = defaultValue;



        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override

    
public void paintComponent(Graphics g)
{
        super.paintComponent(g);

        // Draw track
        g.setColor(Color.GRAY);
        g.fillRect(0, getHeight() / 2 - trackLength / 2, trackLength, trackLength);

        // Draw knob
        g.setColor(Color.BLUE);
        g.fillOval(knobX - knobSize / 2, getHeight() / 2 - knobSize / 2, knobSize, knobSize);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int clickedX = e.getX();
        if (clickedX >= knobX - knobSize / 2 && clickedX <= knobX + knobSize / 2) {
            // Start dragging the knob
            setKnobX(clickedX);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging()) {
            int draggedX = e.getX();
            setKnobX(draggedX);
            currentValue = (draggedX - knobSize / 2) * (MAX_VALUE - MIN_VALUE) / trackLength + MIN_VALUE;
            repaint();
        }
    }

    private void setKnobX(int x) {
        knobX = Math.min(Math.max(x, knobSize / 2), trackLength - knobSize / 2);
    }

    private boolean isDragging() {
        return knobX > knobSize / 2 && knobX < trackLength - knobSize / 2;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
