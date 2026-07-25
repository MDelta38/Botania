/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  thaumcraft.common.blocks.ItemJarFilled
 *  thaumcraft.common.blocks.ItemJarNode
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.lib.PacketGetCowData;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.blocks.ItemJarNode;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class EntityWizardCow
extends EntityCow
implements IEntityAdditionalSpawnData {
    AspectList aspects = new AspectList();
    public AspectList essentia = new AspectList();
    public int nodeMod;
    public int nodeType;
    public boolean hasNode;

    public EntityWizardCow(World p_i1683_1_) {
        super(p_i1683_1_);
        if (p_i1683_1_.field_72995_K) {
            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketGetCowData(this.func_145782_y()));
        }
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_) {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
        if (itemstack != null && this.hasNode && (itemstack.func_77973_b() == ConfigItems.itemJarFilled || itemstack.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockJar))) {
            ItemStack jarOut = new ItemStack(ConfigItems.itemJarFilled);
            jarOut.func_77964_b(p_70085_1_.field_71071_by.func_70448_g().func_77960_j());
            boolean found = false;
            for (Aspect asp : this.essentia.getAspects()) {
                if (this.essentia.getAmount(asp) > 0) {
                    if (itemstack.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockJar)) {
                        ((ItemJarFilled)jarOut.func_77973_b()).setAspects(jarOut, new AspectList().add(asp, this.essentia.getAmount(asp)));
                        this.essentia.remove(asp);
                        found = true;
                    } else {
                        int amount = ((ItemJarFilled)jarOut.func_77973_b()).getAspects(itemstack).getAmount(asp);
                        if (amount > 0 && amount < 64) {
                            found = true;
                            if (amount + this.essentia.getAmount(asp) <= 64) {
                                ((ItemJarFilled)jarOut.func_77973_b()).setAspects(jarOut, new AspectList().add(asp, amount + this.essentia.getAmount(asp)));
                                this.essentia.remove(asp);
                            } else {
                                this.essentia.remove(asp, 64 - amount);
                                ((ItemJarFilled)jarOut.func_77973_b()).setAspects(jarOut, new AspectList().add(asp, 64));
                            }
                        }
                    }
                }
                if (!found) continue;
                if (itemstack.field_77994_a-- == 1) {
                    p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, jarOut);
                } else if (!p_70085_1_.field_71071_by.func_70441_a(jarOut)) {
                    p_70085_1_.func_71019_a(jarOut, false);
                }
                p_70085_1_.field_71071_by.func_70296_d();
                return true;
            }
            return false;
        }
        if (itemstack != null && !this.hasNode && itemstack.func_77973_b() == ConfigItems.itemJarNode) {
            this.aspects = ((ItemJarNode)itemstack.func_77973_b()).getAspects(itemstack);
            this.hasNode = true;
            NodeModifier mod = ((ItemJarNode)itemstack.func_77973_b()).getNodeModifier(itemstack);
            switch (mod) {
                case BRIGHT: {
                    this.nodeMod = 1;
                    break;
                }
                case PALE: {
                    this.nodeMod = -1;
                    break;
                }
                case FADING: {
                    this.nodeMod = -2;
                }
            }
            NodeType type = ((ItemJarNode)itemstack.func_77973_b()).getNodeType(itemstack);
            switch (type) {
                case NORMAL: {
                    this.nodeType = 1;
                    break;
                }
                case UNSTABLE: {
                    this.nodeType = 2;
                    break;
                }
                case DARK: {
                    this.nodeType = 3;
                    break;
                }
                case TAINTED: {
                    this.nodeType = 4;
                    break;
                }
                case HUNGRY: {
                    this.nodeType = 5;
                    break;
                }
                case PURE: {
                    this.nodeType = 6;
                }
            }
            p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, new ItemStack(ConfigBlocks.blockJar));
            p_70085_1_.field_71071_by.func_70296_d();
            return true;
        }
        return super.func_70085_c(p_70085_1_);
    }

    public NodeType getNodeType() {
        switch (this.nodeType) {
            case 2: {
                return NodeType.UNSTABLE;
            }
            case 3: {
                return NodeType.DARK;
            }
            case 4: {
                return NodeType.TAINTED;
            }
            case 5: {
                return NodeType.HUNGRY;
            }
            case 6: {
                return NodeType.PURE;
            }
        }
        return NodeType.NORMAL;
    }

    public NodeModifier getNodeMod() {
        switch (this.nodeMod) {
            case 1: {
                return NodeModifier.BRIGHT;
            }
            case -1: {
                return NodeModifier.PALE;
            }
            case -2: {
                return NodeModifier.FADING;
            }
        }
        return null;
    }

    public void func_70629_bd() {
        super.func_70629_bd();
        if (this.hasNode) {
            for (Aspect asp : this.aspects.getAspects()) {
                int divisor = this.aspects.getAmount(asp) * (3 + this.nodeMod);
                if (this.essentia.getAmount(asp) >= 64 || divisor <= 0 || this.field_70173_aa % (150000 / divisor) != 0) continue;
                this.essentia.add(asp, 1);
            }
        }
    }

    public AspectList getEssentia() {
        return this.essentia;
    }

    public AspectList getAspects() {
        return this.aspects;
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("hasNode", this.hasNode);
        p_70014_1_.func_74768_a("nodeMod", this.nodeMod);
        p_70014_1_.func_74768_a("nodeType", this.nodeType);
        NBTTagCompound aspectTag = new NBTTagCompound();
        this.aspects.writeToNBT(aspectTag);
        p_70014_1_.func_74782_a("aspects", (NBTBase)aspectTag);
        NBTTagCompound essentiaTag = new NBTTagCompound();
        this.essentia.writeToNBT(essentiaTag);
        p_70014_1_.func_74782_a("essentia", (NBTBase)essentiaTag);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        this.hasNode = p_70037_1_.func_74767_n("hasNode");
        this.nodeMod = p_70037_1_.func_74762_e("nodeMod");
        this.nodeType = p_70037_1_.func_74762_e("nodeType");
        this.aspects.readFromNBT(p_70037_1_.func_74775_l("aspects"));
        this.essentia.readFromNBT(p_70037_1_.func_74775_l("essentia"));
    }

    public void writeSpawnData(ByteBuf buffer) {
        if (this.aspects.size() > 0 && this.aspects.getAspects()[0] != null) {
            buffer.writeBoolean(true);
            buffer.writeInt(this.aspects.size());
            for (Aspect asp : this.aspects.getAspects()) {
                String tag = asp.getTag();
                buffer.writeInt(tag.length());
                buffer.writeBytes(tag.getBytes());
                buffer.writeInt(this.aspects.getAmount(asp));
            }
        } else {
            buffer.writeBoolean(false);
        }
    }

    public void readSpawnData(ByteBuf buffer) {
        if (buffer.readBoolean()) {
            int numAspects = buffer.readInt();
            for (int i = 0; i < numAspects; ++i) {
                int length = buffer.readInt();
                byte[] bytes = new byte[length];
                char[] chars = new char[length];
                buffer.readBytes(bytes);
                int j = 0;
                while (i < bytes.length) {
                    chars[j] = (char)bytes[j];
                    ++i;
                }
                String tag = String.copyValueOf(chars);
                this.aspects.add(Aspect.getAspect(tag), buffer.readInt());
            }
        }
    }
}

