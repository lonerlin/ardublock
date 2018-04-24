package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class CCStepperIniBlock extends TranslatorBlock {
    public CCStepperIniBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        translator.addDefinitionCommand("#define  CCStepper_EN1  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        translator.addDefinitionCommand("#define  CCStepper_DIR1  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        translator.addDefinitionCommand("#define  CCStepper_STP1  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(3);
        translator.addDefinitionCommand("#define  CCStepper_EN2  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(4);
        translator.addDefinitionCommand("#define  CCStepper_DIR2  "+translatorBlock.toCode());
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(5);
        translator.addDefinitionCommand("#define  CCStepper_STP2  "+translatorBlock.toCode());

        translator.addSetupCommand("pinMode(CCStepper_EN1,OUTPUT);\n" +
                "  pinMode(CCStepper_DIR1,OUTPUT);\n" +
                "  digitalWrite(CCStepper_EN1,HIGH);\n" +
                "  pinMode(CCStepper_EN2,OUTPUT);\n" +
                "  pinMode(CCStepper_DIR2,OUTPUT);\n" +
                "  digitalWrite(CCStepper_EN2,HIGH);\n");
        translator.addDefinitionCommand("void setSteppersDirection(int direction)\n" +
                "{\n" +
                "  if(direction==0)\n" +
                "  {\n" +
                "      analogWrite(CCStepper_STP1,0);\n" +
                "      analogWrite(CCStepper_STP2,0);\n" +
                "  }\n" +
                "  else\n" +
                "  {\n" +
                "      if(direction>0)\n" +
                "      {\n" +
                "          digitalWrite(CCStepper_DIR1,HIGH);\n" +
                "          digitalWrite(CCStepper_DIR2,LOW); \n" +
                "      }\n" +
                "      else \n" +
                "      {\n" +
                "          digitalWrite(CCStepper_DIR1,LOW);\n" +
                "          digitalWrite(CCStepper_DIR2,HIGH);\n" +
                "      }\n" +
                "      analogWrite(CCStepper_STP1,128);\n" +
                "      analogWrite(CCStepper_STP2,128);\n" +
                "  }\n" +
                "}\n");

        return "";

    }
}
