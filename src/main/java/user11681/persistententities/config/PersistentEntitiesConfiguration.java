package user11681.persistententities.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config.Gui.Background;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.Excluded;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Gui.Tooltip;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screen.Screen;

@Config(name = "persistententities")
@Background("textures/block/andesite.png")
public class PersistentEntitiesConfiguration implements ConfigData, ModInitializer {
    @Excluded
    public static transient PersistentEntitiesConfiguration instance;

    @Tooltip
    public int itemTime = 6000;

    @Tooltip
    public int mobTime = 600;

    public boolean disableItems = true;
    public boolean disableMobs = true;

    @Override
    public void onInitialize() {
        instance = AutoConfig.register(PersistentEntitiesConfiguration.class, Toml4jConfigSerializer::new).get();
    }

    @Environment(EnvType.CLIENT)
    public static class ModMenuIntegration implements ModMenuApi {
        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return (Screen parent) -> AutoConfig.getConfigScreen(PersistentEntitiesConfiguration.class, parent).get();
        }
    }
}
