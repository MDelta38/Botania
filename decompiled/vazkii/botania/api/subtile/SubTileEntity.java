/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.api.subtile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileSlowableContainer;
import vazkii.botania.api.subtile.RadiusDescriptor;

public class SubTileEntity {
    protected TileEntity supertile;
    public int ticksExisted = 0;
    public boolean overgrowth = false;
    public boolean overgrowthBoost = false;
    public static final String TAG_TYPE = "type";
    public static final String TAG_TICKS_EXISTED = "ticksExisted";

    public void setSupertile(TileEntity tile) {
        this.supertile = tile;
    }

    public boolean canUpdate() {
        return true;
    }

    public void onUpdate() {
        ++this.ticksExisted;
    }

    public final void writeToPacketNBTInternal(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_TICKS_EXISTED, this.ticksExisted);
        this.writeToPacketNBT(cmp);
    }

    public final void readFromPacketNBTInternal(NBTTagCompound cmp) {
        if (cmp.func_74764_b(TAG_TICKS_EXISTED)) {
            this.ticksExisted = cmp.func_74762_e(TAG_TICKS_EXISTED);
        }
        this.readFromPacketNBT(cmp);
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
    }

    public void sync() {
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.supertile);
    }

    public String getUnlocalizedName() {
        return BotaniaAPI.getSubTileStringMapping(this.getClass());
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon() {
        return BotaniaAPI.internalHandler.getSubTileIconForName(this.getUnlocalizedName());
    }

    public boolean onWanded(EntityPlayer player, ItemStack wand) {
        return false;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    public void onBlockAdded(World world, int x, int y, int z) {
    }

    public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer player) {
    }

    public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
        return list;
    }

    public LexiconEntry getEntry() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public ChunkCoordinates getBinding() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public RadiusDescriptor getRadius() {
        return null;
    }

    public ChunkCoordinates toChunkCoordinates() {
        return new ChunkCoordinates(this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e);
    }

    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return false;
    }

    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderHUD(Minecraft mc, ScaledResolution res) {
    }

    public int getLightValue() {
        return -1;
    }

    public int getComparatorInputOverride(int side) {
        return 0;
    }

    public int getPowerLevel(int side) {
        return 0;
    }

    public boolean isOvergrowthAffected() {
        return true;
    }

    public int getSlowdownFactor() {
        if (this.supertile instanceof ISubTileSlowableContainer) {
            ISubTileSlowableContainer slowable = (ISubTileSlowableContainer)this.supertile;
            return slowable.getSlowdownFactor();
        }
        return 0;
    }
}

