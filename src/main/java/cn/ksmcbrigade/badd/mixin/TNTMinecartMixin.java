package cn.ksmcbrigade.badd.mixin;

import cn.ksmcbrigade.badd.Boom;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(TntMinecartEntity.class)
public abstract class TNTMinecartMixin extends AbstractMinecartEntity {
    public TNTMinecartMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "explode(Lnet/minecraft/entity/damage/DamageSource;D)V",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"), cancellable = true)
    public void explode(@Nullable DamageSource damageSource, double power, CallbackInfo ci) throws IOException {
        float f;
        if(!Boom.init){
            Boom.init();
        }
        f = Boom.tnt_minecraft;
        this.getWorld().createExplosion(this, damageSource, null, this.getX(), this.getY(), this.getZ(), (float)(4.0 + this.random.nextDouble() * 1.5 * f), false, World.ExplosionSourceType.TNT);
        this.discard();
        ci.cancel();
    }
}
