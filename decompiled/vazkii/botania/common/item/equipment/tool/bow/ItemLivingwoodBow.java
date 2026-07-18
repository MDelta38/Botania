/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.registry.GameRegistry
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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 */
package vazkii.botania.common.item.equipment.tool.bow;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemLivingwoodBow
extends ItemBow
implements IManaUsingItem {
    public static final int MANA_PER_DAMAGE = 40;
    IIcon[] pullIcons = new IIcon[3];

    public ItemLivingwoodBow() {
        this("livingwoodBow");
    }

    public ItemLivingwoodBow(String name) {
        this.func_77637_a(BotaniaCreativeTab.INSTANCE);
        this.func_77655_b(name);
        this.func_77656_e(500);
        this.func_77664_n();
    }

    public Item func_77655_b(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.func_77655_b(par1Str);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack).replaceAll("item.", "item.botania:");
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        ArrowNockEvent event = new ArrowNockEvent(p_77659_3_, p_77659_1_);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return event.result;
        }
        if (this.canFire(p_77659_1_, p_77659_2_, p_77659_3_, 0)) {
            p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        }
        return p_77659_1_;
    }

    public void func_77615_a(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        boolean infinity;
        int j = (int)((float)(this.func_77626_a(p_77615_1_) - p_77615_4_) * this.chargeVelocityMultiplier());
        ArrowLooseEvent event = new ArrowLooseEvent(p_77615_3_, p_77615_1_, j);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return;
        }
        j = event.charge;
        boolean flag = this.canFire(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_);
        boolean bl = infinity = EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)p_77615_1_) > 0;
        if (flag) {
            int l;
            int k;
            float f = (float)j / 20.0f;
            if ((double)(f = (f * f + f * 2.0f) / 3.0f) < 0.1) {
                return;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            EntityArrow entityarrow = this.makeArrow(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_, f);
            if (f == 1.0f) {
                entityarrow.func_70243_d(true);
            }
            if ((k = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)p_77615_1_)) > 0) {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)k * 0.5 + 0.5);
            }
            if ((l = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)p_77615_1_)) > 0) {
                entityarrow.func_70240_a(l);
            }
            if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77343_v.field_77352_x, (ItemStack)p_77615_1_) > 0) {
                entityarrow.func_70015_d(100);
            }
            ToolCommons.damageItem(p_77615_1_, 1, (EntityLivingBase)p_77615_3_, 40);
            p_77615_2_.func_72956_a((Entity)p_77615_3_, "random.bow", 1.0f, 1.0f / (field_77697_d.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            this.onFire(p_77615_1_, p_77615_2_, p_77615_3_, p_77615_4_, infinity, entityarrow);
            if (!p_77615_2_.field_72995_K) {
                p_77615_2_.func_72838_d((Entity)entityarrow);
            }
        }
    }

    float chargeVelocityMultiplier() {
        return 1.0f;
    }

    boolean postsEvent() {
        return true;
    }

    EntityArrow makeArrow(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_, float f) {
        return new EntityArrow(p_77615_2_, (EntityLivingBase)p_77615_3_, f * 2.0f);
    }

    boolean canFire(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        return p_77615_3_.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)p_77615_1_) > 0 || p_77615_3_.field_71071_by.func_146028_b(Items.field_151032_g);
    }

    void onFire(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_, boolean infinity, EntityArrow arrow) {
        if (infinity) {
            arrow.field_70251_a = 2;
        } else {
            p_77615_3_.field_71071_by.func_146026_a(Items.field_151032_g);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        for (int i = 0; i < 3; ++i) {
            this.pullIcons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i + 1);
        }
    }

    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (!world.field_72995_K && player instanceof EntityPlayer && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer)player, 80, true)) {
            stack.func_77964_b(stack.func_77960_j() - 1);
        }
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 3 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (stack != usingItem) {
            return this.field_77791_bV;
        }
        int j = (int)((float)(this.func_77626_a(stack) - useRemaining) * this.chargeVelocityMultiplier());
        if (j >= 18) {
            return this.pullIcons[2];
        }
        if (j > 13) {
            return this.pullIcons[1];
        }
        if (j > 0) {
            return this.pullIcons[0];
        }
        return this.field_77791_bV;
    }
}

