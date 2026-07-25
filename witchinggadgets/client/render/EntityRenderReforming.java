/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.Entity
 */
package witchinggadgets.client.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import witchinggadgets.common.items.EntityItemReforming;

public class EntityRenderReforming
extends RenderItem {
    public void func_76986_a(Entity entity, double x, double y, double z, float par8, float par9) {
        if (((EntityItemReforming)entity).renderDelay > 0) {
            this.field_76987_f = 0.4f;
            return;
        }
        this.field_76987_f = 1.0f;
        super.func_76986_a(entity, x, y, z, par8, par9);
    }
}

