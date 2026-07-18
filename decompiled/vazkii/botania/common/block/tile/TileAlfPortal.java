/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.MinecraftForge
 */
package vazkii.botania.common.block.tile;

import com.google.common.base.Function;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.recipe.ElvenPortalUpdateEvent;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemLexicon;
import vazkii.botania.common.lexicon.LexiconData;

public class TileAlfPortal
extends TileMod {
    private static final int[][] LIVINGWOOD_POSITIONS = new int[][]{{-1, 0, 0}, {1, 0, 0}, {-2, 1, 0}, {2, 1, 0}, {-2, 3, 0}, {2, 3, 0}, {-1, 4, 0}, {1, 4, 0}};
    private static final int[][] GLIMMERING_LIVINGWOOD_POSITIONS = new int[][]{{-2, 2, 0}, {2, 2, 0}, {0, 4, 0}};
    private static final int[][] PYLON_POSITIONS = new int[][]{{-3, 1, 3}, {3, 1, 3}};
    private static final int[][] POOL_POSITIONS = new int[][]{{-3, 0, 3}, {3, 0, 3}};
    private static final int[][] AIR_POSITIONS = new int[][]{{-1, 1, 0}, {0, 1, 0}, {1, 1, 0}, {-1, 2, 0}, {0, 2, 0}, {1, 2, 0}, {-1, 3, 0}, {0, 3, 0}, {1, 3, 0}};
    private static final String TAG_TICKS_OPEN = "ticksOpen";
    private static final String TAG_TICKS_SINCE_LAST_ITEM = "ticksSinceLastItem";
    private static final String TAG_STACK_COUNT = "stackCount";
    private static final String TAG_STACK = "portalStack";
    private static final String TAG_PORTAL_FLAG = "_elvenPortal";
    List<ItemStack> stacksIn = new ArrayList<ItemStack>();
    public int ticksOpen = 0;
    int ticksSinceLastItem = 0;
    private boolean closeNow = false;
    private boolean hasUnloadedParts = false;
    private static final Function<int[], int[]> CONVERTER_X_Z = new Function<int[], int[]>(){

        public int[] apply(int[] input) {
            return new int[]{input[2], input[1], input[0]};
        }
    };
    private static final Function<double[], double[]> CONVERTER_X_Z_FP = new Function<double[], double[]>(){

        public double[] apply(double[] input) {
            return new double[]{input[2], input[1], input[0]};
        }
    };
    private static final Function<int[], int[]> CONVERTER_Z_SWAP = new Function<int[], int[]>(){

        public int[] apply(int[] input) {
            return new int[]{input[0], input[1], -input[2]};
        }
    };

    public static MultiblockSet makeMultiblockSet() {
        Multiblock mb = new Multiblock();
        for (int[] l : LIVINGWOOD_POSITIONS) {
            mb.addComponent(l[0], l[1] + 1, l[2], ModBlocks.livingwood, 0);
        }
        for (int[] g : GLIMMERING_LIVINGWOOD_POSITIONS) {
            mb.addComponent(g[0], g[1] + 1, g[2], ModBlocks.livingwood, 5);
        }
        for (int[] p : PYLON_POSITIONS) {
            mb.addComponent(-p[0], p[1] + 1, -p[2], ModBlocks.pylon, 1);
        }
        for (int[] p : POOL_POSITIONS) {
            mb.addComponent(-p[0], p[1] + 1, -p[2], ModBlocks.pool, 0);
        }
        mb.addComponent(0, 1, 0, ModBlocks.alfPortal, 0);
        mb.setRenderOffset(0, -1, 0);
        return mb.makeSet();
    }

    public void func_145845_h() {
        int meta = this.func_145832_p();
        if (meta == 0) {
            this.ticksOpen = 0;
            return;
        }
        int newMeta = this.getValidMetadata();
        if (!this.hasUnloadedParts) {
            ++this.ticksOpen;
            AxisAlignedBB aabb = this.getPortalAABB();
            boolean open = this.ticksOpen > 60;
            ElvenPortalUpdateEvent event = new ElvenPortalUpdateEvent(this, aabb, open, this.stacksIn);
            MinecraftForge.EVENT_BUS.post((Event)event);
            if (this.ticksOpen > 60) {
                ++this.ticksSinceLastItem;
                if (ConfigHandler.elfPortalParticlesEnabled) {
                    this.blockParticle(meta);
                }
                List items = this.field_145850_b.func_72872_a(EntityItem.class, aabb);
                if (!this.field_145850_b.field_72995_K) {
                    for (EntityItem item : items) {
                        ItemStack stack;
                        if (item.field_70128_L || (stack = item.func_92059_d()) == null || stack.func_77973_b() instanceof IElvenItem && ((IElvenItem)stack.func_77973_b()).isElvenItem(stack) || item.getEntityData().func_74764_b(TAG_PORTAL_FLAG)) continue;
                        item.func_70106_y();
                        this.addItem(stack);
                        this.ticksSinceLastItem = 0;
                    }
                }
                if (this.ticksSinceLastItem >= 4 && !this.field_145850_b.field_72995_K) {
                    this.resolveRecipes();
                }
            }
        } else {
            this.closeNow = false;
        }
        if (this.closeNow) {
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
            for (int i = 0; i < 36; ++i) {
                this.blockParticle(meta);
            }
            this.closeNow = false;
        } else if (newMeta != meta) {
            if (newMeta == 0) {
                for (int i = 0; i < 36; ++i) {
                    this.blockParticle(meta);
                }
            }
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, newMeta, 3);
        }
        this.hasUnloadedParts = false;
    }

    private void blockParticle(int meta) {
        int i = this.field_145850_b.field_73012_v.nextInt(AIR_POSITIONS.length);
        double[] pos = new double[]{(float)AIR_POSITIONS[i][0] + 0.5f, (float)AIR_POSITIONS[i][1] + 0.5f, (float)AIR_POSITIONS[i][2] + 0.5f};
        if (meta == 2) {
            pos = (double[])CONVERTER_X_Z_FP.apply((Object)pos);
        }
        float motionMul = 0.2f;
        Botania.proxy.wispFX(this.func_145831_w(), (double)this.field_145851_c + pos[0], (double)this.field_145848_d + pos[1], (double)this.field_145849_e + pos[2], (float)(Math.random() * 0.25), (float)(Math.random() * 0.5 + 0.5), (float)(Math.random() * 0.25), (float)(Math.random() * (double)0.15f + (double)0.1f), (float)(Math.random() - 0.5) * motionMul, (float)(Math.random() - 0.5) * motionMul, (float)(Math.random() - 0.5) * motionMul);
    }

    public boolean onWanded() {
        int newMeta;
        int meta = this.func_145832_p();
        if (meta == 0 && (newMeta = this.getValidMetadata()) != 0) {
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, newMeta, 3);
            return true;
        }
        return false;
    }

    AxisAlignedBB getPortalAABB() {
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d + 1), (double)this.field_145849_e, (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 4), (double)(this.field_145849_e + 1));
        if (this.func_145832_p() == 2) {
            aabb = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d + 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 4), (double)(this.field_145849_e + 2));
        }
        return aabb;
    }

    void addItem(ItemStack stack) {
        int size = stack.field_77994_a;
        stack.field_77994_a = 1;
        for (int i = 0; i < size; ++i) {
            this.stacksIn.add(stack.func_77946_l());
        }
    }

    void resolveRecipes() {
        int i = 0;
        for (ItemStack stack : this.stacksIn) {
            if (stack != null && stack.func_77973_b() instanceof ILexicon) {
                ((ILexicon)stack.func_77973_b()).unlockKnowledge(stack, BotaniaAPI.elvenKnowledge);
                ItemLexicon.setForcedPage(stack, LexiconData.elvenMessage.getUnlocalizedName());
                this.spawnItem(stack);
                this.stacksIn.remove(i);
                return;
            }
            ++i;
        }
        for (RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
            if (!recipe.matches(this.stacksIn, false)) continue;
            recipe.matches(this.stacksIn, true);
            this.spawnItem(recipe.getOutput().func_77946_l());
            break;
        }
    }

    void spawnItem(ItemStack stack) {
        EntityItem item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, stack);
        item.getEntityData().func_74757_a(TAG_PORTAL_FLAG, true);
        this.field_145850_b.func_72838_d((Entity)item);
        this.ticksSinceLastItem = 0;
    }

    @Override
    public void func_145841_b(NBTTagCompound cmp) {
        super.func_145841_b(cmp);
        cmp.func_74768_a(TAG_STACK_COUNT, this.stacksIn.size());
        int i = 0;
        for (ItemStack stack : this.stacksIn) {
            NBTTagCompound stackcmp = new NBTTagCompound();
            stack.func_77955_b(stackcmp);
            cmp.func_74782_a(TAG_STACK + i, (NBTBase)stackcmp);
            ++i;
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound cmp) {
        super.func_145839_a(cmp);
        int count = cmp.func_74762_e(TAG_STACK_COUNT);
        this.stacksIn.clear();
        for (int i = 0; i < count; ++i) {
            NBTTagCompound stackcmp = cmp.func_74775_l(TAG_STACK + i);
            ItemStack stack = ItemStack.func_77949_a((NBTTagCompound)stackcmp);
            this.stacksIn.add(stack);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_TICKS_OPEN, this.ticksOpen);
        cmp.func_74768_a(TAG_TICKS_SINCE_LAST_ITEM, this.ticksSinceLastItem);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.ticksOpen = cmp.func_74762_e(TAG_TICKS_OPEN);
        this.ticksSinceLastItem = cmp.func_74762_e(TAG_TICKS_SINCE_LAST_ITEM);
    }

    private int getValidMetadata() {
        if (this.checkConverter(null)) {
            return 1;
        }
        if (this.checkConverter(CONVERTER_X_Z)) {
            return 2;
        }
        return 0;
    }

    private boolean checkConverter(Function<int[], int[]> baseConverter) {
        return this.checkMultipleConverters(baseConverter) || this.checkMultipleConverters(CONVERTER_Z_SWAP, baseConverter);
    }

    private boolean checkMultipleConverters(Function<int[], int[]> ... converters) {
        if (!this.check2DArray(AIR_POSITIONS, Blocks.field_150350_a, -1, converters)) {
            return false;
        }
        if (!this.check2DArray(LIVINGWOOD_POSITIONS, ModBlocks.livingwood, 0, converters)) {
            return false;
        }
        if (!this.check2DArray(GLIMMERING_LIVINGWOOD_POSITIONS, ModBlocks.livingwood, 5, converters)) {
            return false;
        }
        if (!this.check2DArray(PYLON_POSITIONS, ModBlocks.pylon, 1, converters)) {
            return false;
        }
        if (!this.check2DArray(POOL_POSITIONS, ModBlocks.pool, -1, converters)) {
            return false;
        }
        this.lightPylons(converters);
        return true;
    }

    private void lightPylons(Function<int[], int[]> ... converters) {
        if (this.ticksOpen < 50) {
            return;
        }
        int cost = this.ticksOpen == 50 ? 75000 : 2;
        for (int[] pos : PYLON_POSITIONS) {
            for (Function<int[], int[]> f : converters) {
                if (f == null) continue;
                pos = (int[])f.apply((Object)pos);
            }
            TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + pos[0], this.field_145848_d + pos[1], this.field_145849_e + pos[2]);
            if (tile instanceof TilePylon) {
                TilePylon pylon = (TilePylon)tile;
                pylon.activated = true;
                pylon.centerX = this.field_145851_c;
                pylon.centerY = this.field_145848_d;
                pylon.centerZ = this.field_145849_e;
            }
            if (!((tile = this.field_145850_b.func_147438_o(this.field_145851_c + pos[0], this.field_145848_d + pos[1] - 1, this.field_145849_e + pos[2])) instanceof TilePool)) continue;
            TilePool pool = (TilePool)tile;
            if (pool.getCurrentMana() < cost) {
                this.closeNow = true;
                continue;
            }
            if (this.field_145850_b.field_72995_K) continue;
            pool.recieveMana(-cost);
        }
    }

    private boolean check2DArray(int[][] positions, Block block, int meta, Function<int[], int[]> ... converters) {
        for (int[] pos : positions) {
            for (Function<int[], int[]> f : converters) {
                if (f == null) continue;
                pos = (int[])f.apply((Object)pos);
            }
            if (this.checkPosition(pos, block, meta)) continue;
            return false;
        }
        return true;
    }

    private boolean checkPosition(int[] pos, Block block, int meta) {
        int x = this.field_145851_c + pos[0];
        int y = this.field_145848_d + pos[1];
        int z = this.field_145849_e + pos[2];
        if (!this.field_145850_b.func_72899_e(x, y, z)) {
            this.hasUnloadedParts = true;
            return true;
        }
        Block blockat = this.field_145850_b.func_147439_a(x, y, z);
        if (block == Blocks.field_150350_a ? blockat.isAir((IBlockAccess)this.field_145850_b, x, y, z) : blockat == block) {
            if (meta == -1) {
                return true;
            }
            int metaat = this.field_145850_b.func_72805_g(x, y, z);
            return meta == metaat;
        }
        return false;
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}

