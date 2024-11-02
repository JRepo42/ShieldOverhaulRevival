package elocin.shield_overhaul.registry;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.registry.entity.EntityRegistry;
import elocin.shield_overhaul.registry.entity.ShieldBashEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class RendererRegistry {
    public static void initializeRender() {
        EntityRendererRegistry.register(EntityRegistry.SHIELD_BASH_ENTITY, (context) ->
                new ProjectileEntityRenderer<ShieldBashEntity>(context) {
                    @Override
                    public Identifier getTexture(ShieldBashEntity entity) {
                        return new Identifier(ShieldOverhaul.MOD_ID, "textures/misc/shield_bash.png");
                    }
                });
    }
}
