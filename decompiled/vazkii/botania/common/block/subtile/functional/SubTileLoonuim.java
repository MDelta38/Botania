/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.ChestGenHooks
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileLoonuim
extends SubTileFunctional {
    private static final int COST = 35000;
    private static final int RANGE = 3;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal == 0 && this.ticksExisted % 200 == 0 && this.mana >= 35000) {
            ItemStack stack;
            Random rand = this.supertile.func_145831_w().field_73012_v;
            while ((stack = ChestGenHooks.getOneItem((String)"dungeonChest", (Random)rand)) == null || BotaniaAPI.looniumBlacklist.contains(stack.func_77973_b())) {
            }
            int bound = 7;
            EntityItem entity = new EntityItem(this.supertile.func_145831_w(), (double)(this.supertile.field_145851_c - 3 + rand.nextInt(bound)), (double)(this.supertile.field_145848_d + 1), (double)(this.supertile.field_145849_e - 3 + rand.nextInt(bound)), stack);
            entity.field_70159_w = 0.0;
            entity.field_70181_x = 0.0;
            entity.field_70179_y = 0.0;
            if (!this.supertile.func_145831_w().field_72995_K) {
                this.supertile.func_145831_w().func_72838_d((Entity)entity);
            }
            this.mana -= 35000;
            this.sync();
        }
    }

    @Override
    public int getColor() {
        return 2574848;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.loonium;
    }

    @Override
    public int getMaxMana() {
        return 35000;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 3);
    }
}

