/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockTNT
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityAlchemitePrimed;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockAlchemite
extends BlockTNT {
    private static IIcon blockIconTop;
    private static IIcon blockIconBottom;

    public BlockAlchemite() {
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149715_a(0.5f);
        this.func_149663_c("ThaumicHorizons_alchemite");
        this.func_149658_d("ThaumicHorizons:alchemite");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public void func_149723_a(World p_149723_1_, int p_149723_2_, int p_149723_3_, int p_149723_4_, Explosion p_149723_5_) {
        if (!p_149723_1_.field_72995_K) {
            EntityAlchemitePrimed entitytntprimed = new EntityAlchemitePrimed(p_149723_1_, (float)p_149723_2_ + 0.5f, (float)p_149723_3_ + 0.5f, (float)p_149723_4_ + 0.5f, p_149723_5_.func_94613_c());
            entitytntprimed.fuse = p_149723_1_.field_73012_v.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            p_149723_1_.func_72838_d((Entity)entitytntprimed);
        }
    }

    public void func_150114_a(World p_150114_1_, int p_150114_2_, int p_150114_3_, int p_150114_4_, int p_150114_5_, EntityLivingBase p_150114_6_) {
        if (!p_150114_1_.field_72995_K && (p_150114_5_ & 1) == 1) {
            EntityAlchemitePrimed entitytntprimed = new EntityAlchemitePrimed(p_150114_1_, (float)p_150114_2_ + 0.5f, (float)p_150114_3_ + 0.5f, (float)p_150114_4_ + 0.5f, p_150114_6_);
            p_150114_1_.func_72838_d((Entity)entitytntprimed);
            p_150114_1_.func_72956_a((Entity)entitytntprimed, "game.tnt.primed", 1.0f, 1.0f);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 0 ? blockIconBottom : (p_149691_1_ == 1 ? blockIconTop : this.field_149761_L);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
        this.field_149761_L = p_149651_1_.func_94245_a(this.func_149641_N() + "_side");
        blockIconTop = p_149651_1_.func_94245_a(this.func_149641_N() + "_top");
        blockIconBottom = p_149651_1_.func_94245_a(this.func_149641_N() + "_bottom");
    }
}

