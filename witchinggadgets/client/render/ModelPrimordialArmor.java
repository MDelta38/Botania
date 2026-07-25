/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import java.util.HashMap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelPrimordialArmor
extends ModelBiped {
    public ModelRenderer ll_plate_o0;
    public ModelRenderer ll_plate_o1;
    public ModelRenderer ll_plate_b0;
    public ModelRenderer ll_plate_b1;
    public ModelRenderer ll_plate_f0;
    public ModelRenderer ll_plate_f1;
    public ModelRenderer ll_plate_f2;
    public ModelRenderer ll_plate_f3;
    public ModelRenderer lb_base;
    public ModelRenderer ll_chainmail;
    public ModelRenderer lb_front;
    public ModelRenderer lb_side0;
    public ModelRenderer lb_side1;
    public ModelRenderer rl_plate_o0;
    public ModelRenderer rl_plate_o1;
    public ModelRenderer rl_plate_b0;
    public ModelRenderer rl_plate_b1;
    public ModelRenderer rl_plate_f0;
    public ModelRenderer rl_plate_f1;
    public ModelRenderer rl_plate_f2;
    public ModelRenderer rl_plate_f3;
    public ModelRenderer rb_base;
    public ModelRenderer rl_chainmail;
    public ModelRenderer rb_front;
    public ModelRenderer rb_side0;
    public ModelRenderer rb_side1;
    public ModelRenderer la_plate0;
    public ModelRenderer la_plate1;
    public ModelRenderer la_shoulder0;
    public ModelRenderer la_shoulder1;
    public ModelRenderer la_shoulder2;
    public ModelRenderer la_shoulder3;
    public ModelRenderer la_shoulder4;
    public ModelRenderer ra_plate0;
    public ModelRenderer ra_plate1;
    public ModelRenderer ra_shoulder0;
    public ModelRenderer ra_shoulder1;
    public ModelRenderer ra_shoulder2;
    public ModelRenderer ra_shoulder3;
    public ModelRenderer ra_shoulder4;
    public ModelRenderer bd_belt;
    public ModelRenderer bd_chainmail;
    public ModelRenderer bd_chest;
    public ModelRenderer bd_strap0;
    public ModelRenderer bd_strap1;
    public ModelRenderer bd_strap2;
    public ModelRenderer bd_add0;
    public ModelRenderer bd_add1;
    public ModelRenderer bd_add2;
    public ModelRenderer bd_add3;
    public ModelRenderer bd_back;
    public ModelRenderer bd_strap3;
    public ModelRenderer bd_strap4;
    public ModelRenderer bd_strap5;
    public ModelRenderer bd_add4;
    public ModelRenderer bd_add5;
    public ModelRenderer bd_add6;
    public ModelRenderer bd_add7;
    public ModelRenderer hm_top;
    public ModelRenderer hm_l0;
    public ModelRenderer hm_r0;
    public ModelRenderer hm_b0;
    public ModelRenderer hm_b1;
    public ModelRenderer hm_f0;
    public ModelRenderer face_0;
    public ModelRenderer face_mask;
    public ModelRenderer face_goggles;
    public ModelRenderer face_l1;
    public ModelRenderer face_l2;
    public ModelRenderer face_r1;
    public ModelRenderer face_r2;
    static ModelPrimordialArmor[][] modelHelm = new ModelPrimordialArmor[4][2];
    static ModelPrimordialArmor modelChest;
    static ModelPrimordialArmor modelLegs;
    static ModelPrimordialArmor modelBoots;
    static HashMap<ItemStack, ModelPrimordialArmor> modelMap;

    public ModelPrimordialArmor(ItemStack stack) {
        super(((ItemArmor)stack.func_77973_b()).field_77881_a == 3 ? 0.3125f : 0.0625f, 0.0f, 128, 64);
        float scale = ((ItemArmor)stack.func_77973_b()).field_77881_a == 3 ? 0.3125f : 0.0625f;
        this.field_78114_d.func_78784_a(0, 0);
        this.field_78115_e.field_78804_l.clear();
        int mask = stack.func_77942_o() && stack.func_77978_p().func_74764_b("mask") ? stack.func_77978_p().func_74762_e("mask") : -1;
        boolean goggles = stack.func_77942_o() && stack.func_77978_p().func_74764_b("goggles");
        this.bd_chainmail = new ModelRenderer((ModelBase)this, 16, 16);
        this.bd_chainmail.func_78793_a(0.0f, 0.0f + scale, 0.0f);
        this.bd_chainmail.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, scale);
        this.bd_chest = new ModelRenderer((ModelBase)this, 66, 31);
        this.bd_chest.func_78793_a(0.0f, 1.0f, -2.5f);
        this.bd_chest.func_78790_a(-3.5f, 0.0f, -0.5f, 7, 9, 2, scale);
        this.bd_add0 = new ModelRenderer((ModelBase)this, 66, 46);
        this.bd_add0.func_78793_a(0.0f, 0.0f, -3.3f);
        this.bd_add0.func_78790_a(-2.0f, 0.0f, -1.0f, 4, 4, 2, scale);
        this.setRotateAngle(this.bd_add0, 0.31415927f, 0.0f, 0.0f);
        this.bd_add1 = new ModelRenderer((ModelBase)this, 66, 52);
        this.bd_add1.field_78809_i = true;
        this.bd_add1.func_78793_a(2.5f, 8.7f, -2.5f);
        this.bd_add1.func_78790_a(-1.5f, 0.0f, -0.5f, 3, 2, 1, scale);
        this.setRotateAngle(this.bd_add1, -0.2617994f, -0.34906584f, 0.0f);
        this.bd_add2 = new ModelRenderer((ModelBase)this, 66, 52);
        this.bd_add2.func_78793_a(-2.5f, 8.7f, -2.5f);
        this.bd_add2.func_78790_a(-1.5f, 0.0f, -0.5f, 3, 2, 1, scale);
        this.setRotateAngle(this.bd_add2, -0.2617994f, 0.34906584f, 0.0f);
        this.bd_add3 = new ModelRenderer((ModelBase)this, 66, 52);
        this.bd_add3.func_78793_a(0.0f, 8.7f, -2.9f);
        this.bd_add3.func_78790_a(-1.5f, 0.0f, -0.5f, 3, 2, 1, scale);
        this.setRotateAngle(this.bd_add3, -0.2617994f, 0.0f, 0.0f);
        this.bd_strap0 = new ModelRenderer((ModelBase)this, 66, 42);
        this.bd_strap0.func_78793_a(0.0f, 6.7f, -2.0f);
        this.bd_strap0.func_78790_a(-4.0f, 0.0f, -0.5f, 8, 2, 1, scale);
        this.bd_strap1 = new ModelRenderer((ModelBase)this, 66, 42);
        this.bd_strap1.field_78809_i = true;
        this.bd_strap1.func_78793_a(2.8f, -0.4f, -2.0f);
        this.bd_strap1.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 2, 1, scale);
        this.setRotateAngle(this.bd_strap1, 0.0f, 0.0f, 0.3926991f);
        this.bd_strap2 = new ModelRenderer((ModelBase)this, 66, 42);
        this.bd_strap2.func_78793_a(-2.8f, -0.4f, -2.0f);
        this.bd_strap2.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 2, 1, scale);
        this.setRotateAngle(this.bd_strap2, 0.0f, 0.0f, -0.3926991f);
        this.bd_back = new ModelRenderer((ModelBase)this, 80, 46);
        this.bd_back.func_78793_a(0.0f, 1.0f, 1.5f);
        this.bd_back.func_78790_a(-3.5f, 0.0f, -0.5f, 7, 9, 2, scale);
        this.bd_strap3 = new ModelRenderer((ModelBase)this, 66, 42);
        this.bd_strap3.func_78793_a(0.0f, 5.7f, 2.0f);
        this.bd_strap3.func_78790_a(-4.0f, 0.0f, -0.5f, 8, 2, 1, scale);
        this.bd_strap4 = new ModelRenderer((ModelBase)this, 66, 42);
        this.bd_strap4.field_78809_i = true;
        this.bd_strap4.func_78793_a(2.8f, -0.4f, 2.0f);
        this.bd_strap4.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 2, 1, scale);
        this.setRotateAngle(this.bd_strap4, 0.0f, 0.0f, 0.3926991f);
        this.bd_strap5 = new ModelRenderer((ModelBase)this, 66, 42);
        this.bd_strap5.func_78793_a(-2.8f, -0.4f, 2.0f);
        this.bd_strap5.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 2, 1, scale);
        this.setRotateAngle(this.bd_strap5, 0.0f, 0.0f, -0.3926991f);
        this.bd_add4 = new ModelRenderer((ModelBase)this, 84, 40);
        this.bd_add4.func_78793_a(-2.0f, 7.9f, 2.3f);
        this.bd_add4.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.bd_add4, 0.30019662f, -0.13962634f, 0.0f);
        this.bd_add5 = new ModelRenderer((ModelBase)this, 84, 40);
        this.bd_add5.func_78793_a(-2.0f, 9.8f, 2.1f);
        this.bd_add5.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.bd_add5, 0.30019662f, -0.13962634f, 0.0f);
        this.bd_add6 = new ModelRenderer((ModelBase)this, 84, 40);
        this.bd_add6.field_78809_i = true;
        this.bd_add6.func_78793_a(2.0f, 7.9f, 2.3f);
        this.bd_add6.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.bd_add6, 0.30019662f, 0.13962634f, 0.0f);
        this.bd_add7 = new ModelRenderer((ModelBase)this, 84, 40);
        this.bd_add7.field_78809_i = true;
        this.bd_add7.func_78793_a(2.0f, 9.8f, 2.1f);
        this.bd_add7.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.bd_add7, 0.30019662f, 0.13962634f, 0.0f);
        this.bd_belt = new ModelRenderer((ModelBase)this, 66, 56);
        this.bd_belt.func_78793_a(0.0f, 10.2f, 0.0f);
        this.bd_belt.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 2, 4, scale);
        this.la_shoulder0 = new ModelRenderer((ModelBase)this, 42, 41);
        this.la_shoulder0.field_78809_i = true;
        this.la_shoulder0.func_78793_a(0.7f, -0.5f, 0.0f);
        this.la_shoulder0.func_78790_a(-2.5f, -2.5f, -2.5f, 5, 5, 5, scale);
        this.la_shoulder1 = new ModelRenderer((ModelBase)this, 42, 51);
        this.la_shoulder1.func_78793_a(2.5f, -3.3f, 0.0f);
        this.la_shoulder1.func_78790_a(-0.5f, 0.0f, -2.5f, 1, 5, 5, scale);
        this.setRotateAngle(this.la_shoulder1, 0.0f, 0.0f, -0.32332313f);
        this.la_shoulder2 = new ModelRenderer((ModelBase)this, 42, 51);
        this.la_shoulder2.field_78809_i = true;
        this.la_shoulder2.func_78793_a(2.5f, -0.5f, 0.0f);
        this.la_shoulder2.func_78790_a(-0.5f, 0.0f, -2.5f, 1, 5, 5, scale);
        this.setRotateAngle(this.la_shoulder2, 0.0f, 0.0f, -0.20943952f);
        this.la_shoulder3 = new ModelRenderer((ModelBase)this, 42, 62);
        this.la_shoulder3.field_78809_i = true;
        this.la_shoulder3.func_78793_a(-1.0f, -3.2f, 0.0f);
        this.la_shoulder3.func_78790_a(-0.5f, 0.0f, -0.5f, 4, 1, 1, scale);
        this.setRotateAngle(this.la_shoulder3, 0.0f, 0.0f, -0.075049154f);
        this.la_shoulder4 = new ModelRenderer((ModelBase)this, 42, 62);
        this.la_shoulder4.field_78809_i = true;
        this.la_shoulder4.func_78793_a(-1.1f, -2.8f, 0.0f);
        this.la_shoulder4.func_78790_a(-0.1f, -0.5f, -0.5f, 3, 1, 1, scale);
        this.setRotateAngle(this.la_shoulder4, 0.0f, 0.0f, -0.50265485f);
        this.la_plate0 = new ModelRenderer((ModelBase)this, 42, 32);
        this.la_plate0.field_78809_i = true;
        this.la_plate0.func_78793_a(3.0f, 5.5f, 0.0f);
        this.la_plate0.func_78790_a(-1.5f, 0.0f, -2.5f, 2, 4, 5, scale);
        this.la_plate1 = new ModelRenderer((ModelBase)this, 56, 32);
        this.la_plate1.field_78809_i = true;
        this.la_plate1.func_78793_a(2.5f, 3.7f, 0.0f);
        this.la_plate1.func_78790_a(-0.5f, 0.0f, -2.0f, 1, 2, 4, scale);
        this.setRotateAngle(this.la_plate1, 0.0f, 0.0f, -0.17453292f);
        this.ra_shoulder0 = new ModelRenderer((ModelBase)this, 42, 41);
        this.ra_shoulder0.func_78793_a(-0.7f, -0.5f, 0.0f);
        this.ra_shoulder0.func_78790_a(-2.5f, -2.5f, -2.5f, 5, 5, 5, scale);
        this.ra_shoulder1 = new ModelRenderer((ModelBase)this, 42, 51);
        this.ra_shoulder1.func_78793_a(-2.5f, -3.3f, 0.0f);
        this.ra_shoulder1.func_78790_a(-0.5f, 0.0f, -2.5f, 1, 5, 5, scale);
        this.setRotateAngle(this.ra_shoulder1, 0.0f, 0.0f, 0.32332313f);
        this.ra_shoulder2 = new ModelRenderer((ModelBase)this, 42, 51);
        this.ra_shoulder2.func_78793_a(-2.5f, -0.5f, 0.0f);
        this.ra_shoulder2.func_78790_a(-0.5f, 0.0f, -2.5f, 1, 5, 5, scale);
        this.setRotateAngle(this.ra_shoulder2, 0.0f, 0.0f, 0.20943952f);
        this.ra_shoulder3 = new ModelRenderer((ModelBase)this, 42, 62);
        this.ra_shoulder3.func_78793_a(1.0f, -3.2f, 0.0f);
        this.ra_shoulder3.func_78790_a(-3.5f, 0.0f, -0.5f, 4, 1, 1, scale);
        this.setRotateAngle(this.ra_shoulder3, 0.0f, 0.0f, 0.075049154f);
        this.ra_shoulder4 = new ModelRenderer((ModelBase)this, 42, 62);
        this.ra_shoulder4.func_78793_a(1.1f, -2.8f, 0.0f);
        this.ra_shoulder4.func_78790_a(-2.9f, -0.5f, -0.5f, 3, 1, 1, scale);
        this.setRotateAngle(this.ra_shoulder4, 0.0f, 0.0f, 0.50265485f);
        this.ra_plate0 = new ModelRenderer((ModelBase)this, 42, 32);
        this.ra_plate0.func_78793_a(-2.0f, 5.5f, 0.0f);
        this.ra_plate0.func_78790_a(-1.5f, 0.0f, -2.5f, 2, 4, 5, scale);
        this.ra_plate1 = new ModelRenderer((ModelBase)this, 56, 32);
        this.ra_plate1.func_78793_a(-2.5f, 3.7f, 0.0f);
        this.ra_plate1.func_78790_a(-0.5f, 0.0f, -2.0f, 1, 2, 4, scale);
        this.setRotateAngle(this.ra_plate1, 0.0f, 0.0f, 0.17453292f);
        this.ll_chainmail = new ModelRenderer((ModelBase)this, 26, 32);
        this.ll_chainmail.field_78809_i = true;
        this.ll_chainmail.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ll_chainmail.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, scale);
        this.ll_plate_f0 = new ModelRenderer((ModelBase)this, 0, 32);
        this.ll_plate_f0.field_78809_i = true;
        this.ll_plate_f0.func_78793_a(0.4f, 0.2f, -1.7f);
        this.ll_plate_f0.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.ll_plate_f0, -0.17453292f, 0.0f, 0.0f);
        this.ll_plate_f1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.ll_plate_f1.field_78809_i = true;
        this.ll_plate_f1.func_78793_a(0.0f, 3.0f, -1.7f);
        this.ll_plate_f1.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.ll_plate_f1, -0.17453292f, 0.0f, 0.0f);
        this.ll_plate_f2 = new ModelRenderer((ModelBase)this, 10, 32);
        this.ll_plate_f2.field_78809_i = true;
        this.ll_plate_f2.func_78793_a(0.0f, 0.0f, -1.7f);
        this.ll_plate_f2.func_78790_a(-2.0f, 0.0f, -0.7f, 2, 5, 1, scale);
        this.setRotateAngle(this.ll_plate_f2, -0.17453292f, 0.0f, 0.0f);
        this.ll_plate_f3 = new ModelRenderer((ModelBase)this, 10, 32);
        this.ll_plate_f3.field_78809_i = true;
        this.ll_plate_f3.func_78793_a(0.0f, 3.0f, -1.7f);
        this.ll_plate_f3.func_78790_a(-2.0f, 0.0f, -0.7f, 2, 5, 1, scale);
        this.setRotateAngle(this.ll_plate_f3, -0.17453292f, 0.0f, 0.0f);
        this.ll_plate_o0 = new ModelRenderer((ModelBase)this, 16, 32);
        this.ll_plate_o0.field_78809_i = true;
        this.ll_plate_o0.func_78793_a(1.8f, 0.2f, 0.0f);
        this.ll_plate_o0.func_78790_a(-0.5f, 0.0f, -2.0f, 1, 4, 4, scale);
        this.setRotateAngle(this.ll_plate_o0, 0.0f, 0.0f, -0.31415927f);
        this.ll_plate_o1 = new ModelRenderer((ModelBase)this, 16, 32);
        this.ll_plate_o1.field_78809_i = true;
        this.ll_plate_o1.func_78793_a(1.9f, 3.0f, 0.0f);
        this.ll_plate_o1.func_78790_a(-0.5f, 0.0f, -2.0f, 1, 4, 4, scale);
        this.setRotateAngle(this.ll_plate_o1, 0.0f, 0.0f, -0.2443461f);
        this.ll_plate_b0 = new ModelRenderer((ModelBase)this, 0, 32);
        this.ll_plate_b0.field_78809_i = true;
        this.ll_plate_b0.func_78793_a(0.0f, 0.2f, 2.0f);
        this.ll_plate_b0.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.ll_plate_b0, 0.17453292f, 0.0f, 0.0f);
        this.ll_plate_b1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.ll_plate_b1.field_78809_i = true;
        this.ll_plate_b1.func_78793_a(0.0f, 3.0f, 2.0f);
        this.ll_plate_b1.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.ll_plate_b1, 0.17453292f, 0.0f, 0.0f);
        this.rl_chainmail = new ModelRenderer((ModelBase)this, 26, 32);
        this.rl_chainmail.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rl_chainmail.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, scale);
        this.rl_plate_f0 = new ModelRenderer((ModelBase)this, 0, 32);
        this.rl_plate_f0.func_78793_a(-0.4f, 0.2f, -1.7f);
        this.rl_plate_f0.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.rl_plate_f0, -0.17453292f, 0.0f, 0.0f);
        this.rl_plate_f1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.rl_plate_f1.func_78793_a(0.0f, 3.0f, -1.7f);
        this.rl_plate_f1.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.rl_plate_f1, -0.17453292f, 0.0f, 0.0f);
        this.rl_plate_f2 = new ModelRenderer((ModelBase)this, 10, 32);
        this.rl_plate_f2.func_78793_a(0.0f, 0.0f, -1.7f);
        this.rl_plate_f2.func_78790_a(0.0f, 0.0f, -0.7f, 2, 5, 1, scale);
        this.setRotateAngle(this.rl_plate_f2, -0.17453292f, 0.0f, 0.0f);
        this.rl_plate_f3 = new ModelRenderer((ModelBase)this, 10, 32);
        this.rl_plate_f3.func_78793_a(0.0f, 3.0f, -1.7f);
        this.rl_plate_f3.func_78790_a(0.0f, 0.0f, -0.7f, 2, 5, 1, scale);
        this.setRotateAngle(this.rl_plate_f3, -0.17453292f, 0.0f, 0.0f);
        this.rl_plate_o0 = new ModelRenderer((ModelBase)this, 16, 32);
        this.rl_plate_o0.func_78793_a(-1.8f, 0.2f, 0.0f);
        this.rl_plate_o0.func_78790_a(-0.5f, 0.0f, -2.0f, 1, 4, 4, scale);
        this.setRotateAngle(this.rl_plate_o0, 0.0f, 0.0f, 0.31415927f);
        this.rl_plate_o1 = new ModelRenderer((ModelBase)this, 16, 32);
        this.rl_plate_o1.func_78793_a(-1.9f, 3.0f, 0.0f);
        this.rl_plate_o1.func_78790_a(-0.5f, 0.0f, -2.0f, 1, 4, 4, scale);
        this.setRotateAngle(this.rl_plate_o1, 0.0f, 0.0f, 0.2443461f);
        this.rl_plate_b0 = new ModelRenderer((ModelBase)this, 0, 32);
        this.rl_plate_b0.func_78793_a(0.0f, 0.2f, 2.0f);
        this.rl_plate_b0.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.rl_plate_b0, 0.17453292f, 0.0f, 0.0f);
        this.rl_plate_b1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.rl_plate_b1.func_78793_a(0.0f, 3.0f, 2.0f);
        this.rl_plate_b1.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 4, 1, scale);
        this.setRotateAngle(this.rl_plate_b1, 0.17453292f, 0.0f, 0.0f);
        this.lb_base = new ModelRenderer((ModelBase)this, 0, 40);
        this.lb_base.field_78809_i = true;
        this.lb_base.func_78793_a(0.0f, 8.0f, 0.0f);
        this.lb_base.func_78790_a(-2.0f, -0.5f, -2.0f, 4, 5, 4, scale);
        this.lb_front = new ModelRenderer((ModelBase)this, 0, 50);
        this.lb_front.field_78809_i = true;
        this.lb_front.func_78793_a(0.0f, 3.0f, -2.8f);
        this.lb_front.func_78790_a(-2.0f, 0.5f, 0.0f, 4, 1, 1, scale);
        this.lb_side0 = new ModelRenderer((ModelBase)this, 10, 50);
        this.lb_side0.field_78809_i = true;
        this.lb_side0.func_78793_a(2.0f, 3.0f, -2.1f);
        this.lb_side0.func_78790_a(-0.5f, 0.5f, 0.0f, 1, 1, 4, scale);
        this.lb_side1 = new ModelRenderer((ModelBase)this, 20, 50);
        this.lb_side1.field_78809_i = true;
        this.lb_side1.func_78793_a(2.0f, 2.2f, -1.1f);
        this.lb_side1.func_78790_a(-0.5f, 0.5f, 0.0f, 1, 1, 3, scale);
        this.rb_base = new ModelRenderer((ModelBase)this, 0, 40);
        this.rb_base.func_78793_a(0.0f, 8.0f, 0.0f);
        this.rb_base.func_78790_a(-2.0f, -0.5f, -2.0f, 4, 5, 4, scale);
        this.rb_front = new ModelRenderer((ModelBase)this, 0, 50);
        this.rb_front.func_78793_a(0.0f, 3.0f, -2.8f);
        this.rb_front.func_78790_a(-2.0f, 0.5f, 0.0f, 4, 1, 1, scale);
        this.rb_side0 = new ModelRenderer((ModelBase)this, 10, 50);
        this.rb_side0.func_78793_a(-2.0f, 3.0f, -2.1f);
        this.rb_side0.func_78790_a(-0.5f, 0.5f, 0.0f, 1, 1, 4, scale);
        this.rb_side1 = new ModelRenderer((ModelBase)this, 20, 50);
        this.rb_side1.func_78793_a(-2.0f, 2.2f, -1.1f);
        this.rb_side1.func_78790_a(-0.5f, 0.5f, 0.0f, 1, 1, 3, scale);
        this.hm_top = new ModelRenderer((ModelBase)this, 32, 0);
        this.hm_top.func_78793_a(-0.5f, -1.0f, -0.5f);
        this.hm_top.func_78790_a(-4.0f, -8.0f, -4.0f, 9, 4, 9, 0.0f);
        this.hm_l0 = new ModelRenderer((ModelBase)this, 68, 0);
        this.hm_l0.field_78809_i = true;
        this.hm_l0.func_78793_a(4.1f, -6.3f, -1.2f);
        this.hm_l0.func_78790_a(-0.5f, 0.0f, -4.0f, 1, 4, 10, 0.0f);
        this.setRotateAngle(this.hm_l0, 0.0f, 0.0f, -0.3926991f);
        this.hm_r0 = new ModelRenderer((ModelBase)this, 68, 0);
        this.hm_r0.func_78793_a(-4.1f, -6.3f, -1.2f);
        this.hm_r0.func_78790_a(-0.5f, 0.0f, -4.0f, 1, 4, 10, 0.0f);
        this.setRotateAngle(this.hm_r0, 0.0f, 0.0f, 0.3926991f);
        this.hm_b0 = new ModelRenderer((ModelBase)this, 68, 14);
        this.hm_b0.func_78793_a(0.0f, -4.0f, 3.7f);
        this.hm_b0.func_78790_a(-4.5f, 0.0f, -0.5f, 9, 4, 1, 0.0f);
        this.setRotateAngle(this.hm_b0, 0.3926991f, 0.0f, 0.0f);
        this.hm_b1 = new ModelRenderer((ModelBase)this, 68, 14);
        this.hm_b1.func_78793_a(0.0f, -6.3f, 4.1f);
        this.hm_b1.func_78790_a(-4.5f, 0.0f, -0.5f, 9, 4, 1, 0.0f);
        this.setRotateAngle(this.hm_b1, 0.3926991f, 0.0f, 0.0f);
        this.hm_f0 = new ModelRenderer((ModelBase)this, 68, 19);
        this.hm_f0.func_78793_a(-0.5f, -6.5f, -4.7f);
        this.hm_f0.func_78790_a(-4.0f, -0.0f, -0.5f, 9, 1, 1, 0.0f);
        this.face_0 = new ModelRenderer((ModelBase)this, 102, 0);
        this.face_0.func_78793_a(0.0f, -2.8f, -4.1f);
        this.face_0.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.face_0, 0.08726646f, 0.0f, 0.0f);
        this.face_l1 = new ModelRenderer((ModelBase)this, 90, 0);
        this.face_l1.field_78809_i = true;
        this.face_l1.func_78793_a(3.4f, 0.0f, 0.2f);
        this.face_l1.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 3, 4, 0.0f);
        this.setRotateAngle(this.face_l1, -0.08726646f, 0.0f, 0.0f);
        this.face_l2 = new ModelRenderer((ModelBase)this, 102, 4);
        this.face_l2.field_78809_i = true;
        this.face_l2.func_78793_a(1.7f, 0.9f, 0.2f);
        this.face_l2.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 2, 1, 0.0f);
        this.setRotateAngle(this.face_l2, -0.08726646f, 0.0f, 0.0f);
        this.face_r1 = new ModelRenderer((ModelBase)this, 90, 0);
        this.face_r1.func_78793_a(-3.4f, 0.0f, 0.2f);
        this.face_r1.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 3, 4, 0.0f);
        this.setRotateAngle(this.face_r1, -0.08726646f, 0.0f, 0.0f);
        this.face_r2 = new ModelRenderer((ModelBase)this, 102, 4);
        this.face_r2.func_78793_a(-1.7f, 0.9f, 0.2f);
        this.face_r2.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 2, 1, 0.0f);
        this.face_mask = new ModelRenderer((ModelBase)this, 90, 8 + mask * 7);
        this.face_mask.func_78793_a(0.0f, -5.0f, -3.6f);
        this.face_mask.func_78790_a(-4.0f, 0.0f, -0.5f, 8, 4, 3, 0.0f);
        this.face_goggles = new ModelRenderer((ModelBase)this, 112, 8);
        this.face_goggles.func_78793_a(0.0f, -5.0f, -3.5f);
        this.face_goggles.func_78790_a(-4.0f, 0.0f, -0.5f, 8, 4, 3, 0.0f);
        this.field_78115_e.func_78792_a(this.bd_chainmail);
        this.bd_chainmail.func_78792_a(this.bd_chest);
        this.bd_chainmail.func_78792_a(this.bd_add0);
        this.bd_chainmail.func_78792_a(this.bd_add1);
        this.bd_chainmail.func_78792_a(this.bd_add2);
        this.bd_chainmail.func_78792_a(this.bd_add3);
        this.bd_chainmail.func_78792_a(this.bd_strap0);
        this.bd_chainmail.func_78792_a(this.bd_strap1);
        this.bd_chainmail.func_78792_a(this.bd_strap2);
        this.bd_chainmail.func_78792_a(this.bd_back);
        this.bd_chainmail.func_78792_a(this.bd_add4);
        this.bd_chainmail.func_78792_a(this.bd_add5);
        this.bd_chainmail.func_78792_a(this.bd_add6);
        this.bd_chainmail.func_78792_a(this.bd_add7);
        this.bd_chainmail.func_78792_a(this.bd_strap3);
        this.bd_chainmail.func_78792_a(this.bd_strap4);
        this.bd_chainmail.func_78792_a(this.bd_strap5);
        this.field_78115_e.func_78792_a(this.bd_belt);
        this.field_78113_g.func_78792_a(this.la_shoulder0);
        this.la_shoulder0.func_78792_a(this.la_shoulder1);
        this.la_shoulder0.func_78792_a(this.la_shoulder2);
        this.la_shoulder0.func_78792_a(this.la_shoulder3);
        this.la_shoulder0.func_78792_a(this.la_shoulder4);
        this.field_78113_g.func_78792_a(this.la_plate0);
        this.field_78113_g.func_78792_a(this.la_plate1);
        this.field_78112_f.func_78792_a(this.ra_shoulder0);
        this.ra_shoulder0.func_78792_a(this.ra_shoulder1);
        this.ra_shoulder0.func_78792_a(this.ra_shoulder2);
        this.ra_shoulder0.func_78792_a(this.ra_shoulder3);
        this.ra_shoulder0.func_78792_a(this.ra_shoulder4);
        this.field_78112_f.func_78792_a(this.ra_plate0);
        this.field_78112_f.func_78792_a(this.ra_plate1);
        this.field_78124_i.func_78792_a(this.ll_chainmail);
        this.ll_chainmail.func_78792_a(this.ll_plate_f0);
        this.ll_chainmail.func_78792_a(this.ll_plate_f1);
        this.ll_chainmail.func_78792_a(this.ll_plate_f2);
        this.ll_chainmail.func_78792_a(this.ll_plate_f3);
        this.ll_chainmail.func_78792_a(this.ll_plate_o0);
        this.ll_chainmail.func_78792_a(this.ll_plate_o1);
        this.ll_chainmail.func_78792_a(this.ll_plate_b0);
        this.ll_chainmail.func_78792_a(this.ll_plate_b1);
        this.field_78123_h.func_78792_a(this.rl_chainmail);
        this.rl_chainmail.func_78792_a(this.rl_plate_f0);
        this.rl_chainmail.func_78792_a(this.rl_plate_f1);
        this.rl_chainmail.func_78792_a(this.rl_plate_f2);
        this.rl_chainmail.func_78792_a(this.rl_plate_f3);
        this.rl_chainmail.func_78792_a(this.rl_plate_o0);
        this.rl_chainmail.func_78792_a(this.rl_plate_o1);
        this.rl_chainmail.func_78792_a(this.rl_plate_b0);
        this.rl_chainmail.func_78792_a(this.rl_plate_b1);
        this.field_78124_i.func_78792_a(this.lb_base);
        this.lb_base.func_78792_a(this.lb_front);
        this.lb_base.func_78792_a(this.lb_side0);
        this.lb_base.func_78792_a(this.lb_side1);
        this.field_78123_h.func_78792_a(this.rb_base);
        this.rb_base.func_78792_a(this.rb_front);
        this.rb_base.func_78792_a(this.rb_side0);
        this.rb_base.func_78792_a(this.rb_side1);
        this.field_78116_c.func_78792_a(this.hm_top);
        this.field_78116_c.func_78792_a(this.hm_l0);
        this.field_78116_c.func_78792_a(this.hm_r0);
        this.field_78116_c.func_78792_a(this.hm_b0);
        this.field_78116_c.func_78792_a(this.hm_b1);
        this.field_78116_c.func_78792_a(this.hm_f0);
        this.field_78116_c.func_78792_a(this.face_0);
        this.face_0.func_78792_a(this.face_l1);
        this.face_0.func_78792_a(this.face_l2);
        this.face_0.func_78792_a(this.face_r1);
        this.face_0.func_78792_a(this.face_r2);
        if (mask >= 0) {
            this.field_78116_c.func_78792_a(this.face_mask);
        }
        if (goggles) {
            this.field_78116_c.func_78792_a(this.face_goggles);
        }
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float f6;
        this.field_78117_n = entity.func_70093_af();
        this.field_78093_q = entity.func_70115_ae();
        if (entity instanceof EntityLivingBase) {
            this.field_78091_s = ((EntityLivingBase)entity).func_70631_g_();
            int n = this.field_78120_m = ((EntityLivingBase)entity).func_70694_bm() != null ? 1 : 0;
            if (entity instanceof EntityPlayer && ((EntityPlayer)entity).func_71011_bu() != null) {
                this.field_78118_o = ((EntityPlayer)entity).func_71011_bu().func_77975_n() == EnumAction.bow && ((EntityPlayer)entity).func_71057_bx() > 0;
            }
        }
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        if (entity instanceof EntitySkeleton || entity instanceof EntityZombie) {
            f6 = MathHelper.func_76126_a((float)(this.field_78095_p * 3.141593f));
            float f7 = MathHelper.func_76126_a((float)((1.0f - (1.0f - this.field_78095_p) * (1.0f - this.field_78095_p)) * 3.141593f));
            this.field_78112_f.field_78808_h = 0.0f;
            this.field_78113_g.field_78808_h = 0.0f;
            this.field_78112_f.field_78796_g = -(0.1f - f6 * 0.6f);
            this.field_78113_g.field_78796_g = 0.1f - f6 * 0.6f;
            this.field_78112_f.field_78795_f = -1.570796f;
            this.field_78113_g.field_78795_f = -1.570796f;
            this.field_78112_f.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.field_78113_g.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
            this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
        }
        if (this.field_78091_s) {
            f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * f5), (float)0.0f);
            this.field_78116_c.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.field_78115_e.func_78785_a(f5);
            this.field_78112_f.func_78785_a(f5);
            this.field_78113_g.func_78785_a(f5);
            this.field_78123_h.func_78785_a(f5);
            this.field_78124_i.func_78785_a(f5);
            GL11.glPopMatrix();
        } else {
            this.field_78116_c.func_78785_a(f5);
            this.field_78115_e.func_78785_a(f5);
            this.field_78112_f.func_78785_a(f5);
            this.field_78113_g.func_78785_a(f5);
            this.field_78123_h.func_78785_a(f5);
            this.field_78124_i.func_78785_a(f5);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public static ModelBiped getModel(EntityLivingBase entity, ItemStack stack) {
        boolean goggles;
        int mask;
        if (stack == null || !(stack.func_77973_b() instanceof ItemArmor)) {
            return null;
        }
        int slot = ((ItemArmor)stack.func_77973_b()).field_77881_a;
        ModelPrimordialArmor armor = null;
        if (slot == 0) {
            mask = stack.func_77942_o() && stack.func_77978_p().func_74764_b("mask") ? stack.func_77978_p().func_74762_e("mask") : -1;
            if (modelHelm[mask + 1][(goggles = stack.func_77942_o() && stack.func_77978_p().func_74764_b("goggles")) ? 1 : 0] != null) {
                return modelHelm[mask + 1][goggles ? 1 : 0];
            }
        } else {
            if (slot == 1 && modelChest != null) {
                return modelChest;
            }
            if (slot == 2 && modelLegs != null) {
                return modelLegs;
            }
            if (slot == 3 && modelBoots != null) {
                return modelBoots;
            }
        }
        armor = new ModelPrimordialArmor(stack);
        switch (slot) {
            case 0: {
                mask = stack.func_77942_o() && stack.func_77978_p().func_74764_b("mask") ? stack.func_77978_p().func_74762_e("mask") : -1;
                goggles = stack.func_77942_o() && stack.func_77978_p().func_74764_b("goggles");
                armor.field_78115_e.field_78807_k = true;
                armor.field_78113_g.field_78807_k = true;
                armor.field_78112_f.field_78807_k = true;
                armor.field_78124_i.field_78807_k = true;
                armor.field_78123_h.field_78807_k = true;
                ModelPrimordialArmor.modelHelm[mask + 1][goggles ? 1 : 0] = armor;
                break;
            }
            case 1: {
                armor.field_78116_c.field_78807_k = true;
                armor.field_78124_i.field_78807_k = true;
                armor.field_78123_h.field_78807_k = true;
                armor.bd_belt.field_78807_k = true;
                modelChest = armor;
                break;
            }
            case 2: {
                armor.bd_chainmail.field_78807_k = true;
                armor.field_78116_c.field_78807_k = true;
                armor.field_78113_g.field_78807_k = true;
                armor.field_78112_f.field_78807_k = true;
                armor.lb_base.field_78807_k = true;
                armor.rb_base.field_78807_k = true;
                modelLegs = armor;
                break;
            }
            case 3: {
                armor.field_78116_c.field_78807_k = true;
                armor.field_78115_e.field_78807_k = true;
                armor.field_78113_g.field_78807_k = true;
                armor.field_78112_f.field_78807_k = true;
                armor.rl_chainmail.field_78807_k = true;
                armor.ll_chainmail.field_78807_k = true;
                modelBoots = armor;
            }
        }
        return armor;
    }

    static {
        modelMap = new HashMap();
    }
}

