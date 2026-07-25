/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMindSpider
extends EntitySpider {
    private int lifeSpan = Integer.MAX_VALUE;

    public EntityMindSpider(World par1World) {
        super(par1World);
        this.func_70105_a(0.3f, 0.3f);
        this.field_70728_aV = 1;
    }

    protected int func_70693_a(EntityPlayer p_70693_1_) {
        return this.isHarmless() ? 0 : super.func_70693_a(p_70693_1_);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(22, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(23, (Object)new String(""));
    }

    public String getViewer() {
        return this.field_70180_af.func_75681_e(23);
    }

    public void setViewer(String player) {
        this.field_70180_af.func_75692_b(23, (Object)String.valueOf(player));
    }

    public boolean isHarmless() {
        return this.field_70180_af.func_75683_a(22) != 0;
    }

    public void setHarmless(boolean h) {
        if (h) {
            this.lifeSpan = 1200;
        }
        this.field_70180_af.func_75692_b(22, (Object)((byte)(h ? 1 : 0)));
    }

    protected float func_70647_i() {
        return 0.7f;
    }

    protected Entity func_70782_k() {
        double d0 = 12.0;
        return this.field_70170_p.func_72856_b((Entity)this, d0);
    }

    @SideOnly(value=Side.CLIENT)
    public float spiderScaleAmount() {
        return 0.3f;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa > this.lifeSpan) {
            this.func_70106_y();
        }
    }

    public float func_70053_R() {
        return this.isHarmless() ? 0.0f : 0.1f;
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
    }

    public boolean func_145773_az() {
        return true;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70785_a(Entity p_70785_1_, float p_70785_2_) {
        if (this.isHarmless()) {
            return;
        }
        super.func_70785_a(p_70785_1_, p_70785_2_);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.field_70180_af.func_75692_b(22, (Object)par1NBTTagCompound.func_74771_c("harmless"));
        this.field_70180_af.func_75692_b(23, (Object)String.valueOf(par1NBTTagCompound.func_74779_i("viewer")));
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("harmless", this.field_70180_af.func_75683_a(22));
        par1NBTTagCompound.func_74778_a("viewer", this.field_70180_af.func_75681_e(23));
    }
}

