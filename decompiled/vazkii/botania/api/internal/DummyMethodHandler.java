/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package vazkii.botania.api.internal;

import java.util.List;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.corporea.IWrappedInventory;
import vazkii.botania.api.internal.DummyManaNetwork;
import vazkii.botania.api.internal.DummyPage;
import vazkii.botania.api.internal.IInternalMethodHandler;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.api.subtile.SubTileEntity;

public class DummyMethodHandler
implements IInternalMethodHandler {
    @Override
    public LexiconPage textPage(String key) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage elfPaperTextPage(String key) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage imagePage(String key, String resource) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage craftingRecipesPage(String key, List<IRecipe> recipes) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage craftingRecipePage(String key, IRecipe recipe) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage petalRecipesPage(String key, List<RecipePetals> recipes) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage petalRecipePage(String key, RecipePetals recipe) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage runeRecipesPage(String key, List<RecipeRuneAltar> recipes) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage runeRecipePage(String key, RecipeRuneAltar recipe) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage manaInfusionRecipesPage(String key, List<RecipeManaInfusion> recipes) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage manaInfusionRecipePage(String key, RecipeManaInfusion recipe) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage elvenTradePage(String key, List<RecipeElvenTrade> recipes) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage elvenTradesPage(String key, RecipeElvenTrade recipe) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage brewPage(String key, String bottomText, RecipeBrew recipe) {
        return this.dummyPage(key);
    }

    @Override
    public LexiconPage multiblockPage(String key, MultiblockSet mb) {
        return this.dummyPage(key);
    }

    private LexiconPage dummyPage(String key) {
        return new DummyPage(key);
    }

    @Override
    public ItemStack getSubTileAsStack(String subTile) {
        return new ItemStack(Blocks.field_150348_b, 0, 0);
    }

    @Override
    public ItemStack getSubTileAsFloatingFlowerStack(String subTile) {
        return this.getSubTileAsStack(subTile);
    }

    @Override
    public String getStackSubTileKey(ItemStack stack) {
        return null;
    }

    @Override
    public IIcon getSubTileIconForName(String name) {
        return Blocks.field_150328_O.func_149691_a(0, 0);
    }

    @Override
    public void registerBasicSignatureIcons(String name, IIconRegister register) {
    }

    @Override
    public IManaNetwork getManaNetworkInstance() {
        return DummyManaNetwork.instance;
    }

    @Override
    public void drawSimpleManaHUD(int color, int mana, int maxMana, String name, ScaledResolution res) {
    }

    @Override
    public void drawComplexManaHUD(int color, int mana, int maxMana, String name, ScaledResolution res, ItemStack bindDisplay, boolean properlyBound) {
    }

    @Override
    public ItemStack getBindDisplayForFlowerType(SubTileEntity e) {
        return new ItemStack(Blocks.field_150348_b, 0, 0);
    }

    @Override
    public void renderLexiconText(int x, int y, int width, int height, String unlocalizedText) {
    }

    @Override
    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m) {
    }

    @Override
    public IInventory getBaublesInventory(EntityPlayer player) {
        return null;
    }

    @Override
    public boolean shouldForceCheck() {
        return true;
    }

    @Override
    public int getPassiveFlowerDecay() {
        return 0;
    }

    @Override
    public ResourceLocation getDefaultBossBarTexture() {
        return null;
    }

    @Override
    public void setBossStatus(IBotaniaBoss status) {
    }

    @Override
    public boolean isBuildcraftPipe(TileEntity tile) {
        return false;
    }

    @Override
    public void breakOnAllCursors(EntityPlayer player, Item item, ItemStack stack, int x, int y, int z, int side) {
    }

    @Override
    public boolean hasSolegnoliaAround(Entity e) {
        return false;
    }

    @Override
    public long getWorldElapsedTicks() {
        return 0L;
    }

    @Override
    public boolean isBotaniaFlower(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public void sendBaubleUpdatePacket(EntityPlayer player, int slot) {
    }

    @Override
    public List<IWrappedInventory> wrapInventory(List<IInventory> inventories) {
        return null;
    }
}

