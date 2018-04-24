package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CCStepperOneMotor extends TranslatorBlock {
    public CCStepperOneMotor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String en,dir,stp,value;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        en=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        dir=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        stp=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(3);
        value=translatorBlock.toCode();
        translator.addDefinitionCommand("void setStepperDirectionOneMotor(int en,int dir,int stp,int direction)\n" +
                "{\n" +
                "  pinMode(en,OUTPUT);\n" +
                "  pinMode(dir,OUTPUT);\n" +
                "  digitalWrite(en,HIGH);\n" +
                "  if(direction>0)\n" +
                "  {\n" +
                "      digitalWrite(dir,HIGH);\n" +
                "      analogWrite(stp,128); \n" +
                "   }\n" +
                "   else if(direction<0)\n" +
                "   {\n" +
                "      digitalWrite(dir,LOW);\n" +
                "      analogWrite(stp,128); \n" +
                "   }\n" +
                "   else\n" +
                "   {\n" +
                "      analogWrite(stp,0);\n" +
                "   }\n" +
                "   \n" +
                "}\n");
        String ret=" setStepperDirectionOneMotor("+en+","+dir+","+stp+","+value+");\n";
        return codePrefix+ret+codeSuffix;
    }
}
