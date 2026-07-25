/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.NodeModifier
 *  thaumcraft.api.nodes.NodeType
 *  thaumcraft.client.lib.UtilsFX
 */
package witchinggadgets.client.gui;

import java.util.ArrayList;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.client.lib.UtilsFX;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.gui.ContainerPrimordialGlove;

public class GuiPrimordialGlove
extends GuiContainer {
    String tx1 = "textures/misc/node.png";
    String tx_c_n = "textures/misc/node_core_normal.png";
    String tx_c_d = "textures/misc/node_core_dark.png";
    String tx_c_u = "textures/misc/node_core_unstable.png";
    String tx_c_t = "textures/misc/node_core_taint.png";
    String tx_c_p = "textures/misc/node_core_pure.png";
    String tx_c_h = "textures/misc/node_core_hungry.png";
    InventoryPlayer invPlayer;
    EntityPlayer player;
    protected Slot field_147006_u;

    public GuiPrimordialGlove(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super((Container)new ContainerPrimordialGlove(inventoryPlayer, world, x, y, z));
        this.invPlayer = inventoryPlayer;
        this.player = this.invPlayer.field_70458_d;
        this.field_146999_f = 176;
        this.field_147000_g = 166;
    }

    protected void func_146979_b(int par1, int par2) {
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        if (par1 >= k + 75 && par1 < k + 75 + 26 && par2 >= l + 26 && par2 < l + 26 + 26) {
            AspectList aspects = new AspectList();
            ItemStack bracelet = this.player.func_71045_bC();
            if (bracelet != null && bracelet.func_77942_o() && bracelet.func_77978_p().func_74764_b("storedNode")) {
                NBTTagCompound nodeTag = bracelet.func_77978_p().func_74775_l("storedNode");
                NodeType type = NodeType.values()[nodeTag.func_74762_e("type")];
                NodeModifier modifier = NodeModifier.values()[nodeTag.func_74762_e("modifier")];
                aspects.readFromNBT(nodeTag);
                ArrayList<String> nodeInfo = new ArrayList<String>();
                String s = "\u00a7" + ClientUtilities.nodeTypeChatColour[type.ordinal()] + StatCollector.func_74838_a((String)("nodetype." + type + ".name")) + "\u00a77";
                if (modifier != null) {
                    s = s + ", \u00a7" + ClientUtilities.nodeModifierChatColour[modifier.ordinal()] + StatCollector.func_74838_a((String)("nodemod." + modifier + ".name")) + "\u00a7r";
                }
                nodeInfo.add(s);
                nodeInfo.add("  \u00a75" + StatCollector.func_74838_a((String)"wg.gui.visSize") + ": " + aspects.visSize());
                if (Keyboard.isKeyDown((int)54) || Keyboard.isKeyDown((int)42)) {
                    for (Aspect a : aspects.getAspects()) {
                        nodeInfo.add("   " + a.getName() + " " + aspects.getAmount(a) + "\u00a7r");
                    }
                } else {
                    nodeInfo.add("  " + StatCollector.func_74838_a((String)"wg.gui.shiftForAspectList"));
                }
                UtilsFX.drawCustomTooltip((GuiScreen)this, (RenderItem)field_146296_j, (FontRenderer)this.field_146289_q, nodeInfo, (int)(par1 - k), (int)(par2 - l), (int)7);
                RenderHelper.func_74519_b();
            }
        }
    }

    protected void func_146976_a(float par1, int par2, int par3) {
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ClientUtilities.bindTexture("witchinggadgets:textures/gui/primordialGlove.png");
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        AspectList aspects = new AspectList();
        ItemStack bracelet = this.player.func_71045_bC();
        if (bracelet != null && bracelet.func_77942_o() && bracelet.func_77978_p().func_74764_b("storedNode")) {
            NBTTagCompound nodeTag = bracelet.func_77978_p().func_74775_l("storedNode");
            int nodeType = nodeTag.func_74762_e("type");
            aspects.readFromNBT(nodeTag);
            ClientUtilities.bindTexture("thaumcraft:textures/misc/nodes.png");
            Tessellator tes = Tessellator.field_78398_a;
            int count = 0;
            float average = aspects.visSize() / (aspects.size() > 0 ? aspects.size() : 1);
            for (Aspect a : aspects.aspects.keySet()) {
                float mod = 2.0f * ((float)((System.currentTimeMillis() + (long)(count * 512)) % 4096L) / 4096.0f);
                if (mod > 1.0f) {
                    mod = 2.0f - mod;
                }
                float radius = 10.0f + 8.0f * mod * ((float)aspects.getAmount(a) / average);
                int perm = (int)(System.currentTimeMillis() / 64L % 32L) + count * 4;
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                tes.func_78382_b();
                tes.func_78384_a(a.getColor(), 64);
                tes.func_78374_a((double)((float)(k + 88) - radius), (double)((float)(l + 39) + radius), (double)this.field_73735_i, (double)(perm + 0) * 0.03125, 0.03125);
                tes.func_78374_a((double)((float)(k + 88) + radius), (double)((float)(l + 39) + radius), (double)this.field_73735_i, (double)(perm + 1) * 0.03125, 0.03125);
                tes.func_78374_a((double)((float)(k + 88) + radius), (double)((float)(l + 39) - radius), (double)this.field_73735_i, (double)(perm + 1) * 0.03125, 0.0);
                tes.func_78374_a((double)((float)(k + 88) - radius), (double)((float)(l + 39) - radius), (double)this.field_73735_i, (double)(perm + 0) * 0.03125, 0.0);
                tes.func_78381_a();
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                ++count;
            }
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            if (nodeType != 0) {
                GL11.glBlendFunc((int)770, (int)(nodeType == 3 || nodeType == 4 ? 771 : 1));
            }
            float radius = 10.0f;
            int perm = (int)(System.currentTimeMillis() / 64L % 32L);
            int overl = nodeType == 2 ? 6 : (nodeType == 3 ? 2 : (nodeType == 4 ? 5 : (nodeType == 5 ? 4 : (nodeType == 6 ? 3 : 1))));
            tes.func_78382_b();
            tes.func_78374_a((double)((float)(k + 88) - radius), (double)((float)(l + 39) + radius), (double)(this.field_73735_i + 100.0f), (double)(perm + 0) * 0.03125, (double)(overl + 1) * 0.03125);
            tes.func_78374_a((double)((float)(k + 88) + radius), (double)((float)(l + 39) + radius), (double)(this.field_73735_i + 100.0f), (double)(perm + 1) * 0.03125, (double)(overl + 1) * 0.03125);
            tes.func_78374_a((double)((float)(k + 88) + radius), (double)((float)(l + 39) - radius), (double)(this.field_73735_i + 100.0f), (double)(perm + 1) * 0.03125, (double)(overl + 0) * 0.03125);
            tes.func_78374_a((double)((float)(k + 88) - radius), (double)((float)(l + 39) - radius), (double)(this.field_73735_i + 100.0f), (double)(perm + 0) * 0.03125, (double)(overl + 0) * 0.03125);
            tes.func_78381_a();
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }
}

