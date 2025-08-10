package us.timinc.mc.cobblemon.optionscontim

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.events.CobblemonEvents
import us.timinc.mc.cobblemon.optionscontim.handlers.NoCatchOutsideOfBattleHandler
import us.timinc.mc.cobblemon.optionscontim.handlers.NoDropsOutsideOfBattleHandler
import us.timinc.mc.cobblemon.timcore.AbstractConfig
import us.timinc.mc.cobblemon.timcore.AbstractMod
import us.timinc.mc.cobblemon.timcore.config.OverridableOption

const val MOD_ID: String = "options_con_tim"

object OptionsConTim : AbstractMod<OptionsConTim.OptionsConTimConfig>(MOD_ID, OptionsConTimConfig::class.java) {

    class OptionsConTimConfig : AbstractConfig() {
        val outOfBattleCaptures: OverridableOption.PokemonOption<Boolean> = OverridableOption.PokemonOption(true)
        val outOfBattleCaptureBallBypass: OverridableOption.PokemonOption<List<String>> =
            OverridableOption.PokemonOption(listOf("cobblemon:safari_ball", "cobblemon:master_ball"))
        val onlyDropInBattle: OverridableOption.PokemonOption<Boolean> = OverridableOption.PokemonOption(false)
    }

    init {
        CobblemonEvents.THROWN_POKEBALL_HIT.subscribe(Priority.HIGHEST, NoCatchOutsideOfBattleHandler::handle)
        CobblemonEvents.LOOT_DROPPED.subscribe(Priority.HIGHEST, NoDropsOutsideOfBattleHandler::handle)
    }
}