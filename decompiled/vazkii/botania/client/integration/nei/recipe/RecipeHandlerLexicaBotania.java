/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.integration.nei.recipe;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.page.PageText;

public class RecipeHandlerLexicaBotania
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.lexica");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(50, 4, 18, 18), "botania.lexica", new Object[0]));
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        CachedLexicaBotaniaRecipe recipeObj = (CachedLexicaBotaniaRecipe)((Object)this.arecipes.get(recipe));
        String s = EnumChatFormatting.UNDERLINE + StatCollector.func_74838_a((String)recipeObj.entry.getUnlocalizedName());
        font.func_78276_b(s, 82 - font.func_78256_a(s) / 2, 30, 0x404040);
        KnowledgeType type = recipeObj.entry.getKnowledgeType();
        s = type.color + StatCollector.func_74838_a((String)type.getUnlocalizedName()).replaceAll("\\&.", "");
        font.func_78276_b(s, 82 - font.func_78256_a(s) / 2, 42, 0x404040);
        s = "\"" + StatCollector.func_74838_a((String)recipeObj.entry.getTagline()) + "\"";
        PageText.renderText(5, 42, 160, 200, s);
        String key = LexiconRecipeMappings.stackToString(recipeObj.item.item);
        String quickInfo = "botania.nei.quickInfo:" + key;
        String quickInfoLocal = StatCollector.func_74838_a((String)quickInfo);
        if (GuiScreen.func_146272_n() && GuiScreen.func_146271_m() && Minecraft.func_71410_x().field_71474_y.field_82882_x) {
            s = "name: " + key;
        } else if (quickInfo.equals(quickInfoLocal)) {
            s = StatCollector.func_74838_a((String)"botania.nei.lexicaNoInfo");
        } else {
            s = StatCollector.func_74838_a((String)"botania.nei.lexicaSeparator");
            font.func_78276_b(s, 82 - font.func_78256_a(s) / 2, 80, 0x404040);
            s = quickInfoLocal;
        }
        PageText.renderText(5, 80, 160, 200, s);
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.lexica")) {
            for (LexiconEntry entry : BotaniaAPI.getAllEntries()) {
                List<ItemStack> stacks = entry.getDisplayedRecipes();
                for (ItemStack stack : stacks) {
                    this.arecipes.add(new CachedLexicaBotaniaRecipe(stack, entry));
                }
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (LexiconEntry entry : BotaniaAPI.getAllEntries()) {
            List<ItemStack> stacks = entry.getDisplayedRecipes();
            for (ItemStack stack : stacks) {
                String key2;
                String key1 = LexiconRecipeMappings.stackToString(stack);
                if (!key1.equals(key2 = LexiconRecipeMappings.stackToString(result))) continue;
                this.arecipes.add(new CachedLexicaBotaniaRecipe(stack, entry));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
    }

    public class CachedLexicaBotaniaRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public LexiconEntry entry;
        public PositionedStack item;
        public List<PositionedStack> otherStacks;

        public CachedLexicaBotaniaRecipe(ItemStack stack, LexiconEntry entry) {
            super((TemplateRecipeHandler)RecipeHandlerLexicaBotania.this);
            this.otherStacks = new ArrayList<PositionedStack>();
            this.otherStacks.add(new PositionedStack((Object)new ItemStack(ModItems.lexicon), 51, 5));
            this.item = new PositionedStack((Object)stack, 91, 5);
            this.entry = entry;
        }

        public List<PositionedStack> getIngredients() {
            return this.otherStacks;
        }

        public PositionedStack getResult() {
            return this.item;
        }

        public List<PositionedStack> getOtherStacks() {
            return this.otherStacks;
        }

        public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) {
            return ingredient.func_77973_b() == ModItems.lexicon;
        }
    }
}

