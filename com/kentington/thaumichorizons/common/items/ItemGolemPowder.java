/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.items.ItemFocusAnimation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;

public class ItemGolemPowder
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemGolemPowder() {
        this.func_77625_d(64);
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:golempowder");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.golemPowder";
    }

    public boolean func_77648_a(ItemStack p_77648_1_, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        Block blocky = world.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
        int md = world.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_);
        if (player.func_82247_a(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_)) {
            if (world.field_72995_K) {
                return true;
            }
            if (!blocky.hasTileEntity(md) && !blocky.isAir((IBlockAccess)world, p_77648_4_, p_77648_5_, p_77648_6_) && (blocky.func_149662_c() || ItemFocusAnimation.isWhitelisted(blocky, md)) && blocky.func_149712_f(world, p_77648_4_, p_77648_5_, p_77648_6_) != -1.0f) {
                WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
                if (player.field_71075_bZ.field_75099_e) {
                    if (player.field_71075_bZ.field_75098_d) {
                        gt = WorldSettings.GameType.CREATIVE;
                    }
                } else {
                    gt = WorldSettings.GameType.ADVENTURE;
                }
                if (!world.field_72995_K) {
                    EntityGolemTH golem = new EntityGolemTH(world);
                    golem.loadGolem((double)p_77648_4_ + 0.5, p_77648_5_, (double)p_77648_6_ + 0.5, blocky, md, 1200, false, false, false);
                    BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent((World)player.field_70170_p, (WorldSettings.GameType)gt, (EntityPlayerMP)((EntityPlayerMP)player), (int)p_77648_4_, (int)p_77648_5_, (int)p_77648_6_);
                    if (event.isCanceled()) {
                        golem.func_70106_y();
                        return false;
                    }
                    world.func_147468_f(p_77648_4_, p_77648_5_, p_77648_6_);
                    world.func_72908_a((double)p_77648_4_ + 0.5, (double)p_77648_5_ + 0.5, (double)p_77648_6_ + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                    golem.func_110171_b((int)golem.field_70165_t, (int)golem.field_70163_u, (int)golem.field_70161_v, 32);
                    golem.setOwner(player.func_70005_c_());
                    world.func_72838_d((Entity)golem);
                    world.func_72960_a((Entity)golem, (byte)7);
                } else {
                    Minecraft.func_71410_x().field_71452_i.func_147215_a(p_77648_4_, p_77648_5_, p_77648_6_, blocky, md);
                    player.func_71038_i();
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

