/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  travellersgear.api.ITravellersGear
 */
package witchinggadgets.client.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import travellersgear.api.ITravellersGear;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;

public class ModelMagicalBaubles
extends ModelBiped {
    List<ModelRenderer> parts = new ArrayList<ModelRenderer>();
    static ModelBiped[] modelMap = new ModelBiped[ItemMagicalBaubles.subNames.length];

    public ModelMagicalBaubles(EntityLivingBase entity, ItemStack stack) {
        super(0.01f, 0.0f, 64, 32);
        this.field_78115_e.field_78807_k = true;
        this.field_78116_c.field_78807_k = true;
        this.field_78114_d.field_78807_k = true;
        this.field_78124_i.field_78807_k = true;
        this.field_78123_h.field_78807_k = true;
        int slot = ((ITravellersGear)stack.func_77973_b()).getSlot(stack);
        int meta = stack.func_77960_j();
        float sizeMod = stack.func_77960_j() == 6 ? 0.5f : (slot == 1 ? 1.125f : 0.125f);
        int u = meta == 1 || meta == 2 ? 40 : 24;
        int v = meta == 2 || meta == 3 ? 24 : 16;
        int yOff = slot == 2 ? 7 : 0;
        this.field_78092_r.clear();
        if (stack.func_77960_j() == 6) {
            this.field_78115_e = new ModelRenderer((ModelBase)this, 0, 0);
            this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 7, 4, sizeMod);
            this.field_78115_e.func_78793_a(0.0f, 0.0f, 0.0f);
            this.field_78112_f.field_78807_k = true;
            this.field_78113_g.field_78807_k = true;
        } else {
            this.field_78112_f = new ModelRenderer((ModelBase)this, u, v);
            this.field_78112_f.func_78790_a(-3.0f, -2.0f + (float)yOff, -2.0f, 4, 4, 4, sizeMod);
            this.field_78112_f.func_78793_a(-5.0f, 2.0f, 0.0f);
            this.field_78113_g = new ModelRenderer((ModelBase)this, u, v);
            this.field_78113_g.field_78809_i = true;
            this.field_78113_g.func_78790_a(-1.0f, -2.0f + (float)yOff, -2.0f, 4, 4, 4, sizeMod);
            this.field_78113_g.func_78793_a(5.0f, 2.0f, 0.0f);
        }
    }

    public static ModelBiped getModel(EntityLivingBase entity, ItemStack stack) {
        if (stack == null || !(stack.func_77973_b() instanceof ITravellersGear)) {
            return null;
        }
        int slot = ((ITravellersGear)stack.func_77973_b()).getSlot(stack);
        if (slot < 1 || slot > 2) {
            return null;
        }
        if (modelMap[stack.func_77960_j()] == null) {
            ModelMagicalBaubles.modelMap[stack.func_77960_j()] = new ModelMagicalBaubles(entity, stack);
        }
        return modelMap[stack.func_77960_j()];
    }
}

