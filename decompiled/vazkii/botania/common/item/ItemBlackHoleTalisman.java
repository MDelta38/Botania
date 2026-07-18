/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.item.IBlockProvider;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.InventoryHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.recipe.BlackHoleTalismanExtractRecipe;
import vazkii.botania.common.item.ItemMod;

public class ItemBlackHoleTalisman
extends ItemMod
implements IBlockProvider {
    private static final String TAG_BLOCK_NAME = "blockName";
    private static final String TAG_BLOCK_META = "blockMeta";
    private static final String TAG_BLOCK_COUNT = "blockCount";
    IIcon enabledIcon;

    public ItemBlackHoleTalisman() {
        this.func_77655_b("blackHoleTalisman");
        this.func_77625_d(1);
        this.func_77627_a(true);
        GameRegistry.addRecipe((IRecipe)new BlackHoleTalismanExtractRecipe());
        RecipeSorter.register((String)"botania:blackHoleTalismanExtract", BlackHoleTalismanExtractRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (ItemBlackHoleTalisman.getBlock(par1ItemStack) != Blocks.field_150350_a && par3EntityPlayer.func_70093_af()) {
            int dmg = par1ItemStack.func_77960_j();
            par1ItemStack.func_77964_b(~dmg & 1);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
        }
        return par1ItemStack;
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        int meta;
        Block block = par3World.func_147439_a(par4, par5, par6);
        boolean set = this.setBlock(par1ItemStack, block, meta = par3World.func_72805_g(par4, par5, par6));
        if (!set) {
            Block bBlock = ItemBlackHoleTalisman.getBlock(par1ItemStack);
            int bmeta = ItemBlackHoleTalisman.getBlockMeta(par1ItemStack);
            TileEntity tile = par3World.func_147438_o(par4, par5, par6);
            if (tile != null && tile instanceof IInventory) {
                int[] slots;
                IInventory inv = (IInventory)tile;
                for (int slot : slots = inv instanceof ISidedInventory ? ((ISidedInventory)inv).func_94128_d(par7) : InventoryHelper.buildSlotsForLinearInventory(inv)) {
                    ItemStack stackInSlot = inv.func_70301_a(slot);
                    if (stackInSlot == null) {
                        ItemStack stack = new ItemStack(bBlock, 1, bmeta);
                        int maxSize = stack.func_77976_d();
                        stack.field_77994_a = ItemBlackHoleTalisman.remove(par1ItemStack, maxSize);
                        if (stack.field_77994_a == 0 || !inv.func_94041_b(slot, stack) || inv instanceof ISidedInventory && !((ISidedInventory)inv).func_102007_a(slot, stack, par7)) continue;
                        inv.func_70299_a(slot, stack);
                        inv.func_70296_d();
                        set = true;
                        continue;
                    }
                    if (stackInSlot.func_77973_b() != Item.func_150898_a((Block)bBlock) || stackInSlot.func_77960_j() != bmeta) continue;
                    int maxSize = stackInSlot.func_77976_d();
                    int missing = maxSize - stackInSlot.field_77994_a;
                    if (!inv.func_94041_b(slot, stackInSlot) || inv instanceof ISidedInventory && !((ISidedInventory)inv).func_102007_a(slot, stackInSlot, par7)) continue;
                    stackInSlot.field_77994_a += ItemBlackHoleTalisman.remove(par1ItemStack, missing);
                    inv.func_70296_d();
                    set = true;
                }
            } else {
                ForgeDirection dir = ForgeDirection.getOrientation((int)par7);
                int entities = par3World.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(par4 + dir.offsetX), (double)(par5 + dir.offsetY), (double)(par6 + dir.offsetZ), (double)(par4 + dir.offsetX + 1), (double)(par5 + dir.offsetY + 1), (double)(par6 + dir.offsetZ + 1))).size();
                if (entities == 0) {
                    int remove;
                    int n = remove = par2EntityPlayer.field_71075_bZ.field_75098_d ? 1 : ItemBlackHoleTalisman.remove(par1ItemStack, 1);
                    if (remove > 0) {
                        ItemStack stack = new ItemStack(bBlock, 1, bmeta);
                        ItemsRemainingRenderHandler.set(stack, ItemBlackHoleTalisman.getBlockCount(par1ItemStack));
                        Item.func_150898_a((Block)bBlock).func_77648_a(stack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
                        set = true;
                    }
                }
            }
        }
        par2EntityPlayer.func_70062_b(0, par1ItemStack);
        return set;
    }

    public void func_77663_a(ItemStack itemstack, World p_77663_2_, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        Block block = ItemBlackHoleTalisman.getBlock(itemstack);
        if (!entity.field_70170_p.field_72995_K && itemstack.func_77960_j() == 1 && block != Blocks.field_150350_a && entity instanceof EntityPlayer) {
            int i;
            EntityPlayer player = (EntityPlayer)entity;
            int meta = ItemBlackHoleTalisman.getBlockMeta(itemstack);
            int highest = -1;
            int[] counts = new int[player.field_71071_by.func_70302_i_() - player.field_71071_by.field_70460_b.length];
            Arrays.fill(counts, 0);
            for (i = 0; i < counts.length; ++i) {
                ItemStack stack = player.field_71071_by.func_70301_a(i);
                if (stack == null || Item.func_150898_a((Block)block) != stack.func_77973_b() || stack.func_77960_j() != meta) continue;
                counts[i] = stack.field_77994_a;
                highest = highest == -1 ? i : (counts[i] > counts[highest] && highest > 8 ? i : highest);
            }
            if (highest != -1) {
                for (i = 0; i < counts.length; ++i) {
                    int count = counts[i];
                    if (count == 0) continue;
                    this.add(itemstack, count);
                    player.field_71071_by.func_70299_a(i, null);
                }
            }
        }
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        Block block = ItemBlackHoleTalisman.getBlock(par1ItemStack);
        int meta = ItemBlackHoleTalisman.getBlockMeta(par1ItemStack);
        ItemStack stack = new ItemStack(block, 1, meta);
        return super.func_77653_i(par1ItemStack) + (stack == null || stack.func_77973_b() == null ? "" : " (" + EnumChatFormatting.GREEN + stack.func_82833_r() + EnumChatFormatting.RESET + ")");
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        int count = ItemBlackHoleTalisman.getBlockCount(itemStack);
        if (count == 0) {
            return null;
        }
        int extract = Math.min(64, count);
        ItemStack copy = itemStack.func_77946_l();
        ItemBlackHoleTalisman.remove(copy, extract);
        int dmg = copy.func_77960_j();
        if (dmg == 1) {
            copy.func_77964_b(0);
        }
        return copy;
    }

    public boolean hasContainerItem(ItemStack stack) {
        return this.getContainerItem(stack) != null;
    }

    public boolean func_77630_h(ItemStack p_77630_1_) {
        return false;
    }

    private boolean setBlock(ItemStack stack, Block block, int meta) {
        if (ItemBlackHoleTalisman.getBlock(stack) == Blocks.field_150350_a || ItemBlackHoleTalisman.getBlockCount(stack) == 0) {
            ItemNBTHelper.setString(stack, TAG_BLOCK_NAME, Block.field_149771_c.func_148750_c((Object)block));
            ItemNBTHelper.setInt(stack, TAG_BLOCK_META, meta);
            return true;
        }
        return false;
    }

    private void add(ItemStack stack, int count) {
        int current = ItemBlackHoleTalisman.getBlockCount(stack);
        ItemBlackHoleTalisman.setCount(stack, current + count);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.enabledIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 == 1 ? this.enabledIcon : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        Block block = ItemBlackHoleTalisman.getBlock(par1ItemStack);
        if (block != null && block != Blocks.field_150350_a) {
            int count = ItemBlackHoleTalisman.getBlockCount(par1ItemStack);
            par3List.add(count + " " + StatCollector.func_74838_a((String)(new ItemStack(block, 1, ItemBlackHoleTalisman.getBlockMeta(par1ItemStack)).func_77977_a() + ".name")));
        }
        if (par1ItemStack.func_77960_j() == 1) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.active"), par3List);
        } else {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.inactive"), par3List);
        }
    }

    void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    private static void setCount(ItemStack stack, int count) {
        ItemNBTHelper.setInt(stack, TAG_BLOCK_COUNT, count);
    }

    public static int remove(ItemStack stack, int count) {
        int current = ItemBlackHoleTalisman.getBlockCount(stack);
        ItemBlackHoleTalisman.setCount(stack, Math.max(current - count, 0));
        return Math.min(current, count);
    }

    public static String getBlockName(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_BLOCK_NAME, "");
    }

    public static Block getBlock(ItemStack stack) {
        Block block = Block.func_149684_b((String)ItemBlackHoleTalisman.getBlockName(stack));
        return block;
    }

    public static int getBlockMeta(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_META, 0);
    }

    public static int getBlockCount(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_COUNT, 0);
    }

    @Override
    public boolean provideBlock(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta, boolean doit) {
        int count;
        Block stored = ItemBlackHoleTalisman.getBlock(stack);
        int storedMeta = ItemBlackHoleTalisman.getBlockMeta(stack);
        if (stored == block && storedMeta == meta && (count = ItemBlackHoleTalisman.getBlockCount(stack)) > 0) {
            if (doit) {
                ItemBlackHoleTalisman.setCount(stack, count - 1);
            }
            return true;
        }
        return false;
    }

    @Override
    public int getBlockCount(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta) {
        Block stored = ItemBlackHoleTalisman.getBlock(stack);
        int storedMeta = ItemBlackHoleTalisman.getBlockMeta(stack);
        if (stored == block && storedMeta == meta) {
            return ItemBlackHoleTalisman.getBlockCount(stack);
        }
        return 0;
    }
}

