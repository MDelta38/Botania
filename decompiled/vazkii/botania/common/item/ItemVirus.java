/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemVirus
extends ItemMod {
    IIcon[] icons;
    private static final int SUBTYPES = 2;

    public ItemVirus() {
        this.func_77655_b("virus");
        this.func_77627_a(true);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public boolean func_111207_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase) {
        EntityHorse horse;
        if (par3EntityLivingBase instanceof EntityHorse && (horse = (EntityHorse)par3EntityLivingBase).func_110265_bP() != 3 && horse.func_110265_bP() != 4 && horse.func_110248_bS()) {
            horse.func_110214_p(3 + par1ItemStack.func_77960_j());
            BaseAttributeMap attributes = horse.func_110140_aT();
            IAttributeInstance movementSpeed = attributes.func_111151_a(SharedMonsterAttributes.field_111263_d);
            IAttributeInstance health = attributes.func_111151_a(SharedMonsterAttributes.field_111267_a);
            health.func_111121_a(new AttributeModifier("Ermergerd Virus D:", health.func_111125_b(), 0));
            movementSpeed.func_111121_a(new AttributeModifier("Ermergerd Virus D:", movementSpeed.func_111125_b(), 0));
            IAttributeInstance jumpHeight = attributes.func_111151_a((IAttribute)ReflectionHelper.getPrivateValue(EntityHorse.class, null, (String[])LibObfuscation.HORSE_JUMP_STRENGTH));
            jumpHeight.func_111121_a(new AttributeModifier("Ermergerd Virus D:", jumpHeight.func_111125_b() * 0.5, 0));
            par2EntityPlayer.field_70170_p.func_72980_b(par3EntityLivingBase.field_70165_t + 0.5, par3EntityLivingBase.field_70163_u + 0.5, par3EntityLivingBase.field_70161_v + 0.5, "mob.zombie.remedy", 1.0f + par3EntityLivingBase.field_70170_p.field_73012_v.nextFloat(), par3EntityLivingBase.field_70170_p.field_73012_v.nextFloat() * 0.7f + 1.3f, false);
            --par1ItemStack.field_77994_a;
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        EntityHorse horse;
        EntityLivingBase entity = event.entityLiving;
        if (entity.field_70154_o != null && entity.field_70154_o instanceof EntityLivingBase) {
            entity = (EntityLivingBase)entity.field_70154_o;
        }
        if (entity instanceof EntityHorse && event.source == DamageSource.field_76379_h && ((horse = (EntityHorse)entity).func_110265_bP() == 3 || horse.func_110265_bP() == 4) && horse.func_110248_bS()) {
            event.setCanceled(true);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < 2; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }
}

