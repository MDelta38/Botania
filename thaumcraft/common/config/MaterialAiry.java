/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.MapColor
 *  net.minecraft.block.material.Material
 */
package thaumcraft.common.config;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialAiry
extends Material {
    private int mobilityFlag;

    public MaterialAiry(MapColor par1MapColor) {
        super(par1MapColor);
        this.func_76219_n();
        this.func_76221_f();
    }

    public boolean func_76220_a() {
        return false;
    }

    public boolean func_76222_j() {
        return false;
    }

    public boolean func_76228_b() {
        return false;
    }

    public boolean func_76230_c() {
        return true;
    }

    protected Material func_76221_f() {
        return this;
    }

    protected Material func_76219_n() {
        this.mobilityFlag = 1;
        return this;
    }

    public int func_76227_m() {
        return this.mobilityFlag;
    }
}

