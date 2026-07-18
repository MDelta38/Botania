/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IChatComponent$Serializer
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.core.version;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.core.version.ThreadVersionChecker;

public final class VersionChecker {
    private static final int FLAVOUR_MESSAGES = 65;
    public static boolean doneChecking = false;
    public static String onlineVersion = "";
    public static boolean triedToWarnPlayer = false;
    public static boolean startedDownload = false;
    public static boolean downloadedFile = false;

    public void init() {
        new ThreadVersionChecker();
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (doneChecking && event.phase == TickEvent.Phase.END && Minecraft.func_71410_x().field_71439_g != null && !triedToWarnPlayer) {
            if (!onlineVersion.isEmpty()) {
                int clientBuild;
                EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
                int onlineBuild = Integer.parseInt(onlineVersion.split("-")[1]);
                int n = clientBuild = "249".contains("GRADLE") ? Integer.MAX_VALUE : Integer.parseInt("249");
                if (onlineBuild > clientBuild) {
                    player.func_146105_b(new ChatComponentTranslation("botania.versioning.flavour" + player.field_70170_p.field_73012_v.nextInt(65), new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.LIGHT_PURPLE)));
                    player.func_146105_b((IChatComponent)new ChatComponentTranslation("botania.versioning.outdated", new Object[]{clientBuild, onlineBuild}));
                    IChatComponent component = IChatComponent.Serializer.func_150699_a((String)StatCollector.func_74838_a((String)"botania.versioning.updateMessage").replaceAll("%version%", onlineVersion));
                    player.func_146105_b(component);
                }
            }
            triedToWarnPlayer = true;
        }
    }
}

