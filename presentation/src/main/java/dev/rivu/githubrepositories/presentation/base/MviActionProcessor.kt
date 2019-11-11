package dev.rivu.githubrepositories.presentation.base

import io.reactivex.FlowableTransformer

interface MviActionProcessor<A: MviAction, R: MviResult> {
    fun transformFromAction(): FlowableTransformer<A, R>
}