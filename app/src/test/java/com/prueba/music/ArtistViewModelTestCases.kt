package com.prueba.music

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.prueba.music.MusicApp.Companion.context
import com.prueba.music.api.AplicationApi
import com.prueba.music.api.OperationResult
import com.prueba.music.models.Artists
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository
import com.prueba.music.viewmodels.ArtistsViewModel
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class ArtistViewModelTestCases {


    @Mock
    private val fakeArtistRepository = FakeArtistRepository()
    private val fakeErrorArtistRepository = FakeErrorArtistRepository()
    private val fakeNullArtistRepository = FakeNullArtistRepository()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: ArtistsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ArtistsViewModel(null, null)
    }

    @After
    fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }


    @Test
    fun `retrieve Artists return FullData`() {

        with(viewModel) {
            loadArtist()
        }

        runBlockingTest {
            val response = fakeArtistRepository.retrieveArtist()
            Assert.assertTrue(response is OperationResult.Success)
            viewModel.responseLiveDataArtists.observeForever {
                Assert.assertTrue(it.topartists.artist.isNotEmpty())
            }
        }

    }


    @Test
    fun `retrieve Artists return Error`() {
        with(viewModel) {
            loadArtist()
        }


        runBlockingTest {
            val response = fakeErrorArtistRepository.retrieveArtist()
            Assert.assertTrue(response is OperationResult.Error)
            viewModel.onMessageError.observeForever {
                Assert.assertTrue(it != null)
            }
        }


    }

    @Test
    fun `retrieve Artists return Null`() {
        with(viewModel) {
            loadArtist()
        }

        runBlockingTest {
        val response = fakeNullArtistRepository.retrieveArtist()
            Assert.assertTrue(response is OperationResult.Success)
            viewModel.isEmpty.observeForever {
                Assert.assertTrue(it)
            }

        }
        }


}