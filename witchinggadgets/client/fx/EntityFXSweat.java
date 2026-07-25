/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 */
package witchinggadgets.client.fx;

import java.util.Random;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class EntityFXSweat
extends EntityFX {
    public EntityFXSweat(EntityPlayer player) {
        super(player.field_70170_p, player.field_70165_t - (double)0.2f + (double)(player.func_70681_au().nextFloat() / 2.0f), player.field_70121_D.field_72338_b + 0.5 + (double)player.func_70681_au().nextFloat(), player.field_70161_v - (double)0.2f + (double)(player.func_70681_au().nextFloat() / 2.0f));
        this.field_70551_j = MathHelper.func_151240_a((Random)player.func_70681_au(), (float)0.3f, (float)0.8f);
        this.field_70553_i = 0.2f;
        this.field_70552_h = 0.2f;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70545_g = 0.06f;
        this.field_70547_e = 20;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
    }
}

