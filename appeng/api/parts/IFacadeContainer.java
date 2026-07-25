/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.parts.IFacadePart;
import appeng.api.parts.IPartHost;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public interface IFacadeContainer {
    public boolean addFacade(IFacadePart var1);

    public void removeFacade(IPartHost var1, ForgeDirection var2);

    public IFacadePart getFacade(ForgeDirection var1);

    public void rotateLeft();

    public void writeToNBT(NBTTagCompound var1);

    public boolean readFromStream(ByteBuf var1) throws IOException;

    public void readFromNBT(NBTTagCompound var1);

    public void writeToStream(ByteBuf var1) throws IOException;

    public boolean isEmpty();
}

