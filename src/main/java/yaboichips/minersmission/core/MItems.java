package yaboichips.minersmission.core;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static yaboichips.minersmission.MinersMission.MODID;

public class MItems {
    // Create a Deferred Register to hold Items which will all be registered under the "minersmission" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
}
