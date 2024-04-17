package ru.iteco.fmhandroid.data;


import android.os.SystemClock;

import ru.iteco.fmhandroid.page.Auth;
import ru.iteco.fmhandroid.page.Claim;
import ru.iteco.fmhandroid.page.Claims;
import ru.iteco.fmhandroid.page.Controlpanel;
import ru.iteco.fmhandroid.page.Main;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.page.OurMission;

public class screenLoad {
    Auth authPage = new Auth();
    Main mainScreenPage = new Main();
    Claim claimPage = new Claim();
    Claims claimsPage = new Claims();
    News newsPage = new News();
    Controlpanel controlPanelPage = new Controlpanel();
    OurMission ourMissionPage = new OurMission();

    public void readyAuthScreen() {
        SystemClock.sleep(5000);
        try {
            authPage.checkLoadScreen();
            authPage.isAuthScreen();
        }
        catch (Exception e) {
            mainScreenPage.logOut();
            authPage.isAuthScreen();
        }
    }
    public void readyClaimsScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToClaims();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToClaims();
        } finally {
            claimsPage.checkClaimsScreenLoaded();
        }
    }
    public void readyControlPanelScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } finally {
            controlPanelPage.checkControlPanelScreenLoaded();
        }
    }
    public void readyMainScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
        } catch (Exception e) {
            authPage.login();
        } finally {
            mainScreenPage.checkMainScreenLoaded();
        }
    }
    public void readyNewsScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToNews();
        } finally {
            newsPage.checkNewsScreenLoaded();
        }
    }
    public void readyAboutScreen() {
        SystemClock.sleep(5000);
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goOurMission();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goOurMission();
        } finally {
            ourMissionPage.checkOurMissionScreenLoaded();
        }
    }
}