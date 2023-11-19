package com.example.AssignmentSpecificationPortal.Tests;


import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;


import com.example.TestCase;
import com.example.TestCaseManager;
import com.example.AssignmentSpecificationPortal.AttributeInformation;
import com.example.AssignmentSpecificationPortal.MethodInformation;
import com.example.AssignmentSpecificationPortal.ClassInformation;
import com.example.AssignmentSpecificationPortal.ClassesManager;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.MethodBasicTest;

public class NamingConventionTest extends BaseTest {

    public NamingConventionTest(String description) {
        super(); 
        this.testDescription.setText(description);
        this.testType = "name";
    }
}