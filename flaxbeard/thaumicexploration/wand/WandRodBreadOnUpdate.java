/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.wands.IWandRodOnUpdate
 */
package flaxbeard.thaumicexploration.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import thaumcraft.api.wands.IWandRodOnUpdate;

public class WandRodBreadOnUpdate
implements IWandRodOnUpdate {
    public void onUpdate(ItemStack itemstack, EntityPlayer player) {
        if (player.field_70170_p.field_73012_v.nextInt(1000) == 0) {
            int i = player.field_70170_p.field_73012_v.nextInt(5);
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("<" + itemstack.func_82833_r() + "> " + StatCollector.func_74838_a((String)("thaumicexploration.bread" + i)), new Object[]{new Object()}));
        }
    }
}

