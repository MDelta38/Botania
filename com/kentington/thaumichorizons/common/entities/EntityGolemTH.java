/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.ai.combat.AIAvoidCreeperSwell
 *  thaumcraft.common.entities.ai.combat.AIGolemAttackOnCollide
 *  thaumcraft.common.entities.ai.combat.AIHurtByTarget
 *  thaumcraft.common.entities.ai.combat.AINearestAttackableTarget
 *  thaumcraft.common.entities.ai.misc.AIOpenDoor
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.entities.golems.EnumGolemType
 *  thaumcraft.common.entities.monster.EntityEldritchGuardian
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.client.lib.GolemTHTexture;
import com.kentington.thaumichorizons.common.entities.EntityLightningBoltFinite;
import com.kentington.thaumichorizons.common.entities.EnumGolemTHType;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIFollowPlayer;
import com.kentington.thaumichorizons.common.lib.PacketFXBlocksplosion;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAvoidCreeperSwell;
import thaumcraft.common.entities.ai.combat.AIGolemAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AIHurtByTarget;
import thaumcraft.common.entities.ai.combat.AINearestAttackableTarget;
import thaumcraft.common.entities.ai.misc.AIOpenDoor;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EnumGolemType;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;

public class EntityGolemTH
extends EntityGolemBase {
    public ResourceLocation texture = null;
    public Block blocky;
    public int md;
    public int ticksAlive = 0;
    public int voidCount = 0;
    public boolean berserk;
    public boolean kaboom;
    public EnumGolemTHType type = EnumGolemTHType.SAND;

    public EntityGolemTH(World par1World) {
        super(par1World);
    }

    public void loadGolem(double x, double y, double z, Block block, int md, int ticksAlive, boolean adv, boolean berserk, boolean kaboom) {
        this.func_70107_b(x, y, z);
        this.md = md;
        this.blocky = block;
        this.ticksAlive = ticksAlive;
        this.advanced = adv;
        this.berserk = berserk;
        this.kaboom = kaboom;
        this.loadGolemTexturesAndStats();
        this.setupGolem();
        this.upgrades = new byte[this.type.upgrades + (this.advanced ? 1 : 0)];
        for (int a = 0; a < this.upgrades.length; ++a) {
            this.upgrades[a] = -1;
        }
    }

    public void loadGolemTexturesAndStats() {
        if (this.blocky == null) {
            this.type = EnumGolemTHType.VOID;
            return;
        }
        Material m = this.blocky.func_149688_o();
        this.type = m == Material.field_151577_b ? EnumGolemTHType.GRASS : (m == Material.field_151578_c ? EnumGolemTHType.DIRT : (m == Material.field_151575_d || m == Material.field_151572_C ? EnumGolemTHType.WOOD : (m == Material.field_151584_j || m == Material.field_151585_k || m == Material.field_151582_l ? EnumGolemTHType.PLANT : (m == Material.field_151576_e ? EnumGolemTHType.ROCK : (m == Material.field_151573_f || m == Material.field_151574_g ? EnumGolemTHType.METAL : (m == Material.field_151583_m || m == Material.field_151580_n || m == Material.field_151593_r ? EnumGolemTHType.CLOTH : (m == Material.field_151595_p ? EnumGolemTHType.SAND : (m == Material.field_151591_t || m == Material.field_151594_q ? EnumGolemTHType.REDSTONE : (m == Material.field_151590_u ? EnumGolemTHType.TNT : (m == Material.field_151588_w || m == Material.field_151598_x || m == Material.field_151597_y || m == Material.field_151596_z ? EnumGolemTHType.ICE : (m == Material.field_151570_A ? EnumGolemTHType.CACTUS : (m == Material.field_151568_F ? EnumGolemTHType.CAKE : (m == Material.field_151569_G ? EnumGolemTHType.WEB : EnumGolemTHType.ROCK)))))))))))));
        if (this.field_70170_p.field_72995_K) {
            this.loadTexture();
        }
    }

    public boolean isValidTarget(Entity target) {
        if (!this.berserk) {
            return super.isValidTarget(target) || target instanceof EntityCreeper;
        }
        if (!target.func_70089_S()) {
            return false;
        }
        if (target instanceof EntityPlayer && ((EntityPlayer)target).func_70005_c_().equals(this.getOwnerName())) {
            return false;
        }
        return !target.func_70005_c_().equals(this.func_70005_c_());
    }

    public boolean setupGolem() {
        super.setupGolem();
        if (this.getCore() == -1) {
            this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIAvoidCreeperSwell((EntityGolemBase)this));
            this.field_70715_bh.func_75776_a(1, (EntityAIBase)new AIHurtByTarget((EntityCreature)this, false));
            this.field_70715_bh.func_75776_a(2, (EntityAIBase)new AINearestAttackableTarget((EntityGolemBase)this, 0, true));
            this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIGolemAttackOnCollide((EntityGolemBase)this));
            this.field_70714_bg.func_75776_a(5, (EntityAIBase)new AIOpenDoor((EntityGolemBase)this, true));
            if (!this.kaboom) {
                this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIFollowPlayer(this, this.func_70689_ay() * 3.0f, 2.0f, 12.0f));
            } else {
                this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIFollowPlayer(this, this.func_70689_ay() * 3.0f, 8.0f, 12.0f));
            }
            this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
            this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
            this.paused = false;
            this.inactive = false;
            this.bootup = 0.0f;
        }
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(19, (Object)((byte)this.type.ordinal()));
        }
        if (this.getGolemTHType() == EnumGolemTHType.ROCK || this.getGolemTHType() == EnumGolemTHType.METAL || this.getGolemTHType() == EnumGolemTHType.REDSTONE) {
            this.func_70661_as().func_75491_a(false);
        } else {
            this.func_70661_as().func_75491_a(true);
        }
        int bonus = 0;
        try {
            bonus = this.getGolemDecoration().contains("H") ? 5 : 0;
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(this.getGolemTHType().health + bonus));
        return true;
    }

    public boolean func_70097_a(DamageSource ds, float par2) {
        this.paused = false;
        if (ds == DamageSource.field_76367_g) {
            return false;
        }
        if (this.blocky == ConfigBlocks.blockCosmeticSolid && this.md == 4 && ds == DamageSource.field_76376_m) {
            par2 *= 0.5f;
        }
        if (ds.func_76364_f() != null && this.getUpgradeAmount(5) > 0 && ds.func_76364_f().func_145782_y() != this.func_145782_y()) {
            ds.func_76364_f().func_70097_a(DamageSource.func_92087_a((Entity)this), (float)(this.getUpgradeAmount(5) * 2 + this.field_70146_Z.nextInt(2 * this.getUpgradeAmount(5))));
            ds.func_76364_f().func_85030_a("damage.thorns", 0.5f, 1.0f);
        } else if (ds.func_76364_f() != null && this.blocky == Blocks.field_150434_aF && ds.func_76364_f().func_145782_y() != this.func_145782_y()) {
            ds.func_76364_f().func_70097_a(DamageSource.func_92087_a((Entity)this), (float)(this.getUpgradeAmount(5) * 2 + this.field_70146_Z.nextInt(2)));
            ds.func_76364_f().func_85030_a("damage.thorns", 0.5f, 1.0f);
        }
        return super.func_70097_a(ds, par2);
    }

    public int getCarryLimit() {
        int base = this.type.carry;
        if (this.field_70170_p.field_72995_K) {
            base = this.getGolemTHType().carry;
        }
        base += Math.min(16, Math.max(4, base)) * this.getUpgradeAmount(1);
        return base;
    }

    public float func_70689_ay() {
        if (this.paused || this.inactive) {
            return 0.0f;
        }
        float speed = this.type.speed * (this.decoration.contains("B") ? 1.1f : 1.0f);
        if (this.decoration.contains("P")) {
            speed *= 0.88f;
        }
        speed *= 1.0f + (float)this.getUpgradeAmount(0) * 0.15f;
        if (this.advanced) {
            speed *= 1.1f;
        }
        if (this.func_70090_H() && (this.getGolemTHType() == EnumGolemTHType.ROCK || this.getGolemTHType() == EnumGolemTHType.METAL || this.getGolemTHType() == EnumGolemTHType.REDSTONE)) {
            speed *= 2.0f;
        }
        return speed;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.getCore() == -1 && this.ticksAlive > 0) {
                --this.ticksAlive;
                if (this.field_70170_p.func_72924_a(this.getOwnerName()) != null) {
                    this.func_110171_b((int)this.field_70170_p.func_72924_a((String)this.getOwnerName()).field_70165_t, (int)this.field_70170_p.func_72924_a((String)this.getOwnerName()).field_70163_u, (int)this.field_70170_p.func_72924_a((String)this.getOwnerName()).field_70161_v, 16);
                }
            } else if (this.getCore() == -1 && this.ticksAlive != -420) {
                this.die();
            } else if (this.getCore() == -1) {
                if (this.field_70173_aa % 10 == 0 && this.field_70170_p.field_73012_v.nextInt(500) == 0) {
                    EntityPlayer player = this.field_70170_p.func_72924_a(this.getOwnerName());
                    switch (this.voidCount) {
                        case 0: {
                            if (player == null) break;
                            player.func_145747_a((IChatComponent)new ChatComponentText("\u00ef\u00bf\u00bd5\u00ef\u00bf\u00bdo" + StatCollector.func_74838_a((String)"thaumichorizons.golemWarning1")));
                            break;
                        }
                        case 1: {
                            if (player == null) break;
                            player.func_145747_a((IChatComponent)new ChatComponentText("\u00ef\u00bf\u00bd5\u00ef\u00bf\u00bdo" + StatCollector.func_74838_a((String)"thaumichorizons.golemWarning2")));
                            break;
                        }
                        case 2: {
                            if (player == null) break;
                            player.func_145747_a((IChatComponent)new ChatComponentText("\u00ef\u00bf\u00bd5\u00ef\u00bf\u00bdo" + StatCollector.func_74838_a((String)"thaumichorizons.golemWarning3")));
                            break;
                        }
                        case 3: {
                            this.die();
                            Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0f), this.field_70161_v, 2.0f);
                            EntityEldritchGuardian scaryThing = new EntityEldritchGuardian(this.field_70170_p);
                            scaryThing.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                            this.field_70170_p.func_72838_d((Entity)scaryThing);
                            scaryThing.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 32);
                        }
                    }
                    ++this.voidCount;
                }
                if (this.field_70170_p.func_72924_a(this.getOwnerName()) != null) {
                    this.func_110171_b((int)this.field_70170_p.func_72924_a((String)this.getOwnerName()).field_70165_t, (int)this.field_70170_p.func_72924_a((String)this.getOwnerName()).field_70163_u, (int)this.field_70170_p.func_72924_a((String)this.getOwnerName()).field_70161_v, 16);
                }
            }
            if (this.regenTimer <= 0) {
                this.regenTimer = this.type.regenDelay;
                if (this.decoration.contains("F")) {
                    this.regenTimer = (int)((float)this.regenTimer * 0.66f);
                }
                if (!this.field_70170_p.field_72995_K && this.func_110143_aJ() < this.func_110138_aP()) {
                    this.field_70170_p.func_72960_a((Entity)this, (byte)5);
                    this.func_70691_i(1.0f);
                }
            }
        }
    }

    public boolean func_110176_b(int par1, int par2, int par3) {
        if (this.getCore() == -1) {
            return true;
        }
        return super.func_110176_b(par1, par2, par3);
    }

    public void func_70015_d(int par1) {
        if (!this.type.fireResist) {
            super.func_70015_d(par1);
        }
    }

    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        nbt.func_74774_a("GolemTypeTH", (byte)this.type.ordinal());
        nbt.func_74774_a("GolemType", (byte)EnumGolemType.FLESH.ordinal());
        if (this.blocky != null) {
            nbt.func_74768_a("block", Block.func_149682_b((Block)this.blocky));
        } else {
            nbt.func_74768_a("block", 0);
        }
        nbt.func_74773_a("upgrades", this.upgrades);
        nbt.func_74768_a("metadata", this.md);
        nbt.func_74768_a("ticksAlive", this.ticksAlive);
        nbt.func_74768_a("voidCount", this.voidCount);
        nbt.func_74757_a("berserk", this.berserk);
        nbt.func_74757_a("explosive", this.kaboom);
    }

    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        this.type = EnumGolemTHType.getType(nbt.func_74771_c("GolemTypeTH"));
        this.upgrades = new byte[this.type.upgrades + (this.advanced ? 1 : 0)];
        int ul = this.upgrades.length;
        this.upgrades = nbt.func_74770_j("upgrades");
        if (ul != this.upgrades.length) {
            int a;
            byte[] tt = new byte[ul];
            for (a = 0; a < ul; ++a) {
                tt[a] = -1;
            }
            for (a = 0; a < this.upgrades.length; ++a) {
                if (a >= ul) continue;
                tt[a] = this.upgrades[a];
            }
            this.upgrades = tt;
        }
        String st = "";
        for (byte c : this.upgrades) {
            st = st + Integer.toHexString(c);
        }
        this.field_70180_af.func_75692_b(23, (Object)String.valueOf(st));
        this.blocky = Block.func_149729_e((int)nbt.func_74762_e("block"));
        this.md = nbt.func_74762_e("metadata");
        this.ticksAlive = nbt.func_74762_e("ticksAlive");
        this.voidCount = nbt.func_74762_e("voidCount");
        this.berserk = nbt.func_74767_n("berserk");
        this.kaboom = nbt.func_74767_n("explosive");
        if (this.field_70170_p.field_72995_K) {
            this.loadTexture();
        }
    }

    protected void func_70665_d(DamageSource ds, float par2) {
        if (ds.func_76347_k() && this.type.fireResist) {
            return;
        }
        super.func_70665_d(ds, par2);
    }

    public int func_70658_aO() {
        int var1 = super.func_70658_aO() + this.type.armor;
        if (this.decoration.contains("V")) {
            ++var1;
        }
        if (this.decoration.contains("P")) {
            var1 += 4;
        }
        if (var1 > 20) {
            var1 = 20;
        }
        return var1;
    }

    public EnumGolemTHType getGolemTHType() {
        return EnumGolemTHType.getType(this.field_70180_af.func_75683_a(19));
    }

    @SideOnly(value=Side.CLIENT)
    public void loadTexture() {
        if (this.blocky == null || this.blocky == Blocks.field_150350_a) {
            return;
        }
        IIcon bottom = this.blocky.func_149691_a(0, this.md);
        IIcon top = this.blocky.func_149691_a(1, this.md);
        IIcon east = this.blocky.func_149691_a(2, this.md);
        IIcon west = this.blocky.func_149691_a(3, this.md);
        IIcon north = this.blocky.func_149691_a(4, this.md);
        IIcon south = this.blocky.func_149691_a(5, this.md);
        GolemTHTexture newTex = new GolemTHTexture(new IIcon[]{bottom, top, east, west, north, south}, this.blocky == Blocks.field_150349_c ? 1 : (this.blocky == Blocks.field_150414_aQ ? 2 : 0));
        this.texture = new ResourceLocation("thaumichorizons", "TEMPGOLEMTEX" + this.func_145782_y());
        Minecraft.func_71410_x().func_110434_K().func_110579_a(this.texture, (ITextureObject)newTex);
    }

    public boolean func_70085_c(EntityPlayer player) {
        if (player.func_70093_af()) {
            return false;
        }
        ItemStack itemstack = player.field_71071_by.func_70448_g();
        if (this.getCore() == -1 && itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemCore && itemstack.func_77960_j() != 100) {
            this.func_110171_b((int)Math.round(this.field_70165_t - 0.5), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5), 32);
            this.setCore((byte)itemstack.func_77960_j());
            this.setupGolem();
            this.setupGolemInventory();
            --itemstack.field_77994_a;
            if (itemstack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
            }
            this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:upgrade", 0.5f, 1.0f);
            player.func_71038_i();
            this.field_70170_p.func_72960_a((Entity)this, (byte)7);
            return true;
        }
        if (this.getCore() == -1) {
            return false;
        }
        if (itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemUpgrade) {
            for (int a = 0; a < this.upgrades.length; ++a) {
                if (this.getUpgrade(a) != -1 || this.getUpgradeAmount(itemstack.func_77960_j()) >= 2) continue;
                this.setUpgrade(a, (byte)itemstack.func_77960_j());
                this.setupGolem();
                this.setupGolemInventory();
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
                }
                this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:upgrade", 0.5f, 1.0f);
                player.func_71038_i();
                return true;
            }
            return false;
        }
        return super.func_70085_c(player);
    }

    public void die() {
        int z;
        int x;
        this.func_70106_y();
        this.func_70656_aK();
        this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:craftfail", 1.0f, 1.0f);
        if (this.blocky != null && this.blocky != Blocks.field_150350_a && this.blocky.func_149688_o() == Material.field_151590_u) {
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 4.0f, true);
            return;
        }
        if (this.kaboom) {
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0f, true);
            return;
        }
        if (this.field_70170_p.func_147437_c((int)Math.round(this.field_70165_t - 0.5), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5))) {
            if (this.blocky != null && this.blocky != Blocks.field_150350_a) {
                this.field_70170_p.func_147465_d((int)Math.round(this.field_70165_t - 0.5), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5), this.blocky, this.md, 3);
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlocksplosion(Block.func_149682_b((Block)this.blocky), this.md, (int)Math.round(this.field_70165_t - 0.5), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5)), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, (double)((int)Math.round(this.field_70165_t - 0.5)), (double)((int)Math.round(this.field_70163_u)), (double)((int)Math.round(this.field_70161_v - 0.5)), 32.0));
            }
            return;
        }
        for (x = -1; x < 2; ++x) {
            for (z = -1; z < 2; ++z) {
                if (!this.field_70170_p.func_147437_c((int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5) + z)) continue;
                if (this.blocky != null && this.blocky != Blocks.field_150350_a) {
                    this.field_70170_p.func_147465_d((int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5) + z, this.blocky, this.md, 3);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlocksplosion(Block.func_149682_b((Block)this.blocky), this.md, (int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v - 0.5) + z), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, (double)((int)Math.round(this.field_70165_t - 0.5) + x), (double)((int)Math.round(this.field_70163_u)), (double)((int)Math.round(this.field_70161_v - 0.5) + z), 32.0));
                }
                return;
            }
        }
        for (x = -1; x < 2; ++x) {
            for (z = -1; z < 2; ++z) {
                if (!this.field_70170_p.func_147437_c((int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u) - 1, (int)Math.round(this.field_70161_v - 0.5) + z)) continue;
                if (this.blocky != null && this.blocky != Blocks.field_150350_a) {
                    this.field_70170_p.func_147465_d((int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u) - 1, (int)Math.round(this.field_70161_v - 0.5) + z, this.blocky, this.md, 3);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlocksplosion(Block.func_149682_b((Block)this.blocky), this.md, (int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u) - 1, (int)Math.round(this.field_70161_v - 0.5) + z), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, (double)((int)Math.round(this.field_70165_t - 0.5) + x), (double)((int)Math.round(this.field_70163_u) - 1), (double)((int)Math.round(this.field_70161_v - 0.5) + z), 32.0));
                }
                return;
            }
        }
        for (x = -1; x < 2; ++x) {
            for (z = -1; z < 2; ++z) {
                if (!this.field_70170_p.func_147437_c((int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u) + 1, (int)Math.round(this.field_70161_v - 0.5) + z)) continue;
                if (this.blocky != null && this.blocky != Blocks.field_150350_a) {
                    this.field_70170_p.func_147465_d((int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u) + 1, (int)Math.round(this.field_70161_v - 0.5) + z, this.blocky, this.md, 3);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlocksplosion(Block.func_149682_b((Block)this.blocky), this.md, (int)Math.round(this.field_70165_t - 0.5) + x, (int)Math.round(this.field_70163_u) + 1, (int)Math.round(this.field_70161_v - 0.5) + z), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, (double)((int)Math.round(this.field_70165_t - 0.5) + x), (double)((int)Math.round(this.field_70163_u) + 1), (double)((int)Math.round(this.field_70161_v - 0.5) + z), 32.0));
                }
                return;
            }
        }
    }

    public void writeSpawnData(ByteBuf data) {
        super.writeSpawnData(data);
        if (this.blocky != null) {
            data.writeInt(Block.func_149682_b((Block)this.blocky));
        } else {
            data.writeInt(0);
        }
        data.writeByte(this.md);
        data.writeByte(this.upgrades.length);
        for (byte b : this.upgrades) {
            data.writeByte((int)b);
        }
    }

    public void readSpawnData(ByteBuf data) {
        super.readSpawnData(data);
        this.blocky = Block.func_149729_e((int)data.readInt());
        this.md = data.readByte();
        this.upgrades = new byte[data.readByte()];
        for (int a = 0; a < this.upgrades.length; ++a) {
            this.upgrades[a] = data.readByte();
        }
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        ItemStack stack = new ItemStack(this.blocky, 1, this.md);
        if (stack.func_77973_b() != null) {
            return stack.func_82833_r() + " Golem";
        }
        if (this.blocky == null || this.blocky == Blocks.field_150350_a) {
            return "Voidling Golem";
        }
        return this.blocky.func_149732_F() + " Golem";
    }

    public EnumGolemType getGolemType() {
        return EnumGolemType.WOOD;
    }

    public int getGolemStrength() {
        return this.type.strength + this.getUpgradeAmount(1);
    }

    public boolean func_70652_k(Entity par1Entity) {
        if (this.blocky == Blocks.field_150321_G && par1Entity instanceof EntityLivingBase) {
            ((EntityLivingBase)par1Entity).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 20, 0));
        }
        return super.func_70652_k(par1Entity);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.action = 6;
        } else if (par1 == 5) {
            this.healing = 5;
            int bonus = 0;
            try {
                bonus = this.getGolemDecoration().contains("H") ? 5 : 0;
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(this.type.health + bonus));
        } else if (par1 == 6) {
            this.leftArm = 5;
        } else if (par1 == 8) {
            this.rightArm = 5;
        } else if (par1 == 7) {
            this.bootup = 33.0f;
        } else {
            super.func_70103_a(par1);
        }
    }

    public void func_70077_a(EntityLightningBolt bolt) {
        if (bolt instanceof EntityLightningBoltFinite) {
            return;
        }
        super.func_70077_a(bolt);
    }
}

