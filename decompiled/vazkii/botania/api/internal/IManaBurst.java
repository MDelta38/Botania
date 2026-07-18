/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.api.internal;

import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;

public interface IManaBurst {
    public boolean isFake();

    public void setMotion(double var1, double var3, double var5);

    public int getColor();

    public void setColor(int var1);

    public int getMana();

    public void setMana(int var1);

    public int getStartingMana();

    public void setStartingMana(int var1);

    public int getMinManaLoss();

    public void setMinManaLoss(int var1);

    public float getManaLossPerTick();

    public void setManaLossPerTick(float var1);

    public float getGravity();

    public void setGravity(float var1);

    public ChunkCoordinates getBurstSourceChunkCoordinates();

    public void setBurstSourceCoords(int var1, int var2, int var3);

    public ItemStack getSourceLens();

    public void setSourceLens(ItemStack var1);

    public boolean hasAlreadyCollidedAt(int var1, int var2, int var3);

    public void setCollidedAt(int var1, int var2, int var3);

    public int getTicksExisted();

    public void setFake(boolean var1);

    public void setShooterUUID(UUID var1);

    public UUID getShooterUIID();

    public void ping();
}

