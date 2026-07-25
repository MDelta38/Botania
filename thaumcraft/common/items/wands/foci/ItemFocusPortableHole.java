/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileHole;

public class ItemFocusPortableHole
extends ItemFocusBasic {
    IIcon depthIcon = null;
    private static final AspectList cost = new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.AIR, 10);

    public ItemFocusPortableHole() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BPH" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.depthIcon = ir.func_94245_a("thaumcraft:focus_portablehole_depth");
        this.icon = ir.func_94245_a("thaumcraft:focus_portablehole");
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
        return this.depthIcon;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 594985;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return cost.copy();
    }

    public static boolean createHole(World world, int ii, int jj, int kk, int side, byte count, int max) {
        Block bi = world.func_147439_a(ii, jj, kk);
        if (!(world.func_147438_o(ii, jj, kk) != null || ThaumcraftApi.portableHoleBlackList.contains(bi) || bi == Blocks.field_150357_h || bi == ConfigBlocks.blockHole || bi.isAir((IBlockAccess)world, ii, jj, kk) || bi.func_149742_c(world, ii, jj, kk) || bi.func_149712_f(world, ii, jj, kk) == -1.0f)) {
            TileHole ts = new TileHole(bi, world.func_72805_g(ii, jj, kk), (short)max, count, (byte)side, null);
            world.func_147465_d(ii, jj, kk, Blocks.field_150350_a, 0, 0);
            if (world.func_147465_d(ii, jj, kk, ConfigBlocks.blockHole, 0, 0)) {
                world.func_147455_a(ii, jj, kk, (TileEntity)ts);
            }
            world.func_147471_g(ii, jj, kk);
            Thaumcraft.proxy.blockSparkle(world, ii, jj, kk, 0x400040, 1);
            return true;
        }
        return false;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block bi;
            if (world.field_73011_w.field_76574_g == Config.dimensionOuterId) {
                if (!world.field_72995_K) {
                    world.func_72908_a((double)mop.field_72311_b + 0.5, (double)mop.field_72312_c + 0.5, (double)mop.field_72309_d + 0.5, "thaumcraft:wandfail", 1.0f, 1.0f);
                }
                player.func_71038_i();
                return itemstack;
            }
            int ii = mop.field_72311_b;
            int jj = mop.field_72312_c;
            int kk = mop.field_72309_d;
            int enlarge = wand.getFocusEnlarge(itemstack);
            int distance = 0;
            int maxdis = 33 + enlarge * 8;
            block8: for (distance = 0; distance < maxdis && !ThaumcraftApi.portableHoleBlackList.contains(bi = world.func_147439_a(ii, jj, kk)) && bi != Blocks.field_150357_h && bi != ConfigBlocks.blockHole && !bi.isAir((IBlockAccess)world, ii, jj, kk) && bi.func_149712_f(world, ii, jj, kk) != -1.0f; ++distance) {
                switch (mop.field_72310_e) {
                    case 0: {
                        ++jj;
                        continue block8;
                    }
                    case 1: {
                        --jj;
                        continue block8;
                    }
                    case 2: {
                        ++kk;
                        continue block8;
                    }
                    case 3: {
                        --kk;
                        continue block8;
                    }
                    case 4: {
                        ++ii;
                        continue block8;
                    }
                    case 5: {
                        --ii;
                    }
                }
            }
            AspectList c = this.getVisCost(itemstack);
            for (Aspect a : c.getAspects()) {
                c.merge(a, c.getAmount(a) * distance);
            }
            if (wand.consumeAllVis(itemstack, player, c, true, false)) {
                int di = this.getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.extend);
                short dur = (short)(120 + 60 * di);
                ItemFocusPortableHole.createHole(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, (byte)(distance + 1), dur);
            }
            player.func_71038_i();
            if (!world.field_72995_K) {
                world.func_72908_a((double)mop.field_72311_b + 0.5, (double)mop.field_72312_c + 0.5, (double)mop.field_72309_d + 0.5, "mob.endermen.portal", 1.0f, 1.0f);
            }
        }
        return itemstack;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.extend};
            }
        }
        return null;
    }
}

