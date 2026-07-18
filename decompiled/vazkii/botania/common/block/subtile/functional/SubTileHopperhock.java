/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.subtile.functional.SubTileSpectranthemum;
import vazkii.botania.common.core.helper.InventoryHelper;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

public class SubTileHopperhock
extends SubTileFunctional {
    private static final String TAG_FILTER_TYPE = "filterType";
    private static final int RANGE_MANA = 10;
    private static final int RANGE = 6;
    private static final int RANGE_MANA_MINI = 2;
    private static final int RANGE_MINI = 1;
    private static Set<EntityItem> particled = Collections.newSetFromMap(new WeakHashMap());
    int filterType = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal > 0) {
            return;
        }
        boolean pulledAny = false;
        int range = this.getRange();
        int x = this.supertile.field_145851_c;
        int y = this.supertile.field_145848_d;
        int z = this.supertile.field_145849_e;
        List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(x - range), (double)(y - range), (double)(z - range), (double)(x + range + 1), (double)(y + range + 1), (double)(z + range + 1)));
        int slowdown = this.getSlowdownFactor();
        for (EntityItem item : items) {
            if (item.field_70292_b < 60 + slowdown || item.field_70292_b >= 105 && item.field_70292_b < 110 || item.field_70128_L) continue;
            ItemStack stack = item.func_92059_d();
            IInventory invToPutItemIn = null;
            ForgeDirection sideToPutItemIn = ForgeDirection.UNKNOWN;
            boolean priorityInv = false;
            int amountToPutIn = 0;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                boolean priority;
                int x_ = x + dir.offsetX;
                int y_ = y + dir.offsetY;
                int z_ = z + dir.offsetZ;
                IInventory inv = InventoryHelper.getInventory(this.supertile.func_145831_w(), x_, y_, z_);
                if (inv == null) continue;
                List<ItemStack> filter = this.getFilterForInventory(inv, x_, y_, z_, true);
                boolean canAccept = this.canAcceptItem(stack, filter, this.filterType);
                int availablePut = this.supertile.func_145831_w().field_72995_K ? 1 : InventoryHelper.testInventoryInsertion(inv, stack, dir);
                if (!(canAccept &= availablePut > 0)) continue;
                boolean bl = priority = !filter.isEmpty();
                if (priorityInv && !priority) continue;
                invToPutItemIn = inv;
                priorityInv = priority;
                sideToPutItemIn = dir.getOpposite();
                amountToPutIn = availablePut;
            }
            if (invToPutItemIn == null || item.field_70128_L) continue;
            boolean remote = this.supertile.func_145831_w().field_72995_K;
            if (remote) {
                if (particled.contains(item)) continue;
                SubTileSpectranthemum.spawnExplosionParticles(item, 3);
                particled.add(item);
                continue;
            }
            InventoryHelper.insertItemIntoInventory(invToPutItemIn, stack.func_77979_a(amountToPutIn), sideToPutItemIn, -1);
            item.func_92058_a(stack);
            invToPutItemIn.func_70296_d();
            if (item.func_92059_d().field_77994_a == 0) {
                item.func_70106_y();
            }
            pulledAny = true;
        }
        if (pulledAny && this.mana > 1) {
            --this.mana;
        }
    }

    public boolean canAcceptItem(ItemStack stack, List<ItemStack> filter, int filterType) {
        if (stack == null) {
            return false;
        }
        if (filter.isEmpty()) {
            return true;
        }
        switch (filterType) {
            case 0: {
                boolean anyFilter = false;
                for (ItemStack filterEntry : filter) {
                    if (filterEntry == null) continue;
                    anyFilter = true;
                    boolean itemEqual = stack.func_77973_b() == filterEntry.func_77973_b();
                    boolean damageEqual = stack.func_77960_j() == filterEntry.func_77960_j();
                    boolean nbtEqual = ItemStack.func_77970_a((ItemStack)filterEntry, (ItemStack)stack);
                    if (itemEqual && damageEqual && nbtEqual) {
                        return true;
                    }
                    if (!stack.func_77981_g() && stack.func_77984_f() && stack.func_77976_d() == 1 && itemEqual && nbtEqual) {
                        return true;
                    }
                    if (!(stack.func_77973_b() instanceof IManaItem) || !itemEqual) continue;
                    return true;
                }
                return !anyFilter;
            }
            case 1: {
                return !this.canAcceptItem(stack, filter, 0);
            }
        }
        return true;
    }

    public List<ItemStack> getFilterForInventory(IInventory inv, int x, int y, int z, boolean recursiveForDoubleChests) {
        ArrayList<ItemStack> filter = new ArrayList<ItemStack>();
        if (recursiveForDoubleChests) {
            TileEntity tileEntity = this.supertile.func_145831_w().func_147438_o(x, y, z);
            Block chest = this.supertile.func_145831_w().func_147439_a(x, y, z);
            if (tileEntity instanceof TileEntityChest) {
                ForgeDirection[] forgeDirectionArray = LibMisc.CARDINAL_DIRECTIONS;
                int n = forgeDirectionArray.length;
                for (int i = 0; i < n; ++i) {
                    ForgeDirection dir = forgeDirectionArray[i];
                    if (this.supertile.func_145831_w().func_147439_a(x + dir.offsetX, y, z + dir.offsetZ) != chest) continue;
                    filter.addAll(this.getFilterForInventory((IInventory)this.supertile.func_145831_w().func_147438_o(x + dir.offsetX, y, z + dir.offsetZ), x + dir.offsetX, y, z + dir.offsetZ, false));
                    break;
                }
            }
        }
        int[] orientationToDir = new int[]{3, 4, 2, 5};
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(x + dir.offsetX), (double)(y + dir.offsetY), (double)(z + dir.offsetZ), (double)(x + dir.offsetX + 1), (double)(y + dir.offsetY + 1), (double)(z + dir.offsetZ + 1));
            List frames = this.supertile.func_145831_w().func_72872_a(EntityItemFrame.class, aabb);
            for (EntityItemFrame frame : frames) {
                int orientation = frame.field_82332_a;
                if (orientationToDir[orientation] != dir.ordinal()) continue;
                filter.add(frame.func_82335_i());
            }
        }
        return filter;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public boolean onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return false;
        }
        if (player.func_70093_af()) {
            this.filterType = this.filterType == 2 ? 0 : this.filterType + 1;
            this.sync();
            return true;
        }
        return super.onWanded(player, wand);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    public int getRange() {
        return this.mana > 0 ? 10 : 6;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_FILTER_TYPE, this.filterType);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.filterType = cmp.func_74762_e(TAG_FILTER_TYPE);
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
        int color = this.getColor();
        String filter = StatCollector.func_74838_a((String)("botaniamisc.filter" + this.filterType));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        int x = res.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(filter) / 2;
        int y = res.func_78328_b() / 2 + 30;
        mc.field_71466_p.func_78261_a(filter, x, y, color);
        GL11.glDisable((int)3042);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.hopperhock;
    }

    @Override
    public int getMaxMana() {
        return 20;
    }

    @Override
    public int getColor() {
        return 0x3F3F3F;
    }

    public static class Mini
    extends SubTileHopperhock {
        @Override
        public int getRange() {
            return this.mana > 0 ? 2 : 1;
        }
    }
}

