
package com.tailorfit.android.di

import android.app.Application
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.BaseGigFormFragment
import com.tailorfit.android.tailorfitapp.customer.AddCustomerGenderFragment
import com.tailorfit.android.tailorfitapp.customer.AddCustomerNameFragment
import com.tailorfit.android.tailorfitapp.customer.AddCustomerPhoneFragment
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardFragment
import com.tailorfit.android.tailorfitapp.gig.AddGigDetailsFragment
import com.tailorfit.android.tailorfitapp.measurement.FemaleMeasurementFragment
import com.tailorfit.android.tailorfitapp.measurement.MaleMeasurementFragment
import com.tailorfit.android.tailorfitapp.signin.SignInFragment
import com.tailorfit.android.tailorfitapp.signup.SignUpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIServiceModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: SignUpFragment)
    fun inject(target: SignInFragment)
    fun inject(target: BaseCustomerFormFragment)
    fun inject(target: BaseGigFormFragment)
    fun inject(target: AddCustomerNameFragment)
    fun inject(target: AddCustomerPhoneFragment)
    fun inject(target: AddCustomerGenderFragment)
    fun inject(target: AddGigDetailsFragment)
    fun inject(target: MaleMeasurementFragment)
    fun inject(target: FemaleMeasurementFragment)
    fun inject(target: DashBoardFragment)


    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
