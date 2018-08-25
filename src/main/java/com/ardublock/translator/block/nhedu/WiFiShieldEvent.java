package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.SubroutineBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class WiFiShieldEvent extends TranslatorBlock {
    public WiFiShieldEvent(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException
    {
        translator.addSetupCommand("wSerial.setMessageEvent(WiFiMessageEvent);\n");
        String ret;
        ret="void WiFiMessageEvent(String order,int paraOne,int paraTwo){\n";
        TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
        while (translatorBlock != null)
        {
            ret = ret + translatorBlock.toCode();
            translatorBlock = translatorBlock.nextTranslatorBlock();
        }
        ret = ret + "}\n\n";
        translator.addDefinitionCommand(ret);
        return "";
    }
}
