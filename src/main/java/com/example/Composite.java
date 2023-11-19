package com.example;

import java.util.List;

public interface Composite {

    // @Override
    void add(ZipComponent component);

    // @Override
    void remove(ZipComponent component);

    void removeAll();

    // @Override
    ZipComponent getChild(int index);

    List<ZipComponent> getComponents();

}