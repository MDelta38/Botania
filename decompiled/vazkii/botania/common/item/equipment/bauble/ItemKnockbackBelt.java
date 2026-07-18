/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.common.item.equipment.bauble.ItemBaubleModifier;

public class ItemKnockbackBelt
extends ItemBaubleModifier
implements IBaubleRender {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/knockbackBelt.png");
    private static ModelBiped model;

    public ItemKnockbackBelt() {
        super("knockbackBelt");
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    @Override
    void fillModifiers(Multimap<String, AttributeModifier> attributes, ItemStack stack) {
        attributes.put((Object)SharedMonsterAttributes.field_111266_c.func_111108_a(), (Object)new AttributeModifier(ItemKnockbackBelt.getBaubleUUID(stack), "Bauble modifier", 1.0, 0));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.BODY) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            GL11.glTranslatef((float)0.0f, (float)0.2f, (float)0.0f);
            float s = 0.065625f;
            GL11.glScalef((float)s, (float)s, (float)s);
            if (model == null) {
                model = new ModelBiped();
            }
            ItemKnockbackBelt.model.field_78115_e.func_78785_a(1.0f);
        }
    }
}

