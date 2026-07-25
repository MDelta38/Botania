/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.api.aspects;

import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;

public interface IEssentiaTransport {
    public boolean isConnectable(ForgeDirection var1);

    public boolean canInputFrom(ForgeDirection var1);

    public boolean canOutputTo(ForgeDirection var1);

    public void setSuction(Aspect var1, int var2);

    public Aspect getSuctionType(ForgeDirection var1);

    public int getSuctionAmount(ForgeDirection var1);

    public int takeEssentia(Aspect var1, int var2, ForgeDirection var3);

    public int addEssentia(Aspect var1, int var2, ForgeDirection var3);

    public Aspect getEssentiaType(ForgeDirection var1);

    public int getEssentiaAmount(ForgeDirection var1);

    public int getMinimumSuction();

    public boolean renderExtendedTube();
}

