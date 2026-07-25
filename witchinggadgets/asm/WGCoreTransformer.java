/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  org.apache.logging.log4j.Level
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 *  thaumcraft.common.config.Config
 */
package witchinggadgets.asm;

import baubles.api.BaublesApi;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.Level;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import thaumcraft.common.config.Config;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.armor.ItemPrimordialArmor;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;

public class WGCoreTransformer
implements IClassTransformer {
    static boolean isDeobfEnvironment;
    static Field f_potionAmplifier;
    static Field f_potionDuration;

    public byte[] transform(String className, String newClassName, byte[] origCode) {
        isDeobfEnvironment = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
        if (className.equals("thaumcraft.common.items.armor.ItemBootsTraveller")) {
            return this.patchBoots(className, origCode, isDeobfEnvironment);
        }
        if (className.equals("thaumcraft.common.items.wands.ItemFocusPouchBauble")) {
            byte[] newCode = this.patchFocusPouch_Interface(className, origCode);
            return this.patchFocusPouch_Methods(className, newCode, isDeobfEnvironment);
        }
        if (className.equals("thaumcraft.common.lib.world.WorldGenEldritchRing")) {
            return this.patchThaumcraftWorldgen(origCode, isDeobfEnvironment, "EldritchRing");
        }
        if (className.equals("thaumcraft.common.lib.world.WorldGenHilltopStones")) {
            return this.patchThaumcraftWorldgen(origCode, isDeobfEnvironment, "HilltopStones");
        }
        if (className.equals(isDeobfEnvironment ? "net.minecraft.enchantment.EnchantmentHelper" : "afv")) {
            byte[] newCode = this.patchGetFortuneModifier(origCode, isDeobfEnvironment);
            return newCode;
        }
        if (className.equals(isDeobfEnvironment ? "net.minecraft.entity.EntityLivingBase" : "sv")) {
            byte[] newCode = this.patchOnNewPotionEffect(origCode, isDeobfEnvironment);
            return newCode;
        }
        return origCode;
    }

    private byte[] patchBoots(String className, byte[] origCode, boolean deobf) {
        WitchingGadgets.logger.log(Level.INFO, "[CORE] Patching Boots");
        String methodToPatch = "getIsRepairable";
        String methodToPatch_obf = "func_82789_a";
        String name = deobf ? "getIsRepairable" : "func_82789_a";
        String desc = deobf ? "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z" : "(Ladd;Ladd;)Z";
        ClassReader cr = new ClassReader(origCode);
        ClassWriter cw = new ClassWriter(cr, 0);
        MethodVisitor mv = cw.visitMethod(1, name, desc, null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 1);
        mv.visitVarInsn(25, 2);
        mv.visitMethodInsn(184, "witchinggadgets/asm/WGCoreTransformer", "boots_getIsRepairable", desc, false);
        mv.visitInsn(172);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        cr.accept((ClassVisitor)cw, 0);
        return cw.toByteArray();
    }

    public static boolean boots_getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return WGConfig.coremod_allowBootsRepair && stack2.func_77969_a(new ItemStack(Items.field_151116_aA));
    }

    private byte[] patchFocusPouch_Interface(String className, byte[] origCode) {
        WitchingGadgets.logger.log(Level.INFO, "[CORE] Patching Pouch - Interfaces");
        ClassReader cr = new ClassReader(origCode);
        ClassWriter cw = new ClassWriter(cr, 0);
        ClassVisitor cv = new ClassVisitor(262144, (ClassVisitor)cw){

            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                HashSet<String> intf = new HashSet<String>();
                intf.addAll(Arrays.asList(interfaces));
                intf.add("travellersgear/api/IActiveAbility");
                super.visit(version, access, name, signature, superName, intf.toArray(new String[0]));
            }
        };
        cr.accept(cv, 0);
        return cw.toByteArray();
    }

    private byte[] patchFocusPouch_Methods(String className, byte[] origCode, boolean deobf) {
        WitchingGadgets.logger.log(Level.INFO, "[CORE] Patching Pouch - Methods");
        String methodToPatch1 = "canActivate";
        ClassReader cr = new ClassReader(origCode);
        ClassWriter cw = new ClassWriter(cr, 0);
        String desc1 = deobf ? "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;Z)Z" : "(Lyz;Ladd;Z)Z";
        MethodVisitor mv = cw.visitMethod(1, "canActivate", desc1, null, null);
        mv.visitCode();
        mv.visitFieldInsn(178, "witchinggadgets/common/WGConfig", "coremod_allowFocusPouchActive", "Z");
        mv.visitInsn(172);
        mv.visitMaxs(3, 1);
        mv.visitEnd();
        String methodToPatch2 = "activate";
        String desc2 = deobf ? "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)V" : "(Lyz;Ladd;)V";
        mv = cw.visitMethod(1, "activate", desc2, null, null);
        mv.visitCode();
        mv.visitVarInsn(25, 1);
        mv.visitVarInsn(25, 2);
        mv.visitMethodInsn(184, "witchinggadgets/asm/WGCoreTransformer", "pouch_activate", desc2, false);
        mv.visitInsn(177);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        cr.accept((ClassVisitor)cw, 0);
        return cw.toByteArray();
    }

    public static void pouch_activate(EntityPlayer player, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K) {
            player.openGui((Object)WitchingGadgets.instance, 6, player.field_70170_p, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v));
        }
    }

    private byte[] patchGetFortuneModifier(byte[] origCode, boolean deobf) {
        WitchingGadgets.logger.log(Level.INFO, "[CORE] Patching getFortuneModifier & getLootingModifier");
        String methodToPatch1 = "getFortuneModifier";
        String methodToPatch_srg1 = "func_77517_e";
        String methodToPatch_obf1 = "f";
        String desc = "(Lnet/minecraft/entity/EntityLivingBase;)I";
        String desc_obf = "(Lsv;)I";
        String methodToPatch2 = "getLootingModifier";
        String methodToPatch_srg2 = "func_77519_f";
        String methodToPatch_obf2 = "i";
        ClassReader cr = new ClassReader(origCode);
        ClassNode classNode = new ClassNode();
        cr.accept((ClassVisitor)classNode, 0);
        for (MethodNode methodNode : classNode.methods) {
            InsnList endList;
            if ((methodNode.name.equals("getFortuneModifier") || methodNode.name.equals("func_77517_e") || methodNode.name.equals("f")) && (methodNode.desc.equals("(Lnet/minecraft/entity/EntityLivingBase;)I") || methodNode.desc.equals("(Lsv;)I"))) {
                for (AbstractInsnNode insn : methodNode.instructions) {
                    if (insn.getOpcode() != 172 && insn.getOpcode() != 177 && insn.getOpcode() != 176 && insn.getOpcode() != 173 && insn.getOpcode() != 175) continue;
                    endList = new InsnList();
                    endList.add((AbstractInsnNode)new VarInsnNode(25, 0));
                    endList.add((AbstractInsnNode)new MethodInsnNode(184, "witchinggadgets/asm/WGCoreTransformer", "enchantment_getFortuneLevel", "(Lnet/minecraft/entity/EntityLivingBase;)I", false));
                    methodNode.instructions.insertBefore(insn, endList);
                }
                continue;
            }
            if (!methodNode.name.equals("getLootingModifier") && !methodNode.name.equals("func_77519_f") && !methodNode.name.equals("i") || !methodNode.desc.equals("(Lnet/minecraft/entity/EntityLivingBase;)I") && !methodNode.desc.equals("(Lsv;)I")) continue;
            for (AbstractInsnNode insn : methodNode.instructions) {
                if (insn.getOpcode() != 172 && insn.getOpcode() != 177 && insn.getOpcode() != 176 && insn.getOpcode() != 173 && insn.getOpcode() != 175) continue;
                endList = new InsnList();
                endList.add((AbstractInsnNode)new VarInsnNode(25, 0));
                endList.add((AbstractInsnNode)new MethodInsnNode(184, "witchinggadgets/asm/WGCoreTransformer", "enchantment_getLootingLevel", "(Lnet/minecraft/entity/EntityLivingBase;)I", false));
                methodNode.instructions.insertBefore(insn, endList);
            }
        }
        ClassWriter cw = new ClassWriter(1);
        classNode.accept((ClassVisitor)cw);
        return cw.toByteArray();
    }

    public static int enchantment_getFortuneLevel(EntityLivingBase living) {
        int base = EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)living.func_70694_bm());
        if (WGConfig.coremod_allowEnchantModifications && living instanceof EntityPlayer) {
            for (int i = 0; i < BaublesApi.getBaubles((EntityPlayer)((EntityPlayer)living)).func_70302_i_(); ++i) {
                ItemStack bStack = BaublesApi.getBaubles((EntityPlayer)((EntityPlayer)living)).func_70301_a(i);
                if (bStack == null || !bStack.func_77973_b().equals(WGContent.ItemMagicalBaubles) || !ItemMagicalBaubles.subNames[bStack.func_77960_j()].equals("ringLuck")) continue;
                base += 2;
                break;
            }
        }
        return base;
    }

    public static int enchantment_getLootingLevel(EntityLivingBase living) {
        int base = EnchantmentHelper.func_77506_a((int)Enchantment.field_77335_o.field_77352_x, (ItemStack)living.func_70694_bm());
        if (WGConfig.coremod_allowEnchantModifications && living instanceof EntityPlayer) {
            for (int i = 0; i < BaublesApi.getBaubles((EntityPlayer)((EntityPlayer)living)).func_70302_i_(); ++i) {
                ItemStack bStack = BaublesApi.getBaubles((EntityPlayer)((EntityPlayer)living)).func_70301_a(i);
                if (bStack == null || !bStack.func_77973_b().equals(WGContent.ItemMagicalBaubles) || !ItemMagicalBaubles.subNames[bStack.func_77960_j()].equals("ringLuck")) continue;
                base += 2;
                break;
            }
        }
        return base;
    }

    private byte[] patchOnNewPotionEffect(byte[] origCode, boolean deobf) {
        WitchingGadgets.logger.log(Level.INFO, "[CORE] Patching onNewPotionEffect");
        String methodToPatch = "onNewPotionEffect";
        String methodToPatch_srg = "func_70670_a";
        String methodToPatch_obf = "a";
        String desc1 = "(Lnet/minecraft/potion/PotionEffect;)V";
        String desc1_obf = "(Lrw;)V";
        String desc2 = deobf ? "(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/potion/PotionEffect;)V" : "(Lsv;Lrw;)V";
        ClassReader cr = new ClassReader(origCode);
        ClassNode classNode = new ClassNode();
        cr.accept((ClassVisitor)classNode, 0);
        for (MethodNode methodNode : classNode.methods) {
            if (!methodNode.name.equals("onNewPotionEffect") && !methodNode.name.equals("func_70670_a") && !methodNode.name.equals("a") || !methodNode.desc.equals("(Lnet/minecraft/potion/PotionEffect;)V") && !methodNode.desc.equals("(Lrw;)V")) continue;
            for (AbstractInsnNode insn : methodNode.instructions) {
                if (insn.getOpcode() != 172 && insn.getOpcode() != 177 && insn.getOpcode() != 176 && insn.getOpcode() != 173 && insn.getOpcode() != 175) continue;
                InsnList endList = new InsnList();
                endList.add((AbstractInsnNode)new VarInsnNode(25, 0));
                endList.add((AbstractInsnNode)new VarInsnNode(25, 1));
                endList.add((AbstractInsnNode)new MethodInsnNode(184, "witchinggadgets/asm/WGCoreTransformer", "living_onPotionApplied", desc2, false));
                methodNode.instructions.insertBefore(insn, endList);
            }
        }
        ClassWriter cw = new ClassWriter(1);
        classNode.accept((ClassVisitor)cw);
        return cw.toByteArray();
    }

    public static void living_onPotionApplied(EntityLivingBase living, PotionEffect effect) {
        int id;
        if (WGConfig.coremod_allowPotionApplicationMod && effect != null && !living.field_70170_p.field_72995_K && ((id = effect.func_76456_a()) == Config.potionVisExhaustID || id == Config.potionThaumarhiaID || id == Config.potionUnHungerID || id == Config.potionBlurredID || id == Config.potionSunScornedID || id == Config.potionInfVisExhaustID || id == Config.potionDeathGazeID)) {
            int ordo = 0;
            for (int i = 1; i <= 4; ++i) {
                ItemStack armor = living.func_71124_b(i);
                if (armor == null || armor.func_77973_b() == null || !(armor.func_77973_b() instanceof ItemPrimordialArmor) || ((ItemPrimordialArmor)armor.func_77973_b()).getAbility(armor) != 4) continue;
                ++ordo;
            }
            try {
                int val_Amp;
                if (f_potionAmplifier == null) {
                    f_potionAmplifier = PotionEffect.class.getDeclaredField(isDeobfEnvironment ? "amplifier" : "field_76461_c");
                }
                if (!f_potionAmplifier.isAccessible()) {
                    f_potionAmplifier.setAccessible(true);
                }
                if (f_potionDuration == null) {
                    f_potionDuration = PotionEffect.class.getDeclaredField(isDeobfEnvironment ? "duration" : "field_76460_b");
                }
                if (!f_potionDuration.isAccessible()) {
                    f_potionDuration.setAccessible(true);
                }
                if ((val_Amp = f_potionAmplifier.getInt(effect)) > 0) {
                    val_Amp = Math.max(0, val_Amp - ordo);
                }
                f_potionAmplifier.setInt(effect, val_Amp);
                int val_Dur = f_potionDuration.getInt(effect);
                if (val_Dur > 0) {
                    val_Dur /= ordo + 1;
                }
                f_potionDuration.setInt(effect, val_Dur);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] patchThaumcraftWorldgen(byte[] origCode, boolean deobf, String ident) {
        WitchingGadgets.logger.log(Level.INFO, "[CORE] Patching Thaumcraft's Worldgen");
        String methodToPatch = "GetValidSpawnBlocks";
        String desc = deobf ? "()[Lnet/minecraft/block/Block;" : "()[Laji;";
        ClassReader cr = new ClassReader(origCode);
        ClassNode classNode = new ClassNode();
        cr.accept((ClassVisitor)classNode, 0);
        for (MethodNode methodNode : classNode.methods) {
            if (!methodNode.name.equals("GetValidSpawnBlocks") || !methodNode.desc.equals(desc)) continue;
            for (AbstractInsnNode insn : methodNode.instructions) {
                if (insn.getOpcode() != 176) continue;
                InsnList endList = new InsnList();
                endList.add((AbstractInsnNode)new MethodInsnNode(184, "witchinggadgets/asm/WGCoreTransformer", "worldGen_getValid" + ident, desc, false));
                methodNode.instructions.insertBefore(insn, endList);
            }
        }
        ClassWriter cw = new ClassWriter(1);
        classNode.accept((ClassVisitor)cw);
        return cw.toByteArray();
    }

    public static Block[] worldGen_getValidHilltopStones() {
        return WGConfig.coremod_worldgenValidBase_HilltopStones;
    }

    public static Block[] worldGen_getValidEldritchRing() {
        return WGConfig.coremod_worldgenValidBase_EldritchRing;
    }
}

