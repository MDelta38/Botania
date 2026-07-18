/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package vazkii.botania.common.item;

import java.awt.Color;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.api.wand.ICoordBoundItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemManaMirror
extends ItemMod
implements IManaItem,
ICoordBoundItem,
IManaTooltipDisplay {
    IIcon[] icons;
    private static final String TAG_MANA = "mana";
    private static final String TAG_MANA_BACKLOG = "manaBacklog";
    private static final String TAG_POS_X = "posX";
    private static final String TAG_POS_Y = "posY";
    private static final String TAG_POS_Z = "posZ";
    private static final String TAG_DIM = "dim";
    private static final DummyPool fallbackPool = new DummyPool();

    public ItemManaMirror() {
        this.func_77625_d(1);
        this.func_77656_e(1000);
        this.func_77655_b("manaMirror");
        this.setNoRepair();
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        float mana = this.getMana(par1ItemStack);
        return par2 == 1 ? Color.HSBtoRGB(0.528f, mana / 1000000.0f, 1.0f) : 0xFFFFFF;
    }

    public int getDamage(ItemStack stack) {
        float mana = this.getMana(stack);
        return 1000 - (int)(mana / 1000000.0f * 1000.0f);
    }

    public int getDisplayDamage(ItemStack stack) {
        return this.getDamage(stack);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icons[Math.min(1, pass)];
    }

    public boolean func_77662_d() {
        return true;
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par2World.field_72995_K) {
            return;
        }
        IManaPool pool = this.getManaPool(par1ItemStack);
        if (!(pool instanceof DummyPool)) {
            if (pool == null) {
                this.setMana(par1ItemStack, 0);
            } else {
                pool.recieveMana(this.getManaBacklog(par1ItemStack));
                this.setManaBacklog(par1ItemStack, 0);
                this.setMana(par1ItemStack, pool.getCurrentMana());
            }
        }
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        TileEntity tile;
        if (par2EntityPlayer.func_70093_af() && !par3World.field_72995_K && (tile = par3World.func_147438_o(par4, par5, par6)) != null && tile instanceof IManaPool) {
            this.bindPool(par1ItemStack, tile);
            par3World.func_72956_a((Entity)par2EntityPlayer, "botania:ding", 1.0f, 1.0f);
            return true;
        }
        return false;
    }

    public boolean func_77623_v() {
        return true;
    }

    @Override
    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    public void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, TAG_MANA, Math.max(0, mana));
    }

    public int getManaBacklog(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA_BACKLOG, 0);
    }

    public void setManaBacklog(ItemStack stack, int backlog) {
        ItemNBTHelper.setInt(stack, TAG_MANA_BACKLOG, backlog);
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return 1000000;
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        this.setMana(stack, this.getMana(stack) + mana);
        this.setManaBacklog(stack, this.getManaBacklog(stack) + mana);
    }

    public void bindPool(ItemStack stack, TileEntity pool) {
        ItemNBTHelper.setInt(stack, TAG_POS_X, pool == null ? 0 : pool.field_145851_c);
        ItemNBTHelper.setInt(stack, TAG_POS_Y, pool == null ? -1 : pool.field_145848_d);
        ItemNBTHelper.setInt(stack, TAG_POS_Z, pool == null ? 0 : pool.field_145849_e);
        ItemNBTHelper.setInt(stack, TAG_DIM, pool == null ? 0 : pool.func_145831_w().field_73011_w.field_76574_g);
    }

    public ChunkCoordinates getPoolCoords(ItemStack stack) {
        int x = ItemNBTHelper.getInt(stack, TAG_POS_X, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_POS_Y, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_POS_Z, 0);
        return new ChunkCoordinates(x, y, z);
    }

    public int getDimension(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_DIM, 0);
    }

    public IManaPool getManaPool(ItemStack stack) {
        TileEntity tile;
        MinecraftServer server = MinecraftServer.func_71276_C();
        if (server == null) {
            return fallbackPool;
        }
        ChunkCoordinates coords = this.getPoolCoords(stack);
        if (coords.field_71572_b == -1) {
            return null;
        }
        int dim = this.getDimension(stack);
        WorldServer world = null;
        for (WorldServer w : server.field_71305_c) {
            if (w.field_73011_w.field_76574_g != dim) continue;
            world = w;
            break;
        }
        if (world != null && (tile = world.func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c)) != null && tile instanceof IManaPool) {
            return (IManaPool)tile;
        }
        return null;
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return false;
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return false;
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return true;
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return false;
    }

    @Override
    public ChunkCoordinates getBinding(ItemStack stack) {
        IManaPool pool = this.getManaPool(stack);
        return pool == null || pool instanceof DummyPool ? null : this.getPoolCoords(stack);
    }

    @Override
    public float getManaFractionForDisplay(ItemStack stack) {
        return (float)this.getMana(stack) / (float)this.getMaxMana(stack);
    }

    private static class DummyPool
    implements IManaPool {
        private DummyPool() {
        }

        @Override
        public boolean isFull() {
            return false;
        }

        @Override
        public void recieveMana(int mana) {
        }

        @Override
        public boolean canRecieveManaFromBursts() {
            return false;
        }

        @Override
        public int getCurrentMana() {
            return 0;
        }

        @Override
        public boolean isOutputtingPower() {
            return false;
        }
    }
}

