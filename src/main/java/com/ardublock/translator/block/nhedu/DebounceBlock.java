package com.ardublock.translator.block.nhedu;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
/**
 * 去抖函数，通过去抖方式读取一个输入端口的数字变量
 * @author Lin Jungui
 * @Time 2017/6/16 0016.
 */
public class DebounceBlock extends TranslatorBlock{


    private static final String NHEDU_DEBOUNCEBLOCK_DEFINE= "bool nhedu_debounce(int pin,int debounceDelay)\n" +
            " {\n" +
            "   bool state;\n" +
            "   bool previousState;\n" +
            "   pinMode(pin, INPUT);\n"+
            "   previousState=digitalRead(pin);\n" +
            "   for(int counter=0;counter<debounceDelay;counter++)\n" +
            "   {\n" +
            "     delay(1);\n" +
            "     state=digitalRead(pin);\n" +
            "     if(state!=previousState)\n" +
            "     {\n" +
            "       counter=0;\n" +
            "       previousState=state;\n" +
            "     }\n" +
            "   }\n" +
            "   return state;\n" +
            " }";

    public DebounceBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
    {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }
    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String digitalPin;
        String delayMillis;
        TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
        digitalPin=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        delayMillis=translatorBlock.toCode();
        translator.addDefinitionCommand(NHEDU_DEBOUNCEBLOCK_DEFINE);

        String ret = "nhedu_debounce(";
        ret = ret +digitalPin + "," + delayMillis;
        ret = ret + ")";
        return codePrefix + ret + codeSuffix;
    }
}
