/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.InventoryEffectRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.box;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.gui.box.ContainerBaubleBox;

public class GuiBaubleBox
extends InventoryEffectRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/gui/baubleBox.png");

    public GuiBaubleBox(EntityPlayer player) {
        super((Container)new ContainerBaubleBox(player));
    }

    protected boolean func_146983_a(int p_146983_1_) {
        return false;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        for (int i1 = 0; i1 < 4; ++i1) {
            Slot slot = (Slot)this.field_147002_h.field_75151_b.get(i1);
            if (!slot.func_75216_d() || slot.func_75219_a() != 1) continue;
            this.func_73729_b(k + slot.field_75223_e, l + slot.field_75221_f, 200, 0, 16, 16);
        }
        GuiInventory.func_147046_a((int)(this.field_147003_i + 43), (int)(this.field_147009_r + 61), (int)20, (float)(this.field_147003_i + 43 - p_146976_2_), (float)(this.field_147009_r + 45 - 30 - p_146976_3_), (EntityLivingBase)this.field_146297_k.field_71439_g);
    }
}

