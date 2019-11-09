package dev.rivu.githubrepositories.domain.usecase

sealed class UsecaseExecutionType {
    object Computation: UsecaseExecutionType()
    object IO: UsecaseExecutionType()
}