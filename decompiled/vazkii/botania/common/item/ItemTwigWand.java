/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item;

import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.wand.ICoordBoundItem;
import vazkii.botania.api.wand.ITileBound;
import vazkii.botania.api.wand.IWandBindable;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockPistonRelay;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.Item16Colors;
import vazkii.botania.common.item.ModItems;

public class ItemTwigWand
extends Item16Colors
implements ICoordBoundItem {
    IIcon[] icons;
    private static final String TAG_COLOR1 = "color1";
    private static final String TAG_COLOR2 = "color2";
    private static final String TAG_BOUND_TILE_X = "boundTileX";
    private static final String TAG_BOUND_TILE_Y = "boundTileY";
    private static final String TAG_BOUND_TILE_Z = "boundTileZ";
    private static final String TAG_BIND_MODE = "bindMode";

    public ItemTwigWand() {
        super("twigWand");
        this.func_77625_d(1);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        TileEntity tile;
        Block block = par3World.func_147439_a(par4, par5, par6);
        ChunkCoordinates boundTile = ItemTwigWand.getBoundTile(par1ItemStack);
        if (boundTile.field_71572_b != -1 && par2EntityPlayer.func_70093_af() && (boundTile.field_71574_a != par4 || boundTile.field_71572_b != par5 || boundTile.field_71573_c != par6)) {
            tile = par3World.func_147438_o(boundTile.field_71574_a, boundTile.field_71572_b, boundTile.field_71573_c);
            if (tile instanceof IWandBindable) {
                if (((IWandBindable)tile).bindTo(par2EntityPlayer, par1ItemStack, par4, par5, par6, par7)) {
                    Vector3 orig = new Vector3((double)boundTile.field_71574_a + 0.5, (double)boundTile.field_71572_b + 0.5, (double)boundTile.field_71573_c + 0.5);
                    Vector3 end = new Vector3((double)par4 + 0.5, (double)par5 + 0.5, (double)par6 + 0.5);
                    ItemTwigWand.doParticleBeam(par3World, orig, end);
                    VanillaPacketDispatcher.dispatchTEToNearbyPlayers(par3World, boundTile.field_71574_a, boundTile.field_71572_b, boundTile.field_71573_c);
                    ItemTwigWand.setBoundTile(par1ItemStack, 0, -1, 0);
                }
                return true;
            }
            ItemTwigWand.setBoundTile(par1ItemStack, 0, -1, 0);
        } else if (par2EntityPlayer.func_70093_af()) {
            block.rotateBlock(par3World, par4, par5, par6, ForgeDirection.getOrientation((int)par7));
            if (par3World.field_72995_K) {
                par2EntityPlayer.func_71038_i();
            }
        }
        if (block == Blocks.field_150368_y && ConfigHandler.enchanterEnabled) {
            int meta = -1;
            if (TileEnchanter.canEnchanterExist(par3World, par4, par5, par6, 0)) {
                meta = 0;
            } else if (TileEnchanter.canEnchanterExist(par3World, par4, par5, par6, 1)) {
                meta = 1;
            }
            if (meta != -1 && !par3World.field_72995_K) {
                par3World.func_147465_d(par4, par5, par6, ModBlocks.enchanter, meta, 3);
                par2EntityPlayer.func_71064_a((StatBase)ModAchievements.enchanterMake, 1);
                par3World.func_72908_a((double)par4, (double)par5, (double)par6, "botania:enchanterBlock", 0.5f, 0.6f);
                for (int i = 0; i < 50; ++i) {
                    float red = (float)Math.random();
                    float green = (float)Math.random();
                    float blue = (float)Math.random();
                    double x = (Math.random() - 0.5) * 6.0;
                    double y = (Math.random() - 0.5) * 6.0;
                    double z = (Math.random() - 0.5) * 6.0;
                    float velMul = 0.07f;
                    Botania.proxy.wispFX(par3World, (double)par4 + 0.5 + x, (double)par5 + 0.5 + y, (double)par6 + 0.5 + z, red, green, blue, (float)Math.random() * 0.15f + 0.15f, (float)(-x) * velMul, (float)(-y) * velMul, (float)(-z) * velMul);
                }
            }
        } else {
            if (block instanceof IWandable) {
                tile = par3World.func_147438_o(par4, par5, par6);
                boolean bindable = tile instanceof IWandBindable;
                boolean wanded = false;
                if (ItemTwigWand.getBindMode(par1ItemStack) && bindable && par2EntityPlayer.func_70093_af() && ((IWandBindable)tile).canSelect(par2EntityPlayer, par1ItemStack, par4, par5, par6, par7)) {
                    if (boundTile.field_71574_a == par4 && boundTile.field_71572_b == par5 && boundTile.field_71573_c == par6) {
                        ItemTwigWand.setBoundTile(par1ItemStack, 0, -1, 0);
                    } else {
                        ItemTwigWand.setBoundTile(par1ItemStack, par4, par5, par6);
                    }
                    if (par3World.field_72995_K) {
                        par2EntityPlayer.func_71038_i();
                    }
                    par3World.func_72956_a((Entity)par2EntityPlayer, "botania:ding", 0.1f, 1.0f);
                    wanded = true;
                } else {
                    wanded = ((IWandable)block).onUsedByWand(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, par7);
                    if (wanded && par3World.field_72995_K) {
                        par2EntityPlayer.func_71038_i();
                    }
                }
                return wanded;
            }
            if (BlockPistonRelay.playerPositions.containsKey(par2EntityPlayer.func_70005_c_()) && !par3World.field_72995_K) {
                String bindPos = BlockPistonRelay.playerPositions.get(par2EntityPlayer.func_70005_c_());
                String currentPos = BlockPistonRelay.getCoordsAsString(par3World.field_73011_w.field_76574_g, par4, par5, par6);
                BlockPistonRelay.playerPositions.remove(par2EntityPlayer.func_70005_c_());
                BlockPistonRelay.mappedPositions.put(bindPos, currentPos);
                BlockPistonRelay.WorldData.get(par3World).func_76185_a();
                par3World.func_72956_a((Entity)par2EntityPlayer, "botania:ding", 1.0f, 1.0f);
            }
        }
        return false;
    }

    public static void doParticleBeam(World world, Vector3 orig, Vector3 end) {
        if (!world.field_72995_K) {
            return;
        }
        Vector3 diff = end.copy().sub(orig);
        Vector3 movement = diff.copy().normalize().multiply(0.05);
        int iters = (int)(diff.mag() / movement.mag());
        float huePer = 1.0f / (float)iters;
        float hueSum = (float)Math.random();
        Vector3 currentPos = orig.copy();
        for (int i = 0; i < iters; ++i) {
            float hue = (float)i * huePer + hueSum;
            Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
            float r = (float)color.getRed() / 255.0f;
            float g = (float)color.getGreen() / 255.0f;
            float b = (float)color.getBlue() / 255.0f;
            Botania.proxy.setSparkleFXNoClip(true);
            Botania.proxy.sparkleFX(world, currentPos.x, currentPos.y, currentPos.z, r, g, b, 0.5f, 4);
            Botania.proxy.setSparkleFXNoClip(false);
            currentPos.add(movement);
        }
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        ChunkCoordinates coords = ItemTwigWand.getBoundTile(par1ItemStack);
        TileEntity tile = par2World.func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        if (tile == null || !(tile instanceof IWandBindable)) {
            ItemTwigWand.setBoundTile(par1ItemStack, 0, -1, 0);
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K && player.func_70093_af()) {
            ItemTwigWand.setBindMode(stack, !ItemTwigWand.getBindMode(stack));
            world.func_72956_a((Entity)player, "botania:ding", 0.1f, 1.0f);
        }
        return stack;
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 3 && !ItemTwigWand.getBindMode(stack)) {
            pass = 0;
        }
        return this.icons[Math.min(this.icons.length - 1, pass)];
    }

    @Override
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par2 == 0 || par2 == 3) {
            return 0xFFFFFF;
        }
        float[] color = EntitySheep.field_70898_d[par2 == 1 ? ItemTwigWand.getColor1(par1ItemStack) : ItemTwigWand.getColor2(par1ItemStack)];
        return new Color(color[0], color[1], color[2]).getRGB();
    }

    public boolean func_77623_v() {
        return true;
    }

    public int getRenderPasses(int metadata) {
        return 4;
    }

    @Override
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(ItemTwigWand.forColors(i, i));
        }
    }

    @Override
    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack);
    }

    public void func_77624_a(ItemStack stack, EntityPlayer p, List list, boolean adv) {
        list.add(StatCollector.func_74838_a((String)ItemTwigWand.getModeString(stack)));
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public static ItemStack forColors(int color1, int color2) {
        ItemStack stack = new ItemStack(ModItems.twigWand);
        ItemNBTHelper.setInt(stack, TAG_COLOR1, color1);
        ItemNBTHelper.setInt(stack, TAG_COLOR2, color2);
        return stack;
    }

    public static int getColor1(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_COLOR1, 0);
    }

    public static int getColor2(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_COLOR2, 0);
    }

    public static void setBoundTile(ItemStack stack, int x, int y, int z) {
        ItemNBTHelper.setInt(stack, TAG_BOUND_TILE_X, x);
        ItemNBTHelper.setInt(stack, TAG_BOUND_TILE_Y, y);
        ItemNBTHelper.setInt(stack, TAG_BOUND_TILE_Z, z);
    }

    public static ChunkCoordinates getBoundTile(ItemStack stack) {
        int x = ItemNBTHelper.getInt(stack, TAG_BOUND_TILE_X, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_BOUND_TILE_Y, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_BOUND_TILE_Z, 0);
        return new ChunkCoordinates(x, y, z);
    }

    public static boolean getBindMode(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_BIND_MODE, true);
    }

    public static void setBindMode(ItemStack stack, boolean bindMode) {
        ItemNBTHelper.setBoolean(stack, TAG_BIND_MODE, bindMode);
    }

    public static String getModeString(ItemStack stack) {
        return "botaniamisc.wandMode." + (ItemTwigWand.getBindMode(stack) ? "bind" : "function");
    }

    @Override
    public ChunkCoordinates getBinding(ItemStack stack) {
        TileEntity tile;
        ChunkCoordinates bound = ItemTwigWand.getBoundTile(stack);
        if (bound.field_71572_b != -1) {
            return bound;
        }
        MovingObjectPosition pos = Minecraft.func_71410_x().field_71476_x;
        if (pos != null && (tile = Minecraft.func_71410_x().field_71441_e.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d)) != null && tile instanceof ITileBound) {
            ChunkCoordinates coords = ((ITileBound)tile).getBinding();
            return coords;
        }
        return null;
    }
}

