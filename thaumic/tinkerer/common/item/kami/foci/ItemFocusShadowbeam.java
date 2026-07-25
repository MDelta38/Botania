/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.client.fx.particles.FXSparkle
 *  thaumcraft.codechicken.lib.vec.Vector3
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.kami.foci;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.foci.ItemFocusDeflect;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.foci.ItemModKamiFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemFocusShadowbeam
extends ItemModKamiFocus {
    AspectList cost = new AspectList().add(Aspect.ORDER, 25).add(Aspect.ENTROPY, 25).add(Aspect.AIR, 15);

    public ItemFocusShadowbeam() {
        EntityRegistry.registerModEntity(Beam.class, (String)"ShadowbeamStaffBeam", (int)0, (Object)ThaumicTinkerer.instance, (int)0, (int)0, (boolean)false);
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer player, int count) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (!player.field_70170_p.field_72995_K && wand.consumeAllVis(stack, player, this.getVisCost(stack), true, false)) {
            int potency = 0;
            if (player.field_70170_p.field_73012_v.nextInt(10) == 0) {
                player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:brain", 0.5f, 1.0f);
            }
            Beam beam = new Beam(player.field_70170_p, (EntityLivingBase)player, potency);
            beam.updateUntilDead();
        }
    }

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return true;
    }

    @Override
    protected boolean hasOrnament() {
        return true;
    }

    public int getFocusColor(ItemStack stack) {
        return 4915283;
    }

    public AspectList getVisCost(ItemStack stack) {
        return this.cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int rank) {
        return new FocusUpgradeType[0];
    }

    @Override
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    @Override
    public String getItemName() {
        return "focusShadowbeam";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("FOCUS_SHADOWBEAM", new AspectList().add(Aspect.DARKNESS, 2).add(Aspect.MAGIC, 1).add(Aspect.ELDRITCH, 1).add(Aspect.TAINT, 1), 14, 4, 5, new ItemStack((Item)this)).setParents(new String[]{"ROD_ICHORCLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_SHADOWBEAM")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_SHADOWBEAM", new ItemStack((Item)this), 12, new AspectList().add(Aspect.DARKNESS, 65).add(Aspect.ELDRITCH, 32).add(Aspect.MAGIC, 50).add(Aspect.WEAPON, 32), new ItemStack(ConfigItems.itemFocusShock), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(Items.field_151032_g), new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemFocusExcavation), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusDeflect.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)));
    }

    @Override
    public String getSortingHelper(ItemStack paramItemStack) {
        return "SHADOWBEAM";
    }

    public static class Beam
    extends EntityThrowable {
        private int initialOffset = 2;
        private int length = 298;
        private int maxTicks = this.initialOffset + this.length;
        private int size = 4;
        private int potency;
        private Vector3 movementVector;
        private EntityLivingBase player;

        public Beam(World world, EntityLivingBase player, int potency) {
            super(world, player);
            this.potency = potency;
            this.player = player;
            this.setProjectileVelocity(this.field_70159_w / 10.0, this.field_70181_x / 10.0, this.field_70179_y / 10.0);
            this.movementVector = new Vector3(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        }

        public void setProjectileVelocity(double par1, double par3, double par5) {
            this.field_70159_w = par1;
            this.field_70181_x = par3;
            this.field_70179_y = par5;
            if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
                float f = MathHelper.func_76133_a((double)(par1 * par1 + par5 * par5));
                this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
                this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, f) * 180.0 / Math.PI);
            }
        }

        public void func_70186_c(double par1, double par3, double par5, float par7, float par8) {
            super.func_70186_c(par1, par3, par5, par7, par8);
            float f2 = MathHelper.func_76133_a((double)(par1 * par1 + par3 * par3 + par5 * par5));
            par1 /= (double)f2;
            par3 /= (double)f2;
            par5 /= (double)f2;
            par1 += (double)0.0075f * (double)par8;
            par3 += (double)0.0075f * (double)par8;
            par5 += (double)0.0075f * (double)par8;
            this.field_70159_w = par1 *= (double)par7;
            this.field_70181_x = par3 *= (double)par7;
            this.field_70179_y = par5 *= (double)par7;
        }

        protected void func_70184_a(MovingObjectPosition movingobjectposition) {
            if (movingobjectposition == null) {
                return;
            }
            if (movingobjectposition.field_72308_g != null) {
                if ((MinecraftServer.func_71276_C().func_71219_W() || !(movingobjectposition.field_72308_g instanceof EntityPlayer)) && movingobjectposition.field_72308_g != this.func_85052_h() && this.func_85052_h() instanceof EntityPlayer && !movingobjectposition.field_72308_g.field_70170_p.field_72995_K) {
                    movingobjectposition.field_72308_g.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)this.func_85052_h())), (float)(8 + this.potency));
                }
                return;
            }
            Vector3 movementVec = new Vector3(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            ForgeDirection dir = ForgeDirection.getOrientation((int)movingobjectposition.field_72310_e);
            Vector3 normalVector = new Vector3((double)dir.offsetX, (double)dir.offsetY, (double)dir.offsetZ).normalize();
            this.movementVector = normalVector.multiply(-2.0 * movementVec.dotProduct(normalVector)).add(movementVec);
            this.field_70159_w = this.movementVector.x;
            this.field_70181_x = this.movementVector.y;
            this.field_70179_y = this.movementVector.z;
        }

        public void func_70071_h_() {
            this.field_70159_w = this.movementVector.x;
            this.field_70181_x = this.movementVector.y;
            this.field_70179_y = this.movementVector.z;
            super.func_70071_h_();
            if (this.field_70173_aa > this.initialOffset) {
                ThaumicTinkerer.proxy.shadowSparkle(this.field_70170_p, (float)this.field_70165_t, (float)this.field_70163_u, (float)this.field_70161_v, this.size);
            }
            ++this.field_70173_aa;
            if (this.field_70173_aa >= this.maxTicks) {
                this.func_70106_y();
            }
        }

        public void updateUntilDead() {
            while (!this.field_70128_L) {
                this.func_70071_h_();
            }
        }

        protected float func_70185_h() {
            return 0.0f;
        }
    }

    public static class Particle
    extends FXSparkle {
        public Particle(World world, double x, double y, double z, float scale, float red, float green, float blue, int maxAge) {
            super(world, x, y, z, scale, red, green, blue, 1);
            this.field_70547_e /= 3;
            this.field_70547_e = maxAge;
            this.shrink = false;
            this.blendmode = 0;
            this.multiplier = 1;
            this.particle = 1;
            this.slowdown = false;
            this.field_70145_X = true;
            this.setGravity(-0.7f);
        }
    }
}

