/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileTinyPlanet;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockTinyPlanet
extends BlockModContainer
implements ILexiconable {
    protected BlockTinyPlanet() {
        super(Material.field_151576_e);
        this.func_149711_c(20.0f);
        this.func_149752_b(100.0f);
        this.func_149672_a(field_149769_e);
        float size = 0.1875f;
        this.func_149676_a(size, size, size, 1.0f - size, 1.0f - size, 1.0f - size);
        this.func_149663_c("tinyPlanetBlock");
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileTinyPlanet();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.tinyPlanet;
    }
}

