package org.nanoboot.utils.timecalc.swing.common;

import org.nanoboot.utils.timecalc.entity.Visibility;
import org.nanoboot.utils.timecalc.utils.property.StringProperty;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Robert Vokac
 * @since 20.02.2024
 */
public class Widget extends JPanel {
    protected static final Color FOREGROUND_COLOR = new Color(220, 220, 220);
    protected static final Color FOREGROUND_COLOR2 = new Color(210, 210, 210);
    protected static final Color BACKGROUND_COLOR = new Color(238, 238, 238);
    public StringProperty visibilityProperty =
            new StringProperty("widget.visibilityProperty",
                    Visibility.STRONGLY_COLORED.name());
    protected int side = 0;
    protected double donePercent = 0;
    protected boolean mouseOver = false;

    public Widget() {
        setBackground(BACKGROUND_COLOR);
        new Timer(getTimerDelay(), e -> repaint()).start();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Visibility visibility =
                        Visibility.valueOf(visibilityProperty.getValue());
                if (visibility.isStronglyColored()) {
                    visibilityProperty
                            .setValue(Visibility.WEAKLY_COLORED.name());
                } else {
                    visibilityProperty
                            .setValue(Visibility.STRONGLY_COLORED.name());
                }
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
        if (donePercent > 1) {
            donePercent = 1;
        }
        if (donePercent < 0) {
            donePercent = 0;
        }
        this.donePercent = donePercent;
    }

    public void setBounds(int x, int y, int side) {
        setBounds(x, y, side, side);
    }

    @Override
    public final void paintComponent(Graphics g) {
        super.paintComponent(g);

        Visibility visibility =
                Visibility.valueOf(visibilityProperty.getValue());
        this.setVisible(visibility != Visibility.NONE);
        paintWidget(g);

    }

    protected void paintWidget(Graphics g) {
    }
}