/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IFuelHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBlaze
extends BlockMod
implements ILexiconable,
IFuelHandler {
    public BlockBlaze() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149777_j);
        this.func_149715_a(1.0f);
        this.func_149663_c("blazeBlock");
        GameRegistry.registerFuelHandler((IFuelHandler)this);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.blazeBlock;
    }

    public int getBurnTime(ItemStack fuel) {
        return fuel.func_77973_b() == Item.func_150898_a((Block)this) ? TileEntityFurnace.func_145952_a((ItemStack)new ItemStack(Items.field_151072_bj)) * (Botania.gardenOfGlassLoaded ? 5 : 10) : 0;
    }
}

