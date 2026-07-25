/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking;

import appeng.api.networking.IGridBlock;
import appeng.api.networking.IGridNode;
import java.util.Iterator;

public interface IGridMultiblock
extends IGridBlock {
    public Iterator<IGridNode> getMultiblockNodes();
}

