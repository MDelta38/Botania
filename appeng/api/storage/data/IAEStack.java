/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.storage.data;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAETagCompound;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;

public interface IAEStack<StackType extends IAEStack> {
    public void add(StackType var1);

    public long getStackSize();

    public StackType setStackSize(long var1);

    public long getCountRequestable();

    public StackType setCountRequestable(long var1);

    public boolean isCraftable();

    public StackType setCraftable(boolean var1);

    public StackType reset();

    public boolean isMeaningful();

    public void incStackSize(long var1);

    public void decStackSize(long var1);

    public void incCountRequestable(long var1);

    public void decCountRequestable(long var1);

    public void writeToNBT(NBTTagCompound var1);

    public boolean equals(Object var1);

    public boolean fuzzyComparison(Object var1, FuzzyMode var2);

    public void writeToPacket(ByteBuf var1) throws IOException;

    public StackType copy();

    public StackType empty();

    public IAETagCompound getTagCompound();

    public boolean isItem();

    public boolean isFluid();

    public StorageChannel getChannel();
}

