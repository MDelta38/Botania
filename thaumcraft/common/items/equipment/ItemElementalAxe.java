/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.equipment;

import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityFollowingItem;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockBubble;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;

public class ItemElementalAxe
extends ItemAxe
implements IRepairable {
    public IIcon icon;
    boolean alternateServer = false;
    boolean alternateClient = false;
    public static ArrayList<List> oreDictLogs = new ArrayList();

    public ItemElementalAxe(Item.ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of((Object)"axe");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:elementalaxe");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public EnumAction func_77661_b(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack p_77626_1_) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        return p_77659_1_;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        ArrayList<Entity> stuff = EntityUtils.getEntitiesInRange(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, (Entity)player, EntityItem.class, 10.0);
        if (stuff != null && stuff.size() > 0) {
            for (Entity e : stuff) {
                if (e instanceof EntityFollowingItem && ((EntityFollowingItem)e).target != null || e.field_70128_L || !(e instanceof EntityItem)) continue;
                double d6 = e.field_70165_t - player.field_70165_t;
                double d8 = e.field_70163_u - player.field_70163_u + (double)(player.field_70131_O / 2.0f);
                double d10 = e.field_70161_v - player.field_70161_v;
                double d11 = MathHelper.func_76133_a((double)(d6 * d6 + d8 * d8 + d10 * d10));
                double d13 = 0.3;
                e.field_70159_w -= (d6 /= d11) * d13;
                e.field_70181_x -= (d8 /= d11) * d13;
                e.field_70179_y -= (d10 /= d11) * d13;
                if (e.field_70159_w > 0.35) {
                    e.field_70159_w = 0.35;
                }
                if (e.field_70159_w < -0.35) {
                    e.field_70159_w = -0.35;
                }
                if (e.field_70181_x > 0.35) {
                    e.field_70181_x = 0.35;
                }
                if (e.field_70181_x < -0.35) {
                    e.field_70181_x = -0.35;
                }
                if (e.field_70179_y > 0.35) {
                    e.field_70179_y = 0.35;
                }
                if (e.field_70179_y < -0.35) {
                    e.field_70179_y = -0.35;
                }
                Thaumcraft.proxy.crucibleBubble(player.field_70170_p, (float)e.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125f, (float)e.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125f, (float)e.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125f, 0.33f, 0.33f, 1.0f);
            }
        }
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        World world = player.field_70170_p;
        Block bi = world.func_147439_a(x, y, z);
        if (!player.func_70093_af() && Utils.isWoodLog((IBlockAccess)world, x, y, z)) {
            if (!world.field_72995_K) {
                BlockUtils.breakFurthestBlock(world, x, y, z, bi, player, true, 10);
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockBubble(x, y, z, new Color(0.33f, 0.33f, 1.0f).getRGB()), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)x, (double)y, (double)z, 32.0));
                world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:bubble", 0.15f, 1.0f);
            }
            itemstack.func_77972_a(1, (EntityLivingBase)player);
            return true;
        }
        return super.onBlockStartBreak(itemstack, x, y, z, player);
    }
}

