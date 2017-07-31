package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

/**
 * Created by Administrator on 2017/7/20.
 */
public class RFID_ReadCardBlock extends TranslatorBlock {
    public RFID_ReadCardBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String RFIDName;
        String SectionIndex;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);

        SectionIndex=translatorBlock.toCode();

        String ret="nhedu_RFID.readCard(" + SectionIndex + ")";

        return codePrefix+ ret+ codeSuffix;
    }
}
