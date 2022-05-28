package fosalgo;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Canvas extends JPanel {

    //==========================================================================
    //labyrinth
    int[][] labyrinth = {
        {0, -1, 0, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, -1, -1, -1, 0, 0, 0, -1},
        {0, -1, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, -1, 0, -1},
        {0, -1, 0, -1, 0, -1, 0, -1, 0, 0, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
        {0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, 0, 0, -1, 0, 0},
        {0, -1, 0, 0, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
        {0, -1, 0, -1, -1, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1},
        {0, -1, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, -1},
        {0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, -1, -1, 0, -1, 0, -1, 0, -1},
        {0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1},
        {0, -1, 0, -1, 0, 0, 0, -1, 0, -1, 0, -1, 0, 0, 0, -1, 0, -1, 0, 0},
        {0, 0, 0, -1, 0, -1, -1, -1, 0, -1, 0, -1, -1, -1, -1, -1, 0, -1, 0, -1},
        {0, 0, 0, -1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1}
    };

    int cellSize = 40;
    //==========================================================================

    double translateX;
    double translateY;
    double scale;

    Canvas() {
        translateX = 0;
        translateY = 0;
        scale = 1;
        setOpaque(false);
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics g) {
        //----------------------------------------------------------------------
        AffineTransform tx = new AffineTransform();
        tx.translate(translateX, translateY);
        tx.scale(scale, scale);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.9F));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setTransform(tx);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //----------------------------------------------------------------------
        //g2d.setColor(Color.ORANGE);
        //g2d.fillRect(0, 0, 100, 60);
        //----------------------------------------------------------------------
        //gambar cel
        if (labyrinth != null) {
            int numRows = labyrinth.length;
            int numCols = labyrinth[0].length;

            for (int i = 0; i < labyrinth.length; i++) {
                for (int j = 0; j < labyrinth[0].length; j++) {
                    int value = labyrinth[i][j];
                    if (value == -1) {
                        g2d.setColor(Color.decode("#34495e"));
                    } else if (value == 0) {
                        g2d.setColor(Color.decode("#ecf0f1"));
                    }
                    g2d.fillRect(j*cellSize, i*cellSize, cellSize, cellSize);
                    g2d.setColor(Color.decode("#95a5a6"));
                    g2d.setStroke(new BasicStroke(1));
                    g2d.drawRect(j*cellSize, i*cellSize, cellSize, cellSize);
                }
            }

        }

        //----------------------------------------------------------------------
        g2d.dispose();
    }//end of paint

}
