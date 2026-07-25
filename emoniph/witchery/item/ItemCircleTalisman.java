/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemCircleTalisman
extends ItemBase {
    @SideOnly(value=Side.CLIENT)
    IIcon item0;
    @SideOnly(value=Side.CLIENT)
    IIcon item1;
    @SideOnly(value=Side.CLIENT)
    IIcon item2;
    @SideOnly(value=Side.CLIENT)
    IIcon item3;
    @SideOnly(value=Side.CLIENT)
    IIcon item4;
    @SideOnly(value=Side.CLIENT)
    IIcon item5;
    @SideOnly(value=Side.CLIENT)
    IIcon item6;
    @SideOnly(value=Side.CLIENT)
    IIcon item7;
    @SideOnly(value=Side.CLIENT)
    IIcon item8;
    @SideOnly(value=Side.CLIENT)
    IIcon item9;

    public ItemCircleTalisman() {
        this.func_77625_d(16);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public String func_77667_c(ItemStack itemStack) {
        Integer damage = itemStack.func_77960_j();
        return damage.equals(0) ? this.func_77658_a() : this.func_77658_a() + "." + damage.toString();
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }

    public String func_77653_i(ItemStack itemStack) {
        String localizedName = super.func_77653_i(itemStack);
        int damage = itemStack.func_77960_j();
        return damage > 0 ? String.format("%s (%s)", localizedName, this.getChalkDisplayName(damage)) : localizedName;
    }

    private String getChalkDisplayName(int damage) {
        int small = damage & 7;
        int medium = damage >>> 3 & 7;
        int large = damage >>> 6 & 7;
        StringBuilder result = new StringBuilder();
        if (small > 0) {
            result.append(StatCollector.func_74838_a((String)("circletalisman.small." + Integer.valueOf(small).toString())));
            result.append(", ");
        }
        if (medium > 0) {
            result.append(StatCollector.func_74838_a((String)("circletalisman.medium." + Integer.valueOf(medium).toString())));
            result.append(", ");
        }
        if (large > 0) {
            result.append(StatCollector.func_74838_a((String)("circletalisman.large." + Integer.valueOf(large).toString())));
            result.append(", ");
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        super.func_94581_a(iconRegister);
        this.item0 = this.field_77791_bV;
        this.item1 = iconRegister.func_94245_a(this.func_111208_A() + ".1");
        this.item2 = iconRegister.func_94245_a(this.func_111208_A() + ".2");
        this.item3 = iconRegister.func_94245_a(this.func_111208_A() + ".3");
        this.item4 = iconRegister.func_94245_a(this.func_111208_A() + ".4");
        this.item5 = iconRegister.func_94245_a(this.func_111208_A() + ".5");
        this.item6 = iconRegister.func_94245_a(this.func_111208_A() + ".6");
        this.item7 = iconRegister.func_94245_a(this.func_111208_A() + ".7");
        this.item8 = iconRegister.func_94245_a(this.func_111208_A() + ".8");
        this.item9 = iconRegister.func_94245_a(this.func_111208_A() + ".9");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int damage) {
        int small = damage & 7;
        int medium = damage >>> 3 & 7;
        int large = damage >>> 6 & 7;
        switch (large > 0 ? large + 6 : (medium > 0 ? medium + 3 : small)) {
            default: {
                return this.item0;
            }
            case 1: {
                return this.item1;
            }
            case 2: {
                return this.item2;
            }
            case 3: {
                return this.item3;
            }
            case 4: {
                return this.item4;
            }
            case 5: {
                return this.item5;
            }
            case 6: {
                return this.item6;
            }
            case 7: {
                return this.item7;
            }
            case 8: {
                return this.item8;
            }
            case 9: 
        }
        return this.item9;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item itemID, CreativeTabs tab, List itemList) {
        itemList.add(new ItemStack(itemID, 1, 0));
        itemList.add(new ItemStack(itemID, 1, 1));
        itemList.add(new ItemStack(itemID, 1, 2));
        itemList.add(new ItemStack(itemID, 1, 3));
        itemList.add(new ItemStack(itemID, 1, 8));
        itemList.add(new ItemStack(itemID, 1, 16));
        itemList.add(new ItemStack(itemID, 1, 24));
        itemList.add(new ItemStack(itemID, 1, 64));
        itemList.add(new ItemStack(itemID, 1, 128));
        itemList.add(new ItemStack(itemID, 1, 192));
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (BlockSide.TOP.isEqual(side) && world.func_147439_a(posX, posY, posZ) == Witchery.Blocks.CIRCLE || Witchery.Blocks.CIRCLE.func_149718_j(world, posX, posY + 1, posZ)) {
            int damage = itemstack.func_77960_j();
            if (damage > 0) {
                if (!world.field_72995_K) {
                    int a = damage & 7;
                    int b = damage >>> 3 & 7;
                    int c = damage >>> 6 & 7;
                    boolean _ = false;
                    int[][] PATTERN = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, c, c, c, c, c, c, c, 0, 0, 0, 0, 0}, {0, 0, 0, 0, c, 0, 0, 0, 0, 0, 0, 0, c, 0, 0, 0, 0}, {0, 0, 0, c, 0, 0, b, b, b, b, b, 0, 0, c, 0, 0, 0}, {0, 0, c, 0, 0, b, 0, 0, 0, 0, 0, b, 0, 0, c, 0, 0}, {0, c, 0, 0, b, 0, 0, a, a, a, 0, 0, b, 0, 0, c, 0}, {0, c, 0, b, 0, 0, a, 0, 0, 0, a, 0, 0, b, 0, c, 0}, {0, c, 0, b, 0, a, 0, 0, 0, 0, 0, a, 0, b, 0, c, 0}, {0, c, 0, b, 0, a, 0, 0, 4, 0, 0, a, 0, b, 0, c, 0}, {0, c, 0, b, 0, a, 0, 0, 0, 0, 0, a, 0, b, 0, c, 0}, {0, c, 0, b, 0, 0, a, 0, 0, 0, a, 0, 0, b, 0, c, 0}, {0, c, 0, 0, b, 0, 0, a, a, a, 0, 0, b, 0, 0, c, 0}, {0, 0, c, 0, 0, b, 0, 0, 0, 0, 0, b, 0, 0, c, 0, 0}, {0, 0, 0, c, 0, 0, b, b, b, b, b, 0, 0, c, 0, 0, 0}, {0, 0, 0, 0, c, 0, 0, 0, 0, 0, 0, 0, c, 0, 0, 0, 0}, {0, 0, 0, 0, 0, c, c, c, c, c, c, c, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
                    int y = world.func_147439_a(posX, posY, posZ) == Witchery.Blocks.CIRCLE ? posY : posY + 1;
                    boolean fail = false;
                    for (int pass = 0; pass < 2 && !fail; ++pass) {
                        int offsetZ = (PATTERN.length - 1) / 2;
                        for (int z = 0; z < PATTERN.length - 1; ++z) {
                            int worldZ = posZ - offsetZ + z;
                            int offsetX = (PATTERN[z].length - 1) / 2;
                            block8: for (int x = 0; x < PATTERN[z].length; ++x) {
                                int worldX = posX - offsetX + x;
                                int item = PATTERN[PATTERN.length - 1 - z][x];
                                Material material = world.func_147439_a(worldX, y, worldZ).func_149688_o();
                                boolean solidBlock = material != null && (material.func_76218_k() || material.func_76220_a());
                                switch (item) {
                                    case 1: {
                                        if (!solidBlock && Witchery.Blocks.GLYPH_RITUAL.func_149718_j(world, worldX, y, worldZ)) {
                                            if (pass != 1) break;
                                            world.func_147465_d(worldX, y, worldZ, Witchery.Blocks.GLYPH_RITUAL, world.field_73012_v.nextInt(12), 3);
                                            break;
                                        }
                                        fail = true;
                                        break;
                                    }
                                    case 2: {
                                        if (!solidBlock && Witchery.Blocks.GLYPH_OTHERWHERE.func_149718_j(world, worldX, y, worldZ)) {
                                            if (pass != 1) break;
                                            world.func_147465_d(worldX, y, worldZ, Witchery.Blocks.GLYPH_OTHERWHERE, world.field_73012_v.nextInt(12), 3);
                                            break;
                                        }
                                        fail = true;
                                        break;
                                    }
                                    case 3: {
                                        if (!solidBlock && Witchery.Blocks.GLYPH_INFERNAL.func_149718_j(world, worldX, y, worldZ)) {
                                            if (pass != 1) break;
                                            world.func_147465_d(worldX, y, worldZ, Witchery.Blocks.GLYPH_INFERNAL, world.field_73012_v.nextInt(12), 3);
                                            break;
                                        }
                                        fail = true;
                                        break;
                                    }
                                    case 4: {
                                        if (y == posY) break;
                                        if (Witchery.Blocks.CIRCLE.func_149718_j(world, worldX, y, worldZ)) {
                                            if (pass != 1) break;
                                            world.func_147449_b(worldX, y, worldZ, Witchery.Blocks.CIRCLE);
                                            break;
                                        }
                                        fail = true;
                                        break;
                                    }
                                    default: {
                                        continue block8;
                                    }
                                }
                                if (pass != 1) continue;
                                ParticleEffect.SMOKE.send(SoundEffect.NONE, world, worldX, posY + 1, worldZ, 0.5, 1.0, 16);
                            }
                        }
                    }
                    if (fail) {
                        world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)world.field_73012_v.nextDouble() * 0.4f + 0.8f));
                    } else if (itemstack.field_77994_a > 1) {
                        ItemStack newStack = new ItemStack((Item)this);
                        if (!player.field_71071_by.func_70441_a(newStack)) {
                            world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t + 0.5, player.field_70163_u + 1.5, player.field_70161_v + 0.5, newStack));
                        } else if (player instanceof EntityPlayerMP) {
                            ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                        }
                        --itemstack.field_77994_a;
                        if (itemstack.field_77994_a <= 0) {
                            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                        }
                    } else {
                        itemstack.func_77964_b(0);
                    }
                } else {
                    world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)world.field_73012_v.nextDouble() * 0.4f + 0.8f));
                }
            }
            return true;
        }
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
        return true;
    }
}

