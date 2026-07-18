/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 */
package vazkii.botania.common.item.lens;

import java.awt.Color;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import vazkii.botania.api.mana.IManaSpreader;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

public class LensFlare
extends Lens {
    @Override
    public boolean allowBurstShooting(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        return false;
    }

    @Override
    public void onControlledSpreaderTick(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        if (!redstone) {
            this.emitParticles(stack, spreader, redstone);
        }
    }

    @Override
    public void onControlledSpreaderPulse(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        this.emitParticles(stack, spreader, redstone);
    }

    private void emitParticles(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        float rotationYaw = -(spreader.getRotationX() + 90.0f);
        float rotationPitch = spreader.getRotationY();
        float f = 0.3f;
        float mx = (float)((double)(MathHelper.func_76126_a((float)(rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(rotationPitch / 180.0f * (float)Math.PI)) * f) / 2.0);
        float mz = (float)((double)(-(MathHelper.func_76134_b((float)(rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(rotationPitch / 180.0f * (float)Math.PI)) * f)) / 2.0);
        float my = (float)((double)(MathHelper.func_76126_a((float)(rotationPitch / 180.0f * (float)Math.PI)) * f) / 2.0);
        int storedColor = ItemLens.getStoredColor(stack);
        float r = 1.0f;
        float g = 1.0f;
        float b = 1.0f;
        TileEntity tile = (TileEntity)spreader;
        if (storedColor == 16) {
            Color c = Color.getHSBColor((float)(tile.func_145831_w().func_82737_E() * 2L % 360L) / 360.0f, 1.0f, 1.0f);
            r = (float)c.getRed() / 255.0f;
            g = (float)c.getGreen() / 255.0f;
            b = (float)c.getBlue() / 255.0f;
        } else if (storedColor >= 0) {
            float[] colortable = EntitySheep.field_70898_d[storedColor];
            r = colortable[0];
            g = colortable[1];
            b = colortable[2];
        }
        Botania.proxy.wispFX(tile.func_145831_w(), (double)tile.field_145851_c + 0.5, (double)tile.field_145848_d + 0.5, (double)tile.field_145849_e + 0.5, r, g, b, 0.4f, mx, my, mz);
    }
}

