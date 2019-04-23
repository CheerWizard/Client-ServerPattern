package com.example.mockup.dependency_injection.modules;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@Singleton
@Module
public class DisposableModule {
    @Contract(value = " -> new", pure = true)
    @NotNull
    @Singleton
    @Provides
    static Disposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}