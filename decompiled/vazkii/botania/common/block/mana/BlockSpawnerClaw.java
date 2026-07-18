/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileSpawnerClaw;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSpawnerClaw
extends BlockModContainer
implements ILexiconable {
    public BlockSpawnerClaw() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149663_c("spawnerClaw");
        float f = 0.125f;
        float f1 = 0.0625f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, f1, 1.0f - f);
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item));
        list.add(new ItemStack(Blocks.field_150474_ac));
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idSpawnerClaw;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileSpawnerClaw();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.spawnerClaw;
    }
}

