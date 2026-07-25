/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPartCollisionHelper {
    public void addBox(double var1, double var3, double var5, double var7, double var9, double var11);

    public ForgeDirection getWorldX();

    public ForgeDirection getWorldY();

    public ForgeDirection getWorldZ();

    public boolean isBBCollision();
}

