package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

/**
 * Created by Administrator on 2017/7/20.
 */
public class RFID_WriteCardBlock extends TranslatorBlock {
    public RFID_WriteCardBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {

        String SectionIndex;
        String BlockIndex;
        String WriteData;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);

        SectionIndex=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        BlockIndex=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        WriteData=translatorBlock.toCode();
        String ret="nhedu_RFID.writeCard(" + SectionIndex + "," + BlockIndex + ","+ WriteData + ")";
        return codePrefix+ret+codeSuffix;
    }
}
