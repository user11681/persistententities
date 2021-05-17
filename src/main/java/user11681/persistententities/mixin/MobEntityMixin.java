package user11681.persistententities.mixin;

import java.util.Random;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import user11681.persistententities.config.PersistentEntitiesConfiguration;

@Mixin(MobEntity.class)
abstract class MobEntityMixin {
    @ModifyConstant(method =  "checkDespawn",
                    constant = @Constant(intValue = 600))
    public int modifyDespawnTime(int previous) {
        return PersistentEntitiesConfiguration.instance.mobTime;
    }

    @Redirect(method = "checkDespawn",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/Random;nextInt(I)I",
                       ordinal = 0))
    public int disableDespawn(Random random, int bound) {
        return PersistentEntitiesConfiguration.instance.disableMobs ? -1 : random.nextInt(bound);
    }
}
