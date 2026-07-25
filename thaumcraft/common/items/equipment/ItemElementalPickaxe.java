/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.equipment;

import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemElementalPickaxe
extends ItemPickaxe
implements IRepairable {
    public IIcon icon;

    public ItemElementalPickaxe(Item.ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of((Object)"pickaxe");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:elementalpick");
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

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!(player.field_70170_p.field_72995_K || entity instanceof EntityPlayer && !MinecraftServer.func_71276_C().func_71219_W())) {
            entity.func_70015_d(2);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        itemstack.func_77972_a(5, (EntityLivingBase)player);
        if (!world.field_72995_K) {
            world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wandfail", 0.2f, 0.2f + world.field_73012_v.nextFloat() * 0.2f);
            return super.func_77648_a(itemstack, player, world, x, y, z, side, par8, par9, par10);
        }
        Minecraft mc = Minecraft.func_71410_x();
        Thaumcraft.instance.renderEventHandler.startScan((Entity)player, x, y, z, System.currentTimeMillis() + 5000L, 8);
        player.func_71038_i();
        return super.func_77648_a(itemstack, player, world, x, y, z, side, par8, par9, par10);
    }
}

