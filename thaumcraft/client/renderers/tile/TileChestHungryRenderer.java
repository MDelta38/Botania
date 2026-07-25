/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelChest
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileChestHungry;

@SideOnly(value=Side.CLIENT)
public class TileChestHungryRenderer
extends TileEntitySpecialRenderer {
    private ModelChest chestModel = new ModelChest();

    public void renderTileEntityChestAt(TileChestHungry chest, double par2, double par4, double par6, float par8) {
        int var9 = 0;
        if (!chest.func_145830_o()) {
            var9 = 0;
        } else {
            Block var10 = chest.func_145838_q();
            var9 = chest.func_145832_p();
        }
        ModelChest var14 = this.chestModel;
        UtilsFX.bindTexture("textures/models/chesthungry.png");
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + 1.0f), (float)((float)par6 + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int var11 = 0;
        if (var9 == 2) {
            var11 = 180;
        }
        if (var9 == 3) {
            var11 = 0;
        }
        if (var9 == 4) {
            var11 = 90;
        }
        if (var9 == 5) {
            var11 = -90;
        }
        GL11.glRotatef((float)var11, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float var12 = chest.prevLidAngle + (chest.lidAngle - chest.prevLidAngle) * par8;
        var12 = 1.0f - var12;
        var12 = 1.0f - var12 * var12 * var12;
        var14.field_78234_a.field_78795_f = -(var12 * (float)Math.PI / 2.0f);
        var14.func_78231_a();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityChestAt((TileChestHungry)par1TileEntity, par2, par4, par6, par8);
    }
}

