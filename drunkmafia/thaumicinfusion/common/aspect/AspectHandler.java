/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.LoaderState
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.Logger
 *  thaumcraft.api.aspects.Aspect
 */
package drunkmafia.thaumicinfusion.common.aspect;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import drunkmafia.thaumicinfusion.client.gui.aspect.EffectGui;
import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import thaumcraft.api.aspects.Aspect;

public final class AspectHandler {
    private static final Map<Aspect, Class<? extends AspectEffect>> registeredEffects = new HashMap<Aspect, Class<? extends AspectEffect>>();
    private static final List<EffectBundle> guiEffects = new ArrayList<EffectBundle>();
    private static final Map<Aspect, Aspect[]> opposites = new HashMap<Aspect, Aspect[]>();
    private static ArrayList<Class<? extends AspectEffect>> effectsToRegister = new ArrayList();

    public static void registerEffect(Class<? extends AspectEffect> effect) {
        Logger logger = ThaumicInfusion.getLogger();
        if (AspectHandler.isInCorretState(LoaderState.PREINITIALIZATION)) {
            logger.warn("Aspect registering cannot be called outside the pre init event");
            return;
        }
        if (effect != null && effect.isAnnotationPresent(Effect.class)) {
            try {
                Effect annotation = effect.getAnnotation(Effect.class);
                AspectEffect effectInstace = effect.newInstance();
                if (effectsToRegister.contains(effect)) {
                    logger.error("Failed to register Effect: " + annotation.aspect());
                    return;
                }
                boolean isDef = annotation.aspect().equals("default");
                Configuration config = ThaumicInfusion.instance.config;
                config.load();
                effectInstace.readConfig(config);
                if (effectInstace.shouldRegister() && !isDef) {
                    effectsToRegister.add(effect);
                }
                config.save();
            }
            catch (Throwable e) {
                ThaumicInfusion.getLogger().error("Aspect: " + effect.getSimpleName() + " has caused an exception!", e);
            }
        }
    }

    public static void postInit() {
        Logger logger = ThaumicInfusion.getLogger();
        if (AspectHandler.isInCorretState(LoaderState.POSTINITIALIZATION)) {
            logger.warn("Post Init cannot be called outside it's state");
            return;
        }
        for (Class<? extends AspectEffect> effect : effectsToRegister) {
            Effect annotation = effect.getAnnotation(Effect.class);
            Aspect aspect = Aspect.getAspect((String)annotation.aspect().toLowerCase());
            if (aspect != null) {
                if (registeredEffects.containsKey(aspect)) continue;
                registeredEffects.put(aspect, effect);
                continue;
            }
            logger.log(Level.ERROR, "Aspect: " + annotation.aspect() + " does not exist in the instance");
        }
        for (Aspect aspect : AspectHandler.getRegisteredAspects()) {
            opposites.put(aspect, AspectHandler.calculateEffectOpposites(aspect));
        }
        logger.info(registeredEffects.size() + " effects have been binded to their aspect, Failed to find: " + (effectsToRegister.size() - registeredEffects.size()) + " effects aspects.");
        effectsToRegister = null;
    }

    private static Aspect[] calculateEffectOpposites(Aspect aspect) {
        try {
            ArrayList<Aspect> aspects = new ArrayList<Aspect>();
            AspectEffect effect = AspectHandler.getEffectFromAspect(aspect).newInstance();
            block2: for (Aspect checking : AspectHandler.getRegisteredAspects()) {
                for (Method method : AspectHandler.getEffectFromAspect(checking).getDeclaredMethods()) {
                    if (!effect.hasMethod(method.getName())) continue;
                    aspects.add(checking);
                    continue block2;
                }
            }
            return aspects.toArray(new Aspect[aspects.size()]);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Aspect[0];
        }
    }

    private static boolean isInCorretState(LoaderState state) {
        Loader loader = Loader.instance();
        return !loader.isInState(state) && loader.activeModContainer().getModId().matches("thaumicinfusion");
    }

    public static EffectBundle getGUI(int id) {
        for (EffectBundle bundle : guiEffects) {
            if (bundle.guiID != id) continue;
            return bundle;
        }
        return null;
    }

    public static EffectBundle getGUI(Class<? extends AspectEffect> effect) {
        for (EffectBundle bundle : guiEffects) {
            if (bundle.effect != effect) continue;
            return bundle;
        }
        return null;
    }

    public static Aspect[] getGUIAspects() {
        ArrayList aspects = new ArrayList();
        for (Aspect aspect : AspectHandler.getRegisteredAspects()) {
            Class<? extends AspectEffect> clazz = AspectHandler.getEffectFromAspect(aspect);
        }
        return aspects.toArray(new Aspect[aspects.size()]);
    }

    public static boolean canInfuse(Aspect[] aspects) {
        for (Aspect aspect : aspects) {
            Aspect[] opposite = opposites.get(aspect);
            if (opposite.length <= 0) continue;
            for (Aspect checking : aspects) {
                if (checking == aspect) continue;
                for (Aspect checkingOpposite : opposite) {
                    if (checkingOpposite != checking) continue;
                    return false;
                }
            }
        }
        return true;
    }

    public static int getCostOfEffect(Aspect aspect) {
        Class<? extends AspectEffect> c = AspectHandler.getEffectFromAspect(aspect);
        try {
            return c == null || c.getAnnotation(Effect.class) == null ? -1 : c.newInstance().getCost();
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Aspect[] getRegisteredAspects() {
        Aspect[] aspects = AspectHandler.getAllAspects();
        ArrayList<Aspect> registeredAspect = new ArrayList<Aspect>();
        for (Aspect aspect : aspects) {
            if (!registeredEffects.containsKey(aspect)) continue;
            registeredAspect.add(aspect);
        }
        return registeredAspect.toArray(new Aspect[registeredAspect.size()]);
    }

    public static Aspect[] getAllAspects() {
        return Aspect.aspects.values().toArray(new Aspect[1]);
    }

    public static Aspect getAspectsFromEffect(Class effect) {
        if (effect.isAnnotationPresent(Effect.class)) {
            Effect annotation = effect.getAnnotation(Effect.class);
            return Aspect.getAspect((String)annotation.aspect());
        }
        return null;
    }

    public static Class<? extends AspectEffect> getEffectFromAspect(Aspect aspects) {
        return registeredEffects.get(aspects);
    }

    public static class EffectBundle {
        public int guiID;
        public Class<? extends EffectGui> gui;
        public Class<? extends AspectEffect> effect;
    }
}

