package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class IRSendBlock extends TranslatorBlock {
    public IRSendBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {

        String decoudeType;
        String bitLength;
        String sendValue;
        translator.addHeaderFile("IRremote.h");
        translator.addDefinitionCommand("IRsend irsend;");
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        decoudeType=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        sendValue=translatorBlock.toCode().replace("UL","");
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(3);
        bitLength=translatorBlock.toCode();
        return codePrefix+ "irsend.send"+decoudeType+"("+sendValue+","+bitLength+");"+codeSuffix;
    }
}
