package cn.ksmcbrigade.badd.mixin;

import cn.ksmcbrigade.badd.Boom;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(FireballEntity.class)
public abstract class FireBallMixin extends AbstractFireballEntity {

    @Shadow private int explosionPower;

    public FireBallMixin(EntityType<? extends AbstractFireballEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>*",at = @At("TAIL"))
    public void explode(CallbackInfo ci) throws IOException {
        if(!Boom.init){
            Boom.init();
        }
        this.explosionPower = Boom.fire_ball;
    }
}
