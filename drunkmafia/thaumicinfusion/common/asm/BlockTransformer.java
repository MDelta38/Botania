/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper
 *  cpw.mods.fml.common.asm.transformers.deobf.FMLRemappingAdapter
 *  net.minecraft.block.Block
 *  net.minecraft.launchwrapper.IClassTransformer
 *  net.minecraft.launchwrapper.Launch
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.JumpInsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.MethodInsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package drunkmafia.thaumicinfusion.common.asm;

import cpw.mods.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import cpw.mods.fml.common.asm.transformers.deobf.FMLRemappingAdapter;
import drunkmafia.thaumicinfusion.common.asm.IMethod;
import drunkmafia.thaumicinfusion.common.asm.Interface;
import drunkmafia.thaumicinfusion.common.asm.ThaumicInfusionPlugin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class BlockTransformer
implements IClassTransformer {
    public static List<Interface> blockInterfaces = new ArrayList<Interface>();
    public static List<String> blockMethods = new ArrayList<String>();
    private static boolean shouldInject = true;
    private static List<String> blockClasses = new ArrayList<String>();
    private static Map<String, List<String>> injectedClassess = new HashMap<String, List<String>>();
    private static int injectedClasses;
    private static int totalClasses;

    public static void blockCheck(Iterator classesIter) {
        while (classesIter.hasNext()) {
            Object obj = classesIter.next();
            if (!(obj instanceof Block)) continue;
            try {
                BlockTransformer.searchBlock(Launch.classLoader.getClassBytes(FMLDeobfuscatingRemapper.INSTANCE.unmap(obj.getClass().getName()).replace('/', '.')));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        ThaumicInfusionPlugin.log.info("Thaumic Infusion has finished transforming Block Classes, a total of " + injectedClasses + " out of " + totalClasses + " have been found & transformed!");
        ThaumicInfusionPlugin.log.info("Transformer has been disabled, since no more block classes should be getting loaded in!");
        shouldInject = false;
        injectedClassess = null;
        blockClasses = null;
    }

    private static void searchBlock(byte[] bytecode) throws IOException {
        List<String> methods;
        if (bytecode == null) {
            return;
        }
        ClassNode classNode = new ClassNode(327680);
        new ClassReader(bytecode).accept((ClassVisitor)classNode, 8);
        if (classNode.superName == null) {
            return;
        }
        if (!classNode.superName.replace('/', '.').equals(Block.class.getName())) {
            BlockTransformer.searchBlock(Launch.classLoader.getClassBytes(FMLDeobfuscatingRemapper.INSTANCE.unmap(classNode.superName.replace('.', '/')).replace('/', '.')));
        }
        if ((methods = injectedClassess.get(classNode.name.replace('/', '.'))) == null) {
            return;
        }
        ++totalClasses;
        for (MethodNode method : classNode.methods) {
            if (!methods.contains(method.name)) continue;
            ++injectedClasses;
            return;
        }
    }

    public byte[] transform(String name, String transformedName, byte[] bytecode) {
        if (bytecode == null || !shouldInject) {
            return bytecode;
        }
        ClassNode classNode = new ClassNode(327680);
        ClassNode deobfClassNode = new ClassNode(327680);
        new ClassReader(bytecode).accept((ClassVisitor)classNode, 8);
        this.getDeobfReader(bytecode).accept((ClassVisitor)deobfClassNode, 8);
        MinecraftClassWriter classWriter = new MinecraftClassWriter(classNode.name, 3);
        boolean isBlockClass = deobfClassNode.name.equals("net/minecraft/block/Block");
        if (isBlockClass) {
            ThaumicInfusionPlugin.log.info("Found the Block Class");
            ThaumicInfusionPlugin.logger.println("The following log shows the progress of the transformer, any crashes will be logged in here. If you are reporting a crash/bug for TI, please include this log along with the crash!");
            ThaumicInfusionPlugin.logger.println("==== Transformers ====");
            for (IClassTransformer transformer : Launch.classLoader.getTransformers()) {
                ThaumicInfusionPlugin.logger.println("Transformer: " + transformer.getClass().getName());
            }
        }
        if (!isBlockClass && !this.checkIfisBlock(deobfClassNode.superName)) {
            return bytecode;
        }
        boolean hasInjectedCode = false;
        try {
            int methodNo = 1;
            if (isBlockClass) {
                for (Interface inter : blockInterfaces) {
                    inter.injectMethodsIntoClass(classNode);
                    for (IMethod method : inter.getMethods()) {
                        blockMethods.add(method.getName());
                    }
                }
            }
            ArrayList<String> methodsInjected = new ArrayList<String>();
            for (int i = 0; i < classNode.methods.size() && i < deobfClassNode.methods.size(); ++i) {
                Type[] pars;
                WorldParamaters worldPars;
                MethodNode method = (MethodNode)classNode.methods.get(i);
                MethodNode deobfMethod = (MethodNode)deobfClassNode.methods.get(i);
                if (method.access != 1 && method.access != 2 || !isBlockClass && !blockMethods.contains(deobfMethod.name) || (worldPars = this.getWorldPars(pars = Type.getArgumentTypes((String)method.desc))) == null) continue;
                boolean skip = false;
                if (isBlockClass) {
                    blockMethods.add(deobfMethod.name);
                } else {
                    for (AbstractInsnNode node : deobfMethod.instructions.toArray()) {
                        if (!(node instanceof MethodInsnNode)) continue;
                        MethodInsnNode methodIsn = (MethodInsnNode)node;
                        if (!methodIsn.name.equals(deobfMethod.name) || !methodIsn.owner.equals(deobfClassNode.superName)) continue;
                        ThaumicInfusionPlugin.logger.println(methodNo++ + ") Block Method found: " + deobfMethod.name + " (" + deobfMethod.name.hashCode() + ") " + method.desc + " Access: " + method.access + " | SKIPPED (Super call Detected)");
                        skip = true;
                        break;
                    }
                }
                if (skip) continue;
                int returnType = Type.getReturnType((String)method.desc).getOpcode(172);
                for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (node == null || !(node instanceof MethodInsnNode) || !((MethodInsnNode)node).owner.equals("drunkmafia/thaumicinfusion/common/block/BlockWrapper")) continue;
                    ThaumicInfusionPlugin.logger.println(methodNo++ + ") Block Method found: " + deobfMethod.name + " (" + deobfMethod.name.hashCode() + ") " + method.desc + " Access: " + method.access + " | SKIPPED (Already Injected)");
                    skip = true;
                    break;
                }
                if (skip) continue;
                InsnList toInsert = new InsnList();
                worldPars.loadPars(toInsert);
                toInsert.add((AbstractInsnNode)new VarInsnNode(25, 0));
                toInsert.add((AbstractInsnNode)new LdcInsnNode((Object)deobfMethod.name.hashCode()));
                toInsert.add((AbstractInsnNode)new MethodInsnNode(184, "drunkmafia/thaumicinfusion/common/block/BlockWrapper", "hasWorldData", "(Lnet/minecraft/world/IBlockAccess;IIILnet/minecraft/block/Block;I)Z", false));
                LabelNode hasWorldData = new LabelNode();
                toInsert.add((AbstractInsnNode)new JumpInsnNode(153, hasWorldData));
                toInsert.add((AbstractInsnNode)new LabelNode());
                worldPars.loadPars(toInsert);
                toInsert.add((AbstractInsnNode)new LdcInsnNode((Object)deobfMethod.name.hashCode()));
                toInsert.add((AbstractInsnNode)new MethodInsnNode(184, "drunkmafia/thaumicinfusion/common/block/BlockWrapper", "overrideBlockFunctionality", "(Lnet/minecraft/world/IBlockAccess;IIII)Z", false));
                LabelNode overrideBlockFunctionality = new LabelNode();
                toInsert.add((AbstractInsnNode)new JumpInsnNode(153, overrideBlockFunctionality));
                toInsert.add((AbstractInsnNode)new LabelNode());
                this.injectInvokeBlock(toInsert, method, pars);
                toInsert.add((AbstractInsnNode)new InsnNode(returnType));
                toInsert.add((AbstractInsnNode)overrideBlockFunctionality);
                this.injectInvokeBlock(toInsert, method, pars);
                if (returnType != 177) {
                    toInsert.add((AbstractInsnNode)new InsnNode(87));
                }
                toInsert.add((AbstractInsnNode)hasWorldData);
                method.instructions.insert(toInsert);
                if (!hasInjectedCode) {
                    ThaumicInfusionPlugin.logger.println("==== " + transformedName + " (SuperClass: " + classNode.superName + ") ====");
                    hasInjectedCode = true;
                }
                ThaumicInfusionPlugin.logger.println(methodNo++ + ") Block Method found: " + deobfMethod.name + " (" + deobfMethod.name.hashCode() + ") " + method.desc + " Access: " + method.access + " | INJECTED");
                methodsInjected.add(deobfMethod.name);
            }
            ThaumicInfusionPlugin.logger.flush();
            if (hasInjectedCode) {
                classNode.accept((ClassVisitor)classWriter);
                injectedClassess.put(deobfClassNode.name.replace('/', '.'), methodsInjected);
                return classWriter.toByteArray();
            }
        }
        catch (Throwable t) {
            this.handleCrash(transformedName, t);
        }
        return bytecode;
    }

    private void handleCrash(String transformedName, Throwable t) {
        ThaumicInfusionPlugin.log.info("Block: " + transformedName + "has an issue while merging the changes. A detailed crash has been printed to TI_Transformer.log, please upload this log to pastebin and report it to the mod author");
        ThaumicInfusionPlugin.log.info("Reverting to original bytecode, this block will not be compatible with infusions and will behave abnormally");
        ThaumicInfusionPlugin.logger.println("==== Block: " + transformedName + " has failed injection ==== ");
        t.printStackTrace(ThaumicInfusionPlugin.logger);
    }

    private boolean checkIfisBlock(String superName) {
        if (superName == null) {
            return false;
        }
        if (blockClasses.contains(superName)) {
            return true;
        }
        try {
            ClassReader reader;
            byte[] bytecode = Launch.classLoader.getClassBytes(superName.replace('/', '.'));
            if (bytecode == null) {
                if (ThaumicInfusionPlugin.isObf) {
                    bytecode = Launch.classLoader.getClassBytes(FMLDeobfuscatingRemapper.INSTANCE.unmap(superName.replace('.', '/')).replace('/', '.'));
                }
                if (bytecode == null) {
                    return false;
                }
            }
            ClassReader classReader = reader = ThaumicInfusionPlugin.isObf ? this.getDeobfReader(bytecode) : new ClassReader(bytecode);
            if (this.checkIfisBlock(reader.getSuperName())) {
                ThaumicInfusionPlugin.logger.println("Found new super: " + superName);
                blockClasses.add(superName);
                return true;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        return false;
    }

    private void injectInvokeBlock(InsnList isnList, MethodNode method, Type[] pars) {
        isnList.add((AbstractInsnNode)new FieldInsnNode(178, "drunkmafia/thaumicinfusion/common/block/BlockWrapper", "block", "L" + ThaumicInfusionPlugin.block + ";"));
        int stackIndex = 1;
        for (Type par : pars) {
            int opcode = par.getOpcode(21);
            isnList.add((AbstractInsnNode)new VarInsnNode(opcode, stackIndex++));
            if (opcode != 24) continue;
            ++stackIndex;
        }
        isnList.add((AbstractInsnNode)new MethodInsnNode(182, ThaumicInfusionPlugin.block, method.name, method.desc, false));
    }

    private ClassReader getDeobfReader(byte[] bytecode) {
        if (!ThaumicInfusionPlugin.isObf) {
            return new ClassReader(bytecode);
        }
        ClassReader classReader = new ClassReader(bytecode);
        ClassWriter classWriter = new ClassWriter(1);
        classReader.accept((ClassVisitor)new FMLRemappingAdapter((ClassVisitor)classWriter), 8);
        return new ClassReader(classWriter.toByteArray());
    }

    public WorldParamaters getWorldPars(Type[] pars) {
        WorldParamaters worldPars = new WorldParamaters();
        for (int i = 0; i < pars.length; ++i) {
            Type par = pars[i];
            if (worldPars.world != -1) {
                if (par.getClassName().equals("int")) {
                    if (worldPars.x == -1) {
                        worldPars.x = i + 1;
                        continue;
                    }
                    if (worldPars.y == -1) {
                        worldPars.y = i + 1;
                        continue;
                    }
                    if (worldPars.z != -1) break;
                    worldPars.z = i + 1;
                    continue;
                }
                if (worldPars.x == -1 && worldPars.y == -1 && worldPars.z == -1) continue;
                break;
            }
            if (!par.getClassName().equals(ThaumicInfusionPlugin.world.replace("/", ".")) && !par.getClassName().equals("net.minecraft.world.World") && !(worldPars.isBlockAccess = par.getClassName().equals(ThaumicInfusionPlugin.iBlockAccess.replace("/", "."))) && !(worldPars.isBlockAccess = par.getClassName().equals("net.minecraft.world.IBlockAccess"))) continue;
            worldPars.world = i + 1;
        }
        if (worldPars.world == -1 || worldPars.x == -1 || worldPars.y == -1 || worldPars.z == -1) {
            return null;
        }
        return worldPars;
    }

    static {
        Interface infusionStabiliser = new Interface("thaumcraft/api/crafting/IInfusionStabiliser");
        infusionStabiliser.addMethod(new IMethod("canStabaliseInfusion", "Z", "L" + ThaumicInfusionPlugin.world + ";III"));
        blockInterfaces.add(infusionStabiliser);
        blockClasses.add("net/minecraft/block/Block");
    }

    class WorldParamaters {
        boolean isBlockAccess;
        int world = -1;
        int x = -1;
        int y = -1;
        int z = -1;

        WorldParamaters() {
        }

        public void loadPars(InsnList toInsert) {
            toInsert.add((AbstractInsnNode)new VarInsnNode(25, this.world));
            toInsert.add((AbstractInsnNode)new VarInsnNode(21, this.x));
            toInsert.add((AbstractInsnNode)new VarInsnNode(21, this.y));
            toInsert.add((AbstractInsnNode)new VarInsnNode(21, this.z));
        }
    }

    class MinecraftClassWriter
    extends ClassWriter {
        public String className;

        public MinecraftClassWriter(String className, int flags) {
            super(flags);
            this.className = className;
        }

        protected String getCommonSuperClass(String type1, String type2) {
            Class c = null;
            Class d = null;
            try {
                if (!type1.equals(this.className)) {
                    c = Launch.classLoader.findClass(type1.replace('/', '.'));
                }
                if (!type2.equals(this.className)) {
                    d = Launch.classLoader.findClass(type2.replace('/', '.'));
                }
                if (c == null && d != null) {
                    return d.isInterface() ? "java/lang/Object" : type2;
                }
                if (c != null && d == null) {
                    return c.isInterface() ? "java/lang/Object" : type1;
                }
                if (c == null) {
                    throw new RuntimeException("Unable to find common super class of " + this.className);
                }
            }
            catch (Exception e) {
                return null;
            }
            if (c.isAssignableFrom(d)) {
                return type1;
            }
            if (d.isAssignableFrom(c)) {
                return type2;
            }
            if (c.isInterface() || d.isInterface()) {
                return "java/lang/Object";
            }
            while (!(c = c.getSuperclass()).isAssignableFrom(d)) {
            }
            return c.getName().replace('.', '/');
        }
    }
}

