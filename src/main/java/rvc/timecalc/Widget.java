package rvc.timecalc;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Robert
 * @since 20.02.2024
 */
public class Widget extends JPanel {
    protected int side = 0;
    protected double donePercent = 0;
    protected boolean mouseOver = false;
    protected static final Color FOREGROUND_COLOR = new Color(220, 220, 220);
    protected static final Color FOREGROUND_COLOR2 = new Color(210, 210, 210);
    protected static final Color BACKGROUND_COLOR = new Color(238, 238, 238);
    public Widget() {
        setBackground(BACKGROUND_COLOR);
        new Timer(getTimerDelay(), e -> repaint()).start();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.highlighted.flip();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
            }
        });
    }
    public int getTimerDelay() {
        return 100;
    }
    public final void setDonePercent(double donePercent) {
        this.donePercent = donePercent;
    }

}
