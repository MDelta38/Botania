/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewContainer;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemBloodPendant
extends ItemBauble
implements IBrewContainer,
IBrewItem,
IManaUsingItem,
IBaubleRender {
    private static final String TAG_BREW_KEY = "brewKey";
    IIcon[] icons;

    public ItemBloodPendant() {
        super("bloodPendant");
        this.func_77625_d(1);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        super.func_150895_a(item, tab, list);
        for (String s : BotaniaAPI.brewMap.keySet()) {
            ItemStack brewStack = this.getItemForBrew(BotaniaAPI.brewMap.get(s), new ItemStack((Item)this));
            if (brewStack == null) continue;
            list.add(brewStack);
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        for (int i = 0; i < 4; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icons[pass];
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            return 0xFFFFFF;
        }
        Brew brew = this.getBrew(stack);
        if (brew == BotaniaAPI.fallbackBrew) {
            return 12976142;
        }
        Color color = new Color(brew.getColor(stack));
        int add = (int)(Math.sin((double)ClientTickHandler.ticksInGame * 0.2) * 24.0);
        int r = Math.max(0, Math.min(255, color.getRed() + add));
        int g = Math.max(0, Math.min(255, color.getGreen() + add));
        int b = Math.max(0, Math.min(255, color.getBlue() + add));
        return r << 16 | g << 8 | b;
    }

    @Override
    public void addHiddenTooltip(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        super.addHiddenTooltip(stack, player, list, adv);
        Brew brew = this.getBrew(stack);
        if (brew == BotaniaAPI.fallbackBrew) {
            this.addStringToTooltip(EnumChatFormatting.LIGHT_PURPLE + StatCollector.func_74838_a((String)"botaniamisc.notInfused"), list);
            return;
        }
        this.addStringToTooltip(EnumChatFormatting.LIGHT_PURPLE + String.format(StatCollector.func_74838_a((String)"botaniamisc.brewOf"), StatCollector.func_74838_a((String)brew.getUnlocalizedName(stack))), list);
        for (PotionEffect effect : brew.getPotionEffects(stack)) {
            Potion potion = Potion.field_76425_a[effect.func_76456_a()];
            EnumChatFormatting format = potion.func_76398_f() ? EnumChatFormatting.RED : EnumChatFormatting.GRAY;
            this.addStringToTooltip(" " + format + StatCollector.func_74838_a((String)effect.func_76453_d()) + (effect.func_76458_c() == 0 ? "" : " " + StatCollector.func_74838_a((String)("botania.roman" + (effect.func_76458_c() + 1)))), list);
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        Brew brew = this.getBrew(stack);
        if (brew != BotaniaAPI.fallbackBrew && player instanceof EntityPlayer && !player.field_70170_p.field_72995_K) {
            boolean doRand;
            EntityPlayer eplayer = (EntityPlayer)player;
            PotionEffect effect = brew.getPotionEffects(stack).get(0);
            float cost = (float)brew.getManaCost(stack) / (float)effect.func_76459_b() / (float)(1 + effect.func_76458_c()) * 2.5f;
            boolean bl = doRand = cost < 1.0f;
            if (ManaItemHandler.requestManaExact(stack, eplayer, (int)Math.ceil(cost), false)) {
                boolean nightVision;
                PotionEffect currentEffect = player.func_70660_b(Potion.field_76425_a[effect.func_76456_a()]);
                boolean bl2 = nightVision = effect.func_76456_a() == Potion.field_76439_r.field_76415_H;
                if (currentEffect == null || currentEffect.func_76459_b() < (nightVision ? 205 : 3)) {
                    PotionEffect applyEffect = new PotionEffect(effect.func_76456_a(), nightVision ? 285 : 80, effect.func_76458_c(), true);
                    player.func_70690_d(applyEffect);
                }
                if (!doRand || Math.random() < (double)cost) {
                    ManaItemHandler.requestManaExact(stack, eplayer, (int)Math.ceil(cost), true);
                }
            }
        }
    }

    @Override
    public Brew getBrew(ItemStack stack) {
        String key = ItemNBTHelper.getString(stack, TAG_BREW_KEY, "");
        return BotaniaAPI.getBrewFromKey(key);
    }

    public static void setBrew(ItemStack stack, Brew brew) {
        ItemBloodPendant.setBrew(stack, brew.getKey());
    }

    public static void setBrew(ItemStack stack, String brew) {
        ItemNBTHelper.setString(stack, TAG_BREW_KEY, brew);
    }

    @Override
    public ItemStack getItemForBrew(Brew brew, ItemStack stack) {
        if (!brew.canInfuseBloodPendant() || brew.getPotionEffects(stack).size() != 1 || Potion.field_76425_a[brew.getPotionEffects(stack).get(0).func_76456_a()].func_76403_b()) {
            return null;
        }
        ItemStack brewStack = new ItemStack((Item)this);
        ItemBloodPendant.setBrew(brewStack, brew);
        return brewStack;
    }

    @Override
    public int getManaCost(Brew brew, ItemStack stack) {
        return brew.getManaCost() * 10;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return this.getBrew(stack) != BotaniaAPI.fallbackBrew;
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.BODY) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            boolean armor = event.entityPlayer.func_82169_q(2) != null;
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.26f, (float)-0.4f, (float)(armor ? 0.2f : 0.15f));
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            for (int i = 2; i < 4; ++i) {
                IIcon icon = this.icons[i];
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.03125f);
                Color color = new Color(this.func_82790_a(stack, 1));
                GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                int light = 0xF000F0;
                int lightmapX = light % 65536;
                int lightmapY = light / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
            }
        }
    }
}

