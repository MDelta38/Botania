/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package thaumcraft.client.renderers.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thaumcraft.common.entities.monster.EntityEldritchCrab;

public class ModelEldritchCrab
extends ModelBase {
    ModelRenderer TailHelm;
    ModelRenderer TailBare;
    ModelRenderer RFLeg1;
    ModelRenderer RClaw1;
    ModelRenderer Head1;
    ModelRenderer RClaw0;
    ModelRenderer RClaw2;
    ModelRenderer LClaw2;
    ModelRenderer LClaw1;
    ModelRenderer RArm;
    ModelRenderer Torso;
    ModelRenderer RRLeg1;
    ModelRenderer Head0;
    ModelRenderer LRLeg1;
    ModelRenderer LFLeg1;
    ModelRenderer RRLeg0;
    ModelRenderer RFLeg0;
    ModelRenderer LFLeg0;
    ModelRenderer LRLeg0;
    ModelRenderer LClaw0;
    ModelRenderer LArm;

    public ModelEldritchCrab() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.TailHelm = new ModelRenderer((ModelBase)this, 0, 0);
        this.TailHelm.func_78789_a(-4.5f, -4.5f, -0.4f, 9, 9, 9);
        this.TailHelm.func_78793_a(0.0f, 18.0f, 0.0f);
        this.setRotation(this.TailHelm, 0.1047198f, 0.0f, 0.0f);
        this.TailBare = new ModelRenderer((ModelBase)this, 64, 0);
        this.TailBare.func_78789_a(-4.0f, -4.0f, -0.4f, 8, 8, 8);
        this.TailBare.func_78793_a(0.0f, 18.0f, 0.0f);
        this.setRotation(this.TailBare, 0.1047198f, 0.0f, 0.0f);
        this.RClaw1 = new ModelRenderer((ModelBase)this, 0, 47);
        this.RClaw1.func_78789_a(-2.0f, -1.0f, -5.066667f, 4, 3, 5);
        this.RClaw1.func_78793_a(-6.0f, 15.5f, -10.0f);
        this.Head1 = new ModelRenderer((ModelBase)this, 0, 38);
        this.Head1.func_78789_a(-2.0f, -1.5f, -9.066667f, 4, 4, 1);
        this.Head1.func_78793_a(0.0f, 18.0f, 0.0f);
        this.RClaw0 = new ModelRenderer((ModelBase)this, 0, 55);
        this.RClaw0.func_78789_a(-2.0f, -2.5f, -3.066667f, 4, 5, 3);
        this.RClaw0.func_78793_a(-6.0f, 17.0f, -7.0f);
        this.RClaw2 = new ModelRenderer((ModelBase)this, 14, 54);
        this.RClaw2.func_78789_a(-1.5f, -1.0f, -4.066667f, 3, 2, 5);
        this.RClaw2.func_78793_a(-6.0f, 18.5f, -10.0f);
        this.setRotation(this.RClaw2, 0.3141593f, 0.0f, 0.0f);
        this.RArm = new ModelRenderer((ModelBase)this, 44, 4);
        this.RArm.func_78789_a(-1.0f, -1.0f, -5.066667f, 2, 2, 6);
        this.RArm.func_78793_a(-3.0f, 17.0f, -4.0f);
        this.setRotation(this.RArm, 0.0f, 0.7504916f, 0.0f);
        this.LClaw2 = new ModelRenderer((ModelBase)this, 14, 54);
        this.LClaw2.func_78789_a(-1.5f, -1.0f, -4.066667f, 3, 2, 5);
        this.LClaw2.func_78793_a(6.0f, 18.5f, -10.0f);
        this.setRotation(this.LClaw2, 0.3141593f, 0.0f, 0.0f);
        this.LClaw1 = new ModelRenderer((ModelBase)this, 0, 47);
        this.LClaw1.field_78809_i = true;
        this.LClaw1.func_78789_a(-2.0f, -1.0f, -5.066667f, 4, 3, 5);
        this.LClaw1.func_78793_a(6.0f, 15.5f, -10.0f);
        this.LClaw0 = new ModelRenderer((ModelBase)this, 0, 55);
        this.LClaw0.field_78809_i = true;
        this.LClaw0.func_78789_a(-2.0f, -2.5f, -3.066667f, 4, 5, 3);
        this.LClaw0.func_78793_a(6.0f, 17.0f, -7.0f);
        this.LArm = new ModelRenderer((ModelBase)this, 44, 4);
        this.LArm.func_78789_a(-1.0f, -1.0f, -4.066667f, 2, 2, 6);
        this.LArm.func_78793_a(4.0f, 17.0f, -5.0f);
        this.setRotation(this.LArm, 0.0f, -0.7504916f, 0.0f);
        this.Torso = new ModelRenderer((ModelBase)this, 0, 18);
        this.Torso.func_78789_a(-3.5f, -3.5f, -6.066667f, 7, 7, 6);
        this.Torso.func_78793_a(0.0f, 18.0f, 0.0f);
        this.setRotation(this.Torso, 0.0523599f, 0.0f, 0.0f);
        this.Head0 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Head0.func_78789_a(-2.5f, -2.0f, -8.066667f, 5, 5, 2);
        this.Head0.func_78793_a(0.0f, 18.0f, 0.0f);
        this.RRLeg1 = new ModelRenderer((ModelBase)this, 36, 4);
        this.RRLeg1.func_78789_a(-4.5f, 1.0f, -0.9f, 2, 5, 2);
        this.RRLeg1.func_78793_a(-4.0f, 20.0f, -1.5f);
        this.RFLeg1 = new ModelRenderer((ModelBase)this, 36, 4);
        this.RFLeg1.func_78789_a(-5.0f, 1.0f, -1.066667f, 2, 5, 2);
        this.RFLeg1.func_78793_a(-4.0f, 20.0f, -3.5f);
        this.LRLeg1 = new ModelRenderer((ModelBase)this, 36, 4);
        this.LRLeg1.func_78789_a(2.5f, 1.0f, -0.9f, 2, 5, 2);
        this.LRLeg1.func_78793_a(4.0f, 20.0f, -1.5f);
        this.LFLeg1 = new ModelRenderer((ModelBase)this, 36, 4);
        this.LFLeg1.func_78789_a(3.0f, 1.0f, -1.066667f, 2, 5, 2);
        this.LFLeg1.func_78793_a(4.0f, 20.0f, -3.5f);
        this.RRLeg0 = new ModelRenderer((ModelBase)this, 36, 0);
        this.RRLeg0.func_78789_a(-4.5f, -1.0f, -0.9f, 6, 2, 2);
        this.RRLeg0.func_78793_a(-4.0f, 20.0f, -1.5f);
        this.RFLeg0 = new ModelRenderer((ModelBase)this, 36, 0);
        this.RFLeg0.func_78789_a(-5.0f, -1.0f, -1.066667f, 6, 2, 2);
        this.RFLeg0.func_78793_a(-4.0f, 20.0f, -3.5f);
        this.LFLeg0 = new ModelRenderer((ModelBase)this, 36, 0);
        this.LFLeg0.func_78789_a(-1.0f, -1.0f, -1.066667f, 6, 2, 2);
        this.LFLeg0.func_78793_a(4.0f, 20.0f, -3.5f);
        this.LRLeg0 = new ModelRenderer((ModelBase)this, 36, 0);
        this.LRLeg0.func_78789_a(-1.5f, -1.0f, -0.9f, 6, 2, 2);
        this.LRLeg0.func_78793_a(4.0f, 20.0f, -1.5f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        if (entity instanceof EntityEldritchCrab && ((EntityEldritchCrab)entity).hasHelm()) {
            this.TailHelm.func_78785_a(f5);
        } else {
            this.TailBare.func_78785_a(f5);
        }
        this.RFLeg1.func_78785_a(f5);
        this.RClaw1.func_78785_a(f5);
        this.Head1.func_78785_a(f5);
        this.RClaw0.func_78785_a(f5);
        this.RClaw2.func_78785_a(f5);
        this.LClaw2.func_78785_a(f5);
        this.LClaw1.func_78785_a(f5);
        this.RArm.func_78785_a(f5);
        this.Torso.func_78785_a(f5);
        this.RRLeg1.func_78785_a(f5);
        this.Head0.func_78785_a(f5);
        this.LRLeg1.func_78785_a(f5);
        this.LFLeg1.func_78785_a(f5);
        this.RRLeg0.func_78785_a(f5);
        this.RFLeg0.func_78785_a(f5);
        this.LFLeg0.func_78785_a(f5);
        this.LRLeg0.func_78785_a(f5);
        this.LClaw0.func_78785_a(f5);
        this.LArm.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.setRotation(this.RRLeg1, 0.0f, 0.2094395f, 0.4363323f);
        this.setRotation(this.RFLeg1, 0.0f, -0.2094395f, 0.4363323f);
        this.setRotation(this.LRLeg1, 0.0f, -0.2094395f, -0.4363323f);
        this.setRotation(this.LFLeg1, 0.0f, 0.2094395f, -0.4363323f);
        this.setRotation(this.RRLeg0, 0.0f, 0.2094395f, 0.4363323f);
        this.setRotation(this.RFLeg0, 0.0f, -0.2094395f, 0.4363323f);
        this.setRotation(this.LFLeg0, 0.0f, 0.2094395f, -0.4363323f);
        this.setRotation(this.LRLeg0, 0.0f, -0.2094395f, -0.4363323f);
        float f9 = -(MathHelper.func_76134_b((float)(par1 * 0.6662f * 2.0f + 0.0f)) * 0.4f) * par2;
        float f10 = -(MathHelper.func_76134_b((float)(par1 * 0.6662f * 2.0f + (float)Math.PI)) * 0.4f) * par2;
        this.RRLeg1.field_78796_g += f9;
        this.RRLeg0.field_78796_g += f9;
        this.LRLeg1.field_78796_g += -f9;
        this.LRLeg0.field_78796_g += -f9;
        this.RFLeg1.field_78796_g += f10;
        this.RFLeg0.field_78796_g += f10;
        this.LFLeg1.field_78796_g += -f10;
        this.LFLeg0.field_78796_g += -f10;
        this.RRLeg1.field_78808_h += f9;
        this.RRLeg0.field_78808_h += f9;
        this.LRLeg1.field_78808_h += -f9;
        this.LRLeg0.field_78808_h += -f9;
        this.RFLeg1.field_78808_h += f10;
        this.RFLeg0.field_78808_h += f10;
        this.LFLeg1.field_78808_h += -f10;
        this.LFLeg0.field_78808_h += -f10;
        this.TailBare.field_78796_g = this.TailHelm.field_78796_g = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.125f;
        this.TailBare.field_78808_h = this.TailHelm.field_78808_h = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * par2 * 0.125f;
        this.RClaw2.field_78795_f = 0.3141593f - MathHelper.func_76126_a((float)((float)entity.field_70173_aa / 4.0f)) * 0.25f;
        this.LClaw2.field_78795_f = 0.3141593f + MathHelper.func_76126_a((float)((float)entity.field_70173_aa / 4.1f)) * 0.25f;
        this.RClaw1.field_78795_f = MathHelper.func_76126_a((float)((float)entity.field_70173_aa / 4.0f)) * 0.125f;
        this.LClaw1.field_78795_f = -MathHelper.func_76126_a((float)((float)entity.field_70173_aa / 4.1f)) * 0.125f;
    }
}

