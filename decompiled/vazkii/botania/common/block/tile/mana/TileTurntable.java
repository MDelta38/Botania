/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.tile.mana;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.mana.TileSpreader;

public class TileTurntable
extends TileMod {
    private static final String TAG_SPEED = "speed";
    private static final String TAG_BACKWARDS = "backwards";
    int speed = 1;
    boolean backwards = false;

    public void func_145845_h() {
        TileEntity tile;
        boolean redstone = false;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide = this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal());
            if (redstoneSide <= 0) continue;
            redstone = true;
        }
        if (!redstone && (tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) instanceof TileSpreader) {
            TileSpreader spreader = (TileSpreader)tile;
            spreader.rotationX = spreader.rotationX + (float)(this.speed * (this.backwards ? -1 : 1));
            if (spreader.rotationX >= 360.0f) {
                spreader.rotationX -= 360.0f;
            }
            spreader.checkForReceiver();
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_SPEED, this.speed);
        cmp.func_74757_a(TAG_BACKWARDS, this.backwards);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.speed = cmp.func_74762_e(TAG_SPEED);
        this.backwards = cmp.func_74767_n(TAG_BACKWARDS);
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return;
        }
        if (player.func_70093_af()) {
            this.backwards = !this.backwards;
        } else {
            this.speed = this.speed == 6 ? 1 : this.speed + 1;
        }
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        int color = -1442814464;
        char motion = this.backwards ? (char)'<' : '>';
        String speed = EnumChatFormatting.BOLD + "";
        for (int i = 0; i < this.speed; ++i) {
            speed = speed + motion;
        }
        int x = res.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(speed) / 2;
        int y = res.func_78328_b() / 2 - 15;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        mc.field_71466_p.func_78261_a(speed, x, y, color);
        GL11.glDisable((int)3042);
    }
}

