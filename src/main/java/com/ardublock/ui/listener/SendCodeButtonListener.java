package com.ardublock.ui.listener;

import com.ardublock.core.Context;
import com.ardublock.translator.AutoFormat;
import com.ardublock.ui.OpenblocksFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendCodeButtonListener extends GenerateCodeButtonListener {

    public SendCodeButtonListener(JFrame frame, Context context) {
        super(frame, context);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String codeOut=generateCode();
        if(codeOut!=null)
        {
            AutoFormat formatter = new AutoFormat();
            if (context.isNeedAutoFormat)
            {
                codeOut = formatter.format(codeOut);
            }

            if (!context.isInArduino())
            {
                System.out.println(codeOut);
            }
            
            context.didSendCode(codeOut);
        }
    }
}
