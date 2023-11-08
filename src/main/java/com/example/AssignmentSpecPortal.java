package com.example;

import javax.swing.*;
import java.awt.*;

class AssignmentSpecPortal extends JFrame {
    private JPanel section1;
    private JPanel section2;
    private Container contentPane;

    public AssignmentSpecPortal() {
        initFrame();

        createSection1();
    }

    private void createSection1() {
        section1 = new Section1(this);
        setContentPane(section1);
    }

    public void switchToSection2() {
        createSection2();
        section1.setVisible(false);
        section2.setVisible(true);
        // revalidate();
        // repaint();
    }

    
    private void createSection2() {
        section2 = new Section2(this);
        // getContentPane().add(section2);
        setContentPane(section2);
        revalidate(); // not the best way to do this but its what works for now
        repaint();
    }

    private void initFrame() {
        setTitle("Assignment Specification Portal");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = getContentPane();
        contentPane.setLayout(null);
    }

    public static void main(String[] args) {
        AssignmentSpecPortal assignmentSpecPortal = new AssignmentSpecPortal();
        assignmentSpecPortal.setVisible(true);
    }
}


