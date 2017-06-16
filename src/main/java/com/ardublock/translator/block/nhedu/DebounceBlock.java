package com.ardublock.translator.block.nhedu;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
/**
 * Created by Administrator on 2017/6/16 0016.
 */
public class DebounceBlock extends TranslatorBlock{

    public DebounceBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
    {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }
    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        return null;
    }
}
