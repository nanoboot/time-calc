package org.nanoboot.utils.timecalc.gui.progress;

import org.nanoboot.utils.timecalc.gui.common.Widget;
import org.nanoboot.utils.timecalc.utils.NumberFormats;
import org.nanoboot.utils.timecalc.utils.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ProgressCircle extends Widget {

    public ProgressCircle() {
        setPreferredSize(new Dimension(200, 200));
    }

    @Override
    public void paintComponent(Graphics g) {
        if (side == 0) {
            this.side = Math.min(getWidth(), getHeight());
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Utils.highlighted.get() || mouseOver ? Color.darkGray :
                FOREGROUND_COLOR);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        double angleDouble = donePercent * 360;
        double angleDouble2 = (angleDouble - (int) (angleDouble)) * 360;
        //        System.out.println("remainingAngle=" + angleDouble2);

        g2d.fillArc(0, 0, side, side, 90, -(int) angleDouble);
        int side2 = side / 2;
        g2d.setColor(Utils.highlighted.get() || mouseOver ?
                new Color(105, 175, 236) : FOREGROUND_COLOR2);
        g2d.fillArc(0 + (side2 / 2), 0 + (side2 / 2), side2, side2, 90,
                -(int) angleDouble2);
        g2d.setColor(Utils.highlighted.get() || mouseOver ? Color.blue :FOREGROUND_COLOR);

        g2d.drawString(
                NumberFormats.FORMATTER_ZERO_DECIMAL_PLACES.format(donePercent * 100) + "%", (int)(side / 8d * 0d),(int)(side / 8d * 7.5d));
    }

}