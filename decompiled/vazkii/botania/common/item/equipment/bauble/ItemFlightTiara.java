/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  javax.xml.bind.annotation.adapters.HexBinaryAdapter
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemFlightTiara
extends ItemBauble
implements IManaUsingItem,
IBaubleRender,
ICraftAchievement {
    private static ResourceLocation textureHud = new ResourceLocation("botania:textures/gui/hudIcons.png");
    private static ResourceLocation textureHalo = new ResourceLocation("botania:textures/misc/halo.png");
    private static final String TAG_FLYING = "flying";
    private static final String TAG_TIME_LEFT = "timeLeft";
    private static final String TAG_INFINITE_FLIGHT = "infiniteFlight";
    private static final String TAG_DASH_COOLDOWN = "dashCooldown";
    private static final String TAG_IS_SPRINTING = "isSprinting";
    public static List<String> playersWithFlight = new ArrayList<String>();
    private static final int COST = 35;
    private static final int COST_OVERKILL = 105;
    private static final int MAX_FLY_TIME = 1200;
    public static IIcon[] wingIcons;
    private static final int SUBTYPES = 8;
    private static final int WING_TYPES = 9;

    public ItemFlightTiara() {
        super("flightTiara");
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        this.func_77627_a(true);
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        wingIcons = new IIcon[9];
        for (int i = 0; i < 9; ++i) {
            ItemFlightTiara.wingIcons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i + 1);
        }
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 9; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void addHiddenTooltip(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addHiddenTooltip(par1ItemStack, par2EntityPlayer, par3List, par4);
        par3List.add(StatCollector.func_74838_a((String)("botania.wings" + par1ItemStack.func_77960_j())));
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        super.onEquipped(stack, player);
        if (stack.func_77960_j() != 9 && this.hash(stack.func_82833_r()).equals("16E1BDFD1D6AE1A954C9C5E1B2D9099780F3E1724541F1F2F77310B769CFFBAC")) {
            stack.func_77964_b(9);
            stack.func_77978_p().func_82580_o("display");
        }
    }

    String hash(String str) {
        if (str != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                return new HexBinaryAdapter().marshal(md.digest(this.salt(str).getBytes()));
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    String salt(String str) {
        str = str + "wowsuchsaltmuchsecurityverywow";
        SecureRandom rand = new SecureRandom(str.getBytes());
        int l = str.length();
        int steps = rand.nextInt(l);
        char[] chrs = str.toCharArray();
        for (int i = 0; i < steps; ++i) {
            char c;
            int indB;
            int indA = rand.nextInt(l);
            while ((indB = rand.nextInt(l)) == indA) {
            }
            chrs[indA] = c = (char)(chrs[indA] ^ chrs[indB]);
        }
        return String.copyValueOf(chrs);
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if (player instanceof EntityPlayer) {
            int time;
            EntityPlayer p = (EntityPlayer)player;
            boolean flying = p.field_71075_bZ.field_75100_b;
            boolean wasSprting = ItemNBTHelper.getBoolean(stack, TAG_IS_SPRINTING, false);
            boolean isSprinting = p.func_70051_ag();
            if (isSprinting != wasSprting) {
                ItemNBTHelper.setBoolean(stack, TAG_IS_SPRINTING, isSprinting);
            }
            int newTime = time = ItemNBTHelper.getInt(stack, TAG_TIME_LEFT, 1200);
            Vector3 look = new Vector3(p.func_70040_Z());
            look.y = 0.0;
            look.normalize();
            if (flying) {
                if (time > 0 && !ItemNBTHelper.getBoolean(stack, TAG_INFINITE_FLIGHT, false)) {
                    --newTime;
                }
                int maxCd = 80;
                int cooldown = ItemNBTHelper.getInt(stack, TAG_DASH_COOLDOWN, 0);
                if (!wasSprting && isSprinting && cooldown == 0) {
                    p.field_70159_w += look.x;
                    p.field_70179_y += look.z;
                    p.field_70170_p.func_72956_a((Entity)p, "botania:dash", 1.0f, 1.0f);
                    ItemNBTHelper.setInt(stack, TAG_DASH_COOLDOWN, 80);
                } else if (cooldown > 0) {
                    if (80 - cooldown < 2) {
                        player.func_70060_a(0.0f, 1.0f, 5.0f);
                    } else if (80 - cooldown < 10) {
                        player.func_70031_b(false);
                    }
                    ItemNBTHelper.setInt(stack, TAG_DASH_COOLDOWN, cooldown - 2);
                    if (player instanceof EntityPlayerMP) {
                        BotaniaAPI.internalHandler.sendBaubleUpdatePacket((EntityPlayer)((EntityPlayerMP)player), 0);
                    }
                }
            } else if (!flying) {
                boolean doGlide;
                boolean bl = doGlide = player.func_70093_af() && !player.field_70122_E && player.field_70143_R >= 2.0f;
                if (time < 1200 && player.field_70173_aa % (doGlide ? 6 : 2) == 0) {
                    ++newTime;
                }
                if (doGlide) {
                    player.field_70181_x = Math.max((double)-0.15f, player.field_70181_x);
                    float mul = 0.6f;
                    player.field_70159_w = look.x * (double)mul;
                    player.field_70179_y = look.z * (double)mul;
                    player.field_70143_R = 2.0f;
                }
            }
            ItemNBTHelper.setBoolean(stack, TAG_FLYING, flying);
            if (newTime != time) {
                ItemNBTHelper.setInt(stack, TAG_TIME_LEFT, newTime);
            }
        }
    }

    @SubscribeEvent
    public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack tiara = PlayerHandler.getPlayerBaubles((EntityPlayer)player).func_70301_a(0);
            int left = ItemNBTHelper.getInt(tiara, TAG_TIME_LEFT, 1200);
            if (playersWithFlight.contains(ItemFlightTiara.playerStr(player))) {
                if (this.shouldPlayerHaveFlight(player)) {
                    player.field_71075_bZ.field_75101_c = true;
                    if (player.field_71075_bZ.field_75100_b) {
                        if (!player.field_70170_p.field_72995_K) {
                            ManaItemHandler.requestManaExact(tiara, player, this.getCost(tiara, left), true);
                        } else if (Math.abs(player.field_70159_w) > 0.1 || Math.abs(player.field_70179_y) > 0.1) {
                            double x = event.entityLiving.field_70165_t - 0.5;
                            double y = event.entityLiving.field_70163_u - 1.7;
                            double z = event.entityLiving.field_70161_v - 0.5;
                            player.func_146103_bH().getName();
                            float r = 1.0f;
                            float g = 1.0f;
                            float b = 1.0f;
                            switch (tiara.func_77960_j()) {
                                case 2: {
                                    r = 0.1f;
                                    g = 0.1f;
                                    b = 0.1f;
                                    break;
                                }
                                case 3: {
                                    r = 0.0f;
                                    g = 0.6f;
                                    break;
                                }
                                case 4: {
                                    g = 0.3f;
                                    b = 0.3f;
                                    break;
                                }
                                case 5: {
                                    r = 0.6f;
                                    g = 0.0f;
                                    b = 0.6f;
                                    break;
                                }
                                case 6: {
                                    r = 0.4f;
                                    g = 0.0f;
                                    b = 0.0f;
                                    break;
                                }
                                case 7: {
                                    r = 0.2f;
                                    g = 0.6f;
                                    b = 0.2f;
                                    break;
                                }
                                case 8: {
                                    r = 0.85f;
                                    g = 0.85f;
                                    b = 0.0f;
                                    break;
                                }
                                case 9: {
                                    r = 0.0f;
                                    b = 0.0f;
                                }
                            }
                            for (int i = 0; i < 2; ++i) {
                                Botania.proxy.sparkleFX(event.entityLiving.field_70170_p, x + Math.random() * (double)event.entityLiving.field_70130_N, y + Math.random() * 0.4, z + Math.random() * (double)event.entityLiving.field_70130_N, r, g, b, 2.0f * (float)Math.random(), 20);
                            }
                        }
                    }
                } else {
                    if (!player.field_71075_bZ.field_75098_d) {
                        player.field_71075_bZ.field_75101_c = false;
                        player.field_71075_bZ.field_75100_b = false;
                        player.field_71075_bZ.field_75102_a = false;
                    }
                    playersWithFlight.remove(ItemFlightTiara.playerStr(player));
                }
            } else if (this.shouldPlayerHaveFlight(player)) {
                playersWithFlight.add(ItemFlightTiara.playerStr(player));
                player.field_71075_bZ.field_75101_c = true;
            }
        }
    }

    @SubscribeEvent
    public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        String username = event.player.func_146103_bH().getName();
        playersWithFlight.remove(username + ":false");
        playersWithFlight.remove(username + ":true");
    }

    public static String playerStr(EntityPlayer player) {
        return player.func_146103_bH().getName() + ":" + player.field_70170_p.field_72995_K;
    }

    private boolean shouldPlayerHaveFlight(EntityPlayer player) {
        ItemStack armor = PlayerHandler.getPlayerBaubles((EntityPlayer)player).func_70301_a(0);
        if (armor != null && armor.func_77973_b() == this) {
            int left = ItemNBTHelper.getInt(armor, TAG_TIME_LEFT, 1200);
            boolean flying = ItemNBTHelper.getBoolean(armor, TAG_FLYING, false);
            return (left > (flying ? 0 : 120) || player.field_71071_by.func_146028_b(ModItems.flugelEye)) && ManaItemHandler.requestManaExact(armor, player, this.getCost(armor, left), false);
        }
        return false;
    }

    public int getCost(ItemStack stack, int timeLeft) {
        return timeLeft <= 0 ? 105 : 35;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        int meta = stack.func_77960_j();
        if (type == IBaubleRender.RenderType.BODY) {
            if (meta > 0 && meta <= wingIcons.length) {
                IIcon icon = wingIcons[meta - 1];
                Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
                boolean flying = event.entityPlayer.field_71075_bZ.field_75100_b;
                float rz = 120.0f;
                float rx = 20.0f + (float)((Math.sin((double)((float)event.entityPlayer.field_70173_aa + event.partialRenderTick) * (double)(flying ? 0.4f : 0.2f)) + 0.5) * (double)(flying ? 30.0f : 5.0f));
                float ry = 0.0f;
                float h = 0.2f;
                float i = 0.15f;
                float s = 1.0f;
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                int light = 0xF000F0;
                int lightmapX = light % 65536;
                int lightmapY = light / 65536;
                switch (meta) {
                    case 1: {
                        h = 0.4f;
                        break;
                    }
                    case 2: {
                        s = 1.3f;
                        break;
                    }
                    case 3: {
                        h = -0.1f;
                        rz = 0.0f;
                        rx = 0.0f;
                        i = 0.3f;
                        break;
                    }
                    case 4: {
                        rz = 180.0f;
                        h = 0.5f;
                        rx = 20.0f;
                        ry = -((float)((Math.sin((double)((float)event.entityPlayer.field_70173_aa + event.partialRenderTick) * (double)(flying ? 0.4f : 0.2f)) + (double)0.6f) * (double)(flying ? 30.0f : 5.0f)));
                        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
                        break;
                    }
                    case 5: {
                        h = 0.8f;
                        rz = 180.0f;
                        ry = -rx;
                        rx = 0.0f;
                        s = 2.0f;
                        break;
                    }
                    case 6: {
                        rz = 150.0f;
                        break;
                    }
                    case 7: {
                        h = -0.1f;
                        rz = 0.0f;
                        ry = -rx;
                        rx = 0.0f;
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.5f + (float)Math.cos((double)((float)event.entityPlayer.field_70173_aa + event.partialRenderTick) * (double)0.3f) * 0.2f));
                        break;
                    }
                    case 8: {
                        h = 0.1f;
                        break;
                    }
                    case 9: {
                        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
                        rz = 180.0f;
                        rx = 0.0f;
                        s = 1.5f;
                        h = 1.2f;
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.5f + (flying ? (float)Math.cos((double)((float)event.entityPlayer.field_70173_aa + event.partialRenderTick) * (double)0.3f) * 0.25f + 0.25f : 0.0f)));
                    }
                }
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                float sr = 1.0f / s;
                IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
                GL11.glTranslatef((float)0.0f, (float)h, (float)i);
                GL11.glRotatef((float)rz, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)rx, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)ry, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)s, (float)s, (float)s);
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.03125f);
                GL11.glScalef((float)sr, (float)sr, (float)sr);
                GL11.glRotatef((float)(-ry), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-rx), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(-rz), (float)0.0f, (float)0.0f, (float)1.0f);
                if (meta != 2) {
                    GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
                    GL11.glRotatef((float)rz, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)rx, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)ry, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)s, (float)s, (float)s);
                    ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.03125f);
                    GL11.glScalef((float)sr, (float)sr, (float)sr);
                    GL11.glRotatef((float)(-ry), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)(-rx), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)(-rz), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPopMatrix();
            }
        } else if (meta == 1) {
            ItemFlightTiara.renderHalo(event.entityPlayer, event.partialRenderTick);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderHalo(EntityPlayer player, float partialTicks) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(textureHalo);
        if (player != null) {
            IBaubleRender.Helper.translateToHeadLevel(player);
        }
        GL11.glRotated((double)30.0, (double)1.0, (double)0.0, (double)-1.0);
        GL11.glTranslatef((float)-0.1f, (float)-0.5f, (float)-0.1f);
        if (player != null) {
            GL11.glRotatef((float)((float)player.field_70173_aa + partialTicks), (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glRotatef((float)Botania.proxy.getWorldElapsedTicks(), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        Tessellator tes = Tessellator.field_78398_a;
        ShaderHelper.useShader(ShaderHelper.halo);
        tes.func_78382_b();
        tes.func_78374_a(-0.75, 0.0, -0.75, 0.0, 0.0);
        tes.func_78374_a(-0.75, 0.0, 0.75, 0.0, 1.0);
        tes.func_78374_a(0.75, 0.0, 0.75, 1.0, 1.0);
        tes.func_78374_a(0.75, 0.0, -0.75, 1.0, 0.0);
        tes.func_78381_a();
        ShaderHelper.releaseShader();
        GL11.glEnable((int)2896);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderHUD(ScaledResolution resolution, EntityPlayer player, ItemStack stack) {
        int xo;
        int u = Math.max(1, stack.func_77960_j()) * 9 - 9;
        int v = 0;
        Minecraft mc = Minecraft.func_71410_x();
        mc.field_71446_o.func_110577_a(textureHud);
        int x = xo = resolution.func_78326_a() / 2 + 10;
        int y = resolution.func_78328_b() - ConfigHandler.flightBarHeight;
        if (player.func_70086_ai() < 300) {
            y = resolution.func_78328_b() - ConfigHandler.flightBarBreathHeight;
        }
        int left = ItemNBTHelper.getInt(stack, TAG_TIME_LEFT, 1200);
        int segTime = 120;
        int segs = left / segTime + 1;
        int last = left % segTime;
        for (int i = 0; i < segs; ++i) {
            float trans = 1.0f;
            if (i == segs - 1) {
                trans = (float)last / (float)segTime;
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDisable((int)3008);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)trans);
            RenderHelper.drawTexturedModalRect(x, y, 0.0f, u, v, 9, 9);
            x += 8;
        }
        if (player.field_71075_bZ.field_75100_b) {
            int width = ItemNBTHelper.getInt(stack, TAG_DASH_COOLDOWN, 0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (width > 0) {
                Gui.func_73734_a((int)xo, (int)(y - 2), (int)(xo + 80), (int)(y - 1), (int)-2013265920);
            }
            Gui.func_73734_a((int)xo, (int)(y - 2), (int)(xo + width), (int)(y - 1), (int)-1);
        }
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        mc.field_71446_o.func_110577_a(Gui.field_110324_m);
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return stack.func_77960_j() == 1 ? ModAchievements.tiaraWings : null;
    }
}

