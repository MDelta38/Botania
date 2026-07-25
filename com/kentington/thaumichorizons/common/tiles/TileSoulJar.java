/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigBlocks
 */
package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigBlocks;

public class TileSoulJar
extends TileThaumcraft
implements IWandable {
    public NBTTagCompound jarTag = null;
    public Entity entity = null;
    public boolean drop = true;
    ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/models/jar.png");

    public void func_145845_h() {
        if (this.entity == null && this.jarTag != null && !this.jarTag.func_74767_n("isSoul")) {
            this.entity = EntityList.func_75615_a((NBTTagCompound)this.jarTag, (World)this.field_145850_b);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.jarTag = nbttagcompound.func_74775_l("jarTag");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74782_a("jarTag", (NBTBase)this.jarTag);
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (this.jarTag.func_74767_n("isSoul")) {
            return 0;
        }
        if (!world.field_72995_K) {
            this.drop = false;
            world.func_147468_f(x, y, z);
            Entity ent = EntityList.func_75615_a((NBTTagCompound)this.jarTag, (World)world);
            if (ent == null) {
                return 0;
            }
            if (ent instanceof EntityTameable && ((EntityTameable)ent).func_70902_q() == null) {
                ((EntityTameable)ent).func_152115_b(player.func_110124_au().toString());
            }
            ent.func_70012_b((double)x + 0.5, (double)y + 0.1, (double)z + 0.5, 0.0f, 0.0f);
            world.func_72838_d(ent);
            this.func_70296_d();
        }
        world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)ConfigBlocks.blockJar) + 61440);
        player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.glass", 1.0f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
        player.func_71038_i();
        return 0;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }
}

