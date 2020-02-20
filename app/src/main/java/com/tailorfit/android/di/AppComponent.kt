
package com.tailorfit.android.di

import android.app.Application
import com.tailorfit.android.tailorfitapp.signin.SignInFragment
import com.tailorfit.android.tailorfitapp.signup.SignUpFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIServiceModule::class, ViewModelModule::class])
interface AppComponent {

    // TODO inject stuff
    fun inject(target : SignUpFragment)
    fun inject(target : SignInFragment)


    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
