package com.example;

import java.util.List;

import com.example.ZipFileEntries.ZipComponent;

/*
 * This interface specifies a contract for ZipFileComposite objects
 */
public interface Composite {

    void add(ZipComponent component);

    void remove(ZipComponent component);

    void removeAll();

    ZipComponent getChild(int index);

    List<ZipComponent> getComponents();

}