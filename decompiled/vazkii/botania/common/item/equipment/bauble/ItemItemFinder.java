/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  baubles.common.network.PacketHandler
 *  baubles.common.network.PacketSyncBauble
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.AnimalChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketSyncBauble;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemItemFinder
extends ItemBauble
implements IBaubleRender {
    IIcon gemIcon;
    private static final String TAG_POSITIONS = "highlightPositions";

    public ItemItemFinder() {
        super("itemFinder");
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.gemIcon = IconHelper.forItem(par1IconRegister, (Item)this, "Gem");
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if (!(player instanceof EntityPlayer)) {
            return;
        }
        if (player.field_70170_p.field_72995_K) {
            this.tickClient(stack, (EntityPlayer)player);
        } else {
            this.tickServer(stack, (EntityPlayer)player);
        }
    }

    public void tickClient(ItemStack stack, EntityPlayer player) {
        String[] tokens;
        if (!Botania.proxy.isTheClientPlayer((EntityLivingBase)player)) {
            return;
        }
        String pos = ItemNBTHelper.getString(stack, TAG_POSITIONS, "");
        for (String token : tokens = pos.split(";")) {
            if (token.isEmpty()) continue;
            if (token.contains(",")) {
                String[] tokens_ = token.split(",");
                int x = Integer.parseInt(tokens_[0]);
                int y = Integer.parseInt(tokens_[1]);
                int z = Integer.parseInt(tokens_[2]);
                float m = 0.02f;
                Botania.proxy.setWispFXDepthTest(false);
                Botania.proxy.wispFX(player.field_70170_p, (float)x + (float)Math.random(), (float)y + (float)Math.random(), (float)z + (float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 0.15f + 0.05f * (float)Math.random(), m * (float)(Math.random() - 0.5), m * (float)(Math.random() - 0.5), m * (float)(Math.random() - 0.5));
                continue;
            }
            int id = Integer.parseInt(token);
            Entity e = player.field_70170_p.func_73045_a(id);
            if (e == null || !(Math.random() < 0.6)) continue;
            Botania.proxy.setWispFXDepthTest(Math.random() < 0.6);
            Botania.proxy.wispFX(player.field_70170_p, e.field_70165_t + (double)((float)(Math.random() * 0.5 - 0.25) * 0.45f), e.field_70163_u + (double)e.field_70131_O, e.field_70161_v + (double)((float)(Math.random() * 0.5 - 0.25) * 0.45f), (float)Math.random(), (float)Math.random(), (float)Math.random(), 0.15f + 0.05f * (float)Math.random(), -0.05f - 0.03f * (float)Math.random());
        }
        Botania.proxy.setWispFXDepthTest(true);
    }

    public void tickServer(ItemStack stack, EntityPlayer player) {
        String positions;
        String current;
        ItemStack pstack = player.func_71045_bC();
        StringBuilder positionsBuilder = new StringBuilder();
        if (pstack != null || player.func_70093_af()) {
            int range = 24;
            List entities = player.field_70170_p.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)range), (double)(player.field_70163_u - (double)range), (double)(player.field_70161_v - (double)range), (double)(player.field_70165_t + (double)range), (double)(player.field_70163_u + (double)range), (double)(player.field_70161_v + (double)range)));
            for (Entity e : entities) {
                if (e == player) continue;
                if (e instanceof EntityItem) {
                    EntityItem item = (EntityItem)e;
                    ItemStack istack = item.func_92059_d();
                    if (!player.func_70093_af() && (!istack.func_77969_a(pstack) || !ItemStack.func_77970_a((ItemStack)istack, (ItemStack)pstack))) continue;
                    positionsBuilder.append(item.func_145782_y()).append(";");
                    continue;
                }
                if (e instanceof IInventory) {
                    IInventory inv = (IInventory)e;
                    if (!this.scanInventory(inv, pstack)) continue;
                    positionsBuilder.append(e.func_145782_y()).append(";");
                    continue;
                }
                if (e instanceof EntityHorse) {
                    EntityHorse horse = (EntityHorse)e;
                    AnimalChest chest = (AnimalChest)ReflectionHelper.getPrivateValue(EntityHorse.class, (Object)horse, (String[])LibObfuscation.HORSE_CHEST);
                    if (!this.scanInventory((IInventory)chest, pstack)) continue;
                    positionsBuilder.append(horse.func_145782_y()).append(";");
                    continue;
                }
                if (e instanceof EntityPlayer) {
                    EntityPlayer player_ = (EntityPlayer)e;
                    InventoryPlayer inv = player_.field_71071_by;
                    InventoryBaubles binv = PlayerHandler.getPlayerBaubles((EntityPlayer)player_);
                    if (!this.scanInventory((IInventory)inv, pstack) && !this.scanInventory((IInventory)binv, pstack)) continue;
                    positionsBuilder.append(player_.func_145782_y()).append(";");
                    continue;
                }
                if (e instanceof EntityVillager) {
                    EntityVillager villager = (EntityVillager)e;
                    MerchantRecipeList recipes = villager.func_70934_b(player);
                    if (pstack == null || recipes == null) continue;
                    for (MerchantRecipe recipe : recipes) {
                        if (recipe == null || recipe.func_82784_g() || !this.equalStacks(pstack, recipe.func_77394_a()) && !this.equalStacks(pstack, recipe.func_77397_d())) continue;
                        positionsBuilder.append(villager.func_145782_y()).append(";");
                    }
                    continue;
                }
                if (!(e instanceof EntityLivingBase)) continue;
                EntityLivingBase living = (EntityLivingBase)e;
                ItemStack estack = living.func_71124_b(0);
                if (pstack == null || estack == null || !this.equalStacks(estack, pstack)) continue;
                positionsBuilder.append(living.func_145782_y()).append(";");
            }
            if (pstack != null) {
                range = 12;
                int x = MathHelper.func_76128_c((double)player.field_70165_t);
                int y = MathHelper.func_76128_c((double)player.field_70163_u);
                int z = MathHelper.func_76128_c((double)player.field_70161_v);
                for (int i = -range; i < range + 1; ++i) {
                    for (int j = -range; j < range + 1; ++j) {
                        for (int k = -range; k < range + 1; ++k) {
                            IInventory inv;
                            int xp = x + i;
                            int yp = y + j;
                            int zp = z + k;
                            TileEntity tile = player.field_70170_p.func_147438_o(xp, yp, zp);
                            if (tile == null || !(tile instanceof IInventory) || !this.scanInventory(inv = (IInventory)tile, pstack)) continue;
                            positionsBuilder.append(xp).append(",").append(yp).append(",").append(zp).append(";");
                        }
                    }
                }
            }
        }
        if (!(current = ItemNBTHelper.getString(stack, TAG_POSITIONS, "")).equals(positions = positionsBuilder.toString())) {
            ItemNBTHelper.setString(stack, TAG_POSITIONS, positions);
            PacketHandler.INSTANCE.sendToAll((IMessage)new PacketSyncBauble(player, 0));
        }
    }

    boolean equalStacks(ItemStack stack1, ItemStack stack2) {
        return stack1.func_77969_a(stack2) && ItemStack.func_77970_a((ItemStack)stack1, (ItemStack)stack2);
    }

    boolean scanInventory(IInventory inv, ItemStack pstack) {
        if (pstack == null) {
            return false;
        }
        for (int l = 0; l < inv.func_70302_i_(); ++l) {
            ItemStack istack = inv.func_70301_a(l);
            if (istack == null || !this.equalStacks(istack, pstack)) continue;
            return true;
        }
        return false;
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.HEAD) {
            float f = this.gemIcon.func_94209_e();
            float f1 = this.gemIcon.func_94212_f();
            float f2 = this.gemIcon.func_94206_g();
            float f3 = this.gemIcon.func_94210_h();
            boolean armor = event.entityPlayer.func_82169_q(3) != null;
            IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)(armor ? -0.3f : -0.25f));
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)this.gemIcon.func_94211_a(), (int)this.gemIcon.func_94216_b(), (float)0.0625f);
        }
    }
}

