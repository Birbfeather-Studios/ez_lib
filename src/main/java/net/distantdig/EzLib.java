package net.distantdig;

import net.distantdig.item.EzItemGroups;
import net.distantdig.item.EzItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EzLib implements ModInitializer {
	public static final String MOD_ID = "ez_lib";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		EzItems.regigisterEzItems();
		EzItemGroups.registerItemGroups();

		LOGGER.info("Ez");
	}
}