package us.timinc.mc.cobblemon.optionscontim.handlers

import com.cobblemon.mod.common.api.events.pokeball.ThrownPokeballHitEvent
import us.timinc.mc.cobblemon.optionscontim.OptionsConTim.config
import us.timinc.mc.cobblemon.optionscontim.OptionsConTim.debugger
import us.timinc.mc.cobblemon.timcore.AbstractHandler

object NoCatchOutsideOfBattleHandler : AbstractHandler<ThrownPokeballHitEvent>() {
    override fun handle(evt: ThrownPokeballHitEvent) {
        val pokemon = evt.pokemon.pokemon
        if (!config.outOfBattleCaptures.getContextValue(pokemon)) {
            if (!evt.pokemon.isBattling && !config.outOfBattleCaptureBallBypass.getContextValue(pokemon)
                    .contains(evt.pokeBall.pokeBall.name.toString())
            ) {
                debugger.debug("[outOfBattleCaptures][${pokemon.uuid}](${pokemon.species.name}) Blocked from capture.")
                evt.cancel()
            }
        }
    }
}