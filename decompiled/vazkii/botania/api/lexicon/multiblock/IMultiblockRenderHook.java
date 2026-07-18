/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 */
package vazkii.botania.api.lexicon.multiblock;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public interface IMultiblockRenderHook {
    public static final Map<Block, IMultiblockRenderHook> renderHooks = new HashMap<Block, IMultiblockRenderHook>();

    public void renderBlockForMultiblock(IBlockAccess var1, Multiblock var2, Block var3, int var4, RenderBlocks var5, MultiblockComponent var6, float var7);

    public boolean needsTranslate(Block var1);
}

