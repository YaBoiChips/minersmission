package yaboichips.minersmission.common.entites.villagers;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yaboichips.minersmission.core.MItems;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Foreman extends PathfinderMob implements Npc, Merchant {

    @javax.annotation.Nullable
    private Player tradingPlayer;
    @javax.annotation.Nullable
    protected MerchantOffers offers = new MerchantOffers();

    public Foreman(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }
    public List<UUID> hired = new ArrayList<>();

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        hired.forEach(uuid -> tag.putUUID("Hired", uuid));
        super.addAdditionalSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        UUID uuid;
        if (tag.hasUUID("Hired")){
            uuid = tag.getUUID("Hired");
        }else {
            String s = tag.getString("Hired");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        if (uuid != null){
            setHired(uuid);
        }
        if (tag.contains("Offers", 10)) {
            this.offers = new MerchantOffers(tag.getCompound("Offers"));
        }

        super.readAdditionalSaveData(tag);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level.isClientSide) {
            if (isHired(player.getUUID())) {
                player.sendSystemMessage(Component.literal("Welcome aboard recruit!"));
                if (this.offers != null) {
                    this.overrideOffers(this.offers);
                    this.startTrading(player);
                    return InteractionResult.sidedSuccess(this.level.isClientSide);
                }
            } else {
                ItemStack itemInHand = player.getItemInHand(hand);
                if (itemInHand.is(Items.DIAMOND)) {
                    itemInHand.shrink(1);
                    setHired(player.getUUID());
                    player.sendSystemMessage(Component.literal("You found this? You must be a skilled miner! You're Hired!"));
                } else {
                    player.sendSystemMessage(Component.literal("Who are you? GET AWAY FROM MY MINES!"));
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    private void startTrading(Player player) {
        this.setTradingPlayer(player);
        this.openTradingScreen(player, this.getDisplayName(), 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 0.01F).add(Attributes.ARMOR, 2.0D);
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public boolean isHired(UUID player){
        return hired.contains(player);
    }

    public void setHired(UUID player){
        hired.add(player);
    }

    public void setTradingPlayer(@javax.annotation.Nullable Player player) {
        boolean flag = this.getTradingPlayer() != null && player == null;
        this.tradingPlayer = player;
        if (flag) {
            this.stopTrading();
        }
    }

    protected void stopTrading() {
        this.setTradingPlayer(null);
    }

    @Nullable
    @Override
    public Player getTradingPlayer() {
        return this.tradingPlayer;
    }

    @Override
    public MerchantOffers getOffers() {
        if (this.offers == null) {
            this.offers = new MerchantOffers();
        }

        return this.offers;
    }

    @Override
    public void overrideOffers(MerchantOffers offers) {
        if (this.offers.isEmpty()) {
            offers.add(new MerchantOffer(Items.DIAMOND.getDefaultInstance(), MItems.MINERS_KEY.get().getDefaultInstance(), 10, 5, 2f));
            this.offers = offers;
        }
    }

    @Override
    public void notifyTrade(MerchantOffer offer) {
        offer.increaseUses();
    }

    @Override
    public void notifyTradeUpdated(ItemStack p_45308_) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int p_45309_) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return SoundEvents.ALLAY_ITEM_GIVEN;
    }

    @Override
    public boolean canRestock() {
        return true;
    }

    @Override
    public void openTradingScreen(Player p_45302_, Component p_45303_, int p_45304_) {
        Merchant.super.openTradingScreen(p_45302_, p_45303_, p_45304_);
    }

    @Override
    public boolean isClientSide() {
        return this.level.isClientSide;
    }
}
