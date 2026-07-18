/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.common.block.decor;

import java.util.HashMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface IFloatingFlower {
    public ItemStack getDisplayStack();

    public IslandType getIslandType();

    public void setIslandType(IslandType var1);

    public static class IslandType {
        private static HashMap<String, IslandType> registry = new HashMap();
        public static final IslandType GRASS = new IslandType("GRASS", "botania:textures/model/miniIsland.png");
        public static final IslandType PODZOL = new IslandType("PODZOL", "botania:textures/model/miniIslandPodzol.png");
        public static final IslandType MYCEL = new IslandType("MYCEL", "botania:textures/model/miniIslandMycelium.png");
        public static final IslandType SNOW = new IslandType("SNOW", "botania:textures/model/miniIslandSnow.png");
        public static final IslandType DRY = new IslandType("DRY", "botania:textures/model/miniIslandDry.png");
        public static final IslandType GOLDEN = new IslandType("GOLDEN", "botania:textures/model/miniIslandGolden.png");
        public static final IslandType VIVID = new IslandType("VIVID", "botania:textures/model/miniIslandVivid.png");
        public static final IslandType SCORCHED = new IslandType("SCORCHED", "botania:textures/model/miniIslandScorched.png");
        public static final IslandType INFUSED = new IslandType("INFUSED", "botania:textures/model/miniIslandInfused.png");
        public static final IslandType MUTATED = new IslandType("MUTATED", "botania:textures/model/miniIslandMutated.png");
        private final ResourceLocation res;
        public final String typeName;

        public IslandType(String name, String s) {
            this(name, new ResourceLocation(s));
        }

        public IslandType(String name, ResourceLocation s) {
            if (registry.containsKey(name)) {
                throw new IllegalArgumentException(name + " already registered!");
            }
            this.typeName = name;
            this.res = s;
            registry.put(name, this);
        }

        public static IslandType ofType(String typeStr) {
            IslandType type = registry.get(typeStr);
            return type == null ? GRASS : type;
        }

        public ResourceLocation getResource() {
            return this.res;
        }

        public String toString() {
            return this.typeName;
        }
    }
}

