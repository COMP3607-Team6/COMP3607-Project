package com.example.AssignmentSpecificationPortal;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Section4 extends JPanel {
    private CardLayout cardLayout;
    private JButton backButton;
    private JButton nextButton;

    public Section4(CardLayout layout) {
        cardLayout = layout;
        backButton = new JButton("Back");
        nextButton = new JButton("Next");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                layout.previous(getParent());
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(getParent());
            }
        });

        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(backButton);
        add(nextButton);
    }
}

