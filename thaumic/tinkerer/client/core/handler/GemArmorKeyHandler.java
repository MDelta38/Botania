/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.InputEvent$KeyInputEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 */
package thaumic.tinkerer.client.core.handler;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import thaumic.tinkerer.client.core.handler.kami.KamiArmorClientHandler;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.armor.ItemIchorclothArmorAdv;

@SideOnly(value=Side.CLIENT)
public class GemArmorKeyHandler {
    static KeyBinding SpecialAbility = new KeyBinding("ttmisc.toggleArmor", 22, "ttmisc.keyCategory");

    public GemArmorKeyHandler() {
        FMLCommonHandler.instance().bus().register((Object)this);
        ClientRegistry.registerKeyBinding((KeyBinding)SpecialAbility);
    }

    @SubscribeEvent
    public void keyUp(InputEvent.KeyInputEvent event) {
        if (SpecialAbility.func_151468_f() && (Minecraft.func_71410_x().field_71439_g.func_82169_q(0) != null && Minecraft.func_71410_x().field_71439_g.func_82169_q(0).func_77973_b() instanceof ItemIchorclothArmorAdv || Minecraft.func_71410_x().field_71439_g.func_82169_q(1) != null && Minecraft.func_71410_x().field_71439_g.func_82169_q(1).func_77973_b() instanceof ItemIchorclothArmorAdv || Minecraft.func_71410_x().field_71439_g.func_82169_q(2) != null && Minecraft.func_71410_x().field_71439_g.func_82169_q(2).func_77973_b() instanceof ItemIchorclothArmorAdv || Minecraft.func_71410_x().field_71439_g.func_82169_q(3) != null && Minecraft.func_71410_x().field_71439_g.func_82169_q(3).func_77973_b() instanceof ItemIchorclothArmorAdv)) {
            KamiArmorClientHandler.SetStatus(!ThaumicTinkerer.proxy.armorStatus(ThaumicTinkerer.proxy.getClientPlayer()));
        }
    }
}

