/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.config.Configuration
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect;

import cpw.mods.fml.common.registry.EntityRegistry;
import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.asm.BlockTransformer;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Aer;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Alienis;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Bestia;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Cognitio;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Exanimis;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Fabrico;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Gelum;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Humanus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Ignis;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Infernus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Iter;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Lucrum;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Lux;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Machina;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Messis;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Mortuus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Ordo;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Pannus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Perditio;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Permutatio;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Potentia;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Praecantatio;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Sano;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Sensus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Spiritus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Tempestas;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Terra;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Venenum;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Victus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Vinculum;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Vitreus;
import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.Volatus;
import drunkmafia.thaumicinfusion.common.aspect.entity.InfusedBlockFalling;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.ISavable;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="default")
public abstract class AspectEffect
extends Block
implements ISavable {
    private static final HashMap<Class, ArrayList<MethodInfo>> phasedMethods = new HashMap();
    public BlockData data;
    protected WorldCoordinates pos;
    private boolean shouldRegister;

    public AspectEffect() {
        super(Material.field_151579_a);
    }

    public static void init() {
        AspectHandler.registerEffect(Aer.class);
        AspectHandler.registerEffect(Alienis.class);
        AspectHandler.registerEffect(Bestia.class);
        AspectHandler.registerEffect(Cognitio.class);
        AspectHandler.registerEffect(Exanimis.class);
        AspectHandler.registerEffect(Fabrico.class);
        AspectHandler.registerEffect(Gelum.class);
        AspectHandler.registerEffect(Humanus.class);
        AspectHandler.registerEffect(Ignis.class);
        AspectHandler.registerEffect(Infernus.class);
        AspectHandler.registerEffect(Iter.class);
        AspectHandler.registerEffect(Lucrum.class);
        AspectHandler.registerEffect(Lux.class);
        AspectHandler.registerEffect(Machina.class);
        AspectHandler.registerEffect(Messis.class);
        AspectHandler.registerEffect(Mortuus.class);
        AspectHandler.registerEffect(Pannus.class);
        AspectHandler.registerEffect(Perditio.class);
        AspectHandler.registerEffect(Potentia.class);
        AspectHandler.registerEffect(Permutatio.class);
        AspectHandler.registerEffect(Praecantatio.class);
        AspectHandler.registerEffect(Sano.class);
        AspectHandler.registerEffect(Sensus.class);
        AspectHandler.registerEffect(Spiritus.class);
        AspectHandler.registerEffect(Tempestas.class);
        AspectHandler.registerEffect(Terra.class);
        AspectHandler.registerEffect(Venenum.class);
        AspectHandler.registerEffect(Victus.class);
        AspectHandler.registerEffect(Vinculum.class);
        AspectHandler.registerEffect(Vitreus.class);
        AspectHandler.registerEffect(Volatus.class);
        AspectHandler.registerEffect(Ordo.class);
        EntityRegistry.registerModEntity(InfusedBlockFalling.class, (String)"InfusedBlockFalling", (int)0, (Object)ThaumicInfusion.instance, (int)80, (int)3, (boolean)true);
    }

    public static List<MethodInfo> getMethods(Class<? extends AspectEffect> clazz) {
        List<MethodInfo> methods = (List<MethodInfo>)phasedMethods.get(clazz);
        return methods != null ? methods : AspectEffect.parseForMethods(clazz);
    }

    private static List<MethodInfo> parseForMethods(Class<? extends AspectEffect> c) {
        Method[] effectMethods = c.getMethods();
        ArrayList<MethodInfo> meths = new ArrayList<MethodInfo>();
        for (Method meth : effectMethods) {
            OverrideBlock block;
            if (!BlockTransformer.blockMethods.contains(meth.getName()) || meth.getDeclaringClass() == Block.class || (block = meth.getAnnotation(OverrideBlock.class)) == null) continue;
            meths.add(new MethodInfo(meth.getName().hashCode(), block));
        }
        phasedMethods.put(c, meths);
        return meths;
    }

    public abstract int getCost();

    public boolean shouldDrain() {
        return true;
    }

    public void renderEffect(EntityPlayer player, float partialTicks) {
    }

    public void readConfig(Configuration config) {
        config.load();
        this.shouldRegister = config.getBoolean(this.getClass().getSimpleName(), "Effects", true, "");
        config.save();
    }

    public void onPlaceEffect(EntityPlayer player) {
    }

    public void aspectInit(World world, WorldCoordinates pos) {
        this.pos = pos;
    }

    public WorldCoordinates getPos() {
        return this.pos;
    }

    public void setCoords(WorldCoordinates newPos) {
        this.pos = newPos;
    }

    public boolean shouldRegister() {
        return this.shouldRegister;
    }

    public boolean hasMethod(String methName) {
        return AspectEffect.getMethods(this.getClass()).contains(methName);
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        if (this.pos != null) {
            this.pos.writeNBT(tagCompound);
        }
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        this.pos = new WorldCoordinates();
        this.pos.readNBT(tagCompound);
    }

    public static class MethodInfo {
        public int methodID;
        public OverrideBlock override;

        public MethodInfo(int methodID, OverrideBlock override) {
            this.methodID = methodID;
            this.override = override;
        }
    }
}

