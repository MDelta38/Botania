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
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.tile;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;

public class TileRuneAltar
extends TileSimpleInventory
implements ISidedInventory,
IManaReceiver {
    private static final String TAG_MANA = "mana";
    private static final String TAG_MANA_TO_GET = "manaToGet";
    RecipeRuneAltar currentRecipe;
    public int manaToGet = 0;
    int mana = 0;
    int cooldown = 0;
    public int signal = 0;
    List<ItemStack> lastRecipe = null;
    int recipeKeepTicks = 0;

    public boolean addItem(EntityPlayer player, ItemStack stack) {
        if (this.cooldown > 0 || stack.func_77973_b() == ModItems.twigWand || stack.func_77973_b() == ModItems.lexicon) {
            return false;
        }
        if (stack.func_77973_b() == Item.func_150898_a((Block)ModBlocks.livingrock) && stack.func_77960_j() == 0) {
            if (player == null || !player.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
                if (stack.field_77994_a == 0 && player != null) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
            EntityItem item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)(this.field_145848_d + 1), (double)this.field_145849_e + 0.5, new ItemStack(ModBlocks.livingrock));
            item.field_145804_b = 40;
            item.field_70179_y = 0.0;
            item.field_70181_x = 0.0;
            item.field_70159_w = 0.0;
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_72838_d((Entity)item);
            }
            return true;
        }
        if (this.manaToGet != 0) {
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
        }
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        this.recieveMana(0);
        if (!this.field_145850_b.field_72995_K && this.manaToGet == 0) {
            List items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)));
            for (EntityItem item : items) {
                ItemStack stack;
                if (item.field_70128_L || item.func_92059_d() == null || item.func_92059_d().func_77973_b() == Item.func_150898_a((Block)ModBlocks.livingrock) || !this.addItem(null, stack = item.func_92059_d()) || stack.field_77994_a != 0) continue;
                item.func_70106_y();
            }
        }
        if (this.field_145850_b.field_72995_K && this.manaToGet > 0 && this.mana >= this.manaToGet && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            Vector3 vec = Vector3.fromTileEntityCenter(this);
            Vector3 endVec = vec.copy().add(0.0, 2.5, 0.0);
            Botania.proxy.lightningFX(this.field_145850_b, vec, endVec, 2.0f, 38027, 58583);
        }
        if (this.cooldown > 0) {
            --this.cooldown;
            Botania.proxy.wispFX(this.func_145831_w(), (double)this.field_145851_c + Math.random(), (double)this.field_145848_d + 0.8, (double)this.field_145849_e + Math.random(), 0.2f, 0.2f, 0.2f, 0.2f, -0.025f);
        }
        int newSignal = 0;
        if (this.manaToGet > 0) {
            ++newSignal;
            if (this.mana >= this.manaToGet) {
                ++newSignal;
            }
        }
        if (newSignal != this.signal) {
            this.signal = newSignal;
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
        if (this.recipeKeepTicks > 0) {
            --this.recipeKeepTicks;
        } else {
            this.lastRecipe = null;
        }
        this.updateRecipe();
    }

    public void updateRecipe() {
        int manaToGet;
        block4: {
            manaToGet = this.manaToGet;
            if (this.currentRecipe != null) {
                this.manaToGet = this.currentRecipe.getManaUsage();
            } else {
                for (RecipeRuneAltar recipe : BotaniaAPI.runeAltarRecipes) {
                    if (!recipe.matches(this)) continue;
                    this.manaToGet = recipe.getManaUsage();
                    break block4;
                }
                this.manaToGet = 0;
            }
        }
        if (manaToGet != this.manaToGet) {
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:runeAltarStart", 1.0f, 1.0f);
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
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

    public boolean hasValidRecipe() {
        for (RecipeRuneAltar recipe : BotaniaAPI.runeAltarRecipes) {
            if (!recipe.matches(this)) continue;
            return true;
        }
        return false;
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        RecipeRuneAltar recipe = null;
        if (this.currentRecipe != null) {
            recipe = this.currentRecipe;
        } else {
            for (RecipeRuneAltar recipe_ : BotaniaAPI.runeAltarRecipes) {
                if (!recipe_.matches(this)) continue;
                recipe = recipe_;
                break;
            }
        }
        if (this.manaToGet > 0 && this.mana >= this.manaToGet) {
            List items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)));
            EntityItem livingrock = null;
            for (EntityItem item : items) {
                if (item.field_70128_L || item.func_92059_d() == null || item.func_92059_d().func_77973_b() != Item.func_150898_a((Block)ModBlocks.livingrock)) continue;
                livingrock = item;
                break;
            }
            if (livingrock != null) {
                int mana = recipe.getManaUsage();
                this.recieveMana(-mana);
                if (!this.field_145850_b.field_72995_K) {
                    ItemStack output = recipe.getOutput().func_77946_l();
                    EntityItem outputItem = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, output);
                    this.field_145850_b.func_72838_d((Entity)outputItem);
                    this.currentRecipe = null;
                    this.cooldown = 60;
                }
                this.saveLastRecipe();
                if (!this.field_145850_b.field_72995_K) {
                    for (int i = 0; i < this.func_70302_i_(); ++i) {
                        ItemStack stack = this.func_70301_a(i);
                        if (stack == null) continue;
                        if (!(stack.func_77973_b() != ModItems.rune || player != null && player.field_71075_bZ.field_75098_d)) {
                            EntityItem outputItem = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, stack.func_77946_l());
                            this.field_145850_b.func_72838_d((Entity)outputItem);
                        }
                        this.func_70299_a(i, null);
                    }
                    ItemStack livingrockItem = livingrock.func_92059_d();
                    --livingrockItem.field_77994_a;
                    if (livingrockItem.field_77994_a == 0) {
                        livingrock.func_70106_y();
                    }
                }
                this.craftingFanciness();
            }
        }
    }

    public void craftingFanciness() {
        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:runeAltarCraft", 1.0f, 1.0f);
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

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_MANA, this.mana);
        par1nbtTagCompound.func_74768_a(TAG_MANA_TO_GET, this.manaToGet);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        this.mana = par1nbtTagCompound.func_74762_e(TAG_MANA);
        this.manaToGet = par1nbtTagCompound.func_74762_e(TAG_MANA_TO_GET);
    }

    public int func_70302_i_() {
        return 16;
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
        return this.mana >= this.manaToGet;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(this.mana + mana, this.manaToGet);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return !this.isFull();
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
            for (RecipeRuneAltar recipe : BotaniaAPI.runeAltarRecipes) {
                if (!recipe.matches(this)) continue;
                GL11.glEnable((int)3042);
                GL11.glEnable((int)32826);
                GL11.glBlendFunc((int)770, (int)771);
                recipe.getOutput();
                float progress = (float)this.mana / (float)this.manaToGet;
                mc.field_71446_o.func_110577_a(HUDHandler.manaBar);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                RenderHelper.drawTexturedModalRect(xc + radius + 9, yc - 8, 0.0f, progress == 1.0f ? 0 : 22, 8, 22, 15);
                net.minecraft.client.renderer.RenderHelper.func_74520_c();
                if (progress == 1.0f) {
                    RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, new ItemStack(ModBlocks.livingrock), xc + radius + 16, yc + 8);
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)100.0f);
                    RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, new ItemStack(ModItems.twigWand), xc + radius + 24, yc + 8);
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-100.0f);
                }
                RenderHelper.renderProgressPie(xc + radius + 32, yc - 8, progress, recipe.getOutput());
                net.minecraft.client.renderer.RenderHelper.func_74518_a();
                if (progress != 1.0f) continue;
                mc.field_71466_p.func_78261_a("+", xc + radius + 14, yc + 12, 0xFFFFFF);
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
        } else if (this.recipeKeepTicks > 0) {
            String s = StatCollector.func_74838_a((String)"botaniamisc.altarRefill0");
            mc.field_71466_p.func_78261_a(s, xc - mc.field_71466_p.func_78256_a(s) / 2, yc + 10, 0xFFFFFF);
            s = StatCollector.func_74838_a((String)"botaniamisc.altarRefill1");
            mc.field_71466_p.func_78261_a(s, xc - mc.field_71466_p.func_78256_a(s) / 2, yc + 20, 0xFFFFFF);
        }
    }

    public int getTargetMana() {
        return this.manaToGet;
    }
}

