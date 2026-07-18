/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.item.rod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.item.IBlockProvider;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.lib.LibMisc;

public class ItemTerraformRod
extends ItemMod
implements IManaUsingItem,
IBlockProvider,
ICraftAchievement {
    private static final int COST_PER = 55;
    static final List<String> validBlocks = Arrays.asList("stone", "dirt", "grass", "sand", "gravel", "hardenedClay", "snowLayer", "mycelium", "podzol", "sandstone", "blockDiorite", "stoneDiorite", "blockGranite", "stoneGranite", "blockAndesite", "stoneAndesite", "marble", "blockMarble", "limestone", "blockLimestone");

    public ItemTerraformRod() {
        this.func_77625_d(1);
        this.func_77655_b("terraformRod");
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (count != this.func_77626_a(stack) && count % 10 == 0) {
            this.terraform(stack, player.field_70170_p, player);
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public void terraform(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int range = IManaProficiencyArmor.Helper.hasProficiency(par3EntityPlayer) ? 22 : 16;
        int xCenter = (int)par3EntityPlayer.field_70165_t;
        int yCenter = (int)par3EntityPlayer.field_70163_u - (par2World.field_72995_K ? 2 : 1);
        int zCenter = (int)par3EntityPlayer.field_70161_v;
        if (yCenter < 62) {
            return;
        }
        int yStart = yCenter + range;
        ArrayList<CoordsWithBlock> blocks = new ArrayList<CoordsWithBlock>();
        for (int i = -range; i < range + 1; ++i) {
            for (int j = -range; j < range + 1; ++j) {
                int k = 0;
                while (yStart + k >= 0) {
                    int[] ids;
                    int x = xCenter + i;
                    int y = yStart + k;
                    int z = zCenter + j;
                    Block block = par2World.func_147439_a(x, y, z);
                    int meta = par2World.func_72805_g(x, y, z);
                    for (int id : ids = OreDictionary.getOreIDs((ItemStack)new ItemStack(block, 1, meta))) {
                        if (!validBlocks.contains(OreDictionary.getOreName((int)id))) continue;
                        boolean hasAir = false;
                        ArrayList<ChunkCoordinates> airBlocks = new ArrayList<ChunkCoordinates>();
                        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
                            int x_ = x + dir.offsetX;
                            int y_ = y + dir.offsetY;
                            int z_ = z + dir.offsetZ;
                            Block block_ = par2World.func_147439_a(x_, y_, z_);
                            if (!block_.isAir((IBlockAccess)par2World, x_, y_, z_) && !block_.isReplaceable((IBlockAccess)par2World, x_, y_, z_) && (!(block_ instanceof BlockFlower) || block_ instanceof ISpecialFlower) && block_ != Blocks.field_150398_cm) continue;
                            airBlocks.add(new ChunkCoordinates(x_, y_, z_));
                            hasAir = true;
                        }
                        if (!hasAir) break;
                        if (y > yCenter) {
                            blocks.add(new CoordsWithBlock(x, y, z, Blocks.field_150350_a));
                            break;
                        }
                        for (ChunkCoordinates coords : airBlocks) {
                            if (par2World.func_147439_a(coords.field_71574_a, coords.field_71572_b - 1, coords.field_71573_c) == Blocks.field_150350_a) continue;
                            blocks.add(new CoordsWithBlock(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Blocks.field_150346_d));
                        }
                        break;
                    }
                    --k;
                }
            }
        }
        int cost = 55 * blocks.size();
        if (par2World.field_72995_K || ManaItemHandler.requestManaExactForTool(par1ItemStack, par3EntityPlayer, cost, true)) {
            if (!par2World.field_72995_K) {
                for (CoordsWithBlock block : blocks) {
                    par2World.func_147449_b(block.field_71574_a, block.field_71572_b, block.field_71573_c, block.block);
                }
            }
            if (!blocks.isEmpty()) {
                int i;
                for (i = 0; i < 10; ++i) {
                    par2World.func_72956_a((Entity)par3EntityPlayer, "step.sand", 1.0f, 0.4f);
                }
                for (i = 0; i < 120; ++i) {
                    Botania.proxy.sparkleFX(par2World, (double)(xCenter - range) + (double)(range * 2) * Math.random(), (double)(yCenter + 2) + (Math.random() - 0.5) * 2.0, (double)(zCenter - range) + (double)(range * 2) * Math.random(), 0.35f, 0.2f, 0.05f, 2.0f, 5);
                }
            }
        }
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public boolean provideBlock(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta, boolean doit) {
        if (block == Blocks.field_150346_d && meta == 0) {
            return !doit || ManaItemHandler.requestManaExactForTool(requestor, player, 75, true);
        }
        return false;
    }

    @Override
    public int getBlockCount(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta) {
        if (block == Blocks.field_150346_d && meta == 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.terraformRodCraft;
    }

    class CoordsWithBlock
    extends ChunkCoordinates {
        final Block block;

        public CoordsWithBlock(int x, int y, int z, Block block) {
            super(x, y, z);
            this.block = block;
        }
    }
}

