/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.utils.BlockUtils
 *  thaumcraft.common.lib.utils.InventoryUtils
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntitySoul;
import com.kentington.thaumichorizons.common.lib.PacketFXContainment;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.InventoryUtils;

public class ItemFocusContainment
extends ItemFocusBasic {
    public static FocusUpgradeType slow = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/slow.png"), "focus.upgrade.slow.name", "focus.upgrade.slow.text", new AspectList().add(Aspect.TRAP, 8));
    public static HashMap<String, Object> beam = new HashMap();
    public static HashMap<String, Entity> hitCritters = new HashMap();
    public static HashMap<String, Float> contain = new HashMap();
    public static HashMap<String, Long> soundDelay = new HashMap();
    IIcon depthIcon = null;
    private static final AspectList cost = new AspectList().add(Aspect.AIR, 10).add(Aspect.ENTROPY, 10);

    public ItemFocusContainment() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    public String func_77653_i(ItemStack stack) {
        return StatCollector.func_74838_a((String)"item.focusContainment.name");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.depthIcon = ir.func_94245_a("thaumichorizons:focus_containment_depth");
        this.icon = ir.func_94245_a("thaumichorizons:focus_containment");
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
        return this.depthIcon;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 29631;
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "CN" + super.getSortingHelper(itemstack);
    }

    @Override
    public AspectList getVisCost(ItemStack focusstack) {
        return cost.copy();
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, slow};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, slow};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
        }
        return null;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        p.func_71008_a(itemstack, Integer.MAX_VALUE);
        return itemstack;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (!this.canJarEntity(p)) {
            if (p.field_70173_aa % 5 == 0) {
                p.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"thaumichorizons.noJar")));
            }
            p.func_71034_by();
            return;
        }
        if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), false, false)) {
            p.func_71034_by();
            return;
        }
        String pp = "R" + p.getDisplayName();
        Entity ent = ItemFocusContainment.getPointedEntity(p.field_70170_p, (EntityLivingBase)p, 10.0);
        MovingObjectPosition mop = BlockUtils.getTargetBlock((World)p.field_70170_p, (Entity)p, (boolean)true);
        Vec3 v = p.func_70040_Z();
        double tx = p.field_70165_t + v.field_72450_a * 10.0;
        double ty = p.field_70163_u + v.field_72448_b * 10.0;
        double tz = p.field_70161_v + v.field_72449_c * 10.0;
        int impact = 0;
        if (ent != null && ent instanceof EntityLiving) {
            tx = ent.field_70165_t;
            ty = ent.field_70163_u + (ent.field_70121_D.field_72337_e - ent.field_70121_D.field_72338_b) / 2.0;
            tz = ent.field_70161_v;
            impact = 5;
            if (!(p == null || p.field_70170_p == null || p.field_70170_p.field_72995_K || soundDelay.get(pp) != null && soundDelay.get(pp) >= System.currentTimeMillis())) {
                ent.field_70170_p.func_72908_a(tx, ty, tz, "thaumcraft:jacobs", 0.3f, 1.0f);
                soundDelay.put(pp, System.currentTimeMillis() + 1200L);
            }
        } else if (mop != null) {
            tx = mop.field_72307_f.field_72450_a;
            ty = mop.field_72307_f.field_72448_b;
            tz = mop.field_72307_f.field_72449_c;
            impact = 5;
        } else {
            soundDelay.put(pp, 0L);
        }
        if (p.field_70170_p.field_72995_K) {
            beam.put(pp, Thaumcraft.proxy.beamCont(p.field_70170_p, p, tx, ty, tz, 2, 0x4466FF, true, impact > 0 ? 2.0f : 0.0f, beam.get(pp), impact));
        }
        if (ent != null && ent instanceof EntityLiving && !(ent instanceof EntityPlayer) && !ent.field_70128_L && !(ent instanceof IBossDisplayData) && !(ent instanceof EntityGolemBase) && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false)) {
            if (this.getUpgradeLevel(wand.getFocusItem(stack), slow) > 0) {
                ((EntityLiving)ent).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 40, this.getUpgradeLevel(wand.getFocusItem(stack), slow) - 1));
            }
            if (hitCritters.get(pp) == null || ent.func_145782_y() != hitCritters.get(pp).func_145782_y()) {
                hitCritters.put(pp, ent);
                contain.put(pp, Float.valueOf(2.0f + (float)wand.getFocusPotency(stack) / 2.0f));
            } else {
                contain.put(pp, Float.valueOf(contain.get(pp).floatValue() + 2.0f + (float)(wand.getFocusPotency(stack) / 3)));
            }
            if (!p.field_70170_p.field_72995_K && contain.get(pp).floatValue() > ((EntityLiving)ent).func_110143_aJ() * 20.0f && !(ent instanceof EntitySoul)) {
                NBTTagCompound entityData = new NBTTagCompound();
                entityData.func_74778_a("id", EntityList.func_75621_b((Entity)ent));
                ent.func_70109_d(entityData);
                this.jarEntity(p, entityData, ent.func_70005_c_(), ent.field_70165_t, ent.field_70163_u + (double)(ent.field_70131_O / 2.0f), ent.field_70161_v);
                p.field_70170_p.func_72908_a(ent.field_70165_t, ent.field_70163_u + (ent.field_70121_D.field_72337_e - ent.field_70121_D.field_72338_b) / 2.0, ent.field_70161_v, "thaumcraft:craftfail", 1.0f, 1.0f);
                contain.remove(pp);
                hitCritters.remove(pp);
                p.field_70170_p.func_72900_e(ent);
            } else if (!p.field_70170_p.field_72995_K && contain.get(pp).floatValue() > ((EntityLiving)ent).func_110143_aJ() * 20.0f) {
                p.field_70170_p.func_72908_a(ent.field_70165_t, ent.field_70163_u + (ent.field_70121_D.field_72337_e - ent.field_70121_D.field_72338_b) / 2.0, ent.field_70161_v, "thaumcraft:craftfail", 1.0f, 1.0f);
                p.field_71071_by.func_70298_a(InventoryUtils.isPlayerCarrying((EntityPlayer)p, (ItemStack)new ItemStack(ConfigBlocks.blockJar, 1, 0)), 1);
                ItemStack jar = new ItemStack(ThaumicHorizons.blockJar);
                jar.func_77982_d(new NBTTagCompound());
                jar.func_77978_p().func_74757_a("isSoul", true);
                if (!p.field_71071_by.func_70441_a(jar)) {
                    p.func_70099_a(jar, 1.0f);
                }
                if (!p.field_70170_p.field_72995_K) {
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXContainment(ent.field_70165_t, ent.field_70163_u, ent.field_70161_v), new NetworkRegistry.TargetPoint(p.field_70170_p.field_73011_w.field_76574_g, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, 32.0));
                }
                contain.remove(pp);
                hitCritters.remove(pp);
                p.field_70170_p.func_72900_e(ent);
            }
        }
    }

    public static Entity getPointedEntity(World world, EntityLivingBase entityplayer, double range) {
        Entity pointedEntity = null;
        Vec3 vec3d = Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v);
        Vec3 vec3d1 = entityplayer.func_70040_Z();
        Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * range, vec3d1.field_72448_b * range, vec3d1.field_72449_c * range);
        float f1 = 1.1f;
        List list = world.func_72839_b((Entity)entityplayer, entityplayer.field_70121_D.func_72321_a(vec3d1.field_72450_a * range, vec3d1.field_72448_b * range, vec3d1.field_72449_c * range).func_72314_b((double)f1, (double)f1, (double)f1));
        double d2 = 0.0;
        for (int i = 0; i < list.size(); ++i) {
            double d3;
            Entity entity = (Entity)list.get(i);
            if (!entity.func_70067_L() || world.func_72901_a(Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v), Vec3.func_72443_a((double)entity.field_70165_t, (double)(entity.field_70163_u + (double)entity.func_70047_e()), (double)entity.field_70161_v), false) != null) continue;
            float f2 = Math.max(0.8f, entity.func_70111_Y());
            AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
            MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
            if (axisalignedbb.func_72318_a(vec3d)) {
                if (!(0.0 < d2) && d2 != 0.0) continue;
                pointedEntity = entity;
                d2 = 0.0;
                continue;
            }
            if (movingobjectposition == null || !((d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f)) < d2) && d2 != 0.0) continue;
            pointedEntity = entity;
            d2 = d3;
        }
        return pointedEntity;
    }

    boolean canJarEntity(EntityPlayer p) {
        return InventoryUtils.inventoryContains((IInventory)p.field_71071_by, (ItemStack)new ItemStack(ConfigBlocks.blockJar, 1, 0), (int)0, (boolean)true, (boolean)true, (boolean)false) && InventoryUtils.placeItemStackIntoInventory((ItemStack)new ItemStack(ThaumicHorizons.blockJar), (IInventory)p.field_71071_by, (int)0, (boolean)false) == null;
    }

    void jarEntity(EntityPlayer p, NBTTagCompound tag, String name, double x, double y, double z) {
        p.field_71071_by.func_70298_a(InventoryUtils.isPlayerCarrying((EntityPlayer)p, (ItemStack)new ItemStack(ConfigBlocks.blockJar, 1, 0)), 1);
        ItemStack jar = new ItemStack(ThaumicHorizons.blockJar);
        jar.func_77982_d(tag);
        jar.func_77978_p().func_74778_a("jarredCritterName", name);
        jar.func_77978_p().func_74757_a("isSoul", false);
        if (!p.field_71071_by.func_70441_a(jar)) {
            p.func_70099_a(jar, 1.0f);
        }
        if (!p.field_70170_p.field_72995_K) {
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXContainment(x, y, z), new NetworkRegistry.TargetPoint(p.field_70170_p.field_73011_w.field_76574_g, x, y, z, 32.0));
        }
    }

    @Override
    public boolean isVisCostPerTick(ItemStack focusstack) {
        return true;
    }

    @Override
    public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack focusstack) {
        return ItemFocusBasic.WandFocusAnimation.CHARGE;
    }
}

