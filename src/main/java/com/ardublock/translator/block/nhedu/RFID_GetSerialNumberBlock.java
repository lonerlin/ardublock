package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

/**
 * Created by Administrator on 2017/7/20.
 */
public class RFID_GetSerialNumberBlock extends TranslatorBlock {
    public RFID_GetSerialNumberBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String RFIDName;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        RFIDName=translatorBlock.toCode().replace("\"","");
        String ret="nhedu_"+RFIDName+".getSerialNumber()";
        return codePrefix+ret+codeSuffix;
    }
}
