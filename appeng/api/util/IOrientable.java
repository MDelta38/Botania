/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.util;

import net.minecraftforge.common.util.ForgeDirection;

public interface IOrientable {
    public boolean canBeRotated();

    public ForgeDirection getForward();

    public ForgeDirection getUp();

    public void setOrientation(ForgeDirection var1, ForgeDirection var2);
}

