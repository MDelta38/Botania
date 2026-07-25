/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IEssentiaContainerItem
 *  thaumcraft.common.Thaumcraft
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.RGB;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.EffectSyncPacketC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;

@Effect(aspect="fabrico")
public class Fabrico
extends AspectEffect {
    public Aspect aspect;

    @Override
    public int getCost() {
        return 4;
    }

    @OverrideBlock
    public int func_149720_d(IBlockAccess access, int x, int y, int z) {
        return this.aspect != null ? this.aspect.getColor() : access.func_147439_a(x, y, z).func_149635_D();
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        ItemStack phial = player.func_71045_bC();
        if (world.field_72995_K) {
            AspectList aspects;
            if (phial != null && phial.func_77973_b() instanceof IEssentiaContainerItem && (aspects = ((IEssentiaContainerItem)phial.func_77973_b()).getAspects(phial)) != null && aspects.getAspects()[0] != this.aspect) {
                world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f, false);
                RGB rgb = new RGB(((IEssentiaContainerItem)phial.func_77973_b()).getAspects(phial).getAspects()[0].getColor());
                for (int i = 0; i < 5; ++i) {
                    Thaumcraft.proxy.crucibleBubble(world, (float)x, (float)y, (float)z, rgb.getR(), rgb.getG(), rgb.getB());
                }
            }
            return;
        }
        if (phial != null && phial.func_77973_b() instanceof IEssentiaContainerItem) {
            AspectList aspects = ((IEssentiaContainerItem)phial.func_77973_b()).getAspects(phial);
            this.aspect = aspects != null ? aspects.getAspects()[0] : null;
            ChannelHandler.instance().sendToAll(new EffectSyncPacketC(this, true));
        }
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        super.readNBT(tagCompound);
        this.aspect = tagCompound.func_74764_b("aspect") ? Aspect.getAspect((String)tagCompound.func_74779_i("aspect")) : null;
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        super.writeNBT(tagCompound);
        if (this.aspect != null) {
            tagCompound.func_74778_a("aspect", this.aspect.getTag());
        }
    }
}

