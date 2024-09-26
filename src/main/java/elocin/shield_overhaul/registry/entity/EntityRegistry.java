package elocin.shield_overhaul.registry.entity;

import elocin.shield_overhaul.ShieldOverhaul;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityRegistry {
    public static final EntityType<ShieldBashEntity> SHIELD_BASH_ENTITY = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(ShieldOverhaul.MOD_ID, "shield_bash_entity"),
            FabricEntityTypeBuilder.<ShieldBashEntity>create(SpawnGroup.MISC, ShieldBashEntity::new)
                    .dimensions(EntityDimensions.fixed(2F, 2F)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static void initialize() {}

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
