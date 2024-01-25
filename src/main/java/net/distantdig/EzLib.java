package net.distantdig;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EzLib implements ModInitializer {
	public String MOD_ID = "ez_lib";

	public String getModId() {
		return MOD_ID;
	}

	public void registerModItems() {}

    public final Logger LOGGER = LoggerFactory.getLogger(getModId());

	@Override
	public void onInitialize() {

		registerModItems();
//		EzItems.regigisterEzItems();
//		EzItemGroups.registerItemGroups();

		LOGGER.info("Ez");
	}
}