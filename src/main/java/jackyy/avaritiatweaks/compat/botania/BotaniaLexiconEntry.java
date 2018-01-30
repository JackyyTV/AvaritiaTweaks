package jackyy.avaritiatweaks.compat.botania;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class BotaniaLexiconEntry extends LexiconEntry implements IAddonEntry {

    public BotaniaLexiconEntry(String name, LexiconCategory category){
        super(name, category);
        BotaniaAPI.addEntry(this, category);
    }

    @Override
    public String getSubtitle(){
        return "[Avaritia Tweaks]";
    }

    @Override
    public String getUnlocalizedName() {
        return "avaritia.lexicon." + super.getUnlocalizedName();
    }

}
