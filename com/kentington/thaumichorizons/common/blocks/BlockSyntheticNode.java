/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemWispEssence
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSyntheticNode;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;

public class BlockSyntheticNode
extends BlockContainer {
    IIcon icon;

    public BlockSyntheticNode() {
        super(Material.field_151592_s);
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149715_a(0.5f);
        this.func_149663_c("ThaumicHorizons_synthNode");
        this.func_149647_a(ThaumicHorizons.tabTH);
        this.func_149676_a(0.3f, 0.0f, 0.3f, 0.7f, 1.0f, 0.7f);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileSyntheticNode node = new TileSyntheticNode();
        return node;
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

    public int func_149645_b() {
        return ThaumicHorizons.blockSyntheticNodeRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:crystal");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == ConfigItems.itemWispEssence) {
            ((TileSyntheticNode)world.func_147438_o(x, y, z)).addEssence(player);
            return true;
        }
        return false;
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        TileSyntheticNode tile = (TileSyntheticNode)p_149749_1_.func_147438_o(p_149749_2_, p_149749_3_, p_149749_4_);
        if (tile != null) {
            for (Aspect asp : tile.getMaxAspects().getAspects()) {
                ItemStack essence = new ItemStack(ConfigItems.itemWispEssence, tile.getMaxAspects().getAmount(asp) / 4);
                ((ItemWispEssence)ConfigItems.itemWispEssence).setAspects(essence, new AspectList().add(asp, 2));
                p_149749_1_.func_72838_d((Entity)new EntityItem(p_149749_1_, (double)p_149749_2_, (double)p_149749_3_, (double)p_149749_4_, essence));
            }
        }
        super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
}

