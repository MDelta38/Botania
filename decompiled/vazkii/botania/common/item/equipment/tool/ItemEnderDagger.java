/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool;

import java.awt.Color;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;

public class ItemEnderDagger
extends ItemManasteelSword {
    IIcon iconFront;
    IIcon iconOverlay;

    public ItemEnderDagger() {
        super(BotaniaAPI.manasteelToolMaterial, "enderDagger");
        this.func_77656_e(69);
        this.setNoRepair();
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.iconFront = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconOverlay = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public boolean func_77623_v() {
        return true;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return pass == 0 ? this.iconFront : this.iconOverlay;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par2 == 0) {
            return 0xFFFFFF;
        }
        return Color.HSBtoRGB(0.75f, 1.0f, 1.5f - (float)Math.min(1.0, Math.sin((double)System.currentTimeMillis() / 100.0) * 0.5 + (double)1.2f));
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.none;
    }

    @Override
    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
        if (par2EntityLivingBase instanceof EntityEnderman && par3EntityLivingBase instanceof EntityPlayer) {
            par2EntityLivingBase.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)par3EntityLivingBase)), 20.0f);
        }
        par1ItemStack.func_77972_a(1, par3EntityLivingBase);
        return true;
    }

    @Override
    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return false;
    }
}

