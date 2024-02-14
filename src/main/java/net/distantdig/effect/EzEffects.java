package net.distantdig.effect;

import net.distantdig.EzLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

import java.util.HashMap;

public class EzEffects {

    public final static HashMap<String, MobEffect> effectMap = new HashMap<>();

    public static <T extends MobEffect> MobEffect registerEffect(String key, T effectType) {

        MobEffect effect = Registry.register(BuiltInRegistries.MOB_EFFECT,
                new ResourceLocation(EzLib.getModId(), key),
                effectType);

        effectMap.put(key, effect);
        // Register recipes

        return effect;
    }
}
