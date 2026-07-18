/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMiniIsland
extends ModelBase {
    ModelRenderer island;

    public ModelMiniIsland() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.func_78085_a("island.Shape0", 0, 0);
        this.func_78085_a("island.Shape1-1", 8, 0);
        this.func_78085_a("island.Shape1-2", 16, 0);
        this.func_78085_a("island.Shape1-3", 24, 0);
        this.func_78085_a("island.Shape1-4", 32, 0);
        this.func_78085_a("island.Shape2-1", 8, 6);
        this.func_78085_a("island.Shape2-2", 16, 6);
        this.func_78085_a("island.Shape2-3", 24, 6);
        this.func_78085_a("island.Shape2-4", 32, 6);
        this.func_78085_a("island.Shape2-5", 8, 11);
        this.func_78085_a("island.Shape2-6", 16, 11);
        this.func_78085_a("island.Shape2-7", 24, 11);
        this.func_78085_a("island.Shape2-8", 32, 11);
        this.func_78085_a("island.Shape3-1", 8, 16);
        this.func_78085_a("island.Shape3-2", 16, 16);
        this.func_78085_a("island.Shape3-3", 24, 16);
        this.func_78085_a("island.Shape3-4", 32, 16);
        this.func_78085_a("island.Shape3-5", 8, 20);
        this.func_78085_a("island.Shape3-6", 16, 20);
        this.func_78085_a("island.Shape3-7", 24, 20);
        this.func_78085_a("island.Shape3-8", 32, 20);
        this.func_78085_a("island.Shape4-1", 8, 24);
        this.func_78085_a("island.Shape4-2", 16, 24);
        this.func_78085_a("island.Shape4-3", 24, 24);
        this.func_78085_a("island.Shape4-4", 32, 24);
        this.island = new ModelRenderer((ModelBase)this, "island");
        this.island.func_78793_a(0.0f, 16.0f, 0.0f);
        this.island.func_78786_a("Shape0", -1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.island.func_78786_a("Shape1-1", -1.0f, 0.0f, -3.0f, 2, 4, 2);
        this.island.func_78786_a("Shape1-2", 1.0f, 0.0f, -1.0f, 2, 4, 2);
        this.island.func_78786_a("Shape1-3", -1.0f, 0.0f, 1.0f, 2, 4, 2);
        this.island.func_78786_a("Shape1-4", -3.0f, 0.0f, -1.0f, 2, 4, 2);
        this.island.func_78786_a("Shape2-1", -1.0f, 0.0f, -5.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-2", 3.0f, 0.0f, -1.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-3", -1.0f, 0.0f, 3.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-4", -5.0f, 0.0f, -1.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-5", 1.0f, 0.0f, -3.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-6", 1.0f, 0.0f, 1.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-7", -3.0f, 0.0f, 1.0f, 2, 3, 2);
        this.island.func_78786_a("Shape2-8", -3.0f, 0.0f, -3.0f, 2, 3, 2);
        this.island.func_78786_a("Shape3-1", 1.0f, 0.0f, -5.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-2", 3.0f, 0.0f, 1.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-3", -3.0f, 0.0f, 3.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-4", -5.0f, 0.0f, -3.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-5", 3.0f, 0.0f, -3.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-6", 1.0f, 0.0f, 3.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-7", -5.0f, 0.0f, 1.0f, 2, 2, 2);
        this.island.func_78786_a("Shape3-8", -3.0f, 0.0f, -5.0f, 2, 2, 2);
        this.island.func_78786_a("Shape4-1", 3.0f, 0.0f, -5.0f, 2, 1, 2);
        this.island.func_78786_a("Shape4-2", 3.0f, 0.0f, 3.0f, 2, 1, 2);
        this.island.func_78786_a("Shape4-3", -5.0f, 0.0f, 3.0f, 2, 1, 2);
        this.island.func_78786_a("Shape4-4", -5.0f, 0.0f, -5.0f, 2, 1, 2);
    }

    public void render() {
        this.island.func_78785_a(0.0625f);
    }
}

