/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.FlowerComponent;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.TilePylon;

public class TileEnchanter
extends TileMod
implements ISparkAttachable {
    private static final String TAG_STAGE = "stage";
    private static final String TAG_STAGE_TICKS = "stageTicks";
    private static final String TAG_STAGE_3_END_TICKS = "stage3EndTicks";
    private static final String TAG_MANA_REQUIRED = "manaRequired";
    private static final String TAG_MANA = "mana";
    private static final String TAG_ITEM = "item";
    private static final String TAG_ENCHANTS = "enchantsToApply";
    public int stage = 0;
    public int stageTicks = 0;
    public int stage3EndTicks = 0;
    int manaRequired = -1;
    int mana = 0;
    public ItemStack itemToEnchant = null;
    List<EnchantmentData> enchants = new ArrayList<EnchantmentData>();
    private static final int[][] OBSIDIAN_LOCATIONS = new int[][]{{0, -1, 0}, {0, -1, 1}, {0, -1, -1}, {1, -1, 0}, {-1, -1, 0}, {0, -1, 2}, {-1, -1, 2}, {1, -1, 2}, {0, -1, -2}, {-1, -1, -2}, {1, -1, -2}, {2, -1, 0}, {2, -1, 1}, {2, -1, -1}, {-2, -1, 0}, {-2, -1, 1}, {-2, -1, -1}};
    private static final int[][][] PYLON_LOCATIONS = new int[][][]{new int[][]{{-5, 1, 0}, {5, 1, 0}, {-4, 1, 3}, {4, 1, 3}, {-4, 1, -3}, {4, 1, -3}}, new int[][]{{0, 1, -5}, {0, 1, 5}, {3, 1, -4}, {3, 1, 4}, {-3, 1, -4}, {-3, 1, 4}}};
    private static final int[][] FLOWER_LOCATIONS = new int[][]{{-1, 0, -1}, {1, 0, -1}, {-1, 0, 1}, {1, 0, 1}};

    public static MultiblockSet makeMultiblockSet() {
        Multiblock mb = new Multiblock();
        for (int[] o : OBSIDIAN_LOCATIONS) {
            mb.addComponent(o[0], o[1] + 1, o[2], Blocks.field_150343_Z, 0);
        }
        for (int[] p : PYLON_LOCATIONS[0]) {
            mb.addComponent(p[0], p[1] + 1, p[2], ModBlocks.pylon, 0);
            mb.addComponent(new FlowerComponent(new ChunkCoordinates(p[0], p[1], p[2]), ModBlocks.flower));
        }
        for (int[] f : FLOWER_LOCATIONS) {
            mb.addComponent(new FlowerComponent(new ChunkCoordinates(f[0], f[1] + 1, f[2]), ModBlocks.flower));
        }
        mb.addComponent(0, 1, 0, Blocks.field_150368_y, 0);
        return mb.makeSet();
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        if (this.stage != 0 || this.itemToEnchant == null || !this.itemToEnchant.func_77956_u()) {
            return;
        }
        List items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 2), (double)this.field_145848_d, (double)(this.field_145849_e - 2), (double)(this.field_145851_c + 3), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 3)));
        int count = items.size();
        if (count > 0 && !this.field_145850_b.field_72995_K) {
            for (EntityItem entity : items) {
                NBTTagCompound enchant;
                short id;
                NBTTagList enchants;
                ItemStack item = entity.func_92059_d();
                if (item.func_77973_b() != Items.field_151134_bR || (enchants = Items.field_151134_bR.func_92110_g(item)) == null || enchants.func_74745_c() <= 0 || !this.isEnchantmentValid(id = (enchant = enchants.func_150305_b(0)).func_74765_d("id"))) continue;
                this.advanceStage();
                return;
            }
        }
    }

    public void func_145845_h() {
        Object pylon2;
        int n;
        Object object;
        if (this.func_145832_p() < PYLON_LOCATIONS.length) {
            object = PYLON_LOCATIONS[this.func_145832_p()];
            int n2 = ((int[][])object).length;
            for (n = 0; n < n2; ++n) {
                pylon2 = object[n];
                TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + pylon2[0], this.field_145848_d + pylon2[1], this.field_145849_e + pylon2[2]);
                if (tile == null || !(tile instanceof TilePylon)) continue;
                ((TilePylon)tile).activated = false;
            }
        }
        if (!TileEnchanter.canEnchanterExist(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145832_p())) {
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150368_y, 0, 3);
            for (int i = 0; i < 50; ++i) {
                float red = (float)Math.random();
                float green = (float)Math.random();
                float blue = (float)Math.random();
                Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, red, green, blue, (float)Math.random() * 0.15f + 0.15f, (float)(Math.random() - 0.5) * 0.25f, (float)(Math.random() - 0.5) * 0.25f, (float)(Math.random() - 0.5) * 0.25f);
            }
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:enchanterBlock", 0.5f, 10.0f);
        }
        switch (this.stage) {
            case 1: {
                if (this.stageTicks % 20 != 0) break;
                Object items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 2), (double)this.field_145848_d, (double)(this.field_145849_e - 2), (double)(this.field_145851_c + 3), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 3)));
                int count = items.size();
                int addedEnch = 0;
                if (count > 0 && !this.field_145850_b.field_72995_K) {
                    Iterator blue = items.iterator();
                    while (blue.hasNext()) {
                        NBTTagList enchants;
                        EntityItem entity = (EntityItem)blue.next();
                        ItemStack item = entity.func_92059_d();
                        if (item.func_77973_b() != Items.field_151134_bR || (enchants = Items.field_151134_bR.func_92110_g(item)) == null || enchants.func_74745_c() <= 0) continue;
                        NBTTagCompound enchant = enchants.func_150305_b(0);
                        short enchantId = enchant.func_74765_d("id");
                        short enchantLvl = enchant.func_74765_d("lvl");
                        if (this.hasEnchantAlready(enchantId) || !this.isEnchantmentValid(enchantId)) continue;
                        this.enchants.add(new EnchantmentData(enchantId, enchantLvl));
                        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:ding", 1.0f, 1.0f);
                        addedEnch = 1;
                        break;
                    }
                }
                if (addedEnch != 0) break;
                if (this.enchants.isEmpty()) {
                    this.stage = 0;
                    break;
                }
                this.advanceStage();
                break;
            }
            case 2: {
                Object items;
                for (Object pylon2 : PYLON_LOCATIONS[this.func_145832_p()]) {
                    TilePylon pylonTile = (TilePylon)this.field_145850_b.func_147438_o(this.field_145851_c + pylon2[0], this.field_145848_d + pylon2[1], this.field_145849_e + pylon2[2]);
                    if (pylonTile == null) continue;
                    pylonTile.activated = true;
                    pylonTile.centerX = this.field_145851_c;
                    pylonTile.centerY = this.field_145848_d;
                    pylonTile.centerZ = this.field_145849_e;
                }
                if (this.manaRequired == -1) {
                    this.manaRequired = 0;
                    items = this.enchants.iterator();
                    while (items.hasNext()) {
                        EnchantmentData data = (EnchantmentData)items.next();
                        Enchantment ench = Enchantment.field_77331_b[data.enchant];
                        this.manaRequired += (int)(5000.0f * ((float)(15 - Math.min(15, ench.func_77324_c())) * 1.05f) * ((3.0f + (float)(data.level * data.level)) * 0.25f) * (0.9f + (float)this.enchants.size() * 0.05f));
                    }
                } else {
                    if (this.mana >= this.manaRequired) {
                        this.manaRequired = 0;
                        items = PYLON_LOCATIONS[this.func_145832_p()];
                        int data = ((int[][])items).length;
                        for (n = 0; n < data; ++n) {
                            pylon2 = items[n];
                            ((TilePylon)this.field_145850_b.func_147438_o((int)(this.field_145851_c + pylon2[0]), (int)(this.field_145848_d + pylon2[1]), (int)(this.field_145849_e + pylon2[2]))).activated = false;
                        }
                        this.advanceStage();
                        break;
                    }
                    ISparkEntity spark = this.getAttachedSpark();
                    if (spark == null) break;
                    List<ISparkEntity> sparkEntities = SparkHelper.getSparksAround(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
                    for (ISparkEntity otherSpark : sparkEntities) {
                        if (spark == otherSpark || otherSpark.getAttachedTile() == null || !(otherSpark.getAttachedTile() instanceof IManaPool)) continue;
                        otherSpark.registerTransfer(spark);
                    }
                }
                break;
            }
            case 3: {
                if (this.stageTicks < 100) break;
                object = this.enchants.iterator();
                while (object.hasNext()) {
                    EnchantmentData data = (EnchantmentData)object.next();
                    if (EnchantmentHelper.func_77506_a((int)data.enchant, (ItemStack)this.itemToEnchant) != 0) continue;
                    this.itemToEnchant.func_77966_a(Enchantment.field_77331_b[data.enchant], data.level);
                }
                this.enchants.clear();
                this.manaRequired = -1;
                this.mana = 0;
                this.craftingFanciness();
                this.advanceStage();
                break;
            }
            case 4: {
                if (this.stageTicks < 20) break;
                this.advanceStage();
            }
        }
        if (this.stage != 0) {
            ++this.stageTicks;
        }
    }

    public void advanceStage() {
        ++this.stage;
        if (this.stage == 4) {
            this.stage3EndTicks = this.stageTicks;
        } else if (this.stage == 5) {
            this.stage = 0;
            this.stage3EndTicks = 0;
        }
        this.stageTicks = 0;
        this.sync();
    }

    public void craftingFanciness() {
        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:enchanterEnchant", 1.0f, 1.0f);
        for (int i = 0; i < 25; ++i) {
            float red = (float)Math.random();
            float green = (float)Math.random();
            float blue = (float)Math.random();
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, this.field_145848_d + 1, (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, red, green, blue, (float)Math.random(), 10);
        }
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public int getCurrentMana() {
        return this.mana;
    }

    @Override
    public boolean isFull() {
        return this.mana >= this.manaRequired;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(this.manaRequired, this.mana + mana);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return this.manaRequired > 0;
    }

    public void sync() {
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
        cmp.func_74768_a(TAG_MANA_REQUIRED, this.manaRequired);
        cmp.func_74768_a(TAG_STAGE, this.stage);
        cmp.func_74768_a(TAG_STAGE_TICKS, this.stageTicks);
        cmp.func_74768_a(TAG_STAGE_3_END_TICKS, this.stage3EndTicks);
        NBTTagCompound itemCmp = new NBTTagCompound();
        if (this.itemToEnchant != null) {
            this.itemToEnchant.func_77955_b(itemCmp);
        }
        cmp.func_74782_a(TAG_ITEM, (NBTBase)itemCmp);
        String enchStr = "";
        for (EnchantmentData data : this.enchants) {
            enchStr = enchStr + data.enchant + ":" + data.level + ",";
        }
        cmp.func_74778_a(TAG_ENCHANTS, enchStr.isEmpty() ? enchStr : enchStr.substring(0, enchStr.length() - 1));
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.func_74762_e(TAG_MANA);
        this.manaRequired = cmp.func_74762_e(TAG_MANA_REQUIRED);
        this.stage = cmp.func_74762_e(TAG_STAGE);
        this.stageTicks = cmp.func_74762_e(TAG_STAGE_TICKS);
        this.stage3EndTicks = cmp.func_74762_e(TAG_STAGE_3_END_TICKS);
        NBTTagCompound itemCmp = cmp.func_74775_l(TAG_ITEM);
        this.itemToEnchant = ItemStack.func_77949_a((NBTTagCompound)itemCmp);
        this.enchants.clear();
        String enchStr = cmp.func_74779_i(TAG_ENCHANTS);
        if (!enchStr.isEmpty()) {
            String[] enchTokens;
            for (String token : enchTokens = enchStr.split(",")) {
                String[] entryTokens = token.split(":");
                int id = Integer.parseInt(entryTokens[0]);
                int lvl = Integer.parseInt(entryTokens[1]);
                this.enchants.add(new EnchantmentData(id, lvl));
            }
        }
    }

    private boolean hasEnchantAlready(int enchant) {
        for (EnchantmentData data : this.enchants) {
            if (data.enchant != enchant) continue;
            return true;
        }
        return false;
    }

    public boolean isEnchantmentValid(short id) {
        Enchantment ench = Enchantment.field_77331_b[id];
        if (!ench.func_92089_a(this.itemToEnchant) || !ench.field_77351_y.func_77557_a(this.itemToEnchant.func_77973_b())) {
            return false;
        }
        for (EnchantmentData data : this.enchants) {
            Enchantment otherEnch = Enchantment.field_77331_b[data.enchant];
            if (otherEnch.func_77326_a(ench) && ench.func_77326_a(otherEnch)) continue;
            return false;
        }
        return true;
    }

    public static boolean canEnchanterExist(World world, int x, int y, int z, int meta) {
        for (int[] obsidian : OBSIDIAN_LOCATIONS) {
            if (world.func_147439_a(obsidian[0] + x, obsidian[1] + y, obsidian[2] + z) == Blocks.field_150343_Z) continue;
            return false;
        }
        for (int[] pylon : PYLON_LOCATIONS[meta]) {
            if (world.func_147439_a(pylon[0] + x, pylon[1] + y, pylon[2] + z) == ModBlocks.pylon && BotaniaAPI.internalHandler.isBotaniaFlower(world, pylon[0] + x, pylon[1] + y - 1, pylon[2] + z)) continue;
            return false;
        }
        for (int[] flower : FLOWER_LOCATIONS) {
            if (BotaniaAPI.internalHandler.isBotaniaFlower(world, flower[0] + x, flower[1] + y, flower[2] + z)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean canAttachSpark(ItemStack stack) {
        return true;
    }

    @Override
    public void attachSpark(ISparkEntity entity) {
    }

    @Override
    public ISparkEntity getAttachedSpark() {
        List sparks = this.field_145850_b.func_72872_a(ISparkEntity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d + 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1)));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    @Override
    public boolean areIncomingTranfersDone() {
        return this.stage == 3;
    }

    @Override
    public int getAvailableSpaceForMana() {
        return Math.max(0, this.manaRequired - this.getCurrentMana());
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        if (this.manaRequired > 0 && this.itemToEnchant != null) {
            int x = res.func_78326_a() / 2 + 20;
            int y = res.func_78328_b() / 2 - 8;
            RenderHelper.renderProgressPie(x, y, (float)this.mana / (float)this.manaRequired, this.itemToEnchant);
        }
    }

    private static class EnchantmentData {
        public int enchant;
        public int level;

        public EnchantmentData(int enchant, int level) {
            this.enchant = enchant;
            this.level = level;
        }
    }
}

