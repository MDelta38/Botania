/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.render.block.InterpolatedIcon;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileBifrost;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBifrost
extends BlockModContainer
implements ILexiconable {
    public BlockBifrost() {
        super(Material.field_151592_s);
        this.func_149663_c("bifrost");
        this.func_149713_g(0);
        this.func_149715_a(1.0f);
        this.func_149722_s();
        this.func_149672_a(field_149778_k);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @Override
    public boolean registerInCreative() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(ModItems.rainbowRod);
    }

    public boolean shouldSideBeRendered1(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
        Block block = p_149646_1_.func_147439_a(p_149646_2_, p_149646_3_, p_149646_4_);
        return block == this ? false : super.func_149646_a(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }

    public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
        return this.shouldSideBeRendered1(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, 1 - p_149646_5_);
    }

    public int func_149701_w() {
        return 1;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void loadTextures(TextureStitchEvent.Pre event) {
        InterpolatedIcon icon;
        if (event.map.func_130086_a() == 0 && event.map.setTextureEntry("botania:bifrost", (TextureAtlasSprite)(icon = new InterpolatedIcon("botania:bifrost")))) {
            this.field_149761_L = icon;
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileBifrost();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.rainbowRod;
    }
}

