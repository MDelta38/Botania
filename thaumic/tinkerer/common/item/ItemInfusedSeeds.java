/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockFarmland
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSeeds
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockFarmland;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockInfusedFarmland;
import thaumic.tinkerer.common.block.BlockInfusedGrain;
import thaumic.tinkerer.common.block.tile.TileInfusedGrain;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemInfusedSeeds
extends ItemSeeds
implements ITTinkererItem {
    private static final String NBT_MAIN_ASPECT = "mainAspect";
    private static final String NBT_ASPEPCT_TENDENCIES = "aspectTendencies";
    private IIcon[] icons;

    public ItemInfusedSeeds() {
        super(Blocks.field_150464_aj, Blocks.field_150458_ak);
    }

    public static Aspect getAspect(ItemStack stack) {
        AspectList aspectList = new AspectList();
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        aspectList.readFromNBT(stack.func_77978_p().func_74775_l(NBT_MAIN_ASPECT));
        return aspectList.size() == 0 ? null : aspectList.getAspects()[0];
    }

    public static void setAspect(ItemStack stack, Aspect aspect) {
        if (stack.field_77990_d == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        AspectList aspectList = new AspectList().add(aspect, 1);
        NBTTagCompound nbt = new NBTTagCompound();
        aspectList.writeToNBT(nbt);
        stack.field_77990_d.func_74782_a(NBT_MAIN_ASPECT, (NBTBase)nbt);
    }

    public static AspectList getAspectTendencies(ItemStack stack) {
        AspectList aspectList = new AspectList();
        if (stack.func_77978_p() == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        aspectList.readFromNBT(stack.func_77978_p().func_74775_l(NBT_ASPEPCT_TENDENCIES));
        return aspectList;
    }

    public static void setAspectTendencies(ItemStack stack, AspectList aspectList) {
        if (stack.field_77990_d == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbt = new NBTTagCompound();
        aspectList.writeToNBT(nbt);
        stack.field_77990_d.func_74782_a(NBT_ASPEPCT_TENDENCIES, (NBTBase)nbt);
    }

    public static ItemStack getStackFromAspect(Aspect a) {
        ItemStack stack = new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedSeeds.class));
        ItemInfusedSeeds.setAspect(stack, a);
        return stack;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.func_77624_a(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(ItemInfusedSeeds.getAspect(par1ItemStack).getName());
        AspectList aspectList = ItemInfusedSeeds.getAspectTendencies(par1ItemStack);
        if (aspectList != null && aspectList.getAspects()[0] != null) {
            for (Aspect a : aspectList.getAspects()) {
                par3List.add(a.getName() + ": " + aspectList.getAmount(a));
            }
        }
    }

    public boolean func_77614_k() {
        return true;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List l) {
        for (Aspect primal : Aspect.getPrimalAspects()) {
            ItemStack itemStack = new ItemStack(item, 1);
            ItemInfusedSeeds.setAspect(itemStack, primal);
            ItemInfusedSeeds.setAspectTendencies(itemStack, new AspectList());
            l.add(itemStack);
        }
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[7];
        this.icons[0] = IconHelper.forName(par1IconRegister, "seed_aer");
        this.icons[1] = IconHelper.forName(par1IconRegister, "seed_ignis");
        this.icons[2] = IconHelper.forName(par1IconRegister, "seed_aqua");
        this.icons[3] = IconHelper.forName(par1IconRegister, "seed_terra");
        this.icons[4] = IconHelper.forName(par1IconRegister, "seed_ordo");
        this.icons[5] = IconHelper.forName(par1IconRegister, "seed_perditio");
        this.icons[6] = IconHelper.forName(par1IconRegister, "seed_generic");
    }

    public IIcon func_77650_f(ItemStack stack) {
        return ItemInfusedSeeds.getAspect(stack) == null ? this.icons[0] : this.icons[BlockInfusedGrain.getNumberFromAspectForTexture(ItemInfusedSeeds.getAspect(stack))];
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "infusedSeeds";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererInfusionRecipe("INFUSED_POTIONS0", "INFUSED_POTIONS", ItemInfusedSeeds.getStackFromAspect(Aspect.AIR), 5, new AspectList().add(Aspect.CROP, 32).add(Aspect.HARVEST, 32), new ItemStack(Items.field_151014_N), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 0)), new ThaumicTinkererInfusionRecipe("INFUSED_POTIONS1", "INFUSED_POTIONS", ItemInfusedSeeds.getStackFromAspect(Aspect.FIRE), 5, new AspectList().add(Aspect.CROP, 32).add(Aspect.HARVEST, 32), new ItemStack(Items.field_151014_N), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 1)), new ThaumicTinkererInfusionRecipe("INFUSED_POTIONS2", "INFUSED_POTIONS", ItemInfusedSeeds.getStackFromAspect(Aspect.EARTH), 5, new AspectList().add(Aspect.CROP, 32).add(Aspect.HARVEST, 32), new ItemStack(Items.field_151014_N), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 3)), new ThaumicTinkererInfusionRecipe("INFUSED_POTIONS3", "INFUSED_POTIONS", ItemInfusedSeeds.getStackFromAspect(Aspect.WATER), 5, new AspectList().add(Aspect.CROP, 32).add(Aspect.HARVEST, 32), new ItemStack(Items.field_151014_N), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 2)), new ThaumicTinkererInfusionRecipe("INFUSED_POTIONS4", "INFUSED_POTIONS", ItemInfusedSeeds.getStackFromAspect(Aspect.ORDER), 5, new AspectList().add(Aspect.CROP, 32).add(Aspect.HARVEST, 32), new ItemStack(Items.field_151014_N), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 4)), new ThaumicTinkererInfusionRecipe("INFUSED_POTIONS5", "INFUSED_POTIONS", ItemInfusedSeeds.getStackFromAspect(Aspect.ENTROPY), 5, new AspectList().add(Aspect.CROP, 32).add(Aspect.HARVEST, 32), new ItemStack(Items.field_151014_N), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(ConfigItems.itemShard, 1, 5)));
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        }
        if (world.func_147439_a(x, y, z) instanceof BlockFarmland && par2EntityPlayer.func_82247_a(x, y, z, par7, par1ItemStack) && par2EntityPlayer.func_82247_a(x, y + 1, z, par7, par1ItemStack)) {
            world.func_147449_b(x, y, z, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockInfusedFarmland.class));
            world.func_147449_b(x, y + 1, z, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockInfusedGrain.class));
            BlockInfusedGrain.setAspect((IBlockAccess)world, x, y + 1, z, ItemInfusedSeeds.getAspect(par1ItemStack));
            ((TileInfusedGrain)world.func_147438_o((int)x, (int)(y + 1), (int)z)).primalTendencies = ItemInfusedSeeds.getAspectTendencies(par1ItemStack);
            --par1ItemStack.field_77994_a;
            return true;
        }
        return false;
    }
}

