/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockLight;
import com.kentington.thaumichorizons.common.tiles.TileLight;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLightSolar
extends BlockLight {
    public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
        EntityLivingBase critter;
        if (p_149670_5_ instanceof EntityLivingBase && (critter = (EntityLivingBase)p_149670_5_).func_70662_br()) {
            critter.func_70015_d(5);
        }
    }

    @Override
    public int func_149645_b() {
        return ThaumicHorizons.blockLightSolarRI;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileLight();
    }
}

