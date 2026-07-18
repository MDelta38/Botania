/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.tile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.item.ModItems;

public class TileTerraPlate
extends TileMod
implements ISparkAttachable {
    public static final int MAX_MANA = 500000;
    private static final int[][] LAPIS_BLOCKS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int[][] LIVINGROCK_BLOCKS = new int[][]{{0, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final String TAG_MANA = "mana";
    int mana;

    public static MultiblockSet makeMultiblockSet() {
        Multiblock mb = new Multiblock();
        for (int[] l : LAPIS_BLOCKS) {
            mb.addComponent(l[0], 0, l[1], Blocks.field_150368_y, 0);
        }
        for (int[] l : LIVINGROCK_BLOCKS) {
            mb.addComponent(l[0], 0, l[1], ModBlocks.livingrock, 0);
        }
        mb.addComponent(0, 1, 0, ModBlocks.terraPlate, 0);
        mb.setRenderOffset(0, 1, 0);
        return mb.makeSet();
    }

    public void func_145845_h() {
        List<EntityItem> items;
        boolean removeMana = true;
        if (this.hasValidPlatform() && this.areItemsValid(items = this.getItems())) {
            removeMana = false;
            ISparkEntity spark = this.getAttachedSpark();
            if (spark != null) {
                List<ISparkEntity> sparkEntities = SparkHelper.getSparksAround(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
                for (ISparkEntity otherSpark : sparkEntities) {
                    if (spark == otherSpark || otherSpark.getAttachedTile() == null || !(otherSpark.getAttachedTile() instanceof IManaPool)) continue;
                    otherSpark.registerTransfer(spark);
                }
            }
            if (this.mana > 0) {
                this.doParticles();
            }
            if (this.mana >= 500000 && !this.field_145850_b.field_72995_K) {
                EntityItem item = items.get(0);
                for (EntityItem otherItem : items) {
                    if (otherItem != item) {
                        otherItem.func_70106_y();
                        continue;
                    }
                    item.func_92058_a(new ItemStack(ModItems.manaResource, 1, 4));
                }
                item.field_70170_p.func_72956_a((Entity)item, "botania:terrasteelCraft", 1.0f, 1.0f);
                this.mana = 0;
                this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
        if (removeMana) {
            this.recieveMana(-1000);
        }
    }

    void doParticles() {
        if (this.field_145850_b.field_72995_K) {
            int ticks = (int)(100.0 * ((double)this.getCurrentMana() / 500000.0));
            int totalSpiritCount = 3;
            double tickIncrement = 360.0 / (double)totalSpiritCount;
            int speed = 5;
            double wticks = (double)(ticks * speed) - tickIncrement;
            double r = Math.sin((double)(ticks - 100) / 10.0) * 2.0;
            double g = Math.sin(wticks * Math.PI / 180.0 * 0.55);
            for (int i = 0; i < totalSpiritCount; ++i) {
                double x = (double)this.field_145851_c + Math.sin(wticks * Math.PI / 180.0) * r + 0.5;
                double y = (double)this.field_145848_d + 0.25 + Math.abs(r) * 0.7;
                double z = (double)this.field_145849_e + Math.cos(wticks * Math.PI / 180.0) * r + 0.5;
                wticks += tickIncrement;
                float[] colorsfx = new float[]{0.0f, (float)ticks / 100.0f, 1.0f - (float)ticks / 100.0f};
                Botania.proxy.wispFX(this.field_145850_b, x, y, z, colorsfx[0], colorsfx[1], colorsfx[2], 0.85f, (float)g * 0.05f, 0.25f);
                Botania.proxy.wispFX(this.field_145850_b, x, y, z, colorsfx[0], colorsfx[1], colorsfx[2], (float)Math.random() * 0.1f + 0.1f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, 0.9f);
                if (ticks != 100) continue;
                for (int j = 0; j < 15; ++j) {
                    Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, colorsfx[0], colorsfx[1], colorsfx[2], (float)Math.random() * 0.15f + 0.15f, (float)(Math.random() - 0.5) * 0.125f, (float)(Math.random() - 0.5) * 0.125f, (float)(Math.random() - 0.5) * 0.125f);
                }
            }
        }
    }

    List<EntityItem> getItems() {
        return this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)));
    }

    boolean areItemsValid(List<EntityItem> items) {
        if (items.size() != 3) {
            return false;
        }
        ItemStack ingot = null;
        ItemStack pearl = null;
        ItemStack diamond = null;
        for (EntityItem item : items) {
            ItemStack stack = item.func_92059_d();
            if (stack.func_77973_b() != ModItems.manaResource || stack.field_77994_a != 1) {
                return false;
            }
            int meta = stack.func_77960_j();
            if (meta == 0) {
                ingot = stack;
                continue;
            }
            if (meta == 1) {
                pearl = stack;
                continue;
            }
            if (meta == 2) {
                diamond = stack;
                continue;
            }
            return false;
        }
        return ingot != null && pearl != null && diamond != null;
    }

    boolean hasValidPlatform() {
        return this.checkAll(LAPIS_BLOCKS, Blocks.field_150368_y) && this.checkAll(LIVINGROCK_BLOCKS, ModBlocks.livingrock);
    }

    boolean checkAll(int[][] positions, Block block) {
        for (int[] position : positions) {
            int[] positions_ = position;
            if (this.checkPlatform(positions_[0], positions_[1], block)) continue;
            return false;
        }
        return true;
    }

    boolean checkPlatform(int xOff, int zOff, Block block) {
        return this.field_145850_b.func_147439_a(this.field_145851_c + xOff, this.field_145848_d - 1, zOff + this.field_145849_e) == block;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.func_74762_e(TAG_MANA);
    }

    @Override
    public int getCurrentMana() {
        return this.mana;
    }

    @Override
    public boolean isFull() {
        return this.mana >= 500000;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.max(0, Math.min(500000, this.mana + mana));
        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return this.areItemsValid(this.getItems());
    }

    @Override
    public boolean canAttachSpark(ItemStack stack) {
        return true;
    }

    @Override
    public void attachSpark(ISparkEntity entity) {
    }

    @Override
    public ISparkEntity getAttachedSpark() {
        List sparks = this.field_145850_b.func_72872_a(ISparkEntity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d + 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1)));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    @Override
    public boolean areIncomingTranfersDone() {
        return !this.areItemsValid(this.getItems());
    }

    @Override
    public int getAvailableSpaceForMana() {
        return Math.max(0, 500000 - this.getCurrentMana());
    }
}

