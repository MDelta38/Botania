/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.block.tile.mana;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.api.mana.ITinyPlanetExcempt;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public class TilePrism
extends TileSimpleInventory
implements IManaCollisionGhost,
ISidedInventory {
    public void onBurstCollision(IManaBurst burst) {
        boolean valid;
        ItemStack lens = this.func_70301_a(0);
        boolean active = (this.func_145832_p() & 8) == 0;
        boolean bl = valid = lens != null && lens.func_77973_b() instanceof ILens && (!(lens.func_77973_b() instanceof ITinyPlanetExcempt) || ((ITinyPlanetExcempt)lens.func_77973_b()).shouldPull(lens));
        if (active) {
            burst.setSourceLens(valid ? lens.func_77946_l() : null);
            burst.setColor(0xFFFFFF);
            burst.setGravity(0.0f);
            if (valid) {
                Entity burstEntity = (Entity)burst;
                BurstProperties properties = new BurstProperties(burst.getStartingMana(), burst.getMinManaLoss(), burst.getManaLossPerTick(), burst.getGravity(), 1.0f, burst.getColor());
                ((ILens)lens.func_77973_b()).apply(lens, properties);
                burst.setColor(properties.color);
                burst.setStartingMana(properties.maxMana);
                burst.setMinManaLoss(properties.ticksBeforeManaLoss);
                burst.setManaLossPerTick(properties.manaLossPerTick);
                burst.setGravity(properties.gravity);
                burst.setMotion(burstEntity.field_70159_w * (double)properties.motionModifier, burstEntity.field_70181_x * (double)properties.motionModifier, burstEntity.field_70179_y * (double)properties.motionModifier);
            }
        }
    }

    @Override
    public boolean isGhost() {
        return true;
    }

    public int func_70302_i_() {
        return 1;
    }

    public String func_145825_b() {
        return "prism";
    }

    public int[] func_94128_d(int p_94128_1_) {
        return new int[]{0};
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return p_102007_2_.func_77973_b() instanceof ILens;
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return true;
    }
}

