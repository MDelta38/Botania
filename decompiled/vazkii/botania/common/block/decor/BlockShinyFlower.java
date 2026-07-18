/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.crafting.IInfusionStabiliser
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.Optional;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import vazkii.botania.api.item.IHornHarvestable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.lexicon.LexiconData;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.crafting.IInfusionStabiliser", striprefs=true)
public class BlockShinyFlower
extends BlockModFlower
implements IInfusionStabiliser,
IHornHarvestable {
    private static IIcon[] icons;
    private static IIcon[] iconsAlt;

    public BlockShinyFlower() {
        super("shinyFlower");
        this.func_149715_a(1.0f);
    }

    @Optional.Method(modid="easycoloredlights")
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return ColoredLightHelper.getPackedColor(world.func_72805_g(x, y, z), this.originalLight);
    }

    @Override
    public void func_149651_a(IIconRegister register) {
        icons = new IIcon[16];
        iconsAlt = new IIcon[16];
        for (int i = 0; i < 16; ++i) {
            BlockShinyFlower.icons[i] = IconHelper.forName(register, "flowerGlimmering" + i);
            BlockShinyFlower.iconsAlt[i] = IconHelper.forName(register, "flowerGlimmering" + i, "alt");
        }
    }

    @Override
    public IIcon func_149691_a(int par1, int par2) {
        return (ConfigHandler.altFlowerTextures ? iconsAlt : icons)[Math.min(icons.length - 1, par2)];
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.shinyFlowers;
    }

    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean fuckifiknow) {
        return false;
    }

    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return ConfigHandler.enableThaumcraftStablizers;
    }

    @Override
    public boolean canHornHarvest(World world, int x, int y, int z, ItemStack stack, IHornHarvestable.EnumHornType hornType) {
        return false;
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, int x, int y, int z, ItemStack stack, IHornHarvestable.EnumHornType hornType) {
        return false;
    }

    @Override
    public void harvestByHorn(World world, int x, int y, int z, ItemStack stack, IHornHarvestable.EnumHornType hornType) {
    }
}

