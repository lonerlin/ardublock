package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class L298NMotorControlModleBlock extends TranslatorBlock
{
    public L298NMotorControlModleBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {

        String firstMotorSpeed,secondMotorSpeed;

        translator.addSetupCommand("pinMode(L298NIN1,OUTPUT);\n" +
                "pinMode(L298NIN2,OUTPUT);\n" +
                "pinMode(L298NIN3,OUTPUT);\n" +
                "pinMode(L298NIN4,OUTPUT);\n" );
        translator.addDefinitionCommand("#ifndef L298NIN1\n" +
                "#define  L298NIN1   5  //IN1\n" +
                "#endif\n" +
                "#ifndef L298NIN2\n" +
                "#define  L298NIN2   6  //IN2\n" +
                "#endif\n" +
                "#ifndef  L298NIN3\n" +
                "#define  L298NIN3   9  //IN3\n" +
                "#endif\n" +
                "#ifndef  L298NIN4\n" +
                "#define  L298NIN4   10  //IN4\n" +
                "#endif\n" +
                "\n" +
                "void nhedu_L298NTwoMotor(int firstMotorSpeed,int secondMotorSpeed)\n" +
                "{\n" +
                "  if(firstMotorSpeed<-255)firstMotorSpeed=-255;\n" +
                "  if(firstMotorSpeed>255)firstMotorSpeed=255;\n" +
                "  if(secondMotorSpeed<-255)secondMotorSpeed=-255;\n" +
                "  if(secondMotorSpeed>255)secondMotorSpeed=255;\n" +
                "  if(firstMotorSpeed==0)\n" +
                "  {\n" +
                "      digitalWrite(L298NIN1,false);\n" +
                "      digitalWrite(L298NIN2,false); \n" +
                "   }\n" +
                "    else if(firstMotorSpeed>0)\n" +
                "  {\n" +
                "    digitalWrite( L298NIN2,false);\n" +
                "    analogWrite(L298NIN1,firstMotorSpeed);\n" +
                "  }\n" +
                "  else\n" +
                "  {\n" +
                "    digitalWrite( L298NIN1,false);\n" +
                "    analogWrite(L298NIN2,-firstMotorSpeed);\n" +
                "  }\n" +
                "  if(secondMotorSpeed==0)\n" +
                "  {\n" +
                "        digitalWrite(L298NIN3,false);\n" +
                "      digitalWrite(L298NIN4,false); \n" +
                "  }\n" +
                "  else if(secondMotorSpeed>0)\n" +
                "  {\n" +
                "    digitalWrite( L298NIN3,false);\n" +
                "    analogWrite(L298NIN4,secondMotorSpeed);\n" +
                "  }\n" +
                "  else\n" +
                "  {\n" +
                "      digitalWrite( L298NIN4,false);\n" +
                "      analogWrite(L298NIN3,-secondMotorSpeed);\n" +
                "  }\n" +
                "}\n"

        );
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        firstMotorSpeed=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        secondMotorSpeed=translatorBlock.toCode();

        String ret= "nhedu_L298NTwoMotor("+firstMotorSpeed+","+secondMotorSpeed+");\n";
        return codePrefix+ret+codeSuffix;
    }

}
