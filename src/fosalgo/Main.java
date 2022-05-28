package fosalgo;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

    private JPanel jContentPane = null;
    private static Canvas canvas = null;

    public Main() throws HeadlessException {
        initialize();
    }

    private void initialize() {
        this.setSize(1000, 680);
        this.setContentPane(getJContentPane());
        this.setTitle("Drag and Drop, Scroll, Grid");
    }

    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getCanvas(), BorderLayout.CENTER);//
        }
        return jContentPane;
    }

    private Canvas getCanvas() {
        if (canvas == null) {
            canvas = new Canvas();

            Handler handler = new Handler(this, canvas);
            this.addKeyListener(handler);
            canvas.addMouseListener(handler);
            canvas.addMouseMotionListener(handler);
            canvas.addMouseWheelListener(handler);
        }
        return canvas;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main mainClass = new Main();
                mainClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainClass.setVisible(true);
                mainClass.setLocationRelativeTo(null);
            }
        });
    }

}
