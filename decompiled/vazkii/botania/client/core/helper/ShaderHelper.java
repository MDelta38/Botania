/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.client.renderer.OpenGlHelper
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.opengl.ARBShaderObjects
 */
package vazkii.botania.client.core.helper;

import cpw.mods.fml.common.FMLLog;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minecraft.client.renderer.OpenGlHelper;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.ARBShaderObjects;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.core.handler.ConfigHandler;

public final class ShaderHelper {
    private static final int VERT = 35633;
    private static final int FRAG = 35632;
    public static int pylonGlow = 0;
    public static int enchanterRune = 0;
    public static int manaPool = 0;
    public static int doppleganger = 0;
    public static int halo = 0;
    public static int dopplegangerBar = 0;
    public static int terraPlateRune = 0;
    public static int filmGrain = 0;
    public static int gold = 0;
    public static int categoryButton = 0;

    public static void initShaders() {
        if (!ShaderHelper.useShaders()) {
            return;
        }
        pylonGlow = ShaderHelper.createProgram(null, "/assets/botania/shader/pylon_glow.frag");
        enchanterRune = ShaderHelper.createProgram(null, "/assets/botania/shader/enchanter_rune.frag");
        manaPool = ShaderHelper.createProgram(null, "/assets/botania/shader/mana_pool.frag");
        doppleganger = ShaderHelper.createProgram("/assets/botania/shader/doppleganger.vert", "/assets/botania/shader/doppleganger.frag");
        halo = ShaderHelper.createProgram(null, "/assets/botania/shader/halo.frag");
        dopplegangerBar = ShaderHelper.createProgram(null, "/assets/botania/shader/doppleganger_bar.frag");
        terraPlateRune = ShaderHelper.createProgram(null, "/assets/botania/shader/terra_plate_rune.frag");
        filmGrain = ShaderHelper.createProgram(null, "/assets/botania/shader/film_grain.frag");
        gold = ShaderHelper.createProgram(null, "/assets/botania/shader/gold.frag");
        categoryButton = ShaderHelper.createProgram(null, "/assets/botania/shader/category_button.frag");
    }

    public static void useShader(int shader, ShaderCallback callback) {
        if (!ShaderHelper.useShaders()) {
            return;
        }
        ARBShaderObjects.glUseProgramObjectARB((int)shader);
        if (shader != 0) {
            int time = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"time");
            ARBShaderObjects.glUniform1iARB((int)time, (int)ClientTickHandler.ticksInGame);
            if (callback != null) {
                callback.call(shader);
            }
        }
    }

    public static void useShader(int shader) {
        ShaderHelper.useShader(shader, null);
    }

    public static void releaseShader() {
        ShaderHelper.useShader(0);
    }

    public static boolean useShaders() {
        return ConfigHandler.useShaders && OpenGlHelper.field_148824_g;
    }

    private static int createProgram(String vert, String frag) {
        int vertId = 0;
        int fragId = 0;
        int program = 0;
        if (vert != null) {
            vertId = ShaderHelper.createShader(vert, 35633);
        }
        if (frag != null) {
            fragId = ShaderHelper.createShader(frag, 35632);
        }
        if ((program = ARBShaderObjects.glCreateProgramObjectARB()) == 0) {
            return 0;
        }
        if (vert != null) {
            ARBShaderObjects.glAttachObjectARB((int)program, (int)vertId);
        }
        if (frag != null) {
            ARBShaderObjects.glAttachObjectARB((int)program, (int)fragId);
        }
        ARBShaderObjects.glLinkProgramARB((int)program);
        if (ARBShaderObjects.glGetObjectParameteriARB((int)program, (int)35714) == 0) {
            FMLLog.log((Level)Level.ERROR, (String)ShaderHelper.getLogInfo(program), (Object[])new Object[0]);
            return 0;
        }
        ARBShaderObjects.glValidateProgramARB((int)program);
        if (ARBShaderObjects.glGetObjectParameteriARB((int)program, (int)35715) == 0) {
            FMLLog.log((Level)Level.ERROR, (String)ShaderHelper.getLogInfo(program), (Object[])new Object[0]);
            return 0;
        }
        return program;
    }

    private static int createShader(String filename, int shaderType) {
        int shader = 0;
        try {
            shader = ARBShaderObjects.glCreateShaderObjectARB((int)shaderType);
            if (shader == 0) {
                return 0;
            }
            ARBShaderObjects.glShaderSourceARB((int)shader, (CharSequence)ShaderHelper.readFileAsString(filename));
            ARBShaderObjects.glCompileShaderARB((int)shader);
            if (ARBShaderObjects.glGetObjectParameteriARB((int)shader, (int)35713) == 0) {
                throw new RuntimeException("Error creating shader: " + ShaderHelper.getLogInfo(shader));
            }
            return shader;
        }
        catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB((int)shader);
            e.printStackTrace();
            return -1;
        }
    }

    private static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB((int)obj, (int)ARBShaderObjects.glGetObjectParameteriARB((int)obj, (int)35716));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String readFileAsString(String filename) throws Exception {
        StringBuilder source;
        block28: {
            source = new StringBuilder();
            InputStream in = ShaderHelper.class.getResourceAsStream(filename);
            Exception exception = null;
            if (in == null) {
                return "";
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                Exception innerExc = null;
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        source.append(line).append('\n');
                    }
                }
                catch (Exception exc) {
                    exception = exc;
                }
                finally {
                    try {
                        reader.close();
                    }
                    catch (Exception exc) {
                        if (innerExc == null) {
                            innerExc = exc;
                        }
                        exc.printStackTrace();
                    }
                }
                if (innerExc != null) {
                    throw innerExc;
                }
            }
            catch (Exception exc) {
                exception = exc;
                return exception;
            }
            finally {
                try {
                    in.close();
                }
                catch (Exception exc) {
                    if (exception == null) {
                        exception = exc;
                    }
                    exc.printStackTrace();
                }
                if (exception == null) break block28;
                throw exception;
            }
        }
        return source.toString();
    }
}

