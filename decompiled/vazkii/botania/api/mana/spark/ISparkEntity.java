/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.mana.spark;

import java.util.Collection;
import vazkii.botania.api.mana.spark.ISparkAttachable;

public interface ISparkEntity {
    public ISparkAttachable getAttachedTile();

    public Collection<ISparkEntity> getTransfers();

    public void registerTransfer(ISparkEntity var1);

    public int getUpgrade();

    public void setUpgrade(int var1);

    public boolean areIncomingTransfersDone();
}

