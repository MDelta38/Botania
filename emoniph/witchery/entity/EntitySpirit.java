/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.ChunkProviderFlat
 *  net.minecraft.world.gen.ChunkProviderGenerate
 *  net.minecraft.world.gen.ChunkProviderHell
 *  net.minecraft.world.gen.ChunkProviderServer
 *  net.minecraft.world.gen.structure.MapGenStructure
 *  net.minecraft.world.gen.structure.MapGenVillage
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityFlyingTameable;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint;
import com.emoniph.witchery.entity.ai.EntityAIFlyerFollowOwner;
import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
import com.emoniph.witchery.entity.ai.EntityAIFlyerWander;
import com.emoniph.witchery.entity.ai.EntityAIFlyingTempt;
import com.emoniph.witchery.entity.ai.EntityAISitAndStay;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;

public class EntitySpirit
extends EntityFlyingTameable {
    public EntityAIFlyingTempt aiTempt;
    private int timeToLive = -1;
    private int spiritType = 0;
    private static final ItemStack[] TEMPTATIONS = new ItemStack[]{Witchery.Items.GENERIC.itemFocusedWill.createStack()};
    private static Field fieldStructureGenerators;
    private static Field fieldVillageGenerator;

    public EntitySpirit(World world) {
        super(world);
        this.func_70105_a(0.25f, 0.25f);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISitAndStay(this));
        this.aiTempt = new EntityAIFlyingTempt((EntityCreature)this, 0.6, TEMPTATIONS, true);
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.aiTempt);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIFlyerFollowOwner(this, 1.0, 14.0f, 5.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIFlyerFlyToWaypoint(this, EntityAIFlyerFlyToWaypoint.CarryRequirement.NONE));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIFlyerLand((EntityLiving)this, 0.8, true));
        this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAIFlyerWander((EntityLiving)this, 0.8, 10.0));
        this.field_70714_bg.func_75776_a(11, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0f, 0.2f));
    }

    protected boolean func_70692_ba() {
        return true;
    }

    public void setTarget(String target, int type) {
        this.timeToLive = TimeUtil.secsToTicks(10);
        this.spiritType = type;
        try {
            if (target.equals("Village")) {
                IChunkProvider cp = this.field_70170_p.func_72863_F();
                while (cp != null && cp instanceof ChunkProviderServer) {
                    cp = ((ChunkProviderServer)cp).field_73246_d;
                }
                if (cp != null) {
                    if (cp instanceof ChunkProviderFlat) {
                        if (fieldStructureGenerators == null) {
                            fieldStructureGenerators = ReflectionHelper.findField(ChunkProviderFlat.class, (String[])new String[]{"structureGenerators", "field_82696_f", "f"});
                        }
                        Iterator iterator = ((List)fieldStructureGenerators.get((ChunkProviderFlat)cp)).iterator();
                        while (iterator.hasNext()) {
                            if (!this.setWaypointTo(iterator.next(), MapGenVillage.class)) continue;
                            return;
                        }
                    } else if (cp instanceof ChunkProviderGenerate) {
                        if (fieldVillageGenerator == null) {
                            fieldVillageGenerator = ReflectionHelper.findField(ChunkProviderGenerate.class, (String[])new String[]{"villageGenerator", "field_73224_v", "v"});
                        }
                        if (fieldVillageGenerator != null) {
                            this.setWaypointTo(fieldVillageGenerator.get((ChunkProviderGenerate)cp), MapGenVillage.class);
                        }
                    } else if (cp instanceof ChunkProviderHell) {
                        this.setWaypointTo((MapGenStructure)((ChunkProviderHell)cp).field_73172_c);
                    }
                }
            }
        }
        catch (IllegalAccessException illegalAccessException) {
            // empty catch block
        }
    }

    private boolean setWaypointTo(Object objStructure, Class<? extends MapGenStructure> clazz) {
        if (objStructure != null && clazz.isAssignableFrom(objStructure.getClass())) {
            this.setWaypointTo((MapGenStructure)objStructure);
            return true;
        }
        return false;
    }

    private void setWaypointTo(MapGenStructure mapStructure) {
        ChunkPosition pos;
        if (mapStructure != null && (pos = mapStructure.func_151545_a(this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v)) != null) {
            this.homeX = pos.field_151329_a;
            this.homeY = pos.field_151327_b;
            this.homeZ = pos.field_151328_c;
            this.waypoint = Witchery.Items.GENERIC.itemWaystone.createStack();
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        nbtRoot.func_74768_a("SuicideIn", this.timeToLive);
        nbtRoot.func_74768_a("SpiritType", this.spiritType);
    }

    @Override
    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        this.timeToLive = nbtRoot.func_74764_b("SuicideIn") ? nbtRoot.func_74762_e("SuicideIn") : -1;
        this.spiritType = nbtRoot.func_74764_b("SpiritType") ? nbtRoot.func_74762_e("SpiritType") : 0;
    }

    protected void func_70629_bd() {
        this.func_70661_as().func_75499_g();
        super.func_70629_bd();
        if (this.field_70170_p != null && !this.field_70128_L && !this.field_70170_p.field_72995_K && this.timeToLive != -1 && --this.timeToLive == 0) {
            ParticleEffect.EXPLODE.send(SoundEffect.NONE, (Entity)this, 1.0, 1.0, 16);
            this.func_70106_y();
            if (!this.field_70170_p.field_72995_K) {
                this.func_70628_a(false, 0);
            }
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(21, (Object)0);
    }

    protected int func_70682_h(int par1) {
        return par1;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (this.spiritType == 2) {
            return;
        }
        ItemStack stack = this.spiritType == 1 ? Witchery.Items.GENERIC.itemSubduedSpiritVillage.createStack() : Witchery.Items.GENERIC.itemSubduedSpirit.createStack();
        this.func_70099_a(stack, 0.0f);
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            int color = this.getFeatherColor();
            float red = 1.0f;
            float green = 0.8f;
            float blue = 0.0f;
            if (color > 0) {
                red = (float)(color >> 16 & 0xFF) / 255.0f;
                green = (float)(color >> 8 & 0xFF) / 255.0f;
                blue = (float)(color & 0xFF) / 255.0f;
            }
            Witchery.proxy.generateParticle(this.field_70170_p, this.field_70165_t - (double)this.field_70130_N * 0.5 + this.field_70170_p.field_73012_v.nextDouble() * (double)this.field_70130_N, 0.1 + this.field_70163_u + this.field_70170_p.field_73012_v.nextDouble() * 0.2, this.field_70161_v - (double)this.field_70130_N * 0.5 + this.field_70170_p.field_73012_v.nextDouble() * (double)this.field_70130_N, red, green, blue, 10, -0.1f);
        }
    }

    public int func_70627_aG() {
        return super.func_70627_aG() * 2;
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return null;
    }

    protected String func_70673_aS() {
        return null;
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }

    public EntitySpirit spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
        return null;
    }

    public boolean func_70877_b(ItemStack itemstack) {
        return itemstack != null && itemstack.func_77973_b() == Items.field_151103_aS;
    }

    public boolean func_70878_b(EntityAnimal par1EntityAnimal) {
        return false;
    }

    public int getFeatherColor() {
        return this.field_70180_af.func_75679_c(21);
    }

    public void setFeatherColor(int par1) {
        this.field_70180_af.func_75692_b(21, (Object)par1);
    }

    public boolean func_70601_bi() {
        if (this.field_70170_p.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) {
            boolean superGetCanSpawnHere = this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
            int i = MathHelper.func_76128_c((double)this.field_70165_t);
            int j = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b);
            int k = MathHelper.func_76128_c((double)this.field_70161_v);
            superGetCanSpawnHere = superGetCanSpawnHere && this.func_70783_a(i, j, k) >= 0.0f && j >= 60;
            Block blockID = this.field_70170_p.func_147439_a(i, j - 1, k);
            return superGetCanSpawnHere && this.field_70170_p.field_73012_v.nextInt(10) == 0 && (blockID == Blocks.field_150349_c || blockID == Blocks.field_150354_m) && this.field_70170_p.func_72883_k(i, j, k) > 8;
        }
        return false;
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.spirit.name");
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
        return par1EntityLivingData;
    }

    public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable) {
        return null;
    }
}

