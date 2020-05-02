package com.tailorfit.android.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tailorfit.android.tailorfitapp.customer.AddCustomerViewModel
import com.tailorfit.android.tailorfitapp.gig.GigViewModel
import com.tailorfit.android.tailorfitapp.measurement.MeasurementViewModel
import com.tailorfit.android.tailorfitapp.signin.SignInViewModel
import com.tailorfit.android.tailorfitapp.signup.SignUpViewModel
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindContactSourcesViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GigViewModel::class)
    abstract fun bindGigViewModel(viewModel: GigViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddCustomerViewModel::class)
    abstract fun bindCustomerViewModel(viewModel: AddCustomerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MeasurementViewModel::class)
    abstract fun bindMeasurementViewModel(viewModel: MeasurementViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    abstract fun bindDashBoardViewModel(viewModel: DashBoardViewModel) : ViewModel
}
