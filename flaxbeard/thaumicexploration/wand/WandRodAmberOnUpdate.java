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

public class WandRodAmberOnUpdate
implements IWandRodOnUpdate {
    private Aspect[] aspects = new Aspect[]{Aspect.ORDER, Aspect.ENTROPY, Aspect.EARTH, Aspect.WATER, Aspect.AIR, Aspect.FIRE};

    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (player.field_70173_aa % 100 == 0) {
            int i;
            ArrayList<Aspect> lowAspects = new ArrayList<Aspect>();
            for (i = 0; i < 6; ++i) {
                double cutoffPercent;
                double visCount = ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspects[i]);
                if (!(visCount < (cutoffPercent = (double)((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack)))) continue;
                lowAspects.add(this.aspects[i]);
            }
            if (lowAspects.size() > 0) {
                for (i = 0; i < lowAspects.size(); ++i) {
                    ((ItemWandCasting)itemstack.func_77973_b()).addVis(itemstack, (Aspect)lowAspects.get(i), 1, true);
                }
            }
        }
    }
}

