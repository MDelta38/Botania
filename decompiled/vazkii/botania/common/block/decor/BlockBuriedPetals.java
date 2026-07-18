/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Method
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.Optional;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.item.ModItems;

public class BlockBuriedPetals
extends BlockModFlower {
    public BlockBuriedPetals() {
        super("buriedPetals");
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.1f, 1.0f);
        this.func_149715_a(0.25f);
    }

    @Optional.Method(modid="easycoloredlights")
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return ColoredLightHelper.getPackedColor(world.func_72805_g(x, y, z), this.originalLight);
    }

    @Override
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        float[] color = EntitySheep.field_70898_d[meta];
        Botania.proxy.setSparkleFXNoClip(true);
        Botania.proxy.sparkleFX(par1World, (double)par2 + 0.3 + (double)par5Random.nextFloat() * 0.5, (double)par3 + 0.1 + (double)par5Random.nextFloat() * 0.1, (double)par4 + 0.3 + (double)par5Random.nextFloat() * 0.5, color[0], color[1], color[2], par5Random.nextFloat(), 5);
        Botania.proxy.setSparkleFXNoClip(false);
    }

    @Override
    public boolean registerInCreative() {
        return false;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, (Block)this);
    }

    @Override
    public IIcon func_149691_a(int par1, int par2) {
        return this.field_149761_L;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ModItems.petal;
    }

    @Override
    public int func_149645_b() {
        return 0;
    }
}

