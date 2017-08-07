package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class AttachInterruptBlock extends TranslatorBlock {
    public AttachInterruptBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String interruptPin,intteruptMode,ret;
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        interruptPin=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        intteruptMode=translatorBlock.toCode();
        translatorBlock = getTranslatorBlockAtSocket(2);
        ret="void attachInterrupt_fun_"+interruptPin+"() {\n\t";
        while (translatorBlock != null)
        {
            ret = ret + "\t"+translatorBlock.toCode();
            translatorBlock = translatorBlock.nextTranslatorBlock();
        }
        ret=ret+"\n } \n";

        translator.addSetupCommand("pinMode("+interruptPin+",INPUT);");
        translator.addDefinitionCommand(ret);
        return codePrefix+"attachInterrupt(digitalPinToInterrupt("+interruptPin+"),attachInterrupt_fun_"+interruptPin+","+intteruptMode+");"+codeSuffix;
    }
}
