/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package vazkii.botania.client.model;

import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPump
extends ModelBase {
    private ArrayList<ModelRenderer> parts = new ArrayList();
    private ArrayList<ModelRenderer> innerRing = new ArrayList();
    private ArrayList<ModelRenderer> outerRing = new ArrayList();

    public ModelPump() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        ModelRenderer main = new ModelRenderer((ModelBase)this, 0, 0);
        main.func_78789_a(-2.0f, -2.0f, -7.0f, 4, 4, 14);
        main.func_78793_a(0.0f, 4.0f, 0.0f);
        float r = 0.0f;
        while ((double)r <= Math.PI * 2) {
            ModelRenderer side = new ModelRenderer((ModelBase)this, 22, 0);
            side.func_78789_a(-4.0f, -4.0f, 7.0f, 8, 8, 1);
            side.func_78793_a(0.0f, 0.0f, 0.0f);
            side.field_78796_g = r;
            main.func_78792_a(side);
            r = (float)((double)r + Math.PI);
        }
        r = 0.0f;
        while ((double)r <= Math.PI * 2) {
            ModelRenderer innerPlate = new ModelRenderer((ModelBase)this, 0, 18);
            ModelRenderer outerPlate = new ModelRenderer((ModelBase)this, 22, 18);
            innerPlate.func_78789_a(-3.0f, -3.0f, -7.0f, 5, 1, 6);
            innerPlate.func_78793_a(0.0f, 4.0f, 0.0f);
            innerPlate.field_78808_h = r;
            this.innerRing.add(innerPlate);
            this.parts.add(innerPlate);
            outerPlate.func_78789_a(-4.0f, -4.0f, 3.0f, 7, 1, 4);
            outerPlate.func_78793_a(0.0f, 4.0f, 0.0f);
            outerPlate.field_78808_h = r;
            this.outerRing.add(outerPlate);
            this.parts.add(outerPlate);
            r = (float)((double)r + 1.5707963267948966);
        }
        this.parts.add(main);
    }

    public void render(float ringPos) {
        for (ModelRenderer iRing : this.innerRing) {
            iRing.field_78798_e = ringPos;
        }
        for (ModelRenderer part : this.parts) {
            part.func_78785_a(0.0625f);
        }
    }
}

