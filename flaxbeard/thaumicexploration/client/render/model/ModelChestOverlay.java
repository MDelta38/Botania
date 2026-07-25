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
public class ModelChestOverlay
extends ModelBase {
    public ModelRenderer chestLid = new ModelRenderer((ModelBase)this, 0, 0).func_78787_b(64, 64);
    public ModelRenderer chestBelow;
    public ModelRenderer chestKnob;

    public ModelChestOverlay() {
        this.chestLid.func_78790_a(0.0f, -5.1f, -14.0f, 14, 5, 14, 0.0f);
        this.chestLid.field_78800_c = 1.0f;
        this.chestLid.field_78797_d = 7.0f;
        this.chestLid.field_78798_e = 15.0f;
        this.chestBelow = new ModelRenderer((ModelBase)this, 0, 21).func_78787_b(64, 64);
        this.chestBelow.func_78790_a(-1.0f, 0.0f, -1.0f, 16, 10, 16, 0.0f);
        this.chestBelow.field_78800_c = 1.0f;
        this.chestBelow.field_78797_d = 6.0f;
        this.chestBelow.field_78798_e = 1.0f;
    }

    public void renderAll() {
        this.chestLid.func_78785_a(0.0625f);
        this.chestBelow.func_78785_a(0.0625f);
    }
}

