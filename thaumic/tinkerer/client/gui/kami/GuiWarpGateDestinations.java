/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.codechicken.lib.vec.Vector3
 */
package thaumic.tinkerer.client.gui.kami;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.item.kami.ItemSkyPearl;
import thaumic.tinkerer.common.network.packet.kami.PacketWarpGateTeleport;

public class GuiWarpGateDestinations
extends GuiScreen {
    private static ResourceLocation enderField = new ResourceLocation("textures/entity/end_portal.png");
    TileWarpGate warpGate;
    RenderItem render = new RenderItem();
    int lastMouseX;
    int lastMouseY;
    int x;
    int y;
    int ticks;
    List<String> tooltip = new ArrayList<String>();

    public GuiWarpGateDestinations(TileWarpGate warpGate) {
        this.warpGate = warpGate;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.x = this.warpGate.field_145851_c - this.field_146294_l / 2;
        this.y = this.warpGate.field_145849_e - this.field_146295_m / 2;
    }

    public void func_73876_c() {
        ++this.ticks;
        ScaledResolution res = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        int i = res.func_78326_a();
        int j = res.func_78328_b();
        int mx = Mouse.getX() * i / this.field_146297_k.field_71443_c;
        int my = j - Mouse.getY() * j / this.field_146297_k.field_71440_d - 1;
        if (Mouse.isButtonDown((int)0)) {
            int deltaX = mx - this.lastMouseX;
            int deltaY = my - this.lastMouseY;
            this.x -= deltaX;
            this.y -= deltaY;
        }
        this.lastMouseX = mx;
        this.lastMouseY = my;
    }

    protected void func_73869_a(char par1, int par2) {
        int num;
        ItemStack stack;
        super.func_73869_a(par1, par2);
        if (par2 == 57) {
            this.x = this.warpGate.field_145851_c - this.field_146294_l / 2;
            this.y = this.warpGate.field_145849_e - this.field_146295_m / 2;
            return;
        }
        if (par2 >= 2 && par2 < 12 && (stack = this.warpGate.func_70301_a(num = par2 - 2)) != null && ItemSkyPearl.isAttuned(stack) && ItemSkyPearl.getDim(stack) == this.warpGate.func_145831_w().field_73011_w.field_76574_g) {
            int x = ItemSkyPearl.getX(stack);
            int z = ItemSkyPearl.getZ(stack);
            this.x = x - this.field_146294_l / 2;
            this.y = z - this.field_146295_m / 2;
        }
    }

    public void func_73863_a(int par1, int par2, float par3) {
        this.func_146276_q_();
        super.func_73863_a(par1, par2, par3);
        this.tooltip.clear();
        int gateX = this.warpGate.field_145851_c - this.x;
        int gateY = this.warpGate.field_145849_e - this.y;
        this.field_146297_k.field_71446_o.func_110577_a(TextureMap.field_110576_c);
        ArrayList<Object[]> coords = new ArrayList<Object[]>();
        for (int i = 0; i < this.warpGate.func_70302_i_(); ++i) {
            int dim;
            ItemStack stack = this.warpGate.func_70301_a(i);
            if (stack == null || !ItemSkyPearl.isAttuned(stack) || this.warpGate.func_145831_w().field_73011_w.field_76574_g != (dim = ItemSkyPearl.getDim(stack))) continue;
            int x = ItemSkyPearl.getX(stack);
            int y = ItemSkyPearl.getY(stack);
            int z = ItemSkyPearl.getZ(stack);
            if (y == -1) continue;
            coords.add(new Object[]{x - this.x, z - this.y, stack, i});
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)((Math.sin((double)this.ticks / 10.0) + 1.0) / 4.0 + 0.25)));
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        for (Object[] coords_ : coords) {
            int x = (Integer)coords_[0];
            int y = (Integer)coords_[1];
            GL11.glBegin((int)1);
            GL11.glVertex2i((int)gateX, (int)gateY);
            GL11.glVertex2i((int)x, (int)y);
            GL11.glEnd();
        }
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        this.field_146289_q.func_78261_a(EnumChatFormatting.UNDERLINE + StatCollector.func_74838_a((String)"ttmisc.destinations"), 3, 40, 0xFFFFFF);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawPearlAt(0, null, gateX, gateY, par1, par2);
        for (Object[] coords_ : coords) {
            this.drawPearlAt((Integer)coords_[3], (ItemStack)coords_[2], (Integer)coords_[0], (Integer)coords_[1], par1, par2);
        }
        if (!this.tooltip.isEmpty()) {
            ClientHelper.renderTooltip(par1, par2, this.tooltip);
        }
        this.func_73732_a(this.field_146289_q, StatCollector.func_74838_a((String)"ttmisc.numberKeys"), this.field_146294_l / 2, 5, 0xFFFFFF);
        this.func_73732_a(this.field_146289_q, StatCollector.func_74838_a((String)"ttmisc.spaceToReset"), this.field_146294_l / 2, 16, 0xFFFFFF);
    }

    public void drawPearlAt(int index, ItemStack stack, int xp, int yp, int mx, int my) {
        int x = xp + this.x;
        int y = yp + this.y;
        this.field_146297_k.field_71446_o.func_110577_a(TextureMap.field_110576_c);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)xp, (float)yp, (float)0.0f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
        this.render.func_94149_a(-8, -8, ThaumicTinkerer.registry.getFirstItemFromClass(ItemSkyPearl.class).func_77617_a(0), 16, 16);
        GL11.glPopMatrix();
        String destNum = " " + EnumChatFormatting.ITALIC + String.format(StatCollector.func_74838_a((String)"ttmisc.destinationInd"), index + 1);
        String destName = stack != null && stack.func_82837_s() ? stack.func_82833_r() : StatCollector.func_74838_a((String)(stack == null ? "ttmisc.entrancePoint" : "ttmisc.destination"));
        if (stack != null) {
            this.field_146289_q.func_78276_b(index + 1 + ": " + destName, 5, 54 + index * 11, 0xFFFFFF);
        }
        if (mx >= xp - 4 && mx <= xp + 4 && my >= yp - 4 && my < yp + 4) {
            this.tooltip.add(EnumChatFormatting.AQUA + destName + destNum);
            if (stack != null) {
                ItemSkyPearl.addInfo(stack, this.warpGate.func_145831_w().field_73011_w.field_76574_g, Vector3.fromTileEntity((TileEntity)this.warpGate), this.tooltip, true);
                this.tooltip.add(StatCollector.func_74838_a((String)"ttmisc.clickToTeleport"));
            } else {
                this.tooltip.add("X: " + x);
                this.tooltip.add("Z: " + y);
            }
            if (Mouse.isButtonDown((int)0) && GuiWarpGateDestinations.func_146272_n() && stack != null) {
                ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketWarpGateTeleport(this.warpGate, index));
                this.field_146297_k.func_147108_a(null);
            }
        }
    }

    public void func_146276_q_() {
        boolean par1 = false;
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2912);
        Tessellator tessellator = Tessellator.field_78398_a;
        this.field_146297_k.func_110434_K().func_110577_a(enderField);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float f = 256.0f;
        tessellator.func_78382_b();
        float hue = (float)(Math.sin((double)this.ticks / 150.0) + 0.5);
        tessellator.func_78378_d(Color.HSBtoRGB(hue, 0.5f, 0.4f));
        tessellator.func_78374_a(0.0, (double)this.field_146295_m, 0.0, 0.0, (double)((float)this.field_146295_m / f + (float)par1));
        tessellator.func_78374_a((double)this.field_146294_l, (double)this.field_146295_m, 0.0, (double)((float)this.field_146294_l / f), (double)((float)this.field_146295_m / f + (float)par1));
        tessellator.func_78374_a((double)this.field_146294_l, 0.0, 0.0, (double)((float)this.field_146294_l / f), (double)par1);
        tessellator.func_78374_a(0.0, 0.0, 0.0, 0.0, (double)par1);
        tessellator.func_78381_a();
    }
}

