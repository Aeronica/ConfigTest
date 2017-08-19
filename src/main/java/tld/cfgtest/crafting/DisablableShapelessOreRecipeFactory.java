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
package tld.cfgtest.crafting;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Disableable Shapeless Ore Recipe
 * @author Aeronica
 *
 */
public class DisablableShapelessOreRecipeFactory implements IRecipeFactory
{

    @Override
    public IRecipe parse(JsonContext context, JsonObject json)
    {
        ShapelessOreRecipe recipe = ShapelessOreRecipe.factory(context, json);
        
        ItemStack result = recipe.getRecipeOutput();
        NonNullList<Ingredient> input = recipe.getIngredients();
        return new DisablableRecipe(null, input, result);
    }

    public static class DisablableRecipe extends ShapelessOreRecipe {

        public DisablableRecipe(ResourceLocation group, NonNullList<Ingredient> input, ItemStack result)
        {
            super(group, input, result);
        }

        @Override
        @Nonnull
        public ItemStack getCraftingResult(InventoryCrafting var1)
        {
            return RecipeFactoryUtils.isRecipeEnabled(this.output) ? this.output.copy() : ItemStack.EMPTY;
        }

        @Override
        public boolean isHidden()
        {
            return !RecipeFactoryUtils.isRecipeEnabled(this.output);
        }
    }
    
}
