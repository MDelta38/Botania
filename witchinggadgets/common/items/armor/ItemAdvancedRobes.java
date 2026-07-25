/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.IRepairable
 *  thaumcraft.api.IVisDiscountGear
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.ModelRobeSkirted;

public class ItemAdvancedRobes
extends ItemArmor
implements IRepairable,
IVisDiscountGear {
    public IIcon iconChest;
    public IIcon iconChestOverlay;
    public IIcon iconLegs;
    public IIcon iconLegsOverlay;

    public ItemAdvancedRobes(ItemArmor.ArmorMaterial armorMaterial, int par3, int par4) {
        super(armorMaterial, par3, par4);
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.iconChest = iconRegister.func_94245_a("witchinggadgets:chestplateRobeAdvanced");
        this.iconChestOverlay = iconRegister.func_94245_a("witchinggadgets:chestplateRobeAdvanced_overlay");
        this.iconLegs = iconRegister.func_94245_a("witchinggadgets:leggingsRobeAdvanced");
        this.iconLegsOverlay = iconRegister.func_94245_a("witchinggadgets:leggingsRobeAdvanced_overlay");
    }

    public IIcon func_77617_a(int par1) {
        return this.field_77881_a == 2 ? this.iconLegs : this.iconChest;
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon func_77618_c(int par1, int pass) {
        switch (pass) {
            case 0: {
                switch (this.field_77881_a) {
                    case 1: {
                        return this.iconChest;
                    }
                    case 2: {
                        return this.iconLegs;
                    }
                }
            }
            case 1: {
                switch (this.field_77881_a) {
                    case 1: {
                        return this.iconChestOverlay;
                    }
                    case 2: {
                        return this.iconLegsOverlay;
                    }
                }
            }
        }
        return this.iconChest;
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (slot == 1) {
            return type == null ? "witchinggadgets:textures/models/advancedRobes_1.png" : "witchinggadgets:textures/models/advancedRobes_1_overlay.png";
        }
        if (slot == 2) {
            return type == null ? "witchinggadgets:textures/models/advancedRobes_2.png" : "witchinggadgets:textures/models/advancedRobes_2_overlay.png";
        }
        return type == null ? "witchinggadgets:textures/models/advancedRobes_1.png" : "witchinggadgets:textures/models/advancedRobes_1_overlay.png";
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + this.getVisDiscount(stack, par2EntityPlayer, null) + "%");
    }

    public boolean func_82816_b_(ItemStack par1ItemStack) {
        return true;
    }

    public int func_82814_b(ItemStack par1ItemStack) {
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound == null) {
            return 6961280;
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        return nbttagcompound1 == null ? 6961280 : (nbttagcompound1.func_74764_b("color") ? nbttagcompound1.func_74762_e("color") : 6961280);
    }

    public void func_82815_c(ItemStack par1ItemStack) {
        NBTTagCompound nbttagcompound1;
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound != null && (nbttagcompound1 = nbttagcompound.func_74775_l("display")).func_74764_b("color")) {
            nbttagcompound1.func_82580_o("color");
        }
    }

    public void func_82813_b(ItemStack par1ItemStack, int par2) {
        NBTTagCompound nbttagcompound = par1ItemStack.func_77978_p();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            par1ItemStack.func_77982_d(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        if (!nbttagcompound.func_74764_b("display")) {
            nbttagcompound.func_74782_a("display", (NBTBase)nbttagcompound1);
        }
        nbttagcompound1.func_74768_a("color", par2);
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (armorSlot == 1) {
            return ModelRobeSkirted.getModel();
        }
        return null;
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return this.field_77881_a == 2 ? 4 : 5;
    }
}

