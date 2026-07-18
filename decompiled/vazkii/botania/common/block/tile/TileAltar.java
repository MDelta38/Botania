/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IPetalApothecary;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public class TileAltar
extends TileSimpleInventory
implements ISidedInventory,
IPetalApothecary {
    private static final Pattern SEED_PATTERN = Pattern.compile("(?:(?:(?:[A-Z-_.:]|^)seed)|(?:(?:[a-z-_.:]|^)Seed))(?:[sA-Z-_.:]|$)");
    public static final String TAG_HAS_WATER = "hasWater";
    public static final String TAG_HAS_LAVA = "hasLava";
    public static final String TAG_IS_MOSSY = "isMossy";
    public boolean hasWater = false;
    public boolean hasLava = false;
    public boolean isMossy = false;
    List<ItemStack> lastRecipe = null;
    int recipeKeepTicks = 0;

    public boolean collideEntityItem(EntityItem item) {
        boolean didChange;
        block17: {
            ItemStack stack;
            block18: {
                stack = item.func_92059_d();
                if (stack == null || item.field_70128_L) {
                    return false;
                }
                if (!this.isMossy && this.func_145832_p() == 0 && stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150395_bd) && !this.field_145850_b.field_72995_K) {
                    this.isMossy = true;
                    this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
                    --stack.field_77994_a;
                    if (stack.field_77994_a == 0) {
                        item.func_70106_y();
                    }
                    VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                if (!this.hasWater() && !this.hasLava()) {
                    if (stack.func_77973_b() == Items.field_151131_as && !this.field_145850_b.field_72995_K) {
                        this.setWater(true);
                        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
                        stack.func_150996_a(Items.field_151133_ar);
                    } else if (stack.func_77973_b() == Items.field_151129_at && !this.field_145850_b.field_72995_K) {
                        this.setLava(true);
                        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
                        stack.func_150996_a(Items.field_151133_ar);
                    } else {
                        return false;
                    }
                }
                if (this.hasLava()) {
                    item.func_70015_d(100);
                    return true;
                }
                didChange = false;
                if (!(stack.func_77973_b() instanceof IFlowerComponent) || !((IFlowerComponent)stack.func_77973_b()).canFit(stack, this)) break block18;
                if (this.func_70301_a(this.func_70302_i_() - 1) != null) {
                    return false;
                }
                if (this.field_145850_b.field_72995_K) break block17;
                --stack.field_77994_a;
                if (stack.field_77994_a == 0) {
                    item.func_70106_y();
                }
                for (int i = 0; i < this.func_70302_i_(); ++i) {
                    if (this.func_70301_a(i) != null) continue;
                    ItemStack stackToPut = stack.func_77946_l();
                    stackToPut.field_77994_a = 1;
                    this.func_70299_a(i, stackToPut);
                    didChange = true;
                    this.field_145850_b.func_72956_a((Entity)item, "game.neutral.swim.splash", 0.1f, 1.0f);
                    break block17;
                }
                break block17;
            }
            if (stack.func_77973_b() != null && SEED_PATTERN.matcher(stack.func_77973_b().func_77667_c(stack)).find()) {
                for (RecipePetals recipe : BotaniaAPI.petalRecipes) {
                    if (!recipe.matches(this)) continue;
                    this.saveLastRecipe();
                    if (!this.field_145850_b.field_72995_K) {
                        for (int i = 0; i < this.func_70302_i_(); ++i) {
                            this.func_70299_a(i, null);
                        }
                        --stack.field_77994_a;
                        if (stack.field_77994_a == 0) {
                            item.func_70106_y();
                        }
                        ItemStack output = recipe.getOutput().func_77946_l();
                        EntityItem outputItem = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, output);
                        this.field_145850_b.func_72838_d((Entity)outputItem);
                        this.setWater(false);
                        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
                    }
                    this.craftingFanciness();
                    didChange = true;
                    break;
                }
            }
        }
        return didChange;
    }

    public void saveLastRecipe() {
        ItemStack stack;
        this.lastRecipe = new ArrayList<ItemStack>();
        for (int i = 0; i < this.func_70302_i_() && (stack = this.func_70301_a(i)) != null; ++i) {
            this.lastRecipe.add(stack.func_77946_l());
        }
        this.recipeKeepTicks = 400;
    }

    public void trySetLastRecipe(EntityPlayer player) {
        TileAltar.tryToSetLastRecipe(player, this, this.lastRecipe);
        if (!this.isEmpty()) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public static void tryToSetLastRecipe(EntityPlayer player, IInventory inv, List<ItemStack> lastRecipe) {
        if (lastRecipe == null || lastRecipe.isEmpty() || player.field_70170_p.field_72995_K) {
            return;
        }
        int index = 0;
        boolean didAny = false;
        block0: for (ItemStack stack : lastRecipe) {
            if (stack == null) continue;
            for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
                ItemStack pstack = player.field_71071_by.func_70301_a(i);
                if (pstack == null || !pstack.func_77969_a(stack) || !ItemStack.func_77970_a((ItemStack)stack, (ItemStack)pstack)) continue;
                --pstack.field_77994_a;
                if (pstack.field_77994_a == 0) {
                    player.field_71071_by.func_70299_a(i, null);
                }
                ItemStack stackToPut = pstack.func_77946_l();
                stackToPut.field_77994_a = 1;
                inv.func_70299_a(index, stackToPut);
                didAny = true;
                ++index;
                continue block0;
            }
        }
        if (didAny) {
            if (inv instanceof TileAltar) {
                player.field_70170_p.func_72956_a((Entity)player, "game.neutral.swim.splash", 0.1f, 1.0f);
            }
            if (player instanceof EntityPlayerMP) {
                EntityPlayerMP mp = (EntityPlayerMP)player;
                mp.field_71069_bz.func_75142_b();
            }
        }
    }

    public void craftingFanciness() {
        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:altarCraft", 1.0f, 1.0f);
        for (int i = 0; i < 25; ++i) {
            float red = (float)Math.random();
            float green = (float)Math.random();
            float blue = (float)Math.random();
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, this.field_145848_d + 1, (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, red, green, blue, (float)Math.random(), 10);
        }
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            if (this.func_70301_a(i) == null) continue;
            return false;
        }
        return true;
    }

    public void func_145845_h() {
        ItemStack stackAt;
        List items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)((double)this.field_145848_d + 1.25), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)((double)this.field_145848_d + 1.3125), (double)(this.field_145849_e + 1)));
        boolean didChange = false;
        for (EntityItem item : items) {
            didChange = this.collideEntityItem(item) || didChange;
        }
        if (didChange) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        for (int i = 0; i < this.func_70302_i_() && (stackAt = this.func_70301_a(i)) != null; ++i) {
            if (!(Math.random() >= 0.97)) continue;
            Color color = new Color(((IFlowerComponent)stackAt.func_77973_b()).getParticleColor(stackAt));
            float red = (float)color.getRed() / 255.0f;
            float green = (float)color.getGreen() / 255.0f;
            float blue = (float)color.getBlue() / 255.0f;
            if (Math.random() >= 0.75) {
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "game.neutral.swim.splash", 0.1f, 10.0f);
            }
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, this.field_145848_d + 1, (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, red, green, blue, (float)Math.random(), 10);
        }
        if (this.hasLava()) {
            this.isMossy = false;
            this.field_145850_b.func_72869_a("smoke", (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, (double)(this.field_145848_d + 1), (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, 0.0, 0.05, 0.0);
            if (Math.random() > 0.9) {
                this.field_145850_b.func_72869_a("lava", (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, (double)(this.field_145848_d + 1), (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, 0.0, 0.01, 0.0);
            }
        }
        if (this.recipeKeepTicks > 0) {
            --this.recipeKeepTicks;
        } else {
            this.lastRecipe = null;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74757_a(TAG_HAS_WATER, this.hasWater());
        cmp.func_74757_a(TAG_HAS_LAVA, this.hasLava());
        cmp.func_74757_a(TAG_IS_MOSSY, this.isMossy);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.hasWater = cmp.func_74767_n(TAG_HAS_WATER);
        this.hasLava = cmp.func_74767_n(TAG_HAS_LAVA);
        this.isMossy = cmp.func_74767_n(TAG_IS_MOSSY);
    }

    public String func_145825_b() {
        return "altar";
    }

    public int func_70302_i_() {
        return 16;
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return false;
    }

    public int[] func_94128_d(int var1) {
        return new int[0];
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        return false;
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public void setWater(boolean water) {
        this.hasWater = water;
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void setLava(boolean lava) {
        this.hasLava = lava;
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public boolean hasWater() {
        return this.hasWater;
    }

    public boolean hasLava() {
        return this.hasLava;
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        int xc = res.func_78326_a() / 2;
        int yc = res.func_78328_b() / 2;
        float angle = -90.0f;
        int radius = 24;
        int amt = 0;
        for (int i = 0; i < this.func_70302_i_() && this.func_70301_a(i) != null; ++i) {
            ++amt;
        }
        if (amt > 0) {
            float anglePer = 360.0f / (float)amt;
            for (RecipePetals recipe : BotaniaAPI.petalRecipes) {
                if (!recipe.matches(this)) continue;
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                mc.field_71446_o.func_110577_a(HUDHandler.manaBar);
                RenderHelper.drawTexturedModalRect(xc + radius + 9, yc - 8, 0.0f, 0, 8, 22, 15);
                ItemStack stack = recipe.getOutput();
                net.minecraft.client.renderer.RenderHelper.func_74520_c();
                RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, stack, xc + radius + 32, yc - 8);
                RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, new ItemStack(Items.field_151014_N), xc + radius + 16, yc + 6);
                net.minecraft.client.renderer.RenderHelper.func_74518_a();
                mc.field_71466_p.func_78261_a("+", xc + radius + 14, yc + 10, 0xFFFFFF);
            }
            net.minecraft.client.renderer.RenderHelper.func_74520_c();
            for (int i = 0; i < amt; ++i) {
                double xPos = (double)xc + Math.cos((double)angle * Math.PI / 180.0) * (double)radius - 8.0;
                double yPos = (double)yc + Math.sin((double)angle * Math.PI / 180.0) * (double)radius - 8.0;
                GL11.glTranslated((double)xPos, (double)yPos, (double)0.0);
                RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, this.func_70301_a(i), 0, 0);
                GL11.glTranslated((double)(-xPos), (double)(-yPos), (double)0.0);
                angle += anglePer;
            }
            net.minecraft.client.renderer.RenderHelper.func_74518_a();
        } else if (this.recipeKeepTicks > 0 && this.hasWater) {
            String s = StatCollector.func_74838_a((String)"botaniamisc.altarRefill0");
            mc.field_71466_p.func_78261_a(s, xc - mc.field_71466_p.func_78256_a(s) / 2, yc + 10, 0xFFFFFF);
            s = StatCollector.func_74838_a((String)"botaniamisc.altarRefill1");
            mc.field_71466_p.func_78261_a(s, xc - mc.field_71466_p.func_78256_a(s) / 2, yc + 20, 0xFFFFFF);
        }
    }
}

