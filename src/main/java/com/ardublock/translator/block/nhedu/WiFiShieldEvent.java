package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class WiFiShieldEvent extends TranslatorBlock {
    public WiFiShieldEvent(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String ret="void WiFiMessageEvent(String order,int paraOne,int paraTwo){\n";
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        while (translatorBlock != null)
        {
            ret = ret + "\t"+translatorBlock.toCode();
            translatorBlock = translatorBlock.nextTranslatorBlock();
        }
        ret=ret+"\n } \n";
        translator.addDefinitionCommand(ret);
        translator.addSetupCommand("wSerial.setMessageEvent(WiFiMessageEvent);");
        return null;
    }
}
