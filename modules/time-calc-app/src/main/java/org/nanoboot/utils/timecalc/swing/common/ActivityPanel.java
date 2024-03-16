package org.nanoboot.utils.timecalc.swing.common;

import org.nanoboot.utils.timecalc.entity.Activity;
import org.nanoboot.utils.timecalc.persistence.api.ActivityRepositoryApi;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Robert
 * @since 13.03.2024
 */
public class ActivityPanel extends JPanel {

    private final ActivityRepositoryApi activityRepository;
    private final Activity activity;
    private TTextField name = new TTextField("");
    private TTextField comment = new TTextField("");
    private TTextField ticket = new TTextField("");
    private TTextField spentTime = new TTextField("00:00");

    private TTextField flags = new TTextField("Flags");
    private TTextField subject = new TTextField("");
    private TTextField totalComment = new TTextField("");
    private TTextField today = new TTextField("00:00");
    private TTextField remains = new TTextField("00:00");

    public ActivityPanel(ActivityRepositoryApi activityRepository,
            Activity activity) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.activity = activity;

        add(name);
        add(comment);
        add(ticket);
        add(spentTime);

        add(flags);
        add(subject);
        add(totalComment);
        add(today);
        add(remains);

        name.setPreferredSize(new Dimension(200, 40));
        comment.setPreferredSize(new Dimension(200, 40));
        ticket.setPreferredSize(new Dimension(80, 40));
        spentTime.setPreferredSize(new Dimension(80, 40));

        flags.setPreferredSize(new Dimension(100, 40));
        subject.setPreferredSize(new Dimension(100, 40));
        totalComment.setPreferredSize(new Dimension(100, 40));
        today.setPreferredSize(new Dimension(80, 40));
        remains.setPreferredSize(new Dimension(80, 40));

        this.setPreferredSize(new Dimension(getWidth(), 40));

        name.setEditable(false);
        comment.setEditable(false);
        ticket.setEditable(false);
        spentTime.setEditable(false);

        flags.setEditable(false);
        subject.setEditable(false);
        totalComment.setEditable(false);
        today.setEditable(false);
        remains.setEditable(false);

        name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        comment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ticket.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        spentTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        flags.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        subject.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        totalComment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        today.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        remains.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        name.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String result = (String) JOptionPane.showInputDialog(
                        null,
                        "Select new name",
                        "New name",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        name.getText()
                );
                if (result != null) {
                    activity.setName(result);
                    activityRepository.update(activity);
                    name.setText(result);
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

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        name.setText(activity.getName());
        comment.setText(activity.getComment());
        ticket.setText(activity.getTicket());
        spentTime.setText((activity.getSpentHours() < 10 ? "0" : "") + activity
                .getSpentHours() + ":" + (activity.getSpentMinutes() < 10 ? "0" :
                "") + activity.getSpentMinutes());
        flags.setText(activity.getFlags());
        this.activityRepository = activityRepository;
        //this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        setAlignmentX(LEFT_ALIGNMENT);

    }
}
