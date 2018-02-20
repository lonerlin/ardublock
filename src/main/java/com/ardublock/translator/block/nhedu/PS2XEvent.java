package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class PS2XEvent extends TranslatorBlock {
    public PS2XEvent(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {

        return null;

    }

    protected void buildEventFunction(String button,int type)throws SocketNullException, SubroutineNotDeclaredException, BlockException
    {
        String ret,event;
        switch (type)
        {
            case 0:
                event="Pressed";
                break;
            case 1:
                event="Release";
                break;
            case 2:
                event="Held";
                break;
            default:
                event="";
        }
        ret="void "+button+"_"+event+"(){\n";
        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        while (translatorBlock != null)
        {
            ret = ret + "\t"+translatorBlock.toCode();
            translatorBlock = translatorBlock.nextTranslatorBlock();
        }
        ret=ret+"\n } \n";
        translator.addDefinitionCommand(ret);
        translator.addSetupCommand("eps.set"+event+"Event(PSB_"+button+","+button+"_"+event+");");
    }
}
