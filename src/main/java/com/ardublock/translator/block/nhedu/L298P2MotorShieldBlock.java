package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class L298P2MotorShieldBlock extends TranslatorBlock {

    public L298P2MotorShieldBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String firstMotorSpeed,secondMotorSpeed;

        translator.addSetupCommand("pinMode(M1, OUTPUT); \n" +
                "pinMode(M2, OUTPUT);\n" );
        translator.addDefinitionCommand("int E1 = 5;  //PWMA\n" +
                "int M1 = 4;  //DIRA\n" +
                "int E2 = 6;  //PWMB             \n" +
                "int M2 = 7;  //DIRB\n" +
                "void nhedu_L298PTwoMotor(int firstMotorSpeed,int secondMotorSpeed)\n" +
                "{\n" +
                "  if(firstMotorSpeed<-255)firstMotorSpeed=-255;\n" +
                "  if(firstMotorSpeed>255)firstMotorSpeed=255;\n" +
                "  if(secondMotorSpeed<-255)secondMotorSpeed=-255;\n" +
                "  if(secondMotorSpeed>255)secondMotorSpeed=255;\n" +
                "  if(firstMotorSpeed>=0)\n" +
                "  {\n" +
                "    digitalWrite(M1,true);\n" +
                "    analogWrite(E1,firstMotorSpeed);\n"+
                "  }else\n"+
                "  {\n" +
                "    digitalWrite(M1,false);\n" +
                "    analogWrite(E1,-firstMotorSpeed);\n"+
                "  }\n"+
                "  if(secondMotorSpeed>=0)\n" +
                "  {\n" +
                "    digitalWrite(M2,true);\n" +
                "    analogWrite(E2,secondMotorSpeed);\n"+
                "  }else\n"+
                "  {\n" +
                "    digitalWrite(M2,false);\n" +
                "    analogWrite(E2,-secondMotorSpeed);\n"+
                "  }\n"+
                "}\n");
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        firstMotorSpeed=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        secondMotorSpeed=translatorBlock.toCode();

        String ret= "nhedu_L298PTwoMotor("+firstMotorSpeed+","+secondMotorSpeed+");\n";
        return codePrefix+ret+codeSuffix;
    }

}
