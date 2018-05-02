package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class L298NMotorControlModel_IinBlcok extends TranslatorBlock {
    public L298NMotorControlModel_IinBlcok(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {

        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        translator.addDefinitionCommand("#define  L298NIN1  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        translator.addDefinitionCommand("#define  L298NIN2  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        translator.addDefinitionCommand("#define  L298NIN3  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(3);
        translator.addDefinitionCommand("#define  L298NIN4  "+translatorBlock.toCode());
        return "";
    }
}
