package org.nanoboot.utils.timecalc.swing.common;

import java.awt.BasicStroke;
import org.nanoboot.utils.timecalc.app.GetProperty;
import org.nanoboot.utils.timecalc.app.TimeCalcProperty;
import org.nanoboot.utils.timecalc.entity.Visibility;
import org.nanoboot.utils.timecalc.swing.progress.ProgressSmileyIcon;
import org.nanoboot.utils.timecalc.utils.common.ProgressSmiley;
import org.nanoboot.utils.timecalc.utils.property.BooleanProperty;
import org.nanoboot.utils.timecalc.utils.property.Property;
import org.nanoboot.utils.timecalc.utils.property.StringProperty;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author Robert Vokac
 * @since 20.02.2024
 */
public class Widget extends JPanel implements
        GetProperty {

    private static final int CLOSE_BUTTON_SIDE = 25;
    protected static final Color FOREGROUND_COLOR = new Color(220, 220, 220);
    protected static final Color FOREGROUND_COLOR2 = new Color(210, 210, 210);
    protected static final Color BACKGROUND_COLOR = new Color(238, 238, 238);
    protected static final Font BIG_FONT = new Font("sans", Font.BOLD, 24);
    protected static final Font MEDIUM_FONT = new Font("sans", Font.BOLD, 16);
    protected static final String HEAD = " () ";
    protected static final String BODY = "/||\\";
    protected static final String LEGS = " /\\ ";
    public static final Color CLOSE_BUTTON_FOREGROUND_COLOR
            = new Color(127, 127, 127);
    public static final Color CLOSE_BUTTON_BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static final Color CLOSE_BUTTON_BACKGROUND_COLOR_MOUSE_OVER_CLOSE_ICON = new Color(255, 153, 153);
    public final BooleanProperty visibilitySupportedColoredProperty
            = new BooleanProperty("visibilitySupportedColoredProperty", true);
    public final BooleanProperty visibleProperty
            = new BooleanProperty("visibleProperty", true);
    public final BooleanProperty smileysVisibleProperty
            = new BooleanProperty(TimeCalcProperty.SMILEYS_VISIBLE.getKey());
    public final BooleanProperty smileysVisibleOnlyIfMouseMovingOverProperty
            = new BooleanProperty(
                    TimeCalcProperty.SMILEYS_VISIBLE_ONLY_IF_MOUSE_MOVING_OVER
                            .getKey());
    public final BooleanProperty smileysColoredProperty
            = new BooleanProperty("smileysColoredProperty", true);
    public StringProperty visibilityProperty
            = new StringProperty("widget.visibilityProperty",
                    Visibility.STRONGLY_COLORED.name());
    protected int side = 0;
    protected double donePercent = 0;
    protected boolean mouseOver = false;
    private boolean mouseOverCloseButton = false;
    protected JLabel smileyIcon;
    private long lastUpdate = System.nanoTime();

    public Widget() {
        setBackground(BACKGROUND_COLOR);
        new Timer(getTimerDelay(), e -> repaint()).start();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();
                mouseOverCloseButton = x >= getWidth() - CLOSE_BUTTON_SIDE
                        && y <= CLOSE_BUTTON_SIDE;
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (mouseOverCloseButton) {
                    visibleProperty.setValue(false);
                    return;
                }

                if (visibilitySupportedColoredProperty.isDisabled()) {
                    //nothing to do
                    return;
                }
                if (visibleProperty.isEnabled()) {
                    Visibility visibility
                            = Visibility.valueOf(visibilityProperty.getValue());
                    if (visibility.isStronglyColored()) {
                        visibilityProperty
                                .setValue(Visibility.WEAKLY_COLORED.name());
                    } else {
                        visibilityProperty
                                .setValue(Visibility.STRONGLY_COLORED.name());
                    }
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

    //    @Override
    //    public void setVisible(boolean aFlag) {
    //        if(visibleProperty.isEnabled() && !aFlag) {
    //            super.setVisible(false);
    //        }
    //        if(visibleProperty.isDisabled() && aFlag) {
    //            super.setVisible(false);
    //        }
    //
    //    }
    public final void setDonePercent(double newDonePercent) {
        if (newDonePercent > 1) {
            newDonePercent = 1;
        }
        if (newDonePercent < 0) {
            newDonePercent = 0;
        }
        double oldDonePercent = this.donePercent;
        int oldDonePercentInt1000Mil = (int) (oldDonePercent * 1000000000);
        this.donePercent = newDonePercent;
        int newDonePercentInt1000Mil = (int) (newDonePercent * 1000000000);
        if (newDonePercentInt1000Mil != oldDonePercentInt1000Mil) {
            lastUpdate = System.nanoTime();
        }
    }

    public void setBounds(int x, int y, int side) {
        setBounds(x, y, side, side);
    }

    @Override
    public final void paintComponent(Graphics brush) {
        super.paintComponent(brush);

        setVisible(visibleProperty.isEnabled());

        if (visibleProperty.isDisabled()) {
            //nothing to do
            return;
        }
        Visibility visibility
                = Visibility.valueOf(visibilityProperty.getValue());
        super.setVisible(
                visibility != Visibility.NONE && visibleProperty.isEnabled());
        paintWidget(brush);

        paintCloseIcon(brush, getWidth(), mouseOver, mouseOverCloseButton);

    }

    private static void paintCloseIcon(Graphics brush, int width,
            boolean mouseOver, boolean mouseOverCloseButton) {

        if (!mouseOver) {
            //nothing to do
            return;
        }
//        if(!mouseOverCloseButton) {
//            //nothing to do
//            return;
//        }

        brush.setColor(mouseOverCloseButton ? CLOSE_BUTTON_BACKGROUND_COLOR_MOUSE_OVER_CLOSE_ICON : CLOSE_BUTTON_BACKGROUND_COLOR);

//        if(!mouseOverCloseButton) {
//            brush.drawRect(width - CLOSE_BUTTON_SIDE - 1, 0 + 1, CLOSE_BUTTON_SIDE,
//                    CLOSE_BUTTON_SIDE);
//            brush.drawRect(width - CLOSE_BUTTON_SIDE - 1+1, 0 + 1 +1, CLOSE_BUTTON_SIDE - 2,
//                    CLOSE_BUTTON_SIDE - 2);
//            return;
//        }
        brush.fillOval(width - CLOSE_BUTTON_SIDE - 1, 0 + 1, CLOSE_BUTTON_SIDE,
                CLOSE_BUTTON_SIDE);
        brush.setColor(CLOSE_BUTTON_FOREGROUND_COLOR);
        Graphics2D brush2d = (Graphics2D) brush;
        brush2d.setStroke(new BasicStroke(2f));
        int offset = 6;
        brush.drawLine(width - CLOSE_BUTTON_SIDE - 1 + offset, 0 + 1 + offset,
                width - 0 * CLOSE_BUTTON_SIDE - 1 - offset,
                0 + CLOSE_BUTTON_SIDE + 1 - offset);
        brush.drawLine(width - CLOSE_BUTTON_SIDE - 1 + offset,
                0 + CLOSE_BUTTON_SIDE + 1 - offset,
                width - 0 * CLOSE_BUTTON_SIDE - 1 - offset, 0 + 1 + offset);
    }

    protected void paintWidget(Graphics g) {
    }

    @Override
    public Property getVisibilityProperty() {
        return visibilityProperty;
    }

    @Override
    public Property getVisibilitySupportedColoredProperty() {
        return visibilitySupportedColoredProperty;
    }
    protected void paintSmiley(Visibility visibility, Graphics2D brush, int x,
            int y) {
        paintSmiley(visibility, brush, x, y, false);
    }
    protected void paintSmiley(Visibility visibility, Graphics2D brush, int x,
            int y, boolean paintBody) {
        if (!shouldBeSmileyPainted()) {
            if (this.smileyIcon != null) {
                this.remove(smileyIcon);
                this.smileyIcon = null;
            }

            //nothing more to do
            return;
        }
        boolean colored = smileysColoredProperty.isEnabled();
        if (visibility.isGray()) {
            colored = false;
        }


        if (!colored) {
            y = y - 2;
            if (this.smileyIcon != null) {
                this.remove(smileyIcon);
                this.smileyIcon = null;
            }
            if (!visibility.isStronglyColored()) {
                brush.setColor(Color.GRAY);
            }
            if (visibility.isGray()) {
                brush.setColor(Color.LIGHT_GRAY);
            }
            if (visibility.isStronglyColored()) {
                brush.setColor(Color.BLACK);
            }
            Color currentColor = brush.getColor();
            Font currentFont = brush.getFont();
            brush.setColor(visibility.isStronglyColored() ? Color.WHITE
                    : BACKGROUND_COLOR);
            brush.fillRect(
                    x, y,
                    20,
                    20
            );
            brush.setColor(currentColor);
            brush.setFont(MEDIUM_FONT);
            brush.drawString(
                    ProgressSmiley.forProgress(donePercent).getCharacter(),
                    x + 1, y + 16
            );
            brush.setFont(currentFont);
        }
        if (colored) {
            x = x + 2;
            ImageIcon imageIcon = ProgressSmileyIcon
                    .forSmiley(ProgressSmiley.forProgress(donePercent))
                    .getIcon();
            if (this.smileyIcon != null) {
                this.remove(smileyIcon);
                this.smileyIcon = null;
            }
            this.smileyIcon = new JLabel(imageIcon);
            smileyIcon.setBounds(x, y, 15, 15);
            this.add(smileyIcon);
        }
        if(colored) {
            x = x - 2;
            y = y - 2;
        }
        if(paintBody) {
            brush.drawString(BODY, x - 5, y + 26);
            brush.drawString(LEGS, x - 5, y + 36);
        }
    }

    protected boolean shouldBeSmileyPainted() {
        return smileysVisibleProperty.isEnabled() && (mouseOver
                                                       || !smileysVisibleOnlyIfMouseMovingOverProperty
                                                               .isEnabled());
    }

    protected boolean changedInTheLastXMilliseconds(int milliseconds) {
        return (System.nanoTime() - lastUpdate) < milliseconds * 1000000;
    }
}
