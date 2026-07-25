/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package flaxbeard.thaumicexploration.misc;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class FakePlayerPotion
extends EntityPlayer {
    public FakePlayerPotion(World world, GameProfile name) {
        super(world, name);
    }

    protected void func_70670_a(PotionEffect par1PotionEffect) {
        if (!this.field_70170_p.field_72995_K) {
            Potion.field_76425_a[par1PotionEffect.func_76456_a()].func_111185_a((EntityLivingBase)this, this.func_110140_aT(), par1PotionEffect.func_76458_c());
        }
    }

    public void func_145747_a(IChatComponent var1) {
    }

    public boolean func_70003_b(int var1, String var2) {
        return false;
    }

    public ChunkCoordinates func_82114_b() {
        return null;
    }
}

