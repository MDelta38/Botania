/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.client.fx.bolt.FXLightningBolt;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.projectile.EntityShockOrb;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXZap;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemFocusShock
extends ItemFocusBasic {
    private static final AspectList costBase = new AspectList().add(Aspect.AIR, 25);
    private static final AspectList costChain = new AspectList().add(Aspect.AIR, 40).add(Aspect.WATER, 10);
    private static final AspectList costGround = new AspectList().add(Aspect.AIR, 75).add(Aspect.EARTH, 25);
    public static FocusUpgradeType chainlightning = new FocusUpgradeType(17, new ResourceLocation("thaumcraft", "textures/foci/chainlightning.png"), "focus.upgrade.chainlightning.name", "focus.upgrade.chainlightning.text", new AspectList().add(Aspect.WEATHER, 1));
    public static FocusUpgradeType earthshock = new FocusUpgradeType(18, new ResourceLocation("thaumcraft", "textures/foci/earthshock.png"), "focus.upgrade.earthshock.name", "focus.upgrade.earthshock.text", new AspectList().add(Aspect.WEATHER, 1));

    public ItemFocusShock() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_shock");
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BL" + super.getSortingHelper(itemstack);
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 10466239;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, chainlightning) ? costChain : (this.isUpgradedWith(itemstack, earthshock) ? costGround : costBase);
    }

    @Override
    public int getActivationCooldown(ItemStack focusstack) {
        return this.isUpgradedWith(focusstack, chainlightning) ? 500 : (this.isUpgradedWith(focusstack, earthshock) ? 1000 : 250);
    }

    @Override
    public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, earthshock) ? ItemFocusBasic.WandFocusAnimation.WAVE : ItemFocusBasic.WandFocusAnimation.CHARGE;
    }

    public static void shootLightning(World world, EntityLivingBase entityplayer, double xx, double yy, double zz, boolean offset) {
        double px = entityplayer.field_70165_t;
        double py = entityplayer.field_70163_u;
        double pz = entityplayer.field_70161_v;
        if (entityplayer.func_145782_y() != FMLClientHandler.instance().getClient().field_71439_g.func_145782_y()) {
            py = entityplayer.field_70121_D.field_72338_b + (double)(entityplayer.field_70131_O / 2.0f) + 0.25;
        }
        px += (double)(-MathHelper.func_76134_b((float)(entityplayer.field_70177_z / 180.0f * 3.141593f)) * 0.06f);
        py += (double)-0.06f;
        pz += (double)(-MathHelper.func_76126_a((float)(entityplayer.field_70177_z / 180.0f * 3.141593f)) * 0.06f);
        if (entityplayer.func_145782_y() != FMLClientHandler.instance().getClient().field_71439_g.func_145782_y()) {
            py = entityplayer.field_70121_D.field_72338_b + (double)(entityplayer.field_70131_O / 2.0f) + 0.25;
        }
        Vec3 vec3d = entityplayer.func_70676_i(1.0f);
        FXLightningBolt bolt = new FXLightningBolt(world, px += vec3d.field_72450_a * 0.3, py += vec3d.field_72448_b * 0.3, pz += vec3d.field_72449_c * 0.3, xx, yy, zz, world.field_73012_v.nextLong(), 6, 0.5f, 8);
        bolt.defaultFractal();
        bolt.setType(2);
        bolt.setWidth(0.125f);
        bolt.finalizeBolt();
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (this.isUpgradedWith(wand.getFocusItem(itemstack), earthshock)) {
            if (wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), !p.field_70170_p.field_72995_K, false)) {
                if (!world.field_72995_K) {
                    EntityShockOrb orb = new EntityShockOrb(world, (EntityLivingBase)p);
                    orb.area += this.getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.enlarge) * 2;
                    orb.damage = (int)((double)orb.damage + (double)wand.getFocusPotency(itemstack) * 1.33);
                    world.func_72838_d((Entity)orb);
                    world.func_72956_a((Entity)orb, "thaumcraft:zap", 1.0f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2f);
                }
                p.func_71038_i();
            }
        } else {
            p.func_71008_a(itemstack, Integer.MAX_VALUE);
            WandManager.setCooldown((EntityLivingBase)p, -1);
        }
        return itemstack;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count) {
        this.doLightningBolt(stack, p, count);
    }

    public void doLightningBolt(ItemStack stack, EntityPlayer p, int count) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), !p.field_70170_p.field_72995_K, false)) {
            p.func_71034_by();
            return;
        }
        int potency = wand.getFocusPotency(stack);
        Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, (Entity)p, 0.0, 20.0, 1.1f);
        if (p.field_70170_p.field_72995_K) {
            int a;
            MovingObjectPosition mop = BlockUtils.getTargetBlock(p.field_70170_p, (Entity)p, false);
            Vec3 v = p.func_70676_i(2.0f);
            double px = p.field_70165_t + v.field_72450_a * 10.0;
            double py = p.field_70163_u + v.field_72448_b * 10.0;
            double pz = p.field_70161_v + v.field_72449_c * 10.0;
            if (mop != null) {
                px = mop.field_72307_f.field_72450_a;
                py = mop.field_72307_f.field_72448_b;
                pz = mop.field_72307_f.field_72449_c;
                for (a = 0; a < 5; ++a) {
                    Thaumcraft.proxy.sparkle((float)px + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3f, (float)py + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3f, (float)pz + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.3f, 2.0f + p.field_70170_p.field_73012_v.nextFloat(), 2, 0.05f + p.field_70170_p.field_73012_v.nextFloat() * 0.05f);
                }
            }
            if (pointedEntity != null) {
                px = pointedEntity.field_70165_t;
                py = pointedEntity.field_70121_D.field_72338_b + (double)(pointedEntity.field_70131_O / 2.0f);
                pz = pointedEntity.field_70161_v;
                for (a = 0; a < 5; ++a) {
                    Thaumcraft.proxy.sparkle((float)px + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6f, (float)py + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6f, (float)pz + (p.field_70170_p.field_73012_v.nextFloat() - p.field_70170_p.field_73012_v.nextFloat()) * 0.6f, 2.0f + p.field_70170_p.field_73012_v.nextFloat(), 2, 0.05f + p.field_70170_p.field_73012_v.nextFloat() * 0.05f);
                }
            }
            ItemFocusShock.shootLightning(p.field_70170_p, (EntityLivingBase)p, px, py, pz, true);
        } else {
            p.field_70170_p.func_72908_a(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:shock", 0.25f, 1.0f);
            if (pointedEntity != null && pointedEntity instanceof EntityLivingBase && (!(pointedEntity instanceof EntityPlayer) || MinecraftServer.func_71276_C().func_71219_W())) {
                int cl = this.getUpgradeLevel(wand.getFocusItem(stack), chainlightning) * 2;
                pointedEntity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)p), (float)((cl > 0 ? 6 : 4) + potency));
                if (cl > 0) {
                    cl += this.getUpgradeLevel(wand.getFocusItem(stack), FocusUpgradeType.enlarge) * 2;
                    EntityLivingBase center = (EntityLivingBase)pointedEntity;
                    ArrayList<Integer> targets = new ArrayList<Integer>();
                    targets.add(pointedEntity.func_145782_y());
                    while (cl > 0) {
                        --cl;
                        ArrayList<Entity> list = EntityUtils.getEntitiesInRange(p.field_70170_p, center.field_70165_t, center.field_70163_u, center.field_70161_v, (Entity)p, EntityLivingBase.class, 8.0);
                        double d = Double.MAX_VALUE;
                        Entity closest = null;
                        for (Entity e : list) {
                            double dd;
                            if (targets.contains(e.func_145782_y()) || e instanceof EntityPlayer && !MinecraftServer.func_71276_C().func_71219_W() || !((dd = e.func_70068_e((Entity)center)) < d)) continue;
                            closest = e;
                            d = dd;
                        }
                        if (closest == null) continue;
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXZap(center.func_145782_y(), closest.func_145782_y()), new NetworkRegistry.TargetPoint(p.field_70170_p.field_73011_w.field_76574_g, center.field_70165_t, center.field_70163_u, center.field_70161_v, 64.0));
                        targets.add(closest.func_145782_y());
                        closest.func_70097_a(DamageSource.func_76365_a((EntityPlayer)p), (float)(4 + potency));
                        center = (EntityLivingBase)closest;
                    }
                }
            }
        }
    }

    @Override
    public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
        return !type.equals(FocusUpgradeType.enlarge) || this.isUpgradedWith(focusstack, chainlightning) || this.isUpgradedWith(focusstack, earthshock);
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, chainlightning, earthshock};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
            }
        }
        return null;
    }
}

