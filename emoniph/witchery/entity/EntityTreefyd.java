/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityFlying
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityMooshroom
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityFlyingTameable;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.ai.EntityAITreefydWander;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityTreefyd
extends EntityMob
implements IEntitySelector {
    public CreatureID testID = new CreatureID(new UUID(0L, 0L), "");
    private static ArrayList<String> groupables = null;
    private ArrayList<String> knownPlayers = new ArrayList();
    private ArrayList<String> knownCreatureTypes = new ArrayList();
    private ArrayList<CreatureID> knownCreatures = new ArrayList();

    public EntityTreefyd(World par1World) {
        super(par1World);
        this.func_70105_a(0.4f, 1.8f);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAITreefydWander(this, 0.8));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLivingBase.class, 0, false, true, (IEntitySelector)this));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    }

    public boolean func_82704_a(Entity entity) {
        if (entity.getClass() == ((Object)((Object)this)).getClass() || entity instanceof EntityHornedHuntsman || entity instanceof EntityEnt || entity instanceof EntityFlying || entity instanceof EntityFlyingTameable || entity instanceof EntityAmbientCreature || entity instanceof EntityWaterMob || this.isFamiliar(entity) || entity instanceof EntityCovenWitch || entity instanceof EntityCorpse) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            String ownerName = this.getOwnerName();
            if (ownerName != null && !ownerName.isEmpty() && player.func_70005_c_().equals(ownerName)) {
                return false;
            }
            if (this.knownPlayers != null && this.knownPlayers.contains(player.func_70005_c_())) {
                return false;
            }
        }
        if (entity instanceof EntityLiving) {
            EntityLiving creature = (EntityLiving)entity;
            if (this.knownCreatureTypes != null && this.knownCreatureTypes.contains(creature.func_70005_c_())) {
                return false;
            }
            this.testID.id = entity.func_110124_au();
            if (this.knownCreatures != null && this.knownCreatures.contains(this.testID)) {
                return false;
            }
        }
        return true;
    }

    public int func_70627_aG() {
        return super.func_70627_aG() * 2;
    }

    /*
     * Unable to fully structure code
     */
    protected boolean func_70085_c(EntityPlayer player) {
        block33: {
            block35: {
                block34: {
                    if (this.field_70170_p.field_72995_K || player == null || !player.func_70005_c_().equals(this.getOwnerName())) break block33;
                    stack = player.func_70694_bm();
                    if (stack == null || stack.func_77973_b() != Witchery.Items.TAGLOCK_KIT) break block34;
                    this.func_70624_b(null);
                    boundEntityType = Witchery.Items.TAGLOCK_KIT.getBoundEntityType(stack, 1);
                    switch (1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[boundEntityType.ordinal()]) {
                        case 1: {
                            otherUsername = Witchery.Items.TAGLOCK_KIT.getBoundUsername(stack, 1);
                            if (player.func_70005_c_().equals(otherUsername)) break;
                            if (!player.func_70093_af() && !this.knownPlayers.contains(otherUsername)) {
                                this.knownPlayers.add(otherUsername);
                            } else if (player.func_70093_af() && this.knownPlayers.contains(otherUsername)) {
                                this.knownPlayers.remove(otherUsername);
                            } else {
                                this.showCurrentKnownEntities(player);
                                return super.func_70085_c(player);
                            }
                            --stack.field_77994_a;
                            if (stack.field_77994_a <= 0) {
                                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                            }
                            if (player instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                            }
                            this.showCurrentKnownEntities(player);
                            return true;
                        }
                        case 2: {
                            otherCreature = Witchery.Items.TAGLOCK_KIT.getBoundCreatureID(stack, 1);
                            creatureName = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 1);
                            if (otherCreature.equals(this.func_110124_au())) ** GOTO lbl58
                            if (!this.isGroupableCreature(otherCreature, creatureName)) ** GOTO lbl42
                            if (!player.func_70093_af() && !this.knownCreatureTypes.contains(creatureName)) {
                                this.knownCreatureTypes.add(creatureName);
                            } else if (player.func_70093_af() && this.knownCreatureTypes.contains(creatureName)) {
                                this.knownCreatureTypes.remove(creatureName);
                            } else {
                                this.showCurrentKnownEntities(player);
                                return super.func_70085_c(player);
lbl42:
                                // 1 sources

                                creatureID = new CreatureID(otherCreature, creatureName);
                                if (!player.func_70093_af() && !this.knownCreatures.contains(creatureID)) {
                                    this.knownCreatures.add(creatureID);
                                } else if (player.func_70093_af() && this.knownCreatures.contains(creatureID)) {
                                    this.knownCreatures.remove(creatureID);
                                } else {
                                    this.showCurrentKnownEntities(player);
                                    return super.func_70085_c(player);
                                }
                            }
                            --stack.field_77994_a;
                            if (stack.field_77994_a <= 0) {
                                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                            }
                            if (player instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                            }
lbl58:
                            // 4 sources

                            this.showCurrentKnownEntities(player);
                            return true;
                        }
                    }
                    break block35;
                }
                if (stack != null && Witchery.Items.GENERIC.itemSeedsTreefyd.isMatch(stack)) {
                    if (!this.field_70170_p.field_72995_K) {
                        entity = new EntityTreefyd(this.field_70170_p);
                        entity.func_70012_b(0.5 + this.field_70165_t, this.field_70163_u, 0.5 + this.field_70161_v, 0.0f, 0.0f);
                        entity.func_110161_a(null);
                        entity.setOwner(player.func_70005_c_());
                        entity.func_110163_bv();
                        entity.knownPlayers = (ArrayList)this.knownPlayers.clone();
                        entity.knownCreatureTypes = (ArrayList)this.knownCreatureTypes.clone();
                        entity.knownCreatures = (ArrayList)this.knownCreatures.clone();
                        this.field_70170_p.func_72838_d((Entity)entity);
                        ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, (Entity)this, 1.0, 2.0, 16);
                        ParticleEffect.EXPLODE.send(SoundEffect.NONE, (Entity)this, 1.0, 2.0, 16);
                    }
                    if (!player.field_71075_bZ.field_75098_d) {
                        --stack.field_77994_a;
                    }
                } else if (stack != null && Witchery.Items.GENERIC.itemCreeperHeart.isMatch(stack)) {
                    if (!this.field_70170_p.field_72995_K) {
                        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0);
                        this.func_70606_j(this.func_110138_aP());
                        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
                        ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, (Entity)this, 0.5, 2.0, 16);
                    }
                    if (!player.field_71075_bZ.field_75098_d) {
                        --stack.field_77994_a;
                    }
                } else if (stack != null && Witchery.Items.GENERIC.itemDemonHeart.isMatch(stack)) {
                    if (!this.field_70170_p.field_72995_K) {
                        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(150.0);
                        this.func_70606_j(this.func_110138_aP());
                        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0);
                        ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, (Entity)this, 0.5, 2.0, 16);
                    }
                    if (!player.field_71075_bZ.field_75098_d) {
                        --stack.field_77994_a;
                    }
                } else if (stack != null && stack.func_77973_b() == Witchery.Items.BOLINE && !this.field_70170_p.field_72995_K) {
                    this.setSentinal(this.isSentinal() == false);
                }
            }
            this.showCurrentKnownEntities(player);
        }
        return super.func_70085_c(player);
    }

    private boolean isGroupableCreature(UUID otherCreature, String creatureName) {
        if (groupables == null) {
            groupables = new ArrayList();
            this.addGroupableType(EntityVillager.class);
            this.addGroupableType(EntityGoblin.class);
            this.addGroupableType(EntitySheep.class);
            this.addGroupableType(EntityCow.class);
            this.addGroupableType(EntityMooshroom.class);
            this.addGroupableType(EntityChicken.class);
            this.addGroupableType(EntityPig.class);
            this.addGroupableType(EntityHorse.class);
        }
        return groupables.contains(creatureName);
    }

    private void addGroupableType(Class<? extends EntityLiving> className) {
        String name = (String)EntityList.field_75626_c.get(className);
        if (name != null) {
            String localName = StatCollector.func_74838_a((String)("entity." + name + ".name"));
            groupables.add(localName);
        }
    }

    private void showCurrentKnownEntities(EntityPlayer player) {
        StringBuffer sb = new StringBuffer();
        String ownerName = this.getOwnerName();
        if (ownerName != null && !ownerName.isEmpty()) {
            sb.append(this.getOwnerName());
        }
        for (String s : this.knownPlayers) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(s);
        }
        for (String s : this.knownCreatureTypes) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append("#");
            sb.append(s);
        }
        for (CreatureID cid : this.knownCreatures) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(cid.toString());
        }
        String message = this.func_70005_c_() + " (" + sb.toString() + ")";
        ChatUtil.sendPlain((ICommandSender)player, message);
    }

    private boolean isFamiliar(Entity entity) {
        if (entity instanceof IFamiliar) {
            IFamiliar familiar = (IFamiliar)entity;
            return familiar.isFamiliar();
        }
        return false;
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.treefyd.name");
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0);
    }

    public boolean func_70650_aV() {
        return true;
    }

    public boolean func_70652_k(Entity entity) {
        EntityPlayer player;
        if (!this.field_70170_p.field_72995_K && entity instanceof EntityPlayer && !(player = (EntityPlayer)entity).func_70644_a(Potion.field_76440_q)) {
            player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 100, 0));
        }
        return super.func_70652_k(entity);
    }

    public int func_82143_as() {
        return this.func_70638_az() == null ? 3 : 3 + (int)(this.func_110143_aJ() - 1.0f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, (Object)"");
        this.field_70180_af.func_75682_a(18, (Object)0);
    }

    public boolean isSentinal() {
        return this.field_70180_af.func_75679_c(18) == 1;
    }

    protected void setSentinal(boolean screaming) {
        this.field_70180_af.func_75692_b(18, (Object)(screaming ? 1 : 0));
    }

    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        if (this.getOwnerName() == null) {
            nbtRoot.func_74778_a("Owner", "");
        } else {
            nbtRoot.func_74778_a("Owner", this.getOwnerName());
        }
        if (this.knownPlayers.size() > 0) {
            NBTTagList nbtPlayers = new NBTTagList();
            for (String playerName : this.knownPlayers) {
                NBTTagCompound nbtKnownPlayer = new NBTTagCompound();
                nbtKnownPlayer.func_74778_a("PlayerName", playerName);
                nbtPlayers.func_74742_a((NBTBase)nbtKnownPlayer);
            }
            nbtRoot.func_74782_a("KnownPlayers", (NBTBase)nbtPlayers);
        }
        if (this.knownCreatureTypes.size() > 0) {
            NBTTagList nbtCreatureTypes = new NBTTagList();
            for (String typeName : this.knownCreatureTypes) {
                NBTTagCompound nbtKnownCreatureType = new NBTTagCompound();
                nbtKnownCreatureType.func_74778_a("CreatureTypeName", typeName);
                nbtCreatureTypes.func_74742_a((NBTBase)nbtKnownCreatureType);
            }
            nbtRoot.func_74782_a("KnownCreatureTypes", (NBTBase)nbtCreatureTypes);
        }
        if (this.knownCreatures.size() > 0) {
            NBTTagList nbtCreatures = new NBTTagList();
            for (CreatureID creatureID : this.knownCreatures) {
                NBTTagCompound nbtKnownCreature = new NBTTagCompound();
                nbtKnownCreature.func_74772_a("CreatureMost", creatureID.id.getMostSignificantBits());
                nbtKnownCreature.func_74772_a("CreatureLeast", creatureID.id.getLeastSignificantBits());
                nbtKnownCreature.func_74778_a("CreatureName", creatureID.name);
                nbtCreatures.func_74742_a((NBTBase)nbtKnownCreature);
            }
            nbtRoot.func_74782_a("KnownCreatures", (NBTBase)nbtCreatures);
        }
        nbtRoot.func_74757_a("SentinalPlant", this.isSentinal());
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        String playerName;
        int i;
        super.func_70037_a(nbtRoot);
        String s = nbtRoot.func_74779_i("Owner");
        if (s.length() > 0) {
            this.setOwner(s);
        }
        if (nbtRoot.func_74764_b("KnownPlayers")) {
            NBTTagList nbtPlayers = nbtRoot.func_150295_c("KnownPlayers", 10);
            this.knownPlayers = new ArrayList();
            for (i = 0; i < nbtPlayers.func_74745_c(); ++i) {
                NBTTagCompound nbtKnownPlayer = nbtPlayers.func_150305_b(i);
                playerName = nbtKnownPlayer.func_74779_i("PlayerName");
                if (playerName == null || playerName.isEmpty()) continue;
                this.knownPlayers.add(playerName);
            }
        }
        if (nbtRoot.func_74764_b("KnownCreatureTypes")) {
            NBTTagList nbtCreatureTypes = nbtRoot.func_150295_c("KnownCreatureTypes", 10);
            this.knownCreatureTypes = new ArrayList();
            for (i = 0; i < nbtCreatureTypes.func_74745_c(); ++i) {
                NBTTagCompound nbtKnownCreatureType = nbtCreatureTypes.func_150305_b(i);
                String typeName = nbtKnownCreatureType.func_74779_i("CreatureTypeName");
                if (typeName == null || typeName.isEmpty()) continue;
                this.knownCreatureTypes.add(typeName);
            }
        }
        if (nbtRoot.func_74764_b("KnownCreatures")) {
            NBTTagList nbtCreatures = nbtRoot.func_150295_c("KnownCreatures", 10);
            this.knownCreatures = new ArrayList();
            for (i = 0; i < nbtCreatures.func_74745_c(); ++i) {
                NBTTagCompound nbtKnownCreature = nbtCreatures.func_150305_b(i);
                playerName = nbtKnownCreature.func_74779_i("PlayerName");
                long uuidMost = nbtKnownCreature.func_74763_f("CreatureMost");
                long uuidLeast = nbtKnownCreature.func_74763_f("CreatureLeast");
                String cname = nbtKnownCreature.func_74779_i("CreatureName");
                if (uuidMost == 0L && uuidLeast == 0L) continue;
                UUID creatureID = new UUID(uuidMost, uuidLeast);
                this.knownCreatures.add(new CreatureID(creatureID, cname));
            }
        }
        if (nbtRoot.func_74764_b("SentinalPlant")) {
            this.setSentinal(nbtRoot.func_74767_n("SentinalPlant"));
        }
    }

    public String getOwnerName() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setOwner(String par1Str) {
        this.func_110163_bv();
        this.field_70180_af.func_75692_b(17, (Object)par1Str);
    }

    public EntityPlayer getOwnerEntity() {
        return this.field_70170_p.func_72924_a(this.getOwnerName());
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    protected String func_70621_aR() {
        return "mob.silverfish.hit";
    }

    protected String func_70639_aQ() {
        return "witchery:mob.treefyd.treefyd_say";
    }

    protected String func_70673_aS() {
        return "mob.creeper.death";
    }

    protected Item func_146068_u() {
        return Item.func_150898_a((Block)Blocks.field_150328_O);
    }

    protected void func_70600_l(int par1) {
        this.func_70099_a(Witchery.Items.GENERIC.itemSeedsTreefyd.createStack(), 0.0f);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    static class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType;

        static {
            $SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType = new int[ItemTaglockKit.BoundType.values().length];
            try {
                1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[ItemTaglockKit.BoundType.PLAYER.ordinal()] = 1;
            }
            catch (NoSuchFieldError ex) {
                // empty catch block
            }
            try {
                1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[ItemTaglockKit.BoundType.CREATURE.ordinal()] = 2;
            }
            catch (NoSuchFieldError ex) {
                // empty catch block
            }
            try {
                1.$SwitchMap$com$emoniph$witchery$item$ItemTaglockKit$BoundType[ItemTaglockKit.BoundType.NONE.ordinal()] = 3;
            }
            catch (NoSuchFieldError noSuchFieldError) {
                // empty catch block
            }
        }
    }

    private static class CreatureID {
        UUID id;
        String name;

        public CreatureID(UUID id, String name) {
            this.id = id;
            this.name = name;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj instanceof UUID) {
                return this.id.equals((UUID)obj);
            }
            if (obj.getClass() == this.getClass()) {
                return this.id.equals(((CreatureID)obj).id);
            }
            return false;
        }

        public String toString() {
            return this.name;
        }
    }
}

