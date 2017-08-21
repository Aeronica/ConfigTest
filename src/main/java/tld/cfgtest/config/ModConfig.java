/* MIT License
 *
 * Copyright (c) 2017 Aeronica
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package tld.cfgtest.config;


import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tld.cfgtest.Main;
import tld.cfgtest.ModLogger;


public class ModConfig
{
    
    @Config(modid = Main.MODID, name = Main.MODID + "/" +Main.MODID + "_recipes", type = Config.Type.INSTANCE, category="recipe")
    @Config.LangKey("config.cgftest:recipes")
    public static class CONFIG_RECIPE
    { 
        @Config.Name("toggles")
        @Config.Comment({"Test Config Recipes", "Requires a Server Restart if Changed!", "B:<name>=(true|false)"})
        @Config.RequiresMcRestart
        public static Map<String, Boolean> recipeToggles;

        private static final String[] modItemRecipeNames = {"fork", "spork"};
        
        static
        {
            recipeToggles = Maps.newHashMap();
            for (int i = 0; i < modItemRecipeNames.length; i++)
            {
                recipeToggles.put(modItemRecipeNames[i], true);
            }
        }
    }

    /**
     * Borrowed from Forge Test/Debug Sources
     * @See <a href="https://github.com/MinecraftForge/MinecraftForge/blob/1.12.x/LICENSE-new.txt">Forge License</a>
     *
     */
    @Config.LangKey("config.cfgtest:maps")
    @Config(modid = Main.MODID, name = Main.MODID + "/" +Main.MODID, category="general")
    public static class CONFIG_MAP
    {
        @Config.Name("map")
        @Config.Comment({"Requires a Server Restart if Changed!"})
        @Config.RequiresMcRestart
        public static Map<String, Integer[]> theMap;

        static
        {
            theMap = Maps.newHashMap();
            for (int i = 0; i < 7; i++)
            {
                Integer[] array = new Integer[6];
                for (int x = 0; x < array.length; x++)
                {
                    array[x] = i + x;
                }
                theMap.put("" + i, array);
            }
        }
    }

    /**
     * Borrowed from Forge Test/Debug Sources
     * @See <a href="https://github.com/MinecraftForge/MinecraftForge/blob/1.12.x/LICENSE-new.txt">Forge License</a>
     *
     */
    @Config.LangKey("config.cfgtest:annotations")
    @Config(modid = Main.MODID, name = Main.MODID + "/" +Main.MODID, category="client")
    public static class CONFIG_ANNOTATIONS
    {
        @Config.RangeDouble(min = -10.5, max = 100.5)
        public static double DoubleRange = 10.0;

        @Config.RangeInt(min = -10, max = 100)
        public static double IntRange = 10;

        @Config.LangKey("this.is.not.a.good.key")
        @Config.Comment({"This is a really long", "Multi-line comment"})
        public static String Comments = "Hi Tv!";

        @Config.Comment("Category Comment Test")
        public static NestedType Inner = new NestedType();

        public static class NestedType
        {
            public String HeyLook = "Go in!";
        }
    }
    
    /**
     * Will only allow this mods recipes to be disabled
     * @param stackIn
     * @return recipe state
     */
    public static boolean isRecipeEnabled(ItemStack stackIn)
    {
        String modName = stackIn.getUnlocalizedName().replaceFirst("item\\." + Main.MODID + ":", "");
        boolean enableState = CONFIG_RECIPE.recipeToggles.containsKey(modName) ? CONFIG_RECIPE.recipeToggles.get(modName) && !modName.contains(":"): true;
        ModLogger.info("isRecipeEnabled? %s %s", modName, enableState);
        return enableState;
    }
    
    public static boolean isRecipeHidden(ItemStack stackIn)
    {
        return !isRecipeEnabled(stackIn);
    }
    
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        
        @SubscribeEvent
        public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            ModLogger.info("On ConfigChanged: %s", event.getModID());
            if(event.getModID().equals(Main.MODID))
            {
                ConfigManager.sync(Main.MODID, Config.Type.INSTANCE);
            }
        }
    }
    
}
