/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.IVisDiscountGear
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 */
package flaxbeard.thaumicexploration.item;

import baubles.api.BaubleType;
import flaxbeard.thaumicexploration.item.ItemBauble;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ItemBaubleDiscounter
extends ItemBauble
implements IVisDiscountGear {
    private int percent;
    private AspectList aspects;

    public ItemBaubleDiscounter(BaubleType t, AspectList aspect, int perc) {
        super(t);
        this.percent = perc;
        this.aspects = aspect;
        if (this.aspects.size() == 0) {
            for (Aspect pAspect : Aspect.getPrimalAspects()) {
                this.aspects.add(pAspect, 1);
            }
        }
    }

    public ItemBaubleDiscounter(BaubleType t, Aspect aspect, int perc) {
        this(t, new AspectList().add(aspect, 1), perc);
    }

    public int getVisDiscount(ItemStack arg0, EntityPlayer arg1, Aspect arg2) {
        if (this.aspects.size() == 6) {
            return this.percent;
        }
        if (this.aspects.getAmount(arg2) > 0) {
            return this.percent;
        }
        return 0;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (this.getVisDiscount(stack, player, null) > 0) {
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
        } else {
            for (Aspect pAspect : Aspect.getPrimalAspects()) {
                if (this.getVisDiscount(stack, player, pAspect) <= 0) continue;
                list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"tc.visdiscount") + " (" + pAspect.getName() + "): " + this.getVisDiscount(stack, player, pAspect) + "%");
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }
}

