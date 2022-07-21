package yaboichips.minersmission.core;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import yaboichips.minersmission.MinersMission;

import java.util.function.Supplier;

import static yaboichips.minersmission.MinersMission.MODID;

public class MBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);

    public static final ResourceKey<Biome> MINERS = createBiome("minersworld", MBiomes::minersWorld);

    public static Biome minersWorld() {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder();

        generationSettings.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        return new Biome.BiomeBuilder().precipitation(Biome.Precipitation.SNOW).temperature(0.25F).downfall(0.5F).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).skyColor(0).waterFogColor(329011).fogColor(12638463).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    public static <B extends Biome> ResourceKey<Biome> createBiome(String id, Supplier<? extends B> biome) {
        ResourceLocation modID = MinersMission.createLocation(id);
        BIOMES.register(id, biome);
        return ResourceKey.create(Registry.BIOME_REGISTRY, modID);
    }
}