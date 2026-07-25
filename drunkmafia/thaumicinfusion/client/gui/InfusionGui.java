/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.Thaumcraft
 */
package drunkmafia.thaumicinfusion.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import drunkmafia.thaumicinfusion.client.gui.Image;
import drunkmafia.thaumicinfusion.client.gui.TIGui;
import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.client.WandAspectPacketS;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;

@SideOnly(value=Side.CLIENT)
public class InfusionGui
extends TIGui {
    private final EntityPlayer player;
    private final ItemStack wand;
    private Image background;
    private Image parchment;
    private ScrollRect normalScrollRect;

    public InfusionGui(EntityPlayer player, ItemStack wand) {
        this.player = player;
        this.wand = wand;
        this.xSize = 105;
        this.ySize = 113;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.background = new Image(this, new ResourceLocation("thaumicinfusion", "textures/gui/gui_infusion.png"), 0, 0, 0, 0, this.xSize, this.ySize);
        this.parchment = new Image(this, new ResourceLocation("thaumcraft", "textures/misc/parchment3.png"), 110, -20, 0, 0, 150, 150);
        NBTTagCompound tagCompound = this.wand.func_77978_p();
        Aspect selected = null;
        if (tagCompound != null && tagCompound.func_74764_b("InfusionAspect")) {
            selected = Aspect.getAspect((String)tagCompound.func_74779_i("InfusionAspect"));
        }
        this.normalScrollRect = this.getScrollRect(AspectHandler.getRegisteredAspects(), selected);
    }

    private ScrollRect getScrollRect(Aspect[] aspects, Aspect selected) {
        AspectList knownAspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.player.func_70005_c_());
        ArrayList<AspectSlot> aspectSlots = new ArrayList<AspectSlot>();
        AspectSlot slot = null;
        for (Aspect aspect : aspects) {
            if (knownAspects.getAmount(aspect) <= 0) continue;
            AspectSlot aspectSlot = new AspectSlot(aspect, 16, 16);
            aspectSlots.add(aspectSlot);
            if (aspect != selected) continue;
            slot = aspectSlot;
        }
        ScrollRect scrollRect = new ScrollRect(14, 14, 76, 76, 16, 16, new Image(this, this.background.image, 28, 93, 191, 7, 24, 8), new Image(this, this.background.image, 52, 93, 215, 7, 24, 8), aspectSlots.toArray(new AspectSlot[aspectSlots.size()]));
        scrollRect.selected = slot;
        return scrollRect;
    }

    public void func_73863_a(int mouseX, int mouseY, float tpf) {
        this.func_146276_q_();
        this.background.drawImage();
        if (this.normalScrollRect.selected != null) {
            this.parchment.drawImage();
        }
        this.normalScrollRect.drawScrollBackground(mouseX, mouseY);
        if (this.normalScrollRect.selected != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.guiLeft, (float)this.guiTop, (float)0.0f);
            GL11.glDisable((int)2896);
            this.field_146289_q.func_78279_b(ThaumicInfusion.translate("ti.effect_info." + this.normalScrollRect.selected.aspect.getName().toUpperCase(), new Object[0]), this.parchment.x + 10, this.parchment.y + 5, this.parchment.width - 10, 1);
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int clickedTime) {
        AspectSlot slot;
        super.func_73864_a(mouseX, mouseY, clickedTime);
        this.normalScrollRect.onMouseClicked(mouseX, mouseY);
        AspectSlot aspectSlot = slot = this.normalScrollRect.selected != null ? this.normalScrollRect.findAspect(this.normalScrollRect.selected.aspect) : null;
        if (this.normalScrollRect.selected != null) {
            this.normalScrollRect.selected = slot;
        } else if (this.normalScrollRect.selected == slot) {
            this.normalScrollRect.selected = null;
        }
    }

    public class ScrollRect {
        private final double xAmount;
        private final double yAmount;
        private final double xMargin;
        private final double yMargin;
        private final Image left;
        private final Image right;
        private final int maxYIndex;
        private final AspectSlot[][] slots;
        public int xPos;
        public int yPos;
        public int width;
        public int height;
        private int yIndex;
        private AspectSlot selected;

        public ScrollRect(int xPos, int yPos, int width, int height, int aspectWidth, int aspectHeight, Image left, Image right, AspectSlot ... aspects) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.width = width;
            this.height = height;
            this.left = left;
            this.right = right;
            this.xAmount = width / aspectWidth;
            this.yAmount = height / aspectHeight;
            this.xMargin = 4.0;
            this.yMargin = 4.0;
            int totalY = (int)Math.ceil((double)aspects.length / this.xAmount);
            this.slots = new AspectSlot[(int)this.xAmount][totalY];
            this.maxYIndex = (int)((double)this.slots[0].length - Math.ceil(this.yAmount));
            int i = 0;
            int x = 0;
            while ((double)x < this.xAmount) {
                this.slots[x] = new AspectSlot[totalY];
                for (int y = 0; y < totalY && i < aspects.length; ++y) {
                    this.slots[x][y] = aspects[i++];
                }
                ++x;
            }
        }

        public void onMouseClicked(int mouseX, int mouseY) {
            AspectSlot mouseOver = this.getMouseOver(mouseX, mouseY);
            if (mouseOver != null) {
                AspectSlot aspectSlot = this.selected = mouseOver == this.selected ? null : mouseOver;
                if (((InfusionGui)InfusionGui.this).player.field_71071_by.func_70448_g() != null) {
                    ChannelHandler.instance().sendToServer(new WandAspectPacketS(InfusionGui.this.player, ((InfusionGui)InfusionGui.this).player.field_71071_by.field_70461_c, this.selected != null ? this.selected.aspect : null, false));
                }
            }
            if (this.left.isInRect(mouseX, mouseY)) {
                if (this.yIndex > 0) {
                    this.yIndex = (int)((double)this.yIndex - this.yAmount);
                    if (this.yIndex < 0) {
                        this.yIndex = 0;
                    }
                    InfusionGui.this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(InfusionGui.this.field_146297_k.field_71451_h.field_70165_t, InfusionGui.this.field_146297_k.field_71451_h.field_70163_u, InfusionGui.this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:key", 0.3f, 1.0f, false);
                }
            } else if (this.right.isInRect(mouseX, mouseY) && this.yIndex < this.maxYIndex) {
                this.yIndex = (int)((double)this.yIndex + this.yAmount);
                InfusionGui.this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(InfusionGui.this.field_146297_k.field_71451_h.field_70165_t, InfusionGui.this.field_146297_k.field_71451_h.field_70163_u, InfusionGui.this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:key", 0.3f, 1.0f, false);
            }
        }

        public AspectSlot getMouseOver(int mouseX, int mouseY) {
            for (int x = 0; x < this.slots.length; ++x) {
                int y = 0;
                while ((double)y < this.yAmount && y + this.yIndex < this.slots[x].length) {
                    AspectSlot slot = this.slots[x][y + this.yIndex];
                    if (slot != null && (double)mouseX >= (double)(InfusionGui.this.guiLeft + this.xPos + x) + (double)x * ((double)slot.width + this.xMargin) && (double)mouseX <= (double)(InfusionGui.this.guiLeft + this.xPos + x + x * slot.width) + ((double)slot.width + this.xMargin) && (double)mouseY >= (double)(InfusionGui.this.guiTop + this.yPos + y) + (double)y * ((double)slot.height + this.yMargin) && (double)mouseY <= (double)(InfusionGui.this.guiTop + this.yPos + y + y * slot.height) + ((double)slot.height + this.yMargin)) {
                        return slot;
                    }
                    ++y;
                }
            }
            return null;
        }

        public void drawScrollBackground(int mouseX, int mouseY) {
            AspectSlot mouseOver = this.getMouseOver(mouseX, mouseY);
            if (this.yIndex > 0) {
                this.left.drawImage();
            }
            if (this.yIndex < this.maxYIndex) {
                this.right.drawImage();
            }
            for (int x = 0; x < this.slots.length; ++x) {
                int y = 0;
                while ((double)y < this.yAmount && y + this.yIndex < this.slots[x].length) {
                    AspectSlot slot = this.slots[x][y + this.yIndex];
                    if (y + this.yIndex < this.slots[x].length && slot != null) {
                        UtilsFX.drawTag((int)((int)((double)(InfusionGui.this.guiLeft + this.xPos) + (double)x * ((double)slot.width + this.xMargin))), (int)((int)((double)(InfusionGui.this.guiTop + this.yPos) + (double)y * ((double)slot.height + this.yMargin))), (Aspect)slot.aspect, (float)AspectHandler.getCostOfEffect(slot.aspect), (int)0, (double)0.0, (int)slot.aspect.getBlend(), (float)1.0f, (mouseOver != slot && this.selected != slot ? 1 : 0) != 0);
                    }
                    ++y;
                }
            }
            if (mouseOver != null) {
                ArrayList<String> tooltip = new ArrayList<String>();
                tooltip.add(mouseOver.aspect.getName());
                GL11.glPushMatrix();
                InfusionGui.this.drawHoveringText(tooltip, mouseX, mouseY, InfusionGui.this.field_146289_q);
                GL11.glPopMatrix();
            }
        }

        private AspectSlot findAspect(Aspect aspect) {
            AspectSlot[][] aspectSlotArray = this.slots;
            int n = aspectSlotArray.length;
            for (int i = 0; i < n; ++i) {
                AspectSlot[] xSlots;
                for (AspectSlot slot : xSlots = aspectSlotArray[i]) {
                    if (slot == null || aspect == null || !slot.aspect.getTag().equals(aspect.getTag())) continue;
                    return slot;
                }
            }
            return null;
        }
    }

    class AspectSlot {
        private final Aspect aspect;
        private final int width;
        private final int height;

        public AspectSlot(Aspect aspect, int width, int height) {
            this.aspect = aspect;
            this.width = width;
            this.height = height;
        }
    }
}

