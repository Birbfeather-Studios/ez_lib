package net.distantdig.ezLib;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class Initializer implements ModInitializer {

    private static final boolean isDevEnvironment = FabricLoader.getInstance().isDevelopmentEnvironment();

    @Override
    public void onInitialize() {
    }
}
