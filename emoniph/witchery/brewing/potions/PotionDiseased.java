/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.IHandleRenderLiving;
import com.emoniph.witchery.brewing.potions.ModelOverlayRenderer;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;

public class PotionDiseased
extends PotionBase
implements IHandleRenderLiving {
    @SideOnly(value=Side.CLIENT)
    private static ResourceLocation TEXTURE;

    public PotionDiseased(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void postContructInitialize() {
        this.setIncurable();
        this.func_111184_a(SharedMonsterAttributes.field_111264_e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0, 0);
    }

    public double func_111183_a(int amplifier, AttributeModifier p_111183_2_) {
        return -0.5f * (float)(Math.min(amplifier, 1) + 1);
    }

    public boolean func_76397_a(int duration, int amplifier) {
        return duration % 40 == 4;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (!entity.field_70170_p.field_72995_K && entity.field_70170_p.field_73012_v.nextInt(3) == 0) {
            Coord coord = new Coord((Entity)entity);
            if (entity.field_70170_p.func_147437_c(coord.x, coord.y, coord.z) && entity.field_70170_p.func_147439_a(coord.x, coord.y - 1, coord.z).func_149688_o().func_76220_a() && BlockProtect.checkModsForBreakOK(entity.field_70170_p, coord.x, coord.y, coord.z, entity)) {
                coord.setBlock(entity.field_70170_p, Witchery.Blocks.DISEASE);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        if (TEXTURE == null) {
            TEXTURE = new ResourceLocation("witchery", "textures/entities/disease_overlay.png");
        }
        GL11.glPushMatrix();
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        ModelOverlayRenderer.render(entity, event.x, event.y, event.z, event.renderer);
        GL11.glPopMatrix();
    }
}

