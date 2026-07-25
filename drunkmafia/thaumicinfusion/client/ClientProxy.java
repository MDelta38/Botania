/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  thaumcraft.api.ItemApi
 */
package drunkmafia.thaumicinfusion.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import drunkmafia.thaumicinfusion.client.event.ClientEventContainer;
import drunkmafia.thaumicinfusion.client.gui.InfusionGui;
import drunkmafia.thaumicinfusion.client.renderer.InfusedBlockFallingRenderer;
import drunkmafia.thaumicinfusion.client.renderer.item.EssentiaBlockRenderer;
import drunkmafia.thaumicinfusion.common.CommonProxy;
import drunkmafia.thaumicinfusion.common.aspect.entity.InfusedBlockFalling;
import drunkmafia.thaumicinfusion.common.block.TIBlocks;
import drunkmafia.thaumicinfusion.common.item.ItemFocusInfusing;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.ItemApi;

public class ClientProxy
extends CommonProxy {
    @Override
    public void initRenderers() {
        CommonProxy.isClient = true;
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)TIBlocks.essentiaBlock), (IItemRenderer)new EssentiaBlockRenderer());
        RenderingRegistry.registerEntityRenderingHandler(InfusedBlockFalling.class, (Render)new InfusedBlockFallingRenderer());
        MinecraftForge.EVENT_BUS.register((Object)new ClientEventContainer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0 && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b().getClass().isAssignableFrom(ItemApi.getItem((String)"itemWandCasting", (int)0).func_77973_b().getClass()) && ClientEventContainer.getFocus(player.func_71045_bC()) != null && ClientEventContainer.getFocus(player.func_71045_bC()) instanceof ItemFocusInfusing) {
            return new InfusionGui(player, player.func_71045_bC());
        }
        return null;
    }
}

