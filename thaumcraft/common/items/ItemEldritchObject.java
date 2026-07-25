/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileNode;

public class ItemEldritchObject
extends Item {
    public IIcon[] icon = new IIcon[5];

    public ItemEldritchObject() {
        this.func_77625_d(1);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:eldritch_object");
        this.icon[1] = ir.func_94245_a("thaumcraft:crimson_rites");
        this.icon[2] = ir.func_94245_a("thaumcraft:eldritch_object_2");
        this.icon[3] = ir.func_94245_a("thaumcraft:eldritch_object_3");
        this.icon[4] = ir.func_94245_a("thaumcraft:ob_placer");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        if (par1 < this.icon.length) {
            return this.icon[par1];
        }
        return this.icon[0];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
        par3List.add(new ItemStack((Item)this, 1, 1));
        par3List.add(new ItemStack((Item)this, 1, 2));
        par3List.add(new ItemStack((Item)this, 1, 3));
        par3List.add(new ItemStack((Item)this, 1, 4));
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        switch (stack.func_77960_j()) {
            case 2: {
                return EnumRarity.rare;
            }
            case 3: {
                return EnumRarity.epic;
            }
        }
        return EnumRarity.uncommon;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        super.func_77624_a(stack, player, list, par4);
        if (stack != null) {
            switch (stack.func_77960_j()) {
                case 0: {
                    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.ItemEldritchObject.text.1"));
                    break;
                }
                case 1: {
                    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.ItemEldritchObject.text.2"));
                    list.add(EnumChatFormatting.DARK_BLUE + StatCollector.func_74838_a((String)"item.ItemEldritchObject.text.3"));
                    break;
                }
                case 2: {
                    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.ItemEldritchObject.text.4"));
                    break;
                }
                case 3: {
                    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.ItemEldritchObject.text.5"));
                    list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.ItemEldritchObject.text.6"));
                    break;
                }
                case 4: {
                    list.add("\u00a7oCreative Mode Only");
                }
            }
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World par2World, EntityPlayer player) {
        if (!par2World.field_72995_K && stack.func_77960_j() == 1 && !ResearchManager.isResearchComplete(player.func_70005_c_(), "CRIMSON")) {
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("CRIMSON"), (EntityPlayerMP)player);
            Thaumcraft.proxy.getResearchManager().completeResearch(player, "CRIMSON");
            par2World.func_72956_a((Entity)player, "thaumcraft:learn", 0.75f, 1.0f);
        }
        return stack;
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        if (itemstack.func_77960_j() == 3) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileNode) {
                player.func_71038_i();
                if (!world.field_72995_K) {
                    --itemstack.field_77994_a;
                    TileNode node = (TileNode)te;
                    boolean research = ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "PRIMNODE");
                    for (Aspect a : node.getAspects().getAspects()) {
                        int m = node.getNodeVisBase(a);
                        if (!a.isPrimal()) {
                            if (!world.field_73012_v.nextBoolean()) continue;
                            node.setNodeVisBase(a, (short)(m - 1));
                            continue;
                        }
                        m = m - 2 + world.field_73012_v.nextInt(research ? 9 : 6);
                        node.setNodeVisBase(a, (short)m);
                    }
                    for (Aspect a : Aspect.getPrimalAspects()) {
                        int m = node.getNodeVisBase(a);
                        int r = world.field_73012_v.nextInt(research ? 4 : 3);
                        if (r <= 0 || r <= m) continue;
                        node.setNodeVisBase(a, (short)r);
                        node.addToContainer(a, 1);
                    }
                    if (node.getNodeModifier() == NodeModifier.FADING && world.field_73012_v.nextBoolean()) {
                        node.setNodeModifier(NodeModifier.PALE);
                    } else if (node.getNodeModifier() == NodeModifier.PALE && world.field_73012_v.nextBoolean()) {
                        node.setNodeModifier(null);
                    } else if (node.getNodeModifier() == null && world.field_73012_v.nextInt(5) == 0) {
                        node.setNodeModifier(NodeModifier.BRIGHT);
                    }
                    world.func_147471_g(x, y, z);
                    node.func_70296_d();
                    world.func_72876_a(null, (double)x + 0.5, (double)y + 1.5, (double)z + 0.5, 3.0f + world.field_73012_v.nextFloat() * (float)(research ? 3 : 5), true);
                    for (int a = 0; a < 33; ++a) {
                        int zz;
                        int yy;
                        int xx = x + world.field_73012_v.nextInt(6) - world.field_73012_v.nextInt(6);
                        if (!world.func_147437_c(xx, yy = y + world.field_73012_v.nextInt(6) - world.field_73012_v.nextInt(6), zz = z + world.field_73012_v.nextInt(6) - world.field_73012_v.nextInt(6))) continue;
                        if (yy < y) {
                            world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
                            continue;
                        }
                        world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
                    }
                    return true;
                }
            }
            return false;
        }
        if (side == 1 && itemstack.func_77960_j() == 4) {
            player.func_71038_i();
            for (int a = 1; a <= 6; ++a) {
                if (world.func_147437_c(x, y + a, z)) continue;
                return false;
            }
            world.func_147465_d(x, y + 1, z, ConfigBlocks.blockEldritch, 0, 3);
            world.func_147465_d(x, y + 3, z, ConfigBlocks.blockEldritch, 1, 3);
            world.func_147465_d(x, y + 4, z, ConfigBlocks.blockEldritch, 2, 3);
            world.func_147465_d(x, y + 5, z, ConfigBlocks.blockEldritch, 2, 3);
            world.func_147465_d(x, y + 6, z, ConfigBlocks.blockEldritch, 2, 3);
            world.func_147465_d(x, y + 7, z, ConfigBlocks.blockEldritch, 2, 3);
            return !world.field_72995_K;
        }
        return super.onItemUseFirst(itemstack, player, world, x, y, z, side, par8, par9, par10);
    }
}

