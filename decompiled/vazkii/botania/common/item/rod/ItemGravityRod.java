/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityThrownItem;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ModItems;

public class ItemGravityRod
extends ItemMod
implements IManaUsingItem {
    private static final float RANGE = 3.0f;
    private static final int COST = 2;
    private static final String TAG_TICKS_TILL_EXPIRE = "ticksTillExpire";
    private static final String TAG_TICKS_COOLDOWN = "ticksCooldown";
    private static final String TAG_TARGET = "target";
    private static final String TAG_DIST = "dist";

    public ItemGravityRod() {
        this.func_77625_d(1);
        this.func_77655_b("gravityRod");
    }

    public void func_77663_a(ItemStack stack, World world, Entity par3Entity, int p_77663_4_, boolean p_77663_5_) {
        float check;
        if (!(par3Entity instanceof EntityPlayer)) {
            return;
        }
        int ticksTillExpire = ItemNBTHelper.getInt(stack, TAG_TICKS_TILL_EXPIRE, 0);
        int ticksCooldown = ItemNBTHelper.getInt(stack, TAG_TICKS_COOLDOWN, 0);
        if (ticksTillExpire == 0) {
            ItemNBTHelper.setInt(stack, TAG_TARGET, -1);
            ItemNBTHelper.setDouble(stack, TAG_DIST, -1.0);
        }
        if (ticksCooldown > 0) {
            --ticksCooldown;
        }
        ItemNBTHelper.setInt(stack, TAG_TICKS_TILL_EXPIRE, --ticksTillExpire);
        ItemNBTHelper.setInt(stack, TAG_TICKS_COOLDOWN, ticksCooldown);
        EntityPlayer player = (EntityPlayer)par3Entity;
        PotionEffect haste = player.func_70660_b(Potion.field_76422_e);
        float f = haste == null ? 0.16666667f : (check = haste.func_76458_c() == 1 ? 0.5f : 0.4f);
        if (player.func_71045_bC() == stack && player.field_70733_aJ == check && !world.field_72995_K) {
            ItemGravityRod.leftClick(player);
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        int targetID = ItemNBTHelper.getInt(stack, TAG_TARGET, -1);
        int ticksCooldown = ItemNBTHelper.getInt(stack, TAG_TICKS_COOLDOWN, 0);
        double length = ItemNBTHelper.getDouble(stack, TAG_DIST, -1.0);
        if (ticksCooldown == 0) {
            Object item = null;
            if (targetID != -1 && player.field_70170_p.func_73045_a(targetID) != null) {
                Entity taritem = player.field_70170_p.func_73045_a(targetID);
                boolean found = false;
                Vector3 target = Vector3.fromEntityCenter((Entity)player);
                List entities = new ArrayList();
                for (int distance = 1; entities.size() == 0 && distance < 25; ++distance) {
                    target.add(new Vector3(player.func_70040_Z()).multiply(distance));
                    target.y += 0.5;
                    entities = player.field_70170_p.func_72839_b((Entity)player, AxisAlignedBB.func_72330_a((double)(target.x - 3.0), (double)(target.y - 3.0), (double)(target.z - 3.0), (double)(target.x + 3.0), (double)(target.y + 3.0), (double)(target.z + 3.0)));
                    if (!entities.contains(taritem)) continue;
                    found = true;
                }
                if (found) {
                    item = player.field_70170_p.func_73045_a(targetID);
                }
            }
            if (item == null) {
                Vector3 target = Vector3.fromEntityCenter((Entity)player);
                List entities = new ArrayList();
                for (int distance = 1; entities.size() == 0 && distance < 25; ++distance) {
                    target.add(new Vector3(player.func_70040_Z()).multiply(distance));
                    target.y += 0.5;
                    entities = player.field_70170_p.func_72839_b((Entity)player, AxisAlignedBB.func_72330_a((double)(target.x - 3.0), (double)(target.y - 3.0), (double)(target.z - 3.0), (double)(target.x + 3.0), (double)(target.y + 3.0), (double)(target.z + 3.0)));
                }
                if (entities.size() > 0) {
                    item = (Entity)entities.get(0);
                    length = 5.5;
                    if (item instanceof EntityItem) {
                        length = 2.0;
                    }
                }
            }
            if (item != null) {
                if (BotaniaAPI.isEntityBlacklistedFromGravityRod(item.getClass())) {
                    return stack;
                }
                if (ManaItemHandler.requestManaExactForTool(stack, player, 2, true)) {
                    if (item instanceof EntityItem) {
                        ((EntityItem)item).field_145804_b = 5;
                    }
                    if (item instanceof EntityLivingBase) {
                        EntityLivingBase targetEntity = (EntityLivingBase)item;
                        targetEntity.field_70143_R = 0.0f;
                        if (targetEntity.func_70660_b(Potion.field_76421_d) == null) {
                            targetEntity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 2, 3, true));
                        }
                    }
                    Vector3 target3 = Vector3.fromEntityCenter((Entity)player);
                    target3.add(new Vector3(player.func_70040_Z()).multiply(length));
                    target3.y += 0.5;
                    if (item instanceof EntityItem) {
                        target3.y += 0.25;
                    }
                    for (int i = 0; i < 4; ++i) {
                        float r = 0.5f + (float)Math.random() * 0.5f;
                        float b = 0.5f + (float)Math.random() * 0.5f;
                        float s = 0.2f + (float)Math.random() * 0.1f;
                        float m = 0.1f;
                        float xm = ((float)Math.random() - 0.5f) * m;
                        float ym = ((float)Math.random() - 0.5f) * m;
                        float zm = ((float)Math.random() - 0.5f) * m;
                        Botania.proxy.wispFX(world, ((Entity)item).field_70165_t + (double)(((Entity)item).field_70130_N / 2.0f), ((Entity)item).field_70163_u + (double)(((Entity)item).field_70131_O / 2.0f), ((Entity)item).field_70161_v + (double)(((Entity)item).field_70130_N / 2.0f), r, 0.0f, b, s, xm, ym, zm);
                    }
                    ItemGravityRod.setEntityMotionFromVector((Entity)item, target3, 0.3333333f);
                    ItemNBTHelper.setInt(stack, TAG_TARGET, item.func_145782_y());
                    ItemNBTHelper.setDouble(stack, TAG_DIST, length);
                }
            }
            if (item != null) {
                ItemNBTHelper.setInt(stack, TAG_TICKS_TILL_EXPIRE, 5);
            }
        }
        return stack;
    }

    public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
        Vector3 entityVector = Vector3.fromEntityCenter(entity);
        Vector3 finalVector = originalPosVector.copy().subtract(entityVector);
        if (finalVector.mag() > 1.0) {
            finalVector.normalize();
        }
        entity.field_70159_w = finalVector.x * (double)modifier;
        entity.field_70181_x = finalVector.y * (double)modifier;
        entity.field_70179_y = finalVector.z * (double)modifier;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    public static void leftClick(EntityPlayer player) {
        ItemStack stack = player.func_70694_bm();
        if (stack != null && stack.func_77973_b() == ModItems.gravityRod) {
            int targetID = ItemNBTHelper.getInt(stack, TAG_TARGET, -1);
            ItemNBTHelper.getDouble(stack, TAG_DIST, -1.0);
            Entity item = null;
            if (targetID != -1 && player.field_70170_p.func_73045_a(targetID) != null) {
                Entity taritem = player.field_70170_p.func_73045_a(targetID);
                boolean found = false;
                Vector3 target = Vector3.fromEntityCenter((Entity)player);
                List entities = new ArrayList();
                for (int distance = 1; entities.size() == 0 && distance < 25; ++distance) {
                    target.add(new Vector3(player.func_70040_Z()).multiply(distance));
                    target.y += 0.5;
                    entities = player.field_70170_p.func_72839_b((Entity)player, AxisAlignedBB.func_72330_a((double)(target.x - 3.0), (double)(target.y - 3.0), (double)(target.z - 3.0), (double)(target.x + 3.0), (double)(target.y + 3.0), (double)(target.z + 3.0)));
                    if (!entities.contains(taritem)) continue;
                    found = true;
                }
                if (found) {
                    item = player.field_70170_p.func_73045_a(targetID);
                    ItemNBTHelper.setInt(stack, TAG_TARGET, -1);
                    ItemNBTHelper.setDouble(stack, TAG_DIST, -1.0);
                    Vector3 moveVector = new Vector3(player.func_70040_Z().func_72432_b());
                    if (item instanceof EntityItem) {
                        ((EntityItem)item).field_145804_b = 20;
                        float mot = IManaProficiencyArmor.Helper.hasProficiency(player) ? 2.25f : 1.5f;
                        item.field_70159_w = moveVector.x * (double)mot;
                        item.field_70181_x = moveVector.y;
                        item.field_70179_y = moveVector.z * (double)mot;
                        if (!player.field_70170_p.field_72995_K) {
                            EntityThrownItem thrown = new EntityThrownItem(item.field_70170_p, item.field_70165_t, item.field_70163_u, item.field_70161_v, (EntityItem)item);
                            item.field_70170_p.func_72838_d((Entity)thrown);
                        }
                        item.func_70106_y();
                    } else {
                        item.field_70159_w = moveVector.x * 3.0;
                        item.field_70181_x = moveVector.y * 1.5;
                        item.field_70179_y = moveVector.z * 3.0;
                    }
                    ItemNBTHelper.setInt(stack, TAG_TICKS_COOLDOWN, 10);
                }
            }
        }
    }
}

