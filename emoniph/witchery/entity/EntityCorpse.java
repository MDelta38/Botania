/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.util.Config;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityCorpse
extends EntityLiving {
    private ThreadDownloadImageData downloadImageSkin;
    private ResourceLocation locationSkin;

    public EntityCorpse(World world) {
        super(world);
        this.func_70105_a(1.2f, 0.5f);
    }

    public boolean func_70104_M() {
        return false;
    }

    public boolean func_70067_L() {
        return super.func_70067_L();
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
    }

    public void func_70091_d(double par1, double par3, double par5) {
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, (Object)"");
    }

    protected boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (!this.field_70170_p.field_72995_K) {
            if (par1DamageSource.func_76364_f() != null && par1DamageSource.func_76364_f() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.func_76364_f()).field_71075_bZ.field_75098_d) {
                return super.func_70097_a(par1DamageSource, par2);
            }
            String username = this.getOwnerName();
            for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
                EntityPlayer player = world.func_72924_a(username);
                if (player == null) continue;
                return super.func_70097_a(par1DamageSource, par2);
            }
            return false;
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.body.name");
    }

    public boolean func_70650_aV() {
        return true;
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        if (this.getOwnerName() == null) {
            nbtRoot.func_74778_a("Owner", "");
        } else {
            nbtRoot.func_74778_a("Owner", this.getOwnerName());
        }
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        String s = nbtRoot.func_74779_i("Owner");
        if (s.length() > 0) {
            this.setOwner(s);
        }
    }

    public String getOwnerName() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setOwner(String username) {
        this.func_110163_bv();
        this.field_70180_af.func_75692_b(17, (Object)username);
    }

    protected void setupCustomSkin() {
        String username = this.getOwnerName();
        this.locationSkin = AbstractClientPlayer.func_110311_f((String)username);
        this.downloadImageSkin = AbstractClientPlayer.func_110304_a((ResourceLocation)this.locationSkin, (String)username);
    }

    public EntityPlayer getOwnerEntity() {
        return this.field_70170_p.func_72924_a(this.getOwnerName());
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (!this.field_70170_p.field_72995_K) {
            String username = this.getOwnerName();
            for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
                EntityPlayer player = world.func_72924_a(username);
                if (player == null) continue;
                if (player.field_71093_bK == Config.instance().dimensionDreamID) {
                    WorldProviderDreamWorld.returnPlayerToOverworld(player);
                    break;
                }
                if (!WorldProviderDreamWorld.getPlayerIsGhost(player)) break;
                WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
                WorldProviderDreamWorld.returnPlayerToOverworld(player);
                break;
            }
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public ResourceLocation getLocationSkin() {
        if (this.locationSkin == null) {
            this.setupCustomSkin();
        }
        if (this.locationSkin != null) {
            return this.locationSkin;
        }
        return AbstractClientPlayer.field_110314_b;
    }
}

