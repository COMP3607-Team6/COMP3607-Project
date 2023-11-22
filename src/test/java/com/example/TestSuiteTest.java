package com.example;
import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.BasicTest.AttributeBasicTest;
import com.example.BasicTest.ClassBasicTest;
import com.example.BasicTest.MethodBasicTest;
import com.example.BehaviourTests.AttributeTypeTest;
import com.example.BehaviourTests.AttributeValueTest;
import com.example.BehaviourTests.ClassTypeTest;
import com.example.BehaviourTests.MethodTypeTest;
import com.example.BehaviourTests.MethodValueTest;
import com.example.FileCopy.JavaFileCopier;
import com.example.HierarchyTests.SubClassTest;
import com.example.HierarchyTests.SubTypeTest;
import com.example.Constants;

public class TestSuiteTest {
    

    //Basic Test - Tests
    /**
    * This test ensures classBasicTest outputs a passing result when ideal parameters are passed into the constructor

    */
    @BeforeClass
    public static void copyTestFile(){
        System.out.println("run");

        JavaFileCopier.copyFiles(Constants.TEST_ASSIGNMENTS);
        System.out.println("copy");

        try {
            // Pause for 5 seconds
            Thread.sleep (5000);
        } catch (InterruptedException e) {
            // Handle the interruption
            e.printStackTrace ();
        }
    }
    
    @AfterClass
    public static void deleteTestFile(){

        Delete.deleteFilesInFolder(Constants.STUDENT_SUBMISSION_TESTING_FOLDER);

    }



    @Test
    public void  classBasicTest_GoodRequest()
    {

        ClassBasicTest c  = new ClassBasicTest(3,"Device","name");
        String s = c.test();
        System.out.println(s);
        assertTrue(c.testMarks.getTestPassed());
    }

    @Test
    /**
    * This test ensures classBasicTest outputs a passing result when ideal parameters are passed into the constructor

    */
    public void  classBasicTest_BadClassTypeRequest()
    {
        ClassBasicTest c  = new ClassBasicTest(3,"Device","final");
        String s = c.test();
        System.out.println(s);
        assertFalse(c.testMarks.getTestPassed());
    }

    @Test
    public void  classBasicTest_BadClassNameRequest()
    {
        ClassBasicTest c  = new ClassBasicTest(3,"device","final");
        String s = c.test();
        System.out.println(s);
        assertFalse(c.testMarks.getTestPassed());
    }

    @Test
    public void  methodBasicTest_GoodRequest()
    {
        MethodBasicTest m = new MethodBasicTest(1,"AC","coolsBy","public");
        System.out.println(m.test());
        assertTrue(m.testMarks.getTestPassed());
    }
    @Test
    public void  methodBasicTest_BadMethodTypeRequest()
    {
       MethodBasicTest m = new MethodBasicTest(1,"AC","coolsBy","private");
        System.out.println(m.test());
        assertFalse(m.testMarks.getTestPassed());
    }

    @Test
    public void  methodBasicTest_BadMethodNameRequest()
    {
       MethodBasicTest m = new MethodBasicTest(1,"AC","coolingBy","private");
        System.out.println(m.test());
        assertFalse(m.testMarks.getTestPassed());
    }

    @Test
    public void  attributeBasicTest_GoodRequest()
    {
        AttributeBasicTest a = new AttributeBasicTest(1,"Fan", "id","protected");
        System.out.println(a.test());
        assertTrue(a.testMarks.getTestPassed());
    }

    @Test
    public void  attributeBasicTest_BadAttributeTypeRequest()
    {
       AttributeBasicTest a = new AttributeBasicTest(1,"Fan", "id","static");
        System.out.println(a.test());
        assertFalse(a.testMarks.getTestPassed());
    }

    @Test
    public void  attributeBasicTest_BadAttributeNameRequest()
    {
       AttributeBasicTest a = new AttributeBasicTest(1,"Fan", "i","static");
        System.out.println(a.test());
        assertFalse(a.testMarks.getTestPassed());
    }

    //HierarchyTest - Tests
    @Test
    public void  subTypeTest_GoodRequest()
    {
       SubTypeTest t = new SubTypeTest("AC", "Device",1);
        
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }

