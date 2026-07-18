/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.client.core.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.client.challenge.Challenge;
import vazkii.botania.client.challenge.ModChallenges;
import vazkii.botania.client.gui.lexicon.GuiLexicon;

public final class PersistentVariableHelper {
    private static final String TAG_FIRST_LOAD = "firstLoad";
    private static final String TAG_DOG = "dog";
    private static final String TAG_BOOKMARK_COUNT = "bookmarkCount";
    private static final String TAG_BOOKMARK_PREFIX = "bookmark";
    private static final String TAG_BOOKMARKS = "bookmarks";
    private static final String TAG_CHALLENGES = "challenges";
    private static final String TAG_LEXICON_NOTES = "lexiconNotes";
    private static final String TAG_LAST_BOTANIA_VERSION = "lastBotaniaVersion";
    private static File cacheFile;
    public static boolean firstLoad;
    public static boolean dog;
    public static String lastBotaniaVersion;

    public static void save() throws IOException {
        NBTTagCompound cmp = new NBTTagCompound();
        List<GuiLexicon> bookmarks = GuiLexicon.bookmarks;
        int count = bookmarks.size();
        cmp.func_74768_a(TAG_BOOKMARK_COUNT, count);
        NBTTagCompound bookmarksCmp = new NBTTagCompound();
        for (int i = 0; i < count; ++i) {
            GuiLexicon lex = bookmarks.get(i);
            NBTTagCompound bookmarkCmp = new NBTTagCompound();
            lex.serialize(bookmarkCmp);
            bookmarksCmp.func_74782_a(TAG_BOOKMARK_PREFIX + i, (NBTBase)bookmarkCmp);
        }
        cmp.func_74782_a(TAG_BOOKMARKS, (NBTBase)bookmarksCmp);
        NBTTagCompound challengesCmp = new NBTTagCompound();
        for (Challenge c : ModChallenges.challengeLookup.values()) {
            c.writeToNBT(challengesCmp);
        }
        cmp.func_74782_a(TAG_CHALLENGES, (NBTBase)challengesCmp);
        NBTTagCompound notesCmp = new NBTTagCompound();
        for (String s : GuiLexicon.notes.keySet()) {
            String note = GuiLexicon.notes.get(s);
            if (note == null || note.trim().isEmpty()) continue;
            notesCmp.func_74778_a(s, note);
        }
        cmp.func_74782_a(TAG_LEXICON_NOTES, (NBTBase)notesCmp);
        cmp.func_74757_a(TAG_FIRST_LOAD, firstLoad);
        cmp.func_74757_a(TAG_DOG, dog);
        cmp.func_74778_a(TAG_LAST_BOTANIA_VERSION, lastBotaniaVersion);
        PersistentVariableHelper.injectNBTToFile(cmp, PersistentVariableHelper.getCacheFile());
    }

    public static void load() throws IOException {
        NBTTagCompound cmp = PersistentVariableHelper.getCacheCompound();
        int count = cmp.func_74762_e(TAG_BOOKMARK_COUNT);
        GuiLexicon.bookmarks.clear();
        if (count > 0) {
            NBTTagCompound bookmarksCmp = cmp.func_74775_l(TAG_BOOKMARKS);
            for (int i = 0; i < count; ++i) {
                NBTTagCompound bookmarkCmp = bookmarksCmp.func_74775_l(TAG_BOOKMARK_PREFIX + i);
                GuiLexicon gui = GuiLexicon.create(bookmarkCmp);
                if (gui == null) continue;
                GuiLexicon.bookmarks.add(gui);
                GuiLexicon.bookmarkKeys.add(gui.getNotesKey());
            }
        }
        if (cmp.func_74764_b(TAG_CHALLENGES)) {
            NBTTagCompound challengesCmp = cmp.func_74775_l(TAG_CHALLENGES);
            for (Challenge c : ModChallenges.challengeLookup.values()) {
                c.readFromNBT(challengesCmp);
            }
        }
        if (cmp.func_74764_b(TAG_LEXICON_NOTES)) {
            NBTTagCompound notesCmp = cmp.func_74775_l(TAG_LEXICON_NOTES);
            Set keys = notesCmp.func_150296_c();
            GuiLexicon.notes.clear();
            for (String key : keys) {
                GuiLexicon.notes.put(key, notesCmp.func_74779_i(key));
            }
        }
        lastBotaniaVersion = cmp.func_74764_b(TAG_LAST_BOTANIA_VERSION) ? cmp.func_74779_i(TAG_LAST_BOTANIA_VERSION) : "(N/A)";
        boolean bl = firstLoad = cmp.func_74764_b(TAG_FIRST_LOAD) ? cmp.func_74767_n(TAG_FIRST_LOAD) : firstLoad;
        if (firstLoad) {
            lastBotaniaVersion = "r1.8-249";
        }
        dog = cmp.func_74767_n(TAG_DOG);
    }

    public static void saveSafe() {
        try {
            PersistentVariableHelper.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setCacheFile(File f) {
        cacheFile = f;
    }

    public static File getCacheFile() throws IOException {
        if (!cacheFile.exists()) {
            cacheFile.createNewFile();
        }
        return cacheFile;
    }

    public static NBTTagCompound getCacheCompound() throws IOException {
        return PersistentVariableHelper.getCacheCompound(PersistentVariableHelper.getCacheFile());
    }

    public static NBTTagCompound getCacheCompound(File cache) throws IOException {
        if (cache == null) {
            throw new RuntimeException("No cache file!");
        }
        try {
            NBTTagCompound cmp = CompressedStreamTools.func_74796_a((InputStream)new FileInputStream(cache));
            return cmp;
        }
        catch (IOException e) {
            NBTTagCompound cmp = new NBTTagCompound();
            CompressedStreamTools.func_74799_a((NBTTagCompound)cmp, (OutputStream)new FileOutputStream(cache));
            return PersistentVariableHelper.getCacheCompound(cache);
        }
    }

    public static void injectNBTToFile(NBTTagCompound cmp, File f) {
        try {
            CompressedStreamTools.func_74799_a((NBTTagCompound)cmp, (OutputStream)new FileOutputStream(f));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        firstLoad = true;
        dog = true;
        lastBotaniaVersion = "";
    }
}

