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
        return null;
    }
}
