/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelZombieVillager
 *  net.minecraft.client.renderer.entity.RenderZombie
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;

@SideOnly(value=Side.CLIENT)
public class RenderBrainyZombie
extends RenderZombie {
    private static final ResourceLocation field_110865_p = new ResourceLocation("thaumcraft", "textures/models/bzombie.png");
    private static final ResourceLocation field_110864_q = new ResourceLocation("thaumcraft", "textures/models/bzombievil.png");
    private ModelBiped field_82434_o;
    private ModelZombieVillager field_82432_p;
    private int field_82431_q = 1;

    protected ResourceLocation func_110775_a(EntityZombie par1EntityZombie) {
        return par1EntityZombie.func_82231_m() ? field_110864_q : field_110865_p;
    }

    protected void preRenderScale(EntityGiantBrainyZombie z, float par2) {
        GL11.glScalef((float)z.getAnger(), (float)z.getAnger(), (float)z.getAnger());
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        if (par1EntityLiving instanceof EntityGiantBrainyZombie) {
            this.preRenderScale((EntityGiantBrainyZombie)par1EntityLiving, par2);
        }
    }

    private void func_82427_a(EntityZombie par1EntityZombie) {
        if (par1EntityZombie.func_82231_m()) {
            if (this.field_82431_q != this.field_82432_p.func_82897_a()) {
                this.field_82432_p = new ModelZombieVillager();
                this.field_82431_q = this.field_82432_p.func_82897_a();
                this.field_82436_m = new ModelZombieVillager(1.0f, 0.0f, true);
                this.field_82433_n = new ModelZombieVillager(0.5f, 0.0f, true);
            }
            this.field_77045_g = this.field_82432_p;
            this.field_82423_g = this.field_82436_m;
            this.field_82425_h = this.field_82433_n;
        } else {
            this.field_77045_g = this.field_82434_o;
            this.field_82423_g = this.field_82437_k;
            this.field_82425_h = this.field_82435_l;
        }
        this.field_77071_a = (ModelBiped)this.field_77045_g;
    }
}

