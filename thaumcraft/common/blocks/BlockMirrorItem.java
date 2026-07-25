/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.DimensionManager
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileMirror;
import thaumcraft.common.tiles.TileMirrorEssentia;

public class BlockMirrorItem
extends ItemBlock {
    public IIcon[] icon = new IIcon[5];

    public BlockMirrorItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon[0] = par1IconRegister.func_94245_a("thaumcraft:mirrorframe");
        this.icon[1] = par1IconRegister.func_94245_a("thaumcraft:mirrorpane");
        this.icon[2] = par1IconRegister.func_94245_a("thaumcraft:mirrorpanetrans");
        this.icon[3] = par1IconRegister.func_94245_a("thaumcraft:mirrorpaneopen");
        this.icon[4] = par1IconRegister.func_94245_a("thaumcraft:mirrorframe2");
    }

    public IIcon func_77618_c(int par1, int par2) {
        if (par2 == 0) {
            return this.icon[par1 <= 1 ? 0 : 4];
        }
        return this.icon[par2 + par1 % 2 * 2];
    }

    public boolean func_77651_p() {
        return true;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        int d = par1ItemStack.func_77960_j() < 6 ? 0 : 6;
        return super.func_77658_a() + "." + d;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.func_147439_a(x, y, z) == ConfigBlocks.blockMirror) {
            if (world.field_72995_K) {
                player.func_71038_i();
                return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
            }
            if (stack.func_77960_j() <= 5) {
                TileEntity tm = world.func_147438_o(x, y, z);
                if (tm != null && tm instanceof TileMirror && !((TileMirror)tm).isLinkValid()) {
                    ItemStack st = stack.func_77946_l();
                    st.field_77994_a = 1;
                    st.func_77964_b(1);
                    st.func_77983_a("linkX", (NBTBase)new NBTTagInt(tm.field_145851_c));
                    st.func_77983_a("linkY", (NBTBase)new NBTTagInt(tm.field_145848_d));
                    st.func_77983_a("linkZ", (NBTBase)new NBTTagInt(tm.field_145849_e));
                    st.func_77983_a("linkDim", (NBTBase)new NBTTagInt(world.field_73011_w.field_76574_g));
                    st.func_77983_a("dimname", (NBTBase)new NBTTagString(DimensionManager.getProvider((int)world.field_73011_w.field_76574_g).func_80007_l()));
                    world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:jar", 1.0f, 2.0f);
                    if (!player.field_71071_by.func_70441_a(st) && !world.field_72995_K) {
                        world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
                    }
                    if (!player.field_71075_bZ.field_75098_d) {
                        --stack.field_77994_a;
                    }
                    player.field_71069_bz.func_75142_b();
                } else if (tm != null && tm instanceof TileMirror) {
                    player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75\u00a7oThat mirror is already linked to a valid destination.", new Object[0]));
                }
            } else {
                TileEntity tm = world.func_147438_o(x, y, z);
                if (tm != null && tm instanceof TileMirrorEssentia && !((TileMirrorEssentia)tm).isLinkValid()) {
                    ItemStack st = stack.func_77946_l();
                    st.field_77994_a = 1;
                    st.func_77964_b(7);
                    st.func_77983_a("linkX", (NBTBase)new NBTTagInt(tm.field_145851_c));
                    st.func_77983_a("linkY", (NBTBase)new NBTTagInt(tm.field_145848_d));
                    st.func_77983_a("linkZ", (NBTBase)new NBTTagInt(tm.field_145849_e));
                    st.func_77983_a("linkDim", (NBTBase)new NBTTagInt(world.field_73011_w.field_76574_g));
                    st.func_77983_a("dimname", (NBTBase)new NBTTagString(DimensionManager.getProvider((int)world.field_73011_w.field_76574_g).func_80007_l()));
                    world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:jar", 1.0f, 2.0f);
                    if (!player.field_71071_by.func_70441_a(st) && !world.field_72995_K) {
                        world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
                    }
                    if (!player.field_71075_bZ.field_75098_d) {
                        --stack.field_77994_a;
                    }
                    player.field_71069_bz.func_75142_b();
                } else if (tm != null && tm instanceof TileMirrorEssentia) {
                    player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75\u00a7oThat mirror is already linked to a valid destination.", new Object[0]));
                }
            }
        }
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (ret && !world.field_72995_K) {
            if (metadata <= 5) {
                TileEntity te = world.func_147438_o(x, y, z);
                if (te != null && te instanceof TileMirror && stack.func_77942_o()) {
                    ((TileMirror)te).linkX = stack.field_77990_d.func_74762_e("linkX");
                    ((TileMirror)te).linkY = stack.field_77990_d.func_74762_e("linkY");
                    ((TileMirror)te).linkZ = stack.field_77990_d.func_74762_e("linkZ");
                    ((TileMirror)te).linkDim = stack.field_77990_d.func_74762_e("linkDim");
                    ((TileMirror)te).restoreLink();
                }
            } else {
                TileEntity te = world.func_147438_o(x, y, z);
                if (te != null && te instanceof TileMirrorEssentia && stack.func_77942_o()) {
                    ((TileMirrorEssentia)te).linkX = stack.field_77990_d.func_74762_e("linkX");
                    ((TileMirrorEssentia)te).linkY = stack.field_77990_d.func_74762_e("linkY");
                    ((TileMirrorEssentia)te).linkZ = stack.field_77990_d.func_74762_e("linkZ");
                    ((TileMirrorEssentia)te).linkDim = stack.field_77990_d.func_74762_e("linkDim");
                    ((TileMirrorEssentia)te).restoreLink();
                }
            }
        }
        return ret;
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderPasses(int metadata) {
        return 2;
    }

    public boolean func_77623_v() {
        return true;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (item.func_77942_o()) {
            int lx = item.field_77990_d.func_74762_e("linkX");
            int ly = item.field_77990_d.func_74762_e("linkY");
            int lz = item.field_77990_d.func_74762_e("linkZ");
            int ldim = item.field_77990_d.func_74762_e("linkDim");
            String dimname = item.field_77990_d.func_74779_i("dimname");
            list.add("Linked to " + lx + "," + ly + "," + lz + " in " + dimname);
        }
    }
}

