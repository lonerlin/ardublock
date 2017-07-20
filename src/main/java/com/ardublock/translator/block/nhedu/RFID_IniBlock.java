package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

/**
 * Created by Administrator on 2017/7/20.
 */
public class RFID_IniBlock extends TranslatorBlock {
    public RFID_IniBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix,String label) {
        super(blockId, translator, codePrefix, codeSuffix,label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String RFIDName;
        String PinMOSI;
        String PinRST;
        String ObjectName;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        RFIDName=translatorBlock.toCode().replace("\"","");
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        PinMOSI=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        PinRST=translatorBlock.toCode();
        ObjectName="nhedu_" + RFIDName;
        translator.addHeaderFile("EasyRFID.h");
        translator.addDefinitionCommand("EasyRFID "+ ObjectName + "( " + PinMOSI +"," +PinRST + " );");
        //translator.addSetupCommand(ObjectName+ ".init();");
        return "";
    }
}
