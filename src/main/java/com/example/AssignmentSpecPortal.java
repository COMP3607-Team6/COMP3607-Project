package com.example;

import javax.swing.*;
import java.awt.*;

class AssignmentSpecPortal extends JFrame {
    private JPanel section1;
    private JPanel section2;
    private JPanel section3;
    private Container contentPane;

    public AssignmentSpecPortal() {
        initFrame();

        createSection1();
    }

    private void createSection1() {
        section1 = new Section1(this);
        setContentPane(section1);
    }
    
    public void switchToSection1() {
        setContentPane(section1);
        section1.setVisible(true);
        section2.setVisible(false);   
    }

    private void createSection2() {
        section2 = new Section2(this);
        setContentPane(section2);
        revalidate(); // not the best way to do this but its what works for now
        repaint();
        // getContentPane().add(section2);
    }
    
    public void switchToSection2() {
        createSection2();
        section1.setVisible(false);
        section2.setVisible(true);
        // revalidate();
        // repaint();
    }

     private void createSection3(JList<String> classList) {
        section3 = new Section3(this,classList);
        setContentPane(section3);
        revalidate(); 
        repaint();
    }

    public void switchToSection3(JList<String> classList) {
        createSection3(classList);
        section2.setVisible(false);
        section3.setVisible(true);
        
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


