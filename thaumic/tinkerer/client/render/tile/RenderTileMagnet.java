/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.armor.ItemHoverHarness
 */
package thaumic.tinkerer.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.armor.ItemHoverHarness;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.model.ModelMagnet;

public class RenderTileMagnet
extends TileEntitySpecialRenderer {
    private static final ResourceLocation blue = new ResourceLocation("ttinkerer:textures/model/magnetS.png");
    private static final ResourceLocation red = new ResourceLocation("ttinkerer:textures/model/magnetN.png");
    private static final ResourceLocation blueMob = new ResourceLocation("ttinkerer:textures/model/mobMagnetS.png");
    private static final ResourceLocation redMob = new ResourceLocation("ttinkerer:textures/model/mobMagnetN.png");
    public static boolean mob = false;
    ModelMagnet model = new ModelMagnet();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        boolean blue;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        boolean bl = blue = tileentity.func_145831_w() == null || (tileentity.func_145832_p() & 1) == 0;
        boolean mob = tileentity.func_145831_w() == null ? RenderTileMagnet.mob : (tileentity.func_145832_p() & 2) == 2;
        ClientHelper.minecraft().field_71446_o.func_110577_a(mob ? (blue ? blueMob : redMob) : (blue ? RenderTileMagnet.blue : red));
        int redstone = 0;
        if (tileentity.func_145831_w() != null) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                redstone = Math.max(redstone, tileentity.func_145831_w().func_72878_l(tileentity.field_145851_c + dir.offsetX, tileentity.field_145848_d + dir.offsetY, tileentity.field_145849_e + dir.offsetZ, dir.ordinal()));
            }
        } else {
            redstone = 15;
        }
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render();
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.6f);
        IIcon icon = ((ItemHoverHarness)ConfigItems.itemHoverHarness).iconLightningRing;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 2; ++j) {
                GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
                UtilsFX.renderQuadCenteredFromIcon((boolean)false, (IIcon)icon, (float)((float)redstone / 15.0f * 0.7f + (redstone == 0 ? 0.0f : 0.4f)), (float)(blue ? 0.0f : 1.0f), (float)0.0f, (float)(blue ? 1.0f : 0.0f), (int)225, (int)771, (float)0.9f);
            }
            GL11.glTranslated((double)0.0, (double)0.0, (double)(-(Math.cos((float)System.currentTimeMillis() / 500.0f) + 1.0) * 0.09 - 0.1));
        }
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

