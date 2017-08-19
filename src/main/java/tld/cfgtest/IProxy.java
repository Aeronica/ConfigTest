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
package tld.cfgtest;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public interface IProxy
{
    
    /**
     * FML preInit
     * @param event
     */
    public void preInit(FMLPreInitializationEvent event);
    
    /**
     * FML init
     * @param event
     */
    public void init(FMLInitializationEvent event);
    
    /**
     * FML postInit
     * @param event
     */
    public void postInit(FMLPostInitializationEvent event);
    
    /**
     * @return The physical side, is always Side.SERVER on the server and
     *         Side.CLIENT on the client
     */
    Side getPhysicalSide();

    /**
     * @return The effective side, on the server, this is always Side.SERVER, on
     *         the client it is dependent on the thread
     */
    Side getEffectiveSide();
    
    /** Returns the instance of Minecraft */
    Minecraft getMinecraft();
    
    /** Returns the instance of the EntityPlayer on the client, null on the server */
    EntityPlayer getClientPlayer();

    /** Returns the client World object on the client, null on the server */
    World getClientWorld();

    /** Returns the World object corresponding to the dimension id */
    World getWorldByDimensionId(int dimension);

    /** Returns the entity in that dimension with that id */
    Entity getEntityById(int dimension, int id);

    /** Returns the entity in that World object with that id */
    Entity getEntityById(World world, int id);

    /** helper to determine whether the given player is in creative mode */
    boolean playerIsInCreativeMode(EntityPlayer player);

    /** Returns a side-appropriate EntityPlayer for use during message handling */
    EntityPlayer getPlayerEntity(MessageContext ctx);

    /**
     * Returns the current thread based on side during message handling, used
     * for ensuring that the message is being handled by the main thread
     */
    IThreadListener getThreadFromContext(MessageContext ctx);

}
