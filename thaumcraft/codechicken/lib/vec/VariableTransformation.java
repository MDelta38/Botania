/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package thaumcraft.codechicken.lib.vec;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.codechicken.lib.vec.Matrix4;
import thaumcraft.codechicken.lib.vec.Transformation;
import thaumcraft.codechicken.lib.vec.Vector3;

public abstract class VariableTransformation
extends Transformation {
    public Matrix4 mat;

    public VariableTransformation(Matrix4 mat) {
        this.mat = mat;
    }

    @Override
    public void applyN(Vector3 normal) {
        this.apply(normal);
    }

    @Override
    public void apply(Matrix4 mat) {
        mat.multiply(this.mat);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void glApply() {
        this.mat.glApply();
    }
}

