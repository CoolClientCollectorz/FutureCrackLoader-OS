package team.ccc.futurecrack;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/*
 * @author Crystallinqq on 6/21/2020
 * updated by Tigermouthbear
 */
@IFMLLoadingPlugin.Name("FutureCrack")
@IFMLLoadingPlugin.MCVersion("1.12.2")
@Mod(modid = "futurecrackloader", name = "FutureCrackLoader", version = "b0.3")
public class FutureCrack implements IFMLLoadingPlugin {
    public static Logger log = LogManager.getLogger("FutureCrack");

    public static void mixinLoad() {
        //straight crippin
        MixinBootstrap.init();
        log.info("Initialized Mixin Bootstrap");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
        log.info("Loading Future Mixin Configs");
        Mixins.addConfiguration("mixins.future.common.json");
        log.info("loaded main future cfg");
        Mixins.addConfiguration("mixins.future.forge.json");
        log.info("loaded forge future cfg");
        Mixins.addConfiguration("mixins.future.optifine.json");
        //load baritone cfg only if we find the baritone tweaker class.
        try {
            Class.forName("baritone.launch.BaritoneTweaker", true, Thread.currentThread().getContextClassLoader());
            Mixins.addConfiguration("mixins.future.baritone.json");
            log.info("loaded baritone future cfg (You should only see this if you have baritone forge api in ur mods folder)");
        } catch (ClassNotFoundException ignored) { }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle("Future Cracked By CCC -Crystallinqq, mastercooker, Tigermouthbear");
    }
	
	@Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        log.info("Starting Future Crack at " + dtf.format(time));
        mixinLoad();
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
