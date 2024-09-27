package elocin.shield_overhaul.registry.enchantment;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.util.ShieldUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnchantmentRegistry {
    public static Enchantment VIGOR = new VigorEnchantment();

    public static void initialize() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(ShieldOverhaul.MOD_ID, "vigor"), VIGOR);
    }
}
