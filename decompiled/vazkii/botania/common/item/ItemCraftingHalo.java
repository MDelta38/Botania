/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerWorkbench
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.gui.crafting.InventoryCraftingHalo;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.InventoryHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemMod;

public class ItemCraftingHalo
extends ItemMod
implements ICraftAchievement {
    private static final ResourceLocation glowTexture = new ResourceLocation("botania:textures/misc/glow0.png");
    private static final ItemStack craftingTable = new ItemStack(Blocks.field_150462_ai);
    public static final int SEGMENTS = 12;
    private static final String TAG_LAST_CRAFTING = "lastCrafting";
    private static final String TAG_STORED_RECIPE_PREFIX = "storedRecipe";
    private static final String TAG_ITEM_PREFIX = "item";
    private static final String TAG_EQUIPPED = "equipped";
    private static final String TAG_ROTATION_BASE = "rotationBase";

    public ItemCraftingHalo() {
        this("craftingHalo");
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public ItemCraftingHalo(String name) {
        this.func_77655_b(name);
        this.func_77625_d(1);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        int segment = ItemCraftingHalo.getSegmentLookedAt(stack, (EntityLivingBase)player);
        ItemStack itemForPos = ItemCraftingHalo.getItemForSlot(stack, segment);
        if (segment == 0) {
            player.openGui((Object)Botania.instance, 1, world, 0, 0, 0);
        } else if (itemForPos == null) {
            ItemCraftingHalo.assignRecipe(stack, itemForPos, segment);
        } else {
            this.tryCraft(player, stack, segment, true, ItemCraftingHalo.getFakeInv(player), true);
        }
        return stack;
    }

    public static IInventory getFakeInv(EntityPlayer player) {
        InventoryHelper.GenericInventory tempInv = new InventoryHelper.GenericInventory("temp", false, player.field_71071_by.func_70302_i_() - 4);
        tempInv.copyFrom((IInventory)player.field_71071_by);
        return tempInv;
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int pos, boolean equipped) {
        boolean eqLastTick = ItemCraftingHalo.wasEquipped(stack);
        if (eqLastTick != equipped) {
            ItemCraftingHalo.setEquipped(stack, equipped);
        }
        if (!equipped && entity instanceof EntityLivingBase) {
            int angles = 360;
            int segAngles = angles / 12;
            float shift = segAngles / 2;
            ItemCraftingHalo.setRotationBase(stack, ItemCraftingHalo.getCheckingAngle((EntityLivingBase)entity) - shift);
        }
    }

    void tryCraft(EntityPlayer player, ItemStack stack, int slot, boolean particles, IInventory inv, boolean validate) {
        ItemStack itemForPos = ItemCraftingHalo.getItemForSlot(stack, slot);
        if (itemForPos == null) {
            return;
        }
        ItemStack[] recipe = ItemCraftingHalo.getCraftingItems(stack, slot);
        if (validate) {
            recipe = ItemCraftingHalo.validateRecipe(player, stack, recipe, slot);
        }
        if (ItemCraftingHalo.canCraft(player, recipe, inv)) {
            ItemCraftingHalo.doCraft(player, recipe, particles);
        }
    }

    private static ItemStack[] validateRecipe(EntityPlayer player, ItemStack stack, ItemStack[] recipe, int slot) {
        InventoryCrafting fakeInv = new InventoryCrafting((Container)new ContainerWorkbench(player.field_71071_by, player.field_70170_p, 0, 0, 0), 3, 3);
        for (int i = 0; i < 9; ++i) {
            fakeInv.func_70299_a(i, recipe[i]);
        }
        ItemStack result = CraftingManager.func_77594_a().func_82787_a(fakeInv, player.field_70170_p);
        if (result == null) {
            ItemCraftingHalo.assignRecipe(stack, recipe[9], slot);
            return null;
        }
        if (!result.func_77969_a(recipe[9]) || result.field_77994_a != recipe[9].field_77994_a || !ItemStack.func_77970_a((ItemStack)recipe[9], (ItemStack)result)) {
            ItemCraftingHalo.assignRecipe(stack, recipe[9], slot);
            return null;
        }
        return recipe;
    }

    private static boolean canCraft(EntityPlayer player, ItemStack[] recipe, IInventory inv) {
        if (recipe == null) {
            return false;
        }
        if (recipe[9].field_77994_a != InventoryHelper.testInventoryInsertion(inv, recipe[9], ForgeDirection.UNKNOWN)) {
            return false;
        }
        return ItemCraftingHalo.consumeRecipeIngredients(recipe, inv, null);
    }

    private static void doCraft(EntityPlayer player, ItemStack[] recipe, boolean particles) {
        ItemCraftingHalo.consumeRecipeIngredients(recipe, (IInventory)player.field_71071_by, player);
        if (!player.field_71071_by.func_70441_a(recipe[9])) {
            player.func_71019_a(recipe[9], false);
        }
        if (!particles) {
            return;
        }
        Vec3 lookVec3 = player.func_70040_Z();
        Vector3 centerVector = Vector3.fromEntityCenter((Entity)player).add(lookVec3.field_72450_a * 3.0, 1.3, lookVec3.field_72449_c * 3.0);
        float m = 0.1f;
        for (int i = 0; i < 4; ++i) {
            Botania.proxy.wispFX(player.field_70170_p, centerVector.x, centerVector.y, centerVector.z, 1.0f, 0.0f, 1.0f, 0.2f + 0.2f * (float)Math.random(), ((float)Math.random() - 0.5f) * m, ((float)Math.random() - 0.5f) * m, ((float)Math.random() - 0.5f) * m);
        }
    }

    private static boolean consumeRecipeIngredients(ItemStack[] recipe, IInventory inv, EntityPlayer player) {
        for (int i = 0; i < 9; ++i) {
            ItemStack ingredient = recipe[i];
            if (ingredient == null || ItemCraftingHalo.consumeFromInventory(ingredient, inv, player)) continue;
            return false;
        }
        return true;
    }

    private static boolean consumeFromInventory(ItemStack stack, IInventory inv, EntityPlayer player) {
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stackAt = inv.func_70301_a(i);
            if (stackAt == null || !stack.func_77969_a(stackAt) || !ItemStack.func_77970_a((ItemStack)stack, (ItemStack)stackAt)) continue;
            boolean consume = true;
            ItemStack container = stackAt.func_77973_b().getContainerItem(stackAt);
            if (container != null) {
                if (container == stackAt) {
                    consume = false;
                } else {
                    InventoryHelper.insertItemIntoInventory(inv, container);
                    if (container.field_77994_a != 0 && player != null) {
                        player.func_71019_a(container, false);
                    }
                }
            }
            if (consume) {
                --stackAt.field_77994_a;
                if (stackAt.field_77994_a == 0) {
                    inv.func_70299_a(i, null);
                }
            }
            return true;
        }
        return false;
    }

    public boolean onEntitySwing(EntityLivingBase player, ItemStack stack) {
        int segment = ItemCraftingHalo.getSegmentLookedAt(stack, player);
        if (segment == 0) {
            return false;
        }
        ItemStack itemForPos = ItemCraftingHalo.getItemForSlot(stack, segment);
        if (itemForPos != null && player.func_70093_af()) {
            ItemCraftingHalo.assignRecipe(stack, itemForPos, segment);
            return true;
        }
        return false;
    }

    private static int getSegmentLookedAt(ItemStack stack, EntityLivingBase player) {
        ItemCraftingHalo.getRotationBase(stack);
        float yaw = ItemCraftingHalo.getCheckingAngle(player, ItemCraftingHalo.getRotationBase(stack));
        int angles = 360;
        int segAngles = angles / 12;
        for (int seg = 0; seg < 12; ++seg) {
            float calcAngle = (float)seg * (float)segAngles;
            if (!(yaw >= calcAngle) || !(yaw < calcAngle + (float)segAngles)) continue;
            return seg;
        }
        return -1;
    }

    private static float getCheckingAngle(EntityLivingBase player) {
        return ItemCraftingHalo.getCheckingAngle(player, 0.0f);
    }

    private static float getCheckingAngle(EntityLivingBase player, float base) {
        float angle;
        float yaw = MathHelper.func_76142_g((float)player.field_70177_z) + 90.0f;
        int angles = 360;
        int segAngles = angles / 12;
        float shift = segAngles / 2;
        if (yaw < 0.0f) {
            yaw = 180.0f + (180.0f + yaw);
        }
        if ((angle = 360.0f - (yaw -= 360.0f - base) + shift) < 0.0f) {
            angle = 360.0f + angle;
        }
        return angle;
    }

    public static ItemStack getItemForSlot(ItemStack stack, int slot) {
        if (slot == 0) {
            return craftingTable;
        }
        if (slot >= 12) {
            return null;
        }
        NBTTagCompound cmp = ItemCraftingHalo.getStoredRecipeCompound(stack, slot);
        if (cmp != null) {
            ItemStack cmpStack = ItemCraftingHalo.getLastCraftingItem(cmp, 9);
            return cmpStack;
        }
        return null;
    }

    public static void assignRecipe(ItemStack stack, ItemStack itemForPos, int pos) {
        if (itemForPos != null) {
            ItemNBTHelper.setCompound(stack, TAG_STORED_RECIPE_PREFIX + pos, new NBTTagCompound());
        } else {
            ItemNBTHelper.setCompound(stack, TAG_STORED_RECIPE_PREFIX + pos, ItemCraftingHalo.getLastCraftingCompound(stack, false));
        }
    }

    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (!(event.craftMatrix instanceof InventoryCraftingHalo)) {
            return;
        }
        for (int i = 0; i < event.player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stack = event.player.field_71071_by.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof ItemCraftingHalo)) continue;
            this.saveRecipeToStack(event, stack);
        }
    }

    private void saveRecipeToStack(PlayerEvent.ItemCraftedEvent event, ItemStack stack) {
        NBTTagCompound cmp = new NBTTagCompound();
        NBTTagCompound cmp1 = new NBTTagCompound();
        ItemStack result = CraftingManager.func_77594_a().func_82787_a((InventoryCrafting)event.craftMatrix, event.player.field_70170_p);
        if (result != null) {
            result.func_77955_b(cmp1);
            cmp.func_74782_a("item9", (NBTBase)cmp1);
            for (int i = 0; i < 9; ++i) {
                cmp1 = new NBTTagCompound();
                ItemStack stackSlot = event.craftMatrix.func_70301_a(i);
                if (stackSlot != null) {
                    ItemStack writeStack = stackSlot.func_77946_l();
                    writeStack.field_77994_a = 1;
                    writeStack.func_77955_b(cmp1);
                }
                cmp.func_74782_a(TAG_ITEM_PREFIX + i, (NBTBase)cmp1);
            }
        }
        ItemNBTHelper.setCompound(stack, TAG_LAST_CRAFTING, cmp);
    }

    public static ItemStack[] getLastCraftingItems(ItemStack stack) {
        return ItemCraftingHalo.getCraftingItems(stack, 12);
    }

    public static ItemStack[] getCraftingItems(ItemStack stack, int slot) {
        ItemStack[] stackArray = new ItemStack[10];
        NBTTagCompound cmp = ItemCraftingHalo.getStoredRecipeCompound(stack, slot);
        if (cmp != null) {
            for (int i = 0; i < stackArray.length; ++i) {
                stackArray[i] = ItemCraftingHalo.getLastCraftingItem(cmp, i);
            }
        }
        return stackArray;
    }

    public static NBTTagCompound getLastCraftingCompound(ItemStack stack, boolean nullify) {
        return ItemNBTHelper.getCompound(stack, TAG_LAST_CRAFTING, nullify);
    }

    public static NBTTagCompound getStoredRecipeCompound(ItemStack stack, int slot) {
        return slot == 12 ? ItemCraftingHalo.getLastCraftingCompound(stack, true) : ItemNBTHelper.getCompound(stack, TAG_STORED_RECIPE_PREFIX + slot, true);
    }

    public static ItemStack getLastCraftingItem(ItemStack stack, int pos) {
        return ItemCraftingHalo.getLastCraftingItem(ItemCraftingHalo.getLastCraftingCompound(stack, true), pos);
    }

    public static ItemStack getLastCraftingItem(NBTTagCompound cmp, int pos) {
        if (cmp == null) {
            return null;
        }
        NBTTagCompound cmp1 = cmp.func_74775_l(TAG_ITEM_PREFIX + pos);
        if (cmp1 == null) {
            return null;
        }
        return ItemStack.func_77949_a((NBTTagCompound)cmp1);
    }

    public static boolean wasEquipped(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_EQUIPPED, false);
    }

    public static void setEquipped(ItemStack stack, boolean equipped) {
        ItemNBTHelper.setBoolean(stack, TAG_EQUIPPED, equipped);
    }

    public static float getRotationBase(ItemStack stack) {
        return ItemNBTHelper.getFloat(stack, TAG_ROTATION_BASE, 0.0f);
    }

    public static void setRotationBase(ItemStack stack, float rotation) {
        ItemNBTHelper.setFloat(stack, TAG_ROTATION_BASE, rotation);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        ItemStack stack = player.func_71045_bC();
        if (stack != null && stack.func_77973_b() instanceof ItemCraftingHalo) {
            this.render(stack, (EntityPlayer)player, event.partialTicks);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void render(ItemStack stack, EntityPlayer player, float partialTicks) {
        Minecraft mc = Minecraft.func_71410_x();
        Tessellator tess = Tessellator.field_78398_a;
        Tessellator.renderingWorldRenderer = false;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        float alpha = ((float)Math.sin(((float)ClientTickHandler.ticksInGame + partialTicks) * 0.2f) * 0.5f + 0.5f) * 0.4f + 0.3f;
        double posX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double posY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double posZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        GL11.glTranslated((double)(posX - RenderManager.field_78725_b), (double)(posY - RenderManager.field_78726_c), (double)(posZ - RenderManager.field_78723_d));
        float base = ItemCraftingHalo.getRotationBase(stack);
        int angles = 360;
        int segAngles = angles / 12;
        float shift = base - (float)(segAngles / 2);
        float u = 1.0f;
        float v = 0.25f;
        float s = 3.0f;
        float m = 0.8f;
        float y = v * s * 2.0f;
        float y0 = 0.0f;
        int segmentLookedAt = ItemCraftingHalo.getSegmentLookedAt(stack, (EntityLivingBase)player);
        for (int seg = 0; seg < 12; ++seg) {
            ItemStack slotStack;
            boolean inside = false;
            float rotationAngle = ((float)seg + 0.5f) * (float)segAngles + shift;
            GL11.glPushMatrix();
            GL11.glRotatef((float)rotationAngle, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)(s * m), (float)-0.75f, (float)0.0f);
            if (segmentLookedAt == seg) {
                inside = true;
            }
            if ((slotStack = ItemCraftingHalo.getItemForSlot(stack, seg)) != null) {
                mc.field_71446_o.func_110577_a(slotStack.func_77973_b() instanceof ItemBlock ? TextureMap.field_110575_b : TextureMap.field_110576_c);
                if (slotStack.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)slotStack.func_77973_b()).func_149645_b())) {
                    float scale = seg == 0 ? 0.75f : 0.6f;
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)(seg == 0 ? 0.5f : 0.0f), (float)(seg == 0 ? -0.1f : 0.6f), (float)0.0f);
                    RenderBlocks.getInstance().func_147800_a(Block.func_149634_a((Item)slotStack.func_77973_b()), slotStack.func_77960_j(), 1.0f);
                } else {
                    GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.5f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    int renderPass = 0;
                    do {
                        IIcon icon;
                        if ((icon = slotStack.func_77973_b().getIcon(slotStack, renderPass)) == null) continue;
                        Color color = new Color(slotStack.func_77973_b().func_82790_a(slotStack, renderPass));
                        GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                        float f = icon.func_94209_e();
                        float f1 = icon.func_94212_f();
                        float f2 = icon.func_94206_g();
                        float f3 = icon.func_94210_h();
                        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    } while (++renderPass < slotStack.func_77973_b().getRenderPasses(slotStack.func_77960_j()));
                }
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            float a = alpha;
            if (inside) {
                a += 0.3f;
                y0 = -y;
            }
            if (seg % 2 == 0) {
                GL11.glColor4f((float)0.6f, (float)0.6f, (float)0.6f, (float)a);
            } else {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
            }
            GL11.glDisable((int)2884);
            ItemCraftingHalo item = (ItemCraftingHalo)stack.func_77973_b();
            mc.field_71446_o.func_110577_a(item.getGlowResource());
            tess.func_78382_b();
            for (int i = 0; i < segAngles; ++i) {
                float ang = (float)(i + seg * segAngles) + shift;
                double xp = Math.cos((double)ang * Math.PI / 180.0) * (double)s;
                double zp = Math.sin((double)ang * Math.PI / 180.0) * (double)s;
                tess.func_78374_a(xp * (double)m, (double)y, zp * (double)m, (double)u, (double)v);
                tess.func_78374_a(xp, (double)y0, zp, (double)u, 0.0);
                xp = Math.cos((double)(ang + 1.0f) * Math.PI / 180.0) * (double)s;
                zp = Math.sin((double)(ang + 1.0f) * Math.PI / 180.0) * (double)s;
                tess.func_78374_a(xp, (double)y0, zp, 0.0, 0.0);
                tess.func_78374_a(xp * (double)m, (double)y, zp * (double)m, 0.0, (double)v);
            }
            y0 = 0.0f;
            tess.func_78381_a();
            GL11.glEnable((int)2884);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getGlowResource() {
        return glowTexture;
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderHUD(ScaledResolution resolution, EntityPlayer player, ItemStack stack) {
        Minecraft mc = Minecraft.func_71410_x();
        int slot = ItemCraftingHalo.getSegmentLookedAt(stack, (EntityLivingBase)player);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (slot == 0) {
            String name = craftingTable.func_82833_r();
            int l = mc.field_71466_p.func_78256_a(name);
            int x = resolution.func_78326_a() / 2 - l / 2;
            int y = resolution.func_78328_b() / 2 - 65;
            Gui.func_73734_a((int)(x - 6), (int)(y - 6), (int)(x + l + 6), (int)(y + 37), (int)0x22000000);
            Gui.func_73734_a((int)(x - 4), (int)(y - 4), (int)(x + l + 4), (int)(y + 35), (int)0x22000000);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)32826);
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, craftingTable, resolution.func_78326_a() / 2 - 8, resolution.func_78328_b() / 2 - 52);
            RenderHelper.func_74518_a();
            mc.field_71466_p.func_78261_a(name, x, y, 0xFFFFFF);
        } else {
            ItemStack[] recipe = ItemCraftingHalo.getCraftingItems(stack, slot);
            String label = StatCollector.func_74838_a((String)"botaniamisc.unsetRecipe");
            boolean setRecipe = false;
            if (recipe[9] == null) {
                recipe = ItemCraftingHalo.getCraftingItems(stack, 12);
            } else {
                label = recipe[9].func_82833_r();
                setRecipe = true;
            }
            ItemCraftingHalo.renderRecipe(resolution, label, recipe, player, setRecipe);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderRecipe(ScaledResolution resolution, String label, ItemStack[] recipe, EntityPlayer player, boolean setRecipe) {
        Minecraft mc = Minecraft.func_71410_x();
        if (recipe[9] != null) {
            int x = resolution.func_78326_a() / 2 - 45;
            int y = resolution.func_78328_b() / 2 - 90;
            Gui.func_73734_a((int)(x - 6), (int)(y - 6), (int)(x + 90 + 6), (int)(y + 60), (int)0x22000000);
            Gui.func_73734_a((int)(x - 4), (int)(y - 4), (int)(x + 90 + 4), (int)(y + 58), (int)0x22000000);
            Gui.func_73734_a((int)(x + 66), (int)(y + 14), (int)(x + 92), (int)(y + 40), (int)0x22000000);
            Gui.func_73734_a((int)(x - 2), (int)(y - 2), (int)(x + 56), (int)(y + 56), (int)0x22000000);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)32826);
            for (int i = 0; i < 9; ++i) {
                ItemStack stack = recipe[i];
                if (stack == null) continue;
                int xpos = x + i % 3 * 18;
                int ypos = y + i / 3 * 18;
                Gui.func_73734_a((int)xpos, (int)ypos, (int)(xpos + 16), (int)(ypos + 16), (int)0x22000000);
                RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, stack, xpos, ypos);
            }
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, recipe[9], x + 72, y + 18);
            RenderItem.getInstance().func_77021_b(mc.field_71466_p, mc.field_71446_o, recipe[9], x + 72, y + 18);
            RenderHelper.func_74518_a();
        }
        int yoff = 110;
        if (setRecipe && !ItemCraftingHalo.canCraft(player, recipe, ItemCraftingHalo.getFakeInv(player))) {
            String warning = EnumChatFormatting.RED + StatCollector.func_74838_a((String)"botaniamisc.cantCraft");
            mc.field_71466_p.func_78261_a(warning, resolution.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(warning) / 2, resolution.func_78328_b() / 2 - yoff, 0xFFFFFF);
            yoff += 12;
        }
        mc.field_71466_p.func_78261_a(label, resolution.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(label) / 2, resolution.func_78328_b() / 2 - yoff, 0xFFFFFF);
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.craftingHaloCraft;
    }
}

