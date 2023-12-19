package src.UIComponent;
import java.lang.Exception;
import java.util.ArrayList;

public class lineCreator implements Runnable {
    private int xf;
    private int yf;
    private int yt;
    private int xt;
    private ArrayList<int[]> pos;
    private static final double THRESH = 2;
    private int size;

    public lineCreator(int xf, int yf, int xt, int yt, ArrayList<int[]> p, int size) {
        this.xf = xf;
        this.yf = yf;
        this.xt = xt;
        this.yt = yt;
        this.size = size;
        pos = p;
        new Thread(this).start();
    }

    public void run() {
        try {
            double xc = xf;
            double yc = yf;
            double dx = ((double)(xf-xt))/(size/THRESH);
            double dy = ((double)(yf-yt))/(size/THRESH);
            for (int i=0;i<size/THRESH;i++) {
                pos.add(new int[]{(int)Math.round(xc), (int)Math.round(yc)});
                xc -= dx;
                yc -= dy;
            }
        } catch(Exception e) {
            System.out.println("Line drawing error");
        }
    }
}
