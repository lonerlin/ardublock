package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class L298N2MotorShieldBlock extends TranslatorBlock {

    public L298N2MotorShieldBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String mIndex,mDirection,mSpeed;

        translator.addSetupCommand("pinMode(M1, OUTPUT); \n" +
                "pinMode(M2, OUTPUT);\n" );
        translator.addDefinitionCommand("int E1 = 5;  //PWMA\n" +
                "int M1 = 4;  //DIRA\n" +
                "int E2 = 6;  //PWMB             \n" +
                "int M2 = 7;  //DIRB\n" +
                "void nhedu_L298N(int firstMotorSpeed,int secondMotorSpeed)\n" +
                "{\n" +
                "  if(firstMotorSpeed<-255)firstMotorSpeed=-255;\n" +
                "  if(firstMotorSpeed>255)firstMotorSpeed=255;\n" +
                "  if(secondMotorSpeed<-255)secondMotorSpeed=-255;\n" +
                "  if(secondMotorSpeed>255)secondMotorSpeed=255;\n" +
                "  if(mIndex==1)\n" +
                "  {\n" +
                "      digitalWrite(M1,mDirection);         \n" +
                "      analogWrite(E1, mSpeed); \n" +
                "  }else if(mIndex==2)\n" +
                "  {\n" +
                "       digitalWrite(M2,mDirection);         \n" +
                "       analogWrite(E2, mSpeed); \n" +
                "   }\n" +
                "}\n");
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        mIndex=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        mDirection=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        mSpeed=translatorBlock.toCode();
        String ret= "nhedu_L298N("+mIndex+","+mDirection+","+mSpeed+");\n";
        return codePrefix+ret+codeSuffix;
    }
    }
}
