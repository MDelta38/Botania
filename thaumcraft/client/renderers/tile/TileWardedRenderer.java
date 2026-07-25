/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.blocks.BlockCosmeticOpaque;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.foci.ItemFocusWarding;
import thaumcraft.common.tiles.TileWarded;

@SideOnly(value=Side.CLIENT)
public class TileWardedRenderer
extends TileEntitySpecialRenderer {
    static HashMap<WorldCoordinates, IIcon> iconCache = new HashMap();

    public void renderTileEntityAt(TileWarded tile, double x, double y, double z, float f) {
        ItemWandCasting wand;
        EntityPlayer player;
        EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
        if (viewer instanceof EntityPlayer && (player = (EntityPlayer)viewer).func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ItemWandCasting && (wand = (ItemWandCasting)player.func_71045_bC().func_77973_b()).getFocus(player.func_71045_bC()) != null && wand.getFocus(player.func_71045_bC()) instanceof ItemFocusWarding) {
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
            World world = tile.func_145831_w();
            RenderBlocks renderBlocks = new RenderBlocks();
            GL11.glDisable((int)2896);
            Tessellator t = Tessellator.field_78398_a;
            renderBlocks.func_147782_a((double)-0.001f, (double)-0.001f, (double)-0.001f, (double)1.001f, (double)1.001f, (double)1.001f);
            if (tile.owner == ((EntityPlayer)viewer).func_70005_c_().hashCode()) {
                float r = MathHelper.func_76126_a((float)((float)player.field_70173_aa / 2.0f + (float)tile.field_145851_c)) * 0.2f + 0.8f;
                float g = MathHelper.func_76126_a((float)((float)player.field_70173_aa / 3.0f + (float)tile.field_145848_d)) * 0.2f + 0.7f;
                float b = MathHelper.func_76126_a((float)((float)player.field_70173_aa / 4.0f + (float)tile.field_145849_e)) * 0.2f + 0.28f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)0.5f);
            } else {
                float r = MathHelper.func_76126_a((float)((float)player.field_70173_aa / 2.0f + (float)tile.field_145851_c)) * 0.2f + 0.8f;
                float g = MathHelper.func_76126_a((float)((float)player.field_70173_aa / 3.0f + (float)tile.field_145848_d)) * 0.2f + 0.28f;
                float b = MathHelper.func_76126_a((float)((float)player.field_70173_aa / 4.0f + (float)tile.field_145849_e)) * 0.2f + 0.28f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)0.25f);
            }
            t.func_78382_b();
            t.func_78380_c(200);
            this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
            GL11.glTexEnvi((int)8960, (int)8704, (int)260);
            if (this.shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 1)) {
                renderBlocks.func_147768_a(ConfigBlocks.blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0, tile.owner, player.field_70173_aa));
            }
            if (this.shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 0)) {
                renderBlocks.func_147806_b(ConfigBlocks.blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 1, tile.owner, player.field_70173_aa));
            }
            if (this.shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 3)) {
                renderBlocks.func_147761_c(ConfigBlocks.blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 2, tile.owner, player.field_70173_aa));
            }
            if (this.shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 2)) {
                renderBlocks.func_147734_d(ConfigBlocks.blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 3, tile.owner, player.field_70173_aa));
            }
            if (this.shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 5)) {
                renderBlocks.func_147798_e(ConfigBlocks.blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 4, tile.owner, player.field_70173_aa));
            }
            if (this.shouldSideBeRendered(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 4)) {
                renderBlocks.func_147764_f(ConfigBlocks.blockJar, -0.5001, 0.0, -0.5001, this.getIconOnSide(world, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, 5, tile.owner, player.field_70173_aa));
            }
            t.func_78381_a();
            GL11.glTexEnvi((int)8960, (int)8704, (int)8448);
            GL11.glEnable((int)2896);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glDisable((int)3042);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }

    private boolean shouldSideBeRendered(World world, int x, int y, int z, int side) {
        if (world.func_72805_g(x, y, z) != world.func_72805_g(x - Facing.field_71586_b[side], y - Facing.field_71587_c[side], z - Facing.field_71585_d[side])) {
            return true;
        }
        return world.func_147439_a(x - Facing.field_71586_b[side], y - Facing.field_71587_c[side], z - Facing.field_71585_d[side]) != ConfigBlocks.blockWarded;
    }

    private boolean isConnectedBlock(World world, int x, int y, int z, int owner) {
        TileEntity tile;
        if (world.func_147439_a(x, y, z) == ConfigBlocks.blockWarded && (tile = world.func_147438_o(x, y, z)) != null && tile instanceof TileWarded) {
            return ((TileWarded)tile).owner == owner;
        }
        return false;
    }

    private IIcon getIconOnSide(World world, int x, int y, int z, int side, int owner, int ticks) {
        WorldCoordinates wc = new WorldCoordinates(x, y, z, side);
        IIcon out = iconCache.get(wc);
        if ((ticks + side) % 10 == 0 || out == null) {
            IIcon iIcon;
            boolean[] bitMatrix = new boolean[8];
            if (side == 0 || side == 1) {
                bitMatrix[0] = this.isConnectedBlock(world, x - 1, y, z - 1, owner);
                bitMatrix[1] = this.isConnectedBlock(world, x, y, z - 1, owner);
                bitMatrix[2] = this.isConnectedBlock(world, x + 1, y, z - 1, owner);
                bitMatrix[3] = this.isConnectedBlock(world, x - 1, y, z, owner);
                bitMatrix[4] = this.isConnectedBlock(world, x + 1, y, z, owner);
                bitMatrix[5] = this.isConnectedBlock(world, x - 1, y, z + 1, owner);
                bitMatrix[6] = this.isConnectedBlock(world, x, y, z + 1, owner);
                bitMatrix[7] = this.isConnectedBlock(world, x + 1, y, z + 1, owner);
            }
            if (side == 2 || side == 3) {
                bitMatrix[0] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y + 1, z, owner);
                bitMatrix[1] = this.isConnectedBlock(world, x, y + 1, z, owner);
                bitMatrix[2] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y + 1, z, owner);
                bitMatrix[3] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y, z, owner);
                bitMatrix[4] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y, z, owner);
                bitMatrix[5] = this.isConnectedBlock(world, x + (side == 2 ? 1 : -1), y - 1, z, owner);
                bitMatrix[6] = this.isConnectedBlock(world, x, y - 1, z, owner);
                bitMatrix[7] = this.isConnectedBlock(world, x + (side == 3 ? 1 : -1), y - 1, z, owner);
            }
            if (side == 4 || side == 5) {
                bitMatrix[0] = this.isConnectedBlock(world, x, y + 1, z + (side == 5 ? 1 : -1), owner);
                bitMatrix[1] = this.isConnectedBlock(world, x, y + 1, z, owner);
                bitMatrix[2] = this.isConnectedBlock(world, x, y + 1, z + (side == 4 ? 1 : -1), owner);
                bitMatrix[3] = this.isConnectedBlock(world, x, y, z + (side == 5 ? 1 : -1), owner);
                bitMatrix[4] = this.isConnectedBlock(world, x, y, z + (side == 4 ? 1 : -1), owner);
                bitMatrix[5] = this.isConnectedBlock(world, x, y - 1, z + (side == 5 ? 1 : -1), owner);
                bitMatrix[6] = this.isConnectedBlock(world, x, y - 1, z, owner);
                bitMatrix[7] = this.isConnectedBlock(world, x, y - 1, z + (side == 4 ? 1 : -1), owner);
            }
            int idBuilder = 0;
            for (int i = 0; i <= 7; ++i) {
                idBuilder += bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : 128))))))) : 0;
            }
            if (idBuilder > 255 || idBuilder < 0) {
                BlockCosmeticOpaque cfr_ignored_0 = (BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque;
                iIcon = BlockCosmeticOpaque.wardedGlassIcon[0];
            } else {
                BlockCosmeticOpaque cfr_ignored_1 = (BlockCosmeticOpaque)ConfigBlocks.blockCosmeticOpaque;
                iIcon = BlockCosmeticOpaque.wardedGlassIcon[UtilsFX.connectedTextureRefByID[idBuilder]];
            }
            out = iIcon;
            iconCache.put(wc, out);
        }
        return out;
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileWarded)par1TileEntity, par2, par4, par6, par8);
    }
}

