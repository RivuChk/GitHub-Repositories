package dev.rivu.nasaapodarchive.domain.schedulers

import dev.rivu.githubrepositories.domain.schedulers.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object FakeSchedulerProvider: SchedulerProvider {
    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}