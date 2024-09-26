package elocin.shield_overhaul;

import elocin.shield_overhaul.config.ConfigBuilder;
import elocin.shield_overhaul.config.ConfigEntries;
import elocin.shield_overhaul.effect.EffectRegistry;
import elocin.shield_overhaul.networking.PacketRegistry;
import elocin.shield_overhaul.registry.entity.EntityRegistry;
import elocin.shield_overhaul.registry.particle.ParticleRegistry;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShieldOverhaul implements ModInitializer {
	public static final String MOD_ID = "shield_overhaul";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ConfigEntries CONFIG;

	@Override
	public void onInitialize() {
		CONFIG = ConfigBuilder.loadConfig();

		PacketRegistry.registerC2S();
		EffectRegistry.initialize();
		ParticleRegistry.initialize();
		EntityRegistry.initialize();
	}
}