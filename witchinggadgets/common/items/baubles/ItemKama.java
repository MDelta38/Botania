/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package witchinggadgets.common.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import witchinggadgets.client.render.ModelKama;
import witchinggadgets.common.items.baubles.ItemCloak;

public class ItemKama
extends ItemCloak
implements IBauble {
    IIcon overlay;

    @Override
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:kama");
        this.iconRaven = iconRegister.func_94245_a("witchinggadgets:kama_raven");
        this.iconWolf = iconRegister.func_94245_a("witchinggadgets:kama_wolf");
        this.overlay = iconRegister.func_94245_a("witchinggadgets:kama_overlay");
    }

    public IIcon func_77618_c(int meta, int pass) {
        if (pass == 1) {
            return this.overlay;
        }
        return super.func_77618_c(meta, pass);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77618_c(stack.func_77960_j(), pass);
    }

    public boolean func_77623_v() {
        return true;
    }

    @Override
    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 1) {
            return 0xFFFFFF;
        }
        return super.func_82790_a(stack, pass);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return new ModelKama(this.getColor(itemStack));
    }

    @Override
    public int getSlot(ItemStack stack) {
        return -1;
    }

    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.BELT;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        ItemStack cosmetic;
        if (stack.func_77942_o() && stack.func_77978_p().func_74767_n("noGlide")) {
            list.add(StatCollector.func_74838_a((String)"wg.desc.noGlide"));
        }
        list.add(StatCollector.func_74837_a((String)("wg.desc.gearSlot.bauble." + this.getBaubleType(stack)), (Object[])new Object[0]));
        if (Loader.isModLoaded((String)"Botania") && (cosmetic = this.getCosmeticItem(stack)) != null) {
            list.add(String.format(StatCollector.func_74838_a((String)"botaniamisc.hasCosmetic"), cosmetic.func_82833_r()).replaceAll("&", "\u00a7"));
        }
    }

    public void onEquipped(ItemStack stack, EntityLivingBase living) {
        if (living instanceof EntityPlayer) {
            this.onItemEquipped((EntityPlayer)living, stack);
        }
    }

    public void onUnequipped(ItemStack stack, EntityLivingBase living) {
        if (living instanceof EntityPlayer) {
            this.onItemUnequipped((EntityPlayer)living, stack);
        }
    }

    public void onWornTick(ItemStack stack, EntityLivingBase living) {
        if (living instanceof EntityPlayer) {
            this.onItemTicked((EntityPlayer)living, stack);
        }
    }

    @Override
    public void onTravelGearTick(EntityPlayer player, ItemStack stack) {
    }

    @Override
    public void onTravelGearEquip(EntityPlayer player, ItemStack stack) {
    }

    @Override
    public void onTravelGearUnequip(EntityPlayer player, ItemStack stack) {
    }

    public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }
}

