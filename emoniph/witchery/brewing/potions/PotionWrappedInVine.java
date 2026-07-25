/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingHurt;
import com.emoniph.witchery.brewing.potions.IHandleRenderLiving;
import com.emoniph.witchery.brewing.potions.ModelOverlayRenderer;
import com.emoniph.witchery.brewing.potions.PotionBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;

public class PotionWrappedInVine
extends PotionBase
implements IHandleLivingHurt,
IHandleRenderLiving {
    @SideOnly(value=Side.CLIENT)
    private static ResourceLocation TEXTURE;

    public PotionWrappedInVine(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void postContructInitialize() {
        this.setIncurable();
    }

    @Override
    public boolean handleAllHurtEvents() {
        return false;
    }

    @Override
    public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier) {
        if (!event.entity.field_70170_p.field_72995_K && event.source.func_76347_k()) {
            event.ammount *= (float)Math.min(amplifier + 1, 4);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        if (TEXTURE == null) {
            TEXTURE = new ResourceLocation("witchery", "textures/entities/vine_overlay.png");
        }
        GL11.glPushMatrix();
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        ModelOverlayRenderer.render(entity, event.x, event.y, event.z, event.renderer);
        GL11.glPopMatrix();
    }
}

