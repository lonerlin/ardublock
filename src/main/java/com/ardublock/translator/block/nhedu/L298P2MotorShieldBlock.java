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

        translator.addSetupCommand("pinMode(L298PM1, OUTPUT); \n" +
                "pinMode(L298PM2, OUTPUT);\n" );
        translator.addDefinitionCommand("#ifndef L298PE1\n" +
                "  #define  L298PE1   5  //PWMA\n" +
                "#endif\n" +
                "#ifndef  L298PM1\n" +
                "  #define  L298PM1   4  //DIRA\n" +
                "#endif\n" +
                "#ifndef  L298PE2\n" +
                "  #define  L298PE2   6  //PWMB\n" +
                "#endif\n" +
                "#ifndef  L298PM2\n" +
                "  #define  L298PM2   7  //DIRB\n" +
                "#endif\n" +
                "\n" +
                "void nhedu_L298PTwoMotor(int firstMotorSpeed,int secondMotorSpeed)\n" +
                "{\n" +
                "  if(firstMotorSpeed<-255)firstMotorSpeed=-255;\n" +
                "  if(firstMotorSpeed>255)firstMotorSpeed=255;\n" +
                "  if(secondMotorSpeed<-255)secondMotorSpeed=-255;\n" +
                "  if(secondMotorSpeed>255)secondMotorSpeed=255;\n" +
                "  if(firstMotorSpeed>=0)\n" +
                "  {\n" +
                "    digitalWrite( L298PM1,true);\n" +
                "    analogWrite( L298PE1,firstMotorSpeed);\n" +
                "  }\n" +
                "  else\n" +
                "  {\n" +
                "    digitalWrite( L298PM1,false);\n" +
                "    analogWrite( L298PE1,-firstMotorSpeed);\n" +
                "  }\n" +
                "  if(secondMotorSpeed>=0)\n" +
                "  {\n" +
                "    digitalWrite( L298PM2,true);\n" +
                "    analogWrite( L298PE2,secondMotorSpeed);\n" +
                "  }\n" +
                "  else\n" +
                "  {\n" +
                "    digitalWrite( L298PM2,false);\n" +
                "    analogWrite( L298PE2,-secondMotorSpeed);\n" +
                "  }\n" +
                "}"

        );
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        firstMotorSpeed=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        secondMotorSpeed=translatorBlock.toCode();

        String ret= "nhedu_L298PTwoMotor("+firstMotorSpeed+","+secondMotorSpeed+");\n";
        return codePrefix+ret+codeSuffix;
    }

}
