package com.company;

import com.company.presenter.MainViewPresenter;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());
        MainViewPresenter mainViewPresenter = injector.getInstance(MainViewPresenter.class);
        mainViewPresenter.runWelcomeMenu();
    }
}
