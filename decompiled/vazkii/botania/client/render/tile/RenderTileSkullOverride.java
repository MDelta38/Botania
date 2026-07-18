/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.model.ModelSkullOverride;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.common.block.tile.TileGaiaHead;

public class RenderTileSkullOverride
extends TileEntitySkullRenderer {
    public static final ModelSkullOverride modelSkull = new ModelSkullOverride();

    public void func_147500_a(TileEntitySkull p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
        this.render(p_147500_1_, (float)p_147500_2_, (float)p_147500_4_, (float)p_147500_6_, p_147500_1_.func_145832_p() & 7, (float)(p_147500_1_.func_145906_b() * 360) / 16.0f, p_147500_1_.func_145904_a(), p_147500_1_.func_152108_a());
    }

    public void render(TileEntitySkull skull, float par1, float par2, float par3, int par4, float par5, int par6, GameProfile gameProfile) {
        boolean gaia = skull instanceof TileGaiaHead;
        if (par6 == 3 || gaia) {
            Map map;
            ResourceLocation resourcelocation = AbstractClientPlayer.field_110314_b;
            Minecraft minecraft = Minecraft.func_71410_x();
            if (gaia) {
                resourcelocation = minecraft.field_71439_g.func_110306_p();
            } else if (gameProfile != null && (map = minecraft.func_152342_ad().func_152788_a(gameProfile)).containsKey(MinecraftProfileTexture.Type.SKIN)) {
                resourcelocation = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
            this.func_147499_a(resourcelocation);
            GL11.glPushMatrix();
            GL11.glDisable((int)2884);
            if (par4 != 1) {
                switch (par4) {
                    case 2: {
                        GL11.glTranslatef((float)(par1 + 0.5f), (float)(par2 + 0.25f), (float)(par3 + 0.74f));
                        break;
                    }
                    case 3: {
                        GL11.glTranslatef((float)(par1 + 0.5f), (float)(par2 + 0.25f), (float)(par3 + 0.26f));
                        par5 = 180.0f;
                        break;
                    }
                    case 4: {
                        GL11.glTranslatef((float)(par1 + 0.74f), (float)(par2 + 0.25f), (float)(par3 + 0.5f));
                        par5 = 270.0f;
                        break;
                    }
                    default: {
                        GL11.glTranslatef((float)(par1 + 0.26f), (float)(par2 + 0.25f), (float)(par3 + 0.5f));
                        par5 = 90.0f;
                        break;
                    }
                }
            } else {
                GL11.glTranslatef((float)(par1 + 0.5f), (float)par2, (float)(par3 + 0.5f));
            }
            GL11.glEnable((int)32826);
            GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
            GL11.glEnable((int)3008);
            if (gaia) {
                ShaderHelper.useShader(ShaderHelper.doppleganger, RenderDoppleganger.defaultCallback);
            }
            modelSkull.func_78088_a(null, 0.0f, 0.0f, 0.0f, par5, 0.0f, 0.0625f);
            if (gaia) {
                ShaderHelper.releaseShader();
            }
            GL11.glPopMatrix();
        } else {
            super.func_152674_a(par1, par2, par3, par4, par5, par6, gameProfile);
        }
    }
}

