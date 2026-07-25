/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items.relics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileMirror;

public class ItemHandMirror
extends Item {
    private IIcon icon;

    public ItemHandMirror() {
        this.func_77625_d(1);
        this.func_77627_a(false);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("thaumcraft:mirrorhand");
    }

    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this));
    }

    public boolean func_77651_p() {
        return true;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
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
                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.handmirrorlinked")));
                player.field_71069_bz.func_75142_b();
            }
            return true;
        }
        return false;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.field_72995_K && par1ItemStack.func_77942_o()) {
            int lx = par1ItemStack.field_77990_d.func_74762_e("linkX");
            int ly = par1ItemStack.field_77990_d.func_74762_e("linkY");
            int lz = par1ItemStack.field_77990_d.func_74762_e("linkZ");
            int ldim = par1ItemStack.field_77990_d.func_74762_e("linkDim");
            WorldServer targetWorld = MinecraftServer.func_71276_C().func_71218_a(ldim);
            if (targetWorld == null) {
                return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
            }
            TileEntity te = targetWorld.func_147438_o(lx, ly, lz);
            if (te == null || !(te instanceof TileMirror)) {
                par1ItemStack.func_77982_d(null);
                par2World.func_72956_a((Entity)par3EntityPlayer, "thaumcraft:zap", 1.0f, 0.8f);
                par3EntityPlayer.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.handmirrorerror")));
                return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
            }
            par3EntityPlayer.openGui((Object)Thaumcraft.instance, 16, par2World, MathHelper.func_76128_c((double)par3EntityPlayer.field_70165_t), MathHelper.func_76128_c((double)par3EntityPlayer.field_70163_u), MathHelper.func_76128_c((double)par3EntityPlayer.field_70161_v));
        }
        return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
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

    public static boolean transport(ItemStack mirror, ItemStack items, EntityPlayer player, World worldObj) {
        if (mirror.func_77942_o()) {
            int lx = mirror.field_77990_d.func_74762_e("linkX");
            int ly = mirror.field_77990_d.func_74762_e("linkY");
            int lz = mirror.field_77990_d.func_74762_e("linkZ");
            int ldim = mirror.field_77990_d.func_74762_e("linkDim");
            WorldServer targetWorld = MinecraftServer.func_71276_C().func_71218_a(ldim);
            if (targetWorld == null) {
                return false;
            }
            TileEntity te = targetWorld.func_147438_o(lx, ly, lz);
            if (te == null || !(te instanceof TileMirror)) {
                mirror.func_77982_d(null);
                worldObj.func_72956_a((Entity)player, "thaumcraft:zap", 1.0f, 0.8f);
                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"tc.handmirrorerror")));
                return false;
            }
            TileMirror tm = (TileMirror)te;
            ForgeDirection linkedFacing = ForgeDirection.getOrientation((int)targetWorld.func_72805_g(lx, ly, lz));
            EntityItem ie2 = new EntityItem((World)targetWorld, (double)lx + 0.5 - (double)linkedFacing.offsetX * 0.3, (double)ly + 0.5 - (double)linkedFacing.offsetY * 0.3, (double)lz + 0.5 - (double)linkedFacing.offsetZ * 0.3, items.func_77946_l());
            ie2.field_70159_w = (float)linkedFacing.offsetX * 0.15f;
            ie2.field_70181_x = (float)linkedFacing.offsetY * 0.15f;
            ie2.field_70179_y = (float)linkedFacing.offsetZ * 0.15f;
            ie2.field_71088_bW = 20;
            targetWorld.func_72838_d((Entity)ie2);
            items = null;
            worldObj.func_72956_a((Entity)player, "mob.endermen.portal", 0.1f, 1.0f);
            targetWorld.func_147452_c(lx, ly, lz, ConfigBlocks.blockMirror, 1, 0);
            return true;
        }
        return false;
    }
}

