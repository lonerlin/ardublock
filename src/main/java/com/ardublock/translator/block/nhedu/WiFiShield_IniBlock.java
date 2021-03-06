package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class WiFiShield_IniBlock extends TranslatorBlock {

    public WiFiShield_IniBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        translator.addHeaderFile("WiFiSerial.h");
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        String baud=translatorBlock.toCode();
        translator.addDefinitionCommand("WiFiSerial wSerial(" + baud + ");");
        translator.addSetupCommand("wSerial.Begin();");
        return codePrefix+" wSerial.SerialEvent();\n \t" +codeSuffix;
    }
}
