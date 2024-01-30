package net.distantdig;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class EzLib implements ModInitializer {
	protected static String MOD_ID;

	public EzLib(String modId) {
		MOD_ID = modId;
	}

	public static String getModId() {
			return MOD_ID;
	}

	public void registerModItems() {}
	public void registerModBlocks() {}

	public void registerModGroup() {}

    public final Logger LOGGER = LoggerFactory.getLogger("ez-lib");

	@Override
	public void onInitialize() {
		LOGGER.info("Ez Initialize");

		registerModItems();
		registerModBlocks();
		registerModGroup();
	}
}