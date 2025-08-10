package us.timinc.mc.cobblemon.optionscontim.handlers

import com.cobblemon.mod.common.api.events.drops.LootDroppedEvent
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import us.timinc.mc.cobblemon.optionscontim.OptionsConTim.config
import us.timinc.mc.cobblemon.optionscontim.OptionsConTim.debugger
import us.timinc.mc.cobblemon.timcore.AbstractHandler

object NoDropsOutsideOfBattleHandler : AbstractHandler<LootDroppedEvent>() {
    override fun handle(evt: LootDroppedEvent) {
        val pokemonEntity = (evt.entity as? PokemonEntity ?: return)
        val pokemon = pokemonEntity.pokemon
        if (config.onlyDropInBattle.getContextValue(pokemon)) {
            if (!pokemonEntity.isBattling) {
                debugger.debug("[onlyDropInBattle][${pokemon.uuid}](${pokemon.species.name}) Blocked from dropping.")
                evt.cancel()
            }
        }
    }
}