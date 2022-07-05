package yaboichips.minersmission.common.items.weapons;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import static yaboichips.minersmission.core.MItems.TAB;

public class WarHammer extends AxeItem {
    public WarHammer() {
        super(Tiers.DIAMOND, 5.5F, -3.35F, new Item.Properties().tab(TAB));
    }

    @Override
    public InteractionResult useOn(UseOnContext p_40529_) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player player, InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
}
