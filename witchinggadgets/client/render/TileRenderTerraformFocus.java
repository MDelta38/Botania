/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.api.ITerraformFocus;
import witchinggadgets.client.ClientProxy;
import witchinggadgets.client.ClientUtilities;

public class TileRenderTerraformFocus
extends TileEntitySpecialRenderer {
    public void func_147500_a(TileEntity tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 1.0f), (float)((float)z + 0.5f));
        if (ClientProxy.terraformerModel != null) {
            Aspect a;
            ClientUtilities.bindTexture("witchinggadgets:textures/models/terraformer.png");
            ClientProxy.terraformerModel.renderPart("focus_03");
            if (tile != null && tile.func_145831_w() != null && tile.func_145838_q() instanceof ITerraformFocus) {
                a = ((ITerraformFocus)tile.func_145838_q()).requiredAspect(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
                if (a != null) {
                    GL11.glColor3f((float)((float)(a.getColor() >> 16 & 0xFF) / 255.0f), (float)((float)(a.getColor() >> 8 & 0xFF) / 255.0f), (float)((float)(a.getColor() & 0xFF) / 255.0f));
                }
            } else if (tile != null && tile.field_145847_g > -1 && tile.field_145854_h instanceof ITerraformFocus) {
                a = ((ITerraformFocus)tile.field_145854_h).requiredAspect(tile.field_145847_g);
                GL11.glDisable((int)2896);
                if (a != null) {
                    GL11.glColor3f((float)((float)(a.getColor() >> 16 & 0xFF) / 255.0f), (float)((float)(a.getColor() >> 8 & 0xFF) / 255.0f), (float)((float)(a.getColor() & 0xFF) / 255.0f));
                }
            }
            ClientProxy.terraformerModel.renderPart("focus_crystal_04");
            GL11.glEnable((int)2896);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (tile != null && tile.func_145831_w() != null && tile.func_145838_q() instanceof ITerraformFocus) {
                Block b;
                ClientUtilities.bindTexture("textures/atlas/blocks.png");
                ItemStack stack = ((ITerraformFocus)tile.func_145838_q()).getDisplayedBlock(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
                if (stack != null && (b = Block.func_149634_a((Item)stack.func_77973_b())) != null) {
                    float rot = (float)(RenderManager.field_78727_a.field_78734_h.field_70173_aa % 40) / 40.0f;
                    GL11.glTranslatef((float)0.0f, (float)1.3125f, (float)0.0f);
                    GL11.glScalef((float)0.25f, (float)0.25f, (float)0.25f);
                    GL11.glRotatef((float)(rot * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(rot * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                    RenderBlocks.getInstance().func_147800_a(b, stack.func_77960_j(), 0.75f);
                }
            }
        }
        GL11.glPopMatrix();
    }
}

