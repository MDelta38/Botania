/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileJarNode;

public class ItemJarNode
extends Item
implements IEssentiaContainerItem {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemJarNode() {
        this.func_77656_e(0);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String desc = "\u00a79" + StatCollector.func_74838_a((String)("nodetype." + (Object)((Object)this.getNodeType(stack)) + ".name"));
        if (this.getNodeModifier(stack) != null) {
            desc = desc + ", " + StatCollector.func_74838_a((String)("nodemod." + (Object)((Object)this.getNodeModifier(stack)) + ".name"));
        }
        list.add(desc);
        AspectList aspects = this.getAspects(stack);
        if (aspects != null && aspects.size() > 0) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                    list.add(tag.getName() + " x " + aspects.getAmount(tag));
                    continue;
                }
                list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        Block var11 = world.func_147439_a(x, y, z);
        if (var11 == Blocks.field_150431_aC) {
            side = 1;
        } else if (var11 != Blocks.field_150395_bd && var11 != Blocks.field_150329_H && var11 != Blocks.field_150330_I && (var11.isAir((IBlockAccess)world, x, y, z) || !var11.isReplaceable((IBlockAccess)world, x, y, z))) {
            if (side == 0) {
                --y;
            }
            if (side == 1) {
                ++y;
            }
            if (side == 2) {
                --z;
            }
            if (side == 3) {
                ++z;
            }
            if (side == 4) {
                --x;
            }
            if (side == 5) {
                ++x;
            }
        }
        if (stack.field_77994_a == 0) {
            return false;
        }
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        if (y == 255 && ConfigBlocks.blockJar.func_149688_o().func_76220_a()) {
            return false;
        }
        if (world.func_147472_a(ConfigBlocks.blockJar, x, y, z, false, side, (Entity)player, stack)) {
            Block var12 = ConfigBlocks.blockJar;
            int var13 = 2;
            int var14 = ConfigBlocks.blockJar.func_149660_a(world, x, y, z, side, par8, par9, par10, var13);
            if (this.placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, var14)) {
                AspectList aspects;
                TileEntity te = world.func_147438_o(x, y, z);
                if (te != null && te instanceof TileJarNode && stack.func_77942_o() && (aspects = this.getAspects(stack)) != null) {
                    ((TileJarNode)te).setAspects(aspects);
                    ((TileJarNode)te).setNodeType(this.getNodeType(stack));
                    ((TileJarNode)te).setNodeModifier(this.getNodeModifier(stack));
                    ((TileJarNode)te).setId(this.getNodeId(stack));
                }
                world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), var12.field_149762_H.func_150498_e(), (var12.field_149762_H.func_150497_c() + 1.0f) / 2.0f, var12.field_149762_H.func_150494_d() * 0.8f);
                --stack.field_77994_a;
            }
            return true;
        }
        return false;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (!world.func_147465_d(x, y, z, ConfigBlocks.blockJar, metadata, 3)) {
            return false;
        }
        if (world.func_147439_a(x, y, z) == ConfigBlocks.blockJar) {
            ConfigBlocks.blockJar.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
            ConfigBlocks.blockJar.func_149714_e(world, x, y, z, metadata);
        }
        return true;
    }

    @Override
    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.func_77942_o()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.func_77978_p());
            return aspects.size() > 0 ? aspects : null;
        }
        return null;
    }

    @Override
    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.func_77942_o()) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        aspects.writeToNBT(itemstack.func_77978_p());
    }

    public void setNodeAttributes(ItemStack itemstack, NodeType type, NodeModifier mod, String id) {
        if (!itemstack.func_77942_o()) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        itemstack.func_77983_a("nodetype", (NBTBase)new NBTTagInt(type.ordinal()));
        if (mod != null) {
            itemstack.func_77983_a("nodemod", (NBTBase)new NBTTagInt(mod.ordinal()));
        }
        itemstack.func_77983_a("nodeid", (NBTBase)new NBTTagString(id));
    }

    public NodeType getNodeType(ItemStack itemstack) {
        if (!itemstack.func_77942_o()) {
            return null;
        }
        return NodeType.values()[itemstack.func_77978_p().func_74762_e("nodetype")];
    }

    public NodeModifier getNodeModifier(ItemStack itemstack) {
        if (!itemstack.func_77942_o() || !itemstack.func_77978_p().func_74764_b("nodemod")) {
            return null;
        }
        return NodeModifier.values()[itemstack.func_77978_p().func_74762_e("nodemod")];
    }

    public String getNodeId(ItemStack itemstack) {
        if (!itemstack.func_77942_o()) {
            return "0";
        }
        return itemstack.func_77978_p().func_74779_i("nodeid");
    }
}

