/*
 * Decompiled with CFR 0.152.
 */
package thaumic.tinkerer.common.registry;

import java.util.ArrayList;
import thaumic.tinkerer.common.registry.ITTinkererRegisterable;

public interface ITTinkererItem
extends ITTinkererRegisterable {
    public ArrayList<Object> getSpecialParameters();

    public String getItemName();

    public boolean shouldRegister();

    public boolean shouldDisplayInTab();
}

