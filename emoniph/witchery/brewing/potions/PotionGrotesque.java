/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.IHandleRenderLiving;
import com.emoniph.witchery.brewing.potions.ModelOverlayRenderer;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.client.model.ModelGrotesque;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;

public class PotionGrotesque
extends PotionBase
implements IHandleLivingUpdate,
IHandleRenderLiving {
    @SideOnly(value=Side.CLIENT)
    private static ModelGrotesque DEMON_HEAD_MODEL;
    @SideOnly(value=Side.CLIENT)
    private static ResourceLocation TEXTURE;

    public PotionGrotesque(int id, int color) {
        super(id, color);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        if (DEMON_HEAD_MODEL == null) {
            DEMON_HEAD_MODEL = new ModelGrotesque();
        }
        if (TEXTURE == null) {
            TEXTURE = new ResourceLocation("witchery", "textures/entities/Demon.png");
        }
        GL11.glPushMatrix();
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        ModelOverlayRenderer.renderModel(event.entity, event.x, event.y, event.z, event.renderer, DEMON_HEAD_MODEL);
        GL11.glPopMatrix();
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (!world.field_72995_K && world.func_82737_E() % 5L == 3L) {
            float radius = 4.0f;
            float radiusSq = 16.0f;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - 4.0), (double)(entity.field_70163_u - 4.0), (double)(entity.field_70161_v - 4.0), (double)(entity.field_70165_t + 4.0), (double)(entity.field_70163_u + 4.0), (double)(entity.field_70161_v + 4.0));
            List list = world.func_72872_a(EntityLiving.class, bounds);
            for (EntityLiving victim : list) {
                boolean canApply = entity != victim && !(victim instanceof EntityDemon) && !(victim instanceof IBossDisplayData) && !(victim instanceof EntityGolem);
                if (!canApply || !(victim.func_70068_e((Entity)entity) < 16.0)) continue;
                RiteProtectionCircleRepulsive.push(entity.field_70170_p, (Entity)victim, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
            }
        }
    }
}

