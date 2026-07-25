/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemBrewEndlessWater
extends ItemBase {
    @SideOnly(value=Side.CLIENT)
    protected IIcon itemIconOverlay;

    public ItemBrewEndlessWater() {
        this.func_77625_d(1);
        this.func_77656_e(99);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        return pass == 0;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 0) {
            return this.itemIconOverlay;
        }
        return this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        super.func_94581_a(iconRegister);
        this.itemIconOverlay = iconRegister.func_94245_a("witchery:brew_overlay");
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            int color = 255;
            return 255;
        }
        return super.func_82790_a(stack, pass);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expanded) {
        String localText = String.format(Witchery.resource("item.witchery:brew.water.tip"), Integer.valueOf(stack.func_77958_k() - stack.func_77960_j() + 1).toString(), Integer.valueOf(stack.func_77958_k() + 1).toString());
        if (localText != null) {
            for (String s : localText.split("\n")) {
                if (s.isEmpty()) continue;
                list.add(s);
            }
        }
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.field_72995_K && stack.func_77960_j() <= stack.func_77958_k()) {
            Block block = world.func_147439_a(x, y, z);
            if (block == Blocks.field_150383_bp) {
                int meta = world.func_72805_g(x, y, z);
                if (meta < 3) {
                    stack.func_77972_a(1, (EntityLivingBase)player);
                    Blocks.field_150383_bp.func_150024_a(world, x, y, z, 3);
                    SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
                }
            } else {
                ForgeDirection face = ForgeDirection.getOrientation((int)side);
                if (block != null && BlockUtil.isReplaceableBlock(world, x += face.offsetX, y += face.offsetY, z += face.offsetZ, (EntityLivingBase)player)) {
                    stack.func_77972_a(1, (EntityLivingBase)player);
                    world.func_147449_b(x, y, z, (Block)Blocks.field_150358_i);
                    world.func_147471_g(x, y, z);
                    SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
                }
            }
        }
        return false;
    }
}

