package com.ardublock.translator.block.nhedu;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LCD_PrintBlock extends TranslatorBlock {
    public LCD_PrintBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
        super(blockId, translator, codePrefix, codeSuffix, label);
    }

    @Override
    public String toCode() throws SocketNullException, SubroutineNotDeclaredException, BlockException {
        String printStr;
        String beginCol;
        String beginRow;


        translator.addHeaderFile("Wire.h");
        translator.addHeaderFile("LiquidCrystal_I2C.h");
        translator.addDefinitionCommand("LiquidCrystal_I2C nhedu_LCD(0x27, 16, 2);");

        translator.addSetupCommand("nhedu_LCD.begin();");
        translator.addSetupCommand("nhedu_LCD.backlight();");



        TranslatorBlock translatorBlock=this.getRequiredTranslatorBlockAtSocket(0);
        printStr=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(1);
        beginCol=translatorBlock.toCode();
        translatorBlock=this.getRequiredTranslatorBlockAtSocket(2);
        beginRow=translatorBlock.toCode();

        String ret="nhedu_LCD.setCursor("+beginCol+","+beginRow+");\n";
        ret+="nhedu_LCD.print("+printStr+");\n";
        return codePrefix+ret+codeSuffix;
    }
}
