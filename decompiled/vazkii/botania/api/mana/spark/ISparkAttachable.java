/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.mana.spark;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.spark.ISparkEntity;

public interface ISparkAttachable
extends IManaReceiver {
    public boolean canAttachSpark(ItemStack var1);

    public void attachSpark(ISparkEntity var1);

    public int getAvailableSpaceForMana();

    public ISparkEntity getAttachedSpark();

    public boolean areIncomingTranfersDone();
}

