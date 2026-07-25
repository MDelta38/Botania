/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package witchinggadgets.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import witchinggadgets.common.blocks.tiles.TileEntityAgeingStone;

public class BlockTimeStone
extends BlockContainer {
    public BlockTimeStone() {
        super(Material.field_151576_e);
        this.func_149711_c(0.8f);
        this.func_149752_b(10.0f);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    public void func_149651_a(IIconRegister iconRegister) {
        this.field_149761_L = iconRegister.func_94245_a("witchinggadgets:timeStone");
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEntityAgeingStone();
    }
}

