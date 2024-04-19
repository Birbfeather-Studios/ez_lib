package net.distantdig.ezLib;

import net.fabricmc.loader.api.FabricLoader;

public class ExampleMod extends EzLib {

    private static final boolean isDevEnvironment = FabricLoader.getInstance().isDevelopmentEnvironment();
    public ExampleMod() {
        super("example_mod");
    }
}
