/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.items.wands;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.common.items.wands.ItemWandCasting;

public class WandRodPrimalOnUpdate
implements IWandRodOnUpdate {
    Aspect aspect;
    ArrayList<Aspect> primals;

    public WandRodPrimalOnUpdate(Aspect aspect) {
        this.aspect = aspect;
    }

    public WandRodPrimalOnUpdate() {
        this.aspect = null;
        this.primals = Aspect.getPrimalAspects();
    }

    @Override
    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (this.aspect != null) {
            if (player.field_70173_aa % 200 == 0 && ((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, this.aspect) < ((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) / 10) {
                ((ItemWandCasting)itemstack.func_77973_b()).addVis(itemstack, this.aspect, 1, true);
            }
        } else if (player.field_70173_aa % 50 == 0) {
            ArrayList<Aspect> q = new ArrayList<Aspect>();
            for (Aspect as : this.primals) {
                if (((ItemWandCasting)itemstack.func_77973_b()).getVis(itemstack, as) >= ((ItemWandCasting)itemstack.func_77973_b()).getMaxVis(itemstack) / 10) continue;
                q.add(as);
            }
            if (q.size() > 0) {
                ((ItemWandCasting)itemstack.func_77973_b()).addVis(itemstack, (Aspect)q.get(player.field_70170_p.field_73012_v.nextInt(q.size())), 1, true);
            }
        }
    }
}

