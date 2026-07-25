/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockVat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockVatSolid
extends BlockVat {
    public IIcon iconLidCenterTop;
    public IIcon iconLidSideCenter;
    public IIcon iconLidLeftRight;
    public IIcon iconInnerCenter;
    public IIcon iconInnerPosZ;
    public IIcon iconInnerNegZ;
    public IIcon iconInnerPosX;
    public IIcon iconInnerNegX;
    public IIcon iconInnerCornerA;
    public IIcon iconInnerCornerB;
    public IIcon iconInnerCornerC;
    public IIcon iconInnerCornerD;
    public IIcon iconBaseCenter;
    public IIcon iconBaseLeftRight;
    public IIcon iconBaseSide;
    public IIcon iconBaseSideBottom;
    public IIcon iconGreatwood;

    public BlockVatSolid() {
        super(Material.field_151575_d);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 0;
    }

    @Override
    public int func_149645_b() {
        return ThaumicHorizons.blockVatSolidRI;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconLidCenterTop = ir.func_94245_a("thaumichorizons:vatlidtopcenter");
        this.iconLidSideCenter = ir.func_94245_a("thaumichorizons:vatlidcenter");
        this.iconLidLeftRight = ir.func_94245_a("thaumichorizons:vatlidleftright");
        this.iconInnerCenter = ir.func_94245_a("thaumichorizons:vatinnercenter");
        this.iconInnerPosZ = ir.func_94245_a("thaumichorizons:vatinnerposz");
        this.iconInnerNegZ = ir.func_94245_a("thaumichorizons:vatinnernegz");
        this.iconInnerPosX = ir.func_94245_a("thaumichorizons:vatinnerposx");
        this.iconInnerNegX = ir.func_94245_a("thaumichorizons:vatinnernegx");
        this.iconInnerCornerA = ir.func_94245_a("thaumichorizons:vatinnercornera");
        this.iconInnerCornerB = ir.func_94245_a("thaumichorizons:vatinnercornerb");
        this.iconInnerCornerC = ir.func_94245_a("thaumichorizons:vatinnercornerc");
        this.iconInnerCornerD = ir.func_94245_a("thaumichorizons:vatinnercornerd");
        this.iconBaseCenter = ir.func_94245_a("thaumichorizons:vatbasecenter");
        this.iconBaseLeftRight = ir.func_94245_a("thaumichorizons:vatbaseleftright");
        this.iconBaseSide = ir.func_94245_a("thaumichorizons:vatbasesidecenter");
        this.iconBaseSideBottom = ir.func_94245_a("thaumichorizons:vatbasesidebottom");
        this.iconGreatwood = ir.func_94245_a("thaumcraft:planks_greatwood");
    }

    @Override
    public IIcon func_149691_a(int par1, int par2) {
        return this.iconGreatwood;
    }
}

