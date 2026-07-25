/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusDislocation
extends ItemModFocus {
    private static final String TAG_AVAILABLE = "available";
    private static final String TAG_TILE_CMP = "tileCmp";
    @Deprecated
    private static final String TAG_BLOCK_ID = "blockID";
    private static final String TAG_BLOCK_NAME = "blockName";
    private static final String TAG_BLOCK_META = "blockMeta";
    private static final AspectList visUsage = new AspectList().add(Aspect.ENTROPY, 500).add(Aspect.ORDER, 500).add(Aspect.EARTH, 100);
    private static final AspectList visUsageTile = new AspectList().add(Aspect.ENTROPY, 2500).add(Aspect.ORDER, 2500).add(Aspect.EARTH, 500);
    private static final AspectList visUsageSpawner = new AspectList().add(Aspect.ENTROPY, 10000).add(Aspect.ORDER, 10000).add(Aspect.EARTH, 5000);
    private static ArrayList<Block> blacklist = new ArrayList();
    private IIcon ornament;

    private static AspectList getCost(TileEntity tile) {
        return tile == null ? visUsage : (tile instanceof TileEntityMobSpawner ? visUsageSpawner : visUsageTile);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.ornament = IconHelper.forItem(par1IconRegister, (Item)this, "Orn");
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        if (mop == null) {
            return itemstack;
        }
        Block block = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
        int meta = world.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
        TileEntity tile = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (player.func_82247_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, itemstack)) {
            ItemStack stack = this.getPickedBlock(itemstack);
            if (stack != null) {
                if (mop.field_72310_e == 0) {
                    --mop.field_72312_c;
                }
                if (mop.field_72310_e == 1) {
                    ++mop.field_72312_c;
                }
                if (mop.field_72310_e == 2) {
                    --mop.field_72309_d;
                }
                if (mop.field_72310_e == 3) {
                    ++mop.field_72309_d;
                }
                if (mop.field_72310_e == 4) {
                    --mop.field_72311_b;
                }
                if (mop.field_72310_e == 5) {
                    ++mop.field_72311_b;
                }
                if (block.func_149707_d(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e)) {
                    if (!world.field_72995_K) {
                        world.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ((ItemBlock)stack.func_77973_b()).field_150939_a, stack.func_77960_j(), 3);
                        ((ItemBlock)stack.func_77973_b()).field_150939_a.func_149689_a(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, (EntityLivingBase)player, itemstack);
                        NBTTagCompound tileCmp = this.getStackTileEntity(itemstack);
                        if (tileCmp != null && !tileCmp.func_82582_d()) {
                            TileEntity tile1 = TileEntity.func_145827_c((NBTTagCompound)tileCmp);
                            tile1.field_145851_c = mop.field_72311_b;
                            tile1.field_145848_d = mop.field_72312_c;
                            tile1.field_145849_e = mop.field_72309_d;
                            world.func_147455_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, tile1);
                        }
                    } else {
                        player.func_71038_i();
                    }
                    this.clearPickedBlock(itemstack);
                    for (int i = 0; i < 8; ++i) {
                        float x = (float)((double)mop.field_72311_b + Math.random());
                        float y = (float)((double)mop.field_72312_c + Math.random()) + 0.65f;
                        float z = (float)((double)mop.field_72309_d + Math.random());
                        ThaumicTinkerer.tcProxy.burst(world, (double)x, (double)y, (double)z, 0.2f);
                    }
                    world.func_72956_a((Entity)player, "thaumcraft:wand", 0.5f, 1.0f);
                }
            } else if (!blacklist.contains(block) && !ThaumcraftApi.portableHoleBlackList.contains(block) && block != null && block.func_149712_f(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) != -1.0f && wand.consumeAllVis(itemstack, player, ItemFocusDislocation.getCost(tile), true, false)) {
                if (!world.field_72995_K) {
                    world.func_147475_p(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                    world.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150350_a, 0, 3);
                    this.storePickedBlock(itemstack, block, (short)meta, tile);
                }
                for (int i = 0; i < 8; ++i) {
                    float x = (float)((double)mop.field_72311_b + Math.random());
                    float y = (float)((double)mop.field_72312_c + Math.random());
                    float z = (float)((double)mop.field_72309_d + Math.random());
                    ThaumicTinkerer.tcProxy.burst(world, (double)x, (double)y, (double)z, 0.2f);
                }
                world.func_72956_a((Entity)player, block.field_149762_H.func_150495_a(), 1.0f, 1.0f);
                world.func_72956_a((Entity)player, "thaumcraft:wand", 0.5f, 1.0f);
                if (world.field_72995_K) {
                    player.func_71038_i();
                }
            }
        }
        return itemstack;
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "DISLOCATION" + this.getUniqueKey(itemstack);
    }

    public String getUniqueKey(ItemStack itemstack) {
        ItemStack stack = this.getPickedBlock(itemstack);
        if (stack == null) {
            return "";
        }
        String name = stack.func_77977_a();
        int datahash = 0;
        if (stack.func_77978_p() != null) {
            datahash = stack.func_77978_p().hashCode();
        }
        return String.format("%s-%d", name, datahash);
    }

    public ItemStack getPickedBlock(ItemStack stack) {
        ItemStack focus;
        if (stack.func_77973_b() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
            focus = wand.getFocusItem(stack);
        } else {
            focus = stack;
        }
        return ItemNBTHelper.getBoolean(focus, TAG_AVAILABLE, false) ? this.getPickedBlockStack(stack) : null;
    }

    public ItemStack getPickedBlockStack(ItemStack stack) {
        ItemStack focus;
        if (stack.func_77973_b() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
            focus = wand.getFocusItem(stack);
        } else {
            focus = stack;
        }
        String name = ItemNBTHelper.getString(focus, TAG_BLOCK_NAME, "");
        Block block = Block.func_149684_b((String)name);
        if (block == Blocks.field_150350_a) {
            int id = ItemNBTHelper.getInt(focus, TAG_BLOCK_ID, 0);
            block = Block.func_149729_e((int)id);
        }
        int meta = ItemNBTHelper.getInt(focus, TAG_BLOCK_META, 0);
        ItemStack stck = new ItemStack((Item)new ItemBlock(block), 1, meta);
        return stck;
    }

    public NBTTagCompound getStackTileEntity(ItemStack stack) {
        ItemStack focus;
        if (stack.func_77973_b() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
            focus = wand.getFocusItem(stack);
        } else {
            focus = stack;
        }
        return ItemNBTHelper.getCompound(focus, TAG_TILE_CMP, true);
    }

    private void storePickedBlock(ItemStack stack, Block block, short meta, TileEntity tile) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        ItemStack focus = wand.getFocusItem(stack);
        String blockName = Block.field_149771_c.func_148750_c((Object)block);
        ItemNBTHelper.setString(focus, TAG_BLOCK_NAME, blockName);
        ItemNBTHelper.setInt(focus, TAG_BLOCK_META, meta);
        NBTTagCompound cmp = new NBTTagCompound();
        if (tile != null) {
            tile.func_145841_b(cmp);
        }
        ItemNBTHelper.setCompound(focus, TAG_TILE_CMP, cmp);
        ItemNBTHelper.setBoolean(focus, TAG_AVAILABLE, true);
        wand.setFocus(stack, focus);
    }

    private void clearPickedBlock(ItemStack stack) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        ItemStack focus = wand.getFocusItem(stack);
        ItemNBTHelper.setBoolean(focus, TAG_AVAILABLE, false);
        wand.setFocus(stack, focus);
    }

    public int getFocusColor(ItemStack stack) {
        return 16757248;
    }

    @Override
    public IIcon getOrnament(ItemStack stack) {
        return this.ornament;
    }

    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public String getItemName() {
        return "focusDislocation";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FOCUS_DISLOCATION", new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 1).add(Aspect.EXCHANGE, 1), -5, -5, 2, new ItemStack((Item)this), new ResearchPage[0]).setSecondary().setParents(new String[]{"FOCUS_FLIGHT"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), new ResearchPage("1"), ResearchHelper.infusionPage("FOCUS_DISLOCATION")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_DISLOCATION", new ItemStack((Item)this), 8, new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.DARKNESS, 10).add(Aspect.VOID, 25).add(Aspect.MAGIC, 20).add(Aspect.TAINT, 5), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(Items.field_151045_i));
    }

    static {
        blacklist.add((Block)Blocks.field_150326_M);
        blacklist.add((Block)Blocks.field_150332_K);
    }
}

