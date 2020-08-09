package com.anifichadia.employeehub.feature.employeedetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anifichadia.employeehub.service.domain.Base64EncodedString
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.shared.usecase.RetrieveEmployeeDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.eq
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.MockitoAnnotations
import java.time.LocalDate
import org.mockito.Mockito.`when` as whenever

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
class EmployeeDetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var retrieveEmployeeDetailsUseCase: RetrieveEmployeeDetailsUseCase
    private lateinit var viewModel: EmployeeDetailsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        MockitoAnnotations.openMocks(this)

        viewModel = EmployeeDetailsViewModel(
            retrieveEmployeeDetailsUseCase
        )
        viewModel.employeeId = employeeId
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    //region LoadEmployeeDetailsIfRequired
    @Test
    fun givenLoadingStateIsUnset_whenLoadEmployeeDetailsIfRequired_thenRetrievalInvoked() {
        runBlockingTest {
            viewModel._loadingState.value = null

            viewModel.loadEmployeeDetailsIfRequired()

            verify(retrieveEmployeeDetailsUseCase).execute(employeeId)
        }
    }

    @Test
    fun givenLoadingStateIsLoading_whenLoadEmployeeDetailsIfRequired_thenRetrievalDoesNotOccur() {
        runBlockingTest {
            viewModel._loadingState.value = EmployeeDetailsContract.LoadingState.LOADING

            viewModel.loadEmployeeDetailsIfRequired()

            verifyNoInteractions(retrieveEmployeeDetailsUseCase)
        }
    }

    @Test
    fun givenLoadingStateIsSuccess_whenLoadEmployeeDetailsIfRequired_thenRetrievalDoesNotOccur() {
        runBlockingTest {
            viewModel._loadingState.value = EmployeeDetailsContract.LoadingState.SUCCESS

            viewModel.loadEmployeeDetailsIfRequired()

            verifyNoInteractions(retrieveEmployeeDetailsUseCase)
        }
    }

    @Test
    fun givenLoadingStateIsError_whenLoadEmployeeDetailsIfRequired_thenRetrievalInvoked() {
        runBlockingTest {
            viewModel._loadingState.value = EmployeeDetailsContract.LoadingState.ERROR

            viewModel.loadEmployeeDetailsIfRequired()

            verify(retrieveEmployeeDetailsUseCase).execute(employeeId)
        }
    }


    @Test
    fun givenUseCaseReturnsSuccess_whenLoadEmployeeDetailsIfRequired_thenLoadingStateUpdatedToSuccess_andEmployeeSet() {
        runBlockingTest {
            val employee = givenEmployee()
            whenever((retrieveEmployeeDetailsUseCase.execute(eq(employeeId)))).thenReturn(
                RetrieveEmployeeDetailsUseCase.Result.Success(employee)
            )

            viewModel.loadEmployeeDetailsIfRequired()

            verify(retrieveEmployeeDetailsUseCase).execute(employeeId)
            assertEquals(EmployeeDetailsContract.LoadingState.SUCCESS, viewModel.loadingState.value)
            assertEquals(employee, viewModel.employee.value)
        }
    }

    @Test
    fun givenUseCaseReturnsFailure_whenLoadEmployeeDetailsIfRequired_thenLoadingStateUpdatedToError() {
        runBlockingTest {
            whenever((retrieveEmployeeDetailsUseCase.execute(eq(employeeId)))).thenReturn(
                RetrieveEmployeeDetailsUseCase.Result.Failure
            )

            viewModel.loadEmployeeDetailsIfRequired()

            verify(retrieveEmployeeDetailsUseCase).execute(employeeId)
            assertEquals(EmployeeDetailsContract.LoadingState.ERROR, viewModel.loadingState.value)
        }
    }
    //endregion


    private fun givenEmployee(
        id: Int = employeeId,
        firstName: String = "firstName",
        lastName: String = "lastName",
        gender: String = "gender",
        birthDate: LocalDate = LocalDate.now(),
        imageThumbnail: String? = null,
        image: String? = null
    ) = Employee(
        id = id,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        birthDate = birthDate,
        imageThumbnail = imageThumbnail?.let(::Base64EncodedString),
        image = image?.let(::Base64EncodedString)
    )


    private companion object {
        const val employeeId: Int = 1
    }
}
