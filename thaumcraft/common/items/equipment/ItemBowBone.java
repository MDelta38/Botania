/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 */
package thaumcraft.common.items.equipment;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;

public class ItemBowBone
extends ItemBow
implements IRepairable {
    public static final String[] bowPullIconNameArray = new String[]{"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconArray;

    public ItemBowBone() {
        this.field_77777_bU = 1;
        this.func_77656_e(512);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int ticks = this.func_77626_a(stack) - count;
        if (ticks > 18) {
            player.func_71034_by();
        }
    }

    public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        boolean flag;
        int j = this.func_77626_a(par1ItemStack) - par4;
        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return;
        }
        j = event.charge;
        boolean bl = flag = par3EntityPlayer.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)par1ItemStack) > 0;
        if (flag || par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g)) {
            int l;
            float f = (float)j / 10.0f;
            if ((double)(f = (f * f + f * 2.0f) / 3.0f) < 0.1) {
                return;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            EntityArrow entityarrow = new EntityArrow(par2World, (EntityLivingBase)par3EntityPlayer, f * 2.5f);
            entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5);
            int k = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)par1ItemStack);
            if (k > 0) {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)k * 0.5 + 0.5);
            }
            if ((l = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)par1ItemStack)) > 0) {
                entityarrow.func_70240_a(l);
            }
            if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77343_v.field_77352_x, (ItemStack)par1ItemStack) > 0) {
                entityarrow.func_70015_d(100);
            }
            par1ItemStack.func_77972_a(1, (EntityLivingBase)par3EntityPlayer);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.bow", 1.0f, 1.0f / (field_77697_d.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            if (flag) {
                entityarrow.field_70251_a = 2;
            } else {
                par3EntityPlayer.field_71071_by.func_146026_a(Items.field_151032_g);
            }
            if (!par2World.field_72995_K) {
                par2World.func_72838_d((Entity)entityarrow);
            }
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return event.result;
        }
        if (par3EntityPlayer.field_71075_bZ.field_75098_d || par3EntityPlayer.field_71071_by.func_146028_b(Items.field_151032_g) || EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)par1ItemStack) > 0) {
            par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        }
        return par1ItemStack;
    }

    public int func_77619_b() {
        return 3;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a("thaumcraft:bonebow");
        this.iconArray = new IIcon[bowPullIconNameArray.length];
        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IconRegister.func_94245_a("thaumcraft:bonebow_" + bowPullIconNameArray[i]);
        }
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        int j = stack.func_77988_m() - useRemaining;
        if (usingItem == null) {
            return this.field_77791_bV;
        }
        if (j >= 13) {
            return this.func_94599_c(2);
        }
        if (j > 7) {
            return this.func_94599_c(1);
        }
        if (j > 0) {
            return this.func_94599_c(0);
        }
        return this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_94599_c(int par1) {
        return this.iconArray[par1];
    }
}

