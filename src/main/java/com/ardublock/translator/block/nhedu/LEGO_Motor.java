package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEGO_Motor extends TranslatorBlock {
    public LEGO_Motor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {

        return "";
    }
    protected String buildCode(int motorIndex)throws SocketNullException, SubroutineNotDeclaredException, BlockException
    {
        String mName=String.valueOf(motorIndex);
        String ret="";
        translator.addHeaderFile("Encoder.h");
        translator.addHeaderFile("PID_v1.h");
        translator.addHeaderFile("BricktronicsMotor.h");
        translator.addHeaderFile("BricktronicsMegashield.h");
        translator.addDefinitionCommand("BricktronicsMotor m"+mName+"(BricktronicsMegashield::MOTOR_"+mName+");");
        translator.addSetupCommand("m"+mName+".begin();");

        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
        ret="m"+mName+".setFixedDrive("+translatorBlock.toCode()+");\n";
        return ret;
    }
}
