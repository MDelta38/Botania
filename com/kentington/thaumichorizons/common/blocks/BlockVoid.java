/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import thaumcraft.common.config.Config;

public class BlockVoid
extends Block {
    public BlockVoid() {
        super(Config.airyMaterial);
        this.func_149711_c(-1.0f);
        this.func_149752_b(60000.0f);
        this.func_149663_c("ThaumicHorizons_void");
        this.func_149658_d("ThaumicHorizons:void");
        this.func_149672_a(new Block.SoundType("cloth", 0.0f, 1.0f));
        this.func_149715_a(1.0f);
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a("thaumichorizons:void");
    }
}

