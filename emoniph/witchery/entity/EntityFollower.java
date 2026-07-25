/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIFollowOwner
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityLilith;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFollower
extends EntityTameable {
    private EntityAIWander aiWander = new EntityAIWander((EntityCreature)this, 0.8);
    int ticksToLive = -1;
    int transformCount;
    @SideOnly(value=Side.CLIENT)
    private ThreadDownloadImageData downloadImageSkin;
    @SideOnly(value=Side.CLIENT)
    private ResourceLocation locationSkin;
    private static final String[] FIRST_NAMES_M = new String[]{"Abraham", "Adam", "Adrian", "Alex", "Alexander", "Allen", "Ambrose", "Andrew", "Anthony", "Arthur", "Avery", "Barnaby", "Bartholomew", "Benedict", "Benjamin", "Bernard", "Billy", "Bobby", "Charles", "Charley", "Christopher", "Colin", "Conrad", "Cuthbert", "Daniel", "Danny", "Davey", "David", "Edmund", "Edward", "Francis", "Fred", "Freddy", "Geoffrey", "George", "Georgie", "Gerard", "Gilbert", "Giles", "Gregory", "Hans", "Hansel", "Heinrich", "Henry", "Hugh", "Humphrey", "Isaac", "Jack", "Jacques", "James", "Jamie", "Jerome", "Jim", "Jimmy", "John", "Johnny", "Joseph", "Julian", "Lancelot", "Lawrence", "Leonard", "Lou", "Luke", "Mark", "Martin", "Mathias", "Matthew", "Merlin", "Michael", "Miles", "Nat", "Nathan", "Nathaniel", "Ned", "Nicholas", "Oliver", "Oswyn", "Patrick", "Paul", "Peter", "Philip", "Piers", "Ralph", "Reynold", "Richard", "Ricky", "Robert", "Robin", "Roger", "Rowland", "Samuel", "Simon", "Solomon", "Stephen", "Terence", "Thomas", "Tim", "Tobias", "Tom", "Tommy", "Valentine", "Walter", "Wendell", "Will", "William", "Willie"};
    private static final String[] FIRST_NAMES_F = new String[]{"Agnes", "Alice", "Amy", "Anna", "Annabella", "Anne", "Arabella", "Audrey", "Avis", "Barbara", "Beatrice", "Becky", "Bella", "Belle", "Bertha", "Bessy", "Betty", "Blanche", "Bo", "Bonny", "Bridget", "Catalina", "Catherine", "Cecily", "Charity", "Charlotte", "Christina", "Christine", "Cinderella", "Cindy", "Clara", "Clarissa", "Clemence", "Clementine", "Constance", "Daisy", "Denise", "Dorothy", "Edith", "Elinor", "Elizabeth", "Ella", "Ellen", "Elsa", "Elsie", "Emma", "Eve", "Evelyn", "Fawn", "Flora", "Florence", "Floretta", "Fortune", "Frances", "Frideswide", "Gertrude", "Gillian", "Ginger", "Goat", "Goatley", "Goldie", "Grace", "Gretel", "Helen", "Hilda", "Hazel", "Isabel", "Jane", "Janet", "Jemima", "Jill", "Joan", "Joyce", "Judith", "Julia", "Juliet", "Katherine", "Katie", "Kitty", "Lena", "Lily", "Liza", "Lizzie", "Lucy", "Mabel", "Maggie", "Margaret", "Margery", "Maria", "Marion", "Marlene", "Martha", "Mary", "Maud", "Mildred", "Millicent", "Molly", "Odette", "Pansy", "Pearl", "Petunia", "Philippa", "Polly", "Rachel", "Rapunzel", "Rebecca", "Rose", "Rosie", "Ruth", "Sarah", "Shiela", "Snow", "Susanna", "Susie", "Sybil", "Talia", "Thomasina", "Trudy", "Ursula", "Winifred"};
    private static final String[] SURNAMES = new String[]{"Apple", "Applegreen", "Applerose", "Appleseed", "Appleton", "Applewhite", "Baker", "Barnes", "Bean", "Beanblossom", "Beanstock", "Beaste", "Beasten", "Bell", "Berry", "Bird", "Blackbird", "Blackwood", "Boar", "Botter", "Bowers", "Bremen", "Brockett", "Buckle", "Butcher", "Candle", "Castle", "Castleton", "Cherry", "Cherrytree", "Cherrywood", "Cherrywell", "Cottage", "Daw", "Deer", "Dilly", "Dove", "Duck", "Duckfield", "Duckling", "Faery", "Fairy", "Fiddle", "Fiddler", "Fisher", "Fitcher", "Flinders", "Friday", "Frogg", "Frogley", "Frost", "Gold", "Goldencrown", "Goldhair", "Goodfellow", "Goose", "Gooseberry", "Gosling", "Gray", "Green", "Greengrass", "Griggs", "Grimm", "Grundy", "Hare", "Hay", "Hazeltree", "Hickory", "Hood", "Horner", "Hubbard", "Hunter", "Korbes", "Lamb", "Lampkin", "Lark", "Locket", "Locks", "MacDonald", "Mack", "Malone", "Marsh", "McDiddler", "Meadow", "Meadows", "Merrypips", "Miller", "Mills", "Mockingbird", "Monday", "Mouse", "Mouseley", "Muffet", "Mulberry", "Nimble", "Nutt", "O'Hare", "O'Lairy", "Pea", "Peartree", "Pease", "Peep", "Pie", "Pigeon", "Pinchme", "Piper", "Porgy", "Porridge", "Pott", "Pumpkin", "Pumpkinseed", "Reynard", "River", "Rivers", "Roley", "Rooster", "Roseberry", "Rosebottom", "Roseleaf", "Shoe", "Shoemaker", "Shorter", "Silver", "Slipper", "Sprat", "Saturday", "Sparrow", "Spindle", "Spindler", "Spinner", "Star", "Stone", "Stonebridge", "Sunday", "Swan", "Tailor", "Thatcher", "Thumb", "Thursday", "Toad", "Tower", "Towers", "Trot", "Tucker", "Tuesday", "Twist", "Wednesday", "White", "Whittington", "Winkie", "Wolf", "Wolfram", "Wolfson", "Wolfwood", "Woodcroft", "Woods"};

    public EntityFollower(World world) {
        super(world);
        this.func_70105_a(0.6f, 1.8f);
        this.func_70661_as().func_75495_e(true);
        this.func_70661_as().func_75491_a(false);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 2.0));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 2.0f, 4.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70728_aV = 0;
    }

    protected int func_70693_a(EntityPlayer p_70693_1_) {
        return 0;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
    }

    public void setTTL(int ticks) {
        this.ticksToLive = ticks;
    }

    public EntityAgeable func_90011_a(EntityAgeable lover) {
        return null;
    }

    public String func_70005_c_() {
        switch (this.getFollowerType()) {
            case 0: {
                return Witchery.resource("entity.witchery.follower.elle.name");
            }
        }
        return super.func_70005_c_();
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, (Object)0);
        this.field_70180_af.func_75682_a(19, (Object)String.valueOf(""));
    }

    public String getSkin() {
        return this.field_70180_af.func_75681_e(19);
    }

    public void setSkin(String mode) {
        this.field_70180_af.func_75692_b(19, (Object)mode);
    }

    public int getFollowerType() {
        return this.field_70180_af.func_75679_c(18);
    }

    public void func_70030_z() {
        EntityPlayer player;
        String skin;
        super.func_70030_z();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa == 1 && this.getFollowerType() == 5 && (skin = this.getSkin()) != null && !skin.isEmpty() && (player = this.field_70170_p.func_72924_a(this.getSkin())) != null) {
            for (int i = 0; i <= 4; ++i) {
                ItemStack stack = player.func_71124_b(i);
                if (stack != null) {
                    this.func_70062_b(i, stack.func_77946_l());
                    continue;
                }
                this.func_70062_b(i, null);
            }
        }
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 40 == 5 && this.getFollowerType() == 5) {
            this.attractAttention();
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.func_70089_S() && this.ticksToLive >= 0 && --this.ticksToLive == 0) {
            this.func_70106_y();
        }
    }

    public void setFollowerType(int followerType) {
        this.field_70180_af.func_75692_b(18, (Object)followerType);
        if (followerType == 0) {
            this.field_70178_ae = true;
        } else if (followerType <= 5) {
            this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.aiWander);
        }
    }

    protected int func_70682_h(int par1) {
        if (this.getFollowerType() == 0) {
            return par1;
        }
        return super.func_70682_h(par1);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (this.getFollowerType() >= 1 && this.getFollowerType() <= 4) {
            this.func_70099_a(Witchery.Items.GENERIC.itemHeartOfGold.createStack(), 0.1f);
        }
    }

    private boolean isCourseTraversable(double waypointX, double waypointY, double waypointZ, double p_70790_7_) {
        double d4 = (waypointX - this.field_70165_t) / p_70790_7_;
        double d5 = (waypointY - this.field_70163_u) / p_70790_7_;
        double d6 = (waypointZ - this.field_70161_v) / p_70790_7_;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
        int i = 1;
        while ((double)i < p_70790_7_) {
            axisalignedbb.func_72317_d(d4, d5, d6);
            if (!this.field_70170_p.func_72945_a((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
            ++i;
        }
        return true;
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 10 == 1 && this.getFollowerType() == 0) {
            this.doElleAI();
        }
    }

    public void doElleAI() {
        block6: {
            block3: {
                double waypointZ;
                double waypointY;
                block4: {
                    block5: {
                        if (!this.func_110175_bO()) break block3;
                        if (!this.func_110173_bK()) break block4;
                        ++this.transformCount;
                        if (this.transformCount != 20) break block5;
                        EntityLivingBase owner = this.func_70902_q();
                        if (owner == null || !(owner instanceof EntityPlayer)) break block6;
                        ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, (ICommandSender)((EntityPlayer)owner), "item.witchery:glassgoblet.lilithquestsummon", new Object[0]);
                        SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt((EntityLiving)this);
                        break block6;
                    }
                    if (this.transformCount != 40) break block6;
                    this.transformCount = 0;
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.FIREWORKS_BLAST1, (Entity)this, 1.0, 1.0, 16);
                    EntityLilith vampire = new EntityLilith(this.field_70170_p);
                    vampire.func_110163_bv();
                    vampire.func_82149_j((Entity)this);
                    vampire.func_110161_a(null);
                    this.field_70170_p.func_72900_e((Entity)this);
                    this.field_70170_p.func_72838_d((Entity)vampire);
                    vampire.field_70170_p.func_72889_a(null, 1017, (int)vampire.field_70165_t, (int)vampire.field_70163_u, (int)vampire.field_70161_v, 0);
                    EntityLivingBase owner = this.func_70902_q();
                    if (owner != null && owner instanceof EntityPlayer) {
                        ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, (ICommandSender)((EntityPlayer)owner), "item.witchery:glassgoblet.lilithquestsummon2", new Object[0]);
                        SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt((EntityLiving)vampire);
                    }
                    this.field_70170_p.func_72876_a((Entity)vampire, vampire.field_70165_t, vampire.field_70163_u, vampire.field_70161_v, 6.0f, true);
                    break block6;
                }
                double d0 = (double)this.func_110172_bL().field_71574_a - this.field_70165_t;
                double d1 = (double)this.func_110172_bL().field_71572_b - this.field_70163_u;
                double d2 = (double)this.func_110172_bL().field_71573_c - this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (!(d3 > 0.0)) break block6;
                d3 = MathHelper.func_76133_a((double)d3);
                double waypointX = this.field_70165_t + (double)((this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0f);
                if (this.isCourseTraversable(waypointX, waypointY = this.field_70163_u + (double)((this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0f), waypointZ = this.field_70161_v + (double)((this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0f), d3)) {
                    this.field_70159_w += d0 / d3 * 0.2;
                    this.field_70181_x += d1 / d3 * 0.2;
                    this.field_70179_y += d2 / d3 * 0.2;
                }
                break block6;
            }
            for (int i = 0; i < 10; ++i) {
                int l;
                int k;
                int j = MathHelper.func_76128_c((double)(this.field_70165_t + (double)this.field_70146_Z.nextInt(30) - 15.0));
                if (!EntityFollower.isLavaPool(this.field_70170_p, j, k = MathHelper.func_76128_c((double)(this.field_70121_D.field_72338_b + (double)this.field_70146_Z.nextInt(6) - 3.0)), l = MathHelper.func_76128_c((double)(this.field_70161_v + (double)this.field_70146_Z.nextInt(30) - 15.0)), 6)) continue;
                this.func_110171_b(j, k, l, 2);
                this.func_152115_b("");
                break;
            }
        }
    }

    private static boolean isLavaPool(World world, int x, int y, int z, int max) {
        if (EntityFollower.isLavaPoolColumn(world, x, y, z) && EntityFollower.isLavaPoolColumn(world, x + 1, y, z) && EntityFollower.isLavaPoolColumn(world, x - 1, y, z) && EntityFollower.isLavaPoolColumn(world, x, y, z + 1) && EntityFollower.isLavaPoolColumn(world, x, y, z - 1)) {
            int max2 = max * max;
            for (int dx = x - max; dx <= x + max; ++dx) {
                for (int dz = z - max; dz <= z + max; ++dz) {
                    double dist = Coord.distanceSq(x, y, z, dx, y, dz);
                    if (!(dist <= (double)max2) || world.func_147439_a(dx, y, dz) == Blocks.field_150353_l) continue;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isLavaPoolColumn(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) == Blocks.field_150353_l && world.func_147437_c(x, y + 1, z) && world.func_147437_c(x, y + 2, z)) {
            int depth = 4;
            for (int dy = y - depth; dy < dy; ++dy) {
                if (world.func_147439_a(x, y, z) == Blocks.field_150353_l) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public void setOwner(EntityPlayer player) {
        this.func_70903_f(true);
        this.func_70778_a(null);
        this.func_70624_b(null);
        this.func_70606_j(20.0f);
        this.func_152115_b(player.func_110124_au().toString());
        this.field_70170_p.func_72960_a((Entity)this, (byte)7);
    }

    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        this.setFollowerType(nbtRoot.func_74762_e("FollowerType"));
        this.ticksToLive = nbtRoot.func_74764_b("TicksToLive") ? nbtRoot.func_74762_e("TicksToLive") : -1;
        if (nbtRoot.func_74764_b("Skin")) {
            this.setSkin(nbtRoot.func_74779_i("Skin"));
        }
    }

    public void func_70109_d(NBTTagCompound nbtRoot) {
        super.func_70109_d(nbtRoot);
        nbtRoot.func_74768_a("FollowerType", this.getFollowerType());
        nbtRoot.func_74768_a("TicksToLive", this.ticksToLive);
        nbtRoot.func_74778_a("Skin", this.getSkin());
    }

    public static String generateFollowerName(int followerType) {
        Random ra = new Random();
        if (followerType != 4) {
            return String.format("%s %s", FIRST_NAMES_F[ra.nextInt(FIRST_NAMES_F.length)], SURNAMES[ra.nextInt(SURNAMES.length)]);
        }
        return String.format("%s %s", FIRST_NAMES_M[ra.nextInt(FIRST_NAMES_M.length)], SURNAMES[ra.nextInt(SURNAMES.length)]);
    }

    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getLocationSkin() {
        if (this.locationSkin == null) {
            this.setupCustomSkin();
        }
        if (this.locationSkin != null) {
            return this.locationSkin;
        }
        return AbstractClientPlayer.field_110314_b;
    }

    @SideOnly(value=Side.CLIENT)
    private void setupCustomSkin() {
        String owner = this.getSkin();
        if (owner != null && !owner.isEmpty()) {
            this.locationSkin = AbstractClientPlayer.func_110311_f((String)owner);
            this.downloadImageSkin = AbstractClientPlayer.func_110304_a((ResourceLocation)this.locationSkin, (String)owner);
        }
    }

    public void attractAttention() {
        EntityPlayer player;
        String owner;
        if (!this.field_70170_p.field_72995_K && (owner = this.getSkin()) != null && !owner.isEmpty() && (player = this.field_70170_p.func_72924_a(owner)) != null) {
            List list = this.field_70170_p.func_72872_a(EntityMob.class, this.field_70121_D.func_72314_b(16.0, 8.0, 16.0));
            for (EntityMob mob : list) {
                if (mob.func_70638_az() != player) continue;
                mob.func_70604_c((EntityLivingBase)this);
                mob.func_70624_b((EntityLivingBase)this);
                mob.func_70784_b((Entity)this);
            }
        }
    }
}

