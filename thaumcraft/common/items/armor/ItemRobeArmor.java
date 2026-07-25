/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;

public class ItemRobeArmor
extends ItemArmor
implements IRepairable,
IVisDiscountGear,
IRunicArmor {
    public IIcon iconChest;
    public IIcon iconLegs;
    public IIcon iconBoots;
    public IIcon iconChestOver;
    public IIcon iconLegsOver;
    public IIcon iconBootsOver;

    public ItemRobeArmor(ItemArmor.ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconChest = ir.func_94245_a("thaumcraft:clothchest");
        this.iconLegs = ir.func_94245_a("thaumcraft:clothlegs");
        this.iconBoots = ir.func_94245_a("thaumcraft:clothboots");
        this.iconChestOver = ir.func_94245_a("thaumcraft:clothchestover");
        this.iconLegsOver = ir.func_94245_a("thaumcraft:clothlegsover");
        this.iconBootsOver = ir.func_94245_a("thaumcraft:clothbootsover");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.field_77881_a == 1 ? this.iconChest : (this.field_77881_a == 2 ? this.iconLegs : this.iconBoots);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    public boolean func_82816_b_(ItemStack par1ItemStack) {
        return true;
    }

    public IIcon func_77618_c(int par1, int par2) {
        return par2 == 0 ? (this.field_77881_a == 1 ? this.iconChest : (this.field_77881_a == 2 ? this.iconLegs : this.iconBoots)) : (this.field_77881_a == 1 ? this.iconChestOver : (this.field_77881_a == 2 ? this.iconLegsOver : this.iconBootsOver));
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

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack.func_77973_b() == ConfigItems.itemChestRobe || stack.func_77973_b() == ConfigItems.itemBootsRobe) {
            return type == null ? "thaumcraft:textures/models/robes_1.png" : "thaumcraft:textures/models/robes_1_overlay.png";
        }
        if (stack.func_77973_b() == ConfigItems.itemLegsRobe) {
            return type == null ? "thaumcraft:textures/models/robes_2.png" : "thaumcraft:textures/models/robes_2_overlay.png";
        }
        return type == null ? "thaumcraft:textures/models/robes_1.png" : "thaumcraft:textures/models/robes_1_overlay.png";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 7)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return this.field_77881_a == 3 ? 1 : 2;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.field_72995_K && world.func_147439_a(x, y, z) == Blocks.field_150383_bp && world.func_72805_g(x, y, z) > 0) {
            this.func_82815_c(stack);
            world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) - 1, 2);
            world.func_147453_f(x, y, z, (Block)Blocks.field_150383_bp);
            return true;
        }
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}

