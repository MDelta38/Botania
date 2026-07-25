/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.golems.ContainerTravelingTrunk;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;

public class GuiTravelingTrunk
extends GuiContainer {
    private EntityPlayer theplayer;
    private EntityTravelingTrunk themob;
    private int inventoryRows;

    public GuiTravelingTrunk(EntityPlayer p, EntityTravelingTrunk m) {
        super((Container)new ContainerTravelingTrunk((IInventory)p.field_71071_by, p.field_70170_p, m));
        this.theplayer = p;
        this.themob = m;
        this.inventoryRows = m.inventory.slotCount / 9;
        this.field_147000_g = 200;
    }

    protected void func_146979_b(int par1, int par2) {
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        this.field_146289_q.func_78276_b(this.themob.func_152113_b() + StatCollector.func_74838_a((String)"entity.trunk.guiname"), 8, 4, 12624112);
        GL11.glPopMatrix();
    }

    protected void func_146976_a(float f, int ii, int jj) {
        if (this.themob.field_70128_L) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/gui/guitrunkbase.png");
        GL11.glEnable((int)3042);
        int j = (this.field_146294_l - this.field_146999_f) / 2;
        int k = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(j, k, 0, 0, this.field_146999_f, this.field_147000_g);
        int hp = Math.round(this.themob.func_110143_aJ() / this.themob.func_110138_aP() * 39.0f);
        this.func_73729_b(j + 134, k + 2, 176, 16, hp, 6);
        if (this.themob.getUpgrade() == 1) {
            this.func_73729_b(j, k + 80, 0, 206, this.field_146999_f, 27);
        }
        if (this.themob.getStay()) {
            this.func_73729_b(j + 112, k, 176, 0, 10, 10);
        }
        GL11.glDisable((int)3042);
    }

    protected void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        int sx = (this.field_146294_l - this.field_146999_f) / 2;
        int sy = (this.field_146295_m - this.field_147000_g) / 2;
        int k1 = i - (sx + 112);
        int l1 = j - (sy + 0);
        if (k1 >= 0 && l1 >= 0 && k1 < 10 && l1 <= 10) {
            this.themob.field_70170_p.func_72980_b(this.themob.field_70165_t, this.themob.field_70163_u, this.themob.field_70161_v, "random.click", 0.3f, 0.6f + (this.themob.getStay() ? 0.0f : 0.2f), false);
            if (this.themob.getStay()) {
                this.theplayer.func_145747_a((IChatComponent)new ChatComponentTranslation("entity.trunk.move", new Object[0]));
            } else {
                this.theplayer.func_145747_a((IChatComponent)new ChatComponentTranslation("entity.trunk.stay", new Object[0]));
            }
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    public void func_146281_b() {
        this.themob.setOpen(false);
        super.func_146281_b();
    }
}

