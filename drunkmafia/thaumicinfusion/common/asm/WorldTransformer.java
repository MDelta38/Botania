/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.IClassTransformer
 *  org.objectweb.asm.ClassReader
 *  org.objectweb.asm.ClassVisitor
 *  org.objectweb.asm.ClassWriter
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.ClassNode
 *  org.objectweb.asm.tree.FieldInsnNode
 *  org.objectweb.asm.tree.FieldNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.MethodNode
 *  org.objectweb.asm.tree.VarInsnNode
 */
package drunkmafia.thaumicinfusion.common.asm;

import drunkmafia.thaumicinfusion.common.asm.ThaumicInfusionPlugin;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

public class WorldTransformer
implements IClassTransformer {
    public static boolean hasInjectedIntoWorld;

    public byte[] transform(String name, String transformedName, byte[] bytecode) {
        if (!transformedName.equals("net.minecraft.world.World")) {
            return bytecode;
        }
        ThaumicInfusionPlugin.log.info("Injecting interface into World Class");
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytecode);
        classReader.accept((ClassVisitor)classNode, 0);
        classNode.interfaces.add("drunkmafia/thaumicinfusion/common/world/IWorldDataProvider");
        classNode.fields.add(new FieldNode(2, "worldData", "Ldrunkmafia/thaumicinfusion/common/world/TIWorldData;", null, null));
        MethodNode getWorldData = new MethodNode(1, "getWorldData", "()Ldrunkmafia/thaumicinfusion/common/world/TIWorldData;", null, null);
        InsnList toInsert = new InsnList();
        toInsert.add((AbstractInsnNode)new VarInsnNode(25, 0));
        toInsert.add((AbstractInsnNode)new FieldInsnNode(180, "net/minecraft/world/World", "worldData", "Ldrunkmafia/thaumicinfusion/common/world/TIWorldData;"));
        toInsert.add((AbstractInsnNode)new InsnNode(176));
        getWorldData.instructions.add(toInsert);
        classNode.methods.add(getWorldData);
        MethodNode setWorldData = new MethodNode(1, "setWorldData", "(Ldrunkmafia/thaumicinfusion/common/world/TIWorldData;)V", null, null);
        toInsert = new InsnList();
        toInsert.add((AbstractInsnNode)new VarInsnNode(25, 0));
        toInsert.add((AbstractInsnNode)new VarInsnNode(25, 1));
        toInsert.add((AbstractInsnNode)new FieldInsnNode(181, "net/minecraft/world/World", "worldData", "Ldrunkmafia/thaumicinfusion/common/world/TIWorldData;"));
        toInsert.add((AbstractInsnNode)new InsnNode(177));
        setWorldData.instructions.add(toInsert);
        classNode.methods.add(setWorldData);
        ClassWriter classWriter = new ClassWriter(3);
        classNode.accept((ClassVisitor)classWriter);
        ThaumicInfusionPlugin.log.info("Injected interface into World Class");
        hasInjectedIntoWorld = true;
        return classWriter.toByteArray();
    }
}

