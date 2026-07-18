/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.item.IGrassHornExcempt;
import vazkii.botania.api.item.IHornHarvestable;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemMod;

public class ItemGrassHorn
extends ItemMod {
    private static final int SUBTYPES = 3;
    IIcon[] icons;
    IIcon vuvuzelaIcon;

    public ItemGrassHorn() {
        this.func_77625_d(1);
        this.func_77655_b("grassHorn");
        this.func_77627_a(true);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 3; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[3];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
        this.vuvuzelaIcon = IconHelper.forName(par1IconRegister, "vuvuzela");
    }

    public IIcon func_77650_f(ItemStack par1ItemStack) {
        return par1ItemStack.func_82833_r().toLowerCase().contains("vuvuzela") ? this.vuvuzelaIcon : super.func_77650_f(par1ItemStack);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77650_f(stack);
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameLazy(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    String getUnlocalizedNameLazy(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int time) {
        if (time != this.func_77626_a(stack) && time % 5 == 0) {
            ItemGrassHorn.breakGrass(player.field_70170_p, stack, stack.func_77960_j(), (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        }
        if (!player.field_70170_p.field_72995_K) {
            player.field_70170_p.func_72956_a((Entity)player, "note.bassattack", 1.0f, 0.001f);
        }
    }

    public static void breakGrass(World world, ItemStack stack, int stackDmg, int srcx, int srcy, int srcz) {
        IHornHarvestable.EnumHornType type = IHornHarvestable.EnumHornType.getTypeForMeta(stackDmg);
        Random rand = new Random(srcx ^ srcy ^ srcz);
        int range = 12 - stackDmg * 3;
        int rangeY = 3 + stackDmg * 4;
        ArrayList<ChunkCoordinates> coords = new ArrayList<ChunkCoordinates>();
        for (int i = -range; i < range + 1; ++i) {
            for (int j = -range; j < range + 1; ++j) {
                for (int k = -rangeY; k < rangeY + 1; ++k) {
                    int x = srcx + i;
                    int y = srcy + k;
                    int z = srcz + j;
                    Block block = world.func_147439_a(x, y, z);
                    if (!(block instanceof IHornHarvestable ? ((IHornHarvestable)block).canHornHarvest(world, x, y, z, stack, type) : stackDmg == 0 && block instanceof BlockBush && !(block instanceof ISpecialFlower) && (!(block instanceof IGrassHornExcempt) || ((IGrassHornExcempt)block).canUproot(world, x, y, z)) || stackDmg == 1 && block.isLeaves((IBlockAccess)world, x, y, z) || stackDmg == 2 && block == Blocks.field_150431_aC)) continue;
                    coords.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
        Collections.shuffle(coords, rand);
        int count = Math.min(coords.size(), 32 + stackDmg * 16);
        for (int i = 0; i < count; ++i) {
            ChunkCoordinates currCoords = (ChunkCoordinates)coords.get(i);
            ArrayList items = new ArrayList();
            Block block = world.func_147439_a(currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c);
            int meta = world.func_72805_g(currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c);
            items.addAll(block.getDrops(world, currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c, meta, 0));
            if (block instanceof IHornHarvestable && ((IHornHarvestable)block).hasSpecialHornHarvest(world, currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c, stack, type)) {
                ((IHornHarvestable)block).harvestByHorn(world, currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c, stack, type);
                continue;
            }
            if (world.field_72995_K) continue;
            world.func_147468_f(currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c);
            if (ConfigHandler.blockBreakParticles) {
                world.func_72926_e(2001, currCoords.field_71574_a, currCoords.field_71572_b, currCoords.field_71573_c, Block.func_149682_b((Block)block) + (meta << 12));
            }
            for (ItemStack stack_ : items) {
                world.func_72838_d((Entity)new EntityItem(world, (double)currCoords.field_71574_a + 0.5, (double)currCoords.field_71572_b + 0.5, (double)currCoords.field_71573_c + 0.5, stack_));
            }
        }
    }

    public boolean func_77662_d() {
        return true;
    }
}

