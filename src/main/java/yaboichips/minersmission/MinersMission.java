package yaboichips.minersmission;

import com.mojang.logging.LogUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import yaboichips.minersmission.client.model.ForemanModel;
import yaboichips.minersmission.client.renderers.ForemanRenderer;
import yaboichips.minersmission.client.renderers.GremlinRenderer;
import yaboichips.minersmission.common.entites.Gremlin;
import yaboichips.minersmission.common.entites.villagers.Foreman;
import yaboichips.minersmission.core.MEntityTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static net.minecraft.client.model.geom.LayerDefinitions.INNER_ARMOR_DEFORMATION;
import static net.minecraft.client.model.geom.LayerDefinitions.OUTER_ARMOR_DEFORMATION;
import static yaboichips.minersmission.core.MBlocks.BLOCKS;
import static yaboichips.minersmission.core.MEntityTypes.ENTITIES;
import static yaboichips.minersmission.core.MItems.ITEMS;
import static yaboichips.minersmission.core.world.MBiomes.BIOMES;
import static yaboichips.minersmission.core.world.MFeatures.FEATURES;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinersMission.MODID)
public class MinersMission {

    public static ResourceKey<Level> MINERS_WORLD;


    // Define mod id in a common place for everything to reference
    public static final String MODID = "minersmission";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MinersMission() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BIOMES.register(bus);
        ENTITIES.register(bus);
        FEATURES.register(bus);
        bus.addListener(this::bakeLayers);
        bus.addListener(this::registerEntityRenderers);
        bus.addListener(this::entityAttributes);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        GeckoLib.initialize();
    }

    public static ResourceLocation createLocation(String id) {
        return new ResourceLocation(MODID, id);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
//        ModNoiseSettings.registerNoise();
        MINERS_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(MODID, "minersworld"));
    }

    public void entityAttributes(final EntityAttributeCreationEvent event) {
        event.put(MEntityTypes.FOREMAN.get(), Foreman.createAttributes().build());
        event.put(MEntityTypes.GREMLIN.get(), Gremlin.createAttributes().build());
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers renderer) {
        renderer.registerEntityRenderer(MEntityTypes.FOREMAN.get(), ForemanRenderer::new);
        renderer.registerEntityRenderer(MEntityTypes.GREMLIN.get(), GremlinRenderer::new);
    }

    public void bakeLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ForemanModel.LAYER_LOCATION, () -> LayerDefinition.create(HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F), 64, 64));
        event.registerLayerDefinition(ForemanModel.LAYER_LOCATION_OUTER, () -> LayerDefinition.create(HumanoidModel.createMesh(OUTER_ARMOR_DEFORMATION, 0.0F), 64, 32));
        event.registerLayerDefinition(ForemanModel.LAYER_LOCATION_INNER, () -> LayerDefinition.create(HumanoidModel.createMesh(INNER_ARMOR_DEFORMATION, 0.0F), 64, 32));
    }


    public Map<UUID, Integer> playerTimerMap = new HashMap<>();

    @SubscribeEvent
    public void startMission(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getTo() == MinersMission.MINERS_WORLD) {
            setMissionTimer(event.getEntity(), 6000);
        }
    }

    @SubscribeEvent
    public void tickMission(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        UUID uuid = player.getUUID();
        if (playerTimerMap.containsKey(uuid)){
            setMissionTimer(player, getMissionTimer(player) - 1);
            System.out.println(getMissionTimer(player));
        }
    }

    public int getMissionTimer(Player player) {
        return playerTimerMap.get(player.getUUID());
    }

    private void setMissionTimer(Player player, int timer) {
        playerTimerMap.put(player.getUUID(), timer);
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
