/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileDaffomill
extends SubTileFunctional {
    private static final String TAG_ORIENTATION = "orientation";
    private static final String TAG_WIND_TICKS = "windTicks";
    int windTicks = 0;
    int orientation = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        ForgeDirection dir = ForgeDirection.getOrientation((int)(this.orientation + 2));
        if (this.supertile.func_145831_w().field_73012_v.nextInt(4) == 0) {
            Botania.proxy.wispFX(this.supertile.func_145831_w(), (double)this.supertile.field_145851_c + Math.random(), (double)this.supertile.field_145848_d + Math.random(), (double)this.supertile.field_145849_e + Math.random(), 0.05f, 0.05f, 0.05f, 0.25f + (float)Math.random() * 0.15f, (float)dir.offsetX * 0.1f, (float)dir.offsetY * 0.1f, (float)dir.offsetZ * 0.1f);
        }
        if (this.windTicks == 0 && this.mana > 0) {
            this.windTicks = 20;
            --this.mana;
        }
        if (this.windTicks > 0 && this.redstoneSignal == 0) {
            AxisAlignedBB axis = this.aabbForOrientation();
            if (axis != null) {
                List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, axis);
                int slowdown = this.getSlowdownFactor();
                for (EntityItem item : items) {
                    if (item.field_70128_L || item.field_70292_b < slowdown) continue;
                    item.field_70159_w += (double)dir.offsetX * 0.05;
                    item.field_70181_x += (double)dir.offsetY * 0.05;
                    item.field_70179_y += (double)dir.offsetZ * 0.05;
                }
            }
            --this.windTicks;
        }
    }

    AxisAlignedBB aabbForOrientation() {
        int x = this.supertile.field_145851_c;
        int y = this.supertile.field_145848_d;
        int z = this.supertile.field_145849_e;
        int w = 2;
        int h = 3;
        int l = 16;
        AxisAlignedBB axis = null;
        switch (this.orientation) {
            case 0: {
                axis = AxisAlignedBB.func_72330_a((double)(x - w), (double)(y - h), (double)(z - l), (double)(x + w + 1), (double)(y + h), (double)z);
                break;
            }
            case 1: {
                axis = AxisAlignedBB.func_72330_a((double)(x - w), (double)(y - h), (double)(z + 1), (double)(x + w + 1), (double)(y + h), (double)(z + l + 1));
                break;
            }
            case 2: {
                axis = AxisAlignedBB.func_72330_a((double)(x - l), (double)(y - h), (double)(z - w), (double)x, (double)(y + h), (double)(z + w + 1));
                break;
            }
            case 3: {
                axis = AxisAlignedBB.func_72330_a((double)(x + 1), (double)(y - h), (double)(z - w), (double)(x + l + 1), (double)(y + h), (double)(z + w + 1));
            }
        }
        return axis;
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
            if (!player.field_70170_p.field_72995_K) {
                this.orientation = this.orientation == 3 ? 0 : this.orientation + 1;
                this.sync();
            }
            return true;
        }
        return super.onWanded(player, wand);
    }

    @Override
    public RadiusDescriptor getRadius() {
        AxisAlignedBB aabb = this.aabbForOrientation();
        aabb.field_72338_b = this.supertile.field_145848_d;
        return new RadiusDescriptor.Rectangle(this.toChunkCoordinates(), aabb);
    }

    @Override
    public int getColor() {
        return 14203392;
    }

    @Override
    public int getMaxMana() {
        return 100;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.daffomill;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_ORIENTATION, this.orientation);
        cmp.func_74768_a(TAG_WIND_TICKS, this.windTicks);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.orientation = cmp.func_74762_e(TAG_ORIENTATION);
        this.windTicks = cmp.func_74762_e(TAG_WIND_TICKS);
    }
}

