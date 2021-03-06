package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class EasyFPM_DeleteBlock extends TranslatorBlock {
    public EasyFPM_DeleteBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String fID;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        fID=translatorBlock.toCode();
        return codePrefix+"DeletePrint("+fID+")"+codeSuffix;
    }
}
