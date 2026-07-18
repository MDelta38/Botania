/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.block;

import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.block.ItemBlockMod;

public class ItemBlockTinyPotato
extends ItemBlockMod {
    private static final List<String> TYPOS = Arrays.asList("vaskii", "vazki", "voskii", "vazkkii", "vazkki", "vazzki", "vaskki", "vozkii", "vazkil", "vaskil", "vazkill", "vaskill", "vaski");
    private static final String[] NOT_MY_NAME = new String[]{"Six letter word just to get me along", "It's a intricacy and I'm coding on my mod and I,", "I keep fixin', and keepin' it together", "People around gotta find something to play now", "Holding back, every mod's the same", "Don't wanna be a loser", "Listen to me, oh no, I don't break anything at all", "But with nothing to consider they forget my name", "'ame, 'ame, 'ame", "They call me Vaskii", "They call me Vazki", "They call me Voskii", "They call me Vazkki", "That's not my name", "That's not my name", "That's not my name", "That's not my name"};
    private static final String TAG_TICKS = "notMyNameTicks";

    public ItemBlockTinyPotato(Block block) {
        super(block);
    }

    public void func_77663_a(ItemStack stack, World world, Entity e, int t, boolean idunno) {
        if (!world.field_72995_K && e instanceof EntityPlayer && e.field_70173_aa % 30 == 0 && TYPOS.contains(stack.func_82833_r().toLowerCase())) {
            EntityPlayer player = (EntityPlayer)e;
            int ticks = ItemNBTHelper.getInt(stack, TAG_TICKS, 0);
            if (ticks < NOT_MY_NAME.length) {
                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + NOT_MY_NAME[ticks]));
                ItemNBTHelper.setInt(stack, TAG_TICKS, ticks + 1);
            }
        }
    }
}

