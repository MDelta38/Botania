/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block$SoundType
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;

public class CustomStepSound
extends Block.SoundType {
    public CustomStepSound(String par1Str, float par2, float par3) {
        super(par1Str, par2, par3);
    }

    public String func_150495_a() {
        return "thaumcraft:" + this.field_150501_a;
    }

    public String func_150498_e() {
        return "thaumcraft:" + this.field_150501_a;
    }
}

