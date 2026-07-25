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
public class ModelCandle
extends ModelBase {
    public ModelRenderer candleBase = new ModelRenderer((ModelBase)this, 0, 0).func_78787_b(16, 96);
    public ModelRenderer candleWick;

    public ModelCandle() {
        this.candleBase.func_78789_a(6.0f, 4.0f, 6.0f, 4, 8, 4);
        this.candleWick = new ModelRenderer((ModelBase)this, 0, 0).func_78787_b(16, 16);
        this.candleWick.func_78789_a(7.5f, 12.0f, 7.5f, 1, 2, 1);
    }

    public void renderAll(int x) {
        this.candleBase.func_78784_a(0, x % 8 * 12);
        this.candleBase.func_78785_a(0.0625f);
    }

    public void renderWick() {
        this.candleWick.func_78785_a(0.0625f);
    }
}

