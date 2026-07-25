/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Post
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.client.gui.GuiResearchBrowser
 *  thaumcraft.client.gui.GuiResearchRecipe
 *  thaumcraft.common.config.ConfigItems
 */
package witchinggadgets.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.gui.GuiResearchRecipe;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGConfig;

public class ThaumonomiconIndexSearcher {
    static final int mouseBufferIdent = 17;
    static ByteBuffer mouseBuffer;
    static final int selectedCategoryIdent = 21;
    static Field f_selectedCategory;
    public static ThaumonomiconIndexSearcher instance;
    public static GuiTextField thaumSearchField;
    static int listDisplayOffset;
    static String searchCategory;
    static String lastKeyword;
    static List<SearchQuery> searchResults;

    public static void init() {
        instance = new ThaumonomiconIndexSearcher();
        MinecraftForge.EVENT_BUS.register((Object)instance);
        FMLCommonHandler.instance().bus().register((Object)instance);
        if (mouseBuffer == null) {
            try {
                Field f_buf = Mouse.class.getDeclaredFields()[17];
                if (!f_buf.getName().equalsIgnoreCase("readBuffer")) {
                    f_buf = Mouse.class.getDeclaredField("readBuffer");
                }
                f_buf.setAccessible(true);
                mouseBuffer = (ByteBuffer)f_buf.get(null);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            f_selectedCategory = GuiResearchBrowser.class.getDeclaredFields()[21];
            if (!f_selectedCategory.getName().equalsIgnoreCase("selectedCategory")) {
                f_selectedCategory = GuiResearchBrowser.class.getDeclaredField("selectedCategory");
            }
            f_selectedCategory.setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui.getClass().getName().endsWith("GuiResearchBrowser")) {
            int guiLeft = event.gui.field_146294_l / 2;
            int guiTop = event.gui.field_146295_m / 2 - 110;
            thaumSearchField = new GuiTextField(event.gui.field_146297_k.field_71466_p, guiLeft, guiTop, 103, 13);
            thaumSearchField.func_146193_g(-1);
            thaumSearchField.func_146204_h(-1);
            thaumSearchField.func_146185_a(false);
            thaumSearchField.func_146203_f(40);
            Keyboard.enableRepeatEvents((boolean)true);
        }
    }

    @SubscribeEvent
    public void onGuiPreDraw(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (thaumSearchField != null) {
            boolean cont = mouseBuffer.hasRemaining();
            int to = 0;
            if (Mouse.isCreated() && cont && to < 40) {
                ++to;
                int mx = Mouse.getEventX() * event.gui.field_146294_l / event.gui.field_146297_k.field_71443_c;
                int my = event.gui.field_146295_m - Mouse.getEventY() * event.gui.field_146295_m / event.gui.field_146297_k.field_71440_d - 1;
                int button = Mouse.getEventButton();
                int wheel = Mouse.getEventDWheel();
                if (Mouse.getEventButtonState()) {
                    thaumSearchField.func_146192_a(mx, my, button);
                    if (thaumSearchField.func_146206_l() && button == 1) {
                        thaumSearchField.func_146180_a("");
                        searchResults.clear();
                    } else if (mx > event.gui.field_146294_l / 2 + 128 + (ResearchCategories.researchCategories.size() > 9 ? 24 : 2) && my > event.gui.field_146295_m / 2 - 115 && my < event.gui.field_146295_m / 2 + 115) {
                        int clicked = my - (event.gui.field_146295_m / 2 - 109);
                        int selected = (clicked /= 11) + listDisplayOffset;
                        if (selected < searchResults.size()) {
                            ResearchItem item = ResearchCategories.getResearch((String)ThaumonomiconIndexSearcher.searchResults.get((int)selected).research);
                            event.gui.field_146297_k.func_147108_a((GuiScreen)new GuiResearchRecipe(item, 0, (double)item.displayColumn, (double)item.displayRow));
                        }
                        return;
                    }
                    cont = mouseBuffer.hasRemaining();
                    return;
                }
                if (wheel != 0 && mx > event.gui.field_146294_l / 2 + 128 + (ResearchCategories.researchCategories.size() > 9 ? 24 : 2)) {
                    listDisplayOffset = wheel < 0 ? ++listDisplayOffset : --listDisplayOffset;
                    if (listDisplayOffset > searchResults.size() - 20) {
                        listDisplayOffset = searchResults.size() - 20;
                    }
                    if (listDisplayOffset < 0) {
                        listDisplayOffset = 0;
                    }
                    return;
                }
                return;
            }
        }
    }

    @SubscribeEvent
    public void onGuiPostDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (thaumSearchField != null) {
            int x = event.gui.field_146294_l / 2 + 128 + (ResearchCategories.researchCategories.size() > 9 ? 24 : 0);
            int y = event.gui.field_146295_m / 2 - 115;
            int maxWidth = Math.min(event.gui.field_146294_l - x, 224);
            if (!searchResults.isEmpty()) {
                ClientUtilities.bindTexture("thaumcraft:textures/misc/parchment3.png");
                GL11.glEnable((int)3042);
                Tessellator tes = Tessellator.field_78398_a;
                tes.func_78382_b();
                tes.func_78378_d(0xFFFFFF);
                tes.func_78374_a((double)x, (double)(y + 230), 0.0, 0.0, 0.5859375);
                tes.func_78374_a((double)(x + maxWidth), (double)(y + 230), 0.0, 0.5859375, 0.5859375);
                tes.func_78374_a((double)(x + maxWidth), (double)y, 0.0, 0.5859375, 0.0);
                tes.func_78374_a((double)x, (double)y, 0.0, 0.0, 0.0);
                tes.func_78381_a();
            }
            ClientUtilities.bindTexture("thaumcraft:textures/gui/guiresearchtable2.png");
            event.gui.func_73729_b(ThaumonomiconIndexSearcher.thaumSearchField.field_146209_f - 2, ThaumonomiconIndexSearcher.thaumSearchField.field_146210_g - 4, 94, 8, ThaumonomiconIndexSearcher.thaumSearchField.field_146218_h + 8, ThaumonomiconIndexSearcher.thaumSearchField.field_146219_i);
            event.gui.func_73729_b(ThaumonomiconIndexSearcher.thaumSearchField.field_146209_f - 2, ThaumonomiconIndexSearcher.thaumSearchField.field_146210_g + ThaumonomiconIndexSearcher.thaumSearchField.field_146219_i - 4, 138, 158, ThaumonomiconIndexSearcher.thaumSearchField.field_146218_h + 8, 2);
            event.gui.func_73729_b(ThaumonomiconIndexSearcher.thaumSearchField.field_146209_f + ThaumonomiconIndexSearcher.thaumSearchField.field_146218_h + 6, ThaumonomiconIndexSearcher.thaumSearchField.field_146210_g - 4, 244, 136, 2, ThaumonomiconIndexSearcher.thaumSearchField.field_146219_i + 2);
            if ((searchResults == null || searchResults.isEmpty()) && !thaumSearchField.func_146206_l()) {
                event.gui.func_73731_b(event.gui.field_146297_k.field_71466_p, StatCollector.func_74838_a((String)"wg.gui.search"), ThaumonomiconIndexSearcher.thaumSearchField.field_146209_f, ThaumonomiconIndexSearcher.thaumSearchField.field_146210_g, 0x777777);
            } else {
                for (int i = 0; i < 20; ++i) {
                    if (i + listDisplayOffset >= searchResults.size()) continue;
                    String name = ThaumonomiconIndexSearcher.searchResults.get((int)(ThaumonomiconIndexSearcher.listDisplayOffset + i)).display != null ? ThaumonomiconIndexSearcher.searchResults.get((int)(ThaumonomiconIndexSearcher.listDisplayOffset + i)).display : ResearchCategories.getResearch((String)ThaumonomiconIndexSearcher.searchResults.get((int)(ThaumonomiconIndexSearcher.listDisplayOffset + i)).research).getName();
                    name = ThaumonomiconIndexSearcher.searchResults.get((int)(ThaumonomiconIndexSearcher.listDisplayOffset + i)).modifier + event.gui.field_146297_k.field_71466_p.func_78269_a(name, maxWidth - 10);
                    event.gui.field_146297_k.field_71466_p.func_85187_a(name, x + 6, y + 6 + i * 11, 0xFFFFFF, false);
                }
            }
            thaumSearchField.func_146194_f();
        }
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (thaumSearchField != null) {
            thaumSearchField = null;
            Keyboard.enableRepeatEvents((boolean)false);
        }
    }

