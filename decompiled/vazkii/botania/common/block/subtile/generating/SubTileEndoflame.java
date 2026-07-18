/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileEndoflame
extends SubTileGenerating {
    private static final String TAG_BURN_TIME = "burnTime";
    private static final int FUEL_CAP = 32000;
    private static final int RANGE = 3;
    int burnTime = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.linkedCollector != null) {
            if (this.burnTime == 0) {
                if (this.mana < this.getMaxMana()) {
                    boolean didSomething = false;
                    int slowdown = this.getSlowdownFactor();
                    List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 3), (double)(this.supertile.field_145848_d - 3), (double)(this.supertile.field_145849_e - 3), (double)(this.supertile.field_145851_c + 3 + 1), (double)(this.supertile.field_145848_d + 3 + 1), (double)(this.supertile.field_145849_e + 3 + 1)));
                    for (EntityItem item : items) {
                        int burnTime;
                        ItemStack stack;
                        if (item.field_70292_b < 59 + slowdown || item.field_70128_L || (stack = item.func_92059_d()).func_77973_b().hasContainerItem(stack) || (burnTime = stack == null || stack.func_77973_b() == Item.func_150898_a((Block)ModBlocks.spreader) ? 0 : TileEntityFurnace.func_145952_a((ItemStack)stack)) <= 0 || stack.field_77994_a <= 0) continue;
                        this.burnTime = Math.min(32000, burnTime) / 2;
                        if (!this.supertile.func_145831_w().field_72995_K) {
                            --stack.field_77994_a;
                            this.supertile.func_145831_w().func_72908_a((double)this.supertile.field_145851_c, (double)this.supertile.field_145848_d, (double)this.supertile.field_145849_e, "botania:endoflame", 0.2f, 1.0f);
                            if (stack.field_77994_a == 0) {
                                item.func_70106_y();
                            }
                            didSomething = true;
                            break;
                        }
                        item.field_70170_p.func_72869_a("largesmoke", item.field_70165_t, item.field_70163_u + 0.1, item.field_70161_v, 0.0, 0.0, 0.0);
                        item.field_70170_p.func_72869_a("flame", item.field_70165_t, item.field_70163_u, item.field_70161_v, 0.0, 0.0, 0.0);
                        break;
                    }
                    if (didSomething) {
                        this.sync();
                    }
                }
            } else {
                if (this.supertile.func_145831_w().field_73012_v.nextInt(10) == 0) {
                    this.supertile.func_145831_w().func_72869_a("flame", (double)this.supertile.field_145851_c + 0.4 + Math.random() * 0.2, (double)this.supertile.field_145848_d + 0.65, (double)this.supertile.field_145849_e + 0.4 + Math.random() * 0.2, 0.0, 0.0, 0.0);
                }
                --this.burnTime;
            }
        }
    }

    @Override
    public int getMaxMana() {
        return 300;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return 3;
    }

    @Override
    public int getColor() {
        return 7884800;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 3);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.endoflame;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_BURN_TIME, this.burnTime);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.burnTime = cmp.func_74762_e(TAG_BURN_TIME);
    }

    @Override
    public boolean canGeneratePassively() {
        return this.burnTime > 0;
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return 2;
    }
}

