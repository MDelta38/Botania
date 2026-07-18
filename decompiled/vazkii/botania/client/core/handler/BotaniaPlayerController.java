/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.network.NetHandlerPlayClient
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import vazkii.botania.api.item.IExtendedPlayerController;

@SideOnly(value=Side.CLIENT)
public class BotaniaPlayerController
extends PlayerControllerMP
implements IExtendedPlayerController {
    private float distance = 0.0f;

    public BotaniaPlayerController(Minecraft p_i45062_1_, NetHandlerPlayClient p_i45062_2_) {
        super(p_i45062_1_, p_i45062_2_);
    }

    public float func_78757_d() {
        return super.func_78757_d() + this.distance;
    }

    @Override
    public void setReachDistanceExtension(float f) {
        this.distance = f;
    }

    @Override
    public float getReachDistanceExtension() {
        return this.distance;
    }
}

