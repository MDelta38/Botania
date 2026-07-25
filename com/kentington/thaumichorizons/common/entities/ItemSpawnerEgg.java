/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSpawnerEgg
extends Item {
    static ArrayList<EntityEggStuff> spawnList = new ArrayList();
    @SideOnly(value=Side.CLIENT)
    private IIcon theIcon;

    public static void addMapping(String name, int c1, int c2) {
        spawnList.add(new EntityEggStuff("ThaumicHorizons." + name, c1, c2));
    }

    public ItemSpawnerEgg() {
        this.func_77627_a(true);
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        String s = ("" + StatCollector.func_74838_a((String)"item.monsterPlacer.name")).trim();
        String s1 = ItemSpawnerEgg.spawnList.get((int)par1ItemStack.func_77960_j()).name;
        if (s1 != null) {
            s = s + " " + StatCollector.func_74838_a((String)("entity." + s1 + ".name"));
        }
        return s;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int layer) {
        EntityEggStuff entityegginfo = spawnList.get(stack.func_77960_j());
        return entityegginfo != null ? entityegginfo.color2 : (layer == 0 ? entityegginfo.color1 : 0xFFFFFF);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        Entity entity;
        if (world.field_72995_K) {
            return true;
        }
        Block block = world.func_147439_a(x, y, z);
        x += Facing.field_71586_b[side];
        y += Facing.field_71587_c[side];
        z += Facing.field_71585_d[side];
        double d0 = 0.0;
        if (side == 1 && block.func_149645_b() == 11) {
            d0 = 0.5;
        }
        if ((entity = ItemSpawnerEgg.spawnCreature(world, stack.func_77960_j(), (double)x + 0.5, (double)y + d0, (double)z + 0.5)) != null) {
            if (entity instanceof EntityLivingBase && stack.func_82837_s()) {
                ((EntityLiving)entity).func_94058_c(stack.func_82833_r());
            }
            if (!player.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
            }
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (world.field_72995_K) {
            return stack;
        }
        MovingObjectPosition movingobjectposition = this.func_77621_a(world, player, true);
        if (movingobjectposition == null) {
            return stack;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            Entity entity;
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (!world.func_72962_a(player, i, j, k)) {
                return stack;
            }
            if (!player.func_82247_a(i, j, k, movingobjectposition.field_72310_e, stack)) {
                return stack;
            }
            if (world.func_147439_a(i, j, k) instanceof BlockLiquid && (entity = ItemSpawnerEgg.spawnCreature(world, stack.func_77960_j(), i, j, k)) != null) {
                if (entity instanceof EntityLivingBase && stack.func_82837_s()) {
                    ((EntityLiving)entity).func_94058_c(stack.func_82833_r());
                }
                if (!player.field_71075_bZ.field_75098_d) {
                    --stack.field_77994_a;
                }
            }
        }
        return stack;
    }

    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
        if (spawnList.get(par1) == null) {
            return null;
        }
        Entity entity = null;
        for (int j = 0; j < 1; ++j) {
            entity = EntityList.func_75620_a((String)ItemSpawnerEgg.spawnList.get((int)par1).name, (World)par0World);
            if (entity == null || !(entity instanceof EntityLivingBase)) continue;
            EntityLiving entityliving = (EntityLiving)entity;
            entity.func_70012_b(par2, par4, par6, MathHelper.func_76142_g((float)(par0World.field_73012_v.nextFloat() * 360.0f)), 0.0f);
            entityliving.field_70759_as = entityliving.field_70177_z;
            entityliving.field_70761_aq = entityliving.field_70177_z;
            entityliving.func_110161_a((IEntityLivingData)null);
            par0World.func_72838_d(entity);
            entityliving.func_70642_aH();
        }
        return entity;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return par2 > 0 ? this.theIcon : super.func_77618_c(par1, par2);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int a = 0; a < spawnList.size(); ++a) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, a));
        }
    }

    protected String func_111208_A() {
        return "spawn_egg";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.theIcon = par1IconRegister.func_94245_a(this.func_111208_A() + "_overlay");
    }

    static class EntityEggStuff {
        String name;
        int color1;
        int color2;

        public EntityEggStuff(String name, int color1, int color2) {
            this.name = name;
            this.color1 = color1;
            this.color2 = color2;
        }
    }
}

