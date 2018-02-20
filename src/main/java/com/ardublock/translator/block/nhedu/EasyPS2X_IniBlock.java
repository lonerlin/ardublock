package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class EasyPS2X_IniBlock extends TranslatorBlock {
    public EasyPS2X_IniBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        translator.addHeaderFile("EasyPS2X.h");
        translator.addDefinitionCommand("EasyPS2X eps;");
        String clk,cmd,sel,dat;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        clk=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        cmd=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        sel=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(3);
        dat=translatorBlock.toCode();
        translator.addSetupCommand("eps.config_gamepad("+clk+","+cmd+","+sel+","+dat+");");

        return codePrefix+" eps.update();"+codeSuffix;
    }
}
