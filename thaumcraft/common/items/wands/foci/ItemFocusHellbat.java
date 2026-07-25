/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemFocusHellbat
extends ItemFocusBasic {
    public IIcon iconOrnament;
    private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 200).add(Aspect.ENTROPY, 100).add(Aspect.AIR, 100);
    private static final AspectList costBomb = new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 200).add(Aspect.AIR, 100);
    private static final AspectList costDevil = new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 100).add(Aspect.AIR, 100).add(Aspect.EARTH, 100);
    public static FocusUpgradeType batbombs = new FocusUpgradeType(13, new ResourceLocation("thaumcraft", "textures/foci/batbombs.png"), "focus.upgrade.batbombs.name", "focus.upgrade.batbombs.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.TRAP, 1));
    public static FocusUpgradeType devilbats = new FocusUpgradeType(14, new ResourceLocation("thaumcraft", "textures/foci/devilbats.png"), "focus.upgrade.devilbats.name", "focus.upgrade.devilbats.text", new AspectList().add(Aspect.ARMOR, 1));
    public static FocusUpgradeType vampirebats = new FocusUpgradeType(19, new ResourceLocation("thaumcraft", "textures/foci/vampirebats.png"), "focus.upgrade.vampirebats.name", "focus.upgrade.vampirebats.text", new AspectList().add(Aspect.HUNGER, 1).add(Aspect.LIFE, 1));

    public ItemFocusHellbat() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "HH" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_hellbat");
        this.iconOrnament = ir.func_94245_a("thaumcraft:focus_hellbat_orn");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int renderPass) {
        return renderPass == 1 ? this.icon : this.iconOrnament;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @Override
    public IIcon getOrnament(ItemStack itemstack) {
        return this.iconOrnament;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        Entity pointedEntity = EntityUtils.getPointedEntity(player.field_70170_p, player, 32.0, EntityFireBat.class);
        double px = player.field_70165_t;
        double py = player.field_70163_u;
        double pz = player.field_70161_v;
        py = player.field_70121_D.field_72338_b + (double)(player.field_70131_O / 2.0f) + 0.25;
        px -= (double)(MathHelper.func_76134_b((float)(player.field_70177_z / 180.0f * 3.141593f)) * 0.16f);
        py -= 0.05000000014901161;
        pz -= (double)(MathHelper.func_76126_a((float)(player.field_70177_z / 180.0f * 3.141593f)) * 0.16f);
        Vec3 vec3d = player.func_70676_i(1.0f);
        px += vec3d.field_72450_a * 0.5;
        py += vec3d.field_72448_b * 0.5;
        pz += vec3d.field_72449_c * 0.5;
        if (pointedEntity != null && pointedEntity instanceof EntityLivingBase) {
            if (!world.field_72995_K) {
                if (pointedEntity instanceof EntityPlayer && !MinecraftServer.func_71276_C().func_71219_W()) {
                    return itemstack;
                }
                EntityFireBat firebat = new EntityFireBat(world);
                firebat.func_70012_b(px, py + (double)firebat.field_70131_O, pz, player.field_70177_z, 0.0f);
                firebat.func_70784_b(pointedEntity);
                firebat.damBonus = wand.getFocusPotency(itemstack);
                firebat.setIsSummoned(true);
                firebat.setIsBatHanging(false);
                if (this.isUpgradedWith(wand.getFocusItem(itemstack), devilbats)) {
                    firebat.setIsDevil(true);
                }
                if (this.isUpgradedWith(wand.getFocusItem(itemstack), batbombs)) {
                    firebat.setIsExplosive(true);
                }
                if (this.isUpgradedWith(wand.getFocusItem(itemstack), vampirebats)) {
                    firebat.owner = player;
                    firebat.setIsVampire(true);
                }
                if (wand.consumeAllVis(itemstack, player, this.getVisCost(itemstack), true, false) && world.func_72838_d((Entity)firebat)) {
                    world.func_72926_e(2004, (int)px, (int)py, (int)pz, 0);
                    world.func_72956_a((Entity)firebat, "thaumcraft:ice", 0.2f, 0.95f + world.field_73012_v.nextFloat() * 0.1f);
                } else {
                    world.func_72956_a((Entity)player, "thaumcraft:wandfail", 0.1f, 0.8f + world.field_73012_v.nextFloat() * 0.1f);
                }
            }
            player.func_71038_i();
        }
        return itemstack;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 14431746;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, batbombs) ? costBomb : (this.isUpgradedWith(itemstack, devilbats) ? costDevil : costBase);
    }

    @Override
    public int getActivationCooldown(ItemStack focusstack) {
        return 1000;
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
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, batbombs, devilbats};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, vampirebats};
            }
        }
        return null;
    }

    @Override
    public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
        return !type.equals(vampirebats) || ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "VAMPBAT");
    }
}

