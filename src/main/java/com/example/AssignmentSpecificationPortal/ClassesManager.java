package com.example.AssignmentSpecificationPortal;

import java.util.ArrayList;

public class ClassesManager {
    private static ArrayList<ClassInformation> classes = new ArrayList<>();

    public static ArrayList<ClassInformation> getClasses() {
        return classes;
    }

    public static ClassInformation getClass(int index) {
        return classes.get(index);
    }

    public static void addClass(ClassInformation classInfo) {
        classes.add(classInfo);
    }

    public static void removeClass(ClassInformation classInfo) {
        classes.remove(classInfo);
    }

    public static void removeClass(int index) {
        classes.remove(index);
    }

    public static void removeClasses(ArrayList<ClassInformation> classesToRemove) {
        classes.removeAll(classesToRemove);
    }

    public static void removeAllClasses() {
        classes.clear();
    }
}
