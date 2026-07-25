/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentData
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemNameTag
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemNameTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.pech.AIPechItemEntityGoto;
import thaumcraft.common.entities.ai.pech.AIPechTradePlayer;
import thaumcraft.common.entities.projectile.EntityPechBlast;
import thaumcraft.common.items.ItemManaBean;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.utils.InventoryUtils;

public class EntityPech
extends EntityMob
implements IRangedAttackMob {
    public ItemStack[] loot = new ItemStack[9];
    public boolean trading = false;
    public boolean updateAINextTick = false;
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack((IRangedAttackMob)this, 0.6, 20, 50, 15.0f);
    private EntityAIArrowAttack aiBlastAttack = new EntityAIArrowAttack((IRangedAttackMob)this, 0.6, 20, 30, 15.0f);
    private AIAttackOnCollide aiMeleeAttack = new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 0.6, false);
    private EntityAIAvoidEntity aiAvoidPlayer = new EntityAIAvoidEntity((EntityCreature)this, EntityPlayer.class, 8.0f, 0.5, 0.6);
    public float mumble = 0.0f;
    int chargecount = 0;
    static HashMap<Integer, Integer> valuedItems;
    public static HashMap<Integer, ArrayList<List>> tradeInventory;

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        switch (this.getPechType()) {
            case 0: {
                return StatCollector.func_74838_a((String)"entity.Thaumcraft.Pech.name");
            }
            case 1: {
                return StatCollector.func_74838_a((String)"entity.Thaumcraft.Pech.1.name");
            }
            case 2: {
                return StatCollector.func_74838_a((String)"entity.Thaumcraft.Pech.2.name");
            }
        }
        return StatCollector.func_74838_a((String)"entity.Thaumcraft.Pech.name");
    }

    public EntityPech(World world) {
        super(world);
        this.func_70105_a(0.6f, 1.8f);
        this.func_70661_as().func_75498_b(false);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIPechTradePlayer(this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIPechItemEntityGoto(this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.5));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f));
        this.field_70714_bg.func_75776_a(11, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        if (world != null && !world.field_72995_K) {
            this.setCombatTask();
        }
        this.field_82174_bp[0] = 0.2f;
    }

    public void func_70062_b(int par1, ItemStack par2ItemStack) {
        super.func_70062_b(par1, par2ItemStack);
        if (!this.field_70170_p.field_72995_K && par1 == 0) {
            this.updateAINextTick = true;
        }
    }

    protected void func_82164_bB() {
        super.func_82164_bB();
        switch (this.field_70146_Z.nextInt(20)) {
            case 0: 
            case 12: {
                ItemStack wand = new ItemStack(ConfigItems.itemWandCasting);
                ItemStack focus = new ItemStack(ConfigItems.itemFocusPech);
                ((ItemWandCasting)wand.func_77973_b()).setFocus(wand, focus);
                ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.EARTH, 2 + this.field_70146_Z.nextInt(6), true);
                ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.ENTROPY, 2 + this.field_70146_Z.nextInt(6), true);
                ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.WATER, 2 + this.field_70146_Z.nextInt(6), true);
                ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.AIR, this.field_70146_Z.nextInt(4), true);
                ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.FIRE, this.field_70146_Z.nextInt(4), true);
                ((ItemWandCasting)wand.func_77973_b()).addVis(wand, Aspect.ORDER, this.field_70146_Z.nextInt(4), true);
                this.func_70062_b(0, wand);
                break;
            }
            case 1: {
                this.func_70062_b(0, new ItemStack(Items.field_151052_q));
                break;
            }
            case 3: {
                this.func_70062_b(0, new ItemStack(Items.field_151049_t));
                break;
            }
            case 5: {
                this.func_70062_b(0, new ItemStack(Items.field_151040_l));
                break;
            }
            case 6: {
                this.func_70062_b(0, new ItemStack(Items.field_151036_c));
                break;
            }
            case 7: {
                this.func_70062_b(0, new ItemStack((Item)Items.field_151112_aM));
                break;
            }
            case 8: {
                this.func_70062_b(0, new ItemStack(Items.field_151050_s));
                break;
            }
            case 9: {
                this.func_70062_b(0, new ItemStack(Items.field_151035_b));
                break;
            }
            case 2: 
            case 4: 
            case 10: 
            case 11: 
            case 13: {
                this.func_70062_b(0, new ItemStack((Item)Items.field_151031_f));
            }
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
        this.func_82164_bB();
        ItemStack itemstack = this.func_70694_bm();
        if (itemstack != null && itemstack.func_77973_b() == ConfigItems.itemWandCasting) {
            this.setPechType(1);
            this.field_82174_bp[0] = 0.1f;
        } else if (itemstack != null) {
            if (itemstack.func_77973_b() == Items.field_151031_f) {
                this.setPechType(2);
            }
            this.func_82162_bC();
        }
        this.func_98053_h(this.field_70146_Z.nextFloat() < 0.75f * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v));
        return super.func_110161_a(par1EntityLivingData);
    }

    public boolean func_70601_bi() {
        BiomeGenBase biome = this.field_70170_p.func_72807_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70161_v));
        boolean magicBiome = false;
        if (biome != null) {
            magicBiome = BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MAGICAL) && biome.field_76756_M != Config.biomeTaintID;
        }
        int count = 0;
        try {
            List l = this.field_70170_p.func_72872_a(EntityPech.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
            if (l != null) {
                count = l.size();
            }
        }
        catch (Exception e) {
            // empty catch block
        }
        if (this.field_70170_p.field_73011_w.field_76574_g != 0 && biome.field_76756_M != Config.biomeMagicalForestID && biome.field_76756_M != Config.biomeEerieID) {
            magicBiome = false;
        }
        return count < 4 && magicBiome && super.func_70601_bi();
    }

    public float func_70047_e() {
        return this.field_70131_O * 0.66f;
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(13, (Object)new Byte(0));
        this.field_70180_af.func_75682_a(14, (Object)new Short(0));
        this.field_70180_af.func_75682_a(16, (Object)new Byte(0));
    }

    public int getPechType() {
        return this.field_70180_af.func_75683_a(13);
    }

    public int getAnger() {
        return this.field_70180_af.func_75693_b(14);
    }

    public boolean isTamed() {
        return this.field_70180_af.func_75683_a(16) == 1;
    }

    public void setPechType(int par1) {
        this.field_70180_af.func_75692_b(13, (Object)((byte)par1));
    }

    public void setAnger(int par1) {
        this.field_70180_af.func_75692_b(14, (Object)((short)par1));
    }

    public void setTamed(boolean par1) {
        this.field_70180_af.func_75692_b(16, (Object)(par1 ? (byte)1 : 0));
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("PechType", (byte)this.getPechType());
        par1NBTTagCompound.func_74777_a("Anger", (short)this.getAnger());
        par1NBTTagCompound.func_74757_a("Tamed", this.isTamed());
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.loot.length; ++i) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            if (this.loot[i] != null) {
                this.loot[i].func_77955_b(nbttagcompound1);
            }
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.func_74782_a("Loot", (NBTBase)nbttaglist);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        if (par1NBTTagCompound.func_74764_b("PechType")) {
            byte b0 = par1NBTTagCompound.func_74771_c("PechType");
            this.setPechType(b0);
        }
        this.setAnger(par1NBTTagCompound.func_74765_d("Anger"));
        this.setTamed(par1NBTTagCompound.func_74767_n("Tamed"));
        if (par1NBTTagCompound.func_74764_b("Loot")) {
            NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Loot", 10);
            for (int i = 0; i < this.loot.length; ++i) {
                this.loot[i] = ItemStack.func_77949_a((NBTTagCompound)nbttaglist.func_150305_b(i));
            }
        }
        this.updateAINextTick = true;
    }

    protected boolean func_70692_ba() {
        try {
            if (this.loot == null) {
                return true;
            }
            int q = 0;
            for (ItemStack is : this.loot) {
                if (is == null || is.field_77994_a <= 0) continue;
                ++q;
            }
            return q < 5;
        }
        catch (Exception e) {
            return true;
        }
    }

    public boolean func_110164_bC() {
        return false;
    }

    protected void func_70628_a(boolean flag, int i) {
        for (int a = 0; a < this.loot.length; ++a) {
            if (this.loot[a] == null || !(this.field_70170_p.field_73012_v.nextFloat() < 0.88f)) continue;
            this.func_70099_a(this.loot[a].func_77946_l(), 1.5f);
        }
        Aspect[] aspects = Aspect.getPrimalAspects().toArray(new Aspect[0]);
        for (int a = 0; a < 1 + i; ++a) {
            if (!this.field_70146_Z.nextBoolean()) continue;
            ItemStack is = new ItemStack(ConfigItems.itemManaBean);
            ((ItemManaBean)is.func_77973_b()).setAspects(is, new AspectList().add(aspects[this.field_70146_Z.nextInt(aspects.length)], 1));
            this.func_70099_a(is, 1.5f);
        }
        if (this.field_70170_p.field_73012_v.nextInt(10) < 1 + i) {
            this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 18), 1.5f);
        }
        super.func_70628_a(flag, i);
    }

    protected void func_70600_l(int par1) {
        this.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 9), 1.5f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        double d2;
        double d1;
        double d0;
        int i;
        if (par1 == 16) {
            this.mumble = (float)Math.PI;
        } else if (par1 == 17) {
            this.mumble = (float)Math.PI * 2;
        } else if (par1 == 18) {
            for (i = 0; i < 5; ++i) {
                d0 = this.field_70146_Z.nextGaussian() * 0.02;
                d1 = this.field_70146_Z.nextGaussian() * 0.02;
                d2 = this.field_70146_Z.nextGaussian() * 0.02;
                this.field_70170_p.func_72869_a("happyVillager", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2);
            }
        }
        if (par1 == 19) {
            for (i = 0; i < 5; ++i) {
                d0 = this.field_70146_Z.nextGaussian() * 0.02;
                d1 = this.field_70146_Z.nextGaussian() * 0.02;
                d2 = this.field_70146_Z.nextGaussian() * 0.02;
                this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2);
            }
            this.mumble = (float)Math.PI * 2;
        } else {
            super.func_70103_a(par1);
        }
    }

    public void func_70642_aH() {
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70146_Z.nextInt(3) == 0) {
                List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(4.0, 2.0, 4.0));
                for (int i = 0; i < list.size(); ++i) {
                    Entity entity1 = (Entity)list.get(i);
                    if (!(entity1 instanceof EntityPech)) continue;
                    this.field_70170_p.func_72960_a((Entity)this, (byte)17);
                    this.func_85030_a("thaumcraft:pech_trade", this.func_70599_aP(), this.func_70647_i());
                    return;
                }
            }
            this.field_70170_p.func_72960_a((Entity)this, (byte)16);
        }
        super.func_70642_aH();
    }

    public int func_70627_aG() {
        return 120;
    }

    protected float func_70599_aP() {
        return 0.4f;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:pech_idle";
    }

    protected String func_70621_aR() {
        return "thaumcraft:pech_hit";
    }

    protected String func_70673_aS() {
        return "thaumcraft:pech_death";
    }

    protected Entity func_70782_k() {
        return this.getAnger() == 0 ? null : super.func_70782_k();
    }

    public void setCombatTask() {
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiMeleeAttack);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiArrowAttack);
        this.field_70714_bg.func_85156_a((EntityAIBase)this.aiBlastAttack);
        ItemStack itemstack = this.func_70694_bm();
        if (itemstack != null && itemstack.func_77973_b() == Items.field_151031_f) {
            this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.aiArrowAttack);
        } else if (itemstack != null && itemstack.func_77973_b() == ConfigItems.itemWandCasting) {
            this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.aiBlastAttack);
        } else {
            this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.aiMeleeAttack);
        }
        if (this.isTamed()) {
            this.field_70714_bg.func_85156_a((EntityAIBase)this.aiAvoidPlayer);
        } else {
            this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiAvoidPlayer);
        }
    }

    public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
        if (this.getPechType() == 2) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, (EntityLivingBase)this, entitylivingbase, 1.6f, (float)(14 - this.field_70170_p.field_73013_u.func_151525_a() * 4));
            int i = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)this.func_70694_bm());
            int j = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)this.func_70694_bm());
            entityarrow.func_70239_b((double)(f * 2.0f) + this.field_70146_Z.nextGaussian() * 0.25 + (double)((float)this.field_70170_p.field_73013_u.func_151525_a() * 0.11f));
            if (i > 0) {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)i * 0.5 + 0.5);
            }
            if (j > 0) {
                entityarrow.func_70240_a(j);
            }
            this.func_85030_a("random.bow", 1.0f, 1.0f / (this.func_70681_au().nextFloat() * 0.4f + 0.8f));
            this.field_70170_p.func_72838_d((Entity)entityarrow);
        } else if (this.getPechType() == 1) {
            EntityPechBlast blast = new EntityPechBlast(this.field_70170_p, (EntityLivingBase)this, 1, 0, this.field_70146_Z.nextFloat() < 0.1f);
            double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
            double d1 = entitylivingbase.field_70163_u + (double)entitylivingbase.func_70047_e() - 1.500000023841858 - this.field_70163_u;
            double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
            float f1 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
            blast.func_70186_c(d0, d1 + (double)(f1 * 0.1f), d2, 1.5f, 4.0f);
            this.func_85030_a("thaumcraft:ice", 0.4f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
            this.field_70170_p.func_72838_d((Entity)blast);
        }
        this.func_71038_i();
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        if (this.getAnger() <= 0) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)19);
            this.func_85030_a("thaumcraft:pech_charge", this.func_70599_aP(), this.func_70647_i());
        }
        this.func_70624_b((EntityLivingBase)par1Entity);
        this.setAnger(400 + this.field_70146_Z.nextInt(400));
        this.setTamed(false);
        this.updateAINextTick = true;
    }

    public int func_70658_aO() {
        int i = super.func_70658_aO() + 2;
        if (i > 20) {
            i = 20;
        }
        return i;
    }

    public boolean func_70097_a(DamageSource damSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        Entity entity = damSource.func_76346_g();
        if (entity instanceof EntityPlayer) {
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(32.0, 16.0, 32.0));
            for (int i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);
                if (!(entity1 instanceof EntityPech)) continue;
                EntityPech entitypech = (EntityPech)entity1;
                entitypech.becomeAngryAt(entity);
            }
            this.becomeAngryAt(entity);
        }
        return super.func_70097_a(damSource, par2);
    }

    public void func_70071_h_() {
        double d2;
        double d1;
        double d0;
        if (this.mumble > 0.0f) {
            this.mumble *= 0.75f;
        }
        if (this.getAnger() > 0) {
            this.setAnger(this.getAnger() - 1);
        }
        if (this.getAnger() > 0 && (this.field_70789_a == null || this.func_70638_az() == null)) {
            this.func_70782_k();
            this.func_70624_b((EntityLivingBase)this.field_70789_a);
            if (this.field_70789_a != null) {
                if (this.chargecount > 0) {
                    --this.chargecount;
                }
                if (this.chargecount == 0) {
                    this.chargecount = 100;
                    this.func_85030_a("thaumcraft:pech_charge", this.func_70599_aP(), this.func_70647_i());
                }
                this.field_70170_p.func_72960_a((Entity)this, (byte)17);
            }
        }
        if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(15) == 0 && this.getAnger() > 0) {
            d0 = this.field_70146_Z.nextGaussian() * 0.02;
            d1 = this.field_70146_Z.nextGaussian() * 0.02;
            d2 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a("angryVillager", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2);
        }
        if (this.field_70170_p.field_72995_K && this.field_70146_Z.nextInt(25) == 0 && this.isTamed()) {
            d0 = this.field_70146_Z.nextGaussian() * 0.02;
            d1 = this.field_70146_Z.nextGaussian() * 0.02;
            d2 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a("happyVillager", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2);
        }
        super.func_70071_h_();
    }

    public void func_70619_bc() {
        if (this.updateAINextTick) {
            this.updateAINextTick = false;
            this.setCombatTask();
        }
        super.func_70619_bc();
        if (this.field_70173_aa % 40 == 0) {
            this.func_70691_i(1.0f);
        }
    }

    public boolean canPickup(ItemStack entityItem) {
        if (entityItem == null) {
            return false;
        }
        if (!this.isTamed() && valuedItems.containsKey(Item.func_150891_b((Item)entityItem.func_77973_b()))) {
            return true;
        }
        for (int a = 0; a < this.loot.length; ++a) {
            if (this.loot[a] != null && this.loot[a].field_77994_a <= 0) {
                this.loot[a] = null;
            }
            if (this.loot[a] == null) {
                return true;
            }
            if (!InventoryUtils.areItemStacksEqualStrict(entityItem, this.loot[a]) || entityItem.field_77994_a + this.loot[a].field_77994_a > this.loot[a].func_77976_d()) continue;
            return true;
        }
        return false;
    }

    public ItemStack pickupItem(ItemStack entityItem) {
        int a;
        if (entityItem == null) {
            return entityItem;
        }
        if (!this.isTamed() && this.isValued(entityItem)) {
            if (this.field_70146_Z.nextInt(10) < this.getValue(entityItem)) {
                this.setTamed(true);
                this.updateAINextTick = true;
                this.field_70170_p.func_72960_a((Entity)this, (byte)18);
            }
            --entityItem.field_77994_a;
            if (entityItem.field_77994_a <= 0) {
                return null;
            }
            return entityItem;
        }
        for (a = 0; a < this.loot.length; ++a) {
            if (this.loot[a] != null && this.loot[a].field_77994_a <= 0) {
                this.loot[a] = null;
            }
            if (entityItem != null && entityItem.field_77994_a > 0 && this.loot[a] != null && this.loot[a].field_77994_a < this.loot[a].func_77976_d() && InventoryUtils.areItemStacksEqualStrict(entityItem, this.loot[a])) {
                if (entityItem.field_77994_a + this.loot[a].field_77994_a <= this.loot[a].func_77976_d()) {
                    this.loot[a].field_77994_a += entityItem.field_77994_a;
                    return null;
                }
                int sz = Math.min(entityItem.field_77994_a, this.loot[a].func_77976_d() - this.loot[a].field_77994_a);
                this.loot[a].field_77994_a += sz;
                entityItem.field_77994_a -= sz;
            }
            if (entityItem == null || entityItem.field_77994_a > 0) continue;
            entityItem = null;
        }
        for (a = 0; a < this.loot.length; ++a) {
            if (this.loot[a] != null && this.loot[a].field_77994_a <= 0) {
                this.loot[a] = null;
            }
            if (entityItem == null || entityItem.field_77994_a <= 0 || this.loot[a] != null) continue;
            this.loot[a] = entityItem.func_77946_l();
            return null;
        }
        if (entityItem != null && entityItem.field_77994_a <= 0) {
            entityItem = null;
        }
        return entityItem;
    }

    public boolean func_70085_c(EntityPlayer player) {
        if (player.func_70093_af() || player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemNameTag) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K && this.isTamed()) {
            player.openGui((Object)Thaumcraft.instance, 1, this.field_70170_p, this.func_145782_y(), 0, 0);
            return true;
        }
        return super.func_70085_c(player);
    }

    public boolean isValued(ItemStack item) {
        if (item == null) {
            return false;
        }
        boolean value = valuedItems.containsKey(Item.func_150891_b((Item)item.func_77973_b()));
        if (!value) {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
            if ((al = ThaumcraftCraftingManager.getBonusTags(item, al)).getAmount(Aspect.GREED) > 0) {
                value = true;
            }
        }
        return value;
    }

    public int getValue(ItemStack item) {
        int value;
        if (item == null) {
            return 0;
        }
        int n = value = valuedItems.containsKey(Item.func_150891_b((Item)item.func_77973_b())) ? valuedItems.get(Item.func_150891_b((Item)item.func_77973_b())) : 0;
        if (value == 0) {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(item);
            al = ThaumcraftCraftingManager.getBonusTags(item, al);
            value = Math.min(32, al.getAmount(Aspect.GREED));
        }
        return value;
    }

    static {
        int a;
        valuedItems = new HashMap();
        tradeInventory = new HashMap();
        valuedItems.put(Item.func_150891_b((Item)ConfigItems.itemManaBean), 1);
        valuedItems.put(Item.func_150891_b((Item)Items.field_151043_k), 2);
        valuedItems.put(Item.func_150891_b((Item)Items.field_151153_ao), 2);
        valuedItems.put(Item.func_150891_b((Item)Items.field_151079_bi), 3);
        valuedItems.put(Item.func_150891_b((Item)Items.field_151045_i), 4);
        valuedItems.put(Item.func_150891_b((Item)Items.field_151166_bC), 5);
        ArrayList<List<Object>> forInv = new ArrayList<List<Object>>();
        forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemManaBean)));
        forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 16)));
        forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 31)));
        forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 21)));
        if (Config.foundCopperIngot) {
            forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 17)));
        }
        if (Config.foundTinIngot) {
            forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 18)));
        }
        if (Config.foundSilverIngot) {
            forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 19)));
        }
        if (Config.foundLeadIngot) {
            forInv.add(Arrays.asList(1, new ItemStack(ConfigItems.itemNugget, 1, 20)));
        }
        forInv.add(Arrays.asList(2, new ItemStack(Items.field_151072_bj)));
        forInv.add(Arrays.asList(2, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0)));
        forInv.add(Arrays.asList(2, new ItemStack((Item)Items.field_151068_bn, 1, 8201)));
        forInv.add(Arrays.asList(2, new ItemStack((Item)Items.field_151068_bn, 1, 8194)));
        forInv.add(Arrays.asList(3, new ItemStack(Items.field_151062_by)));
        forInv.add(Arrays.asList(3, new ItemStack(ConfigItems.itemResource, 1, 9)));
        forInv.add(Arrays.asList(3, new ItemStack(Items.field_151153_ao, 1, 0)));
        forInv.add(Arrays.asList(3, new ItemStack((Item)Items.field_151068_bn, 1, 8265)));
        forInv.add(Arrays.asList(3, new ItemStack((Item)Items.field_151068_bn, 1, 8262)));
        forInv.add(Arrays.asList(5, new ItemStack(Items.field_151153_ao, 1, 1)));
        forInv.add(Arrays.asList(4, new ItemStack(ConfigItems.itemPickThaumium)));
        forInv.add(Arrays.asList(5, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1)));
        forInv.add(Arrays.asList(5, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1)));
        tradeInventory.put(0, forInv);
        ArrayList<List<Object>> forMag = new ArrayList<List<Object>>();
        forMag.add(Arrays.asList(1, new ItemStack(ConfigItems.itemManaBean)));
        for (a = 0; a < 6; ++a) {
            forMag.add(Arrays.asList(1, new ItemStack(ConfigItems.itemShard, 1, a)));
        }
        forMag.add(Arrays.asList(1, new ItemStack(ConfigItems.itemResource, 1, 9)));
        forMag.add(Arrays.asList(2, new ItemStack(ConfigItems.itemResource, 1, 9)));
        forMag.add(Arrays.asList(2, new ItemStack((Item)Items.field_151068_bn, 1, 8193)));
        forMag.add(Arrays.asList(2, new ItemStack((Item)Items.field_151068_bn, 1, 8261)));
        forMag.add(Arrays.asList(3, Items.field_151134_bR.func_92111_a(new EnchantmentData(Config.enchHaste, 1))));
        forMag.add(Arrays.asList(3, new ItemStack(Items.field_151153_ao, 1, 0)));
        forMag.add(Arrays.asList(3, new ItemStack((Item)Items.field_151068_bn, 1, 8225)));
        forMag.add(Arrays.asList(3, new ItemStack((Item)Items.field_151068_bn, 1, 8229)));
        for (a = 0; a < 7; ++a) {
            forMag.add(Arrays.asList(4, new ItemStack(ConfigBlocks.blockCrystal, 1, a)));
        }
        forMag.add(Arrays.asList(5, new ItemStack(Items.field_151153_ao, 1, 1)));
        forMag.add(Arrays.asList(5, Items.field_151134_bR.func_92111_a(new EnchantmentData(Config.enchRepair, 1))));
        forMag.add(Arrays.asList(5, new ItemStack(ConfigItems.itemFocusPouch)));
        forMag.add(Arrays.asList(5, new ItemStack(ConfigItems.itemFocusPech)));
        forMag.add(Arrays.asList(5, new ItemStack(ConfigItems.itemAmuletVis, 1, 0)));
        tradeInventory.put(1, forMag);
        ArrayList<List<Object>> forArc = new ArrayList<List<Object>>();
        forArc.add(Arrays.asList(1, new ItemStack(ConfigItems.itemManaBean)));
        for (int a2 = 0; a2 < 15; ++a2) {
            forArc.add(Arrays.asList(1, new ItemStack(ConfigBlocks.blockCandle, 1, a2)));
        }
        forArc.add(Arrays.asList(2, new ItemStack(Items.field_151073_bk)));
        forArc.add(Arrays.asList(2, new ItemStack((Item)Items.field_151068_bn, 1, 8194)));
        forArc.add(Arrays.asList(2, new ItemStack((Item)Items.field_151068_bn, 1, 8201)));
        forArc.add(Arrays.asList(2, Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77345_t, 1))));
        forArc.add(Arrays.asList(3, new ItemStack(Items.field_151062_by)));
        forArc.add(Arrays.asList(3, new ItemStack(ConfigItems.itemResource, 1, 9)));
        forArc.add(Arrays.asList(3, new ItemStack((Item)Items.field_151068_bn, 1, 8270)));
        forArc.add(Arrays.asList(3, new ItemStack((Item)Items.field_151068_bn, 1, 8225)));
        forArc.add(Arrays.asList(3, new ItemStack(Items.field_151153_ao, 1, 0)));
        forArc.add(Arrays.asList(5, new ItemStack(Items.field_151153_ao, 1, 1)));
        forArc.add(Arrays.asList(4, new ItemStack(ConfigItems.itemBootsThaumium)));
        forArc.add(Arrays.asList(5, new ItemStack(ConfigItems.itemRingRunic, 1, 0)));
        forArc.add(Arrays.asList(5, Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77343_v, 1))));
        forArc.add(Arrays.asList(5, Items.field_151134_bR.func_92111_a(new EnchantmentData(Enchantment.field_77342_w, 1))));
        tradeInventory.put(2, forArc);
    }
}

