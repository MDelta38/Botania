/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.tile;

import java.awt.Color;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.brew.ItemIncenseStick;

public class TileIncensePlate
extends TileSimpleInventory
implements ISidedInventory {
    private static final String TAG_TIME_LEFT = "timeLeft";
    private static final String TAG_BURNING = "burning";
    private static final int RANGE = 32;
    public int timeLeft = 0;
    public boolean burning = false;
    public int comparatorOutput = 0;

    public void func_145845_h() {
        ItemStack stack = this.func_70301_a(0);
        if (stack != null && this.burning) {
            Brew brew = ((ItemIncenseStick)ModItems.incenseStick).getBrew(stack);
            PotionEffect effect = brew.getPotionEffects(stack).get(0);
            if (this.timeLeft > 0) {
                --this.timeLeft;
                if (!this.field_145850_b.field_72995_K) {
                    List players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c + 0.5 - 32.0), (double)((double)this.field_145848_d + 0.5 - 32.0), (double)((double)this.field_145849_e + 0.5 - 32.0), (double)((double)this.field_145851_c + 0.5 + 32.0), (double)((double)this.field_145848_d + 0.5 + 32.0), (double)((double)this.field_145849_e + 0.5 + 32.0)));
                    for (EntityPlayer player : players) {
                        boolean nightVision;
                        PotionEffect currentEffect = player.func_70660_b(Potion.field_76425_a[effect.func_76456_a()]);
                        boolean bl = nightVision = effect.func_76456_a() == Potion.field_76439_r.field_76415_H;
                        if (currentEffect != null && currentEffect.func_76459_b() >= (nightVision ? 205 : 3)) continue;
                        PotionEffect applyEffect = new PotionEffect(effect.func_76456_a(), nightVision ? 285 : 80, effect.func_76458_c(), true);
                        player.func_70690_d(applyEffect);
                    }
                    if (this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                        this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.1, (double)this.field_145849_e + 0.5, "fire.fire", 0.1f, 1.0f);
                    }
                } else {
                    double x = (double)this.field_145851_c + 0.5;
                    double y = (double)this.field_145848_d + 0.5;
                    double z = (double)this.field_145849_e + 0.5;
                    Color color = new Color(brew.getColor(stack));
                    float r = (float)color.getRed() / 255.0f;
                    float g = (float)color.getGreen() / 255.0f;
                    float b = (float)color.getBlue() / 255.0f;
                    Botania.proxy.wispFX(this.field_145850_b, x - (Math.random() - 0.5) * 0.2, y - (Math.random() - 0.5) * 0.2, z - (Math.random() - 0.5) * 0.2, r, g, b, 0.05f + (float)Math.random() * 0.02f, 0.005f - (float)Math.random() * 0.01f, 0.01f + (float)Math.random() * 0.005f, 0.005f - (float)Math.random() * 0.01f);
                    Botania.proxy.wispFX(this.field_145850_b, x - (Math.random() - 0.5) * 0.2, y - (Math.random() - 0.5) * 0.2, z - (Math.random() - 0.5) * 0.2, 0.2f, 0.2f, 0.2f, 0.05f + (float)Math.random() * 0.02f, 0.005f - (float)Math.random() * 0.01f, 0.01f + (float)Math.random() * 0.001f, 0.005f - (float)Math.random() * 0.01f);
                }
            } else {
                this.func_70299_a(0, null);
                this.burning = false;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            }
        } else {
            this.timeLeft = 0;
        }
        int newComparator = 0;
        if (stack != null) {
            newComparator = 1;
        }
        if (this.burning) {
            newComparator = 2;
        }
        if (this.comparatorOutput != newComparator) {
            this.comparatorOutput = newComparator;
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
    }

    public void ignite() {
        ItemStack stack = this.func_70301_a(0);
        if (stack == null || this.burning) {
            return;
        }
        this.burning = true;
        Brew brew = ((ItemIncenseStick)ModItems.incenseStick).getBrew(stack);
        this.timeLeft = brew.getPotionEffects(stack).get(0).func_76459_b() * 60;
    }

    public int func_70302_i_() {
        return 1;
    }

    public String func_145825_b() {
        return "incensePlate";
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_TIME_LEFT, this.timeLeft);
        par1nbtTagCompound.func_74757_a(TAG_BURNING, this.burning);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        this.timeLeft = par1nbtTagCompound.func_74762_e(TAG_TIME_LEFT);
        this.burning = par1nbtTagCompound.func_74767_n(TAG_BURNING);
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return itemstack != null && itemstack.func_77973_b() == ModItems.incenseStick && ((ItemIncenseStick)ModItems.incenseStick).getBrew(itemstack) != BotaniaAPI.fallbackBrew;
    }

    public int[] func_94128_d(int side) {
        return new int[]{0};
    }

    public boolean func_102007_a(int slot, ItemStack stack, int side) {
        return this.func_94041_b(slot, stack);
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    public void func_70296_d() {
        super.func_70296_d();
        if (!this.field_145850_b.field_72995_K) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
    }
}

