/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.wands.IWandRodOnUpdate
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package flaxbeard.thaumicexploration.wand;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandRodTransmutationOnUpdate
implements IWandRodOnUpdate {
    private Aspect[] aspects = new Aspect[]{Aspect.ORDER, Aspect.ENTROPY, Aspect.EARTH, Aspect.WATER, Aspect.AIR, Aspect.FIRE};

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        double excessVis;
        double cutoffPercent;
        double visCount;
        int i;
        int numberUnder90 = 0;
        double totalExcessVis = 0.0;
        ArrayList<Integer> lowAspects = new ArrayList<Integer>();
        for (i = 0; i < 6; ++i) {
            visCount = ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspects[i]);
            excessVis = visCount - (cutoffPercent = (double)(((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) / 10 * 9));
            if (!(excessVis <= 0.0)) continue;
            ++numberUnder90;
            lowAspects.add(i);
        }
        if (numberUnder90 > 0) {
            double visCount2;
            int z;
            for (i = 0; i < 6; ++i) {
                visCount = ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspects[i]);
                excessVis = visCount - (cutoffPercent = (double)(((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) / 10 * 9) + 0.1);
                if (!(excessVis > 0.0)) continue;
                ((ItemWandCasting)itemstack.func_77973_b()).consumeVis(itemstack, player, this.aspects[i], (int)excessVis, true);
                totalExcessVis += excessVis;
            }
            if (totalExcessVis / 100.0 < 0.1) {
                totalExcessVis = 0.0;
            }
            double wastedVis = 0.0;
            int eachToAdd = (int)(totalExcessVis / (double)(lowAspects.size() * 4));
            for (z = 0; z < lowAspects.size(); ++z) {
                visCount2 = ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspects[(Integer)lowAspects.get(z)]);
                double myWastedVis = visCount2 - (double)((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) + (double)eachToAdd;
                if (myWastedVis > 0.0) {
                    wastedVis += myWastedVis;
                }
                ((ItemWandCasting)itemstack.func_77973_b()).storeVis(itemstack, this.aspects[(Integer)lowAspects.get(z)], (int)(visCount2 + (double)eachToAdd));
            }
            wastedVis /= 6.0;
            for (z = 0; z < 6; ++z) {
                visCount2 = ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspects[z]);
                ((ItemWandCasting)itemstack.func_77973_b()).storeVis(itemstack, this.aspects[z], (int)(visCount2 + wastedVis));
            }
        }
    }
}

