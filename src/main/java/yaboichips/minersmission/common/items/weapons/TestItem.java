package yaboichips.minersmission.common.items.weapons;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static yaboichips.minersmission.core.MItems.TAB;
import static yaboichips.minersmission.events.MiningEvent.spawnWave;

public class TestItem extends Item {
    public TestItem() {
        super(new Item.Properties().tab(TAB));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand p_41434_) {
        spawnWave(1, player);
        return super.use(p_41432_, player, p_41434_);
    }
}
