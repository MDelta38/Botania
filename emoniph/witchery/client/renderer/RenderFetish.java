/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockFetish;
import com.emoniph.witchery.client.model.ModelFetishScarecrow;
import com.emoniph.witchery.client.model.ModelFetishTrent;
import com.emoniph.witchery.client.renderer.RenderBlockItem;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderFetish
extends TileEntitySpecialRenderer {
    private static final ResourceLocation TEXTURE_URL_SCARECROW = new ResourceLocation("witchery", "textures/blocks/scarecrow.png");
    private static final ResourceLocation TEXTURE_URL_TRENT = new ResourceLocation("witchery", "textures/blocks/trent.png");
    private final ModelFetishScarecrow modelScarecrow = new ModelFetishScarecrow();
    private final ModelFetishTrent modelTrent = new ModelFetishTrent();

    public void func_147500_a(TileEntity te, double d, double d1, double d2, float f) {
        BlockFetish.TileEntityFetish fetish;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d), (float)((float)d1), (float)((float)d2));
        BlockFetish.TileEntityFetish tileEntityFetish = fetish = te instanceof BlockFetish.TileEntityFetish ? (BlockFetish.TileEntityFetish)te : null;
        if (fetish != null && fetish.isSpectral()) {
            GL11.glEnable((int)2977);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.6f);
        }
        this.renderModel(fetish, te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
        GL11.glPopMatrix();
    }

    public void renderModel(BlockFetish.TileEntityFetish te, World world, int x, int y, int z) {
        Block block;
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        if (world != null) {
            int meta = world.func_72805_g(x, y, z);
            float rotation = 0.0f;
            switch (meta) {
                case 2: {
                    rotation = 0.0f;
                    break;
                }
                case 3: {
                    rotation = 180.0f;
                    break;
                }
                case 4: {
                    rotation = 270.0f;
                    break;
                }
                case 5: {
                    rotation = 90.0f;
                }
            }
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        Block block2 = block = world != null ? BlockUtil.getBlock(world, x, y, z) : null;
        if (block == null) {
            block = te.getExpectedBlock();
        }
        if (block == Witchery.Blocks.FETISH_TREANT_IDOL) {
            this.func_147499_a(TEXTURE_URL_TRENT);
            this.modelTrent.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f, te);
        } else if (block != Witchery.Blocks.FETISH_WITCHS_LADDER) {
            this.func_147499_a(TEXTURE_URL_SCARECROW);
            this.modelScarecrow.render(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f, te);
        }
    }

    public static class RenderFetishBlockItem
    extends RenderBlockItem {
        private final Block block;
        private final BlockFetish.TileEntityFetish tileFetish;

        public RenderFetishBlockItem(Block block, TileEntitySpecialRenderer render, BlockFetish.TileEntityFetish dummy) {
            super(render, dummy);
            this.block = block;
            this.tileFetish = dummy;
        }

        @Override
        public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
            this.tileFetish.setExpectedBlock(this.block);
            NBTTagCompound nbtRoot = item.func_77978_p();
            if (nbtRoot != null && nbtRoot.func_74764_b("BlockColor")) {
                this.tileFetish.setColor(nbtRoot.func_74771_c("BlockColor"));
            }
            super.renderItem(type, item, data);
        }
    }
}

