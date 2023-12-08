package src.UIComponent;

import javax.swing.*;

import src.UIComponent.UIComponent;

import java.awt.*;

public class Slider extends JPanel implements UIComponent {
    private JSlider slider;
    private int x;
    private int y;

    public Slider(int x, int y, int min, int max, int initialValue) {
        slider = new JSlider(min, max, initialValue);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.x = x;
        this.y = y;
        

        setLayout(new BorderLayout());
        add(slider, BorderLayout.CENTER);
    }

    public int getValue() {
        return slider.getValue();
    }

    public void setValue(int value) {
        slider.setValue(value);
    }

    @Override
    public void draw(Graphics window) {
        window.drawImage(null, x, y, slider);
    }

}
