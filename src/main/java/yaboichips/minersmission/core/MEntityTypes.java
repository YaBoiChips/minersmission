package yaboichips.minersmission.core;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yaboichips.minersmission.common.entites.villagers.Foreman;

import static yaboichips.minersmission.MinersMission.MODID;

public class MEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<Foreman>> FOREMAN = ENTITIES.register("foreman", () -> EntityType.Builder.<Foreman>of(Foreman::new, MobCategory.MISC).sized(1F, 2F).build("foreman"));
}
