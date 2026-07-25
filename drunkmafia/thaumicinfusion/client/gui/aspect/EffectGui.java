/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.client.gui.aspect;

import drunkmafia.thaumicinfusion.client.gui.TIGui;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import thaumcraft.api.WorldCoordinates;

public class EffectGui
extends TIGui {
    protected AspectEffect effect;
    protected int x;
    protected int y;
    protected int z;

    public EffectGui(AspectEffect effect) {
        this.effect = effect;
        WorldCoordinates pos = effect.getPos();
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
    }
}

