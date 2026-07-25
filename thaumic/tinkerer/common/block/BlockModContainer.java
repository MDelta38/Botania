/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.registry.ITTinkererBlock;

public abstract class BlockModContainer<T extends TileEntity>
extends BlockContainer
implements ITTinkererBlock {
    protected BlockModContainer(Material par2Material) {
        super(par2Material);
        if (this.registerInCreative()) {
            this.func_149647_a(ModCreativeTab.INSTANCE);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, (Block)this);
    }

    boolean registerInCreative() {
        return true;
    }

    public abstract T func_149915_a(World var1, int var2);

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }
}

