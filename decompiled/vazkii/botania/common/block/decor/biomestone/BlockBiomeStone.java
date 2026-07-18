/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor.biomestone;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBiomeStone
extends BlockMod
implements ILexiconable {
    private static IIcon[] icons = new IIcon[32];
    int iconOffset;

    public BlockBiomeStone(int iconOffset, String name) {
        super(Material.field_151576_e);
        this.func_149711_c(1.5f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c(name);
        this.iconOffset = iconOffset;
    }

    @Override
    public void func_149651_a(IIconRegister register) {
        for (int i = 0; i < 16; ++i) {
            int index = i + this.iconOffset;
            BlockBiomeStone.icons[index] = IconHelper.forName(register, "biomeStone" + index);
        }
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 16; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        return icons[meta + this.iconOffset];
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        return new ItemStack((Block)this, 1, meta);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.marimorphosis;
    }
}

