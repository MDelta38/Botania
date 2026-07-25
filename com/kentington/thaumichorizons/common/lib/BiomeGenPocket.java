/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  thaumcraft.common.config.ConfigBlocks
 */
package com.kentington.thaumichorizons.common.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.common.config.ConfigBlocks;

public class BiomeGenPocket
extends BiomeGenBase {
    public BiomeGenPocket(int inty) {
        super(inty);
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
        this.field_82914_M.clear();
        this.field_76752_A = ConfigBlocks.blockEldritchNothing;
        this.field_76753_B = ConfigBlocks.blockEldritchNothing;
        this.func_76735_a("Pocket Plane");
        this.func_76745_m();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76731_a(float p_76731_1_) {
        return 0;
    }

    public void func_76728_a(World world, Random random, int x, int z) {
    }

    public BiomeGenBase func_150566_k() {
        return null;
    }
}

