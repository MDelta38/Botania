/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBoreBase;
import thaumcraft.common.tiles.TileArcaneBoreBase;
import thaumcraft.common.tiles.TileArcaneLamp;
import thaumcraft.common.tiles.TileArcaneLampFertility;
import thaumcraft.common.tiles.TileArcaneLampGrowth;

public class TileArcaneLampRenderer
extends TileEntitySpecialRenderer {
    private ModelBoreBase model = new ModelBoreBase();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        if (tileentity.func_145831_w() != null) {
            ForgeDirection dir = ForgeDirection.DOWN;
            if (tileentity instanceof TileArcaneLamp) {
                dir = ((TileArcaneLamp)tileentity).facing;
            } else if (tileentity instanceof TileArcaneLampGrowth) {
                dir = ((TileArcaneLampGrowth)tileentity).facing;
            } else if (tileentity instanceof TileArcaneLampFertility) {
                dir = ((TileArcaneLampFertility)tileentity).facing;
            }
            GL11.glPushMatrix();
            UtilsFX.bindTexture("textures/models/Bore.png");
            if (tileentity.func_145831_w().func_147438_o(tileentity.field_145851_c + dir.offsetX, tileentity.field_145848_d + dir.offsetY, tileentity.field_145849_e + dir.offsetZ) instanceof TileArcaneBoreBase) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)x + 0.5f + (float)dir.offsetX), (float)((float)y + (float)dir.offsetY), (float)((float)z + 0.5f + (float)dir.offsetZ));
                switch (dir.getOpposite().ordinal()) {
                    case 0: {
                        GL11.glTranslatef((float)-0.5f, (float)0.5f, (float)0.0f);
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                        break;
                    }
                    case 1: {
                        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                        break;
                    }
                    case 2: {
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        break;
                    }
                    case 3: {
                        GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        break;
                    }
                    case 4: {
                        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        break;
                    }
                    case 5: {
                        GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    }
                }
                this.model.renderNozzle();
                GL11.glPopMatrix();
            }
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
            GL11.glPushMatrix();
            switch (dir.ordinal()) {
                case 0: {
                    GL11.glTranslatef((float)-0.5f, (float)0.5f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                    break;
                }
                case 1: {
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break;
                }
                case 2: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 3: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 4: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 5: {
                    GL11.glRotatef((float)0.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                }
            }
            this.model.renderNozzle();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }
}

