package org.example;

public interface FillablePanel {

    void display(Object obj);

    void clearAllFields();

    void enableAllFields();

    void disableAllFields();

    void resetAllButtons();

    void update(Object obj);

    void add();
}
