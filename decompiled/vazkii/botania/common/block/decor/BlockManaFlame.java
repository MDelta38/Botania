/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Method
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.Optional;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileManaFlame;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.world.WorldTypeSkyblock;

public class BlockManaFlame
extends BlockModContainer
implements ILexiconable {
    public BlockManaFlame() {
        super(Material.field_151580_n);
        this.func_149663_c("manaFlame");
        float f = 0.25f;
        this.func_149672_a(field_149775_l);
        this.func_149676_a(f, f, f, 1.0f - f, 1.0f - f, 1.0f - f);
        this.func_149715_a(1.0f);
    }

    @Override
    public boolean registerInCreative() {
        return false;
    }

    @Optional.Method(modid="easycoloredlights")
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return ((TileManaFlame)world.func_147438_o(x, y, z)).getLightColor();
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return true;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        ItemStack stack;
        if (WorldTypeSkyblock.isWorldSkyblock(world) && (stack = player.func_71045_bC()) != null && stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150345_g) && !player.field_71071_by.func_146028_b(ModItems.lexicon)) {
            if (!world.field_72995_K) {
                --stack.field_77994_a;
            }
            if (!player.field_71071_by.func_70441_a(new ItemStack(ModItems.lexicon))) {
                player.func_71019_a(new ItemStack(ModItems.lexicon), false);
            }
            return true;
        }
        return false;
    }

    public IIcon func_149691_a(int side, int meta) {
        return Blocks.field_150480_ab.func_149691_a(side, meta);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>();
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileManaFlame();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.lenses;
    }
}

