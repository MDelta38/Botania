/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 */
package vazkii.botania.common.block.decor.panes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.client.render.block.InterpolatedIcon;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.decor.panes.BlockModPane;

public class BlockBifrostPane
extends BlockModPane {
    public BlockBifrostPane() {
        super(ModBlocks.bifrostPerm);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void loadTextures(TextureStitchEvent.Pre event) {
        InterpolatedIcon icon;
        if (event.map.func_130086_a() == 0 && event.map.setTextureEntry("botania:bifrostPermPane", (TextureAtlasSprite)(icon = new InterpolatedIcon("botania:bifrostPermPane")))) {
            this.iconTop = icon;
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister reg) {
    }
}

