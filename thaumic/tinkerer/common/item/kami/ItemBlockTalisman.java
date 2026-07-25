/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami;

import baubles.api.BaubleType;
import baubles.api.IBauble;
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
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorInterface;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ItemKamiBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemBlockTalisman
extends ItemKamiBase
implements IBauble {
    @Deprecated
    private static final String TAG_BLOCK_ID = "blockID";
    private static final String TAG_BLOCK_NAME = "blockName";
    private static final String TAG_BLOCK_META = "blockMeta";
    private static final String TAG_BLOCK_COUNT = "blockCount";
    IIcon enabledIcon;

    public ItemBlockTalisman() {
        this.func_77625_d(1);
        this.func_77627_a(true);
    }

    private static void setCount(ItemStack stack, int count) {
        ItemNBTHelper.setInt(stack, TAG_BLOCK_COUNT, count);
    }

    public static int remove(ItemStack stack, int count) {
        int current = ItemBlockTalisman.getBlockCount(stack);
        ItemBlockTalisman.setCount(stack, Math.max(current - count, 0));
        return Math.min(current, count);
    }

    @Deprecated
    public static int getBlockID(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_ID, 0);
    }

    public static String getBlockName(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_BLOCK_NAME, "");
    }

    public static Block getBlock(ItemStack stack) {
        Block block = Block.func_149684_b((String)ItemBlockTalisman.getBlockName(stack));
        if (block == Blocks.field_150350_a) {
            block = Block.func_149729_e((int)ItemBlockTalisman.getBlockID(stack));
        }
        return block;
    }

    public static int getBlockMeta(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_META, 0);
    }

    public static int getBlockCount(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_COUNT, 0);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if ((ItemBlockTalisman.getBlock(par1ItemStack) != Blocks.field_150350_a || ItemBlockTalisman.getBlockID(par1ItemStack) != 0) && par3EntityPlayer.func_70093_af()) {
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
            Block bBlock = ItemBlockTalisman.getBlock(par1ItemStack);
            int bmeta = ItemBlockTalisman.getBlockMeta(par1ItemStack);
            TileEntity tile = par3World.func_147438_o(par4, par5, par6);
            if (tile != null && tile instanceof IInventory) {
                int[] slots;
                IInventory inv = (IInventory)tile;
                for (int slot : slots = inv instanceof ISidedInventory ? ((ISidedInventory)inv).func_94128_d(par7) : TileTransvectorInterface.buildSlotsForLinearInventory(inv)) {
                    ItemStack stackInSlot = inv.func_70301_a(slot);
                    if (stackInSlot == null) {
                        ItemStack stack = new ItemStack(bBlock, 1, bmeta);
                        int maxSize = stack.func_77976_d();
                        stack.field_77994_a = ItemBlockTalisman.remove(par1ItemStack, maxSize);
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
                    stackInSlot.field_77994_a += ItemBlockTalisman.remove(par1ItemStack, missing);
                    inv.func_70296_d();
                    set = true;
                }
            } else {
                int remove = ItemBlockTalisman.remove(par1ItemStack, 1);
                if (remove > 0) {
                    Item.func_150898_a((Block)bBlock).func_77648_a(new ItemStack(bBlock, 1, bmeta), par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
                    set = true;
                }
            }
        }
        par2EntityPlayer.func_70062_b(0, par1ItemStack);
        return set;
    }

    private boolean setBlock(ItemStack stack, Block block, int meta) {
        if (ItemBlockTalisman.getBlock(stack) == Blocks.field_150350_a || ItemBlockTalisman.getBlockCount(stack) == 0) {
            ItemNBTHelper.setString(stack, TAG_BLOCK_NAME, Block.field_149771_c.func_148750_c((Object)block));
            ItemNBTHelper.setInt(stack, TAG_BLOCK_META, meta);
            return true;
        }
        return false;
    }

    private void add(ItemStack stack, int count) {
        int current = ItemBlockTalisman.getBlockCount(stack);
        ItemBlockTalisman.setCount(stack, current + count);
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
        Block block = ItemBlockTalisman.getBlock(par1ItemStack);
        if (block != null && block != Blocks.field_150350_a) {
            int count = ItemBlockTalisman.getBlockCount(par1ItemStack);
            par3List.add(StatCollector.func_74838_a((String)(new ItemStack(block, 1, ItemBlockTalisman.getBlockMeta(par1ItemStack)).func_77977_a() + ".name")) + " (x" + count + ")");
        }
        if (par1ItemStack.func_77960_j() == 1) {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.active"));
        } else {
            par3List.add(StatCollector.func_74838_a((String)"ttmisc.inactive"));
        }
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    public void onWornTick(ItemStack itemstack, EntityLivingBase entity) {
        Block block = ItemBlockTalisman.getBlock(itemstack);
        if (!entity.field_70170_p.field_72995_K && itemstack.func_77960_j() == 1 && block != Blocks.field_150350_a && entity instanceof EntityPlayer) {
            ItemStack stack;
            int i;
            EntityPlayer player = (EntityPlayer)entity;
            int meta = ItemBlockTalisman.getBlockMeta(itemstack);
            int highest = -1;
            boolean hasFreeSlot = false;
            int[] counts = new int[player.field_71071_by.func_70302_i_() - player.field_71071_by.field_70460_b.length];
            Arrays.fill(counts, 0);
            for (i = 0; i < counts.length; ++i) {
                stack = player.field_71071_by.func_70301_a(i);
                if (stack == null) {
                    hasFreeSlot = true;
                    continue;
                }
                if (Item.func_150898_a((Block)block) != stack.func_77973_b() || stack.func_77960_j() != meta) continue;
                counts[i] = stack.field_77994_a;
                highest = highest == -1 ? i : (counts[i] > counts[highest] && highest > 8 ? i : highest);
            }
            if (highest == -1) {
                ItemStack heldItem = player.field_71071_by.func_70445_o();
                if (hasFreeSlot && (heldItem == null || Item.func_150898_a((Block)block) == heldItem.func_77973_b() || heldItem.func_77960_j() != meta)) {
                    stack = new ItemStack(block, ItemBlockTalisman.remove(itemstack, 64), meta);
                    if (stack.field_77994_a != 0) {
                        player.field_71071_by.func_70441_a(stack);
                    }
                }
            } else {
                for (i = 0; i < counts.length; ++i) {
                    int count = counts[i];
                    if (i == highest || count == 0) continue;
                    this.add(itemstack, count);
                    player.field_71071_by.func_70299_a(i, null);
                }
                int countInHighest = counts[highest];
                int maxSize = new ItemStack(block, 1, meta).func_77976_d();
                if (countInHighest < maxSize) {
                    int missing = maxSize - countInHighest;
                    ItemStack stackInHighest = player.field_71071_by.func_70301_a(highest);
                    stackInHighest.field_77994_a += ItemBlockTalisman.remove(itemstack, missing);
                }
            }
        }
    }

    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
    }

    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public String getItemName() {
        return "blockTalisman";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("BLOCK_TALISMAN", new AspectList().add(Aspect.VOID, 2).add(Aspect.DARKNESS, 1).add(Aspect.ELDRITCH, 1).add(Aspect.MAGIC, 1), 14, 17, 5, new ItemStack((Item)this)).setParents(new String[]{"ICHOR_PICK_GEM", "ICHOR_SHOVEL_GEM"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("BLOCK_TALISMAN")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("BLOCK_TALISMAN", new ItemStack((Item)this), 9, new AspectList().add(Aspect.VOID, 65).add(Aspect.DARKNESS, 32).add(Aspect.MAGIC, 50).add(Aspect.ELDRITCH, 32), new ItemStack(ConfigItems.itemFocusPortableHole), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(Blocks.field_150477_bB), new ItemStack(Items.field_151045_i), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ConfigItems.itemResource, 1, 11), new ItemStack(ConfigBlocks.blockJar, 1, 3));
    }
}

