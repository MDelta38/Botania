/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.subtile.generating;

import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileSpectrolus
extends SubTileGenerating {
    private static final String TAG_NEXT_COLOR = "nextColor";
    private static final int RANGE = 1;
    int nextColor;

    @Override
    public void onUpdate() {
        super.onUpdate();
        boolean remote = this.supertile.func_145831_w().field_72995_K;
        Item wool = Item.func_150898_a((Block)Blocks.field_150325_L);
        List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 1), (double)(this.supertile.field_145848_d - 1), (double)(this.supertile.field_145849_e - 1), (double)(this.supertile.field_145851_c + 1 + 1), (double)(this.supertile.field_145848_d + 1 + 1), (double)(this.supertile.field_145849_e + 1 + 1)));
        int slowdown = this.getSlowdownFactor();
        for (EntityItem item : items) {
            ItemStack stack = item.func_92059_d();
            if (stack == null || stack.func_77973_b() != wool || item.field_70128_L || item.field_70292_b < slowdown) continue;
            int meta = stack.func_77960_j();
            if (meta == this.nextColor) {
                if (!remote) {
                    this.mana = Math.min(this.getMaxMana(), this.mana + 300);
                    this.nextColor = this.nextColor == 15 ? 0 : this.nextColor + 1;
                    this.sync();
                }
                for (int i = 0; i < 10; ++i) {
                    float m = 0.2f;
                    float mx = (float)(Math.random() - 0.5) * m;
                    float my = (float)(Math.random() - 0.5) * m;
                    float mz = (float)(Math.random() - 0.5) * m;
                    this.supertile.func_145831_w().func_72869_a("blockcrack_" + Item.func_150891_b((Item)stack.func_77973_b()) + "_" + meta, item.field_70165_t, item.field_70163_u, item.field_70161_v, (double)mx, (double)my, (double)mz);
                }
            }
            if (remote) continue;
            item.func_70106_y();
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }

    @Override
    public int getMaxMana() {
        return 8000;
    }

    @Override
    public int getColor() {
        return Color.HSBtoRGB((float)this.ticksExisted / 100.0f, 1.0f, 1.0f);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.spectrolus;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
        ItemStack stack = new ItemStack(Blocks.field_150325_L, 1, this.nextColor);
        int color = this.getColor();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (stack != null && stack.func_77973_b() != null) {
            String stackName = stack.func_82833_r();
            int width = 16 + mc.field_71466_p.func_78256_a(stackName) / 2;
            int x = res.func_78326_a() / 2 - width;
            int y = res.func_78328_b() / 2 + 30;
            mc.field_71466_p.func_78261_a(stackName, x + 20, y + 5, color);
            RenderHelper.func_74520_c();
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, stack, x, y);
            RenderHelper.func_74518_a();
        }
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3042);
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_NEXT_COLOR, this.nextColor);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.nextColor = cmp.func_74762_e(TAG_NEXT_COLOR);
    }
}

