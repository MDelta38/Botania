/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntitySignalFlare;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ModItems;

public class ItemSignalFlare
extends ItemMod {
    IIcon[] icons;
    private static final String TAG_COLOR = "color";

    public ItemSignalFlare() {
        this.func_77625_d(1);
        this.setNoRepair();
        this.func_77656_e(200);
        this.func_77655_b("signalFlare");
    }

    public boolean func_77662_d() {
        return true;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.func_77960_j() == 0) {
            if (par2World.field_72995_K) {
                par3EntityPlayer.func_71038_i();
            } else {
                EntitySignalFlare flare = new EntitySignalFlare(par2World);
                flare.func_70107_b(par3EntityPlayer.field_70165_t, par3EntityPlayer.field_70163_u, par3EntityPlayer.field_70161_v);
                flare.setColor(ItemSignalFlare.getColor(par1ItemStack));
                par2World.func_72956_a((Entity)par3EntityPlayer, "random.explode", 40.0f, (1.0f + (par2World.field_73012_v.nextFloat() - par2World.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
                par2World.func_72838_d((Entity)flare);
                int stunned = 0;
                int range = 5;
                List entities = par2World.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(par3EntityPlayer.field_70165_t - (double)range), (double)(par3EntityPlayer.field_70163_u - (double)range), (double)(par3EntityPlayer.field_70161_v - (double)range), (double)(par3EntityPlayer.field_70165_t + (double)range), (double)(par3EntityPlayer.field_70163_u + (double)range), (double)(par3EntityPlayer.field_70161_v + (double)range)));
                for (EntityLivingBase entity : entities) {
                    if (entity == par3EntityPlayer || entity instanceof EntityPlayer && MinecraftServer.func_71276_C() != null && !MinecraftServer.func_71276_C().func_71219_W()) continue;
                    entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 50, 5));
                    ++stunned;
                }
                if (stunned >= 100) {
                    par3EntityPlayer.func_71064_a((StatBase)ModAchievements.signalFlareStun, 1);
                }
            }
            par1ItemStack.func_77972_a(200, (EntityLivingBase)par3EntityPlayer);
        }
        return par1ItemStack;
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par1ItemStack.func_77951_h()) {
            par1ItemStack.func_77964_b(par1ItemStack.func_77960_j() - 1);
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icons[Math.min(1, pass)];
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par2 == 0) {
            return 0xFFFFFF;
        }
        int colorv = ItemSignalFlare.getColor(par1ItemStack);
        if (colorv >= EntitySheep.field_70898_d.length || colorv < 0) {
            return 0xFFFFFF;
        }
        float[] color = EntitySheep.field_70898_d[ItemSignalFlare.getColor(par1ItemStack)];
        return new Color(color[0], color[1], color[2]).getRGB();
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(ItemSignalFlare.forColor(i));
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        int storedColor = ItemSignalFlare.getColor(par1ItemStack);
        par3List.add(String.format(StatCollector.func_74838_a((String)"botaniamisc.flareColor"), StatCollector.func_74838_a((String)("botania.color" + storedColor))));
    }

    public static ItemStack forColor(int color) {
        ItemStack stack = new ItemStack(ModItems.signalFlare);
        ItemNBTHelper.setInt(stack, TAG_COLOR, color);
        return stack;
    }

    public static int getColor(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_COLOR, 0xFFFFFF);
    }
}

