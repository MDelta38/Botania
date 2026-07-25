/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;

public class PotionSpiked
extends PotionBase
implements IHandleLivingUpdate,
IHandleRenderLiving {
    @SideOnly(value=Side.CLIENT)
    private static ResourceLocation TEXTURE;

    public PotionSpiked(int id, int color) {
        super(id, color);
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (!world.field_72995_K && world.func_82737_E() % 5L == 3L) {
            List entities = world.func_72872_a(EntityLivingBase.class, entity.field_70121_D.func_72314_b(0.2 + 0.1 * (double)amplifier, 0.0, 0.2 + 0.1 * (double)amplifier));
            for (EntityLivingBase otherEntity : entities) {
                if (otherEntity == entity) continue;
                otherEntity.func_70097_a(DamageSource.field_76367_g, (float)(1 + amplifier));
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        if (TEXTURE == null) {
            TEXTURE = new ResourceLocation("witchery", "textures/entities/cactus_overlay.png");
        }
        GL11.glPushMatrix();
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        ModelOverlayRenderer.render(entity, event.x, event.y, event.z, event.renderer);
        GL11.glPopMatrix();
    }
}

