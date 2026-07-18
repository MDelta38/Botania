/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockLivingwood;
import vazkii.botania.common.item.block.ItemBlockDreamwood;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockDreamwood
extends BlockLivingwood {
    public BlockDreamwood() {
        super("dreamwood");
    }

    @Override
    void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockDreamwood.class, (String)name);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.elvenResources;
    }
}

