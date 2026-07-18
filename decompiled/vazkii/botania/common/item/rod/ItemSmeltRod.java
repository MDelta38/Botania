/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemSmeltRod
extends ItemMod
implements IManaUsingItem {
    private static final int TIME = 10;
    private static final int COST = 300;
    private static final int COST_PER_TICK = 30;
    public static Map<EntityPlayer, SmeltData> playerData = new WeakHashMap<EntityPlayer, SmeltData>();

    public ItemSmeltRod() {
        this.func_77655_b("smeltRod");
        this.func_77625_d(1);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer p, int time) {
        if (!ManaItemHandler.requestManaExactForTool(stack, p, 30, false)) {
            return;
        }
        MovingObjectPosition pos = ToolCommons.raytraceFromEntity(p.field_70170_p, (Entity)p, false, 32.0);
        if (pos != null) {
            Block block = p.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            int meta = p.field_70170_p.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            ItemStack blockStack = new ItemStack(block, 1, meta);
            ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(blockStack);
            if (result != null && result.func_77973_b() instanceof ItemBlock) {
                SmeltData data;
                boolean decremented = false;
                if (playerData.containsKey(p) && (data = playerData.get(p)).equalPos(pos)) {
                    --data.progress;
                    decremented = true;
                    if (data.progress <= 0) {
                        if (!p.field_70170_p.field_72995_K) {
                            p.field_70170_p.func_147465_d(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d, Block.func_149634_a((Item)result.func_77973_b()), result.func_77960_j(), 3);
                            p.field_70170_p.func_72956_a((Entity)p, "fire.ignite", 0.6f, 1.0f);
                            p.field_70170_p.func_72956_a((Entity)p, "fire.fire", 1.0f, 1.0f);
                            ManaItemHandler.requestManaExactForTool(stack, p, 30, true);
                            playerData.remove(p.func_146103_bH().getName());
                            decremented = false;
                        }
                        for (int i = 0; i < 25; ++i) {
                            double x = (double)pos.field_72311_b + Math.random();
                            double y = (double)pos.field_72312_c + Math.random();
                            double z = (double)pos.field_72309_d + Math.random();
                            Botania.proxy.wispFX(p.field_70170_p, x, y, z, 1.0f, 0.2f, 0.2f, 0.5f, (float)(-Math.random()) / 10.0f);
                        }
                    }
                }
                if (!decremented) {
                    playerData.put(p, new SmeltData(pos, IManaProficiencyArmor.Helper.hasProficiency(p) ? 6 : 10));
                } else {
                    for (int i = 0; i < 2; ++i) {
                        double x = (double)pos.field_72311_b + Math.random();
                        double y = (double)pos.field_72312_c + Math.random();
                        double z = (double)pos.field_72309_d + Math.random();
                        Botania.proxy.wispFX(p.field_70170_p, x, y, z, 1.0f, 0.2f, 0.2f, 0.5f, (float)(-Math.random()) / 10.0f);
                    }
                    if (time % 10 == 0) {
                        p.field_70170_p.func_72956_a((Entity)p, "fire.fire", (float)Math.random() / 2.0f + 0.5f, 1.0f);
                    }
                }
            }
        }
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    static class SmeltData {
        public MovingObjectPosition pos;
        public int progress;

        public SmeltData(MovingObjectPosition pos, int progress) {
            this.pos = pos;
            this.progress = progress;
        }

        public boolean equalPos(MovingObjectPosition pos) {
            return pos.field_72311_b == this.pos.field_72311_b && pos.field_72312_c == this.pos.field_72312_c && pos.field_72309_d == this.pos.field_72309_d;
        }
    }
}

