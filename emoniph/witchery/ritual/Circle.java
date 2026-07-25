/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class Circle {
    int numRitualGlyphs;
    int numOtherwhereGlyphs;
    int numInfernalGlyphs;
    final int requiredGlyphs;

    public Circle(int requiredGlyphs) {
        this.requiredGlyphs = requiredGlyphs;
    }

    public Circle(int numRitualGlyphs, int numOtherwhereGlyphs, int numInfernalGlyphs) {
        this.requiredGlyphs = numRitualGlyphs + numOtherwhereGlyphs + numInfernalGlyphs;
        this.numRitualGlyphs = numRitualGlyphs;
        this.numOtherwhereGlyphs = numOtherwhereGlyphs;
        this.numInfernalGlyphs = numInfernalGlyphs;
    }

    public void addGlyph(World world, int posX, int posY, int posZ) {
        this.addGlyph(world, posX, posY, posZ, false);
    }

    public void addGlyph(World world, int posX, int posY, int posZ, boolean remove) {
        if (this.requiredGlyphs > 0) {
            Block blockID = world.func_147439_a(posX, posY, posZ);
            boolean found = false;
            if (Witchery.Blocks.GLYPH_RITUAL == blockID) {
                ++this.numRitualGlyphs;
                found = true;
            } else if (Witchery.Blocks.GLYPH_OTHERWHERE == blockID) {
                ++this.numOtherwhereGlyphs;
                found = true;
            } else if (Witchery.Blocks.GLYPH_INFERNAL == blockID) {
                ++this.numInfernalGlyphs;
                found = true;
            }
            if (remove && found) {
                world.func_147468_f(posX, posY, posZ);
            }
        }
    }

    public void removeIfRequired(ArrayList<Circle> circlesToFind) {
        if (this.isComplete()) {
            for (int i = 0; i < circlesToFind.size(); ++i) {
                if (!this.isMatch(circlesToFind.get(i))) continue;
                circlesToFind.remove(i);
                return;
            }
        }
    }

    private boolean isMatch(Circle other) {
        return this.numRitualGlyphs == other.numRitualGlyphs && this.numOtherwhereGlyphs == other.numOtherwhereGlyphs && this.numInfernalGlyphs == other.numInfernalGlyphs;
    }

    public boolean isComplete() {
        return this.requiredGlyphs == this.getGlyphCount();
    }

    private int getGlyphCount() {
        return this.numRitualGlyphs + this.numOtherwhereGlyphs + this.numInfernalGlyphs;
    }

    public int getRadius() {
        return (this.requiredGlyphs + 2) / 6 + 1;
    }

    public int getExclusiveMetadataValue() {
        if (this.numRitualGlyphs == this.requiredGlyphs) {
            return 1;
        }
        if (this.numOtherwhereGlyphs == this.requiredGlyphs) {
            return 2;
        }
        if (this.numInfernalGlyphs == this.requiredGlyphs) {
            return 3;
        }
        return 0;
    }

    public int getTextureIndex() {
        int size = this.getGlyphCount();
        if (size == 40) {
            return this.getExclusiveMetadataValue() - 1;
        }
        if (size == 28) {
            return this.getExclusiveMetadataValue() + 3 - 1;
        }
        return this.getExclusiveMetadataValue() + 6 - 1;
    }
}

