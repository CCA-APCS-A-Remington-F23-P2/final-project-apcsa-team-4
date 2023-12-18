package src.WindowComponent;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.KeyEvent;
import src.UIComponent.KeyLis;
import src.UIComponent.imageExport;

public class Window extends JFrame {

    public static final int PROJECT_WIDTH = 500;
    public static final int PROJECT_HEIGHT = 400;

    public static final int UI_WIDTH = 200;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final int UI_HEIGHT = HEIGHT;

    private KeyLis kl;
    private Paint p;
    private UI u;

    public Window() {
        super("Paint-- (v0.0.1)");
        java.awt.image.BufferedImage in = null;

        File img = new File("assets/cursor.png");
        try {
            in = ImageIO.read(img);
        } catch (IOException ex) {
            System.err.print("LMAO\n");
            ex.printStackTrace();
            System.exit(1);
        }
        setSize(WIDTH, HEIGHT);
        try {
            setIconImage(ImageIO.read(new File("assets/logo.png")));
        } catch (Exception e) {
        }
        ;

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem save = new JMenuItem(new AbstractAction("Save") {
            public void actionPerformed(ActionEvent ae) {
                imageExport.export(null, p.getBufferedImage());
            }
        });
        JMenuItem open = new JMenuItem(new AbstractAction("Open") {
            public void actionPerformed(ActionEvent ae) {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                }


                try {
                    u.getLayerSelector().addLayer(p.importImage(ImageIO.read(chooser.getSelectedFile())));
                } catch (IOException e) {
                    System.out.println("what the crap did u do");
                }
            }
        });

        JMenu tool = new JMenu("Tool");
        JMenuItem fill = new JRadioButtonMenuItem(new AbstractAction("Fill") {
            public void actionPerformed(ActionEvent ae) {
                p.setFill(true);
            }
        });

        JMenuItem brushM = new JRadioButtonMenuItem(new AbstractAction("Brush") {
            public void actionPerformed(ActionEvent ae) {
                p.setFill(false);
            }
        });

        JMenuItem brushUp = new JMenuItem(new AbstractAction("Size Up") {
            public void actionPerformed(ActionEvent ae) {
                p.resizeBrush(1);
            }
        });

        JMenuItem brushDown = new JMenuItem(new AbstractAction("Size Down") {
            public void actionPerformed(ActionEvent ae) {
                p.resizeBrush(-1);
            }
        });

        ButtonGroup toolGroup = new ButtonGroup();
        toolGroup.add(fill);
        toolGroup.add(brushM);

        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(brushUp);
        sizeGroup.add(brushDown);

        JComponent separator = new JSeparator();

        JComponent colorPicker = new JColorChooser();

        menu.add(open);
        menu.add(save);
        tool.add(fill);
        tool.add(brushM);
        tool.add(separator);
        tool.add(brushUp);
        tool.add(brushDown);
        tool.add(colorPicker);
        menubar.add(menu);
        menubar.add(tool);
        brushM.setSelected(true);
        menubar.setBackground(new Color(210, 210, 210));
        setJMenuBar(menubar);
        ((JColorChooser)colorPicker).setColor(Color.BLACK);

        p = new Paint(PROJECT_WIDTH, PROJECT_HEIGHT, (WIDTH-UI_WIDTH)/2 - PROJECT_WIDTH/2, HEIGHT/2-PROJECT_HEIGHT/2-25);
        u = new UI(UI_WIDTH, UI_HEIGHT, 0, 0, p);
        p.setUI(u);
        u.setCP((JColorChooser)colorPicker);

        ((Component) p).setFocusable(false);
        ((Component) u).setFocusable(false);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(u, BorderLayout.WEST);

        Image brush = new ImageIcon("assets/pencil.png").getImage();

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Component c = (Component) evt.getSource();
                p.setXY((getWidth() - UI_WIDTH) / 2 - PROJECT_WIDTH / 2, getHeight() / 2 - PROJECT_HEIGHT / 2 - 25);
            }
        });

        p.setCursor(getToolkit().createCustomCursor(
                brush,
                new Point(0, 31),
                "brush"));

        u.setCursor(getToolkit().createCustomCursor(
                new ImageIcon("assets/cursor.png").getImage(),
                new Point(0, 0),
                "cursor"));

        p.setBackground(new java.awt.Color(200, 200, 200));
        u.setBackground(new java.awt.Color(210, 210, 210));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(WIDTH, HEIGHT));

        kl = new KeyLis(this);
        this.addKeyListener(kl);

        // getContentPane().setLayout(new FlowLayout());
        setVisible(true);
    }

    public void keyHandle(KeyEvent e) {
        p.keyPress(e);
        u.keyPress(e);

    }

    public void keyHandleRelease(KeyEvent e) {
        p.keyRelease(e);
        u.keyRelease(e);
    }
}