package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ADXL335Block extends TranslatorBlock {
    public ADXL335Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {

        String pinX;
        String pinY;
        String pinZ;
        translator.addHeaderFile("ADXL335.h");
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        pinX=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        pinY=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        pinZ=translatorBlock.toCode();
        translator.addDefinitionCommand("ADXL335 nhedu_adxl335("+pinX+","+pinY+","+pinZ+",5);");
        return "";
    }

}
