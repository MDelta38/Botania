/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;

public class EntitySignalFlare
extends Entity {
    private static final String COLOR_TAG = "color";

    public EntitySignalFlare(World par1World) {
        super(par1World);
        this.func_70105_a(0.0f, 0.0f);
        this.field_70180_af.func_75682_a(30, (Object)0);
        this.field_70180_af.func_82708_h(30);
    }

    protected void func_70088_a() {
    }

    public void func_70030_z() {
        super.func_70030_z();
        if (this.field_70173_aa++ >= 100) {
            this.func_70106_y();
        }
        if (!this.field_70128_L) {
            int color;
            if (this.field_70173_aa % 10 == 0) {
                this.func_85030_a("creeper.primed", 1.0f, 1.0f);
            }
            if ((color = this.getColor()) < 16 && color >= 0) {
                int i;
                float[] colorArray = EntitySheep.field_70898_d[color];
                Botania.proxy.setWispFXDistanceLimit(false);
                for (i = 0; i < 3; ++i) {
                    Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v + 0.5, colorArray[0], colorArray[1], colorArray[2], (float)Math.random() * 5.0f + 1.0f, (float)(Math.random() - 0.5), 10.0f * (float)Math.sqrt(256.0f / (256.0f - (float)this.field_70163_u)), (float)(Math.random() - 0.5));
                }
                for (i = 0; i < 4; ++i) {
                    Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t + 0.5, 256.0, this.field_70161_v + 0.5, colorArray[0], colorArray[1], colorArray[2], (float)Math.random() * 15.0f + 8.0f, (float)(Math.random() - 0.5) * 8.0f, 0.0f, (float)(Math.random() - 0.5) * 8.0f);
                }
                Botania.proxy.setWispFXDistanceLimit(true);
            }
        }
    }

    protected void func_70037_a(NBTTagCompound nbttagcompound) {
        this.setColor(nbttagcompound.func_74762_e(COLOR_TAG));
    }

    protected void func_70014_b(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a(COLOR_TAG, this.getColor());
    }

    public void setColor(int color) {
        this.field_70180_af.func_75692_b(30, (Object)color);
    }

    public int getColor() {
        return this.field_70180_af.func_75679_c(30);
    }
}

