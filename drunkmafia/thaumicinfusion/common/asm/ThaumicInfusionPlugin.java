/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.CoreModManager
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$Name
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$TransformerExclusions
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package drunkmafia.thaumicinfusion.common.asm;

import cpw.mods.fml.relauncher.CoreModManager;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import drunkmafia.thaumicinfusion.common.asm.BlockTransformer;
import drunkmafia.thaumicinfusion.common.asm.WorldTransformer;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@IFMLLoadingPlugin.Name(value="thaumicinfusion")
@IFMLLoadingPlugin.TransformerExclusions(value={"drunkmafia.thaumicinfusion.common.asm.", "drunkmafia.thaumicinfusion.common.aspect"})
@IFMLLoadingPlugin.MCVersion(value="1.7.10")
public class ThaumicInfusionPlugin
implements IFMLLoadingPlugin {
    public static Logger log = LogManager.getLogger((String)"TI Transformer");
    public static PrintWriter logger;
    public static boolean isObf;
    public static String block;
    public static String world;
    public static String iBlockAccess;

    public ThaumicInfusionPlugin() {
        try {
            Field deobfuscatedEnvironment = CoreModManager.class.getDeclaredField("deobfuscatedEnvironment");
            deobfuscatedEnvironment.setAccessible(true);
            isObf = !deobfuscatedEnvironment.getBoolean(null);
            logger = new PrintWriter("TI_Transformer.log", "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Thaumic Infusion has detected an " + (isObf ? "Obfuscated" : "Deobfuscated") + " environment!");
        block = isObf ? "aji" : "net/minecraft/block/Block";
        world = isObf ? "ahb" : "net/minecraft/world/World";
        iBlockAccess = isObf ? "ahl" : "net/minecraft/world/IBlockAccess";
    }

    public String[] getASMTransformerClass() {
        return new String[]{BlockTransformer.class.getName(), WorldTransformer.class.getName()};
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

