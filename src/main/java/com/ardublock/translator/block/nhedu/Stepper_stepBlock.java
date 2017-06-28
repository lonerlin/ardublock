package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

/**
 * 控制步进电机的转动步数，转动速度
 * 需要通过模块名称与StepperBlock配合
 * Created by Lin Jungui on 2017/6/27 0027.
 */
public class Stepper_stepBlock extends TranslatorBlock {
    public Stepper_stepBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String StepperName;
        String Speed;
        String Step;

        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
        StepperName = translatorBlock.toCode().replace("\"","");
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
        Speed = translatorBlock.toCode();
        translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
        Step = translatorBlock.toCode();


        String ret = "nhedu_"+StepperName+".setSpeed("+Speed+");\n"+ "nhedu_"+StepperName+".step("+Step+");";

        return codePrefix + ret + codeSuffix;
    }
}
