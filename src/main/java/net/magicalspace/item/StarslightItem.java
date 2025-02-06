
package net.magicalspace.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.magicalspace.init.MagicalspaceModFluids;

public class StarslightItem extends BucketItem {
	public StarslightItem() {
		super(MagicalspaceModFluids.STARSLIGHT.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).rarity(Rarity.RARE));
	}
}
