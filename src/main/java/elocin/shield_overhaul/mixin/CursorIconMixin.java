package elocin.shield_overhaul.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.util.ShieldUtils;
import elocin.shield_overhaul.util.UIUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class CursorIconMixin {
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;
    @Shadow
    private final MinecraftClient client;

    private static final Identifier SHIELD_ICONS = new Identifier(ShieldOverhaul.MOD_ID, "textures/gui/shield_sprites.png");

    public CursorIconMixin(MinecraftClient client) {
        this.client = client;
    }

    @Inject(at = @At("TAIL"), method = "renderHotbar")
    private void $shield_overhaul_renderHotbar(float tickDelta, DrawContext context, CallbackInfo ci) {
        if (this.client.player.isHolding(Items.SHIELD)) {
            drawBashShield(context);
            drawParryShield(context);
        }
        RenderSystem.defaultBlendFunc();
    }

    private void drawBashShield(DrawContext context) {
        float f = this.client.player.getItemCooldownManager().getCooldownProgress(Items.SHIELD, 0.0F);

        int j = this.scaledHeight / 2 - 7 + 16;
        int k = this.scaledWidth / 2 - 8;

        // Bash shield
        if (f < 1.0F) {
            int l = (int) (f * 16.0f);
            context.drawTexture(SHIELD_ICONS, k - 13 - 20, j - 19, 0, 56, 16, 18);
            context.drawTexture(SHIELD_ICONS, k - 13 - 20, j - 19, 16, 56, l, 18);
        }
    }

    private void drawParryShield(DrawContext context) {
        if (this.client == null) return;
        ItemStack shield = this.client.player.getStackInHand(client.player.getActiveHand());

        float f = UIUtils.getParryProgress(client.player, shield);

        int j = this.scaledHeight / 2 - 7 + 16;
        int k = this.scaledWidth / 2 - 8;


        if (f < 1.0) {
            int l = (int) (f * 16.0f);
            context.drawTexture(SHIELD_ICONS, k + 15 + 20, j - 16, 0, 0, 16, 18);
            context.drawTexture(SHIELD_ICONS, k + 16 + 20, j - 16, 16, 0, l, 18);
        }
    }

}