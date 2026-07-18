/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.bag;

import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.gui.bag.ContainerFlowerBag;
import vazkii.botania.client.gui.bag.SlotFlower;
import vazkii.botania.common.block.ModBlocks;

public class GuiFlowerBag
extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/gui/flowerBag.png");

    public GuiFlowerBag(EntityPlayer player) {
        super((Container)new ContainerFlowerBag(player));
    }

    protected void func_146979_b(int p_146979_1_, int p_146979_2_) {
        String s = StatCollector.func_74838_a((String)"item.botania:flowerBag.name");
        this.field_146289_q.func_78276_b(s, this.field_146999_f / 2 - this.field_146289_q.func_78256_a(s) / 2, 6, 0x404040);
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.field_147000_g - 96 + 2, 0x404040);
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(texture);
        int k = (this.field_146294_l - this.field_146999_f) / 2;
        int l = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
        List slotList = this.field_147002_h.field_75151_b;
        for (Slot slot : slotList) {
            SlotFlower slotf;
            if (!(slot instanceof SlotFlower) || (slotf = (SlotFlower)slot).func_75216_d()) continue;
            ItemStack stack = new ItemStack(ModBlocks.flower, 0, slotf.color);
            int x = this.field_147003_i + slotf.field_75223_e;
            int y = this.field_147009_r + slotf.field_75221_f;
            RenderHelper.func_74520_c();
            RenderItem.getInstance().func_77015_a(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, stack, x, y);
            RenderHelper.func_74518_a();
            this.field_146297_k.field_71466_p.func_78261_a("0", x + 11, y + 9, 0xFF6666);
        }
    }

    protected boolean func_146983_a(int p_146983_1_) {
        return false;
    }
}

