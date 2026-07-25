/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.entities.monster.boss;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.tiles.TileBanner;

public class EntityCultistPortal
extends EntityMob
implements IBossDisplayData {
    int stage = 0;
    int stagecounter = 200;
    public int pulse = 0;

    public EntityCultistPortal(World par1World) {
        super(par1World);
        this.field_70178_ae = true;
        this.field_70728_aV = 30;
        this.func_70105_a(1.5f, 3.0f);
    }

    public int func_70658_aO() {
        return 5;
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        nbt.func_74768_a("stage", this.stage);
    }

    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        this.stage = nbt.func_74762_e("stage");
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
    }

    public float func_70053_R() {
        return 0.0f;
    }

    public boolean func_70067_L() {
        return true;
    }

    public boolean func_70104_M() {
        return false;
    }

    public void func_70091_d(double par1, double par3, double par5) {
    }

    protected void func_70626_be() {
    }

    public boolean func_70112_a(double par1) {
        return par1 < 4096.0;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float par1) {
        return 0xF000F0;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.stagecounter > 0) {
                int a;
                --this.stagecounter;
                if (this.stagecounter == 160 && this.stage == 0) {
                    this.field_70170_p.func_72960_a((Entity)this, (byte)16);
                    for (a = 2; a < 6; ++a) {
                        ForgeDirection dir = ForgeDirection.getOrientation((int)a);
                        this.field_70170_p.func_147465_d((int)this.field_70165_t - dir.offsetX * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.offsetZ * 6, ConfigBlocks.blockWoodenDevice, 8, 3);
                        TileEntity te = this.field_70170_p.func_147438_o((int)this.field_70165_t - dir.offsetX * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.offsetZ * 6);
                        if (te == null || !(te instanceof TileBanner)) continue;
                        int face = 0;
                        switch (a) {
                            case 2: {
                                face = 8;
                                break;
                            }
                            case 3: {
                                face = 0;
                                break;
                            }
                            case 4: {
                                face = 12;
                                break;
                            }
                            case 5: {
                                face = 4;
                                break;
                            }
                        }
                        ((TileBanner)te).setFacing((byte)face);
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockArc((int)this.field_70165_t - dir.offsetX * 6, (int)this.field_70163_u, (int)this.field_70161_v + dir.offsetZ * 6, this.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0));
                        this.func_85030_a("thaumcraft:wandfail", 1.0f, 1.0f);
                    }
                }
                if (this.stagecounter > 20 && this.stagecounter < 150 && this.stage == 0 && this.stagecounter % 13 == 0) {
                    a = (int)this.field_70165_t + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5);
                    int b = (int)this.field_70161_v + this.field_70146_Z.nextInt(5) - this.field_70146_Z.nextInt(5);
                    if (a != (int)this.field_70165_t && b != (int)this.field_70161_v && this.field_70170_p.func_147437_c(a, (int)this.field_70163_u, b)) {
                        this.field_70170_p.func_72960_a((Entity)this, (byte)16);
                        float rr = this.field_70170_p.field_73012_v.nextFloat();
                        int md = rr < 0.05f ? 2 : (rr < 0.2f ? 1 : 0);
                        this.field_70170_p.func_147465_d(a, (int)this.field_70163_u, b, ConfigBlocks.blockLootCrate, md, 3);
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockArc(a, (int)this.field_70163_u, b, this.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0));
                        this.func_85030_a("thaumcraft:wandfail", 1.0f, 1.0f);
                    }
                }
            } else if (this.field_70170_p.func_72890_a((Entity)this, 48.0) != null) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)16);
                switch (this.stage) {
                    case 0: 
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: {
                        this.stagecounter = 15 + this.field_70146_Z.nextInt(10 - this.stage) - this.stage;
                        this.spawnMinions();
                        break;
                    }
                    case 12: {
                        this.stagecounter = 50 + this.getTiming() * 2 + this.field_70146_Z.nextInt(50);
                        this.spawnBoss();
                        break;
                    }
                    default: {
                        int t = this.getTiming();
                        this.stagecounter = t + this.field_70146_Z.nextInt(5 + t / 3);
                        this.spawnMinions();
                    }
                }
                ++this.stage;
            } else {
                this.stagecounter = 30 + this.field_70146_Z.nextInt(30);
            }
            if (this.stage < 12) {
                this.func_70691_i(1.0f);
            }
        }
        if (this.pulse > 0) {
            --this.pulse;
        }
    }

    int getTiming() {
        ArrayList<Entity> l = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, (Entity)this, EntityCultist.class, 32.0);
        return l.size() * 20;
    }

    void spawnMinions() {
        EntityCultist cultist = null;
        cultist = (double)this.field_70146_Z.nextFloat() > 0.33 ? new EntityCultistKnight(this.field_70170_p) : new EntityCultistCleric(this.field_70170_p);
        cultist.func_70107_b(this.field_70165_t + (double)this.field_70146_Z.nextFloat() - (double)this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25, this.field_70161_v + (double)this.field_70146_Z.nextFloat() - (double)this.field_70146_Z.nextFloat());
        cultist.func_110161_a(null);
        cultist.func_70656_aK();
        cultist.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 32);
        this.field_70170_p.func_72838_d((Entity)cultist);
        cultist.func_85030_a("thaumcraft:wandfail", 1.0f, 1.0f);
        if (this.stage > 12) {
            this.func_70097_a(DamageSource.field_76380_i, 5 + this.field_70146_Z.nextInt(5));
        }
    }

    void spawnBoss() {
        EntityCultistLeader cultist = new EntityCultistLeader(this.field_70170_p);
        cultist.func_70107_b(this.field_70165_t + (double)this.field_70146_Z.nextFloat() - (double)this.field_70146_Z.nextFloat(), this.field_70163_u + 0.25, this.field_70161_v + (double)this.field_70146_Z.nextFloat() - (double)this.field_70146_Z.nextFloat());
        cultist.func_110161_a(null);
        cultist.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 32);
        cultist.func_70656_aK();
        this.field_70170_p.func_72838_d((Entity)cultist);
        cultist.func_85030_a("thaumcraft:wandfail", 1.0f, 1.0f);
    }

    public void func_70100_b_(EntityPlayer p) {
        if (this.func_70068_e((Entity)p) < 3.0 && p.func_70097_a(DamageSource.func_76354_b((Entity)this, (Entity)this), 8.0f)) {
            this.func_85030_a("thaumcraft:zap", 1.0f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1f + 1.0f);
        }
    }

    protected float func_70599_aP() {
        return 0.75f;
    }

    public int func_70627_aG() {
        return 540;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:monolith";
    }

    protected String func_70621_aR() {
        return "thaumcraft:zap";
    }

    protected String func_70673_aS() {
        return "thaumcraft:shock";
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean flag, int fortune) {
        EntityUtils.entityDropSpecialItem((Entity)this, new ItemStack(ConfigItems.itemEldritchObject, 1, 3), this.field_70131_O / 2.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte msg) {
        if (msg == 16) {
            this.pulse = 10;
            this.func_70656_aK();
        } else {
            super.func_70103_a(msg);
        }
    }

    public void func_70690_d(PotionEffect p_70690_1_) {
    }

    protected void func_70069_a(float p_70069_1_) {
    }

    public void func_70645_a(DamageSource p_70645_1_) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 2.0f, false, false);
        }
        super.func_70645_a(p_70645_1_);
    }
}

