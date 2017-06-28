package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

/**
 * 步进电机模块
 * 搭配ULN2003驱动IC（驱动板）
 * Created by Lin Jungui on 2017/6/27 0027.
 */
public class StepperBlock extends TranslatorBlock {

   public StepperBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
   {
       super(blockId,translator,codePrefix,codeSuffix,label);

   }
    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String StepperName;
        String Pin1 ;
        String Pin2 ;
        String Pin3 ;
        String Pin4 ;
        String Stepperround;

        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
        StepperName = translatorBlock.toCode().replace("\"","");
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
        Pin1 = translatorBlock.toCode();
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
        Pin2 = translatorBlock.toCode();
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(3);
        Pin3 = translatorBlock.toCode();
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(4);
        Pin4 = translatorBlock.toCode();
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(5);
        Stepperround = translatorBlock.toCode();


        translator.addHeaderFile("Stepper.h");
        translator.addDefinitionCommand("int nbrDePasParTour"+Pin1+" = "+Stepperround+";\n"+
                "Stepper nhedu_"+StepperName+"(nbrDePasParTour"+Pin1+","+Pin1+","+Pin2+","+Pin3+","+Pin4+");");
        return "";
    }
}
