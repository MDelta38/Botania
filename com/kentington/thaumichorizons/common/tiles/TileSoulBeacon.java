/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.Thaumcraft;

public class TileSoulBeacon
extends TileThaumcraft {
    @SideOnly(value=Side.CLIENT)
    private float field_146014_j;
    @SideOnly(value=Side.CLIENT)
    private long field_146016_i;

    public boolean activate(EntityPlayer p) {
        p.getEntityData().func_74757_a("soulBeacon", true);
        p.getEntityData().func_74783_a("soulBeaconCoords", new int[]{this.field_145851_c, this.field_145848_d, this.field_145849_e});
        p.getEntityData().func_74768_a("soulBeaconDim", this.field_145850_b.field_73011_w.field_76574_g);
        p.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"thaumichorizons.setBeacon")));
        Thaumcraft.proxy.arcLightning(this.field_145850_b, p.field_70165_t, p.field_70163_u + (double)p.func_70047_e(), p.field_70161_v, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.75, (double)this.field_145849_e + 0.5, 0.05f, 1.0f, 0.05f, 0.5f);
        this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.75, (double)this.field_145849_e + 0.5, "thaumcraft:zap", 1.0f, 1.0f);
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_146002_i() {
        int i = (int)(this.field_145850_b.func_82737_E() - this.field_146016_i);
        this.field_146016_i = this.field_145850_b.func_82737_E();
        if (i > 1) {
            this.field_146014_j -= (float)i / 40.0f;
            if (this.field_146014_j < 0.0f) {
                this.field_146014_j = 0.0f;
            }
        }
        this.field_146014_j += 0.025f;
        if (this.field_146014_j > 1.0f) {
            this.field_146014_j = 1.0f;
        }
        return this.field_146014_j;
    }

    @SideOnly(value=Side.CLIENT)
    public double func_145833_n() {
        return 65536.0;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}

