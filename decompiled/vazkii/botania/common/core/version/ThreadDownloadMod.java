/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IChatComponent$Serializer
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.core.version;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.core.version.VersionChecker;

public class ThreadDownloadMod
extends Thread {
    String fileName;
    byte[] buffer = new byte[10240];
    int totalBytesDownloaded;
    int bytesJustDownloaded;
    InputStream webReader;

    public ThreadDownloadMod(String fileName) {
        this.setName("Botania Download File Thread");
        this.fileName = fileName;
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        try {
            IChatComponent component = IChatComponent.Serializer.func_150699_a((String)String.format(StatCollector.func_74838_a((String)"botania.versioning.startingDownload"), this.fileName));
            if (Minecraft.func_71410_x().field_71439_g != null) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a(component);
            }
            VersionChecker.startedDownload = true;
            String base = "http://botaniamod.net/";
            String file = this.fileName.replaceAll(" ", "%20");
            URL url = new URL(base + "dl.php?file=" + file);
            try {
                url.openStream().close();
            }
            catch (IOException iOException) {
                // empty catch block
            }
            url = new URL(base + "files/" + file);
            this.webReader = url.openStream();
            File dir = new File(".", "mods");
            File f = new File(dir, this.fileName + ".dl");
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f.getAbsolutePath());
            while ((this.bytesJustDownloaded = this.webReader.read(this.buffer)) > 0) {
                outputStream.write(this.buffer, 0, this.bytesJustDownloaded);
                this.buffer = new byte[10240];
                this.totalBytesDownloaded += this.bytesJustDownloaded;
            }
            outputStream.close();
            this.webReader.close();
            File f1 = new File(dir, this.fileName);
            if (!f1.exists()) {
                f.renameTo(f1);
            }
            if (Minecraft.func_71410_x().field_71439_g != null) {
                Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentTranslation("botania.versioning.doneDownloading", new Object[]{this.fileName}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GREEN)));
            }
            Desktop.getDesktop().open(dir);
            VersionChecker.downloadedFile = true;
            this.finalize();
        }
        catch (Throwable e) {
            e.printStackTrace();
            this.sendError();
            try {
                this.finalize();
            }
            catch (Throwable e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sendError() {
        if (Minecraft.func_71410_x().field_71439_g != null) {
            Minecraft.func_71410_x().field_71439_g.func_146105_b(new ChatComponentTranslation("botania.versioning.error", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
        }
    }
}

