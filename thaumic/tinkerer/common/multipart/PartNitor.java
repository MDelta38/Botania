/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.vec.Cuboid6
 *  codechicken.multipart.minecraft.McMetaPart
 *  net.minecraft.block.Block
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.multipart;

import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.McMetaPart;
import net.minecraft.block.Block;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.common.ThaumicTinkerer;

public class PartNitor
extends McMetaPart {
    public PartNitor() {
        super(1);
    }

    public Cuboid6 getBounds() {
        return new Cuboid6(0.3, 0.3, 0.3, 0.7, 0.7, 0.7);
    }

    public boolean doesTick() {
        return true;
    }

    public void update() {
        if (this.world().field_72995_K) {
            if (this.world().field_73012_v.nextInt(9 - ThaumicTinkerer.tcProxy.particleCount(3)) == 0) {
                ThaumicTinkerer.tcProxy.wispFX3(this.world(), (double)((float)this.x() + 0.5f), (double)((float)this.y() + 0.5f), (double)((float)this.z() + 0.5f), (double)((float)this.x() + 0.3f + this.world().field_73012_v.nextFloat() * 0.4f), (double)((float)this.y() + 0.5f), (double)((float)this.z() + 0.3f + this.world().field_73012_v.nextFloat() * 0.4f), 0.5f, 4, true, -0.025f);
            }
            if (this.world().field_73012_v.nextInt(15 - ThaumicTinkerer.tcProxy.particleCount(5)) == 0) {
                ThaumicTinkerer.tcProxy.wispFX3(this.world(), (double)((float)this.x() + 0.5f), (double)((float)this.y() + 0.5f), (double)((float)this.z() + 0.5f), (double)((float)this.x() + 0.4f + this.world().field_73012_v.nextFloat() * 0.2f), (double)((float)this.y() + 0.5f), (double)((float)this.z() + 0.4f + this.world().field_73012_v.nextFloat() * 0.2f), 0.25f, 1, true, -0.02f);
            }
        }
    }

    public Block getBlock() {
        return ConfigBlocks.blockAiry;
    }

    public int getMetadata() {
        return 1;
    }

    public String getType() {
        return this.getBlock().func_149739_a();
    }
}

