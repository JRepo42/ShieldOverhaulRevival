package elocin.shield_overhaul;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShieldOverhaul implements ModInitializer {
	public static final String MOD_ID = "shield_overhaul";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Shield Overhaul Initialised!");
	}
}