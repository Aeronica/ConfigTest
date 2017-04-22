/**
 * Copyright {2016} Paul Boese aka Aeronica
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tld.cfgtest.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tld.cfgtest.Main;
import tld.cfgtest.ModLogger;

@Config(modid = Main.MODID, name = "ConfigTest/ConfigTest", type = Config.Type.INSTANCE)
public class ModConfig
{

    @Config.Comment("client")
    public static Client client = new Client();
    
    public static class Client
    {
        @Config.Comment("visual")
        public static Visual visual = new Visual();

        public static class Visual
        {
            @Config.LangKey("config.cgftest:client.visual.disableNetherFog")
            public static boolean disableNetherFog = true;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        
        @SubscribeEvent
        public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            ModLogger.info("On ConfigChanged: %s", event.getModID());
            if(event.getModID().equals(Main.MODID))
            {
//                ConfigManager.sync(Main.MODID, Type.INSTANCE);
            }
        }
    }
    
}
