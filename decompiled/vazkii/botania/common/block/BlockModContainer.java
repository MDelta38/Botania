/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;

public abstract class BlockModContainer<T extends TileEntity>
extends BlockContainer {
    public int originalLight;

    protected BlockModContainer(Material par2Material) {
        super(par2Material);
        if (this.registerInCreative()) {
            this.func_149647_a(BotaniaCreativeTab.INSTANCE);
        }
    }

    public Block func_149663_c(String par1Str) {
        if (this.shouldRegisterInNameSet()) {
            GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)par1Str);
        }
        return super.func_149663_c(par1Str);
    }

    protected boolean shouldRegisterInNameSet() {
        return true;
    }

    public Block func_149715_a(float p_149715_1_) {
        this.originalLight = (int)(p_149715_1_ * 15.0f);
        return super.func_149715_a(p_149715_1_);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, (Block)this);
    }

    public boolean registerInCreative() {
        return true;
    }

    public abstract T func_149915_a(World var1, int var2);
}

