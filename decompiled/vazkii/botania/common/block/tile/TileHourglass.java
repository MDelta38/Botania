/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.tile;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public class TileHourglass
extends TileSimpleInventory {
    private static final String TAG_TIME = "time";
    private static final String TAG_TIME_FRACTION = "timeFraction";
    private static final String TAG_FLIP = "flip";
    private static final String TAG_FLIP_TICKS = "flipTicks";
    private static final String TAG_LOCK = "lock";
    private static final String TAG_MOVE = "move";
    int time = 0;
    public float timeFraction = 0.0f;
    public boolean flip = false;
    public int flipTicks = 0;
    public boolean lock = false;
    public boolean move = true;

    public void func_145845_h() {
        super.func_145845_h();
        int totalTime = this.getTotalTime();
        if (totalTime > 0) {
            if (this.move) {
                ++this.time;
            }
            if (this.time >= totalTime) {
                this.time = 0;
                this.flip = !this.flip;
                this.flipTicks = 4;
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
                this.field_145850_b.func_147464_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), this.func_145838_q().func_149738_a(this.field_145850_b));
            }
            this.timeFraction = (float)this.time / (float)totalTime;
        } else {
            this.time = 0;
            this.timeFraction = 0.0f;
        }
        if (this.flipTicks > 0) {
            --this.flipTicks;
        }
    }

    public int getTotalTime() {
        ItemStack stack = this.func_70301_a(0);
        if (stack == null) {
            return 0;
        }
        return TileHourglass.getStackItemTime(stack) * stack.field_77994_a;
    }

    public static int getStackItemTime(ItemStack stack) {
        if (stack == null) {
            return 0;
        }
        if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150354_m)) {
            return stack.func_77960_j() == 1 ? 200 : 20;
        }
        if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150425_aM)) {
            return 1200;
        }
        return 0;
    }

    public int getColor() {
        ItemStack stack = this.func_70301_a(0);
        if (stack == null) {
            return 0;
        }
        if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150354_m)) {
            return stack.func_77960_j() == 1 ? 15292416 : 16772169;
        }
        if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150425_aM)) {
            return 5914927;
        }
        return 0;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        }
        Item item = itemstack.func_77973_b();
        return item == Item.func_150898_a((Block)Blocks.field_150354_m) || item == Item.func_150898_a((Block)Blocks.field_150425_aM);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_TIME, this.time);
        par1nbtTagCompound.func_74776_a(TAG_TIME_FRACTION, this.timeFraction);
        par1nbtTagCompound.func_74757_a(TAG_FLIP, this.flip);
        par1nbtTagCompound.func_74768_a(TAG_FLIP_TICKS, this.flipTicks);
        par1nbtTagCompound.func_74757_a(TAG_MOVE, this.move);
        par1nbtTagCompound.func_74757_a(TAG_LOCK, this.lock);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        this.time = par1nbtTagCompound.func_74762_e(TAG_TIME);
        this.timeFraction = par1nbtTagCompound.func_74760_g(TAG_TIME_FRACTION);
        this.flip = par1nbtTagCompound.func_74767_n(TAG_FLIP);
        this.flipTicks = par1nbtTagCompound.func_74762_e(TAG_FLIP_TICKS);
        this.move = par1nbtTagCompound.func_74767_n(TAG_MOVE);
        this.lock = par1nbtTagCompound.func_74767_n(TAG_LOCK);
    }

    public int func_70302_i_() {
        return 1;
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.time = 0;
        this.timeFraction = 0.0f;
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    public void renderHUD(ScaledResolution res) {
        Minecraft mc = Minecraft.func_71410_x();
        int x = res.func_78326_a() / 2 + 10;
        int y = res.func_78328_b() / 2 - 10;
        ItemStack stack = this.func_70301_a(0);
        if (stack != null) {
            RenderHelper.func_74520_c();
            GL11.glEnable((int)32826);
            RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, stack, x, y);
            RenderItem.getInstance().func_77021_b(mc.field_71466_p, mc.field_71446_o, stack, x, y);
            GL11.glDisable((int)32826);
            RenderHelper.func_74518_a();
            int time = this.getTotalTime();
            String timeStr = StringUtils.func_76337_a((int)time);
            mc.field_71466_p.func_78261_a(timeStr, x + 20, y, this.getColor());
            String status = "";
            if (this.lock) {
                status = "locked";
            }
            if (!this.move) {
                String string = status = status.isEmpty() ? "stopped" : "lockedStopped";
            }
            if (!status.isEmpty()) {
                mc.field_71466_p.func_78261_a(StatCollector.func_74838_a((String)("botaniamisc." + status)), x + 20, y + 12, this.getColor());
            }
        }
    }

    public String func_145825_b() {
        return "hourglass";
    }
}

