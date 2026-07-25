/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.equipment;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IWarpingGear;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemVoidSword
extends ItemSword
implements IRepairable,
IWarpingGear {
    public IIcon icon;

    public ItemVoidSword(Item.ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:voidsword");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 15)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
        if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
            stack.func_77972_a(-1, (EntityLivingBase)entity);
        }
    }

    public boolean func_77644_a(ItemStack is, EntityLivingBase target, EntityLivingBase hitter) {
        if (!(target.field_70170_p.field_72995_K || target instanceof EntityPlayer && hitter instanceof EntityPlayer && !MinecraftServer.func_71276_C().func_71219_W())) {
            try {
                target.func_70690_d(new PotionEffect(Potion.field_76437_t.func_76396_c(), 60));
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        return super.func_77644_a(is, target, hitter);
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 1;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"enchantment.special.sapless"));
        super.func_77624_a(stack, player, list, par4);
    }
}

