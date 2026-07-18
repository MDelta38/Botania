/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.tile;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileSlowableContainer;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.wand.IWandBindable;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.string.TileRedStringRelay;

public class TileSpecialFlower
extends TileMod
implements IWandBindable,
ISubTileSlowableContainer {
    private static final String TAG_SUBTILE_NAME = "subTileName";
    private static final String TAG_SUBTILE_CMP = "subTileCmp";
    public String subTileName = "";
    SubTileEntity subTile;

    @Override
    public SubTileEntity getSubTile() {
        return this.subTile;
    }

    @Override
    public void setSubTile(String name) {
        this.subTileName = name;
        this.provideSubTile(this.subTileName);
    }

    public void setSubTile(SubTileEntity tile) {
        this.subTile = tile;
        this.subTile.setSupertile(this);
    }

    private void provideSubTile(String name) {
        this.subTileName = name;
        Class<? extends SubTileEntity> tileClass = BotaniaAPI.getSubTileMapping(name);
        try {
            SubTileEntity tile = tileClass.newInstance();
            this.setSubTile(tile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void func_145845_h() {
        if (this.subTile != null) {
            ChunkCoordinates coords;
            TileEntity tileBelow = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            if (tileBelow instanceof TileRedStringRelay && (coords = ((TileRedStringRelay)tileBelow).getBinding()) != null) {
                int currX = this.field_145851_c;
                int currY = this.field_145848_d;
                int currZ = this.field_145849_e;
                this.field_145851_c = coords.field_71574_a;
                this.field_145848_d = coords.field_71572_b;
                this.field_145849_e = coords.field_71573_c;
                this.subTile.onUpdate();
                this.field_145851_c = currX;
                this.field_145848_d = currY;
                this.field_145849_e = currZ;
                return;
            }
            boolean special = this.isOnSpecialSoil();
            if (special) {
                this.subTile.overgrowth = true;
                if (this.subTile.isOvergrowthAffected()) {
                    this.subTile.onUpdate();
                    this.subTile.overgrowthBoost = true;
                }
            }
            this.subTile.onUpdate();
            this.subTile.overgrowth = false;
            this.subTile.overgrowthBoost = false;
        }
    }

    public boolean isOnSpecialSoil() {
        return this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ModBlocks.enchantedSoil;
    }

    public boolean canUpdate() {
        return this.subTile == null || this.subTile.canUpdate();
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74778_a(TAG_SUBTILE_NAME, this.subTileName);
        NBTTagCompound subCmp = new NBTTagCompound();
        cmp.func_74782_a(TAG_SUBTILE_CMP, (NBTBase)subCmp);
        if (this.subTile != null) {
            this.subTile.writeToPacketNBTInternal(subCmp);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.subTileName = cmp.func_74779_i(TAG_SUBTILE_NAME);
        NBTTagCompound subCmp = cmp.func_74775_l(TAG_SUBTILE_CMP);
        if (this.subTile == null || !BotaniaAPI.getSubTileStringMapping(this.subTile.getClass()).equals(this.subTileName)) {
            this.provideSubTile(this.subTileName);
        }
        if (this.subTile != null) {
            this.subTile.readFromPacketNBTInternal(subCmp);
        }
    }

    public IIcon getIcon() {
        return this.subTile == null ? Blocks.field_150328_O.func_149691_a(0, 0) : this.subTile.getIcon();
    }

    public LexiconEntry getEntry() {
        return this.subTile == null ? null : this.subTile.getEntry();
    }

    public boolean onWanded(ItemStack wand, EntityPlayer player) {
        return this.subTile == null ? false : this.subTile.onWanded(player, wand);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (this.subTile != null) {
            this.subTile.onBlockPlacedBy(world, x, y, z, entity, stack);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return this.subTile == null ? false : this.subTile.onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        if (this.subTile != null) {
            this.subTile.onBlockAdded(world, x, y, z);
        }
    }

    public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
        if (this.subTile != null) {
            this.subTile.onBlockHarvested(world, x, y, z, side, player);
        }
    }

    public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
        if (this.subTile != null) {
            this.subTile.getDrops(list);
        }
        return list;
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        if (this.subTile != null) {
            this.subTile.renderHUD(mc, res);
        }
    }

    @Override
    public ChunkCoordinates getBinding() {
        if (this.subTile == null) {
            return null;
        }
        return this.subTile.getBinding();
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        if (this.subTile == null) {
            return false;
        }
        return this.subTile.canSelect(player, wand, x, y, z, side);
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        if (this.subTile == null) {
            return false;
        }
        return this.subTile.bindTo(player, wand, x, y, z, side);
    }

    public int getLightValue() {
        if (this.subTile == null) {
            return -1;
        }
        return this.subTile.getLightValue();
    }

    public int getComparatorInputOverride(int side) {
        if (this.subTile == null) {
            return 0;
        }
        return this.subTile.getComparatorInputOverride(side);
    }

    public int getPowerLevel(int side) {
        if (this.subTile == null) {
            return 0;
        }
        return this.subTile.getPowerLevel(side);
    }

    @Override
    public int getSlowdownFactor() {
        int meta;
        Block below = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (below == Blocks.field_150391_bh) {
            return 10;
        }
        if (below == Blocks.field_150346_d && (meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) == 2) {
            return 5;
        }
        return 0;
    }
}

