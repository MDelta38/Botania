/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.settings.GameSettings$Options
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.FMLLog;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.subtile.signature.SubTileSignature;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.version.VersionChecker;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.item.material.ItemManaResource;

public final class ContributorFancinessHandler {
    public static volatile Map<String, IIcon> flowerMap = null;
    private static volatile boolean startedLoading = false;
    private static boolean phi = true;

    public static void render(RenderPlayerEvent.Specials event) {
        String name = event.entityPlayer.getDisplayName();
        if (name.equals("Vazkii") || name.equals("_phi")) {
            if (phi) {
                ContributorFancinessHandler.renderPhiFlower((RenderPlayerEvent)event);
            } else {
                ContributorFancinessHandler.renderTwintails((RenderPlayerEvent)event);
            }
        } else if (name.equals("haighyorkie")) {
            ContributorFancinessHandler.renderGoldfish((RenderPlayerEvent)event);
        }
        ContributorFancinessHandler.firstStart();
        name = name.toLowerCase();
        if (Minecraft.func_71410_x().field_71474_y.func_74308_b(GameSettings.Options.SHOW_CAPE) && flowerMap != null && flowerMap.containsKey(name)) {
            ContributorFancinessHandler.renderFlower((RenderPlayerEvent)event, flowerMap.get(name));
        }
    }

    public static void firstStart() {
        if (!startedLoading) {
            new ThreadContributorListLoader();
            startedLoading = true;
        }
    }

    public static void load(Properties props) {
        flowerMap = new HashMap<String, IIcon>();
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            try {
                int i = Integer.parseInt(value);
                if (i < 0 || i >= 16) {
                    throw new NumberFormatException();
                }
                flowerMap.put(key, ModBlocks.flower.func_149735_b(0, i));
            }
            catch (NumberFormatException e) {
                SubTileSignature sig = BotaniaAPI.getSignatureForName(value);
                if (sig == null) continue;
                flowerMap.put(key, ItemBlockSpecialFlower.ofType(value).func_77954_c());
            }
        }
    }

    private static void renderTwintails(RenderPlayerEvent event) {
        GL11.glPushMatrix();
        IIcon icon = ((ItemManaResource)ModItems.manaResource).tailIcon;
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        float t = 0.13f;
        GL11.glTranslatef((float)t, (float)-0.5f, (float)-0.1f);
        if (event.entityPlayer.field_70181_x < 0.0) {
            GL11.glRotatef((float)((float)event.entityPlayer.field_70181_x * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        float r = -18.0f + (float)Math.sin(((float)ClientTickHandler.ticksInGame + event.partialRenderTick) * 0.05f) * 2.0f;
        GL11.glRotatef((float)r, (float)0.0f, (float)0.0f, (float)1.0f);
        float s = 0.9f;
        GL11.glScalef((float)s, (float)s, (float)s);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        GL11.glRotatef((float)(-r), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)(-t), (float)-0.0f, (float)0.0f);
        GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)t, (float)-0.0f, (float)0.0f);
        GL11.glRotatef((float)r, (float)0.0f, (float)0.0f, (float)1.0f);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        GL11.glPopMatrix();
    }

    private static void renderPhiFlower(RenderPlayerEvent event) {
        GL11.glPushMatrix();
        IIcon icon = ((ItemManaResource)ModItems.manaResource).phiFlowerIcon;
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)-0.25f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        GL11.glTranslatef((float)-1.2f, (float)0.2f, (float)0.125f);
        GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        GL11.glPopMatrix();
    }

    private static void renderGoldfish(RenderPlayerEvent event) {
        GL11.glPushMatrix();
        IIcon icon = ((ItemManaResource)ModItems.manaResource).goldfishIcon;
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.75f, (float)0.5f, (float)0.0f);
        GL11.glScalef((float)0.4f, (float)0.4f, (float)0.4f);
        GL11.glTranslatef((float)1.2f, (float)0.5f, (float)0.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        GL11.glPopMatrix();
    }

    private static void renderFlower(RenderPlayerEvent event, IIcon icon) {
        GL11.glPushMatrix();
        IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glTranslatef((float)-0.5f, (float)0.7f, (float)0.0f);
        ShaderHelper.useShader(ShaderHelper.gold);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        ShaderHelper.releaseShader();
        GL11.glPopMatrix();
    }

    public static class ThreadContributorListLoader
    extends Thread {
        public ThreadContributorListLoader() {
            this.setName("Botania Contributor Fanciness Thread");
            this.setDaemon(true);
            this.start();
        }

        @Override
        public void run() {
            try {
                URL url = new URL("https://raw.githubusercontent.com/Vazkii/Botania/master/contributors.properties");
                Properties props = new Properties();
                props.load(new InputStreamReader(url.openStream()));
                ContributorFancinessHandler.load(props);
            }
            catch (Exception e) {
                FMLLog.info((String)"[Botania] Could not load contributors list. Either you're offline or github is down. Nothing to worry about, carry on~", (Object[])new Object[0]);
                e.printStackTrace();
            }
            VersionChecker.doneChecking = true;
        }
    }
}

