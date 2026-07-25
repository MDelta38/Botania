/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.IHandleLivingAttack;
import com.emoniph.witchery.brewing.potions.IHandleLivingHurt;
import com.emoniph.witchery.brewing.potions.IHandleLivingJump;
import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.IHandlePreRenderLiving;
import com.emoniph.witchery.brewing.potions.IHandleRenderLiving;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.network.PacketSyncEntitySize;
import com.emoniph.witchery.util.EntitySizeInfo;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;

public class PotionResizing
extends PotionBase
implements IHandlePreRenderLiving,
IHandleRenderLiving,
IHandleLivingUpdate,
IHandleLivingHurt,
IHandleLivingJump,
IHandleLivingAttack {
    private static Method methodEntitySetSize;
    private static Method methodZombieSetSize;
    private static Method methodZombieSetSize2;
    private static Method methodAgeableSetSize;
    private static Method methodAgeableSetSize2;

    public PotionResizing(int id, int color) {
        super(id, color);
    }

    @Override
    public void func_111187_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier) {
        EntitySizeInfo sizeInfo = new EntitySizeInfo(entity);
        PotionResizing.setEntitySize((Entity)entity, sizeInfo.defaultWidth, sizeInfo.defaultHeight);
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            player.eyeHeight = sizeInfo.eyeHeight;
        }
        entity.field_70138_W = sizeInfo.stepSize;
        Witchery.packetPipeline.sendToAll(new PacketSyncEntitySize((Entity)entity));
        super.func_111187_a(entity, attributes, amplifier);
    }

    public static void setEntitySize(Entity entity, float width, float height) {
        try {
            if (entity instanceof EntityZombie) {
                if (methodZombieSetSize == null) {
                    methodZombieSetSize = ReflectionHelper.findMethod(EntityZombie.class, (Object)((EntityZombie)entity), (String[])new String[]{"setSize", "func_70105_a", "a"}, (Class[])new Class[]{Float.TYPE, Float.TYPE});
                }
                if (methodZombieSetSize2 == null) {
                    methodZombieSetSize2 = ReflectionHelper.findMethod(EntityZombie.class, (Object)((EntityZombie)entity), (String[])new String[]{"func_146069_a", "a"}, (Class[])new Class[]{Float.TYPE});
                }
                methodZombieSetSize.invoke(entity, Float.valueOf(width), Float.valueOf(height));
                methodZombieSetSize2.invoke(entity, Float.valueOf(1.0f));
            } else if (entity instanceof EntityAgeable) {
                if (methodAgeableSetSize == null) {
                    methodAgeableSetSize = ReflectionHelper.findMethod(EntityAgeable.class, (Object)((EntityAgeable)entity), (String[])new String[]{"setSize", "func_70105_a", "a"}, (Class[])new Class[]{Float.TYPE, Float.TYPE});
                }
                if (methodAgeableSetSize2 == null) {
                    methodAgeableSetSize2 = ReflectionHelper.findMethod(EntityAgeable.class, (Object)((EntityAgeable)entity), (String[])new String[]{"setScale", "func_98055_j", "a"}, (Class[])new Class[]{Float.TYPE});
                }
                methodAgeableSetSize.invoke(entity, Float.valueOf(width), Float.valueOf(height));
                methodAgeableSetSize2.invoke(entity, Float.valueOf(1.0f));
            } else {
                if (methodEntitySetSize == null) {
                    methodEntitySetSize = ReflectionHelper.findMethod(Entity.class, (Object)entity, (String[])new String[]{"setSize", "func_70105_a", "a"}, (Class[])new Class[]{Float.TYPE, Float.TYPE});
                }
                methodEntitySetSize.invoke(entity, Float.valueOf(width), Float.valueOf(height));
            }
        }
        catch (IllegalAccessException ex) {
        }
        catch (IllegalArgumentException ex) {
        }
        catch (InvocationTargetException invocationTargetException) {
            // empty catch block
        }
    }

    @Override
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Pre event, int amplifier) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)event.x, (double)event.y, (double)event.z);
        float scale = PotionResizing.getModifiedScaleFactor(entity, amplifier);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslated((double)(-event.x), (double)(-event.y), (double)(-event.z));
    }

    public static float getModifiedScaleFactor(EntityLivingBase entity, int amplifier) {
        float currentHeight = entity.field_70131_O;
        EntitySizeInfo sizeInfo = new EntitySizeInfo(entity);
        float ratio = currentHeight / sizeInfo.defaultHeight;
        float factor = PotionResizing.getScaleFactor(amplifier);
        float scale = factor < 1.0f ? Math.max(ratio, factor) : Math.min(ratio, factor);
        return scale;
    }

    @Override
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        GL11.glPopMatrix();
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        float reductionFactor = 0.03f * (float)(event.entity.field_70170_p.field_72995_K ? 1 : 20);
        if (world.field_72995_K || entity.field_70173_aa % 20 == 0) {
            EntitySizeInfo sizeInfo = new EntitySizeInfo(entity);
            float scale = PotionResizing.getScaleFactor(amplifier);
            float requiredHeight = sizeInfo.defaultHeight * scale;
            float requiredWidth = sizeInfo.defaultWidth * scale;
            float currentHeight = event.entityLiving.field_70131_O;
            if (requiredHeight != currentHeight) {
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)entity;
                    if (!world.field_72995_K) {
                        player.eyeHeight = currentHeight * 0.92f;
                    }
                }
                float f = entity.field_70138_W = scale < 1.0f ? 0.0f : scale - 1.0f;
                if (scale < 1.0f) {
                    PotionResizing.setEntitySize((Entity)entity, Math.max(entity.field_70130_N - reductionFactor, requiredWidth), Math.max(currentHeight - reductionFactor, requiredHeight));
                } else {
                    PotionResizing.setEntitySize((Entity)entity, Math.min(entity.field_70130_N + reductionFactor, requiredWidth), Math.min(currentHeight + reductionFactor, requiredHeight));
                }
            }
        }
    }

    @Override
    public boolean handleAllHurtEvents() {
        return true;
    }

    @Override
    public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier) {
        if (!world.field_72995_K) {
            PotionEffect effectDefender = entity.func_70660_b((Potion)this);
            boolean isDefenderShrunken = effectDefender != null;
            DamageSource source = event.source;
            if (source.func_76355_l() == "mob" || source.func_76355_l() == "player") {
                if (source.func_76346_g() != null && source.func_76346_g() instanceof EntityLivingBase) {
                    EntityLivingBase attacker = (EntityLivingBase)source.func_76346_g();
                    PotionEffect effectAttacker = attacker.func_70660_b((Potion)this);
                    if (isDefenderShrunken || effectAttacker != null) {
                        float scale = PotionResizing.getDamageMultiplier(effectAttacker, effectDefender);
                        event.ammount *= Math.max(Math.min(scale, 3.0f), 0.5f);
                    }
                }
            } else if (source == DamageSource.field_76379_h && isDefenderShrunken && PotionResizing.getScaleFactor(effectDefender.func_76458_c()) > event.ammount) {
                event.setCanceled(true);
            }
        }
    }

    @Override
    public void onLivingAttack(World world, EntityLivingBase entity, LivingAttackEvent event, int amplifier) {
        if (Witchery.modHooks.isAM2Present && !world.field_72995_K && event.source == DamageSource.field_76368_d && amplifier <= 1 && entity instanceof EntityPlayer && !event.entity.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)event.entity.field_70165_t), MathHelper.func_76128_c((double)event.entity.field_70163_u), MathHelper.func_76128_c((double)event.entity.field_70161_v)).func_149721_r()) {
            event.setCanceled(true);
        }
    }

    public static float getScaleFactor(int amplifier) {
        switch (amplifier) {
            default: {
                return 0.25f;
            }
            case 1: {
                return 0.4f;
            }
            case 2: {
                return 2.0f;
            }
            case 3: 
        }
        return 3.0f;
    }

    private static int getSize(PotionEffect amplifier) {
        if (amplifier == null) {
            return 3;
        }
        switch (amplifier.func_76458_c()) {
            default: {
                return 3;
            }
            case 0: {
                return 1;
            }
            case 1: {
                return 2;
            }
            case 2: {
                return 4;
            }
            case 3: 
        }
        return 5;
    }

    public static float getDamageMultiplier(PotionEffect amplifierA, PotionEffect amplifierB) {
        int sizeA = PotionResizing.getSize(amplifierA);
        int sizeB = PotionResizing.getSize(amplifierB);
        float sizeDiff = sizeA / sizeB;
        return sizeDiff;
    }

    @Override
    public void onLivingJump(World world, EntityLivingBase entity, LivingEvent.LivingJumpEvent event, int amplifier) {
        float scale = PotionResizing.getScaleFactor(amplifier);
        event.entityLiving.field_70181_x = scale > 1.0f ? (event.entityLiving.field_70181_x *= (double)scale * 0.5 + 0.5) : (event.entityLiving.field_70181_x *= Math.max((double)scale, 0.5) * 1.5);
    }
}

