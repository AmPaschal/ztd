package com.ampaschal.google.agents.agent5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import com.ampaschal.google.PermissionsManager;
import com.ampaschal.google.TestHelper;
import com.ampaschal.google.entities.PermissionArgs;
import com.ampaschal.google.enums.ProfileKey;
import com.ampaschal.google.transformers.BytecodeGenerator;
import com.ampaschal.google.utils.Utils;

public class BytecodeGeneratorAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        TestHelper.logTime(ProfileKey.AGENT_CALLED);

        System.out.println("Permissions Agent");
        

        inst.addTransformer(new BytecodeGenerator(), true);

        try {
            inst.retransformClasses(FileInputStream.class, FileOutputStream.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        TestHelper.logTime(ProfileKey.AGENT_EXITING);

    }
}
