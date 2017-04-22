package tld.cfgtest;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION, dependencies = Main.DEPS)
public class Main
{
    public static final String MODID = "cfgtest";
    public static final String MODNAME = "Config Test";
    public static final String VERSION = "{@VERSION}";
    public static final String DEPS = "";
    
    @Mod.Instance(MODID)
    public static Main instance;

    @SidedProxy(clientSide = "tld.cfgtest.ClientProxy", serverSide = "tld.cfgtest.ServerProxy")
    public static IProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModLogger.setLogger(event.getModLog());
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    public static String prependModID(String name)
    {
        return MODID + ":" + name;
    }
}
