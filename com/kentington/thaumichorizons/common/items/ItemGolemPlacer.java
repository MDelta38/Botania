/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.codechicken.lib.math.MathHelper
 *  thaumcraft.common.entities.golems.ItemGolemPlacer
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.items.ItemGolemBellTH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.codechicken.lib.math.MathHelper;

public class ItemGolemPlacer
extends thaumcraft.common.entities.golems.ItemGolemPlacer {
    public IIcon icon;
    public IIcon newBell;

    public ItemGolemPlacer() {
        this.func_77637_a(null);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        super.func_94581_a(ir);
        this.icon = ir.func_94245_a("thaumichorizons:golem");
        this.newBell = ir.func_94245_a("thaumichorizons:newbell");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int p_82790_2_) {
        if (stack.func_77978_p().func_74764_b("block")) {
            int[] block = stack.func_77978_p().func_74759_k("block");
            if (Block.func_149729_e((int)block[0]) == Blocks.field_150350_a) {
                return 0;
            }
            int color = Block.func_149729_e((int)block[0]).func_149728_f((int)block[1]).field_76291_p;
            if (color != 0) {
                return color;
            }
            return -1;
        }
        return 0;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("block")) {
            int[] block = stack.func_77978_p().func_74759_k("block");
            String name = "?";
            ItemStack blockStack = new ItemStack(Block.func_149729_e((int)block[0]), 1, block[1]);
            if (blockStack.func_77973_b() != null) {
                list.add(blockStack.func_82833_r());
            } else if (Block.func_149729_e((int)block[0]) == Blocks.field_150350_a) {
                list.add("Voidling");
            } else {
                list.add(Block.func_149729_e((int)block[0]).func_149732_F());
            }
        }
        super.func_77624_a(stack, par2EntityPlayer, list, par4);
    }

    public boolean spawnCreature(World par0World, double par2, double par4, double par6, int side, ItemStack stack, EntityPlayer player) {
        EntityGolemTH golem;
        boolean adv = false;
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("advanced")) {
            adv = true;
        }
        if ((golem = new EntityGolemTH(par0World)) != null) {
            golem.func_70012_b(par2, par4, par6, par0World.field_73012_v.nextFloat() * 360.0f, 0.0f);
            golem.func_70642_aH();
            golem.func_110171_b(MathHelper.floor_double((double)par2), MathHelper.floor_double((double)par4), MathHelper.floor_double((double)par6), 32);
            int[] block = new int[]{0, 0};
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("core")) {
                golem.setCore(stack.field_77990_d.func_74771_c("core"));
            }
            String deco = "";
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("deco")) {
                golem.decoration = deco = stack.field_77990_d.func_74779_i("deco");
            }
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("block")) {
                block = stack.field_77990_d.func_74759_k("block");
            }
            golem.setup(side);
            golem.loadGolem(golem.field_70165_t, golem.field_70163_u, golem.field_70161_v, Block.func_149729_e((int)block[0]), block[1], 600, adv, stack.field_77990_d.func_74767_n("berserk"), stack.field_77990_d.func_74767_n("explosive"));
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("upgrades")) {
                int ul = golem.upgrades.length;
                golem.upgrades = stack.field_77990_d.func_74770_j("upgrades");
                if (ul != golem.upgrades.length) {
                    int a;
                    byte[] tt = new byte[ul];
                    for (a = 0; a < ul; ++a) {
                        tt[a] = -1;
                    }
                    for (a = 0; a < golem.upgrades.length; ++a) {
                        if (a >= ul) continue;
                        tt[a] = golem.upgrades[a];
                    }
                    golem.upgrades = tt;
                }
            }
            par0World.func_72838_d((Entity)golem);
            golem.setGolemDecoration(deco);
            golem.setOwner(player.func_70005_c_());
            golem.setMarkers(ItemGolemBellTH.getMarkers(stack));
            int a = 0;
            for (byte b : golem.upgrades) {
                golem.setUpgrade(a, b);
                ++a;
            }
            if (stack.func_82837_s()) {
                golem.func_94058_c(stack.func_82833_r());
                golem.func_110163_bv();
            }
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("Inventory")) {
                NBTTagList nbttaglist2 = stack.field_77990_d.func_150295_c("Inventory", 10);
                golem.inventory.readFromNBT(nbttaglist2);
            }
        }
        return golem != null;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.golemPlacer";
    }
}

