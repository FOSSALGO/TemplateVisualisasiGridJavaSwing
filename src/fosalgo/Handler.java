package fosalgo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;

public class Handler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    JFrame jFrame;
    Canvas canvas;

    //------------
    private int lastOffsetX;
    private int lastOffsetY;

    //SCALE
    final double MIN_SCALE = 0.1;
    final double MAX_SCALE = 20;

    public Handler(JFrame jFrame, Canvas canvas) {
        this.jFrame = jFrame;
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x1 = e.getX() - canvas.translateX;
        double y1 = e.getY() - canvas.translateY;
        
        double scale = canvas.scale;
        double cellSizeScale = canvas.cellSize * scale;
        double x = Math.floor(x1/cellSizeScale);        
        double y = Math.floor(y1/cellSizeScale);
        
        int i = (int)y;
        int j = (int)x;
        
        if(i>=0&&i<canvas.labyrinth.length&&j>=0&&j<canvas.labyrinth[0].length){
            if(canvas.labyrinth[i][j] == 0){
                canvas.labyrinth[i][j] = -1;
            }else{
                canvas.labyrinth[i][j] = 0;
            }            
        }
        
        canvas.repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // capture starting point
        lastOffsetX = e.getX();
        lastOffsetY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // new x and y are defined by current mouse location subtracted
        // by previously processed mouse location
        int newX = e.getX() - lastOffsetX;
        int newY = e.getY() - lastOffsetY;

        // increment last offset to last processed by drag event.
        lastOffsetX += newX;
        lastOffsetY += newY;

        // update the canvas locations
        canvas.translateX += newX;
        canvas.translateY += newY;

        // schedule a repaint.
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            if (canvas.scale >= MIN_SCALE && canvas.scale <= MAX_SCALE) {
                double oldScale = canvas.scale;

                double newScale = (0.1 * e.getWheelRotation());
                newScale = canvas.scale + newScale;
                newScale = Math.max(0.00001, newScale);

                //System.out.println("SCALE: " + canvas.scale);
                if (newScale < MIN_SCALE) {
                    newScale = MIN_SCALE;
                } else if (newScale > MAX_SCALE) {
                    newScale = MAX_SCALE;
                }

                double x1 = e.getX() - canvas.translateX;
                double y1 = e.getY() - canvas.translateY;

                double x2 = (x1 * newScale) / oldScale;
                double y2 = (y1 * newScale) / oldScale;

                double newTranslateX = canvas.translateX + x1 - x2;
                double newTranslateY = canvas.translateY + y1 - y2;

                //set new scale and translate
                canvas.translateX = newTranslateX;
                canvas.translateY = newTranslateY;
                canvas.scale = newScale;

                canvas.repaint();
            }
        }
    }

}
