/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.tile;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.IBrewContainer;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public class TileBrewery
extends TileSimpleInventory
implements ISidedInventory,
IManaReceiver {
    private static final String TAG_MANA = "mana";
    public RecipeBrew recipe;
    int mana = 0;
    int manaLastTick = 0;
    public int signal = 0;

    public boolean addItem(EntityPlayer player, ItemStack stack) {
        if (this.recipe != null || stack == null || stack.func_77973_b() instanceof IBrewItem && ((IBrewItem)stack.func_77973_b()).getBrew(stack) != null && ((IBrewItem)stack.func_77973_b()).getBrew(stack) != BotaniaAPI.fallbackBrew || this.func_70301_a(0) == null != stack.func_77973_b() instanceof IBrewContainer) {
            return false;
        }
        boolean did = false;
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            if (this.func_70301_a(i) != null) continue;
            did = true;
            ItemStack stackToAdd = stack.func_77946_l();
            stackToAdd.field_77994_a = 1;
            this.func_70299_a(i, stackToAdd);
            if (player != null && player.field_71075_bZ.field_75098_d) break;
            --stack.field_77994_a;
            if (stack.field_77994_a != 0 || player == null) break;
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            break;
        }
        if (did) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            for (RecipeBrew recipe : BotaniaAPI.brewRecipes) {
                if (!recipe.matches(this) || recipe.getOutput(this.func_70301_a(0)) == null) continue;
                this.recipe = recipe;
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
            }
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public void func_145845_h() {
        super.func_145845_h();
        if (this.mana > 0 && this.recipe == null) {
            for (RecipeBrew recipeBrew : BotaniaAPI.brewRecipes) {
                if (!recipeBrew.matches(this)) continue;
                this.recipe = recipeBrew;
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
            }
            if (this.recipe == null) {
                this.mana = 0;
            }
        }
        this.recieveMana(0);
        if (!this.field_145850_b.field_72995_K && this.recipe == null) {
            List items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)));
            for (EntityItem item : items) {
                ItemStack stack;
                if (item.field_70128_L || item.func_92059_d() == null || !this.addItem(null, stack = item.func_92059_d()) || stack.field_77994_a != 0) continue;
                item.func_70106_y();
            }
        }
        if (this.recipe != null) {
            if (!this.recipe.matches(this)) {
                this.recipe = null;
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
            }
            if (this.recipe != null) {
                if (this.mana != this.manaLastTick) {
                    Color color = new Color(this.recipe.getBrew().getColor(this.func_70301_a(0)));
                    float f = (float)color.getRed() / 255.0f;
                    float g = (float)color.getGreen() / 255.0f;
                    float b = (float)color.getBlue() / 255.0f;
                    for (int i = 0; i < 5; ++i) {
                        Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.7 - Math.random() * 0.4, (double)this.field_145848_d + 0.9 - Math.random() * 0.2, (double)this.field_145849_e + 0.7 - Math.random() * 0.4, f, g, b, 0.1f + (float)Math.random() * 0.05f, 0.03f - (float)Math.random() * 0.06f, 0.03f + (float)Math.random() * 0.015f, 0.03f - (float)Math.random() * 0.06f);
                        for (int j = 0; j < 2; ++j) {
                            Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.7 - Math.random() * 0.4, (double)this.field_145848_d + 0.9 - Math.random() * 0.2, (double)this.field_145849_e + 0.7 - Math.random() * 0.4, 0.2f, 0.2f, 0.2f, 0.1f + (float)Math.random() * 0.2f, 0.03f - (float)Math.random() * 0.06f, 0.03f + (float)Math.random() * 0.015f, 0.03f - (float)Math.random() * 0.06f);
                        }
                    }
                }
                if (this.mana >= this.getManaCost() && !this.field_145850_b.field_72995_K) {
                    void var2_10;
                    int mana = this.getManaCost();
                    this.recieveMana(-mana);
                    if (!this.field_145850_b.field_72995_K) {
                        ItemStack itemStack = this.recipe.getOutput(this.func_70301_a(0));
                        EntityItem outputItem = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, itemStack);
                        this.field_145850_b.func_72838_d((Entity)outputItem);
                    }
                    boolean bl = false;
                    while (var2_10 < this.func_70302_i_()) {
                        this.func_70299_a((int)var2_10, null);
                        ++var2_10;
                    }
                    this.craftingFanciness();
                }
            }
        }
        int newSignal = 0;
        if (this.recipe != null) {
            ++newSignal;
        }
        if (newSignal != this.signal) {
            this.signal = newSignal;
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
        this.manaLastTick = this.mana;
    }

    public int getManaCost() {
        ItemStack stack = this.func_70301_a(0);
        if (this.recipe == null || stack == null || !(stack.func_77973_b() instanceof IBrewContainer)) {
            return 0;
        }
        IBrewContainer container = (IBrewContainer)stack.func_77973_b();
        return container.getManaCost(this.recipe.getBrew(), stack);
    }

    public void craftingFanciness() {
        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:potionCreate", 1.0f, 1.5f + (float)Math.random() * 0.25f);
        for (int i = 0; i < 25; ++i) {
            Color color = new Color(this.recipe.getBrew().getColor(this.func_70301_a(0)));
            float r = (float)color.getRed() / 255.0f;
            float g = (float)color.getGreen() / 255.0f;
            float b = (float)color.getBlue() / 255.0f;
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, this.field_145848_d + 1, (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, r, g, b, (float)Math.random() * 2.0f + 0.5f, 10);
            for (int j = 0; j < 2; ++j) {
                Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.7 - Math.random() * 0.4, (double)this.field_145848_d + 0.9 - Math.random() * 0.2, (double)this.field_145849_e + 0.7 - Math.random() * 0.4, 0.2f, 0.2f, 0.2f, 0.1f + (float)Math.random() * 0.2f, 0.05f - (float)Math.random() * 0.1f, 0.05f + (float)Math.random() * 0.03f, 0.05f - (float)Math.random() * 0.1f);
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_MANA, this.mana);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        this.mana = par1nbtTagCompound.func_74762_e(TAG_MANA);
    }

    public int func_70302_i_() {
        return 7;
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public String func_145825_b() {
        return "runeAltar";
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    public int[] func_94128_d(int var1) {
        int[] nArray;
        int accessibleSlot = -1;
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            if (this.func_70301_a(i) == null) continue;
            accessibleSlot = i;
        }
        if (accessibleSlot == -1) {
            nArray = new int[]{};
        } else {
            int[] nArray2 = new int[1];
            nArray = nArray2;
            nArray2[0] = accessibleSlot;
        }
        return nArray;
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        return true;
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return this.mana == 0;
    }

    @Override
    public int getCurrentMana() {
        return this.mana;
    }

    @Override
    public boolean isFull() {
        return this.mana >= this.getManaCost();
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(this.mana + mana, this.getManaCost());
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return !this.isFull();
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        int manaToGet = this.getManaCost();
        if (manaToGet > 0) {
            int x = res.func_78326_a() / 2 + 20;
            int y = res.func_78328_b() / 2 - 8;
            if (this.recipe == null) {
                return;
            }
            RenderHelper.renderProgressPie(x, y, (float)this.mana / (float)manaToGet, this.recipe.getOutput(this.func_70301_a(0)));
        }
    }
}

