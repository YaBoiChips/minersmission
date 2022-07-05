package yaboichips.minersmission.common.items.weapons;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import yaboichips.minersmission.core.MItems;

public class BattleAxeItem extends AxeItem {
    public BattleAxeItem() {
        super(Tiers.DIAMOND, 6F, -3.5F, (new Item.Properties()).tab(MItems.TAB));
    }
}
