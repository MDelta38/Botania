/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.tiles.TileMirror
 */
package com.kentington.thaumichorizons.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.IRunicArmor;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileMirror;

public class ItemAmuletMirror
extends Item
implements IBauble,
IRunicArmor {
    public IIcon icon;

    public ItemAmuletMirror() {
        this.field_77777_bU = 1;
        this.canRepair = false;
        this.func_77656_e(0);
        this.func_77637_a(ThaumicHorizons.tabTH);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:amuletmirror");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.amuletMirror";
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }

    public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
    }

    public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
    }

    public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
    }

    public boolean func_77651_p() {
        return true;
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return par1ItemStack.func_77942_o();
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        Block bi = world.func_147439_a(x, y, z);
        if (bi == ConfigBlocks.blockMirror) {
            if (world.field_72995_K) {
                player.func_71038_i();
                return super.onItemUseFirst(itemstack, player, world, x, y, z, par7, par8, par9, par10);
            }
            TileEntity tm = world.func_147438_o(x, y, z);
            if (tm != null && tm instanceof TileMirror) {
                itemstack.func_77983_a("linkX", (NBTBase)new NBTTagInt(tm.field_145851_c));
                itemstack.func_77983_a("linkY", (NBTBase)new NBTTagInt(tm.field_145848_d));
                itemstack.func_77983_a("linkZ", (NBTBase)new NBTTagInt(tm.field_145849_e));
                itemstack.func_77983_a("linkDim", (NBTBase)new NBTTagInt(world.field_73011_w.field_76574_g));
                itemstack.func_77983_a("dimname", (NBTBase)new NBTTagString(DimensionManager.getProvider((int)world.field_73011_w.field_76574_g).func_80007_l()));
                world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:jar", 1.0f, 2.0f);
                player.func_145747_a((IChatComponent)new ChatComponentText("\u00ef\u00bf\u00bd5\u00ef\u00bf\u00bdo" + StatCollector.func_74838_a((String)"tc.handmirrorlinked")));
                player.field_71069_bz.func_75142_b();
            }
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (item.func_77942_o()) {
            int lx = item.field_77990_d.func_74762_e("linkX");
            int ly = item.field_77990_d.func_74762_e("linkY");
            int lz = item.field_77990_d.func_74762_e("linkZ");
            int ldim = item.field_77990_d.func_74762_e("linkDim");
            String dimname = item.field_77990_d.func_74779_i("dimname");
            list.add(StatCollector.func_74838_a((String)"tc.handmirrorlinkedto") + " " + lx + "," + ly + "," + lz + " in " + dimname);
        }
    }
}

