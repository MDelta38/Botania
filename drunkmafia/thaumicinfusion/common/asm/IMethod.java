/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.tree.AbstractInsnNode
 *  org.objectweb.asm.tree.InsnList
 *  org.objectweb.asm.tree.InsnNode
 *  org.objectweb.asm.tree.LabelNode
 *  org.objectweb.asm.tree.LdcInsnNode
 *  org.objectweb.asm.tree.LocalVariableNode
 *  org.objectweb.asm.tree.MethodNode
 */
package drunkmafia.thaumicinfusion.common.asm;

import java.util.ArrayList;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LocalVariableNode;
import org.objectweb.asm.tree.MethodNode;

class IMethod {
    private final String name;
    private final String returnType;
    private final String paramaters;

    public IMethod(String name, String returnType, String paramaters) {
        this.name = name;
        this.returnType = returnType;
        this.paramaters = paramaters;
    }

    public MethodNode getMethodNode(String className) {
        MethodNode node = new MethodNode(1, this.name, "(" + this.paramaters + ")" + this.returnType, null, null);
        node.localVariables = new ArrayList();
        node.localVariables.add(new LocalVariableNode("this", "L" + className + ";", null, new LabelNode(), new LabelNode(), 0));
        if (this.returnType != null) {
            InsnList list = new InsnList();
            int opcode = Type.getReturnType((String)node.desc).getOpcode(172);
            list.add((AbstractInsnNode)new LdcInsnNode((Object)(opcode == 172 || opcode == 174 || opcode == 175 || opcode == 173 ? Integer.valueOf(0) : null)));
            list.add((AbstractInsnNode)new InsnNode(opcode));
            node.instructions.add(list);
        }
        return node;
    }

    public String getName() {
        return this.name;
    }

    public String getReturnType() {
        return this.returnType;
    }

    public String getParamaters() {
        return this.paramaters;
    }
}

