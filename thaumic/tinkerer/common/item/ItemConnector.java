/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.golems.EntityGolemBase
 */
package thaumic.tinkerer.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumic.tinkerer.common.block.tile.TileGolemConnector;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvector;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorInterface;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemConnector
extends ItemBase {
    private static final String TAG_POS_X = "posx";
    private static final String TAG_POS_Y = "posy";
    private static final String TAG_POS_Z = "posz";
    private static final String TAG_CONNECTING_GOLEM = "ConnectingGolem";

    public ItemConnector() {
        this.func_77625_d(1);
    }

    public static boolean getConnectingGolem(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_CONNECTING_GOLEM, false);
    }

    public static void setConnectingGolem(ItemStack stack, boolean connecting) {
        ItemNBTHelper.setBoolean(stack, TAG_CONNECTING_GOLEM, connecting);
    }

    public static void setX(ItemStack stack, int x) {
        ItemNBTHelper.setInt(stack, TAG_POS_X, x);
    }

    public static void setY(ItemStack stack, int y) {
        ItemNBTHelper.setInt(stack, TAG_POS_Y, y);
    }

    public static void setZ(ItemStack stack, int z) {
        ItemNBTHelper.setInt(stack, TAG_POS_Z, z);
    }

    public static int getX(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_POS_X, 0);
    }

    public static int getY(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_POS_Y, -1);
    }

    public static int getZ(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_POS_Z, 0);
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("INTERFACE1", "INTERFACE", new ItemStack((Item)this), new AspectList().add(Aspect.ORDER, 2), " I ", " WI", "S  ", Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('W'), new ItemStack(Items.field_151055_y), Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 4));
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.field_72995_K) {
            return false;
        }
        TileEntity tile = par3World.func_147438_o(par4, par5, par6);
        if (ItemConnector.getY(par1ItemStack) == -1) {
            if (tile != null && (tile instanceof TileTransvector || tile instanceof TileGolemConnector)) {
                ItemConnector.setX(par1ItemStack, par4);
                ItemConnector.setY(par1ItemStack, par5);
                ItemConnector.setZ(par1ItemStack, par6);
                if (par3World.field_72995_K) {
                    par2EntityPlayer.func_71038_i();
                }
                this.playSound(par3World, par4, par5, par6);
                if (tile instanceof TileTransvector) {
                    par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.connector.set", new Object[0]));
                } else {
                    par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.golemconnector.set", new Object[0]));
                }
            } else {
                par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.connector.notinterf", new Object[0]));
            }
        } else {
            int z;
            int y;
            int x = ItemConnector.getX(par1ItemStack);
            TileEntity tile1 = par3World.func_147438_o(x, y = ItemConnector.getY(par1ItemStack), z = ItemConnector.getZ(par1ItemStack));
            if (tile1 == null || !(tile1 instanceof TileTransvector)) {
                ItemConnector.setY(par1ItemStack, -1);
                par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.connector.notpresent", new Object[0]));
            } else {
                TileTransvector trans = (TileTransvector)tile1;
                if (tile != null && tile1 instanceof TileTransvectorInterface && tile instanceof TileTransvectorInterface) {
                    par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.connector.interffail", new Object[0]));
                    return true;
                }
                if (Math.abs(x - par4) > trans.getMaxDistance() || Math.abs(y - par5) > trans.getMaxDistance() || Math.abs(z - par6) > trans.getMaxDistance()) {
                    par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.connector.toofar", new Object[0]));
                    return true;
                }
                trans.x = par4;
                trans.y = par5;
                trans.z = par6;
                ItemConnector.setY(par1ItemStack, -1);
                this.playSound(par3World, par4, par5, par6);
                par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.connector.complete", new Object[0]));
                par3World.func_147471_g(trans.x, trans.y, trans.z);
            }
        }
        return true;
    }

    public boolean func_111207_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase) {
        par1ItemStack = par2EntityPlayer.func_71045_bC();
        if (par2EntityPlayer.func_70093_af() && par3EntityLivingBase instanceof EntityGolemBase) {
            int z;
            int y;
            if (ItemConnector.getY(par1ItemStack) == -1) {
                if (par3EntityLivingBase.field_70170_p.field_72995_K) {
                    return false;
                }
                par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.golemconnector.notinterf", new Object[0]));
                return true;
            }
            int x = ItemConnector.getX(par1ItemStack);
            TileEntity tile1 = par2EntityPlayer.field_70170_p.func_147438_o(x, y = ItemConnector.getY(par1ItemStack), z = ItemConnector.getZ(par1ItemStack));
            if (tile1 == null || !(tile1 instanceof TileGolemConnector)) {
                ItemConnector.setY(par1ItemStack, -1);
                if (par3EntityLivingBase.field_70170_p.field_72995_K) {
                    return false;
                }
                par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.golemconnector.notpresent", new Object[0]));
                return false;
            }
            if (par3EntityLivingBase.field_70170_p.field_72995_K) {
                par2EntityPlayer.func_71038_i();
                return false;
            }
            TileGolemConnector trans = (TileGolemConnector)tile1;
            trans.ConnectGolem(par3EntityLivingBase.func_110124_au());
            ItemConnector.setY(par1ItemStack, -1);
            this.playSound(par3EntityLivingBase.field_70170_p, (int)par3EntityLivingBase.field_70165_t, (int)par3EntityLivingBase.field_70163_u, (int)par3EntityLivingBase.field_70161_v);
            par2EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.golemconnector.complete", new Object[0]));
            par2EntityPlayer.field_70170_p.func_147471_g(trans.field_145851_c, trans.field_145848_d, trans.field_145849_e);
            return true;
        }
        return false;
    }

    private void playSound(World world, int x, int y, int z) {
        if (!world.field_72995_K) {
            world.func_72908_a((double)x, (double)y, (double)z, "random.orb", 0.8f, 1.0f);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77662_d() {
        return true;
    }

    @Override
    public String getItemName() {
        return "connector";
    }
}

