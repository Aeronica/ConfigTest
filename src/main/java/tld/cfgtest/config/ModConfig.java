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

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tld.cfgtest.Main;
import tld.cfgtest.ModLogger;

@Config(modid = Main.MODID, name = "ConfigTest/ConfigTest", type = Config.Type.INSTANCE)
@Config.LangKey("config.cfgtest:title")
public class ModConfig
{
    
    @Config.Comment("Server Configuration Options")
    public static Server server = new Server();
    
    public static class Server
    {
        @Config.Comment({"Enabled Recipes", "Requires a Server Restart!"})
        public Recipes receipes = new Recipes();

        public static class Recipes
        {      
            @Config.Name("Recipe Enables")
            @Config.RequiresMcRestart
            @Config.LangKey("config.cgftest:general.enabled_recipes")
            public String[] recipeEnables = {"cfgtest:fork", "cfgtest:spork"};
        }
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
