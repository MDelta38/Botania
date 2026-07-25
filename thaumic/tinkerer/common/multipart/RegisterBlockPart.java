/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.vec.BlockCoord
 *  codechicken.multipart.MultiPartRegistry
 *  codechicken.multipart.MultiPartRegistry$IPartConverter
 *  codechicken.multipart.MultiPartRegistry$IPartFactory
 *  codechicken.multipart.TMultiPart
 *  com.google.common.collect.Lists
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class RegisterBlockPart
implements MultiPartRegistry.IPartFactory,
MultiPartRegistry.IPartConverter {
    Block block = null;
    Class<? extends TMultiPart> part = null;
    String name = "";

    public RegisterBlockPart(Block block, Class<? extends TMultiPart> part) {
        try {
            this.name = part.getConstructor(new Class[0]).newInstance(new Object[0]).getType();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegisterBlockPart(Block block, Class<? extends TMultiPart> part, String name) {
        this.block = block;
        this.part = part;
        this.name = name;
    }

    public TMultiPart createPart(String name, boolean client) {
        if (name.equals(name)) {
            try {
                return this.part.getConstructor(new Class[0]).newInstance(new Object[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void init() {
        if (this.name.isEmpty() || this.block == null || this.part == null) {
            return;
        }
        MultiPartRegistry.registerConverter((MultiPartRegistry.IPartConverter)this);
        MultiPartRegistry.registerParts((MultiPartRegistry.IPartFactory)this, (String[])new String[]{this.name});
    }

    public Iterable<Block> blockTypes() {
        return Lists.newArrayList((Object[])new Block[]{this.block});
    }

    public TMultiPart convert(World world, BlockCoord pos) {
        Block blockInQuestion = world.func_147439_a(pos.x, pos.y, pos.z);
        int meta = world.func_72805_g(pos.x, pos.y, pos.z);
        if (blockInQuestion == this.block) {
            try {
                if (this.part.getName().equals("vazkii.tinkerer.common.block.multipart.PartNitor") && meta != 1) {
                    return null;
                }
                if (this.part.getDeclaredConstructor(Integer.TYPE) != null) {
                    return this.part.getDeclaredConstructor(Integer.TYPE).newInstance(meta);
                }
                return this.part.getConstructor(new Class[0]).newInstance(new Object[0]);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}

