package jackyy.avaritiatweaks.compat.botania.subtile;

import jackyy.avaritiatweaks.config.ModConfig;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileAsgardandelion extends SubTileGenerating {

    public static LexiconEntry lexicon;

    @Override
    public int getMaxMana() {
        return ModConfig.compats.botania.asgardandelion.maxMana;
    }

    @Override
    public int getColor() {
        return 0x11FF00;
    }

    @Override
    public LexiconEntry getEntry() {
        return lexicon;
    }

    @Override
    public boolean canGeneratePassively() {
        return true;
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return ModConfig.compats.botania.asgardandelion.generationCycleDelay;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return ModConfig.compats.botania.asgardandelion.generationCycleMana;
    }

}
