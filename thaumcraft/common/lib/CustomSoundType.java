/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block$SoundType
 */
package thaumcraft.common.lib;

import net.minecraft.block.Block;

public class CustomSoundType
extends Block.SoundType {
    public CustomSoundType(String par1Str, float par2, float par3) {
        super(par1Str, par2, par3);
    }

    public String func_150495_a() {
        return "thaumcraft:" + this.field_150501_a;
    }

    public String func_150498_e() {
        return "thaumcraft:" + this.field_150501_a;
    }
}

