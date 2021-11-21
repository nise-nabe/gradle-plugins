package com.nisecoder.gradle.plugin.github.publish

import org.gradle.api.Action
import org.gradle.api.artifacts.repositories.AuthenticationContainer
import org.gradle.api.artifacts.repositories.AuthenticationSupported
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.credentials.Credentials
import org.gradle.api.internal.artifacts.repositories.AuthenticationSupporter
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ProviderFactory
import org.gradle.internal.reflect.Instantiator

abstract class AuthenticationSupportedSupport(
    instantiator: Instantiator,
    authenticationContainer: AuthenticationContainer,
    objectFactory: ObjectFactory,
    private val providerFactory: ProviderFactory,
): AuthenticationSupported {
    abstract fun getName(): String
    abstract fun setName(name: String)

    private val delegate: AuthenticationSupporter = AuthenticationSupporter(instantiator, objectFactory, authenticationContainer, providerFactory)

    override fun getCredentials(): PasswordCredentials {
        return delegate.credentials
    }

    override fun <T : Credentials> getCredentials(credentialsType: Class<T>): T {
        return delegate.getCredentials(credentialsType)
    }

    override fun credentials(action: Action<in PasswordCredentials>) {
        delegate.credentials(action)
    }

    override fun <T : Credentials?> credentials(credentialsType: Class<T>, action: Action<in T>) {
        delegate.credentials(credentialsType, action)
    }

    override fun credentials(credentialsType: Class<out Credentials>) {
        delegate.credentials(credentialsType, providerFactory.provider { getName() })
    }

    override fun authentication(action: Action<in AuthenticationContainer>) {
        delegate.authentication(action)
    }

    override fun getAuthentication(): AuthenticationContainer {
        return delegate.authentication
    }
}
