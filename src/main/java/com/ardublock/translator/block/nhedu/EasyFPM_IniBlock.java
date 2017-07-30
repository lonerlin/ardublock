package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class EasyFPM_IniBlock extends TranslatorBlock {
    public EasyFPM_IniBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
       translator.addHeaderFile("EasyFPM.h");
       translator.addDefinitionCommand("EasyFPM nhedu_efpm;");
       String sIn,sOut;
       TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
       sIn=translatorBlock.toCode();
       translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
       sOut=translatorBlock.toCode();
       translator.addDefinitionCommand("SoftwareSerial mySerial(" + sIn+"," +sOut+");");
       translator.addSetupCommand("Serial.begin(9600);");
       translator.addSetupCommand("mySerial.begin(57600);");

       return codePrefix+"nhedu_efpm.begin(&mySerial)"+codeSuffix;
    }
}
