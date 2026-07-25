/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBoreBase;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileTube;

@SideOnly(value=Side.CLIENT)
public class TileAlembicRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)ALEMBIC);
    private static final ResourceLocation ALEMBIC = new ResourceLocation("thaumcraft", "textures/models/alembic.obj");
    private ModelBoreBase modelBore = new ModelBoreBase();

    public void renderTileEntityAt(TileAlembic tile, double par2, double par4, double par6, float par8) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4), (float)((float)par6 + 0.5f));
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/models/alembic.png");
        boolean md = false;
        if (tile.func_145831_w() != null) {
            switch (tile.facing) {
                case 5: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break;
                }
                case 3: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break;
                }
                case 2: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                }
            }
            if (tile.aboveFurnace) {
                this.model.renderPart("TubeMain");
                this.model.renderPart("Legs");
            } else if (tile.aboveAlembic) {
                this.model.renderPart("TubeMain");
                this.model.renderPart("TubeSmall");
            } else {
                this.model.renderPart("Legs");
            }
        } else {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.4f);
            this.model.renderPart("Legs");
        }
        this.model.renderPart("Pot");
        this.model.renderPart("Panel");
        GL11.glPopMatrix();
        if (tile.aspectFilter != null) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            switch (tile.facing) {
                case 5: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 3: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 2: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                }
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.468f, (float)-0.409f);
            UtilsFX.renderQuadCenteredFromTexture("textures/models/label.png", 0.27f, 1.0f, 1.0f, 1.0f, -99, 771, 1.0f);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)0.468f, (float)-0.41f);
            GL11.glScaled((double)0.013, (double)0.013, (double)0.013);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            UtilsFX.drawTag(-8, -8, tile.aspectFilter);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        if (tile.func_145831_w() != null) {
            UtilsFX.bindTexture("textures/models/Bore.png");
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity te;
                if (!tile.canOutputTo(dir) || (te = ThaumcraftApiHelper.getConnectableTile(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, dir)) == null || !(te instanceof IEssentiaTransport) || te instanceof TileTube) continue;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4), (float)((float)par6 + 0.5f));
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
                this.modelBore.renderNozzle();
                GL11.glPopMatrix();
            }
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileAlembic)par1TileEntity, par2, par4, par6, par8);
    }
}

