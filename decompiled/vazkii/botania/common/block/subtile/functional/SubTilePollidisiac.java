/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibObfuscation;

public class SubTilePollidisiac
extends SubTileFunctional {
    private static final int RANGE = 6;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.supertile.func_145831_w().field_72995_K) {
            int manaCost = 12;
            List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 6), (double)this.supertile.field_145848_d, (double)(this.supertile.field_145849_e - 6), (double)(this.supertile.field_145851_c + 1 + 6), (double)(this.supertile.field_145848_d + 1), (double)(this.supertile.field_145849_e + 1 + 6)));
            List animals = this.supertile.func_145831_w().func_72872_a(EntityAnimal.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 6), (double)this.supertile.field_145848_d, (double)(this.supertile.field_145849_e - 6), (double)(this.supertile.field_145851_c + 1 + 6), (double)(this.supertile.field_145848_d + 1), (double)(this.supertile.field_145849_e + 1 + 6)));
            int slowdown = this.getSlowdownFactor();
            for (EntityAnimal animal : animals) {
                if (this.mana < manaCost) break;
                int love = (Integer)ReflectionHelper.getPrivateValue(EntityAnimal.class, (Object)animal, (String[])LibObfuscation.IN_LOVE);
                if (animal.func_70874_b() != 0 || love > 0) continue;
                for (EntityItem item : items) {
                    ItemStack stack;
                    if (item.field_70292_b < 60 + slowdown || item.field_70128_L || !animal.func_70877_b(stack = item.func_92059_d())) continue;
                    --stack.field_77994_a;
                    if (stack.field_77994_a == 0) {
                        item.func_70106_y();
                    }
                    this.mana -= manaCost;
                    ReflectionHelper.setPrivateValue(EntityAnimal.class, (Object)animal, (Object)1200, (String[])LibObfuscation.IN_LOVE);
                    animal.func_70784_b(null);
                    this.supertile.func_145831_w().func_72960_a((Entity)animal, (byte)18);
                }
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 6);
    }

    @Override
    public int getMaxMana() {
        return 120;
    }

    @Override
    public int getColor() {
        return 13584665;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.pollidisiac;
    }
}

