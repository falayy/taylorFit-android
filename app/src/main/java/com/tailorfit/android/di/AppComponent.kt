/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.tailorfit.android.tailorfitapp.completedjobs.CompletedJobsFragment
import com.tailorfit.android.tailorfitapp.customerdetails.CustomerDetailsFragment
import com.tailorfit.android.tailorfitapp.customerdetails.StyleImagesPagerFragment
import com.tailorfit.android.tailorfitapp.gig.AddGigStyleFragment
import com.tailorfit.android.tailorfitapp.measurement.GetMeasurementFragment
import com.tailorfit.android.tailorfitapp.pendingjobs.PendingJobsFragment
import com.tailorfit.android.tailorfitapp.test.TestyFragment
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
    fun inject(target: CompletedJobsFragment)
    fun inject(target: PendingJobsFragment)
    fun inject(target: CustomerDetailsFragment)
    fun inject(target: GetMeasurementFragment)
    fun inject(target: StyleImagesPagerFragment)
    fun inject(target: AddGigStyleFragment)
    fun inject(target: TestyFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
