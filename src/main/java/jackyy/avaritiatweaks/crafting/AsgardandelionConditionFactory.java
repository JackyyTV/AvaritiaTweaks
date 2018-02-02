package jackyy.avaritiatweaks.crafting;

import com.google.gson.JsonObject;
import jackyy.avaritiatweaks.config.ModConfig;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

import java.util.function.BooleanSupplier;

public class AsgardandelionConditionFactory implements IConditionFactory {
    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return () -> (ModConfig.compats.botania.asgardandelion && ModConfig.compats.botaniaCompat) == JsonUtils.getBoolean(json, "value", true);
    }
}
