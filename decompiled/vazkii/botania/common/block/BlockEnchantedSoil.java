/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.render.block.InterpolatedIcon;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockEnchantedSoil
extends BlockMod
implements ILexiconable {
    IIcon iconSide;

    public BlockEnchantedSoil() {
        super(Material.field_151577_b);
        this.func_149711_c(0.6f);
        this.func_149672_a(field_149779_h);
        this.func_149663_c("enchantedSoil");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @Override
    boolean registerInCreative() {
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void loadTextures(TextureStitchEvent.Pre event) {
        if (event.map.func_130086_a() == 0) {
            InterpolatedIcon icon = new InterpolatedIcon("botania:enchantedSoil0");
            if (event.map.setTextureEntry("botania:enchantedSoil0", (TextureAtlasSprite)icon)) {
                this.field_149761_L = icon;
            }
            if (event.map.setTextureEntry("botania:enchantedSoil1", (TextureAtlasSprite)(icon = new InterpolatedIcon("botania:enchantedSoil1")))) {
                this.iconSide = icon;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side == 0 ? Blocks.field_150346_d.func_149691_a(0, 0) : (side == 1 ? this.field_149761_L : this.iconSide);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Blocks.field_150346_d.func_149650_a(0, p_149650_2_, p_149650_3_);
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        return plantable.getPlantType(world, x, y - 1, z) == EnumPlantType.Plains;
    }

    protected boolean func_149700_E() {
        return false;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.overgrowthSeed;
    }
}

