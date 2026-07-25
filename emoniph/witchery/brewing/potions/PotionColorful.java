/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.IHandlePreRenderLiving;
import com.emoniph.witchery.brewing.potions.IHandleRenderLiving;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.Dye;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;

public class PotionColorful
extends PotionBase
implements IHandlePreRenderLiving,
IHandleRenderLiving {
    public PotionColorful(int id, int color) {
        super(id, true, color);
        this.setIncurable();
        this.hideInventoryText();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Pre event, int amplifier) {
        GL11.glPushMatrix();
        Dye dye = Dye.DYES[Math.min(amplifier, Dye.DYES.length - 1)];
        float red = (float)(dye.rgb >>> 16 & 0xFF) / 256.0f;
        float green = (float)(dye.rgb >>> 8 & 0xFF) / 256.0f;
        float blue = (float)(dye.rgb & 0xFF) / 256.0f;
        GL11.glColor3f((float)red, (float)green, (float)blue);
    }

    @Override
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        GL11.glPopMatrix();
    }

    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        Dye dye = Dye.DYES[Math.min(effect.func_76458_c(), Dye.DYES.length - 1)];
        String label = Witchery.resource("witchery:color." + dye.unlocalizedName);
        mc.field_71466_p.func_78261_a(label, x + 10 + 18, y + 6, 0xFFFFFF);
        String duration = Potion.func_76389_a((PotionEffect)effect);
        mc.field_71466_p.func_78261_a(duration, x + 10 + 18, y + 6 + 10, 0x7F7F7F);
    }
}

