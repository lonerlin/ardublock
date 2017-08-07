package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class DetachInterruptBlock extends TranslatorBlock {
    public DetachInterruptBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
       String interruptPin;
       TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
       interruptPin=translatorBlock.toCode();

        return codePrefix+"detachInterrupt(digitalPinToInterrupt("+interruptPin+"));"+codeSuffix;
    }
}
