/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  org.apache.commons.lang3.text.WordUtils
 */
package vazkii.botania.api.wiki;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.apache.commons.lang3.text.WordUtils;
import vazkii.botania.api.wiki.IWikiProvider;

public class SimpleWikiProvider
implements IWikiProvider {
    final String name;
    final String urlBase;
    final String replacement;
    final boolean lowercase;

    public SimpleWikiProvider(String name, String urlBase) {
        this(name, urlBase, "%20");
    }

    public SimpleWikiProvider(String name, String urlBase, boolean lowercase) {
        this(name, urlBase, "%20", lowercase);
    }

    public SimpleWikiProvider(String name, String urlBase, String replacement) {
        this.name = name;
        this.urlBase = urlBase;
        this.replacement = replacement;
        this.lowercase = false;
    }

    public SimpleWikiProvider(String name, String urlBase, String replacement, boolean lowercase) {
        this.name = name;
        this.urlBase = urlBase;
        this.replacement = replacement;
        this.lowercase = lowercase;
    }

    @Override
    public String getBlockName(World world, MovingObjectPosition pos) {
        int x = pos.field_72311_b;
        int y = pos.field_72312_c;
        int z = pos.field_72309_d;
        Block block = world.func_147439_a(x, y, z);
        if (block == null) {
            return null;
        }
        ItemStack stack = block.getPickBlock(pos, world, x, y, z);
        if (stack == null || stack.func_77973_b() == null) {
            stack = new ItemStack(block, 1, world.func_72805_g(x, y, z));
        }
        if (stack.func_77973_b() == null) {
            return null;
        }
        String name = stack.func_82833_r();
        if (name == null || name.isEmpty()) {
            return null;
        }
        return name;
    }

    @Override
    public String getWikiURL(World world, MovingObjectPosition pos) {
        String name = this.getBlockName(world, pos);
        if (name == null) {
            return null;
        }
        if (this.lowercase) {
            return String.format(this.urlBase, name.toLowerCase().replaceAll(" ", this.replacement));
        }
        return String.format(this.urlBase, WordUtils.capitalizeFully((String)name).replaceAll(" ", this.replacement));
    }

    @Override
    public String getWikiName(World world, MovingObjectPosition pos) {
        return this.name;
    }
}

