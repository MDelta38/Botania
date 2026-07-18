/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.api.lexicon.multiblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public class Multiblock {
    public List<MultiblockComponent> components = new ArrayList<MultiblockComponent>();
    public List<ItemStack> materials = new ArrayList<ItemStack>();
    public int minX;
    public int minY;
    public int minZ;
    public int maxX;
    public int maxY;
    public int maxZ;
    public int offX;
    public int offY;
    public int offZ;
    public HashMap<List<Integer>, MultiblockComponent> locationCache = new HashMap();

    public void addComponent(MultiblockComponent component) {
        if (this.getComponentForLocation(component.relPos.field_71574_a, component.relPos.field_71572_b, component.relPos.field_71573_c) != null) {
            throw new IllegalArgumentException("Location in multiblock already occupied");
        }
        this.components.add(component);
        this.changeAxisForNewComponent(component.relPos.field_71574_a, component.relPos.field_71572_b, component.relPos.field_71573_c);
        this.calculateCostForNewComponent(component);
        this.addComponentToLocationCache(component);
    }

    public void addComponent(int x, int y, int z, Block block, int meta) {
        this.addComponent(new MultiblockComponent(new ChunkCoordinates(x, y, z), block, meta));
    }

    private void changeAxisForNewComponent(int x, int y, int z) {
        if (x < this.minX) {
            this.minX = x;
        } else if (x > this.maxX) {
            this.maxX = x;
        }
        if (y < this.minY) {
            this.minY = y;
        } else if (y > this.maxY) {
            this.maxY = y;
        }
        if (z < this.minZ) {
            this.minZ = z;
        } else if (z > this.maxZ) {
            this.maxZ = z;
        }
    }

    private void calculateCostForNewComponent(MultiblockComponent comp) {
        ItemStack[] materials = comp.getMaterials();
        if (materials != null) {
            for (ItemStack stack : materials) {
                this.addStack(stack);
            }
        }
    }

    private void addStack(ItemStack stack) {
        if (stack == null) {
            return;
        }
        for (ItemStack oStack : this.materials) {
            if (!oStack.func_77969_a(stack) || !ItemStack.func_77970_a((ItemStack)oStack, (ItemStack)stack)) continue;
            oStack.field_77994_a += stack.field_77994_a;
            return;
        }
        this.materials.add(stack);
    }

    public void setRenderOffset(int x, int y, int z) {
        this.offX = x;
        this.offY = y;
        this.offZ = z;
    }

    public List<MultiblockComponent> getComponents() {
        return this.components;
    }

    public void rotate(double angle) {
        for (MultiblockComponent comp : this.getComponents()) {
            comp.rotate(angle);
        }
        this.updateLocationCache();
    }

    public Multiblock copy() {
        Multiblock mb = new Multiblock();
        for (MultiblockComponent comp : this.getComponents()) {
            mb.addComponent(comp.copy());
        }
        return mb;
    }

    public Multiblock[] createRotations() {
        Multiblock[] blocks = new Multiblock[4];
        blocks[0] = this;
        blocks[1] = blocks[0].copy();
        blocks[1].rotate(1.5707963267948966);
        blocks[2] = blocks[1].copy();
        blocks[2].rotate(1.5707963267948966);
        blocks[3] = blocks[2].copy();
        blocks[3].rotate(1.5707963267948966);
        return blocks;
    }

    public MultiblockSet makeSet() {
        return new MultiblockSet(this);
    }

    public int getXSize() {
        return Math.abs(this.minX) + Math.abs(this.maxX) + 1;
    }

    public int getYSize() {
        return Math.abs(this.minY) + Math.abs(this.maxY) + 1;
    }

    public int getZSize() {
        return Math.abs(this.minZ) + Math.abs(this.maxZ) + 1;
    }

    public void updateLocationCache() {
        this.locationCache.clear();
        for (MultiblockComponent comp : this.components) {
            this.addComponentToLocationCache(comp);
        }
    }

    private void addComponentToLocationCache(MultiblockComponent comp) {
        ChunkCoordinates pos = comp.getRelativePosition();
        this.locationCache.put(Arrays.asList(pos.field_71574_a, pos.field_71572_b, pos.field_71573_c), comp);
    }

    public MultiblockComponent getComponentForLocation(int x, int y, int z) {
        return this.locationCache.get(Arrays.asList(x, y, z));
    }
}

