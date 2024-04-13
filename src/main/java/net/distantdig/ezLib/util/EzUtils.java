package net.distantdig.ezLib.util;

import net.distantdig.ezLib.EzLib;
import net.distantdig.ezLib.EzLibDataGenerator;
import net.fabricmc.loader.api.FabricLoader;

public class EzUtils {
    public static String checkModContainerId() {
        String modId = EzLib.getModId();
        if (FabricLoader.getInstance().getModContainer(modId).isPresent()) {
            EzLibDataGenerator.MOD_ID = modId;
            return modId;
        } else {
            EzLibDataGenerator.MOD_ID = "ez_lib";
            return "ez_lib";
        }
    }
}
