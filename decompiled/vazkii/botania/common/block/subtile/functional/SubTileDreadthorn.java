/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityAnimal
 */
package vazkii.botania.common.block.subtile.functional;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.functional.SubTileBellethorn;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileDreadthorn
extends SubTileBellethorn {
    @Override
    public int getColor() {
        return 2493253;
    }

    @Override
    public IEntitySelector getSelector() {
        return new IEntitySelector(){

            public boolean func_82704_a(Entity var1) {
                return var1 instanceof EntityAnimal && ((EntityAnimal)var1).func_70874_b() == 0;
            }
        };
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.dreadthorne;
    }
}

