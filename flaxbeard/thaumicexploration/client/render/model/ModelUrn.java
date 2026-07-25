/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package flaxbeard.thaumicexploration.client.render.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ModelUrn
extends ModelBase {
    public ModelRenderer urn = new ModelRenderer((ModelBase)this, 0, 0).func_78787_b(64, 64);

    public ModelUrn() {
        this.urn.func_78789_a(-6.0f, -16.0f, -6.0f, 12, 9, 12);
        this.urn.func_78789_a(-3.0f, -7.0f, -3.0f, 6, 4, 6);
        this.urn.func_78789_a(-4.0f, -3.0f, -4.0f, 8, 3, 8);
        this.urn.field_78809_i = true;
    }

    public void renderAll() {
        this.urn.func_78785_a(0.0625f);
    }
}

