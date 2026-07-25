/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  minetweaker.IUndoableAction
 *  minetweaker.MineTweakerAPI
 *  minetweaker.api.item.IIngredient
 *  net.minecraft.item.ItemStack
 *  stanhebben.zenscript.annotations.ZenClass
 *  stanhebben.zenscript.annotations.ZenMethod
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.common.minetweaker;

import java.util.ArrayList;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.common.minetweaker.WGMinetweaker;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.handler.InfusedGemHandler;

@ZenClass(value="mods.witchinggadgets.GemCutting")
public class GemCutting {
    @ZenMethod
    public static void addAffinity(IIngredient gem, String[] aspects) {
        Object oInput = WGMinetweaker.toObject(gem);
        if (oInput == null) {
            return;
        }
        ArrayList<Aspect> aspectList = new ArrayList<Aspect>();
        for (String s : aspects) {
            Aspect a = Aspect.getAspect((String)s);
            if (a == null) continue;
            aspectList.add(a);
        }
        MineTweakerAPI.apply((IUndoableAction)new Add(0, oInput, aspectList.toArray(new Aspect[aspectList.size()])));
    }

    @ZenMethod
    public static void removeAffinity(IIngredient gem) {
        MineTweakerAPI.apply((IUndoableAction)new Remove(0, WGMinetweaker.toObject(gem)));
    }

    @ZenMethod
    public static void addAversion(IIngredient gem, String[] aspects) {
        Object oInput = WGMinetweaker.toObject(gem);
        if (oInput == null) {
            return;
        }
        ArrayList<Aspect> aspectList = new ArrayList<Aspect>();
        for (String s : aspects) {
            Aspect a = Aspect.getAspect((String)s);
            if (a == null) continue;
            aspectList.add(a);
        }
        MineTweakerAPI.apply((IUndoableAction)new Add(1, oInput, aspectList.toArray(new Aspect[aspectList.size()])));
    }

    @ZenMethod
    public static void removeAversion(IIngredient gem) {
        MineTweakerAPI.apply((IUndoableAction)new Remove(1, WGMinetweaker.toObject(gem)));
    }

    private static class Remove
    implements IUndoableAction {
        byte type;
        Object key;
        Aspect[] removedAspects;

        public Remove(byte type, Object key) {
            this.type = type;
            this.key = key;
        }

        public void apply() {
            if (this.type == 0) {
                if (this.key instanceof String) {
                    this.removedAspects = InfusedGemHandler.naturalAffinities.get((String)this.key);
                } else if (this.key instanceof ItemStack) {
                    this.removedAspects = InfusedGemHandler.naturalAffinities.get(Utilities.getItemStackString((ItemStack)this.key));
                }
                InfusedGemHandler.removeAffinities(this.key);
            } else {
                if (this.key instanceof String) {
                    this.removedAspects = InfusedGemHandler.naturalAversions.get((String)this.key);
                } else if (this.key instanceof ItemStack) {
                    this.removedAspects = InfusedGemHandler.naturalAversions.get(Utilities.getItemStackString((ItemStack)this.key));
                }
                InfusedGemHandler.removeAversions(this.key);
            }
        }

        public void undo() {
            if (this.removedAspects != null) {
                if (this.type == 0) {
                    InfusedGemHandler.registerAffinities(this.key, this.removedAspects);
                } else {
                    InfusedGemHandler.registerAversions(this.key, this.removedAspects);
                }
            }
        }

        public String describe() {
            return "Removing Gem " + (this.type == 0 ? "Affinities" : "Aversions") + " for " + this.key;
        }

        public String describeUndo() {
            return "Re-Adding Gem " + (this.type == 0 ? "Affinities" : "Aversions") + " for " + this.key;
        }

        public Object getOverrideKey() {
            return null;
        }

        public boolean canUndo() {
            return true;
        }
    }

    private static class Add
    implements IUndoableAction {
        byte type;
        Object key;
        Aspect[] aspects;

        public Add(byte type, Object key, Aspect ... aspects) {
            this.type = type;
            this.key = key;
            this.aspects = aspects;
        }

        public void apply() {
            if (this.type == 0) {
                InfusedGemHandler.registerAffinities(this.key, this.aspects);
            } else {
                InfusedGemHandler.registerAversions(this.key, this.aspects);
            }
        }

        public boolean canUndo() {
            return true;
        }

        public void undo() {
            if (this.type == 0) {
                InfusedGemHandler.removeAffinities(this.key);
            } else {
                InfusedGemHandler.removeAversions(this.key);
            }
        }

        public String describe() {
            return "Adding Gem " + (this.type == 0 ? "Affinities" : "Aversions") + " for " + this.key;
        }

        public String describeUndo() {
            return "Removing Gem " + (this.type == 0 ? "Affinities" : "Aversions") + " for " + this.key;
        }

        public Object getOverrideKey() {
            return null;
        }
    }
}

