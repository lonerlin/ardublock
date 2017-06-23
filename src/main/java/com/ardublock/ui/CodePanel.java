package com.ardublock.ui;

import javax.swing.*;

/**
 * Add a new textArea to show Code
 * Created by Lin on 2017/6/23 0023.
 */
public class CodePanel extends JPanel {
    private JTextArea textArea;
    public CodePanel(){
        textArea=new JTextArea("Arduino code");
        this.add(textArea);
    }
}
