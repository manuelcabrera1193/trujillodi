package com.cabrera.manuel.trujillodi.screena

import com.cabrera.manuel.trujillodi.base.coordinator.Coordinator
import com.cabrera.manuel.trujillodi.base.EmitterData
import com.cabrera.manuel.trujillodi.base.navigation.NavigationService
import com.willard.cabrera.data.repository.RecipesRepositoryImpl
import com.willard.cabrera.data.service.base.HttpClientFactory
import com.willard.cabrera.data.service.base.HttpClientType
import com.willard.cabrera.domain.repository.RecipesRepository
import kotlinx.coroutines.CoroutineScope

class ScreenACoordinatorFactory(
    private val scope: CoroutineScope,
    private val parentCoordinator: Coordinator,
    private val emitterData: EmitterData,
    private val navigationService: NavigationService,
    private val httpClientFactory: HttpClientFactory,
) {

    fun create() = ScreenACoordinator(
        scope = scope,
        parent = parentCoordinator,
        emitterData = emitterData,
        navigationService = navigationService,
        repository = createRepository(httpClientFactory),
    )

    private fun createRepository(httpClientFactory: HttpClientFactory): RecipesRepository {
        val httpClientProvider = httpClientFactory.create(HttpClientType.KTOR)
        return RecipesRepositoryImpl(httpClientProvider)
    }
}