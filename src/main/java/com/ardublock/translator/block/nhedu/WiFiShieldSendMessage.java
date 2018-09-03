package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class WiFiShieldSendMessage extends TranslatorBlock {
    public WiFiShieldSendMessage(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String order,paraOne,paraTwo;

        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        order=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        paraOne=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        paraTwo=translatorBlock.toCode();
        return codePrefix+"wSerial.sendMessage( " + order+ "," + paraOne + "," +paraTwo +");"+codeSuffix;
    }
}
