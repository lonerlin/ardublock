package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CCStepperDirection extends TranslatorBlock {
    public CCStepperDirection(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String stepperDirection;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        stepperDirection=translatorBlock.toCode();
        String ret="setSteppersDirection("+stepperDirection+");\n";
        return codePrefix+ret+codeSuffix;

    }
}
