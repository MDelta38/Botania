/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Method
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import cpw.mods.fml.common.Optional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.mana.TileRFGenerator;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockRFGenerator
extends BlockModContainer
implements ILexiconable {
    public BlockRFGenerator() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("rfGenerator");
    }

    @Optional.Method(modid="CoFHAPI|energy")
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileRFGenerator) {
            ((TileRFGenerator)tile).onNeighborTileChange(tileX, tileY, tileZ);
        }
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileRFGenerator();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.rfGenerator;
    }
}

