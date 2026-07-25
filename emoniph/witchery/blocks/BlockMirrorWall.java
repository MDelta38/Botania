/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.blocks.BlockBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockMirrorWall
extends BlockBase {
    @SideOnly(value=Side.CLIENT)
    protected IIcon[] icons;

    public BlockMirrorWall() {
        super(Material.field_151576_e);
        this.func_149722_s();
        this.func_149752_b(9999.0f);
        this.func_149649_H();
    }

    public int func_149645_b() {
        return super.func_149645_b();
    }

    public int func_149635_D() {
        return 0xCCDDFF;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        return this.func_149635_D();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149741_i(int par1) {
        return this.func_149635_D();
    }

    protected boolean func_149700_E() {
        return false;
    }

    public int func_149745_a(Random rand) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side != 0 && side != 1 ? this.icons[1] : this.icons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.icons = new IIcon[]{iconRegister.func_94245_a(this.func_149641_N() + "_still"), iconRegister.func_94245_a(this.func_149641_N() + "_flow")};
    }
}

