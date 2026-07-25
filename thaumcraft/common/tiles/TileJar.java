/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.TileThaumcraft;

public class TileJar
extends TileThaumcraft {
    protected static Random rand = new Random();
    ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/models/jar.png");

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }
}

