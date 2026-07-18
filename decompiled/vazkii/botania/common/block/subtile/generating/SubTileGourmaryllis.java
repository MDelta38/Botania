/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileGourmaryllis
extends SubTileGenerating {
    private static final String TAG_COOLDOWN = "cooldown";
    private static final int RANGE = 1;
    int cooldown = 0;
    int storedMana = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.cooldown > 0) {
            --this.cooldown;
        }
        if (this.cooldown == 0 && !this.supertile.func_145831_w().field_72995_K) {
            this.mana = Math.min(this.getMaxMana(), this.mana + this.storedMana);
            this.storedMana = 0;
            this.sync();
        }
        int slowdown = this.getSlowdownFactor();
        boolean remote = this.supertile.func_145831_w().field_72995_K;
        List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 1), (double)(this.supertile.field_145848_d - 1), (double)(this.supertile.field_145849_e - 1), (double)(this.supertile.field_145851_c + 1 + 1), (double)(this.supertile.field_145848_d + 1 + 1), (double)(this.supertile.field_145849_e + 1 + 1)));
        for (EntityItem item : items) {
            ItemStack stack = item.func_92059_d();
            if (stack == null || !(stack.func_77973_b() instanceof ItemFood) || item.field_70128_L || item.field_70292_b < slowdown) continue;
            if (this.cooldown == 0) {
                if (!remote) {
                    int val = ((ItemFood)stack.func_77973_b()).func_150905_g(stack);
                    this.storedMana = val * val * 64;
                    this.cooldown = val * 10;
                    this.supertile.func_145831_w().func_72908_a((double)this.supertile.field_145851_c, (double)this.supertile.field_145848_d, (double)this.supertile.field_145849_e, "random.eat", 0.2f, 0.5f + (float)Math.random() * 0.5f);
                    this.sync();
                } else {
                    for (int i = 0; i < 10; ++i) {
                        float m = 0.2f;
                        float mx = (float)(Math.random() - 0.5) * m;
                        float my = (float)(Math.random() - 0.5) * m;
                        float mz = (float)(Math.random() - 0.5) * m;
                        this.supertile.func_145831_w().func_72869_a("iconcrack_" + Item.func_150891_b((Item)stack.func_77973_b()), item.field_70165_t, item.field_70163_u, item.field_70161_v, (double)mx, (double)my, (double)mz);
                    }
                }
            }
            if (remote) continue;
            item.func_70106_y();
        }
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_COOLDOWN, this.cooldown);
        cmp.func_74768_a(TAG_COOLDOWN, this.cooldown);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.cooldown = cmp.func_74762_e(TAG_COOLDOWN);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }

    @Override
    public int getMaxMana() {
        return 8000;
    }

    @Override
    public int getColor() {
        return 13882884;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.gourmaryllis;
    }
}