    @Test
    public void  subTypeTest_BadRequest()
    {
       SubTypeTest t = new SubTypeTest("AC", "Fan",1);
        
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    @Test
    public void  subTypeTest_BadSubclassClassRequest()
    {
       SubTypeTest t = new SubTypeTest("Breeze", "Device",1);
        
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    @Test
    public void  subTypeTest_BadSuperclassClassRequest()
    {
       SubTypeTest t = new SubTypeTest("AC", "Apples",1);
        
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }


    
    public void  subClassTest_GoodRequest()
    {
        SubClassTest t = new SubClassTest("StandingFan", "Fan",1);
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }
    @Test
    public void  subClassTest_BadRequest()
    {
       SubClassTest t = new SubClassTest("StandingFan", "CeilingFan",1);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }
    @Test
    public void  subClassTest_BadSubclassClassRequest()
    {
       SubClassTest t = new SubClassTest("SittingFan", "Fan",1);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }
    @Test
    public void  subClassTest_BadSuperclassClassRequest()
    {
      SubClassTest t = new SubClassTest("StandingFan", "C_Fan",1);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    //Behaviour Test - Tests

    @Test
    public void  attributeTypeTest_GoodRequest()
    {
        TestCase t = new AttributeTypeTest(1, "AC", "coolBy", "int" );
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }

    @Test
    public void  attributeTypeTest_BadRequest()
    {
        TestCase t = new AttributeTypeTest(1, "AC", "notcoolBy", "int" );
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    @Test
    public void  attributeTypeTest_BadClassNameRequest()
    {
        TestCase t = new AttributeTypeTest(1, "A", "coolBy", "int" );
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }


    public void  attributeValueTest_GoodRequest()
    {
        TestCase t = new AttributeValueTest("coolBy", "AC",1, 5);
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }

    @Test
    public void  attributeValueTest_BadRequest()
    {
        TestCase t = new AttributeValueTest("coolBy", "AC",1, 8);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    @Test
    public void  attributeValueTest_BadClassNameRequest()
    {
        TestCase t = new AttributeValueTest("coolBy", "A",1, 5);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    @Test
    public void  attributeValueTest_BadAttributeNameRequest()
    {
        TestCase t = new AttributeValueTest("coolByes", "AC",1, 5);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }
    
    //ClassTypeTest - Test
    @Test
    public void  classTypeTest_GoodRequest()
    {
        ClassTypeTest t = new ClassTypeTest("Fan", 1, "abstract");
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }

    @Test
    public void  classTypeTest_BadRequest()
    {
        ClassTypeTest t = new ClassTypeTest("Fan", 1, "interface");
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    @Test
    public void  classTypeTest_BadClassNameRequest()
    {
        ClassTypeTest t = new ClassTypeTest("C-Fan", 1, "abstract");
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }
    

    //MethodTypeTest - Test
    @Test
    public void methodTypeTest_GoodRequest(){
        TestCase t = new MethodTypeTest(1, "AC", "coolsBy", "int" );
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }

    @Test
    public void methodTypeTest_BadRequest(){
        TestCase t = new MethodTypeTest(1, "AC", "coolsBy", "float" );
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());

    }

     @Test
    public void methodTypeTest_BadClassNameRequest(){
        TestCase t = new MethodTypeTest(1, "A", "coolsBy", "float" );
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());

    }

     @Test
    public void methodTypeTest_BadMethodNameRequest(){
        TestCase t = new MethodTypeTest(1, "AC", "coolerBy", "float" );
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    //MethodValueTest
     public void methodValueTest_GoodRequest(){
        ArrayList<Object> paras = new ArrayList<>();
        paras.add(5);
        paras.add("{}");

        TestCase t = new MethodValueTest("coolsBy", "AC",1,paras, 13);
        System.out.println(t.test());
        assertTrue(t.testMarks.getTestPassed());
    }

    public void methodValueTest_BadRequest(){
        ArrayList<Object> paras = new ArrayList<>();
        paras.add(5);
        paras.add("{}");

        TestCase t = new MethodValueTest("coolsBy", "AC",1,paras, -9);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    public void methodValueTest_BadClassNameRequest(){
        ArrayList<Object> paras = new ArrayList<>();
        paras.add(5);
        paras.add("{}");

        TestCase t = new MethodValueTest("coolsBy", "A",1,paras, 13);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }

    public void methodValueTest_BadMethodNameRequest(){
        ArrayList<Object> paras = new ArrayList<>();
        paras.add(5);
        paras.add("{}");

        TestCase t = new MethodValueTest("coolerBy", "AC",1,paras, 13);
        System.out.println(t.test());
        assertFalse(t.testMarks.getTestPassed());
    }




} 