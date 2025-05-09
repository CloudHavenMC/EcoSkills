package com.willfp.ecoskills.libreforge

import com.willfp.eco.core.integrations.antigrief.AntigriefManager
import com.willfp.libreforge.toDispatcher
import com.willfp.libreforge.triggers.Trigger
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import gg.cloudhaven.suite.forests.axes.data.ForestBlockBreakEvent
import gg.cloudhaven.suite.mirages.events.MirageBlockBreakEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority

object TriggerForestBlockBreak : Trigger("forest_block_break") {
    override val parameters = setOf(
        TriggerParameter.PLAYER,
        TriggerParameter.BLOCK,
        TriggerParameter.LOCATION,
        TriggerParameter.EVENT,
        TriggerParameter.ITEM
    )

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun handle(event: ForestBlockBreakEvent) {
        val player = event.player
        val block = event.block

        if (!AntigriefManager.canBreakBlock(player, block)) {
            return
        }

        this.dispatch(
            player.toDispatcher(),
            TriggerData(
                player = player,
                block = block,
                location = block.location,
                event = event,
                item = player.inventory.itemInMainHand
            )
        )
    }
}