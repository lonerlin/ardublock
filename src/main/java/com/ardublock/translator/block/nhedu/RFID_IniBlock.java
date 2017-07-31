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

        String PinMOSI;
        String PinRST;
        String ObjectName;
        String ret;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);

        PinMOSI=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        PinRST=translatorBlock.toCode();
        ObjectName="nhedu_RFID" ;
        translator.addHeaderFile("EasyRFID.h");
        translator.addDefinitionCommand("EasyRFID "+ ObjectName + "( " + PinMOSI +"," +PinRST + " );");
        ret="if(nhedu_RFID.checkCard())\n\t {";
        translatorBlock = getTranslatorBlockAtSocket(2);
        while (translatorBlock != null)
        {
            ret = ret + "\t"+translatorBlock.toCode();
            translatorBlock = translatorBlock.nextTranslatorBlock();
        }
        ret = ret + "\t nhedu_RFID.halt();\n}\n\n";
        return ret;

    }
}
