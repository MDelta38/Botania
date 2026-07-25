/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.items.armor.Hover
 *  thaumcraft.common.items.armor.ItemFortressArmor
 *  travellersgear.api.IActiveAbility
 *  travellersgear.api.IEventGear
 */
package witchinggadgets.common.items.armor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumcraft.common.items.armor.ItemFortressArmor;
import travellersgear.api.IActiveAbility;
import travellersgear.api.IEventGear;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.client.render.ModelPrimordialArmor;
import witchinggadgets.common.items.tools.IPrimordialGear;
import witchinggadgets.common.util.Utilities;

public class ItemPrimordialArmor
extends ItemFortressArmor
implements IActiveAbility,
IPrimordialCrafting,
IEventGear,
IPrimordialGear {
    IIcon rune;

    public ItemPrimordialArmor(ItemArmor.ArmorMaterial mat, int idx, int type) {
        super(mat, idx, type);
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public boolean showNodes(ItemStack stack, EntityLivingBase living) {
        return this.field_77881_a == 0 && stack.func_77942_o() && stack.func_77978_p().func_74764_b("goggles");
    }

    public boolean showIngamePopups(ItemStack stack, EntityLivingBase living) {
        return this.field_77881_a == 0 && stack.func_77942_o() && stack.func_77978_p().func_74764_b("goggles");
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int slot, boolean equipped) {
        super.func_77663_a(stack, world, entity, slot, equipped);
        if (!world.field_72995_K && stack.func_77951_h() && entity.field_70173_aa % 40 == 0 && entity instanceof EntityLivingBase) {
            stack.func_77972_a(-1, (EntityLivingBase)entity);
        }
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (!world.field_72995_K && stack.func_77960_j() > 0 && player.field_70173_aa % 20 == 0) {
            stack.func_77972_a(-1, (EntityLivingBase)player);
        }
        if (this.field_77881_a == 3) {
            if (!player.field_71075_bZ.field_75100_b && player.field_70701_bs > 0.0f) {
                if (player.field_70170_p.field_72995_K && !player.func_70093_af()) {
                    if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.func_145782_y())) {
                        Thaumcraft.instance.entityEventHandler.prevStep.put(player.func_145782_y(), Float.valueOf(player.field_70138_W));
                    }
                    player.field_70138_W = 1.0f;
                }
                if (player.field_70122_E) {
                    float bonus = 0.055f;
                    if (player.func_70090_H()) {
                        bonus /= 4.0f;
                    }
                    player.func_70060_a(0.0f, 1.0f, bonus);
                } else {
                    player.field_70747_aH = Hover.getHover((int)player.func_145782_y()) ? 0.03f : 0.05f;
                }
            }
            if (player.field_70143_R > 0.0f) {
                player.field_70143_R -= 0.25f;
            }
        }
        switch (this.getAbility(stack)) {
            case 0: {
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 0.5), (double)(player.field_70163_u - 0.5), (double)(player.field_70161_v - 0.5), (double)(player.field_70165_t + 0.5), (double)(player.field_70163_u + 0.5), (double)(player.field_70161_v + 0.5)).func_72314_b(4.0, 4.0, 4.0);
                for (Entity projectile : world.func_72872_a(Entity.class, aabb)) {
                    if (projectile == null || !(projectile instanceof IProjectile) || projectile.getClass().getSimpleName().equalsIgnoreCase("IManaBurst")) continue;
                    Entity shooter = null;
                    if (projectile instanceof EntityArrow) {
                        shooter = ((EntityArrow)projectile).field_70250_c;
                    } else if (projectile instanceof EntityThrowable) {
                        shooter = ((EntityThrowable)projectile).func_85052_h();
                    }
                    if (shooter != null && shooter.equals((Object)player)) continue;
                    double delX = projectile.field_70165_t - player.field_70165_t;
                    double delY = projectile.field_70163_u - player.field_70163_u;
                    double delZ = projectile.field_70161_v - player.field_70161_v;
                    double angle = (delX * projectile.field_70159_w + delY * projectile.field_70181_x + delZ * projectile.field_70179_y) / (Math.sqrt(delX * delX + delY * delY + delZ * delZ) * Math.sqrt(projectile.field_70159_w * projectile.field_70159_w + projectile.field_70181_x * projectile.field_70181_x + projectile.field_70179_y * projectile.field_70179_y));
                    if ((angle = Math.acos(angle)) < 2.356194490192345) continue;
                    if (shooter != null) {
                        delX = -projectile.field_70165_t + shooter.field_70165_t;
                        delY = -projectile.field_70163_u + (shooter.field_70163_u + (double)shooter.func_70047_e());
                        delZ = -projectile.field_70161_v + shooter.field_70161_v;
                    }
                    double curVel = Math.sqrt(delX * delX + delY * delY + delZ * delZ);
                    double newVel = Math.sqrt(projectile.field_70159_w * projectile.field_70159_w + projectile.field_70181_x * projectile.field_70181_x + projectile.field_70179_y * projectile.field_70179_y);
                    projectile.field_70159_w = newVel * (delX /= curVel);
                    projectile.field_70181_x = newVel * (delY /= curVel);
                    projectile.field_70179_y = newVel * (delZ /= curVel);
                }
                break;
            }
            case 3: {
                int[] curedPotions;
                for (int c : curedPotions = new int[]{Potion.field_76440_q.field_76415_H, Potion.field_76436_u.field_76415_H, Potion.field_82731_v.field_76415_H, Potion.field_76431_k.field_76415_H, Config.potionTaintPoisonID}) {
                    if (world.field_72995_K) {
                        player.func_70618_n(c);
                        continue;
                    }
                    player.func_82170_o(c);
                }
                break;
            }
        }
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        int priority = 0;
        double ratio = (double)this.field_77879_b / 25.0;
        if (source.func_82725_o()) {
            priority = 1;
            ratio = (double)this.field_77879_b / 35.0;
        } else if (source.func_76347_k() || source.func_94541_c()) {
            if (source.func_76347_k() && this.getAbility(armor) == 2 && player.func_70027_ad()) {
                player.func_70066_B();
            }
            priority = 1;
            ratio = this.getAbility(armor) == 2 ? 0.75 : (double)this.field_77879_b / 20.0;
        } else if (source.func_76363_c()) {
            int ab = this.getAbility(armor);
            priority = ab == 1 ? 1 : 0;
            double d = ratio = ab == 1 ? (double)this.field_77879_b / 50.0 : 0.0;
        }
        if (player instanceof EntityPlayer) {
            double set = 0.875;
            for (int a = 1; a <= 4; ++a) {
                ItemStack piece = player.func_71124_b(a);
                if (piece == null || !(piece.func_77973_b() instanceof ItemPrimordialArmor)) continue;
                set += 0.125;
                if (!piece.func_77942_o() || !piece.field_77990_d.func_74764_b("mask")) continue;
                set += 0.05;
            }
            ratio *= set;
        }
        return new ISpecialArmor.ArmorProperties(priority, ratio, armor.func_77958_k() + 1 - armor.func_77960_j());
    }

    public boolean canActivate(EntityPlayer player, ItemStack stack, boolean isInHand) {
        return true;
    }

    public void activate(EntityPlayer player, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K) {
            this.cycleAbilities(stack);
        }
    }

    public String func_77653_i(ItemStack stack) {
        int ab = this.getAbility(stack);
        String add = ab >= 0 && ab < 6 ? " " + EnumChatFormatting.DARK_GRAY + "- \u00a7" + ((Aspect)Aspect.getPrimalAspects().get(ab)).getChatcolor() + ((Aspect)Aspect.getPrimalAspects().get(ab)).getName() + EnumChatFormatting.RESET : "";
        return super.func_77653_i(stack) + add;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return ModelPrimordialArmor.getModel(entityLiving, itemStack);
    }

    @SideOnly(value=Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "witchinggadgets:textures/models/primordialArmor.png";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:primordialArmor" + this.field_77881_a);
        this.rune = iconRegister.func_94245_a("witchinggadgets:primordialArmorRune");
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon func_77618_c(int par1, int pass) {
        return pass == 0 ? this.rune : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int pass) {
        int ab;
        if (pass == 0 && (ab = this.getAbility(stack)) >= 0 && ab < 6) {
            return ((Aspect)Aspect.getPrimalAspects().get(this.getAbility(stack))).getColor();
        }
        return 0xFFFFFF;
    }

    @Override
    public int getReturnedPearls(ItemStack stack) {
        return 3;
    }

    @Override
    public void cycleAbilities(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        int cur = stack.func_77978_p().func_74762_e("currentMode");
        if (++cur >= 6) {
            cur = 0;
        }
        stack.func_77978_p().func_74768_a("currentMode", cur);
    }

    @Override
    public int getAbility(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p().func_74762_e("currentMode");
    }

    public void onUserDamaged(LivingHurtEvent event, ItemStack stack) {
        if (event.entityLiving instanceof EntityPlayer) {
            switch (this.getAbility(stack)) {
                case 0: {
                    if (!event.source.func_76352_a()) break;
                    event.setCanceled(true);
                    break;
                }
                case 5: {
                    if (!(event.source.func_76364_f() instanceof EntityLivingBase) || event.entityLiving.func_70681_au().nextInt(4) != 0) break;
                    ((EntityLivingBase)event.source.func_76364_f()).func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 10, 0));
                    ((EntityLivingBase)event.source.func_76364_f()).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 10, 3));
                    break;
                }
            }
        }
    }

    public boolean func_82789_a(ItemStack stack1, ItemStack stack2) {
        return Utilities.compareToOreName(stack2, "ingotVoid");
    }

    public void onUserAttacking(AttackEntityEvent event, ItemStack stack) {
    }

    public void onUserJump(LivingEvent.LivingJumpEvent event, ItemStack stack) {
    }

    public void onUserFall(LivingFallEvent event, ItemStack stack) {
    }

    public void onUserTargeted(LivingSetAttackTargetEvent event, ItemStack stack) {
    }
}

