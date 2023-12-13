package src.WindowComponent;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
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

import src.UIComponent.imageExport;
import src.UIComponent.keyLis;

public class Window extends JFrame {

    public static final int PROJECT_WIDTH = 500;
    public static final int PROJECT_HEIGHT = 400;

    public static final int UI_WIDTH = 200;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final int UI_HEIGHT = HEIGHT;

    private keyLis kl;
    private paint p;
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

        menu.add(save);
        menu.add(open);
        menubar.add(menu);
        setJMenuBar(menubar);

        p = new paint(PROJECT_WIDTH, PROJECT_HEIGHT, (WIDTH - UI_WIDTH) / 2 - PROJECT_WIDTH / 2,
                HEIGHT / 2 - PROJECT_HEIGHT / 2 - 25);
        u = new UI(UI_WIDTH, UI_HEIGHT, 0, 0, p);
        p.setUI(u);

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

        kl = new keyLis(this);
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