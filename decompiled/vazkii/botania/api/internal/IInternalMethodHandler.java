/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.api.subtile.SubTileEntity;

public interface IInternalMethodHandler {
    public LexiconPage textPage(String var1);

    public LexiconPage elfPaperTextPage(String var1);

    public LexiconPage imagePage(String var1, String var2);

    public LexiconPage craftingRecipesPage(String var1, List<IRecipe> var2);

    public LexiconPage craftingRecipePage(String var1, IRecipe var2);

    public LexiconPage petalRecipesPage(String var1, List<RecipePetals> var2);

    public LexiconPage petalRecipePage(String var1, RecipePetals var2);

    public LexiconPage runeRecipesPage(String var1, List<RecipeRuneAltar> var2);

    public LexiconPage runeRecipePage(String var1, RecipeRuneAltar var2);

    public LexiconPage manaInfusionRecipesPage(String var1, List<RecipeManaInfusion> var2);

    public LexiconPage manaInfusionRecipePage(String var1, RecipeManaInfusion var2);

    public LexiconPage elvenTradePage(String var1, List<RecipeElvenTrade> var2);

    public LexiconPage elvenTradesPage(String var1, RecipeElvenTrade var2);

    public LexiconPage brewPage(String var1, String var2, RecipeBrew var3);

    public LexiconPage multiblockPage(String var1, MultiblockSet var2);

    public IManaNetwork getManaNetworkInstance();

    public ItemStack getSubTileAsStack(String var1);

    public ItemStack getSubTileAsFloatingFlowerStack(String var1);

    public String getStackSubTileKey(ItemStack var1);

    public IIcon getSubTileIconForName(String var1);

    public void registerBasicSignatureIcons(String var1, IIconRegister var2);

    public boolean shouldForceCheck();

    public int getPassiveFlowerDecay();

    public IInventory getBaublesInventory(EntityPlayer var1);

    public void breakOnAllCursors(EntityPlayer var1, Item var2, ItemStack var3, int var4, int var5, int var6, int var7);

    public boolean hasSolegnoliaAround(Entity var1);

    @SideOnly(value=Side.CLIENT)
    public void drawSimpleManaHUD(int var1, int var2, int var3, String var4, ScaledResolution var5);

    @SideOnly(value=Side.CLIENT)
    public void drawComplexManaHUD(int var1, int var2, int var3, String var4, ScaledResolution var5, ItemStack var6, boolean var7);

    @SideOnly(value=Side.CLIENT)
    public ItemStack getBindDisplayForFlowerType(SubTileEntity var1);

    @SideOnly(value=Side.CLIENT)
    public void renderLexiconText(int var1, int var2, int var3, int var4, String var5);

    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getDefaultBossBarTexture();

    @SideOnly(value=Side.CLIENT)
    public void setBossStatus(IBotaniaBoss var1);

    public boolean isBuildcraftPipe(TileEntity var1);

    public void sparkleFX(World var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11, int var12);

    public long getWorldElapsedTicks();

    public boolean isBotaniaFlower(World var1, int var2, int var3, int var4);

    public void sendBaubleUpdatePacket(EntityPlayer var1, int var2);

    public List<IWrappedInventory> wrapInventory(List<IInventory> var1);
}

