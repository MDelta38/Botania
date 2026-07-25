/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockEvanescent
extends Block {
    public BlockEvanescent() {
        super(Material.field_151592_s);
        this.func_149711_c(Float.MAX_VALUE);
        this.func_149752_b(Float.MAX_VALUE);
        this.func_149663_c("ThaumicHorizons_evanescent");
        this.func_149658_d("ThaumicHorizons:evanescent");
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        ThaumicHorizons.instance.renderEventHandler.resetBlocks((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
    }

    public boolean func_149742_c(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
        return false;
    }
}

