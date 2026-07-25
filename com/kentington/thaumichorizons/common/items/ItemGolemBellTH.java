/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagByteArray
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagIntArray
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.IIcon
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.entities.golems.EntityTravelingTrunk
 *  thaumcraft.common.entities.golems.Marker
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.items.ItemGolemPlacer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.IIcon;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.golems.ItemGolemBell;
import thaumcraft.common.entities.golems.Marker;

public class ItemGolemBellTH
extends ItemGolemBell {
    public ItemGolemBellTH() {
        this.func_77627_a(false);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77625_d(1);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return ((ItemGolemPlacer)ThaumicHorizons.itemGolemPlacer).newBell;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.GolemBell";
    }

    @Override
    public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        if (target instanceof EntityGolemBase) {
            if (stack.func_77942_o()) {
                stack.func_77978_p().func_82580_o("golemid");
                stack.func_77978_p().func_82580_o("markers");
                stack.func_77978_p().func_82580_o("golemhomex");
                stack.func_77978_p().func_82580_o("golemhomey");
                stack.func_77978_p().func_82580_o("golemhomez");
                stack.func_77978_p().func_82580_o("golemhomeface");
            }
            if (target.field_70170_p.field_72995_K) {
                if (player != null) {
                    player.func_71038_i();
                }
            } else {
                ArrayList markers = ((EntityGolemBase)target).getMarkers();
                NBTTagList tl = new NBTTagList();
                for (Marker l : markers) {
                    NBTTagCompound nbtc = new NBTTagCompound();
                    nbtc.func_74768_a("x", l.x);
                    nbtc.func_74768_a("y", l.y);
                    nbtc.func_74768_a("z", l.z);
                    nbtc.func_74768_a("dim", l.dim);
                    nbtc.func_74774_a("side", l.side);
                    nbtc.func_74774_a("color", l.color);
                    tl.func_74742_a((NBTBase)nbtc);
                }
                stack.func_77983_a("markers", (NBTBase)tl);
                stack.func_77978_p().func_74768_a("golemid", target.func_145782_y());
                stack.func_77978_p().func_74768_a("golemhomex", ((EntityGolemBase)target).func_110172_bL().field_71574_a);
                stack.func_77978_p().func_74768_a("golemhomey", ((EntityGolemBase)target).func_110172_bL().field_71572_b);
                stack.func_77978_p().func_74768_a("golemhomez", ((EntityGolemBase)target).func_110172_bL().field_71573_c);
                stack.func_77978_p().func_74768_a("golemhomeface", ((EntityGolemBase)target).homeFacing);
                target.field_70170_p.func_72956_a((Entity)target, "random.orb", 0.7f, 1.0f + target.field_70170_p.field_73012_v.nextFloat() * 0.1f);
                if (player != null && player.field_71075_bZ.field_75098_d) {
                    player.func_70062_b(0, stack.func_77946_l());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityTravelingTrunk && !entity.field_70128_L) {
            byte upgrade = (byte)((EntityTravelingTrunk)entity).getUpgrade();
            if (upgrade == 3 && !((EntityTravelingTrunk)entity).func_152113_b().equals(player.func_70005_c_())) {
                return false;
            }
            if (entity.field_70170_p.field_72995_K && entity instanceof EntityLiving) {
                ((EntityLiving)entity).func_70656_aK();
                return false;
            }
            ItemStack dropped = new ItemStack(ConfigItems.itemTrunkSpawner);
            if (player.func_70093_af()) {
                if (upgrade > -1 && entity.field_70170_p.field_73012_v.nextBoolean()) {
                    ((EntityTravelingTrunk)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, (int)upgrade), 0.5f);
                }
            } else {
                if (((EntityTravelingTrunk)entity).func_94056_bM()) {
                    dropped.func_151001_c(((EntityTravelingTrunk)entity).func_94057_bL());
                }
                dropped.func_77983_a("upgrade", (NBTBase)new NBTTagByte(upgrade));
                if (upgrade == 4) {
                    dropped.func_77983_a("inventory", (NBTBase)((EntityTravelingTrunk)entity).inventory.writeToNBT(new NBTTagList()));
                }
            }
            ((EntityTravelingTrunk)entity).func_70099_a(dropped, 0.5f);
            if (upgrade != 4 || player.func_70093_af()) {
                ((EntityTravelingTrunk)entity).inventory.dropAllItems();
            }
            entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.func_70106_y();
            return true;
        }
        if (entity instanceof EntityGolemBase && !(entity instanceof EntityGolemTH) && !entity.field_70128_L) {
            if (entity.field_70170_p.field_72995_K && entity instanceof EntityLiving) {
                ((EntityLiving)entity).func_70656_aK();
                return false;
            }
            int type = ((EntityGolemBase)entity).golemType.ordinal();
            String deco = ((EntityGolemBase)entity).decoration;
            byte core = ((EntityGolemBase)entity).getCore();
            byte[] upgrades = ((EntityGolemBase)entity).upgrades;
            boolean advanced = ((EntityGolemBase)entity).advanced;
            ItemStack dropped = new ItemStack(ConfigItems.itemGolemPlacer, 1, type);
            if (advanced) {
                dropped.func_77983_a("advanced", (NBTBase)new NBTTagByte(1));
            }
            if (player.func_70093_af()) {
                if (core > -1) {
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemCore, 1, (int)core), 0.5f);
                }
                for (byte b : upgrades) {
                    if (b <= -1 || !entity.field_70170_p.field_73012_v.nextBoolean()) continue;
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, (int)b), 0.5f);
                }
            } else {
                if (((EntityGolemBase)entity).func_94056_bM()) {
                    dropped.func_151001_c(((EntityGolemBase)entity).func_94057_bL());
                }
                if (deco.length() > 0) {
                    dropped.func_77983_a("deco", (NBTBase)new NBTTagString(deco));
                }
                if (core > -1) {
                    dropped.func_77983_a("core", (NBTBase)new NBTTagByte(core));
                }
                dropped.func_77983_a("upgrades", (NBTBase)new NBTTagByteArray(upgrades));
                ArrayList markers = ((EntityGolemBase)entity).getMarkers();
                NBTTagList tl = new NBTTagList();
                for (Marker l : markers) {
                    NBTTagCompound nbtc = new NBTTagCompound();
                    nbtc.func_74768_a("x", l.x);
                    nbtc.func_74768_a("y", l.y);
                    nbtc.func_74768_a("z", l.z);
                    nbtc.func_74768_a("dim", l.dim);
                    nbtc.func_74774_a("side", l.side);
                    nbtc.func_74774_a("color", l.color);
                    tl.func_74742_a((NBTBase)nbtc);
                }
                dropped.func_77983_a("markers", (NBTBase)tl);
                dropped.func_77983_a("Inventory", (NBTBase)((EntityGolemBase)entity).inventory.writeToNBT(new NBTTagList()));
            }
            ((EntityGolemBase)entity).func_70099_a(dropped, 0.5f);
            ((EntityGolemBase)entity).dropStuff();
            entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.func_70106_y();
            return true;
        }
        if (entity instanceof EntityGolemTH && !entity.field_70128_L) {
            if (entity.field_70170_p.field_72995_K && entity instanceof EntityLiving) {
                ((EntityLiving)entity).func_70656_aK();
                return false;
            }
            EntityGolemTH golem = (EntityGolemTH)entity;
            if (golem.getCore() == -1) {
                golem.ticksAlive = 0;
                return true;
            }
            int type = golem.type.ordinal();
            String deco = golem.decoration;
            byte core = golem.getCore();
            byte[] upgrades = golem.upgrades;
            int[] nArray = new int[2];
            Block cfr_ignored_0 = golem.blocky;
            nArray[0] = Block.func_149682_b((Block)golem.blocky);
            nArray[1] = golem.md;
            int[] blockData = nArray;
            boolean advanced = golem.advanced;
            ItemStack dropped = new ItemStack(ThaumicHorizons.itemGolemPlacer, 1, type);
            if (advanced) {
                dropped.func_77983_a("advanced", (NBTBase)new NBTTagByte(1));
            }
            if (player.func_70093_af()) {
                if (core > -1) {
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemCore, 1, (int)core), 0.5f);
                }
                for (byte b : upgrades) {
                    if (b <= -1 || !entity.field_70170_p.field_73012_v.nextBoolean()) continue;
                    ((EntityGolemBase)entity).func_70099_a(new ItemStack(ConfigItems.itemGolemUpgrade, 1, (int)b), 0.5f);
                }
                golem.die();
                return true;
            }
            if (((EntityGolemBase)entity).func_94056_bM()) {
                dropped.func_151001_c(((EntityGolemBase)entity).func_94057_bL());
            }
            if (deco.length() > 0) {
                dropped.func_77983_a("deco", (NBTBase)new NBTTagString(deco));
            }
            if (core > -1) {
                dropped.func_77983_a("core", (NBTBase)new NBTTagByte(core));
            }
            dropped.func_77983_a("upgrades", (NBTBase)new NBTTagByteArray(upgrades));
            dropped.func_77983_a("block", (NBTBase)new NBTTagIntArray(blockData));
            dropped.field_77990_d.func_74757_a("berserk", golem.berserk);
            dropped.field_77990_d.func_74757_a("explosive", golem.kaboom);
            ArrayList markers = ((EntityGolemBase)entity).getMarkers();
            NBTTagList tl = new NBTTagList();
            for (Marker l : markers) {
                NBTTagCompound nbtc = new NBTTagCompound();
                nbtc.func_74768_a("x", l.x);
                nbtc.func_74768_a("y", l.y);
                nbtc.func_74768_a("z", l.z);
                nbtc.func_74768_a("dim", l.dim);
                nbtc.func_74774_a("side", l.side);
                nbtc.func_74774_a("color", l.color);
                tl.func_74742_a((NBTBase)nbtc);
            }
            dropped.func_77983_a("markers", (NBTBase)tl);
            dropped.func_77983_a("Inventory", (NBTBase)((EntityGolemBase)entity).inventory.writeToNBT(new NBTTagList()));
            ((EntityGolemBase)entity).func_70099_a(dropped, 0.5f);
            ((EntityGolemBase)entity).dropStuff();
            entity.field_70170_p.func_72956_a(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.func_70106_y();
            return true;
        }
        return false;
    }
}

