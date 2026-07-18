/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.common.block.subtile.functional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileSpectranthemum
extends SubTileFunctional {
    private static final String TAG_BIND_X = "bindX";
    private static final String TAG_BIND_Y = "bindY";
    private static final String TAG_BIND_Z = "bindZ";
    private static final int COST = 24;
    private static final int RANGE = 2;
    private static final int BIND_RANGE = 12;
    private static final String TAG_TELEPORTED = "Botania_TPd";
    int bindX;
    int bindY = -1;
    int bindZ;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal == 0 && this.supertile.func_145831_w().func_72899_e(this.bindX, this.bindY, this.bindZ)) {
            int x = this.supertile.field_145851_c;
            int y = this.supertile.field_145848_d;
            int z = this.supertile.field_145849_e;
            boolean did = false;
            List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(x - 2), (double)(y - 2), (double)(z - 2), (double)(x + 2 + 1), (double)(y + 2), (double)(z + 2 + 1)));
            int slowdown = this.getSlowdownFactor();
            for (EntityItem item : items) {
                int cost;
                Item sitem;
                ItemStack stack;
                if (item.field_70292_b < 60 + slowdown || item.field_70128_L || item.getEntityData().func_74767_n(TAG_TELEPORTED) || (stack = item.func_92059_d()) == null || (sitem = stack.func_77973_b()) instanceof IManaItem || this.mana < (cost = stack.field_77994_a * 24)) continue;
                SubTileSpectranthemum.spawnExplosionParticles(item, 10);
                item.func_70107_b((double)this.bindX + 0.5, (double)this.bindY + 1.5, (double)this.bindZ + 0.5);
                item.getEntityData().func_74757_a(TAG_TELEPORTED, true);
                item.field_70179_y = 0.0;
                item.field_70181_x = 0.0;
                item.field_70159_w = 0.0;
                SubTileSpectranthemum.spawnExplosionParticles(item, 10);
                if (this.supertile.func_145831_w().field_72995_K) continue;
                this.mana -= cost;
                did = true;
            }
            if (did) {
                this.sync();
            }
        }
    }

    public static void spawnExplosionParticles(EntityItem item, int p) {
        for (int i = 0; i < p; ++i) {
            double m = 0.01;
            double d0 = item.field_70170_p.field_73012_v.nextGaussian() * m;
            double d1 = item.field_70170_p.field_73012_v.nextGaussian() * m;
            double d2 = item.field_70170_p.field_73012_v.nextGaussian() * m;
            double d3 = 10.0;
            item.field_70170_p.func_72869_a("explode", item.field_70165_t + (double)(item.field_70170_p.field_73012_v.nextFloat() * item.field_70130_N * 2.0f) - (double)item.field_70130_N - d0 * d3, item.field_70163_u + (double)(item.field_70170_p.field_73012_v.nextFloat() * item.field_70131_O) - d1 * d3, item.field_70161_v + (double)(item.field_70170_p.field_73012_v.nextFloat() * item.field_70130_N * 2.0f) - (double)item.field_70130_N - d2 * d3, d0, d1, d2);
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 2);
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_BIND_X, this.bindX);
        cmp.func_74768_a(TAG_BIND_Y, this.bindY);
        cmp.func_74768_a(TAG_BIND_Z, this.bindZ);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.bindX = cmp.func_74762_e(TAG_BIND_X);
        this.bindY = cmp.func_74762_e(TAG_BIND_Y);
        this.bindZ = cmp.func_74762_e(TAG_BIND_Z);
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 10009855;
    }

    @Override
    public int getMaxMana() {
        return 16000;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        boolean bound = super.bindTo(player, wand, x, y, z, side);
        if (!(bound || x == this.bindX && y == this.bindY && z == this.bindZ || !(MathHelper.pointDistanceSpace(x, y, z, this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e) <= 12.0f) || x == this.supertile.field_145851_c && y == this.supertile.field_145848_d && z == this.supertile.field_145849_e)) {
            this.bindX = x;
            this.bindY = y;
            this.bindZ = z;
            this.sync();
            return true;
        }
        return bound;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ChunkCoordinates getBinding() {
        return Minecraft.func_71410_x().field_71439_g.func_70093_af() && this.bindY != -1 ? new ChunkCoordinates(this.bindX, this.bindY, this.bindZ) : super.getBinding();
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.spectranthemum;
    }
}