    @SubscribeEvent
    public void renderTick(TickEvent.ClientTickEvent event) {
        if (thaumSearchField != null && Keyboard.isCreated() && thaumSearchField.func_146206_l()) {
            while (Keyboard.next()) {
                if (!Keyboard.getEventKeyState()) continue;
                if (Keyboard.getEventKey() == 1) {
                    Minecraft.func_71410_x().func_147108_a(null);
                    continue;
                }
                thaumSearchField.func_146201_a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
                listDisplayOffset = 0;
                if (WGConfig.limitBookSearchToCategory) {
                    searchCategory = ThaumonomiconIndexSearcher.getActiveCategory();
                }
                ThaumonomiconIndexSearcher.buildEntryList(thaumSearchField.func_146179_b());
            }
        }
    }

    static void buildEntryList(String query) {
        Set<Object> keys;
        if (query == null || query.isEmpty()) {
            searchResults.clear();
            return;
        }
        query = query.toLowerCase();
        ArrayList<SearchQuery> valids = new ArrayList<SearchQuery>();
        if (searchCategory != null && !searchCategory.isEmpty()) {
            keys = ResearchCategories.getResearchList((String)ThaumonomiconIndexSearcher.searchCategory).research.keySet();
        } else {
            keys = new HashSet();
            for (ResearchCategoryList cat : ResearchCategories.researchCategories.values()) {
                keys.addAll(cat.research.keySet());
            }
        }
        HashSet<SearchQuery> recipeBased = new HashSet<SearchQuery>();
        HashSet<String> usedResearches = new HashSet<String>();
        for (String string : keys) {
            if (string == null || string.isEmpty() || ResearchCategories.getResearch((String)string) == null || !ThaumcraftApiHelper.isResearchComplete((String)Minecraft.func_71410_x().field_71439_g.func_70005_c_(), (String)string) || ResearchCategories.getResearch((String)string).getName().startsWith("tc.research_name")) continue;
            recipeBased.clear();
            ResearchPage[] pages = ResearchCategories.getResearch((String)string).getPages();
            int iPage = 0;
            if (pages != null) {
                for (ResearchPage page : pages) {
                    if (page.recipeOutput != null && page.recipeOutput.func_82833_r().toLowerCase().contains(query)) {
                        String dn = "";
                        if (page.recipeOutput.func_77973_b() == ConfigItems.itemGolemCore) {
                            for (String info : page.recipeOutput.func_82840_a((EntityPlayer)Minecraft.func_71410_x().field_71439_g, false)) {
                                dn = dn + info + " ";
                            }
                        } else {
                            dn = page.recipeOutput.func_82833_r();
                        }
                        if (!usedResearches.contains(dn)) {
                            recipeBased.add(new SearchQuery(string, "Item: " + dn, iPage));
                            usedResearches.add(dn);
                        }
                    }
                    ++iPage;
                }
            }
            boolean rAdded = false;
            if (recipeBased.size() <= 1 && !usedResearches.contains(ResearchCategories.getResearch((String)string).getName()) && (string.toLowerCase().contains(query) || ResearchCategories.getResearch((String)string).getName().toLowerCase().contains(query))) {
                valids.add(new SearchQuery(string, null, 0));
                usedResearches.add(ResearchCategories.getResearch((String)string).getName());
                rAdded = true;
            }
            if (rAdded) continue;
            valids.addAll(recipeBased);
        }
        Collections.sort(valids, ResearchSorter.instance);
        searchResults = valids;
    }

    static String getActiveCategory() {
        String s = null;
        try {
            s = (String)f_selectedCategory.get(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    static {
        f_selectedCategory = null;
        listDisplayOffset = 0;
        searchResults = new ArrayList<SearchQuery>();
    }

    static class SearchQuery {
        public final String research;
        public final String display;
        public final int page;
        String modifier;

        public SearchQuery(String research, String display, int page) {
            this.research = research;
            this.display = display;
            this.page = page;
            this.modifier = display != null ? EnumChatFormatting.DARK_GRAY.toString() : "";
        }
    }

    static class ResearchSorter
    implements Comparator<SearchQuery> {
        static ResearchSorter instance = new ResearchSorter();

        ResearchSorter() {
        }

        @Override
        public int compare(SearchQuery o1, SearchQuery o2) {
            String c1 = o1.display != null ? o1.display : ResearchCategories.getResearch((String)o1.research).getName();
            String c2 = o2.display != null ? o2.display : ResearchCategories.getResearch((String)o2.research).getName();
            return c1.compareToIgnoreCase(c2);
        }
    }
}

